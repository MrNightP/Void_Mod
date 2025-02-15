package net.night.voidmod.item;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;
import net.minecraftforge.common.TierSortingRegistry;
import net.night.voidmod.VoidMod;
import net.night.voidmod.util.ModTags;

import java.util.List;

public class ModToolTiers {
    public static final Tier VOID = TierSortingRegistry.registerTier(
            new ForgeTier(5, 3600,12f,8f,25,
                    ModTags.Blocks.NEEDS_VOID_TOOL, () -> Ingredient.of(ModItems.VoidShard.get())),
            new ResourceLocation(VoidMod.MOD_ID, "void"), List.of(Tiers.NETHERITE), List.of());

}
