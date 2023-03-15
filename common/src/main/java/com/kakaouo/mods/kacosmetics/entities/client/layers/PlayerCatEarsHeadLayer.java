package com.kakaouo.mods.kacosmetics.entities.client.layers;

import com.kakaouo.mods.kacosmetics.util.Modifiers;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.RenderLayerParent;

public class PlayerCatEarsHeadLayer extends AbstractPlayerModifierLayer {
    public PlayerCatEarsHeadLayer(RenderLayerParent<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> renderLayerParent) {
        super(renderLayerParent, Modifiers.CAT_EARS, PlayerModifierCalls::renderCatEars);
    }
}

