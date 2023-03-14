package com.kakaouo.mods.kacosmetics.mixin.client;

import net.minecraft.client.model.SkullModel;
import net.minecraft.client.model.geom.ModelPart;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(SkullModel.class)
public interface SkullModelAccessor {
    @Accessor ModelPart getRoot();
}
