package net.smartbridge.bungeecord;

import net.md_5.bungee.api.plugin.Plugin;
import net.smartbridge.api.SmartBridgePlugin;
import net.smartbridge.api.bridge.ServerSide;
import net.smartbridge.api.drivers.RedissonDriverConfig;
import net.smartbridge.api.util.ServerIP;
import net.smartbridge.common.SmartBridgeImplementation;

public class CoreProxy extends Plugin implements SmartBridgePlugin {

    private SmartBridgeImplementation smartBridgeImplementation;

    private ServerIP serverIP;

    @Override
    public void onEnable() {
        this.initServer();

        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @Override
    public void initServer() {
        this.serverIP = new ServerIP(
                "127.0.0.1", 25565
        );

        this.smartBridgeImplementation = new SmartBridgeImplementation(this);

    }

    @Override
    public ServerSide getPluginSide() {
        return ServerSide.BUNGEECORD;
    }

    @Override
    public String getServerName() {
        return "unknown-bungeecord";
    }

    @Override
    public Object getPlugin() {
        return this;
    }

    @Override
    @Deprecated
    public ServerIP getServerIP() {
        return serverIP;
    }

    @Override
    public RedissonDriverConfig getRedissonConfig() {
        return null;
    }

    public SmartBridgeImplementation getSmartBridgeImplementation() {
        return smartBridgeImplementation;
    }
}
