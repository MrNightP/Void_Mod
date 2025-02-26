package net.night.voidmod.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.client.settings.KeyModifier;
import org.lwjgl.glfw.GLFW;

public class KeyBinding {
    // Бинды для брони
    public static final KeyMapping KEY_ARMOR_DASH = new KeyMapping(
            "key.voidmod.armor_dash", // Описание
            KeyConflictContext.IN_GAME, // Контекст (работает только в игре)
            KeyModifier.NONE, // Модификатор (например, Shift, Ctrl)
            InputConstants.Type.KEYSYM, // Тип ввода (клавиша)
            GLFW.GLFW_KEY_G, // Код клавиши
            "category.voidmod.keys" // Категория
    );

    public static final KeyMapping KEY_ARMOR_EFFECT = new KeyMapping(
            "key.voidmod.armor_effect",
            KeyConflictContext.IN_GAME,
            KeyModifier.NONE,
            InputConstants.Type.KEYSYM,
            GLFW.GLFW_KEY_H,
            "category.voidmod.keys"
    );
}