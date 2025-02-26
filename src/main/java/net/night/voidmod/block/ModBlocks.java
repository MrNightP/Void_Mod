package net.night.voidmod.block;

import net.minecraft.core.registries.Registries;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.night.voidmod.block.custom.SoundBlock;
import net.night.voidmod.item.ModItems;
import net.night.voidmod.item.custom.FuelItem;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(Registries.BLOCK, "voidmod");

    public static final RegistryObject<Block> VoidShardsBlock = registerBlock("void_shards_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).sound(SoundType.STONE)));
    public static final RegistryObject<Block> RawVoidShardsBlock = registerBlock("raw_void_shards_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.DIAMOND_BLOCK).sound(SoundType.STONE)));

    //Ores
    public static final RegistryObject<Block> VoidShardOreStone = registerBlock("void_shard_ore_stone",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
            .strength(2.0F, 5.0F).requiresCorrectToolForDrops(), UniformInt.of(5, 10)));
    public static final RegistryObject<Block> EndGemOre = registerBlock("end_gem_ore_end_stone",
            () -> new DropExperienceBlock(BlockBehaviour.Properties.copy(Blocks.STONE)
                    .strength(2.0F, 5.0F).requiresCorrectToolForDrops(), UniformInt.of(5, 10)));

    public static final RegistryObject<Block> Sound_Test_Block = registerBlock("sound_test_block",
            () -> new SoundBlock(BlockBehaviour.Properties.copy(Blocks.STONE)));
    public static final RegistryObject<Block> Void_Fuel_Block = registerBlock("void_fuel_block",
            () -> new Block(BlockBehaviour.Properties.copy(Blocks.COAL_BLOCK)));


    private static <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block) {
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
