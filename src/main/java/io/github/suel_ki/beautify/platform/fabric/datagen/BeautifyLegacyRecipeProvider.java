//? if fabric && <1.21.1 {
/*package io.github.suel_ki.beautify.platform.fabric.datagen;

import net.minecraft.world.level.block.Block;
import io.github.suel_ki.beautify.core.init.BlockInit;

import io.github.suel_ki.beautify.Beautify;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.item.crafting.Ingredient;

import java.util.function.Consumer;

public class BeautifyLegacyRecipeProvider extends FabricRecipeProvider {

	public BeautifyLegacyRecipeProvider(FabricPackOutput output) {
		super(output);
	}

	@Override
	public void buildRecipes(Consumer<FinishedRecipe> consumer) {
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.ACACIA_BLINDS)
				.pattern("/_/")
				.pattern("/_/")
				.define('/', Items.STICK)
				.define('_', Items.ACACIA_SLAB)
				.group("blinds")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.ACACIA_BLINDS))
				.save(consumer, recipeId(BlockInit.ACACIA_BLINDS));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.ACACIA_PICTURE_FRAME)
				.pattern(" _ ")
				.pattern(" W ")
				.pattern(" _/")
				.define('_', Items.ACACIA_SLAB)
				.define('W', whiteWool())
				.define('/', Items.STICK)
				.group("frames")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.ACACIA_PICTURE_FRAME))
				.save(consumer, recipeId(BlockInit.ACACIA_PICTURE_FRAME));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.ACACIA_TRELLIS, 4)
				.pattern("/ /")
				.pattern(" O ")
				.pattern("/ /")
				.define('/', Items.STICK)
				.define('O', Items.ACACIA_LOG)
				.group("trellis")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.ACACIA_TRELLIS))
				.save(consumer, recipeId(BlockInit.ACACIA_TRELLIS));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.BAMBOO_BLINDS)
				.pattern("/_/")
				.pattern("/_/")
				.define('/', Items.STICK)
				.define('_', Items.BAMBOO_SLAB)
				.group("blinds")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.BAMBOO_BLINDS))
				.save(consumer, recipeId(BlockInit.BAMBOO_BLINDS));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.BAMBOO_PICTURE_FRAME)
				.pattern(" _ ")
				.pattern(" W ")
				.pattern(" _/")
				.define('_', Items.BAMBOO_SLAB)
				.define('W', whiteWool())
				.define('/', Items.STICK)
				.group("frames")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.BAMBOO_PICTURE_FRAME))
				.save(consumer, recipeId(BlockInit.BAMBOO_PICTURE_FRAME));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.BAMBOO_TRELLIS, 4)
				.pattern("/ /")
				.pattern(" O ")
				.pattern("/ /")
				.define('/', Items.STICK)
				.define('O', Items.BAMBOO_BLOCK)
				.group("trellis")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.BAMBOO_TRELLIS))
				.save(consumer, recipeId(BlockInit.BAMBOO_TRELLIS));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.BIRCH_BLINDS)
				.pattern("/_/")
				.pattern("/_/")
				.define('/', Items.STICK)
				.define('_', Items.BIRCH_SLAB)
				.group("blinds")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.BIRCH_BLINDS))
				.save(consumer, recipeId(BlockInit.BIRCH_BLINDS));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.BIRCH_PICTURE_FRAME)
				.pattern(" _ ")
				.pattern(" W ")
				.pattern(" _/")
				.define('_', Items.BIRCH_SLAB)
				.define('W', whiteWool())
				.define('/', Items.STICK)
				.group("frames")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.BIRCH_PICTURE_FRAME))
				.save(consumer, recipeId(BlockInit.BIRCH_PICTURE_FRAME));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.BIRCH_TRELLIS, 4)
				.pattern("/ /")
				.pattern(" O ")
				.pattern("/ /")
				.define('/', Items.STICK)
				.define('O', Items.BIRCH_LOG)
				.group("trellis")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.BIRCH_TRELLIS))
				.save(consumer, recipeId(BlockInit.BIRCH_TRELLIS));
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, BlockInit.BOOKSTACK)
				.requires(Items.BOOK)
				.requires(Items.BOOK)
				.requires(Items.BOOK)
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.BOOKSTACK))
				.save(consumer, recipeId(BlockInit.BOOKSTACK));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.BOTANIST_WORKBENCH)
				.pattern("##")
				.pattern("PP")
				.define('#', Items.FLOWER_POT)
				.define('P', Ingredient.of(itemTag("minecraft:planks")))
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.BOTANIST_WORKBENCH))
				.save(consumer, recipeId(BlockInit.BOTANIST_WORKBENCH));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.LAMP_CANDELABRA)
				.pattern("###")
				.pattern("III")
				.pattern(" I ")
				.define('#', Items.CANDLE)
				.define('I', Items.IRON_NUGGET)
				.group("candelabras")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.LAMP_CANDELABRA))
				.save(consumer, recipeId(BlockInit.LAMP_CANDELABRA));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.LAMP_CANDELABRA_BLACK)
				.pattern("###")
				.pattern("III")
				.pattern(" I ")
				.define('#', candle(DyeColor.BLACK))
				.define('I', Items.IRON_NUGGET)
				.group("candelabras")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.LAMP_CANDELABRA_BLACK))
				.save(consumer, recipeId(BlockInit.LAMP_CANDELABRA_BLACK));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.LAMP_CANDELABRA_BLUE)
				.pattern("###")
				.pattern("III")
				.pattern(" I ")
				.define('#', candle(DyeColor.BLUE))
				.define('I', Items.IRON_NUGGET)
				.group("candelabras")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.LAMP_CANDELABRA_BLUE))
				.save(consumer, recipeId(BlockInit.LAMP_CANDELABRA_BLUE));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.LAMP_CANDELABRA_BROWN)
				.pattern("###")
				.pattern("III")
				.pattern(" I ")
				.define('#', candle(DyeColor.BROWN))
				.define('I', Items.IRON_NUGGET)
				.group("candelabras")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.LAMP_CANDELABRA_BROWN))
				.save(consumer, recipeId(BlockInit.LAMP_CANDELABRA_BROWN));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.LAMP_CANDELABRA_CYAN)
				.pattern("###")
				.pattern("III")
				.pattern(" I ")
				.define('#', candle(DyeColor.CYAN))
				.define('I', Items.IRON_NUGGET)
				.group("candelabras")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.LAMP_CANDELABRA_CYAN))
				.save(consumer, recipeId(BlockInit.LAMP_CANDELABRA_CYAN));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.LAMP_CANDELABRA_GRAY)
				.pattern("###")
				.pattern("III")
				.pattern(" I ")
				.define('#', candle(DyeColor.GRAY))
				.define('I', Items.IRON_NUGGET)
				.group("candelabras")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.LAMP_CANDELABRA_GRAY))
				.save(consumer, recipeId(BlockInit.LAMP_CANDELABRA_GRAY));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.LAMP_CANDELABRA_GREEN)
				.pattern("###")
				.pattern("III")
				.pattern(" I ")
				.define('#', candle(DyeColor.GREEN))
				.define('I', Items.IRON_NUGGET)
				.group("candelabras")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.LAMP_CANDELABRA_GREEN))
				.save(consumer, recipeId(BlockInit.LAMP_CANDELABRA_GREEN));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.LAMP_CANDELABRA_LIGHT_BLUE)
				.pattern("###")
				.pattern("III")
				.pattern(" I ")
				.define('#', candle(DyeColor.LIGHT_BLUE))
				.define('I', Items.IRON_NUGGET)
				.group("candelabras")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.LAMP_CANDELABRA_LIGHT_BLUE))
				.save(consumer, recipeId(BlockInit.LAMP_CANDELABRA_LIGHT_BLUE));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.LAMP_CANDELABRA_LIGHT_GRAY)
				.pattern("###")
				.pattern("III")
				.pattern(" I ")
				.define('#', candle(DyeColor.LIGHT_GRAY))
				.define('I', Items.IRON_NUGGET)
				.group("candelabras")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.LAMP_CANDELABRA_LIGHT_GRAY))
				.save(consumer, recipeId(BlockInit.LAMP_CANDELABRA_LIGHT_GRAY));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.LAMP_CANDELABRA_LIME)
				.pattern("###")
				.pattern("III")
				.pattern(" I ")
				.define('#', candle(DyeColor.LIME))
				.define('I', Items.IRON_NUGGET)
				.group("candelabras")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.LAMP_CANDELABRA_LIME))
				.save(consumer, recipeId(BlockInit.LAMP_CANDELABRA_LIME));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.LAMP_CANDELABRA_MAGENTA)
				.pattern("###")
				.pattern("III")
				.pattern(" I ")
				.define('#', candle(DyeColor.MAGENTA))
				.define('I', Items.IRON_NUGGET)
				.group("candelabras")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.LAMP_CANDELABRA_MAGENTA))
				.save(consumer, recipeId(BlockInit.LAMP_CANDELABRA_MAGENTA));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.LAMP_CANDELABRA_ORANGE)
				.pattern("###")
				.pattern("III")
				.pattern(" I ")
				.define('#', candle(DyeColor.ORANGE))
				.define('I', Items.IRON_NUGGET)
				.group("candelabras")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.LAMP_CANDELABRA_ORANGE))
				.save(consumer, recipeId(BlockInit.LAMP_CANDELABRA_ORANGE));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.LAMP_CANDELABRA_PINK)
				.pattern("###")
				.pattern("III")
				.pattern(" I ")
				.define('#', candle(DyeColor.PINK))
				.define('I', Items.IRON_NUGGET)
				.group("candelabras")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.LAMP_CANDELABRA_PINK))
				.save(consumer, recipeId(BlockInit.LAMP_CANDELABRA_PINK));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.LAMP_CANDELABRA_PURPLE)
				.pattern("###")
				.pattern("III")
				.pattern(" I ")
				.define('#', candle(DyeColor.PURPLE))
				.define('I', Items.IRON_NUGGET)
				.group("candelabras")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.LAMP_CANDELABRA_PURPLE))
				.save(consumer, recipeId(BlockInit.LAMP_CANDELABRA_PURPLE));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.LAMP_CANDELABRA_RED)
				.pattern("###")
				.pattern("III")
				.pattern(" I ")
				.define('#', candle(DyeColor.RED))
				.define('I', Items.IRON_NUGGET)
				.group("candelabras")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.LAMP_CANDELABRA_RED))
				.save(consumer, recipeId(BlockInit.LAMP_CANDELABRA_RED));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.LAMP_CANDELABRA_WHITE)
				.pattern("###")
				.pattern("III")
				.pattern(" I ")
				.define('#', candle(DyeColor.WHITE))
				.define('I', Items.IRON_NUGGET)
				.group("candelabras")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.LAMP_CANDELABRA_WHITE))
				.save(consumer, recipeId(BlockInit.LAMP_CANDELABRA_WHITE));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.LAMP_CANDELABRA_YELLOW)
				.pattern("###")
				.pattern("III")
				.pattern(" I ")
				.define('#', candle(DyeColor.YELLOW))
				.define('I', Items.IRON_NUGGET)
				.group("candelabras")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.LAMP_CANDELABRA_YELLOW))
				.save(consumer, recipeId(BlockInit.LAMP_CANDELABRA_YELLOW));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.CHERRY_BLINDS)
				.pattern("/_/")
				.pattern("/_/")
				.define('/', Items.STICK)
				.define('_', Items.CHERRY_SLAB)
				.group("blinds")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.CHERRY_BLINDS))
				.save(consumer, recipeId(BlockInit.CHERRY_BLINDS));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.CHERRY_PICTURE_FRAME)
				.pattern(" _ ")
				.pattern(" W ")
				.pattern(" _/")
				.define('_', Items.CHERRY_SLAB)
				.define('W', whiteWool())
				.define('/', Items.STICK)
				.group("frames")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.CHERRY_PICTURE_FRAME))
				.save(consumer, recipeId(BlockInit.CHERRY_PICTURE_FRAME));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.CHERRY_TRELLIS, 4)
				.pattern("/ /")
				.pattern(" O ")
				.pattern("/ /")
				.define('/', Items.STICK)
				.define('O', Items.CHERRY_LOG)
				.group("trellis")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.CHERRY_TRELLIS))
				.save(consumer, recipeId(BlockInit.CHERRY_TRELLIS));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.CRIMSON_BLINDS)
				.pattern("/_/")
				.pattern("/_/")
				.define('/', Items.STICK)
				.define('_', Items.CRIMSON_SLAB)
				.group("blinds")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.CRIMSON_BLINDS))
				.save(consumer, recipeId(BlockInit.CRIMSON_BLINDS));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.CRIMSON_PICTURE_FRAME)
				.pattern(" _ ")
				.pattern(" W ")
				.pattern(" _/")
				.define('_', Items.CRIMSON_SLAB)
				.define('W', whiteWool())
				.define('/', Items.STICK)
				.group("frames")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.CRIMSON_PICTURE_FRAME))
				.save(consumer, recipeId(BlockInit.CRIMSON_PICTURE_FRAME));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.CRIMSON_TRELLIS, 4)
				.pattern("/ /")
				.pattern(" O ")
				.pattern("/ /")
				.define('/', Items.STICK)
				.define('O', Items.CRIMSON_STEM)
				.group("trellis")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.CRIMSON_TRELLIS))
				.save(consumer, recipeId(BlockInit.CRIMSON_TRELLIS));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.DARK_OAK_BLINDS)
				.pattern("/_/")
				.pattern("/_/")
				.define('/', Items.STICK)
				.define('_', Items.DARK_OAK_SLAB)
				.group("blinds")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.DARK_OAK_BLINDS))
				.save(consumer, recipeId(BlockInit.DARK_OAK_BLINDS));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.DARK_OAK_PICTURE_FRAME)
				.pattern(" _ ")
				.pattern(" W ")
				.pattern(" _/")
				.define('_', Items.DARK_OAK_SLAB)
				.define('W', whiteWool())
				.define('/', Items.STICK)
				.group("frames")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.DARK_OAK_PICTURE_FRAME))
				.save(consumer, recipeId(BlockInit.DARK_OAK_PICTURE_FRAME));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.DARK_OAK_TRELLIS, 4)
				.pattern("/ /")
				.pattern(" O ")
				.pattern("/ /")
				.define('/', Items.STICK)
				.define('O', Items.DARK_OAK_LOG)
				.group("trellis")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.DARK_OAK_TRELLIS))
				.save(consumer, recipeId(BlockInit.DARK_OAK_TRELLIS));
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(BlockInit.IRON_BLINDS), RecipeCategory.MISC,
				Items.IRON_INGOT, 0.1F, 250)
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_INGOT))
				.save(consumer, Beautify.id("from_" + recipePath(BlockInit.IRON_BLINDS)));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.HANGING_POT)
				.pattern("#")
				.pattern("F")
				.define('#', BlockInit.ROPE)
				.define('F', Items.FLOWER_POT)
				.group("hanging_pot")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.HANGING_POT))
				.save(consumer, recipeId(BlockInit.HANGING_POT));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.IRON_BLINDS, 2)
				.pattern("/_/")
				.pattern("/_/")
				.define('/', Items.STICK)
				.define('_', Items.IRON_INGOT)
				.group("blinds")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.IRON_BLINDS))
				.save(consumer, recipeId(BlockInit.IRON_BLINDS));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.JUNGLE_BLINDS)
				.pattern("/_/")
				.pattern("/_/")
				.define('/', Items.STICK)
				.define('_', Items.JUNGLE_SLAB)
				.group("blinds")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.JUNGLE_BLINDS))
				.save(consumer, recipeId(BlockInit.JUNGLE_BLINDS));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.JUNGLE_PICTURE_FRAME)
				.pattern(" _ ")
				.pattern(" W ")
				.pattern(" _/")
				.define('_', Items.JUNGLE_SLAB)
				.define('W', whiteWool())
				.define('/', Items.STICK)
				.group("frames")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.JUNGLE_PICTURE_FRAME))
				.save(consumer, recipeId(BlockInit.JUNGLE_PICTURE_FRAME));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.JUNGLE_TRELLIS, 4)
				.pattern("/ /")
				.pattern(" O ")
				.pattern("/ /")
				.define('/', Items.STICK)
				.define('O', Items.JUNGLE_LOG)
				.group("trellis")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.JUNGLE_TRELLIS))
				.save(consumer, recipeId(BlockInit.JUNGLE_TRELLIS));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.LAMP_BAMBOO)
				.pattern(" # ")
				.pattern("ScS")
				.pattern(" # ")
				.define('#', Items.BAMBOO)
				.define('S', Items.STRING)
				.define('c', Items.COPPER_INGOT)
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.LAMP_BAMBOO))
				.save(consumer, recipeId(BlockInit.LAMP_BAMBOO));
		ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, BlockInit.LAMP_JAR)
				.requires(Items.GLOWSTONE_DUST)
				.requires(Items.GLASS_BOTTLE)
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.LAMP_JAR))
				.save(consumer, recipeId(BlockInit.LAMP_JAR));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.LAMP_LIGHT_BULB)
				.pattern(" # ")
				.pattern("#c#")
				.pattern(" # ")
				.define('#', Items.GLASS_PANE)
				.define('c', Items.COPPER_INGOT)
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.LAMP_LIGHT_BULB))
				.save(consumer, recipeId(BlockInit.LAMP_LIGHT_BULB));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.MANGROVE_BLINDS)
				.pattern("/_/")
				.pattern("/_/")
				.define('/', Items.STICK)
				.define('_', Items.MANGROVE_SLAB)
				.group("blinds")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.MANGROVE_BLINDS))
				.save(consumer, recipeId(BlockInit.MANGROVE_BLINDS));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.MANGROVE_PICTURE_FRAME)
				.pattern(" _ ")
				.pattern(" W ")
				.pattern(" _/")
				.define('_', Items.MANGROVE_SLAB)
				.define('W', whiteWool())
				.define('/', Items.STICK)
				.group("frames")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.MANGROVE_PICTURE_FRAME))
				.save(consumer, recipeId(BlockInit.MANGROVE_PICTURE_FRAME));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.MANGROVE_TRELLIS, 4)
				.pattern("/ /")
				.pattern(" O ")
				.pattern("/ /")
				.define('/', Items.STICK)
				.define('O', Items.MANGROVE_LOG)
				.group("trellis")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.MANGROVE_TRELLIS))
				.save(consumer, recipeId(BlockInit.MANGROVE_TRELLIS));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.OAK_BLINDS)
				.pattern("/_/")
				.pattern("/_/")
				.define('/', Items.STICK)
				.define('_', Items.OAK_SLAB)
				.group("blinds")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.OAK_BLINDS))
				.save(consumer, recipeId(BlockInit.OAK_BLINDS));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.OAK_PICTURE_FRAME)
				.pattern(" _ ")
				.pattern(" W ")
				.pattern(" _/")
				.define('_', Items.OAK_SLAB)
				.define('W', whiteWool())
				.define('/', Items.STICK)
				.group("frames")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.OAK_PICTURE_FRAME))
				.save(consumer, recipeId(BlockInit.OAK_PICTURE_FRAME));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.OAK_TRELLIS, 4)
				.pattern("/ /")
				.pattern(" O ")
				.pattern("/ /")
				.define('/', Items.STICK)
				.define('O', Items.OAK_LOG)
				.group("trellis")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.OAK_TRELLIS))
				.save(consumer, recipeId(BlockInit.OAK_TRELLIS));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.QUARTZ_PICTURE_FRAME)
				.pattern(" _ ")
				.pattern(" W ")
				.pattern(" _/")
				.define('_', Items.QUARTZ_SLAB)
				.define('W', whiteWool())
				.define('/', Items.STICK)
				.group("frames")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.QUARTZ_PICTURE_FRAME))
				.save(consumer, recipeId(BlockInit.QUARTZ_PICTURE_FRAME));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.ROPE)
				.pattern("#")
				.pattern("#")
				.pattern("#")
				.define('#', Items.STRING)
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.ROPE))
				.save(consumer, recipeId(BlockInit.ROPE));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.SPRUCE_BLINDS)
				.pattern("/_/")
				.pattern("/_/")
				.define('/', Items.STICK)
				.define('_', Items.SPRUCE_SLAB)
				.group("blinds")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.SPRUCE_BLINDS))
				.save(consumer, recipeId(BlockInit.SPRUCE_BLINDS));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.SPRUCE_PICTURE_FRAME)
				.pattern(" _ ")
				.pattern(" W ")
				.pattern(" _/")
				.define('_', Items.SPRUCE_SLAB)
				.define('W', whiteWool())
				.define('/', Items.STICK)
				.group("frames")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.SPRUCE_PICTURE_FRAME))
				.save(consumer, recipeId(BlockInit.SPRUCE_PICTURE_FRAME));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.SPRUCE_TRELLIS, 4)
				.pattern("/ /")
				.pattern(" O ")
				.pattern("/ /")
				.define('/', Items.STICK)
				.define('O', Items.SPRUCE_LOG)
				.group("trellis")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.SPRUCE_TRELLIS))
				.save(consumer, recipeId(BlockInit.SPRUCE_TRELLIS));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.WARPED_BLINDS)
				.pattern("/_/")
				.pattern("/_/")
				.define('/', Items.STICK)
				.define('_', Items.WARPED_SLAB)
				.group("blinds")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.WARPED_BLINDS))
				.save(consumer, recipeId(BlockInit.WARPED_BLINDS));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.WARPED_PICTURE_FRAME)
				.pattern(" _ ")
				.pattern(" W ")
				.pattern(" _/")
				.define('_', Items.WARPED_SLAB)
				.define('W', whiteWool())
				.define('/', Items.STICK)
				.group("frames")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.WARPED_PICTURE_FRAME))
				.save(consumer, recipeId(BlockInit.WARPED_PICTURE_FRAME));
		ShapedRecipeBuilder.shaped(RecipeCategory.MISC, BlockInit.WARPED_TRELLIS, 4)
				.pattern("/ /")
				.pattern(" O ")
				.pattern("/ /")
				.define('/', Items.STICK)
				.define('O', Items.WARPED_STEM)
				.group("trellis")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.WARPED_TRELLIS))
				.save(consumer, recipeId(BlockInit.WARPED_TRELLIS));
	}

	private static TagKey<Item> itemTag(String id) {
		return TagKey.create(Registries.ITEM, new Identifier(id));
	}

	private static ItemLike candle(DyeColor color) {
		//? if >=26.2 {
		return Items.DYED_CANDLE.pick(color);
		//?} else {
		/^return switch (color) {
			case BLACK -> Items.BLACK_CANDLE;
			case BLUE -> Items.BLUE_CANDLE;
			case BROWN -> Items.BROWN_CANDLE;
			case CYAN -> Items.CYAN_CANDLE;
			case GRAY -> Items.GRAY_CANDLE;
			case GREEN -> Items.GREEN_CANDLE;
			case LIGHT_BLUE -> Items.LIGHT_BLUE_CANDLE;
			case LIGHT_GRAY -> Items.LIGHT_GRAY_CANDLE;
			case LIME -> Items.LIME_CANDLE;
			case MAGENTA -> Items.MAGENTA_CANDLE;
			case ORANGE -> Items.ORANGE_CANDLE;
			case PINK -> Items.PINK_CANDLE;
			case PURPLE -> Items.PURPLE_CANDLE;
			case RED -> Items.RED_CANDLE;
			case WHITE -> Items.WHITE_CANDLE;
			case YELLOW -> Items.YELLOW_CANDLE;
		};
		^///?}
	}

	private static ItemLike whiteWool() {
		//? if >=26.2
		return Items.WOOL.pick(DyeColor.WHITE);
		//? if <26.2
		//return Items.WHITE_WOOL;
	}

	private static String recipePath(Block block) {
		String path = BuiltInRegistries.BLOCK.getKey(block).getPath();
		if (path.startsWith("lamp_candelabra")) path = path.substring(5);
		return path;
	}

	private static Identifier recipeId(Block block) {
		return Beautify.id(recipePath(block));
	}

	private static Item item(String id) {
		return BuiltInRegistries.ITEM.get(new Identifier(id));
	}
}
*///?}
