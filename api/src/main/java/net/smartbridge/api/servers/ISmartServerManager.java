package net.smartbridge.api.servers;

import java.util.UUID;

public interface ISmartServerManager {

    ISmartServer getServer(String name);

    ISmartServer getServer(UUID uuid);

    ISmartServer createServerByGroup(ServerType serverType, String group);

    ISmartServer createServerByName(ServerType serverType, String name);

    void delServer(ISmartServer iSmartServer);

    boolean isServerExists(String name);

    boolean isServerExists(UUID uuid);
}
