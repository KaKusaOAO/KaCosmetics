package com.kakaouo.mods.kacosmetics.mixin.client;

import com.kakaouo.mods.kacosmetics.entities.client.layers.PlayerCatEarsHeadLayer;
import com.kakaouo.mods.kacosmetics.entities.client.layers.PlayerCatTailBodyLayer;
import com.kakaouo.mods.kacosmetics.entities.client.layers.PlayerEeveeEarsHeadLayer;
import com.kakaouo.mods.kacosmetics.entities.client.layers.PlayerGrassHeadLayer;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerRenderer.class)
public abstract class PlayerRendererMixin extends LivingEntityRendererMixin<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {
    @Inject(method = "<init>", at = @At("RETURN"))
    public void injectInit(EntityRendererProvider.Context context, boolean bl, CallbackInfo ci) {
        PlayerRenderer renderer = (PlayerRenderer) (Object) this;
        this.addLayer(new PlayerGrassHeadLayer(renderer));
        this.addLayer(new PlayerEeveeEarsHeadLayer(renderer));
        this.addLayer(new PlayerCatEarsHeadLayer(renderer));
        this.addLayer(new PlayerCatTailBodyLayer(renderer));
    }
}
