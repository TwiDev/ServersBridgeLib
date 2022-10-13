package net.smartbridge.common.drivers.redisson;

import net.smartbridge.api.drivers.DriverType;
import net.smartbridge.api.drivers.RedissonDriverConfig;
import net.smartbridge.common.drivers.Driver;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.codec.JsonJacksonCodec;
import org.redisson.config.Config;

@Driver.DriverInfo(type = DriverType.REDISSON)
public class RedissonDriver extends Driver<RedissonDriverConfig> {

    private RedissonClient connection;

    public RedissonDriver(RedissonDriverConfig driverConfig) {
        super(driverConfig);

        this.initConnection();
    }

    @Override
    public void initConnection() {
        RedissonDriverConfig driverConfig = this.getDriverConfig();
        Config config = new Config();
        config.setCodec(new JsonJacksonCodec());
        config.setThreads(8);
        config.setNettyThreads(8);

        config.useSingleServer()
                .setAddress("redis://" + driverConfig.getAddress() + ":" + driverConfig.getPort())
                .setDatabase(driverConfig.getDatabase())
                .setPassword(driverConfig.getPassword());


        connection = Redisson.create(config);
    }

    /**
     * Close and disconnect driver
     */
    @Deprecated
    public void closeConnection() {
        connection.shutdown();
    }


    /**
     * Get main driver connection
     *
     * @return Redisson Client
     */
    public RedissonClient getConnection() {
        return connection;
    }

    public boolean exists() {
        return connection != null;
    }
}
