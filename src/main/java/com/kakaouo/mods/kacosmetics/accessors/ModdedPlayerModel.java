package com.kakaouo.mods.kacosmetics.accessors;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import org.spongepowered.asm.mixin.Mixin;

public interface ModdedPlayerModel {
    void renderGrass(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int j);
}
