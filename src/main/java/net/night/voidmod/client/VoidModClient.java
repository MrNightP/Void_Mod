package net.night.voidmod.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.night.voidmod.VoidMod;
import net.night.voidmod.client.model.BladeEntityModel;
import net.night.voidmod.client.renderer.BladeEntityRenderer;
import net.night.voidmod.entity.VoidModEntities;

@Mod.EventBusSubscriber(modid = VoidMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class VoidModClient {
    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(VoidModEntities.BLADE_ENTITY.get(), BladeEntityRenderer::new);
    }

    @SubscribeEvent
    public static void registerLayerDefinitions(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(BladeEntityModel.LAYER_LOCATION, BladeEntityModel::createBodyLayer);
    }
}
