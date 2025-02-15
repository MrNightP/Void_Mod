package net.night.voidmod.event;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.particles.DustParticleOptions;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
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
import net.night.voidmod.item.ModItems;
import net.night.voidmod.network.EffectPacket;
import net.night.voidmod.util.KeyBinding;
import org.joml.Vector3f;

@Mod.EventBusSubscriber(modid = VoidMod.MOD_ID, value = Dist.CLIENT)
public class ArmorEffect {
    private static long lastUsedTime = 0;
    private static final long COOLDOWN = 30000; // 60 секунд
    private static final long EFFECT_DURATION = 5000; // 5 секунд
    private static boolean hasPlayedSound = false;

    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (event.phase != TickEvent.Phase.END) return;

        long currentTime = System.currentTimeMillis();
        LocalPlayer player = Minecraft.getInstance().player;
        if (player == null) return;

        String cooldownText = I18n.get("message.cooldown");
        String secText = I18n.get("message.sec");
        String cooldownEndText1 = I18n.get("message.cooldown_end1");
        String cooldownEndText2 = I18n.get("message.cooldown_end2");

        // Создаем стилизованные компоненты
        MutableComponent cooldownMessage = Component.literal(cooldownText).withStyle(
                Style.EMPTY.withColor(0xca3ecb));

        MutableComponent remainingTime = Component.literal(String.valueOf((COOLDOWN - (currentTime - lastUsedTime)) / 1000))
                .withStyle(ChatFormatting.WHITE);

        MutableComponent seconds = Component.literal(secText)
                .withStyle(ChatFormatting.WHITE);

        MutableComponent cooldownPart = Component.literal(cooldownEndText1).withStyle(
                Style.EMPTY.withColor(0xca3ecb));

        MutableComponent endPart = Component.literal(cooldownEndText2)
                .withStyle(ChatFormatting.WHITE);

        MutableComponent skillActivatedMessage = Component.literal(I18n.get("message.skill_activated"))
                .withStyle(ChatFormatting.DARK_PURPLE, ChatFormatting.BOLD);

        // Отображение кулдауна
        if (currentTime - lastUsedTime < COOLDOWN) {
            player.displayClientMessage(cooldownMessage.append(remainingTime).append(seconds), true);
        }
        else if (currentTime < lastUsedTime + COOLDOWN + 1000) {
            player.displayClientMessage(cooldownPart.append(endPart), true);
            if (!hasPlayedSound) {
                player.playSound(SoundEvents.NOTE_BLOCK_BELL.get(), 1.0f, 1.0f);
                hasPlayedSound = true;
            }
        }
        else {
            hasPlayedSound = false;
        }

        // Активация эффекта
        if (KeyBinding.ARMOR_EFFECT.consumeClick()) {
            if (isWearingFullArmorSet(player) && (currentTime - lastUsedTime >= COOLDOWN)) {
                EffectPacket.CHANNEL.sendToServer(new EffectPacket());
                lastUsedTime = currentTime;
                player.playSound(SoundEvents.ILLUSIONER_CAST_SPELL, 1.0f, 1.0f);

                // Создаем частицы вокруг игрока
                spawnDashParticles(player);
                // Отправляем сообщение в чат
                player.displayClientMessage(skillActivatedMessage, false); // false, чтобы сообщение не отображалось в action bar
            }
        }
    }

    static void spawnDashParticles(LocalPlayer player) {
        if (player.level().isClientSide()) {
            float r = 0.47f; // Красный компонент
            float g = 0.26f; // Зеленый компонент
            float b = 0.57f; // Синий компонент
            float scale = 1.5f; // Размер частиц

            // Создаем частицы по всей высоте игрока
            for (double yOffset = 0; yOffset < player.getBbHeight(); yOffset += 0.01) {
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

    private static boolean isWearingFullArmorSet(Player player) {
        Item helmet = ModItems.Dragon_Armor_Helmet.get();
        Item chestplate = ModItems.Dragon_Armor_Chestplate.get();
        Item leggings = ModItems.Dragon_Armor_Leggings.get();
        Item boots = ModItems.Dragon_Armor_Boots.get();

        return player.getItemBySlot(EquipmentSlot.HEAD).getItem() == helmet &&
                player.getItemBySlot(EquipmentSlot.CHEST).getItem() == chestplate &&
                player.getItemBySlot(EquipmentSlot.LEGS).getItem() == leggings &&
                player.getItemBySlot(EquipmentSlot.FEET).getItem() == boots;
    }

    @Mod.EventBusSubscriber(modid = VoidMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModBusEvents {
        @SubscribeEvent
        public static void onKeyRegister(RegisterKeyMappingsEvent event) {
            event.register(KeyBinding.ARMOR_EFFECT);
        }
    }
}
