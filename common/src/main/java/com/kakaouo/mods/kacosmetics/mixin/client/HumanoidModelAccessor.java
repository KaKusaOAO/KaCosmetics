package com.kakaouo.mods.kacosmetics.mixin.client;

import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelPart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(HumanoidModel.class)
public interface HumanoidModelAccessor {
    @Accessor
    ModelPart getHead();

    @Accessor
    ModelPart getBody();
}
