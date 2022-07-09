package com.kakaouo.mods.kacosmetics.accessors;

import net.minecraft.client.player.AbstractClientPlayer;

import java.util.function.Consumer;

public interface ModdedHttpTexture {
    void callIfPlayerHasGrass(Runnable callback);
}
