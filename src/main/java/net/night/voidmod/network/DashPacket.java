package net.night.voidmod.network; // Замените на ваш пакет

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.network.NetworkDirection;
import net.minecraftforge.network.NetworkEvent;
import net.minecraftforge.network.NetworkRegistry;
import net.minecraftforge.network.simple.SimpleChannel;

import java.util.function.Supplier;

public class DashPacket {
    public static final ResourceLocation ID = new ResourceLocation("voidmod", "dash");

    // Создаем канал
    public static final SimpleChannel CHANNEL = NetworkRegistry.ChannelBuilder.named(ID)
            .networkProtocolVersion(() -> "1.0")
            .clientAcceptedVersions(s -> true)
            .serverAcceptedVersions(s -> true)
            .simpleChannel();

    // Регистрируем пакет
    public static void register() {
        int packetId = 0;
        CHANNEL.messageBuilder(DashPacket.class, packetId++, NetworkDirection.PLAY_TO_SERVER)
                .encoder((msg, buffer) -> {})
                .decoder(DashPacket::new)
                .consumerMainThread(DashPacket::handle)
                .add();
    }

    // Конструктор для десериализации
    public DashPacket(FriendlyByteBuf buffer) {}

    // Конструктор для создания пакета
    public DashPacket() {}

    // Обработка пакета на сервере
    public void handle(Supplier<NetworkEvent.Context> ctx) {
        ctx.get().enqueueWork(() -> {
            ServerPlayer player = ctx.get().getSender();
            if (player != null && isWearingFullArmorSet(player)) {
                Vec3 lookAngle = player.getLookAngle();
                double maxDistance = 16.0; // Максимальное расстояние телепортации

                // Находим точку телепортации с учетом препятствий
                Vec3 targetPosition = findTeleportPosition(player, lookAngle, maxDistance);

                if (targetPosition != null) {
                    // Телепортируем игрока
                    player.teleportTo(targetPosition.x, targetPosition.y, targetPosition.z);

                    // Звук телепортации
                    player.level().playSound(
                            null,
                            player.getX(), player.getY(), player.getZ(),
                            SoundEvents.PHANTOM_FLAP,
                            SoundSource.PLAYERS,
                            2.5f,
                            1.0f
                    );

                    player.hurtMarked = true;
                }
            }
        });
        ctx.get().setPacketHandled(true);
    }

    // Поиск безопасной позиции для телепортации
    private Vec3 findTeleportPosition(ServerPlayer player, Vec3 direction, double maxDistance) {
        Vec3 startPosition = player.position();
        Vec3 endPosition = startPosition.add(direction.scale(maxDistance));

        // Используем RayTrace для поиска препятствий
        BlockHitResult hitResult = player.level().clip(
                new ClipContext(
                        startPosition,
                        endPosition,
                        ClipContext.Block.COLLIDER,
                        ClipContext.Fluid.NONE,
                        player
                )
        );

        // Если луч столкнулся с блоком, телепортируем игрока перед препятствием
        if (hitResult.getType() != HitResult.Type.MISS) {
            return hitResult.getLocation().subtract(direction.scale(0.5)); // Отступаем немного от блока
        }

        // Если препятствий нет, телепортируем на максимальное расстояние
        return endPosition;
    }

    // Проверка, свободно ли место для телепортации
    private boolean isPositionSafe(ServerPlayer player, Vec3 position) {
        return player.level().noCollision(player, player.getBoundingBox().move(position.subtract(player.position())));
    }

    // Поиск ближайшей безопасной позиции
    private Vec3 findSafePosition(ServerPlayer player, Vec3 targetPosition) {
        Vec3 direction = targetPosition.subtract(player.position()).normalize();
        double step = 0.5; // Шаг для поиска свободного места
        Vec3 currentPosition = player.position();

        // Поиск свободного места вдоль направления
        for (double distance = 0; distance <= 5.0; distance += step) {
            Vec3 testPosition = player.position().add(direction.scale(distance));
            if (isPositionSafe(player, testPosition)) {
                return testPosition;
            }
        }

        return null; // Если свободное место не найдено
    }

    private static boolean isWearingFullArmorSet(ServerPlayer player) {
        // Реализуйте проверку брони здесь
        return true; // Заглушка
    }
}