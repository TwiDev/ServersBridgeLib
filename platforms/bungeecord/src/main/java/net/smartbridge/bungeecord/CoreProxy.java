package net.smartbridge.bungeecord;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;
import net.smartbridge.api.SmartBridgePlugin;
import net.smartbridge.api.bridge.ServerSide;
import net.smartbridge.api.drivers.RedissonDriverConfig;
import net.smartbridge.api.exceptions.BridgeException;
import net.smartbridge.api.exceptions.ConfigException;
import net.smartbridge.api.exceptions.DatabaseException;
import net.smartbridge.api.util.ServerIP;
import net.smartbridge.bungeecord.listeners.servers.ServersListeners;
import net.smartbridge.bungeecord.redisson.ServerMessageListener;
import net.smartbridge.common.SmartBridgeImplementation;
import net.smartbridge.common.config.DatabasesConfig;
import net.smartbridge.common.config.ProxyConfig;
import net.smartbridge.common.util.ToolsFile;

public class CoreProxy extends Plugin implements SmartBridgePlugin {

    private static String token = null;

    private static CoreProxy instance;

    private SmartBridgeImplementation smartBridgeImplementation;

    private DatabasesConfig databasesConfig;
    private ProxyConfig proxyConfig;

    private ServerIP serverIP;

    @Override
    public void onEnable() {
        instance = this;

        try {
            this.initServer();
        } catch (BridgeException e) {
            throw new RuntimeException(e);
        }

        new ServerMessageListener();

        PluginManager pluginManager = getProxy().getPluginManager();
        pluginManager.registerListener(this, new ServersListeners());

        super.onEnable();
    }

    @Override
    public void onDisable() {
        super.onDisable();
    }

    @Override
    public void initServer() throws BridgeException {
        this.serverIP = new ServerIP(
                "127.0.0.1", 25565
        );

        this.databasesConfig = new DatabasesConfig().parseConfig(
                new ToolsFile("databases.json", this.getClassLoader())
        );

        this.proxyConfig = new ProxyConfig().parseConfig(
                new ToolsFile("config.json", this.getClassLoader())
        );

        token = proxyConfig.getBridgeProperties().getLinkToken();
        if(token == null || token.length() < 10) {
            throw new ConfigException("You have not defined a token for the connection with your proxy or your token is not long enough, please define a token in the configuration of the plugin with a minimum of 10 characters");
        }

        if(databasesConfig == null || !databasesConfig.exists()) {
            throw new DatabaseException("Cannot read database configuration file");
        }

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
        return databasesConfig.getRedis();
    }

    public ClassLoader getClassLoader() {
        return this.getClass().getClassLoader();
    }

    public SmartBridgeImplementation getSmartBridgeImplementation() {
        return smartBridgeImplementation;
    }

    public static String getToken() {
        return token;
    }

    public static CoreProxy getInstance() {
        return instance;
    }
}
