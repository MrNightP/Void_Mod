package net.night.voidmod.datagen;

import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.night.voidmod.VoidMod;
import net.night.voidmod.block.ModBlocks;
import net.night.voidmod.item.ModItems;

import java.util.List;
import java.util.function.Consumer;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    private static final List<ItemLike> Void_Smelting = List.of(ModItems.Raw_VoidShard.get(),
            ModBlocks.VoidShardOreStone.get());

    public ModRecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> pWriter) {
        oreSmelting(pWriter, Void_Smelting, RecipeCategory.MISC, ModItems.VoidShard.get(), 0.7F, 200, "void_shard");
        oreBlasting(pWriter, Void_Smelting, RecipeCategory.MISC, ModItems.VoidShard.get(), 0.7F, 100, "void_shard");

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.VoidShardsBlock.get())
                .pattern("VVV")
                .pattern("VVV")
                .pattern("VVV")
                .define('V', ModItems.VoidShard.get())
                .unlockedBy(getHasName(ModItems.VoidShard.get()), has(ModItems.VoidShard.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.VoidShard.get(), 9)
                .requires(ModBlocks.VoidShardsBlock.get())
                .unlockedBy(getHasName(ModBlocks.VoidShardsBlock.get()), has(ModBlocks.VoidShardsBlock.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.Void_Fuel_Block.get())
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', ModItems.Void_Fuel.get())
                .unlockedBy(getHasName(ModItems.Void_Fuel.get()), has(ModItems.Void_Fuel.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.Empty_MRE_Can.get())
                .pattern(" D ")
                .pattern(" I ")
                .pattern(" P ")
                .define('I', Items.IRON_INGOT)
                .define('D', Items.PURPLE_DYE)
                .define('P', Items.PAPER)
                .unlockedBy(getHasName(ModItems.Empty_MRE_Can.get()), has(ModItems.Empty_MRE_Can.get()))
                .save(pWriter);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.MRE.get())
                .pattern(" P ")
                .pattern("CMA")
                .pattern(" B ")
                .define('P', Items.COOKED_PORKCHOP)
                .define('C', Items.GOLDEN_CARROT)
                .define('M', ModItems.Empty_MRE_Can.get())
                .define('A', Items.GOLDEN_APPLE)
                .define('B', Items.BREAD)
                .unlockedBy(getHasName(ModItems.MRE.get()), has(ModItems.MRE.get()))
                .save(pWriter);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.Void_Fuel.get(), 9)
                .requires(ModBlocks.Void_Fuel_Block.get())
                .unlockedBy(getHasName(ModBlocks.Void_Fuel_Block.get()), has(ModBlocks.Void_Fuel_Block.get()))
                .save(pWriter);
    }

    protected static void oreSmelting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.SMELTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(Consumer<FinishedRecipe> pFinishedRecipeConsumer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup) {
        oreCooking(pFinishedRecipeConsumer, RecipeSerializer.BLASTING_RECIPE, pIngredients, pCategory, pResult, pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static void oreCooking(Consumer<FinishedRecipe> pFinishedRecipeConsumer, RecipeSerializer<? extends AbstractCookingRecipe> pCookingSerializer, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult,
                    pExperience, pCookingTime, pCookingSerializer)
                    .group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(pFinishedRecipeConsumer, VoidMod.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }

    }
}
