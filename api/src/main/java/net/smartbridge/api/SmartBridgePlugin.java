package net.smartbridge.api;

import net.smartbridge.api.bridge.ServerSide;

public interface SmartBridgePlugin {

    SmartBridgeAPI getApi();

    ServerSide getPluginSide();

    String getServerName();
}
