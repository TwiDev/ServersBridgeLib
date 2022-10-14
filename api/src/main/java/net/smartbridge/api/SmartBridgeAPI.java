package net.smartbridge.api;

import net.smartbridge.api.exceptions.BridgeException;
import net.smartbridge.api.servers.ISmartServerManager;
import net.smartbridge.api.util.BridgeLogger;
import net.smartbridge.api.util.LibInfo;

public abstract class SmartBridgeAPI {

    private static SmartBridgeAPI instance;

    private final SmartBridgePlugin plugin;

    public SmartBridgeAPI(SmartBridgePlugin plugin) {
        instance = this;

        this.plugin = plugin;
    }

    public abstract void initConnections() throws BridgeException;

    public abstract ISmartServerManager getServerManager();

    public abstract BridgeLogger getLogger();

    public abstract LibInfo getApiInfo();

    public SmartBridgePlugin getPlugin() {
        return plugin;
    }

    public static SmartBridgeAPI getInstance() {
        return instance;
    }
}
