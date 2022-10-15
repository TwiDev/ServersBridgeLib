import com.google.gson.Gson;
import net.smartbridge.common.config.GroupConfig;
import net.smartbridge.common.drivers.redisson.messengers.ConfigRedissonMessage;
import net.smartbridge.common.util.ToolsFile;
import org.junit.Test;

public class Debug {

    @Test
    public void run() {
        GroupConfig groupConfig = new GroupConfig().parseConfig( new ToolsFile("groups.json", this.getClass().getClassLoader()));

        ConfigRedissonMessage configRedissonMessage = new ConfigRedissonMessage("ssasafafasdf");

        String json = new Gson().toJson(configRedissonMessage);
        System.out.println(json);

        Object redissonMessage = new Gson().fromJson(json, Object.class);

        ConfigRedissonMessage r = (ConfigRedissonMessage) redissonMessage;
        System.out.println(r.getConfigName());
    }

}
