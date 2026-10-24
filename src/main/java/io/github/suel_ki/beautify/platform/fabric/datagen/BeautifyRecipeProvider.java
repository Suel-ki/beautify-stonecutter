//? if fabric && >=1.21.1 {
package io.github.suel_ki.beautify.platform.fabric.datagen;

import net.minecraft.world.level.block.Block;
import io.github.suel_ki.beautify.core.init.BlockInit;

import io.github.suel_ki.beautify.Beautify;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.advancements.Advancement;
//? if >=26.2 {
import net.minecraft.advancements.triggers.InventoryChangeTrigger;
//?} else if >=1.21.11 {
/*import net.minecraft.advancements.criterion.InventoryChangeTrigger;
*///?} else {
/*import net.minecraft.advancements.critereon.InventoryChangeTrigger;
*///?}
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.DyeColor;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.item.crafting.Recipe;

import java.util.concurrent.CompletableFuture;

public class BeautifyRecipeProvider extends FabricRecipeProvider {

//? if <26.3 {
	/*@Override
	public String getName() {
		return "Beautify/Recipes";
	}
*///?}

	public BeautifyRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
		super(output, registries);
	}

//? if >=26.3 {
	@Override
	protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, BootstrapContext<Recipe<?>> recipes,
			BootstrapContext<Advancement> advancements) {
		return new RecipeProvider(recipes, advancements) {
			@Override
			public void buildRecipes() {
				build(output, registries.lookupOrThrow(Registries.ITEM));
			}
		};
	}
