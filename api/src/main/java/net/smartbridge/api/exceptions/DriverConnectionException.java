package net.smartbridge.api.exceptions;

public class DriverConnectionException extends BridgeException{

    public DriverConnectionException(String message) {
        super("Cannot init driver connection " + message);
    }
}
