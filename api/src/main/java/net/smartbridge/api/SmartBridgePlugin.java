package net.smartbridge.api;

import net.smartbridge.api.bridge.ServerSide;
import net.smartbridge.api.drivers.RedissonDriverConfig;
import net.smartbridge.api.util.ServerIP;

public interface SmartBridgePlugin {

    ServerSide getPluginSide();

    String getServerName();

    Object getPlugin();

    ServerIP getServerIP();

    RedissonDriverConfig getRedissonConfig();
 }
