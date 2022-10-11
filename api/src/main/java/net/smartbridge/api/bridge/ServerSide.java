package net.smartbridge.api.bridge;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ServerSide {

    BUNGEECORD("BungeeCord"),
    SPIGOT("Spigot"),
    STANDALONE("Standalone"),
    // Bridge modules
    MODULE("CustomModule");

    private final String platformName;

}
