package net.smartbridge.common;

import net.smartbridge.api.SmartBridgeAPI;
import net.smartbridge.api.SmartBridgePlugin;
import net.smartbridge.api.exceptions.BridgeException;
import net.smartbridge.api.servers.ISmartServerManager;
import net.smartbridge.api.util.LibInfo;
import net.smartbridge.common.drivers.redisson.RedissonDriver;
import net.smartbridge.api.exceptions.DriverConnectionException;
import net.smartbridge.common.servers.SmartServerManager;

public class SmartBridgeImplementation extends SmartBridgeAPI {

    //  Managers
    private final SmartServerManager smartServerManager;

    //  Drivers
    private RedissonDriver redissonDriver;

    public SmartBridgeImplementation(SmartBridgePlugin smartBridgePlugin) {
        super(smartBridgePlugin);

        try {
            this.initConnections();
        } catch (BridgeException e) {
            throw new RuntimeException(e);
        }

        this.smartServerManager = new SmartServerManager();
    }

    @Override
    public void initConnections() throws BridgeException {
        this.redissonDriver = new RedissonDriver(
                getPlugin().getRedissonConfig()
        );

        if(!redissonDriver.exists()) {
            throw new DriverConnectionException("Redisson driver connection isn't connected, maybe look at the database configuration");
        }
    }

    @Override
    public ISmartServerManager getServerManager() {
        return smartServerManager;
    }

    public RedissonDriver getRedissonDriver() {
        return redissonDriver;
    }

    @Override
    public LibInfo getApiInfo() {
        return null;
    }
}
