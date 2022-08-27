package com.kakaouo.mods.kacosmetics.util;

import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.resources.ResourceLocation;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;

public enum SkinModifier {
    ;

    private static final Set<ResourceLocation> GRASS_TEXTURES = new HashSet<>();

    public static void registerGrassPlayerSkin(ResourceLocation location) {
        GRASS_TEXTURES.add(location);
    }

    public static boolean isGrassSkin(ResourceLocation location) {
        return GRASS_TEXTURES.contains(location);
    }

    public static boolean applyGrassModification(NativeImage bitmap) {
        if (!KakaUtils.colorAtPixel(bitmap, 63, 0).equals(new Color(0x3acb28))) return false;
        if (!KakaUtils.colorAtPixel(bitmap, 62, 0).equals(new Color(0xf9ca8b))) return false;
        if (!KakaUtils.colorAtPixel(bitmap, 61, 0).equals(new Color(0xff859b))) return false;

        // 視需求套用專屬頭頂
        if (KakaUtils.colorAtPixel(bitmap, 60, 0).equals(new Color(0xfffffe))) {
            // sourceX, sourceY, offsetX, offsetY, width, height, flipX, flipY
            bitmap.copyRect(32, 0, 8, 0, 8, 8, false, false);
        }
        return true;
    }
}
