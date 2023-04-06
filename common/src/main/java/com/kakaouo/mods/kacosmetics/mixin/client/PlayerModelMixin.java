package com.kakaouo.mods.kacosmetics.mixin.client;

import com.kakaouo.mods.kacosmetics.accessors.ModdedPlayerModel;
import com.kakaouo.mods.kacosmetics.util.ModelModifier;
import com.kakaouo.mods.kacosmetics.util.Modifiers;
import com.kakaouo.mods.kacosmetics.util.PlayerModelRegistry;
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
        PlayerModelRegistry.definePartsToModelRoot(m -> true, root);
    }

    private ModelPart root;

    @Inject(method = "<init>", at = @At("RETURN"))
    public void injectInit(ModelPart modelPart, boolean bl, CallbackInfo ci) {
        // 神奇的 Mojang 把豬布林的模型設定成繼承 PlayerModel
        // 有人可以告訴我他們到底在想什麼嗎?
        PlayerModel<?> self = (PlayerModel<?>) (Object) this;
        if (!self.getClass().equals(PlayerModel.class)) return;
        this.root = modelPart;
    }

    @Override
    public ModelPart getRoot() {
        return root;
    }
}
