package net.smartbridge.api;

import net.smartbridge.api.bridge.ServerSide;
import net.smartbridge.api.drivers.RedissonDriverConfig;
import net.smartbridge.api.exceptions.BridgeException;
import net.smartbridge.api.util.ServerIP;

public interface SmartBridgePlugin {

    void initServer() throws BridgeException;

    ServerSide getPluginSide();

    String getServerName();

    Object getPlugin();

    ServerIP getServerIP();

    RedissonDriverConfig getRedissonConfig();
 }
