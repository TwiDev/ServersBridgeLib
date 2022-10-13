package net.smartbridge.api.drivers;

public abstract class RedissonDriverConfig implements DriverConfig{

    private String address, password;
    private int port, database;

    public RedissonDriverConfig host(String address) {
        this.address = address;

        return this;
    }

    public RedissonDriverConfig port(int port) {
        this.port = port;

        return this;
    }

    public RedissonDriverConfig password(String password) {
        this.password = password;

        return this;
    }

    public RedissonDriverConfig database(int database) {
        this.database = database;

        return this;
    }

    public int getDatabase() {
        return database;
    }

    public String getAddress() {
        return address;
    }

    public String getPassword() {
        return password;
    }

    public int getPort() {
        return port;
    }

    @Override
    public DriverType getDriverType() {
        return DriverType.REDISSON;
    }
}
