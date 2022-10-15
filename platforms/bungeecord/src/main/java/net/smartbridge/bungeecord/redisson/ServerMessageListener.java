package net.smartbridge.bungeecord.redisson;

import net.md_5.bungee.api.ProxyServer;
import net.smartbridge.api.SmartBridgeAPI;
import net.smartbridge.api.bridge.ServerSide;
import net.smartbridge.api.servers.ISmartServer;
import net.smartbridge.api.servers.ISmartServerManager;
import net.smartbridge.api.servers.ServerType;
import net.smartbridge.api.util.ServerIP;
import net.smartbridge.bungeecord.CoreProxy;
import net.smartbridge.bungeecord.util.ServerUtil;
import net.smartbridge.common.SmartBridgeImplementation;
import net.smartbridge.common.drivers.redisson.messengers.*;

import java.net.InetSocketAddress;
import java.util.UUID;

public class ServerMessageListener extends RedissonMessageListener {

    private final ISmartServerManager serverManager;

    public ServerMessageListener() {
        super(ServerSide.BUNGEECORD);

        this.serverManager = SmartBridgeAPI.getInstance().getServerManager();
    }

    @Override
    public void onMessageReceived(StaticsTopics staticsTopics, RedissonMessage redissonMessage) {
        switch (redissonMessage.getRedissonMessageType()) {
            case LINK_BUNGEECORD:
                String serverName = redissonMessage.getData()[0];
                String token = redissonMessage.getData()[1];

                try {
                    if (token.equals(CoreProxy.getToken())) {
                        ISmartServer iSmartServer;
                        if(!serverManager.isServerExists(serverName)) {
                            iSmartServer = serverManager.createServerByName(ServerType.UNKNOWN, serverName);
                        }else{
                            iSmartServer = serverManager.getServer(serverName);
                        }

                        if(iSmartServer.getUUID() == null) {
                            return;
                        }

                        RedissonMessengersManager.sendMessage(StaticsTopics.TO_SPIGOT, new RedissonMessage(RedissonMessageType.CONFIRM_SYNC_BUNGEECORD, new String[]{iSmartServer.getUUID().toString()}));
                        SmartBridgeImplementation.getInstance().getLogger().log("Confirm synchronization with server " + serverName);
                    }
                } catch (NullPointerException ignore) {}
                break;
            case REGISTER_SERVER:
                UUID uuid = UUID.fromString(
                        redissonMessage.getData()[0]
                );

                if(serverManager.isServerExists(uuid)) {
                    ISmartServer iSmartServer = serverManager.getServer(uuid);
                    ProxyServer proxyServer = CoreProxy.getInstance().getProxy();

                    serverName = iSmartServer.getName();

                    if(proxyServer.getServers().containsKey(serverName)) {
                        ServerUtil.removeServer(serverName);
                    }

                    ServerIP serverIP = iSmartServer.getServerIP();

                    CoreProxy.getInstance().getProxy().constructServerInfo(serverName, new InetSocketAddress(serverIP.getHost(), serverIP.getPort()), "A another Smart Sync Server", false);
                    SmartBridgeImplementation.getInstance().getLogger().log("Register successfully server " + serverName);

                }

                break;
        }
    }
}
