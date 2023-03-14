package com.kakaouo.mods.kacosmetics.entities.client.layers;

import com.kakaouo.mods.kacosmetics.accessors.ModdedPlayerModel;
import com.kakaouo.mods.kacosmetics.util.Modifiers;
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

public abstract class AbstractPlayerModifierLayer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {
    private final Modifiers modifier;
    private final RendererProvider provider;

    public AbstractPlayerModifierLayer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> renderLayerParent, Modifiers modifier, RendererProvider provider) {
        super(renderLayerParent);
        this.modifier = modifier;
        this.provider = provider;
    }

    @Override
    public void render(@NotNull PoseStack poseStack, @NotNull MultiBufferSource multiBufferSource, int i, @NotNull AbstractClientPlayer player, float f, float g, float h, float j, float k, float l) {
        if (!player.isSkinLoaded() || player.isInvisible()) return;
        GameProfile profile = player.getGameProfile();
        if (profile == null || !profile.isComplete()) return;

        ResourceLocation location = player.getSkinTextureLocation();
        if (!SkinModifier.isOfModifier(modifier, location)) return;

        VertexConsumer vertexConsumer = multiBufferSource.getBuffer(RenderType.entityTranslucent(location));
        int m = LivingEntityRenderer.getOverlayCoords(player, 0.0F);
        this.provider.render(this.getParentModel(), poseStack, vertexConsumer, i, m);
    }

    protected interface RendererProvider {
        void render(PlayerModel<AbstractClientPlayer> model, PoseStack poseStack, VertexConsumer vertexConsumer, int i, int j);
    }
}
