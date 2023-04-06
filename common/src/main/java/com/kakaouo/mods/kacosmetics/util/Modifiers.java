package com.kakaouo.mods.kacosmetics.util;

import com.kakaouo.mods.kacosmetics.accessors.ModdedPlayerModel;
import com.kakaouo.mods.kacosmetics.mixin.client.HumanoidModelAccessor;
import com.kakaouo.mods.kacosmetics.mixin.client.PlayerRendererMixin;
import com.mojang.blaze3d.platform.NativeImage;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.builders.PartDefinition;
import net.minecraft.client.player.AbstractClientPlayer;

public enum Modifiers {
    GRASS(
        Part.HEAD,
        SkinModifier::applyGrassModification,
        ModelModifier::addGrassToPartDefinition),
    CAT_EARS(
        Part.HEAD,
        SkinModifier::applyCatEarsModification,
        ModelModifier::addCatEarsToPartDefinition),
    CAT_TAIL(
        Part.BODY,
        SkinModifier::applyCatTailModification,
        ModelModifier::addCatTailToPartDefinition),
    EEVEE(
        Part.HEAD,
        SkinModifier::applyEeveeModification,
        ModelModifier::addEeveeEarsToPartDefinition);

    private final Part part;
    private final SkinModifyOperator modifier;
    private final ModelModifyOperator modelModifier;

    Modifiers(Part part, SkinModifyOperator modifier, ModelModifyOperator modelModifier) {
        this.part = part;
        this.modifier = modifier;
        this.modelModifier = modelModifier;
    }

    public Part part() {
        return part;
    }

    public boolean modifySkin(NativeImage image) {
        return this.modifier.modify(image);
    }

    public void defineModelParts(PartDefinition part) {
        this.modelModifier.modify(part);
    }

    public ModelPart resolvePart(PlayerModel<?> model) {
        return this.part.resolvePart(model);
    }

    public ModelPart getPartFromRoot(ModelPart root) {
        return root.getChild(this.name());
    }

    public PartDefinition getPartFromRoot(PartDefinition root) {
        return root.getChild(this.name());
    }

    public void render(PlayerModel<AbstractClientPlayer> model, PoseStack poseStack, VertexConsumer vertexConsumer, int i, int j) {
        var root = ((ModdedPlayerModel) model).getRoot();
        var part = this.getPartFromRoot(root);
        part.copyFrom(this.resolvePart(model));
        part.render(poseStack, vertexConsumer, i, j);
    }

    @FunctionalInterface
    private interface SkinModifyOperator {
        boolean modify(NativeImage image);
    }

    @FunctionalInterface
    private interface ModelModifyOperator {
        void modify(PartDefinition part);
    }

    public enum Part {
        HEAD(model -> ((HumanoidModelAccessor) model).getHead()),
        BODY(model -> ((HumanoidModelAccessor) model).getBody());

        private final ModelPartResolver partResolver;

        Part(ModelPartResolver partResolver) {
            this.partResolver = partResolver;
        }

        public ModelPart resolvePart(PlayerModel<?> model) {
            return this.partResolver.resolve(model);
        }

        private interface ModelPartResolver {
            ModelPart resolve(PlayerModel<?> model);
        }
    }
}
