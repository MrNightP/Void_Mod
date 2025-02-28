package net.night.voidmod.event;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.night.voidmod.client.KeybindHandler;
import net.night.voidmod.network.SpawnBladeEntityPacket;
import net.night.voidmod.network.VoidModNetwork;

@Mod.EventBusSubscriber(modid = net.night.voidmod.VoidMod.MOD_ID, value = Dist.CLIENT)
public class ClientEvents {
    @SubscribeEvent
    public static void onKeyPress(InputEvent.Key event) {
        if (KeybindHandler.CHAIN_BLADE_KEY.consumeClick()) {
            // Отправляем пакет на сервер для спавна лезвия
            VoidModNetwork.sendToServer(new SpawnBladeEntityPacket());
        }
    }
}

