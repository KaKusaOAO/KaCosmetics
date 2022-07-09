package com.kakaouo.mods.kacosmetics.util;

import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeDeformation;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.LayerDefinition;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;

public enum ModelModifier {
    ;

    public static void addGrassToPartDefinition(PartDefinition head) {
        @SuppressWarnings("UnnecessaryLocalVariable")
        PartDefinition Head = head;

        PartDefinition grass_root_r1 = Head.addOrReplaceChild("grass_root_r1", CubeListBuilder.create().texOffs(62, 42).addBox(-0.5F, -0.975F, -0.5F, 1.0F, 4.0F, 0.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(0.0F, -11.0F, 1.0F, 0.0F, 0.0F, 0.0F));
        PartDefinition grass_stem = Head.addOrReplaceChild("grass_stem", CubeListBuilder.create().texOffs(60, 40).addBox(-1.0F, -0.5F, 0.0F, 2.0F, 1.0F, 0.0F, new CubeDeformation(0.001F)), PartPose.offsetAndRotation(0.0F, -12.0F, 0.5F, -0.6545F, 0.0F, 0.0F));
        PartDefinition left_leave_r1 = grass_stem.addOrReplaceChild("left_leave_r1", CubeListBuilder.create().texOffs(58, 32).addBox(-3.95F, -37.0F, 0.3F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(0.0F, 35.5F, 0.0F, 0.0F, -0.3054F, 0.0F));
        PartDefinition right_leave_r1 = grass_stem.addOrReplaceChild("right_leave_r1", CubeListBuilder.create().texOffs(58, 36).addBox(0.95F, -37.0F, 0.3F, 3.0F, 3.0F, 0.0F, new CubeDeformation(0.01F)), PartPose.offsetAndRotation(0.0F, 35.5F, 0.0F, 0.0F, 0.3054F, 0.0F));
    }
}
