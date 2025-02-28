package net.night.voidmod.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.night.voidmod.VoidMod;

@Mod.EventBusSubscriber(modid = VoidMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {

    @SubscribeEvent
    public static void onRegisterKeyMappings(RegisterKeyMappingsEvent event) {
        // Регистрация клавиш
        event.register(KeybindHandler.KEY_ARMOR_DASH);
        event.register(KeybindHandler.KEY_ARMOR_EFFECT);
        event.register(KeybindHandler.CHAIN_BLADE_KEY);
    }
}