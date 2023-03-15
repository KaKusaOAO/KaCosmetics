package com.kakaouo.mods.kacosmetics.util;

import com.mojang.blaze3d.platform.NativeImage;

public enum Modifiers {
    GRASS(SkinModifier::applyGrassModification),
    CAT_EARS(SkinModifier::applyCatEarsModification),
    CAT_TAIL(SkinModifier::applyCatTailModification),
    EEVEE(SkinModifier::applyEeveeModification);

    private final ModifyOperator modifier;

    Modifiers(ModifyOperator modifier) {
        this.modifier = modifier;
    }

    public boolean modify(NativeImage image) {
        return this.modifier.modify(image);
    }

    @FunctionalInterface
    private interface ModifyOperator {
        boolean modify(NativeImage image);
    }
}
