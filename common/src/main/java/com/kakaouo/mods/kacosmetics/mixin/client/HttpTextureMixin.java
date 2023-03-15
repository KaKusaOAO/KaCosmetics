package com.kakaouo.mods.kacosmetics.mixin.client;

import com.kakaouo.mods.kacosmetics.accessors.ModdedHttpTexture;
import com.kakaouo.mods.kacosmetics.util.Modifiers;
import com.kakaouo.mods.kacosmetics.util.SkinModifier;
import com.mojang.blaze3d.platform.NativeImage;
import net.minecraft.client.renderer.texture.HttpTexture;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.io.InputStream;
import java.util.function.Consumer;

@Mixin(HttpTexture.class)
public class HttpTextureMixin implements ModdedHttpTexture {
    private Consumer<Modifiers> modifyCallback = null;

    @Inject(method = "load(Ljava/io/InputStream;)Lcom/mojang/blaze3d/platform/NativeImage;", at = @At("RETURN"))
    public void injectLoad(InputStream inputStream, CallbackInfoReturnable<NativeImage> cir) {
        NativeImage bitmap = cir.getReturnValue();

        for (Modifiers modifiers : Modifiers.values()) {
            if (modifiers.modify(bitmap)) {
                this.modifyCallback.accept(modifiers);
            }
        }
    }

    @Inject(method = "setNoAlpha", at = @At("HEAD"), cancellable = true)
    private static void cancelNoAlphaModification(NativeImage bitmap, int i, int j, int k, int l, CallbackInfo ci) {
        // We need the alpha to work in our custom areas where possible
        if (SkinModifier.validateKakaPalette(bitmap)) {
            ci.cancel();
        }
    }

    @Override
    public void executeAfterLoadIfModified(Consumer<Modifiers> callback) {
        this.modifyCallback = callback;
    }
}
