package net.night.voidmod.config;

import net.minecraftforge.common.ForgeConfigSpec;
import org.lwjgl.glfw.GLFW;

public class VoidModConfig {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<Integer> ARMOR_DASH;
    public static final ForgeConfigSpec.ConfigValue<Integer> ARMOR_EFFECT;

    static {
        BUILDER.push("Keybinds");
        ARMOR_DASH = BUILDER.define("armor_dash_keybind", GLFW.GLFW_KEY_G);
        ARMOR_EFFECT = BUILDER.define("armor_effect_keybind", GLFW.GLFW_KEY_H);
        BUILDER.pop();

        SPEC = BUILDER.build();
    }
}