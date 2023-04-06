package com.kakaouo.mods.kacosmetics;

import com.kakaouo.mods.kacosmetics.util.PlayerModelRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class KaCosmetics {
    public static final String NAMESPACE = "kacosmetics";
    public static final Logger LOGGER = LogManager.getLogger("KaCosmetics");

    public static void init() {
        Platform platform = PlatformManager.getPlatform();
        String name = platform.getPlatformName();
        LOGGER.info("Initializing KaCosmetics on {}...", name);

        if (name.equals("Forge")) {
            LOGGER.warn("There might be problems with Mixin on Forge, which make this mod unusable!");
            LOGGER.warn("Using Fabric or Quilt is recommended.");
        }

        PlayerModelRegistry.load();
    }
}
