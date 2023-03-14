package com.kakaouo.mods.kacosmetics.mixin.client;

import com.kakaouo.mods.kacosmetics.accessors.ModdedHttpTexture;
import com.kakaouo.mods.kacosmetics.internal.SkinRegisterSwitch;
import com.kakaouo.mods.kacosmetics.util.SkinModifier;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.HttpTexture;
import net.minecraft.client.renderer.texture.TextureManager;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TextureManager.class)
public class TextureManagerMixin {
    @Inject(method = "register(Lnet/minecraft/resources/ResourceLocation;Lnet/minecraft/client/renderer/texture/AbstractTexture;)V", at = @At("HEAD"))
    public void injectRegisterTexture(ResourceLocation location, AbstractTexture abstractTexture, CallbackInfo ci) {
        if (!SkinRegisterSwitch.FLAG) return;
        SkinRegisterSwitch.FLAG = false;

        if (!(abstractTexture instanceof HttpTexture texture)) return;
        ((ModdedHttpTexture) texture).executeAfterLoadIfModified((modifiers) -> {
            SkinModifier.registerPlayerSkin(modifiers, location);
        });
    }
}
