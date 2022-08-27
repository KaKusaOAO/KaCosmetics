package com.kakaouo.mods.kacosmetics.mixin.client;

import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(EntityRenderer.class)
public abstract class EntityRendererMixin<T extends Entity> {

}
