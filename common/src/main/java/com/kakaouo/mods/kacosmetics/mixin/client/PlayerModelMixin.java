package com.kakaouo.mods.kacosmetics.mixin.client;

import com.kakaouo.mods.kacosmetics.accessors.ModdedPlayerModel;
import com.kakaouo.mods.kacosmetics.util.ModelModifier;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.MeshDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlayerModel.class)
public abstract class PlayerModelMixin<T extends LivingEntity> extends HumanoidModelMixin<T> implements ModdedPlayerModel {
    @Inject(method = "createMesh", at = @At("RETURN"))
    private static void injectModel(CubeDeformation cubeDeformation, boolean bl, CallbackInfoReturnable<MeshDefinition> cir) {
        MeshDefinition mesh = cir.getReturnValue();
        PartDefinition root = mesh.getRoot();

        // Grass
        PartDefinition grass = root.addOrReplaceChild("grass", CubeListBuilder.create(), PartPose.ZERO);
        ModelModifier.addGrassToPartDefinition(grass);

        // Eevee Ears
        PartDefinition eeveeEars = root.addOrReplaceChild("eeveeEars", CubeListBuilder.create(), PartPose.ZERO);
        ModelModifier.addEeveeEarsToPartDefinition(eeveeEars);

        // Cat Ears
        PartDefinition catEars = root.addOrReplaceChild("catEars", CubeListBuilder.create(), PartPose.ZERO);
        ModelModifier.addCatEarsToPartDefinition(catEars);

        // Cat Tail
        PartDefinition catTail = root.addOrReplaceChild("catTail", CubeListBuilder.create(), PartPose.ZERO);
        ModelModifier.addCatTailToPartDefinition(catTail);
    }

    private ModelPart grass;
    private ModelPart eeveeEars;
    private ModelPart catEars;
    private ModelPart catTail;

    @Inject(method = "<init>", at = @At("RETURN"))
    public void injectInit(ModelPart modelPart, boolean bl, CallbackInfo ci) {
        // 神奇的 Mojang 把豬布林的模型設定成繼承 PlayerModel
        // 有人可以告訴我他們到底在想什麼嗎?
        PlayerModel<?> self = (PlayerModel<?>) (Object) this;
        if (!self.getClass().equals(PlayerModel.class)) return;

        this.grass = modelPart.getChild("grass");
        this.eeveeEars = modelPart.getChild("eeveeEars");
        this.catEars = modelPart.getChild("catEars");
        this.catTail = modelPart.getChild("catTail");
    }

    @Override
    public void renderGrass(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int j) {
        this.grass.copyFrom(this.head);
        this.grass.render(poseStack, vertexConsumer, i, j);
    }

    @Override
    public void renderEeveeEars(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int j) {
        this.eeveeEars.copyFrom(this.head);
        this.eeveeEars.render(poseStack, vertexConsumer, i, j);
    }

    @Override
    public void renderCatEars(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int j) {
        this.catEars.copyFrom(this.head);
        this.catEars.render(poseStack, vertexConsumer, i, j);
    }

    @Override
    public void renderCatTail(PoseStack poseStack, VertexConsumer vertexConsumer, int i, int j) {
        this.catTail.copyFrom(this.body);
        this.catTail.render(poseStack, vertexConsumer, i, j);
    }
}
