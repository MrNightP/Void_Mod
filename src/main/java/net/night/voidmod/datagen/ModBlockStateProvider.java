package net.night.voidmod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.client.model.generators.BlockStateProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.night.voidmod.VoidMod;
import net.night.voidmod.block.ModBlocks;

public class ModBlockStateProvider  extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, VoidMod.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        blockWithItem(ModBlocks.VoidShardOreStone);
        blockWithItem(ModBlocks.RawVoidShardsBlock);

        blockWithItem(ModBlocks.EndGemOre);

        //blockWithItem(ModBlocks.VoidShardOreStone);
        //blockWithItem(ModBlocks.VoidShardOreNetherrack);
       //blockWithItem(ModBlocks.VoidShardOreEndStone);
        //blockWithItem(ModBlocks.VoidShardOreDeepslate);

        blockWithItem(ModBlocks.Sound_Test_Block);

        blockWithItem(ModBlocks.Void_Fuel_Block);
        blockWithItem(ModBlocks.VoidShardsBlock);
    }

    private void blockWithItem(RegistryObject<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(), cubeAll(blockRegistryObject.get()));
    }
}
