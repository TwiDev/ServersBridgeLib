package net.smartbridge.api;

import net.smartbridge.api.servers.ISmartServerManager;
import net.smartbridge.api.util.LibInfo;

public interface SmartBridgeAPI {

    ISmartServerManager getServerManager();

    LibInfo getApiInfo();

    SmartBridgePlugin getPlugin();

}
