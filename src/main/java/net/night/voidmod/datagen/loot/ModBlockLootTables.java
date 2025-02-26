package net.night.voidmod.datagen.loot;

import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import net.night.voidmod.block.ModBlocks;
import net.night.voidmod.item.ModItems;

import java.util.Set;

public class ModBlockLootTables extends BlockLootSubProvider {
    public ModBlockLootTables() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());}

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.VoidShardsBlock.get());
        this.dropSelf(ModBlocks.RawVoidShardsBlock.get());
        this.dropSelf(ModBlocks.Void_Fuel_Block.get());
        this.dropSelf(ModBlocks.Sound_Test_Block.get());
        this.dropSelf(ModBlocks.EndGemOre.get());

        this.add(ModBlocks.VoidShardOreStone.get(),
                block -> createVoidShardOreLikeDrops(ModBlocks.VoidShardOreStone.get(), ModItems.Raw_VoidShard.get()));
    }
protected LootTable.Builder createVoidShardOreLikeDrops(Block pBlock, Item item) {
    return createSilkTouchDispatchTable(pBlock, this.applyExplosionDecay(pBlock,
            LootItem.lootTableItem(item)
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 3.0F)))
                    .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))));
}

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(RegistryObject::get)::iterator;

    }
}
