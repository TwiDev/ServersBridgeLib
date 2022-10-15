package net.smartbridge.common.util;

import com.google.gson.Gson;
import lombok.Getter;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

//Author: @TwiDev
public class JsonLib {

    @Getter
    private final JSONParser parser;

    /**
     * Construct Json Lib
     */
    public JsonLib() {
        this.parser = new JSONParser();
    }

    /**
     * Save Json Data into a file
     *
     * @param c                 Data class
     * @param jsonFile          Json File instance
     * @param createIfNotExists create the file in not exist
     * @param <E>               Data type
     */
    public <E> void save(E c, ToolsFile jsonFile, boolean createIfNotExists) {
        File file = jsonFile.getFile();

        if (file.exists()) {

            JSONObject object = null;
            try {
                object = (JSONObject) this.getParser().parse(
                        new Gson().toJson(c)
                );
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (object == null)
                return;

            try (FileWriter w = new FileWriter(file)) {

                try {
                    w.write(object.toJSONString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    w.flush();
                    w.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (createIfNotExists) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Read json file a translate into a data class
     *
     * @param translateClass data
     * @param file           ToolsFile instance
     * @param <E>            Data class type
     * @return Data class
     */
    public <E> E read(Class<E> translateClass, ToolsFile file) {

        File f = file.getFile();

        try {
            JSONObject object = (JSONObject) this.getParser().parse(new FileReader(f));

            return (E) new Gson().fromJson(object.toJSONString(), translateClass);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
            System.out.println("Error, file not found");
        }

        try {
            return (E) translateClass.getConstructors()[0].newInstance();
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Map read(ToolsFile file) {

        File f = file.getFile();

        try {
            JSONObject object = (JSONObject) this.getParser().parse(new FileReader(f));

            return new Gson().fromJson(object.toJSONString(), Map.class);

        } catch (IOException | ParseException e) {
            e.printStackTrace();
            System.out.println("Error, file not found");
        }

        return null;
    }

    public void save(ToolsFile jsonFile, HashMap<String, Object> values, boolean createIfNotExists) {
        File file = jsonFile.getFile();

        if (file.exists()) {

            JSONObject object = null;
            try {
                object = (JSONObject) this.getParser().parse(
                        new Gson().toJson(values)
                );
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if (object == null)
                return;

            try (FileWriter w = new FileWriter(file)) {

                try {
                    w.write(object.toJSONString());
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    w.flush();
                    w.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (createIfNotExists) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void save(ToolsFile file, HashMap<String, Object> values) {
        this.save(file, values, true);
    }


}
