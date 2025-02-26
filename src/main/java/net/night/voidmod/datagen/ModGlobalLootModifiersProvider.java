package net.night.voidmod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraftforge.common.data.GlobalLootModifierProvider;
import net.minecraftforge.common.loot.LootTableIdCondition;
import net.night.voidmod.VoidMod;
import net.night.voidmod.item.ModItems;
import net.night.voidmod.loot.AddItemModifier;

public class ModGlobalLootModifiersProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifiersProvider(PackOutput output) {
        super(output, VoidMod.MOD_ID);
    }

    @Override
    protected void start() {
        add("dragon_scale_from_ender_dragon", new AddItemModifier(
                new LootItemCondition[] {
                        // Поменять шарды на чешую дракона
                        new LootTableIdCondition.Builder(new ResourceLocation("entities/ender_dragon")).build() }, ModItems.DragonScale.get(),4,16));
    }
}