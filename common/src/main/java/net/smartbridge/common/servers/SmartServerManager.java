package net.smartbridge.common.servers;

import net.smartbridge.api.servers.ISmartServer;
import net.smartbridge.api.servers.ISmartServerManager;
import net.smartbridge.api.servers.ServerType;

import java.util.UUID;

public class SmartServerManager implements ISmartServerManager {

    @Override
    public ISmartServer getServer(String name) {
        return null;
    }

    @Override
    public ISmartServer getServer(UUID uuid) {
        return null;
    }

    @Override
    public ISmartServer createServerByGroup(ServerType serverType, String group) {
        return null;
    }

    @Override
    public ISmartServer createServerByName(ServerType serverType, String name) {
        return null;
    }

    @Override
    public void delServer(ISmartServer iSmartServer) {

    }

    @Override
    public boolean isServerExists(String name) {
        return false;
    }

    @Override
    public boolean isServerExists(UUID uuid) {
        return false;
    }
}
