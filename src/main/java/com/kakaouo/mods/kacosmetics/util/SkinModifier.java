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
        boolean useAltHeadTop = false;
        boolean useAltHatTop = false;
        boolean valid = true;

        Color keyColor = KakaUtils.colorAtPixel(bitmap, 60, 0);
        if (keyColor.getRed() == 0xff && keyColor.getAlpha() == 0xff) {
            int g = keyColor.getGreen();
            int b = keyColor.getBlue();

            if (g == 0xfe) {
                useAltHeadTop = true;
            } else {
                valid = g == 0xff;
            }

            if (b == 0xfe) {
                useAltHatTop = true;
            } else {
                valid = valid && g == 0xff;
            }
        }

        if (valid && useAltHatTop) {
            // sourceX, sourceY, offsetX, offsetY, width, height, flipX, flipY
            bitmap.copyRect(32, 0, 8, 0, 8, 8, false, false);
        }

        if (valid && useAltHeadTop) {
            bitmap.copyRect(0, 0, 8, 0, 8, 8, false, false);
        }
        return true;
    }
}
