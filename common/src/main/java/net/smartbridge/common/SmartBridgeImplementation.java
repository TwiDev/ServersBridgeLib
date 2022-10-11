package net.smartbridge.common;

import net.smartbridge.api.SmartBridgeAPI;
import net.smartbridge.api.SmartBridgePlugin;
import net.smartbridge.api.servers.ISmartServerManager;
import net.smartbridge.api.util.LibInfo;
import net.smartbridge.common.servers.SmartServerManager;

public class SmartBridgeImplementation implements SmartBridgeAPI {

    // Managers
    private final SmartServerManager smartServerManager;

    private final SmartBridgePlugin parent;

    public SmartBridgeImplementation(SmartBridgePlugin smartBridgePlugin) {
        this.parent = smartBridgePlugin;

        this.smartServerManager = new SmartServerManager();
    }

    @Override
    public ISmartServerManager getServerManager() {
        return smartServerManager;
    }

    @Override
    public LibInfo getApiInfo() {
        return null;
    }

    @Override
    public SmartBridgePlugin getPlugin() {
        return parent;
    }
}
