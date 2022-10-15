package net.smartbridge.common.config;

import lombok.Getter;
import net.smartbridge.api.bridge.ServerSide;
import net.smartbridge.api.drivers.RedissonDriverConfig;

@Getter
@ConfigInfo(fileName = "databases.json", serverSide = ServerSide.STANDALONE)
public class DatabasesConfig extends Config<DatabasesConfig> {

    private RedissonDriverConfig redis;

    public boolean exists() {
        return redis != null;
    }


}
