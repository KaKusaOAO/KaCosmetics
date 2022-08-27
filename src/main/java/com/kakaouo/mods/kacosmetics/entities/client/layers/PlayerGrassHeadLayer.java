package com.kakaouo.mods.kacosmetics.entities.client.layers;

import com.kakaouo.mods.kacosmetics.accessors.ModdedPlayerModel;
import com.kakaouo.mods.kacosmetics.util.SkinModifier;
import com.mojang.authlib.GameProfile;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class PlayerGrassHeadLayer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {
    public PlayerGrassHeadLayer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> renderLayerParent) {
        super(renderLayerParent);
    }

    @Override
    public void render(@NotNull PoseStack poseStack, @NotNull MultiBufferSource multiBufferSource, int i, @NotNull AbstractClientPlayer player, float f, float g, float h, float j, float k, float l) {
        if (!player.isSkinLoaded() || player.isInvisible()) return;
        GameProfile profile = player.getGameProfile();
        if (profile == null || !profile.isComplete()) return;

        ResourceLocation location = player.getSkinTextureLocation();
        if (!SkinModifier.isGrassSkin(location)) return;

        VertexConsumer vertexConsumer = multiBufferSource.getBuffer(RenderType.entityTranslucent(location));
        int m = LivingEntityRenderer.getOverlayCoords(player, 0.0F);
        ((ModdedPlayerModel) getParentModel()).renderGrass(poseStack, vertexConsumer, i, m);
    }
}
