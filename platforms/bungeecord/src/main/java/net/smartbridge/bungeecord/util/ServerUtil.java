package net.smartbridge.bungeecord.util;

import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.smartbridge.bungeecord.CoreProxy;
import reactor.core.CoreSubscriber;

import java.util.Map;

public class ServerUtil {

    public static boolean serverExists(String name) {
        return CoreProxy.getInstance().getProxy().getServers().containsKey(name);
    }

    public static void removeServer(String name) {
        if (!serverExists(name)) {
            return;
        }

        ServerInfo info = getServerInfo(name);

        for (ProxiedPlayer p : info.getPlayers()) {
            p.connect(getServers().get(p.getPendingConnection().getListener().getFallbackServer()));
        }

        getServers().remove(name);
    }

    public static ServerInfo getServerInfo(String name) {
        return getServers().get(name);
    }

    public static Map<String, ServerInfo> getServers() {
        return CoreProxy.getInstance().getProxy().getServers();
    }
}
