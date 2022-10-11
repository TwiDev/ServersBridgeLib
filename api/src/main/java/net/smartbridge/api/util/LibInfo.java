package net.smartbridge.api.util;

import lombok.Getter;

@Getter
public class LibInfo {

    /**
     * Version of the API maven build
     *
     * placeholder = default value before maven replacing
     */
    public final static String API_VERSION = "placeholder";

    /**
     * Latest GitHub commit id of the API before the maven build
     *
     * placeholder = default value before maven replacing
     */
    public final static String GIT_COMMIT = "placeholder";

    /**
     * Version of the official attachment core plugin SmartServersBridge, the version the api is running on
     *
     * placeholder = default value before maven replacing
     */
    public final static String CORE_ATTACHMENT_VERSION = "placeholder";
}
