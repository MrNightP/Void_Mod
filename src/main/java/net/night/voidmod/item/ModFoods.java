package net.night.voidmod.item;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties MRE = new FoodProperties.Builder().nutrition(20)
            .saturationMod(20)
            .effect(() -> new MobEffectInstance(MobEffects.REGENERATION, 200, 0), 1.0F).build();

}
