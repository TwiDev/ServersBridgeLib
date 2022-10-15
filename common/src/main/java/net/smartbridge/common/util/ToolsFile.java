package net.smartbridge.common.util;

import lombok.Getter;

import java.io.File;

public class ToolsFile {

    @Getter
    final File file;

    public ToolsFile(File file) {
        this.file = file;
    }

    public ToolsFile(String path) {
        this.file = new File(path);
    }

    /**
     * For resource project files
     *
     * @param path path of the file in resource dir
     * @param classLoader classloader
     */
    public ToolsFile(String path, ClassLoader classLoader) {
        this.file = new File(
                classLoader.getResource(path).getPath().replaceAll("%20"," ")
        );
    }

}
