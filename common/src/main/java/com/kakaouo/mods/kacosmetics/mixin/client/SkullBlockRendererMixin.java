package com.kakaouo.mods.kacosmetics.mixin.client;

import com.kakaouo.mods.kacosmetics.util.Modifiers;
import com.kakaouo.mods.kacosmetics.util.SkinModifier;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.SkullModel;
import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.SkullBlock;
import net.minecraft.world.level.block.entity.SkullBlockEntity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

import static com.kakaouo.mods.kacosmetics.util.SkullBlockRendererHelper.lastQueriedModel;

@Mixin(SkullBlockRenderer.class)
public class SkullBlockRendererMixin {
    @Shadow @Final private Map<SkullBlock.Type, SkullModelBase> modelByType;

    @Inject(method = "render(Lnet/minecraft/world/level/block/entity/SkullBlockEntity;FLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;II)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/blockentity/SkullBlockRenderer;getRenderType(Lnet/minecraft/world/level/block/SkullBlock$Type;Lcom/mojang/authlib/GameProfile;)Lnet/minecraft/client/renderer/RenderType;"))
    public void injectRenderPreRenderType(SkullBlockEntity skullBlockEntity, float f, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, int j, CallbackInfo ci) {
        lastQueriedModel = modelByType.get(SkullBlock.Types.PLAYER);
    }

    @ModifyArg(method = "getRenderType", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderType;entityTranslucent(Lnet/minecraft/resources/ResourceLocation;)Lnet/minecraft/client/renderer/RenderType;"))
    private static ResourceLocation injectRenderType(ResourceLocation resourceLocation) {
        if (lastQueriedModel != null) {
            SkullModelBase modelBase = lastQueriedModel;
            if (modelBase instanceof SkullModel model) {
                try {
                    ModelPart root = ((SkullModelAccessor) model).getRoot();

                    if (SkinModifier.isOfModifier(Modifiers.GRASS, resourceLocation)) {
                        root.getChild("grass").visible = true;
                    }

                    if (SkinModifier.isOfModifier(Modifiers.EEVEE, resourceLocation)) {
                        root.getChild("eeveeEars").visible = true;
                    }

                    if (SkinModifier.isOfModifier(Modifiers.CAT_EARS, resourceLocation)) {
                        root.getChild("catEars").visible = true;
                    }
                } catch (Exception ex) {
                    // ;
                }
            }
        }
        return resourceLocation;
    }

    @Inject(method = "renderSkull", at = @At("RETURN"))
    private static void injectPostRenderSkull(Direction direction, float f, float g, PoseStack poseStack, MultiBufferSource multiBufferSource, int i, SkullModelBase skullModelBase, RenderType renderType, CallbackInfo ci) {
        if (lastQueriedModel == null) return;

        SkullModelBase modelBase = lastQueriedModel;
        if (modelBase instanceof SkullModel model) {
            try {
                ModelPart root = ((SkullModelAccessor) model).getRoot();
                root.getChild("grass").visible = false;
                root.getChild("eeveeEars").visible = false;
                root.getChild("catEars").visible = false;
            } catch (Exception ex) {
                // ;
            }
        }
    }
}
