package net.smartbridge.common.drivers.redisson;

import lombok.Getter;
import net.smartbridge.api.SmartBridgeAPI;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;

import java.util.HashMap;
import java.util.UUID;

public class RedissonModel<E> {

    private static final String PATH_SEPARATOR = "/";

    @Getter
    private final RedissonClient connection;

    @Getter
    private final Class<E> redisKey;

    @Getter
    private final UUID uuid;

    @Getter
    private final String path;

    @Getter
    private String name;

    @Getter
    private boolean exists = true;

    public RedissonModel(final Class<E> redisKey, final UUID uuid, final RedissonClient driver) {
        this(redisKey, uuid, null, driver);
    }

    public RedissonModel(final Class<E> redisKey, final UUID uuid, HashMap<E, Object> values, final RedissonClient driver) {
        this.redisKey = redisKey;
        this.uuid = uuid;

        RedissonKeys key = redisKey.getAnnotation(RedissonKeys.class);

        if (key != null && redisKey.isEnum()) {
            this.name = key.name();

            if (!name.isEmpty()) {
                this.name = name + PATH_SEPARATOR;
            }
        } else {
            this.exists = false;
            throw new NullPointerException("Your redis enum don't have redis annotation");
        }

        this.connection = driver;
        this.path = name + uuid.toString() + PATH_SEPARATOR;

        if(values != null) {
            values.forEach((e, o) -> {
                connection.getBucket(path + e.toString().toLowerCase()).setAsync(o);
            });
        }

    }

    public Object get(E e) {
        return connection.getBucket(path + e.toString().toLowerCase()).get();
    }

    public String getString(E e) {
        return this.get(e).toString();
    }

    public long getLong(E e) {
        return connection.getAtomicLong(path + e.toString().toLowerCase()).get();
    }

    public boolean getBoolean(E e) {
        return Boolean.parseBoolean(getString(e));
    }

    public void set(E e, Object v) {
        connection.getBucket(path + e.toString().toLowerCase()).setAsync(v);
    }

    public void setString(E e, String v) {
        connection.getBucket(path + e.toString().toLowerCase()).setAsync(v);
    }

    public void setLong(E e, long v) {
        connection.getAtomicLong(path + e.toString().toLowerCase()).setAsync(v);
    }

    public void setBoolean(E e, boolean v) {
        this.setString(e, Boolean.toString(v));
    }

    public <W,V> RMap<W, V> getHashMap(E e) {
        return connection.getMap(path + e.toString().toLowerCase());
    }

    public void clearCache() {
        for (E enumConstant : redisKey.getEnumConstants()) {
            connection.getBucket(path + enumConstant.toString().toLowerCase()).deleteAsync();
        }

        SmartBridgeAPI.getInstance().getLogger().log("Clear Redisson Model for element " + uuid.toString());
    }

}