//?} else if >=1.21.11 {
	/*@Override
	protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
		return new RecipeProvider(registries, output) {
			@Override
			public void buildRecipes() {
				build(output, registries.lookupOrThrow(Registries.ITEM));
			}
		};
	}
*///?}

	//? if <1.21.11 {
	/*@Override
	public void buildRecipes(RecipeOutput output) {
		build(output, null);
	}
	*///?}

	private static ShapedRecipeBuilder shaped(HolderGetter<Item> items, ItemLike result) {
		//? if >=1.21.11 {
		return ShapedRecipeBuilder.shaped(items, RecipeCategory.MISC, result);
		//?} else {
		/*return ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result);
		*///?}
	}

	private static ShapedRecipeBuilder shaped(HolderGetter<Item> items, ItemLike result, int count) {
		//? if >=1.21.11 {
		return ShapedRecipeBuilder.shaped(items, RecipeCategory.MISC, result, count);
		//?} else {
		/*return ShapedRecipeBuilder.shaped(RecipeCategory.MISC, result, count);
		*///?}
	}

	private static ShapelessRecipeBuilder shapeless(HolderGetter<Item> items, ItemLike result) {
		//? if >=1.21.11 {
		return ShapelessRecipeBuilder.shapeless(items, RecipeCategory.MISC, result);
		//?} else {
		/*return ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, result);
		*///?}
	}

	private static void build(RecipeOutput output, HolderGetter<Item> itemGetter) {
		shaped(itemGetter, BlockInit.ACACIA_BLINDS)
				.pattern("/_/")
				.pattern("/_/")
				.define('/', Items.STICK)
				.define('_', Items.ACACIA_SLAB)
				.group("blinds")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.ACACIA_BLINDS))
				.save(output, key(recipePath(BlockInit.ACACIA_BLINDS)));
		shaped(itemGetter, BlockInit.ACACIA_PICTURE_FRAME)
				.pattern(" _ ")
				.pattern(" W ")
				.pattern(" _/")
				.define('_', Items.ACACIA_SLAB)
				.define('W', whiteWool())
				.define('/', Items.STICK)
				.group("frames")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.ACACIA_PICTURE_FRAME))
				.save(output, key(recipePath(BlockInit.ACACIA_PICTURE_FRAME)));
		shaped(itemGetter, BlockInit.ACACIA_TRELLIS, 4)
				.pattern("/ /")
				.pattern(" O ")
				.pattern("/ /")
				.define('/', Items.STICK)
				.define('O', Items.ACACIA_LOG)
				.group("trellis")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.ACACIA_TRELLIS))
				.save(output, key(recipePath(BlockInit.ACACIA_TRELLIS)));
		shaped(itemGetter, BlockInit.BAMBOO_BLINDS)
				.pattern("/_/")
				.pattern("/_/")
				.define('/', Items.STICK)
				.define('_', Items.BAMBOO_SLAB)
				.group("blinds")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.BAMBOO_BLINDS))
				.save(output, key(recipePath(BlockInit.BAMBOO_BLINDS)));
		shaped(itemGetter, BlockInit.BAMBOO_PICTURE_FRAME)
				.pattern(" _ ")
				.pattern(" W ")
				.pattern(" _/")
				.define('_', Items.BAMBOO_SLAB)
				.define('W', whiteWool())
				.define('/', Items.STICK)
				.group("frames")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.BAMBOO_PICTURE_FRAME))
				.save(output, key(recipePath(BlockInit.BAMBOO_PICTURE_FRAME)));
		shaped(itemGetter, BlockInit.BAMBOO_TRELLIS, 4)
				.pattern("/ /")
				.pattern(" O ")
				.pattern("/ /")
				.define('/', Items.STICK)
				.define('O', Items.BAMBOO_BLOCK)
				.group("trellis")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.BAMBOO_TRELLIS))
				.save(output, key(recipePath(BlockInit.BAMBOO_TRELLIS)));
		//? if >=1.21.11 {
			shaped(itemGetter, BlockInit.PALE_OAK_BLINDS)
					.pattern("/_/")
					.pattern("/_/")
					.define('/', Items.STICK)
					.define('_', Items.PALE_OAK_SLAB)
					.group("blinds")
					.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.PALE_OAK_BLINDS))
					.save(output, key(recipePath(BlockInit.PALE_OAK_BLINDS)));
			shaped(itemGetter, BlockInit.PALE_OAK_PICTURE_FRAME)
					.pattern(" _ ")
					.pattern(" W ")
					.pattern(" _/")
					.define('_', Items.PALE_OAK_SLAB)
					.define('W', whiteWool())
					.define('/', Items.STICK)
					.group("frames")
					.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.PALE_OAK_PICTURE_FRAME))
					.save(output, key(recipePath(BlockInit.PALE_OAK_PICTURE_FRAME)));
			shaped(itemGetter, BlockInit.PALE_OAK_TRELLIS, 4)
					.pattern("/ /")
					.pattern(" O ")
					.pattern("/ /")
					.define('/', Items.STICK)
					.define('O', Items.PALE_OAK_LOG)
					.group("trellis")
					.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.PALE_OAK_TRELLIS))
					.save(output, key(recipePath(BlockInit.PALE_OAK_TRELLIS)));
		//?}
		shaped(itemGetter, BlockInit.BIRCH_BLINDS)
				.pattern("/_/")
				.pattern("/_/")
				.define('/', Items.STICK)
				.define('_', Items.BIRCH_SLAB)
				.group("blinds")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.BIRCH_BLINDS))
				.save(output, key(recipePath(BlockInit.BIRCH_BLINDS)));
		shaped(itemGetter, BlockInit.BIRCH_PICTURE_FRAME)
				.pattern(" _ ")
				.pattern(" W ")
				.pattern(" _/")
				.define('_', Items.BIRCH_SLAB)
				.define('W', whiteWool())
				.define('/', Items.STICK)
				.group("frames")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.BIRCH_PICTURE_FRAME))
				.save(output, key(recipePath(BlockInit.BIRCH_PICTURE_FRAME)));
		shaped(itemGetter, BlockInit.BIRCH_TRELLIS, 4)
				.pattern("/ /")
				.pattern(" O ")
				.pattern("/ /")
				.define('/', Items.STICK)
				.define('O', Items.BIRCH_LOG)
				.group("trellis")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.BIRCH_TRELLIS))
				.save(output, key(recipePath(BlockInit.BIRCH_TRELLIS)));
		shapeless(itemGetter, BlockInit.BOOKSTACK)
				.requires(Items.BOOK)
				.requires(Items.BOOK)
				.requires(Items.BOOK)
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.BOOKSTACK))
				.save(output, key(recipePath(BlockInit.BOOKSTACK)));
		shaped(itemGetter, BlockInit.BOTANIST_WORKBENCH)
				.pattern("##")
				.pattern("PP")
				.define('#', Items.FLOWER_POT)
				.define('P', itemTag("minecraft:planks"))
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.BOTANIST_WORKBENCH))
				.save(output, key(recipePath(BlockInit.BOTANIST_WORKBENCH)));
		shaped(itemGetter, BlockInit.LAMP_CANDELABRA)
				.pattern("###")
				.pattern("III")
				.pattern(" I ")
				.define('#', Items.CANDLE)
				.define('I', Items.IRON_NUGGET)
				.group("candelabras")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.LAMP_CANDELABRA))
				.save(output, key(recipePath(BlockInit.LAMP_CANDELABRA)));
		shaped(itemGetter, BlockInit.LAMP_CANDELABRA_BLACK)
				.pattern("###")
				.pattern("III")
				.pattern(" I ")
				.define('#', candle(DyeColor.BLACK))
				.define('I', Items.IRON_NUGGET)
				.group("candelabras")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.LAMP_CANDELABRA_BLACK))
				.save(output, key(recipePath(BlockInit.LAMP_CANDELABRA_BLACK)));
		shaped(itemGetter, BlockInit.LAMP_CANDELABRA_BLUE)
				.pattern("###")
				.pattern("III")
				.pattern(" I ")
				.define('#', candle(DyeColor.BLUE))
				.define('I', Items.IRON_NUGGET)
				.group("candelabras")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.LAMP_CANDELABRA_BLUE))
				.save(output, key(recipePath(BlockInit.LAMP_CANDELABRA_BLUE)));
		shaped(itemGetter, BlockInit.LAMP_CANDELABRA_BROWN)
				.pattern("###")
				.pattern("III")
				.pattern(" I ")
				.define('#', candle(DyeColor.BROWN))
				.define('I', Items.IRON_NUGGET)
				.group("candelabras")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.LAMP_CANDELABRA_BROWN))
				.save(output, key(recipePath(BlockInit.LAMP_CANDELABRA_BROWN)));
		shaped(itemGetter, BlockInit.LAMP_CANDELABRA_CYAN)
				.pattern("###")
				.pattern("III")
				.pattern(" I ")
				.define('#', candle(DyeColor.CYAN))
				.define('I', Items.IRON_NUGGET)
				.group("candelabras")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.LAMP_CANDELABRA_CYAN))
				.save(output, key(recipePath(BlockInit.LAMP_CANDELABRA_CYAN)));
		shaped(itemGetter, BlockInit.LAMP_CANDELABRA_GRAY)
				.pattern("###")
				.pattern("III")
				.pattern(" I ")
				.define('#', candle(DyeColor.GRAY))
				.define('I', Items.IRON_NUGGET)
				.group("candelabras")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.LAMP_CANDELABRA_GRAY))
				.save(output, key(recipePath(BlockInit.LAMP_CANDELABRA_GRAY)));
		shaped(itemGetter, BlockInit.LAMP_CANDELABRA_GREEN)
				.pattern("###")
				.pattern("III")
				.pattern(" I ")
				.define('#', candle(DyeColor.GREEN))
				.define('I', Items.IRON_NUGGET)
				.group("candelabras")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.LAMP_CANDELABRA_GREEN))
				.save(output, key(recipePath(BlockInit.LAMP_CANDELABRA_GREEN)));
		shaped(itemGetter, BlockInit.LAMP_CANDELABRA_LIGHT_BLUE)
				.pattern("###")
				.pattern("III")
				.pattern(" I ")
				.define('#', candle(DyeColor.LIGHT_BLUE))
				.define('I', Items.IRON_NUGGET)
				.group("candelabras")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.LAMP_CANDELABRA_LIGHT_BLUE))
				.save(output, key(recipePath(BlockInit.LAMP_CANDELABRA_LIGHT_BLUE)));
		shaped(itemGetter, BlockInit.LAMP_CANDELABRA_LIGHT_GRAY)
				.pattern("###")
				.pattern("III")
				.pattern(" I ")
				.define('#', candle(DyeColor.LIGHT_GRAY))
				.define('I', Items.IRON_NUGGET)
				.group("candelabras")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.LAMP_CANDELABRA_LIGHT_GRAY))
				.save(output, key(recipePath(BlockInit.LAMP_CANDELABRA_LIGHT_GRAY)));
		shaped(itemGetter, BlockInit.LAMP_CANDELABRA_LIME)
				.pattern("###")
				.pattern("III")
				.pattern(" I ")
				.define('#', candle(DyeColor.LIME))
				.define('I', Items.IRON_NUGGET)
				.group("candelabras")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.LAMP_CANDELABRA_LIME))
				.save(output, key(recipePath(BlockInit.LAMP_CANDELABRA_LIME)));
		shaped(itemGetter, BlockInit.LAMP_CANDELABRA_MAGENTA)
				.pattern("###")
				.pattern("III")
				.pattern(" I ")
				.define('#', candle(DyeColor.MAGENTA))
				.define('I', Items.IRON_NUGGET)
				.group("candelabras")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.LAMP_CANDELABRA_MAGENTA))
				.save(output, key(recipePath(BlockInit.LAMP_CANDELABRA_MAGENTA)));
		shaped(itemGetter, BlockInit.LAMP_CANDELABRA_ORANGE)
				.pattern("###")
				.pattern("III")
				.pattern(" I ")
				.define('#', candle(DyeColor.ORANGE))
				.define('I', Items.IRON_NUGGET)
				.group("candelabras")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.LAMP_CANDELABRA_ORANGE))
				.save(output, key(recipePath(BlockInit.LAMP_CANDELABRA_ORANGE)));
		shaped(itemGetter, BlockInit.LAMP_CANDELABRA_PINK)
				.pattern("###")
				.pattern("III")
				.pattern(" I ")
				.define('#', candle(DyeColor.PINK))
				.define('I', Items.IRON_NUGGET)
				.group("candelabras")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.LAMP_CANDELABRA_PINK))
				.save(output, key(recipePath(BlockInit.LAMP_CANDELABRA_PINK)));
		shaped(itemGetter, BlockInit.LAMP_CANDELABRA_PURPLE)
				.pattern("###")
				.pattern("III")
				.pattern(" I ")
				.define('#', candle(DyeColor.PURPLE))
				.define('I', Items.IRON_NUGGET)
				.group("candelabras")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.LAMP_CANDELABRA_PURPLE))
				.save(output, key(recipePath(BlockInit.LAMP_CANDELABRA_PURPLE)));
		shaped(itemGetter, BlockInit.LAMP_CANDELABRA_RED)
				.pattern("###")
				.pattern("III")
				.pattern(" I ")
				.define('#', candle(DyeColor.RED))
				.define('I', Items.IRON_NUGGET)
				.group("candelabras")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.LAMP_CANDELABRA_RED))
				.save(output, key(recipePath(BlockInit.LAMP_CANDELABRA_RED)));
		shaped(itemGetter, BlockInit.LAMP_CANDELABRA_WHITE)
				.pattern("###")
				.pattern("III")
				.pattern(" I ")
				.define('#', candle(DyeColor.WHITE))
				.define('I', Items.IRON_NUGGET)
				.group("candelabras")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.LAMP_CANDELABRA_WHITE))
				.save(output, key(recipePath(BlockInit.LAMP_CANDELABRA_WHITE)));
		shaped(itemGetter, BlockInit.LAMP_CANDELABRA_YELLOW)
				.pattern("###")
				.pattern("III")
				.pattern(" I ")
				.define('#', candle(DyeColor.YELLOW))
				.define('I', Items.IRON_NUGGET)
				.group("candelabras")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.LAMP_CANDELABRA_YELLOW))
				.save(output, key(recipePath(BlockInit.LAMP_CANDELABRA_YELLOW)));
		shaped(itemGetter, BlockInit.CHERRY_BLINDS)
				.pattern("/_/")
				.pattern("/_/")
				.define('/', Items.STICK)
				.define('_', Items.CHERRY_SLAB)
				.group("blinds")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.CHERRY_BLINDS))
				.save(output, key(recipePath(BlockInit.CHERRY_BLINDS)));
		shaped(itemGetter, BlockInit.CHERRY_PICTURE_FRAME)
				.pattern(" _ ")
				.pattern(" W ")
				.pattern(" _/")
				.define('_', Items.CHERRY_SLAB)
				.define('W', whiteWool())
				.define('/', Items.STICK)
				.group("frames")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.CHERRY_PICTURE_FRAME))
				.save(output, key(recipePath(BlockInit.CHERRY_PICTURE_FRAME)));
		shaped(itemGetter, BlockInit.CHERRY_TRELLIS, 4)
				.pattern("/ /")
				.pattern(" O ")
				.pattern("/ /")
				.define('/', Items.STICK)
				.define('O', Items.CHERRY_LOG)
				.group("trellis")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.CHERRY_TRELLIS))
				.save(output, key(recipePath(BlockInit.CHERRY_TRELLIS)));
		shaped(itemGetter, BlockInit.CRIMSON_BLINDS)
				.pattern("/_/")
				.pattern("/_/")
				.define('/', Items.STICK)
				.define('_', Items.CRIMSON_SLAB)
				.group("blinds")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.CRIMSON_BLINDS))
				.save(output, key(recipePath(BlockInit.CRIMSON_BLINDS)));
		shaped(itemGetter, BlockInit.CRIMSON_PICTURE_FRAME)
				.pattern(" _ ")
				.pattern(" W ")
				.pattern(" _/")
				.define('_', Items.CRIMSON_SLAB)
				.define('W', whiteWool())
				.define('/', Items.STICK)
				.group("frames")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.CRIMSON_PICTURE_FRAME))
				.save(output, key(recipePath(BlockInit.CRIMSON_PICTURE_FRAME)));
		shaped(itemGetter, BlockInit.CRIMSON_TRELLIS, 4)
				.pattern("/ /")
				.pattern(" O ")
				.pattern("/ /")
				.define('/', Items.STICK)
				.define('O', Items.CRIMSON_STEM)
				.group("trellis")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.CRIMSON_TRELLIS))
				.save(output, key(recipePath(BlockInit.CRIMSON_TRELLIS)));
		shaped(itemGetter, BlockInit.DARK_OAK_BLINDS)
				.pattern("/_/")
				.pattern("/_/")
				.define('/', Items.STICK)
				.define('_', Items.DARK_OAK_SLAB)
				.group("blinds")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.DARK_OAK_BLINDS))
				.save(output, key(recipePath(BlockInit.DARK_OAK_BLINDS)));
		shaped(itemGetter, BlockInit.DARK_OAK_PICTURE_FRAME)
				.pattern(" _ ")
				.pattern(" W ")
				.pattern(" _/")
				.define('_', Items.DARK_OAK_SLAB)
				.define('W', whiteWool())
				.define('/', Items.STICK)
				.group("frames")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.DARK_OAK_PICTURE_FRAME))
				.save(output, key(recipePath(BlockInit.DARK_OAK_PICTURE_FRAME)));
		shaped(itemGetter, BlockInit.DARK_OAK_TRELLIS, 4)
				.pattern("/ /")
				.pattern(" O ")
				.pattern("/ /")
				.define('/', Items.STICK)
				.define('O', Items.DARK_OAK_LOG)
				.group("trellis")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.DARK_OAK_TRELLIS))
				.save(output, key(recipePath(BlockInit.DARK_OAK_TRELLIS)));
		//? if >=26.1.2 {
		SimpleCookingRecipeBuilder.smelting(Ingredient.of(BlockInit.IRON_BLINDS), RecipeCategory.MISC,
				CookingBookCategory.BLOCKS, Items.IRON_INGOT, 0.1F, 250)
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_INGOT))
				.save(output, key("from_" + recipePath(BlockInit.IRON_BLINDS)));
		//?} else {
		/*SimpleCookingRecipeBuilder.smelting(Ingredient.of(BlockInit.IRON_BLINDS), RecipeCategory.MISC, Items.IRON_INGOT, 0.1F, 250)
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(Items.IRON_INGOT))
				.save(output, key("from_" + recipePath(BlockInit.IRON_BLINDS)));
		*///?}
		shaped(itemGetter, BlockInit.HANGING_POT)
				.pattern("#")
				.pattern("F")
				.define('#', BlockInit.ROPE)
				.define('F', Items.FLOWER_POT)
				.group("hanging_pot")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.HANGING_POT))
				.save(output, key(recipePath(BlockInit.HANGING_POT)));
		shaped(itemGetter, BlockInit.IRON_BLINDS, 2)
				.pattern("/_/")
				.pattern("/_/")
				.define('/', Items.STICK)
				.define('_', Items.IRON_INGOT)
				.group("blinds")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.IRON_BLINDS))
				.save(output, key(recipePath(BlockInit.IRON_BLINDS)));
		shaped(itemGetter, BlockInit.JUNGLE_BLINDS)
				.pattern("/_/")
				.pattern("/_/")
				.define('/', Items.STICK)
				.define('_', Items.JUNGLE_SLAB)
				.group("blinds")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.JUNGLE_BLINDS))
				.save(output, key(recipePath(BlockInit.JUNGLE_BLINDS)));
		shaped(itemGetter, BlockInit.JUNGLE_PICTURE_FRAME)
				.pattern(" _ ")
				.pattern(" W ")
				.pattern(" _/")
				.define('_', Items.JUNGLE_SLAB)
				.define('W', whiteWool())
				.define('/', Items.STICK)
				.group("frames")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.JUNGLE_PICTURE_FRAME))
				.save(output, key(recipePath(BlockInit.JUNGLE_PICTURE_FRAME)));
		shaped(itemGetter, BlockInit.JUNGLE_TRELLIS, 4)
				.pattern("/ /")
				.pattern(" O ")
				.pattern("/ /")
				.define('/', Items.STICK)
				.define('O', Items.JUNGLE_LOG)
				.group("trellis")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.JUNGLE_TRELLIS))
				.save(output, key(recipePath(BlockInit.JUNGLE_TRELLIS)));
		shaped(itemGetter, BlockInit.LAMP_BAMBOO)
				.pattern(" # ")
				.pattern("ScS")
				.pattern(" # ")
				.define('#', Items.BAMBOO)
				.define('S', Items.STRING)
				.define('c', Items.COPPER_INGOT)
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.LAMP_BAMBOO))
				.save(output, key(recipePath(BlockInit.LAMP_BAMBOO)));
		shapeless(itemGetter, BlockInit.LAMP_JAR)
				.requires(Items.GLOWSTONE_DUST)
				.requires(Items.GLASS_BOTTLE)
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.LAMP_JAR))
				.save(output, key(recipePath(BlockInit.LAMP_JAR)));
		shaped(itemGetter, BlockInit.LAMP_LIGHT_BULB)
				.pattern(" # ")
				.pattern("#c#")
				.pattern(" # ")
				.define('#', Items.GLASS_PANE)
				.define('c', Items.COPPER_INGOT)
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.LAMP_LIGHT_BULB))
				.save(output, key(recipePath(BlockInit.LAMP_LIGHT_BULB)));
		shaped(itemGetter, BlockInit.MANGROVE_BLINDS)
				.pattern("/_/")
				.pattern("/_/")
				.define('/', Items.STICK)
				.define('_', Items.MANGROVE_SLAB)
				.group("blinds")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.MANGROVE_BLINDS))
				.save(output, key(recipePath(BlockInit.MANGROVE_BLINDS)));
		shaped(itemGetter, BlockInit.MANGROVE_PICTURE_FRAME)
				.pattern(" _ ")
				.pattern(" W ")
				.pattern(" _/")
				.define('_', Items.MANGROVE_SLAB)
				.define('W', whiteWool())
				.define('/', Items.STICK)
				.group("frames")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.MANGROVE_PICTURE_FRAME))
				.save(output, key(recipePath(BlockInit.MANGROVE_PICTURE_FRAME)));
		shaped(itemGetter, BlockInit.MANGROVE_TRELLIS, 4)
				.pattern("/ /")
				.pattern(" O ")
				.pattern("/ /")
				.define('/', Items.STICK)
				.define('O', Items.MANGROVE_LOG)
				.group("trellis")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.MANGROVE_TRELLIS))
				.save(output, key(recipePath(BlockInit.MANGROVE_TRELLIS)));
		shaped(itemGetter, BlockInit.OAK_BLINDS)
				.pattern("/_/")
				.pattern("/_/")
				.define('/', Items.STICK)
				.define('_', Items.OAK_SLAB)
				.group("blinds")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.OAK_BLINDS))
				.save(output, key(recipePath(BlockInit.OAK_BLINDS)));
		shaped(itemGetter, BlockInit.OAK_PICTURE_FRAME)
				.pattern(" _ ")
				.pattern(" W ")
				.pattern(" _/")
				.define('_', Items.OAK_SLAB)
				.define('W', whiteWool())
				.define('/', Items.STICK)
				.group("frames")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.OAK_PICTURE_FRAME))
				.save(output, key(recipePath(BlockInit.OAK_PICTURE_FRAME)));
		shaped(itemGetter, BlockInit.OAK_TRELLIS, 4)
				.pattern("/ /")
				.pattern(" O ")
				.pattern("/ /")
				.define('/', Items.STICK)
				.define('O', Items.OAK_LOG)
				.group("trellis")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.OAK_TRELLIS))
				.save(output, key(recipePath(BlockInit.OAK_TRELLIS)));
		shaped(itemGetter, BlockInit.QUARTZ_PICTURE_FRAME)
				.pattern(" _ ")
				.pattern(" W ")
				.pattern(" _/")
				.define('_', Items.QUARTZ_SLAB)
				.define('W', whiteWool())
				.define('/', Items.STICK)
				.group("frames")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.QUARTZ_PICTURE_FRAME))
				.save(output, key(recipePath(BlockInit.QUARTZ_PICTURE_FRAME)));
		shaped(itemGetter, BlockInit.ROPE)
				.pattern("#")
				.pattern("#")
				.pattern("#")
				.define('#', Items.STRING)
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.ROPE))
				.save(output, key(recipePath(BlockInit.ROPE)));
		shaped(itemGetter, BlockInit.SPRUCE_BLINDS)
				.pattern("/_/")
				.pattern("/_/")
				.define('/', Items.STICK)
				.define('_', Items.SPRUCE_SLAB)
				.group("blinds")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.SPRUCE_BLINDS))
				.save(output, key(recipePath(BlockInit.SPRUCE_BLINDS)));
		shaped(itemGetter, BlockInit.SPRUCE_PICTURE_FRAME)
				.pattern(" _ ")
				.pattern(" W ")
				.pattern(" _/")
				.define('_', Items.SPRUCE_SLAB)
				.define('W', whiteWool())
				.define('/', Items.STICK)
				.group("frames")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.SPRUCE_PICTURE_FRAME))
				.save(output, key(recipePath(BlockInit.SPRUCE_PICTURE_FRAME)));
		shaped(itemGetter, BlockInit.SPRUCE_TRELLIS, 4)
				.pattern("/ /")
				.pattern(" O ")
				.pattern("/ /")
				.define('/', Items.STICK)
				.define('O', Items.SPRUCE_LOG)
				.group("trellis")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.SPRUCE_TRELLIS))
				.save(output, key(recipePath(BlockInit.SPRUCE_TRELLIS)));
		shaped(itemGetter, BlockInit.WARPED_BLINDS)
				.pattern("/_/")
				.pattern("/_/")
				.define('/', Items.STICK)
				.define('_', Items.WARPED_SLAB)
				.group("blinds")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.WARPED_BLINDS))
				.save(output, key(recipePath(BlockInit.WARPED_BLINDS)));
		shaped(itemGetter, BlockInit.WARPED_PICTURE_FRAME)
				.pattern(" _ ")
				.pattern(" W ")
				.pattern(" _/")
				.define('_', Items.WARPED_SLAB)
				.define('W', whiteWool())
				.define('/', Items.STICK)
				.group("frames")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.WARPED_PICTURE_FRAME))
				.save(output, key(recipePath(BlockInit.WARPED_PICTURE_FRAME)));
		shaped(itemGetter, BlockInit.WARPED_TRELLIS, 4)
				.pattern("/ /")
				.pattern(" O ")
				.pattern("/ /")
				.define('/', Items.STICK)
				.define('O', Items.WARPED_STEM)
				.group("trellis")
				.unlockedBy("has_item", InventoryChangeTrigger.TriggerInstance.hasItems(BlockInit.WARPED_TRELLIS))
				.save(output, key(recipePath(BlockInit.WARPED_TRELLIS)));
	}

	private static TagKey<Item> itemTag(String id) {
		return TagKey.create(Registries.ITEM, Identifier.parse(id));
	}

	private static ItemLike candle(DyeColor color) {
		//? if >=26.2 {
		return Items.DYED_CANDLE.pick(color);
		//?} else {
		/*return switch (color) {
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
		*///?}
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

	private static Item item(String id) {
		//? if >=1.21.2
		return BuiltInRegistries.ITEM.getValue(Identifier.parse(id));
		//? if <1.21.2
		//return BuiltInRegistries.ITEM.get(Identifier.parse(id));
	}

	//? if >=1.21.11 {
	private static ResourceKey<Recipe<?>> key(String id) {
		return ResourceKey.create(Registries.RECIPE, Beautify.id(id));
	}
	//?} else {
	/*private static Identifier key(String id) {
		return Beautify.id(id);
	}
	*///?}
}
//?}
