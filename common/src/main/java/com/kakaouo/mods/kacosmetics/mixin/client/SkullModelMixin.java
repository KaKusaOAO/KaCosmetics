package com.kakaouo.mods.kacosmetics.mixin.client;

import com.kakaouo.mods.kacosmetics.util.ModelModifier;
import com.kakaouo.mods.kacosmetics.util.Modifiers;
import com.kakaouo.mods.kacosmetics.util.PlayerModelRegistry;
import net.minecraft.client.model.SkullModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;
import java.util.NoSuchElementException;

@Mixin(SkullModel.class)
public class SkullModelMixin {
    @Shadow @Final private ModelPart root;

    @Shadow @Final protected ModelPart head;

    @ModifyArg(method = "createHumanoidHeadLayer", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/geom/builders/LayerDefinition;create(Lnet/minecraft/client/model/geom/builders/MeshDefinition;II)Lnet/minecraft/client/model/geom/builders/LayerDefinition;"))
    private static MeshDefinition injectHumanoidModel(MeshDefinition mesh) {
        PartDefinition root = mesh.getRoot();
        PlayerModelRegistry.definePartsToModelRoot(m -> m.part() == Modifiers.Part.HEAD, root);
        return mesh;
    }

    @Inject(method = "setupAnim", at = @At("RETURN"))
    public void injectSetupAnim(float f, float g, float h, CallbackInfo ci) {
        Arrays.stream(Modifiers.values())
            .filter(m -> m.part() == Modifiers.Part.HEAD)
            .forEach(m -> {
                try {
                    var part = m.getPartFromRoot(root);
                    part.copyFrom(head);
                } catch (NoSuchElementException ex) {
                    // There are different types of skulls, and we only have these components on players' skull.
                    // We can simply ignore the exception.
                }
            });
    }
}
