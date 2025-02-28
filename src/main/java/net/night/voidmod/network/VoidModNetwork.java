package net.night.voidmod.network;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.night.voidmod.VoidMod;

public class VoidModNetwork {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel INSTANCE = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(VoidMod.MOD_ID, "network"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    private static int packetId = 0;

    public static int nextId() {
        return packetId++;
    }

    public static void register() {
        INSTANCE.messageBuilder(SpawnBladeEntityPacket.class, nextId(), NetworkDirection.PLAY_TO_SERVER)
                .encoder(SpawnBladeEntityPacket::encode)
                .decoder(SpawnBladeEntityPacket::decode)
                .consumerMainThread(SpawnBladeEntityPacket::handle)
                .add();
    }

    public static void sendToServer(Object msg) {
        INSTANCE.sendToServer(msg);
    }
}
