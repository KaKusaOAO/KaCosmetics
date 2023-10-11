package com.kakaouo.mods.kacosmetics.entities.client.layers;

import com.kakaouo.mods.kacosmetics.util.Modifiers;
import com.kakaouo.mods.kacosmetics.util.SkinModifier;
import com.mojang.authlib.GameProfile;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.multiplayer.ClientPacketListener;
import net.minecraft.client.multiplayer.PlayerInfo;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;

public class PlayerModifierLayer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {
    private final Modifiers modifier;

    public PlayerModifierLayer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> renderLayerParent, Modifiers modifier) {
        super(renderLayerParent);
        this.modifier = modifier;
    }

    @Override
    public void render(@NotNull PoseStack poseStack, @NotNull MultiBufferSource multiBufferSource, int i, @NotNull AbstractClientPlayer player, float f, float g, float h, float j, float k, float l) {
        ClientPacketListener conn = Minecraft.getInstance().getConnection();
        if (conn == null) return;

        PlayerInfo info = conn.getPlayerInfo(player.getUUID());
        if (info == null) return;

        ResourceLocation location = player.getSkin().texture();
        if (!SkinModifier.isOfModifier(modifier, location)) return;

        VertexConsumer vertexConsumer = multiBufferSource.getBuffer(RenderType.entityTranslucent(location));
        int m = LivingEntityRenderer.getOverlayCoords(player, 0.0F);
        this.modifier.render(this.getParentModel(), poseStack, vertexConsumer, i, m);
    }
}
