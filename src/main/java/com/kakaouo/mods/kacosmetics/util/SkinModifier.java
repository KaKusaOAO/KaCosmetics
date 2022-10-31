package com.kakaouo.mods.kacosmetics.util;

import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.resources.ResourceLocation;

import java.awt.*;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.Set;

public enum SkinModifier {
    ;

    private static final EnumMap<Modifiers, Set<ResourceLocation>> TEXTURES = new EnumMap<>(Modifiers.class);

    @Deprecated
    public static void registerGrassPlayerSkin(ResourceLocation location) {
        registerPlayerSkin(Modifiers.GRASS, location);
    }

    public static void registerPlayerSkin(Modifiers modifier, ResourceLocation location) {
        Set<ResourceLocation> set = TEXTURES.computeIfAbsent(modifier, (m) -> new HashSet<>());
        set.add(location);
    }

    @Deprecated
    public static boolean isGrassSkin(ResourceLocation location) {
        return isOfModifier(Modifiers.GRASS, location);
    }

    public static boolean isOfModifier(Modifiers modifiers, ResourceLocation location) {
        if (!TEXTURES.containsKey(modifiers)) return false;
        return TEXTURES.get(modifiers).contains(location);
    }

    public static boolean applyModification(Modifiers modifiers, NativeImage bitmap) {
        return modifiers.modify(bitmap);
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

    public static boolean applyEeveeModification(NativeImage bitmap) {
        if (!KakaUtils.colorAtPixel(bitmap, 63, 0).equals(new Color(0x51280c))) return false;
        if (!KakaUtils.colorAtPixel(bitmap, 62, 0).equals(new Color(0xc5a068))) return false;
        if (!KakaUtils.colorAtPixel(bitmap, 61, 0).equals(new Color(0xd8c5a1))) return false;

        return true;
    }
}
