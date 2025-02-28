package net.night.voidmod.client;

import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.night.voidmod.VoidMod;
import net.night.voidmod.client.renderer.BladeEntityRenderer;
import net.night.voidmod.entities.VoidModEntities;

@Mod.EventBusSubscriber(modid = VoidMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class VoidModClient {
    public static final ModelLayerLocation MODEL_LAYER =
            new ModelLayerLocation(new ResourceLocation(VoidMod.MOD_ID, "blade"), "main");

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(VoidModEntities.BLADE_ENTITY.get(), BladeEntityRenderer::new);
    }
}

