package net.smartbridge.common.drivers.redisson.messengers;

public class ConfigRedissonMessage extends RedissonMessage{

    private final String configName;

    public ConfigRedissonMessage(String configName) {
        super("wsh");

        this.configName = configName;
    }

    public String getConfigName() {
        return configName;
    }
}
