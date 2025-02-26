package net.night.voidmod.item;

import net.minecraft.world.item.*;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.night.voidmod.item.custom.DragonArmorItem;
import net.night.voidmod.item.custom.FuelItem;
import net.night.voidmod.item.custom.MetalDetectorItem;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, "voidmod");

    public static final RegistryObject<Item> VoidShard = ITEMS.register("voidshard",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> Raw_VoidShard = ITEMS.register("raw_voidshard",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> EndGem = ITEMS.register("end_gem",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> RawEndGem = ITEMS.register("raw_end_gem",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DragonSteelAlloy = ITEMS.register("dragon_steel_alloy",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> MoonShineAlloy = ITEMS.register("moonshine_alloy",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> DragonScale = ITEMS.register("dragon_scale",
            () -> new Item(new Item.Properties()));

    public static final RegistryObject<Item> MetalDetector = ITEMS.register("metal_detector",
            () -> new MetalDetectorItem(new Item.Properties().durability(256)));

    public static final RegistryObject<Item> MRE = ITEMS.register("mre",
            () -> new CannedFood(new Item.Properties().food(ModFoods.MRE)));
    public static final RegistryObject<Item> nyamnyambricks = ITEMS.register("nyamnyambricks",
            () -> new Item(new Item.Properties().food(ModFoods.nyamnyambricks)));

    public static final RegistryObject<Item> Empty_MRE_Can = ITEMS.register("empty_mre_can",
            () -> new Item(new Item.Properties()));
    public static final RegistryObject<Item> CannedFood = ITEMS.register("canned_food",
            () -> new CannedFood(new Item.Properties()
                    .food(ModFoods.MRE)));

    public static final RegistryObject<Item> Dragon_Scythe = ITEMS.register("dragon_scythe",
            () -> new Item(new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> Dragon_Sword = ITEMS.register("dragon_sword",
            () -> new SwordItem(ModToolTiers.VOID, 8, 3, new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> Dragon_Pickaxe = ITEMS.register("dragon_pickaxe",
            () -> new PickaxeItem(ModToolTiers.VOID, 0, 5,new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> Dragon_Axe = ITEMS.register("dragon_axe",
            () -> new AxeItem(ModToolTiers.VOID, 9,5,new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> Dragon_Shovel = ITEMS.register("dragon_shovel",
            () -> new ShovelItem(ModToolTiers.VOID,0, 0,new Item.Properties().stacksTo(1)));
    public static final RegistryObject<Item> Dragon_Hoe = ITEMS.register("dragon_hoe",
            () -> new HoeItem(ModToolTiers.VOID, 0, 0,new Item.Properties().stacksTo(1)));

    public static final RegistryObject<Item> Void_Fuel = ITEMS.register("void_fuel",
            () -> new FuelItem(new Item.Properties(),4800));

    public static final RegistryObject<Item> Dragon_Armor_Helmet = ITEMS.register("dragon_armor_helmet",
            () -> new DragonArmorItem(ModArmorMaterials.DRAGON, ArmorItem.Type.HELMET, new Item.Properties()));
    public static final RegistryObject<Item> Dragon_Armor_Chestplate = ITEMS.register("dragon_armor_chestplate",
            () -> new DragonArmorItem(ModArmorMaterials.DRAGON, ArmorItem.Type.CHESTPLATE, new Item.Properties()));
    public static final RegistryObject<Item> Dragon_Armor_Leggings = ITEMS.register("dragon_armor_leggings",
            () -> new DragonArmorItem(ModArmorMaterials.DRAGON, ArmorItem.Type.LEGGINGS, new Item.Properties()));
    public static final RegistryObject<Item> Dragon_Armor_Boots = ITEMS.register("dragon_armor_boots",
            () -> new DragonArmorItem(ModArmorMaterials.DRAGON, ArmorItem.Type.BOOTS, new Item.Properties()));
    
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
