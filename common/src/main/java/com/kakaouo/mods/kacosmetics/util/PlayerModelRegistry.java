package com.kakaouo.mods.kacosmetics.util;

import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.CubeListBuilder;
import net.minecraft.client.model.geom.builders.PartDefinition;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.function.Consumer;
import java.util.function.Predicate;

public enum PlayerModelRegistry {
    ;

    private static final EnumMap<Modifiers, Consumer<PartDefinition>> modifierToPart = new EnumMap<>(Modifiers.class);

    static {
        for (var modifier : Modifiers.values()) {
            PlayerModelRegistry.registerPart(modifier);
        }
    }

    private static void registerPart(Modifiers modifier) {
        modifierToPart.put(modifier, root -> {
            PartDefinition part = root.addOrReplaceChild(modifier.name(), CubeListBuilder.create(), PartPose.ZERO);
            modifier.defineModelParts(part);
        });
    }

    public static void definePartsToModelRoot(Predicate<Modifiers> predicate, PartDefinition root) {
        for (var modifier : modifierToPart.keySet()) {
            if (!predicate.test(modifier)) continue;
            modifierToPart.get(modifier).accept(root);
        }
    }

    public static void load() {
    }
}
