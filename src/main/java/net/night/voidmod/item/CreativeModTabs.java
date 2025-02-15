package net.night.voidmod.item;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.night.voidmod.block.ModBlocks;

public class CreativeModTabs {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABs =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, "voidmod");

    public static final RegistryObject<CreativeModeTab> VOID_TAB = CREATIVE_MODE_TABs.register("void_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.VoidShard.get()))
                    .title(Component.translatable("creativetab.voidmod.void_tab"))
                    .displayItems(((itemDisplayParameters, output) -> {
                        output.accept(ModItems.VoidShard.get());
                        output.accept(ModItems.Raw_VoidShard.get());

                        output.accept(ModBlocks.VoidShardsBlock.get());
                        output.accept(ModBlocks.RawVoidShardsBlock.get());
                        //Ores
                        output.accept(ModBlocks.VoidShardOreStone.get());

                        output.accept(ModItems.MetalDetector.get());

                        output.accept(ModBlocks.Sound_Test_Block.get());

                        output.accept(ModItems.MRE.get());
                        output.accept(ModItems.Empty_MRE_Can.get());

                        output.accept(ModItems.Void_Fuel.get());
                        output.accept(ModBlocks.Void_Fuel_Block.get());

                        output.accept(ModItems.Dragon_Sword.get());
                        output.accept(ModItems.Dragon_Pickaxe.get());
                        output.accept(ModItems.Dragon_Axe.get());
                        output.accept(ModItems.Dragon_Shovel.get());
                        output.accept(ModItems.Dragon_Hoe.get());

                        output.accept(ModItems.Dragon_Armor_Helmet.get());
                        output.accept(ModItems.Dragon_Armor_Chestplate.get());
                        output.accept(ModItems.Dragon_Armor_Leggings.get());
                        output.accept(ModItems.Dragon_Armor_Boots.get());


                    }))

                    .build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABs.register(eventBus);
    }

}
