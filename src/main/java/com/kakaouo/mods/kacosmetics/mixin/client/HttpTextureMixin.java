package com.kakaouo.mods.kacosmetics.mixin.client;

import com.kakaouo.mods.kacosmetics.accessors.ModdedHttpTexture;
import com.kakaouo.mods.kacosmetics.util.SkinModifier;
import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.renderer.texture.HttpTexture;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.InputStream;

@Mixin(HttpTexture.class)
public class HttpTextureMixin implements ModdedHttpTexture {
    private Runnable grassPlayerCallback = null;

    @Inject(method = "load(Ljava/io/InputStream;)Lcom/mojang/blaze3d/platform/NativeImage;", at = @At("RETURN"))
    public void injectLoad(InputStream inputStream, CallbackInfoReturnable<NativeImage> cir) {
        NativeImage bitmap = cir.getReturnValue();

        if (SkinModifier.applyGrassModification(bitmap)) {
            this.grassPlayerCallback.run();
        }
    }

    @Override
    public void executeAfterLoadIfHasGrass(Runnable callback) {
        this.grassPlayerCallback = callback;
    }
}
