package net.smartbridge.common.servers;

import net.smartbridge.api.servers.ISmartServer;
import net.smartbridge.api.servers.ISmartServerManager;
import net.smartbridge.api.servers.ServerType;
import net.smartbridge.common.SmartBridgeImplementation;

import java.util.UUID;

public class SmartServerManager implements ISmartServerManager {

    @Override
    public ISmartServer getServer(String name) {
        return new SmartServer(name);
    }

    @Override
    public ISmartServer getServer(UUID uuid) {
        if(!isServerExists(uuid)) {
            return null;
        }

        return new SmartServer(uuid);
    }

    @Override
    public ISmartServer createServerByGroup(ServerType serverType, String group) {
        return null;
    }

    @Override
    public ISmartServer createServerByName(ServerType serverType, String name) {
        return new SmartServer(name);
    }

    @Override
    public void delServer(ISmartServer iSmartServer) {
        iSmartServer.disconnectServer();
    }

    @Override
    public boolean isServerExists(String name) {
        return SmartBridgeImplementation.getInstance().getRedissonDriver().getConnection().getMap(SmartServer.getServersMap()).containsKey(name);
    }

    @Override
    public boolean isServerExists(UUID uuid) {
        return SmartBridgeImplementation.getInstance().getRedissonDriver().getConnection().getMap(SmartServer.getServersMap()).containsValue(uuid.toString());
    }
}
