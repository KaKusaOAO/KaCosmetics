package com.kakaouo.mods.kacosmetics;

public final class PlatformManager {
    private PlatformManager() {}

    private static Platform instance = null;

    public static void setPlatform(Platform platform) {
        instance = platform;
    }

    public static Platform getPlatform() {
        return instance;
    }
}
