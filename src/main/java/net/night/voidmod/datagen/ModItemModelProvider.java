package net.night.voidmod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;
import net.night.voidmod.VoidMod;
import net.night.voidmod.item.ModItems;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, VoidMod.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels(){
        simpleItem(ModItems.VoidShard);
        simpleItem(ModItems.Raw_VoidShard);
        simpleItem(ModItems.MRE);
        simpleItem(ModItems.Empty_MRE_Can);
        simpleItem(ModItems.MetalDetector);
        simpleItem(ModItems.Void_Fuel);

        simpleItem(ModItems.Dragon_Armor_Helmet);
        simpleItem(ModItems.Dragon_Armor_Chestplate);
        simpleItem(ModItems.Dragon_Armor_Leggings);
        simpleItem(ModItems.Dragon_Armor_Boots);

        handheldItem(ModItems.Dragon_Sword);
        handheldItem(ModItems.Dragon_Pickaxe);
        handheldItem(ModItems.Dragon_Axe);
        handheldItem(ModItems.Dragon_Shovel);
        handheldItem(ModItems.Dragon_Hoe);

    }
    private ItemModelBuilder handheldItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/handheld")).texture("layer0",
                new ResourceLocation(VoidMod.MOD_ID,"item/" + item.getId().getPath()));
    }

    private ItemModelBuilder simpleItem(RegistryObject<Item> item) {
        return withExistingParent(item.getId().getPath(),
                new ResourceLocation("item/generated")).texture("layer0",
                new ResourceLocation(VoidMod.MOD_ID,"item/" + item.getId().getPath()));
    };
}
