package net.night.voidmod.client;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class KeybindHandler {
    public static final KeyMapping CHAIN_BLADE_KEY = new KeyMapping(
            "key.voidmod.chain_blade",
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_Y,
            "key.category.voidmod"
    );
    public static final KeyMapping KEY_ARMOR_EFFECT = new KeyMapping(
            "key.voidmod.armor_effect",
            KeyConflictContext.IN_GAME,
            KeyModifier.NONE,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_H,
            "key.category.voidmod"
    );
    public static final KeyMapping KEY_ARMOR_DASH = new KeyMapping(
            "key.voidmod.armor_dash", // Описание
            KeyConflictContext.IN_GAME, // Контекст (работает только в игре)
            KeyModifier.NONE, // Модификатор (например, Shift, Ctrl)
            InputConstants.Type.KEYSYM, // Тип ввода (клавиша)
            GLFW.GLFW_KEY_G, // Код клавиши
            "category.voidmod.keys" // Категория
    );

    @SubscribeEvent
    public static void registerKeyMappings(RegisterKeyMappingsEvent event) {
        event.register(CHAIN_BLADE_KEY);
    }
}
