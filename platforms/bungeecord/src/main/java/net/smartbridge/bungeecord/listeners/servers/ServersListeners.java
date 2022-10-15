package net.smartbridge.bungeecord.listeners.servers;

import net.md_5.bungee.api.ServerConnectRequest;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import net.smartbridge.api.servers.ISmartServer;
import net.smartbridge.api.servers.ISmartServerManager;
import net.smartbridge.api.servers.ServerType;
import net.smartbridge.common.SmartBridgeImplementation;

public class ServersListeners implements Listener {

    @EventHandler
    public void onServerSwitch(ServerConnectRequest serverConnectRequest) {
        ISmartServerManager serverManager = SmartBridgeImplementation.getInstance().getServerManager();
        String serverName = serverConnectRequest.getTarget().getName();

        if(serverManager.isServerExists(serverName)) {
            ISmartServer smartServer = serverManager.getServer(serverName);

            if(smartServer.getServerType() == ServerType.BALANCER) {

                //TODO: SEND PLAYER TO A SERVER IN THE TARGET GROUP

            }

        }

    }

}
