package net.night.voidmod.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;
import net.night.voidmod.entity.BladeEntity;

import java.util.function.Supplier;

public class SpawnBladeEntityPacket {
    public SpawnBladeEntityPacket() {}

    public static void encode(SpawnBladeEntityPacket msg, FriendlyByteBuf buf) {}

    public static SpawnBladeEntityPacket decode(FriendlyByteBuf buf) {
        return new SpawnBladeEntityPacket();
    }

    public static void handle(SpawnBladeEntityPacket msg, Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player != null) {
                BladeEntity blade = new BladeEntity(player.level(), player, 1);
                blade.setPos(player.getX(), player.getEyeY(), player.getZ());
                player.level().addFreshEntity(blade);
            }
        });
        ctx.get().setPacketHandled(true);
    }
}

