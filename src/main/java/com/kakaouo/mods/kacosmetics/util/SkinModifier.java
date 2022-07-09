package com.kakaouo.mods.kacosmetics.util;

import com.kakaouo.mods.kacosmetics.KaCosmetics;
import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.renderer.texture.HttpTexture;
import net.minecraft.resources.ResourceLocation;

import java.util.HashSet;
import java.util.Set;

public enum SkinModifier {
    ;

    private static Set<ResourceLocation> VALID_TEXTURES = new HashSet<>();

    public static void addValidGrassSkinLocation(ResourceLocation location) {
        VALID_TEXTURES.add(location);
    }

    public static boolean isValidGrassSkin(ResourceLocation location) {
        return VALID_TEXTURES.contains(location);
    }

    public static boolean applyGrassModification(NativeImage bitmap) {
        // 糰子色票 (以下整數是 ABGR 格式)
        if (bitmap.getPixelRGBA(63, 0) != 0xff28cb3a) return false;
        if (bitmap.getPixelRGBA(62, 0) != 0xff8bcaf9) return false;
        if (bitmap.getPixelRGBA(61, 0) != 0xff9b85ff) return false;

        // 視需求套用專屬頭頂
        if (bitmap.getPixelRGBA(60, 0) == 0xfffeffff) {
            // sourceX, sourceY, offsetX, offsetY, width, height, flipX, flipY
            bitmap.copyRect(32, 0, 8, 0, 8, 8, false, false);
        }
        return true;
    }
}
