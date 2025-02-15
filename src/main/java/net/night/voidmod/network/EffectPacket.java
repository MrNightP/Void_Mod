package net.night.voidmod.network;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;
import net.night.voidmod.VoidMod;

import java.util.function.Supplier;

public class EffectPacket {
    private static final String PROTOCOL_VERSION = "1";
    public static final SimpleChannel CHANNEL = NetworkRegistry.newSimpleChannel(
            new ResourceLocation(VoidMod.MOD_ID, "effect"),
            () -> PROTOCOL_VERSION,
            PROTOCOL_VERSION::equals,
            PROTOCOL_VERSION::equals
    );

    public EffectPacket() {}

    public EffectPacket(FriendlyByteBuf buf) {}

    public void encode(FriendlyByteBuf buf) {}

    public static void register() {
        CHANNEL.messageBuilder(EffectPacket.class, 0, NetworkDirection.PLAY_TO_SERVER)
                .encoder(EffectPacket::encode)
                .decoder(EffectPacket::new)
                .consumerMainThread(EffectPacket::handle)
                .add();
    }

    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player != null) {
                player.addEffect(new MobEffectInstance(
                        MobEffects.MOVEMENT_SPEED,
                        600, // n секунд (20 тиков/сек * n)
                        2, false, false
                ));

                player.addEffect(new MobEffectInstance(
                        MobEffects.DAMAGE_RESISTANCE,
                        160, 4, false, false
                ));

                player.addEffect(new MobEffectInstance(
                        MobEffects.HEAL,
                        1, 24, false, false
                ));
                player.addEffect(new MobEffectInstance(
                        MobEffects.DAMAGE_BOOST,
                        600, 1, false, false
                ));
            }
        });
        ctx.get().setPacketHandled(true);
    }
}
