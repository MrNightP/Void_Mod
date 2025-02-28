package net.night.voidmod.client;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.night.voidmod.VoidMod;
import net.night.voidmod.util.KeyBinding;

@Mod.EventBusSubscriber(modid = VoidMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientSetup {

    @SubscribeEvent
    public static void onRegisterKeyMappings(RegisterKeyMappingsEvent event) {
        // Регистрация клавиш
        event.register(KeyBinding.KEY_ARMOR_DASH);
        event.register(KeyBinding.KEY_ARMOR_EFFECT);
        event.register(KeyBinding.BLADE_SHOOT_KEY);
        event.register(KeyBinding.BLADE_MODE_KEY);
    }
}