package com.kakaouo.mods.kacosmetics.entities.client.layers;

import com.kakaouo.mods.kacosmetics.accessors.ModdedPlayerModel;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;

public enum PlayerModifierCalls {
    ;

    public static void renderEeveeEars(PlayerModel<AbstractClientPlayer> model, PoseStack poseStack, VertexConsumer vertexConsumer, int i, int j) {
        ((ModdedPlayerModel) model).renderEeveeEars(poseStack, vertexConsumer, i, j);
    }

    public static void renderGrass(PlayerModel<AbstractClientPlayer> model, PoseStack poseStack, VertexConsumer vertexConsumer, int i, int j) {
        ((ModdedPlayerModel) model).renderGrass(poseStack, vertexConsumer, i, j);
    }

    public static void renderCatEars(PlayerModel<AbstractClientPlayer> model, PoseStack poseStack, VertexConsumer vertexConsumer, int i, int j) {
        ((ModdedPlayerModel) model).renderCatEars(poseStack, vertexConsumer, i, j);
    }

    public static void renderCatTail(PlayerModel<AbstractClientPlayer> model, PoseStack poseStack, VertexConsumer vertexConsumer, int i, int j) {
        ((ModdedPlayerModel) model).renderCatTail(poseStack, vertexConsumer, i, j);
    }
}
