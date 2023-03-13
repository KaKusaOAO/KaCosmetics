package com.kakaouo.mods.kacosmetics;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class KaCosmetics {
    public static final String NAMESPACE = "kacosmetics";
    public static final Logger LOGGER = LogManager.getLogger("KaCosmetics");

    public static void init() {
        LOGGER.debug("Initializing KaCosmetics...");
    }
}
