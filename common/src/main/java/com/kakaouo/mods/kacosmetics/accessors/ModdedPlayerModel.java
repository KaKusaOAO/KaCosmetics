package com.kakaouo.mods.kacosmetics.accessors;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.geom.ModelPart;

public interface ModdedPlayerModel {
    // void renderGrass(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int j);
    // void renderEeveeEars(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int j);
    // void renderCatEars(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int j);
    // void renderCatTail(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int j);

    ModelPart getRoot();
}
