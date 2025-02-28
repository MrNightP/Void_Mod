package net.night.voidmod.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.resources.ResourceLocation;
import net.night.voidmod.VoidMod;
import net.night.voidmod.entity.BladeEntity;

public class BladeEntityModel extends EntityModel<BladeEntity> {
    public static final ModelLayerLocation LAYER_LOCATION = new ModelLayerLocation(
            new ResourceLocation(VoidMod.MOD_ID, "blade"), "main");

    public BladeEntityModel(ModelPart entityModelSet) {
        super(RenderType::entityCutoutNoCull);
    }

    @Override
    public void setupAnim(BladeEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        // Анимации лезвия, если нужны
    }

    @Override
    public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        // Рендер модели
    }
}

