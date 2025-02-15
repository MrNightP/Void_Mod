package net.night.voidmod.datagen;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.night.voidmod.VoidMod;
import net.night.voidmod.block.ModBlocks;
import net.night.voidmod.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagGenerator  extends BlockTagsProvider {
    public ModBlockTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, VoidMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(ModTags.Blocks.METAL_DETECTOR_VALUABLES)
                .add(ModBlocks.VoidShardOreStone.get()). addTag(ModTags.Blocks.METAL_DETECTOR_VALUABLES);

        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.VoidShardOreStone.get(),
                        ModBlocks.VoidShardsBlock.get(),
                        ModBlocks.RawVoidShardsBlock.get(),
                        ModBlocks.Void_Fuel_Block.get()

                );

        this.tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.VoidShardOreStone.get(),
                        ModBlocks.VoidShardsBlock.get(),
                        ModBlocks.RawVoidShardsBlock.get(),
                        ModBlocks.Sound_Test_Block.get()
                        );

        this.tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.Void_Fuel_Block.get());

        this.tag(Tags.Blocks.NEEDS_NETHERITE_TOOL)
                .add(ModBlocks.VoidShardsBlock.get()
                        ,ModBlocks.RawVoidShardsBlock.get()
                        ,ModBlocks.VoidShardOreStone.get()

                );
        this.tag(ModTags.Blocks.NEEDS_VOID_TOOL);

    }
}
