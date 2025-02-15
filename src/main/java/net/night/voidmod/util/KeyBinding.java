package net.night.voidmod.util;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;
import org.lwjgl.glfw.GLFW;

public class KeyBinding {
    public static final String KEY_CATEGORY_VOIDMOD = "key.category.voidmod.voidmod";
    public static final String KEY_ARMOR_EFFECT = "key.category.armor_effect";
    public static final String KEY_ARMOR_DASH = "key.category.armor_dash";

    public static final KeyMapping ARMOR_EFFECT = new KeyMapping(KEY_ARMOR_EFFECT, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_G, KEY_CATEGORY_VOIDMOD);

    public static final KeyMapping ARMOR_DASH = new KeyMapping(KEY_ARMOR_DASH, KeyConflictContext.IN_GAME,
            InputConstants.Type.KEYSYM, GLFW.GLFW_KEY_R, KEY_CATEGORY_VOIDMOD);

}
