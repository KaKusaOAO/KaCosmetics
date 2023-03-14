package com.kakaouo.mods.kacosmetics.quilt;

import com.kakaouo.mods.kacosmetics.KaCosmetics;
import com.kakaouo.mods.kacosmetics.PlatformManager;
import com.kakaouo.mods.kacosmetics.fabriclike.FabricLikePlatform;
import net.fabricmc.api.EnvType;
import net.minecraft.SharedConstants;
import org.jetbrains.annotations.NotNull;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.loader.api.minecraft.MinecraftQuiltLoader;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;

public class ModQuilt implements ModInitializer, FabricLikePlatform {
    @Override
    public @NotNull Type getPlatformType() {
        return Type.QUILT;
    }

    @Override
    public void onInitialize(ModContainer mod) {
        // We don't care about registering data fixers for our entities, so
        // we will just stop Minecraft from checking the data fixer schema.
        SharedConstants.CHECK_DATA_FIXER_SCHEMA = false;

        PlatformManager.setPlatform(this);
        KaCosmetics.init();
    }

    @Override
    public boolean isClient() {
        return MinecraftQuiltLoader.getEnvironmentType() == EnvType.CLIENT;
    }
}
