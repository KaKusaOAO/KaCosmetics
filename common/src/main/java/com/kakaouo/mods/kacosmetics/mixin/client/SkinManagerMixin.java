package com.kakaouo.mods.kacosmetics.mixin.client;

import com.kakaouo.mods.kacosmetics.accessors.ModdedHttpTexture;
import com.kakaouo.mods.kacosmetics.util.SkinModifier;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import net.minecraft.client.renderer.texture.AbstractTexture;
import net.minecraft.client.renderer.texture.HttpTexture;
import net.minecraft.client.resources.SkinManager;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

import java.io.File;

@Mixin(SkinManager.class)
public class SkinManagerMixin {
    @ModifyArgs(method = "registerTexture(Lcom/mojang/authlib/minecraft/MinecraftProfileTexture;Lcom/mojang/authlib/minecraft/MinecraftProfileTexture$Type;Lnet/minecraft/client/resources/SkinManager$SkinTextureCallback;)Lnet/minecraft/resources/ResourceLocation;", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/texture/TextureManager;register(Lnet/minecraft/resources/ResourceLocation;Lnet/minecraft/client/renderer/texture/AbstractTexture;)V"))
    public void injectRegisterSkin(Args args) {
        AbstractTexture abstractTexture = args.get(1);
        if (!(abstractTexture instanceof HttpTexture texture)) return;

        ResourceLocation location = args.get(0);
        ((ModdedHttpTexture) texture).executeAfterLoadIfModified((modifiers) -> {
            SkinModifier.registerPlayerSkin(modifiers, location);
        });
    }
}
