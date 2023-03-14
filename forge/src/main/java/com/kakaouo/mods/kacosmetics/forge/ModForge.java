package com.kakaouo.mods.kacosmetics.forge;

import com.kakaouo.mods.kacosmetics.KaCosmetics;
import com.kakaouo.mods.kacosmetics.Platform;
import com.kakaouo.mods.kacosmetics.PlatformManager;
import com.kakaouo.mods.kacosmetics.forge.utils.MobAttributesEntry;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.item.Item;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLLoader;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Mod(ModForge.MOD_ID)
@Mod.EventBusSubscriber(modid = ModForge.MOD_ID)
public class ModForge implements Platform {
    public static final String MOD_ID = KaCosmetics.NAMESPACE;
    private final List<MobAttributesEntry<?>> mobAttributesEntries = new ArrayList<>();

    public ModForge() {
        PlatformManager.setPlatform(this);
        KaCosmetics.init();

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        modEventBus.addListener(this::onEntityAttributeCreation);
    }

    public void onEntityAttributeCreation(EntityAttributeCreationEvent event) {
        for (MobAttributesEntry<?> entry : mobAttributesEntries) {
            event.put(entry.type(), entry.attributeSupplier());
        }
    }

    @Override
    public String getPlatformName() {
        return "Forge";
    }

    @Override
    public boolean isClient() {
        return FMLLoader.getDist().isClient();
    }

    @Override
    public <T extends LivingEntity> void registerDefaultAttribute(EntityType<T> type, AttributeSupplier.Builder builder) {
        mobAttributesEntries.add(new MobAttributesEntry<>(type, builder.build()));
    }

    @Override
    public <T extends Entity> void registerEntityRenderer(EntityType<T> type, EntityRendererProvider<T> rendererProvider) {
        EntityRenderers.register(type, rendererProvider);
    }

    @Override
    public <T extends Entity> CompletableFuture<EntityType<T>> registerEntityTypeAsync(ResourceLocation id, EntityType.Builder<T> builder) {
        return CompletableFuture.failedFuture(new RuntimeException("Not implemented for Forge"));
    }

    @Override
    public <T extends Item> CompletableFuture<T> registerItemAsync(ResourceLocation id, T item) {
        return CompletableFuture.failedFuture(new RuntimeException("Not implemented for Forge"));
    }
}
