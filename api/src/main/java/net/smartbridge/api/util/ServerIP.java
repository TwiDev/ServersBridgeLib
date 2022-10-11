package net.smartbridge.api.util;

public class ServerIP {

    private final String host;

    private final int port;

    public ServerIP(String host, int port) {
        this.host = host;
        this.port = port;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }
}
