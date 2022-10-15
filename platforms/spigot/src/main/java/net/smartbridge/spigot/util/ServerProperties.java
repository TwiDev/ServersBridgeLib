package net.smartbridge.spigot.util;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

public enum ServerProperties {

    SERVER_NAME("server-name"),
    SERVER_TYPE("server-type"),
    MAX_PLAYERS("max-players"),
    SERVER_IP("server-ip"),
    SERVER_PORT("server-port"),
    RESOURCE_PACK("resource-pack"),
    RESOURCE_PACK_SHA("resource-pack-sha1"),
    WHITELIST("white-list"),
    ONLINE_MODE("online-mode");

    final String key;

    ServerProperties(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public static class Properties {

        PropertiesConfiguration configuration = null;

        public Properties(JavaPlugin plugin) {
            try {
                this.configuration = new PropertiesConfiguration(plugin.getServer().getWorldContainer().getAbsolutePath() + "/server.properties");
            } catch (ConfigurationException e) {
                e.printStackTrace();
            }
        }

        public boolean exists() {
            return configuration != null;
        }

        public boolean contains(ServerProperties properties) {
            return configuration.containsKey(properties.getKey());
        }

        public String get(ServerProperties properties) {
            return configuration.getString(properties.getKey());
        }

        public int getInt(ServerProperties properties) {
            return configuration.getInt(properties.getKey());
        }

        public PropertiesConfiguration getConfiguration() {
            return configuration;
        }
    }
}
