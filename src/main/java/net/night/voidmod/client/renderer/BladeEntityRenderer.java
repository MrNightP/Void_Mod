package net.night.voidmod.client.renderer;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.phys.Vec3;
import net.night.voidmod.VoidMod;
import net.night.voidmod.entity.BladeEntity;

public class BladeEntityRenderer extends EntityRenderer<BladeEntity> {
    private static final ResourceLocation TEXTURE = new ResourceLocation(VoidMod.MOD_ID, "textures/entity/blade.png");
    private static final ResourceLocation CHAIN_TEXTURE = new ResourceLocation(VoidMod.MOD_ID, "textures/entity/chain.png");

    public BladeEntityRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Override
    public void render(BladeEntity entity, float yaw, float tickDelta, PoseStack poseStack, MultiBufferSource bufferSource, int light) {
        super.render(entity, yaw, tickDelta, poseStack, bufferSource, light);

        if (entity.getOwner() != null) {
            VertexConsumer vertexConsumer = bufferSource.getBuffer(RenderType.entityCutout(CHAIN_TEXTURE));
            Vec3 startPos = entity.getOwner().getEyePosition(tickDelta);
            Vec3 endPos = entity.position();
            drawSegmentedChain(vertexConsumer, startPos, endPos, bufferSource);
        }
    }

    private void drawSegmentedChain(VertexConsumer vertexConsumer, Vec3 start, Vec3 end, MultiBufferSource bufferSource) {
        int segments = 10;
        Vec3 segmentVector = end.subtract(start).scale(1.0 / segments);
        Vec3 prevPos = start;

        for (int i = 1; i <= segments; i++) {
            float t = i / (float) segments;
            float waveOffset = Mth.sin(t * Mth.PI) * 0.2f; // Делаем цепь волнистой
            Vec3 currentPos = start.add(segmentVector.scale(i)).add(0, waveOffset, 0);

            VertexConsumer chainTexture = bufferSource.getBuffer(RenderType.entityCutout(CHAIN_TEXTURE));
            chainTexture.vertex((float) prevPos.x, (float) prevPos.y, (float) prevPos.z)
                    .color(255, 255, 255, 255)
                    .endVertex();
            chainTexture.vertex((float) currentPos.x, (float) currentPos.y, (float) currentPos.z)
                    .color(255, 255, 255, 255)
                    .endVertex();

            prevPos = currentPos;
        }
    }

    @Override
    public ResourceLocation getTextureLocation(BladeEntity entity) {
        return TEXTURE;
    }
}
