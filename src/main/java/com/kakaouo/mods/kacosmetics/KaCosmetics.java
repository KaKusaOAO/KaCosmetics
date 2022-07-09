package com.kakaouo.mods.kacosmetics;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class KaCosmetics implements ModInitializer {
    public static final Logger LOGGER = LogManager.getLogger("KaCosmetics");

    @Override
    public void onInitialize() {
        LOGGER.debug("Initializing KaCosmetics...");
    }
}
