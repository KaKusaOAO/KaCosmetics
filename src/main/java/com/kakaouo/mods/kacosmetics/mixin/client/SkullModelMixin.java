package com.kakaouo.mods.kacosmetics.mixin.client;

import com.kakaouo.mods.kacosmetics.util.ModelModifier;
import net.minecraft.client.model.SkullModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.NoSuchElementException;

@Mixin(SkullModel.class)
public class SkullModelMixin {
    @Shadow @Final private ModelPart root;

    @Shadow @Final protected ModelPart head;

    private ModelPart grass;

    @ModifyArg(method = "createHumanoidHeadLayer", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/model/geom/builders/LayerDefinition;create(Lnet/minecraft/client/model/geom/builders/MeshDefinition;II)Lnet/minecraft/client/model/geom/builders/LayerDefinition;"))
    private static MeshDefinition injectHumanoidModel(MeshDefinition mesh) {
        PartDefinition root = mesh.getRoot();
        PartDefinition grass = root.addOrReplaceChild("grass", CubeListBuilder.create(), PartPose.ZERO);
        ModelModifier.addGrassToPartDefinition(grass);
        return mesh;
    }

    @Inject(method = "<init>", at = @At("RETURN"))
    public void injectInit(ModelPart modelPart, CallbackInfo ci) {
        try {
            this.grass = root.getChild("grass");
        } catch (NoSuchElementException ex) {
            // ;
        }
    }

    @Inject(method = "setupAnim", at = @At("RETURN"))
    public void injectSetupAnim(float f, float g, float h, CallbackInfo ci) {
        if (grass != null) {
            grass.copyFrom(head);
        }
    }
}
