package net.smartbridge.api.util;

import java.util.logging.Level;
import java.util.logging.Logger;

public class BridgeLogger {

    private final String name;
    private final Logger logger;

    public BridgeLogger(String name) {
        this.name = name;
        this.logger = Logger.getLogger(name);
    }

    public String getName() {
        return name;
    }

    public Logger getLogger() {
        return logger;
    }

    public void log(String message) {
        logger.log(Level.INFO, message);
    }

    public void log(String message, Level level) {
        logger.log(level, message);
    }

    public void warn(String message) {
        logger.warning(message);
    }

    public void error(String message) {
        logger.severe(message);
    }

}
