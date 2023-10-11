package com.kakaouo.mods.kacosmetics.mixin.client;

import com.kakaouo.mods.kacosmetics.internal.SkinRegisterSwitch;
import com.mojang.authlib.minecraft.MinecraftProfileTexture;
import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.concurrent.CompletableFuture;

@Mixin(targets = "net.minecraft.client.resources.SkinManager$TextureCache")
public class SkinManagerMixin {
    // We don't use @ModifyArgs because it makes problems on Forge
    @Inject(method = "registerTexture", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/texture/TextureManager;register(Lnet/minecraft/resources/ResourceLocation;Lnet/minecraft/client/renderer/texture/AbstractTexture;)V"))
    public void injectPreRegisterTexture(MinecraftProfileTexture minecraftProfileTexture, CallbackInfoReturnable<CompletableFuture<ResourceLocation>> cir) {
        SkinRegisterSwitch.FLAG = true;
    }
}
