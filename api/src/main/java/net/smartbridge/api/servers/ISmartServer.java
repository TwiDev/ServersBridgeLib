package net.smartbridge.api.servers;

import net.smartbridge.api.util.ServerIP;

import java.util.UUID;

public interface ISmartServer {

    @Deprecated
    void initServer();

    void disconnectServer();

    UUID getUUID();

    String getName();

    String getGroup();

    ServerIP getServerIP();

    ServerType getServerType();

    void setServerIP(ServerIP serverIP);

    void setGroup(String group);

    void setServerType(ServerType serverType);

    long getOnlinePlayers();

    boolean isLoaded();
}
