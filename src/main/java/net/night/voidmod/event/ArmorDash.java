package net.night.voidmod.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.night.voidmod.VoidMod;
import net.night.voidmod.config.VoidModConfig;
import net.night.voidmod.item.ModItems;
import net.night.voidmod.network.DashPacket;
import net.night.voidmod.util.KeyBinding;
import org.joml.Vector3f;

public class ArmorDash {
    public static void stopDashing() {
    }

    @Mod.EventBusSubscriber(modid = "voidmod", value = Dist.CLIENT)
    public class KeyInputHandler {
        private static long lastUsedTime = 0;
        private static final long COOLDOWN = 5000; // Кулдаун 5 секунд
        private static boolean isDashing = false; // Флаг, указывающий, что игрок в рывке
        private static long dashEndTime = 0; // Время окончания рывка
        private static long cooldownEndTime = 0; // Время завершения кулдауна
        private static boolean hasPlayedSound = false; // Флаг для предотвращения повторного воспроизведения звука

        @SubscribeEvent
        public static void onClientTick(TickEvent.ClientTickEvent event) {
            if (event.phase != TickEvent.Phase.END) return;

            long currentTime = System.currentTimeMillis();
            LocalPlayer player = Minecraft.getInstance().player;

            if (player == null) return;

            // Проверяем, идет ли перезарядка
            if (currentTime - lastUsedTime < COOLDOWN) {
                // Отображаем сообщение о перезарядке
                int remainingTime = (int) ((COOLDOWN - (currentTime - lastUsedTime)) / 1000);
                player.displayClientMessage(Component.literal( CooldownText + remainingTime + secText), true);
                cooldownEndTime = currentTime + COOLDOWN; // Обновляем время завершения кулдауна
            } else if (currentTime < cooldownEndTime + 500) { // Показываем сообщение еще 0.5 секунду после завершения кулдауна
                player.displayClientMessage(Component.literal(CooldownEndText), true);

                // Воспроизводим звук при завершении кулдауна
                if (!hasPlayedSound) {
                    player.playSound(SoundEvents.AMETHYST_BLOCK_PLACE, 1.0f, 1.0f); // Звук повышения уровня
                    hasPlayedSound = true; // Предотвращаем повторное воспроизведение
                }
            } else {
                hasPlayedSound = false; // Сбрасываем флаг для следующего раза
            }

            // Проверяем, была ли нажата наша клавиша
            while (KeyBinding.KEY_ARMOR_DASH.consumeClick()) {
                if (isWearingFullArmorSet(player)) {
                    if (currentTime - lastUsedTime >= COOLDOWN) {
                        // Отправляем пакет на сервер
                        DashPacket.CHANNEL.sendToServer(new DashPacket());
                        lastUsedTime = currentTime;
                        isDashing = true; // Начинаем рывок
                        dashEndTime = currentTime + 1000; // Рывок длится 1 секунду
                        hasPlayedSound = false; // Сбрасываем флаг при начале нового кулдауна

                        // Воспроизводим звук при нажатии KeyBind
                        player.playSound(SoundEvents.PHANTOM_FLAP, 2.5f, 1.0f); // Звук телепортации
                    }
                }
            }

            // Создаем частицы во время рывка
            if (isDashing && player != null) {
                if (currentTime < dashEndTime) {
                    spawnDashParticles(player);
                } else {
                    isDashing = false; // Завершаем рывок
                }
            }
        }

        // Метод для создания фиолетовых частиц в виде шлейфа
        private static void spawnDashParticles(LocalPlayer player) {
            if (player.level().isClientSide()) {
                // Фиолетовый цвет (r = 0.5, g = 0.0, b = 1.0)
                float r = 0.47f; // Красный компонент
                float g = 0.26f; // Зеленый компонент
                float b = 0.57f; // Синий компонент
                float scale = 1.5f; // Размер частиц

                // Создаем частицы по всей высоте игрока
                for (double yOffset = 0; yOffset < player.getBbHeight(); yOffset += 0.2) {
                    double x = player.getX() + (player.level().random.nextDouble() - 0.5) * 0.5;
                    double y = player.getY() + yOffset;
                    double z = player.getZ() + (player.level().random.nextDouble() - 0.5) * 0.5;

                    // Создаем фиолетовые частицы
                    player.level().addParticle(
                            new DustParticleOptions(new Vector3f(r, g, b), scale), // Фиолетовый цвет
                            x, y, z, // Позиция
                            0, 0, 0  // Скорость (0, чтобы частицы не двигались)
                    );
                }
            }
        }


        // Метод для проверки, надет ли полный комплект брони
        private static boolean isWearingFullArmorSet(Player player) {
            // Предположим, у вас есть свои предметы брони
            Item helmet = ModItems.Dragon_Armor_Helmet.get();
            Item chestplate = ModItems.Dragon_Armor_Chestplate.get();
            Item leggings = ModItems.Dragon_Armor_Leggings.get();
            Item boots = ModItems.Dragon_Armor_Boots.get();

            // Проверяем, что на игроке надет весь комплект
            return player.getItemBySlot(EquipmentSlot.HEAD).getItem() == helmet &&
                    player.getItemBySlot(EquipmentSlot.CHEST).getItem() == chestplate &&
                    player.getItemBySlot(EquipmentSlot.LEGS).getItem() == leggings &&
                    player.getItemBySlot(EquipmentSlot.FEET).getItem() == boots;
        }
    }
    static String CooldownText = I18n.get("message.cooldown");
    static String secText = I18n.get("message.sec");
    static String CooldownEndText = I18n.get("message.cooldown_end");

    @Mod.EventBusSubscriber(modid = VoidMod.MOD_ID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(KeyBinding.KEY_ARMOR_DASH);
        }
    }
}