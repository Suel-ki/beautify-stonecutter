//? if fabric && >=1.21.1 {
package io.github.suel_ki.beautify.platform.fabric.datagen;

import net.minecraft.world.item.DyeColor;
import io.github.suel_ki.beautify.Beautify;
import io.github.suel_ki.beautify.core.init.BlockInit;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRewards;
//? if >=26.2 {
import net.minecraft.advancements.predicates.ItemPredicate;
import net.minecraft.advancements.triggers.InventoryChangeTrigger;
import net.minecraft.advancements.triggers.PlayerTrigger;
//?} else if >=1.21.11 {
/*import net.minecraft.advancements.criterion.InventoryChangeTrigger;
import net.minecraft.advancements.criterion.ItemPredicate;
import net.minecraft.advancements.criterion.PlayerTrigger;
*///?} else {
/*import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.PlayerTrigger;
*///?}
import net.minecraft.advancements.AdvancementType;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.crafting.Recipe;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

@SuppressWarnings("removal")
public class BeautifyAdvancementProvider extends FabricAdvancementProvider {

	public BeautifyAdvancementProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
		super(output, registries);
	}

	@Override
	public void generateAdvancement(HolderLookup.Provider provider, Consumer<AdvancementHolder> consumer) {
		recipeUnlock(consumer, "blinds", Items.STICK,
				List.of(BlockInit.OAK_BLINDS, BlockInit.SPRUCE_BLINDS, BlockInit.BIRCH_BLINDS, BlockInit.DARK_OAK_BLINDS, BlockInit.JUNGLE_BLINDS, BlockInit.CRIMSON_BLINDS, BlockInit.CHERRY_BLINDS, BlockInit.ACACIA_BLINDS, BlockInit.WARPED_BLINDS, BlockInit.MANGROVE_BLINDS, BlockInit.BAMBOO_BLINDS
						//? if >=1.21.11 {
						, BlockInit.PALE_OAK_BLINDS
						//?}
						));
		recipeUnlock(consumer, "bookstack", Items.BOOK, List.of(BlockInit.BOOKSTACK));
		recipeUnlock(consumer, "botanist_workbench", Items.FLOWER_POT, List.of(BlockInit.BOTANIST_WORKBENCH));
		recipeUnlock(consumer, "candelabras", Items.IRON_NUGGET,
				List.of(BlockInit.LAMP_CANDELABRA, BlockInit.LAMP_CANDELABRA_LIGHT_BLUE, BlockInit.LAMP_CANDELABRA_LIGHT_GRAY, BlockInit.LAMP_CANDELABRA_BLACK, BlockInit.LAMP_CANDELABRA_BLUE, BlockInit.LAMP_CANDELABRA_BROWN, BlockInit.LAMP_CANDELABRA_CYAN, BlockInit.LAMP_CANDELABRA_GRAY, BlockInit.LAMP_CANDELABRA_GREEN, BlockInit.LAMP_CANDELABRA_LIME, BlockInit.LAMP_CANDELABRA_MAGENTA, BlockInit.LAMP_CANDELABRA_ORANGE, BlockInit.LAMP_CANDELABRA_PINK, BlockInit.LAMP_CANDELABRA_PURPLE, BlockInit.LAMP_CANDELABRA_RED, BlockInit.LAMP_CANDELABRA_WHITE, BlockInit.LAMP_CANDELABRA_YELLOW));
		recipeUnlock(consumer, "frames", whiteWool(),
				List.of(BlockInit.OAK_PICTURE_FRAME, BlockInit.SPRUCE_PICTURE_FRAME, BlockInit.BIRCH_PICTURE_FRAME, BlockInit.DARK_OAK_PICTURE_FRAME, BlockInit.JUNGLE_PICTURE_FRAME, BlockInit.ACACIA_PICTURE_FRAME, BlockInit.WARPED_PICTURE_FRAME, BlockInit.CRIMSON_PICTURE_FRAME, BlockInit.MANGROVE_PICTURE_FRAME, BlockInit.QUARTZ_PICTURE_FRAME, BlockInit.BAMBOO_PICTURE_FRAME
						//? if >=1.21.11 {
						, BlockInit.PALE_OAK_PICTURE_FRAME
						//?}
						));
		recipeUnlock(consumer, "hanging_pot", BlockInit.ROPE, List.of(BlockInit.HANGING_POT));
		recipeUnlock(consumer, "iron_blinds", Items.IRON_INGOT, List.of(BlockInit.IRON_BLINDS));
		recipeUnlock(consumer, "lamps", Items.COPPER_INGOT, List.of(BlockInit.LAMP_LIGHT_BULB, BlockInit.LAMP_BAMBOO));
		recipeUnlock(consumer, "lamp_jar", Items.GLOWSTONE_DUST, List.of(BlockInit.LAMP_JAR));
		recipeUnlock(consumer, "rope", Items.STRING, List.of(BlockInit.ROPE));
		recipeUnlock(consumer, "trellis", Items.STICK,
				List.of(BlockInit.OAK_TRELLIS, BlockInit.SPRUCE_TRELLIS, BlockInit.BIRCH_TRELLIS, BlockInit.JUNGLE_TRELLIS, BlockInit.ACACIA_TRELLIS, BlockInit.DARK_OAK_TRELLIS, BlockInit.MANGROVE_TRELLIS, BlockInit.CRIMSON_TRELLIS, BlockInit.CHERRY_TRELLIS, BlockInit.WARPED_TRELLIS, BlockInit.BAMBOO_TRELLIS
						//? if >=1.21.11 {
						, BlockInit.PALE_OAK_TRELLIS
						//?}
						));

		progressionRoot(consumer);
		progression(provider, consumer, "blinds", "root", BlockInit.OAK_BLINDS,
				BlockInit.OAK_BLINDS, BlockInit.SPRUCE_BLINDS, BlockInit.DARK_OAK_BLINDS, BlockInit.BIRCH_BLINDS,
				BlockInit.ACACIA_BLINDS, BlockInit.JUNGLE_BLINDS, BlockInit.WARPED_BLINDS, BlockInit.CRIMSON_BLINDS,
				BlockInit.CHERRY_BLINDS, BlockInit.IRON_BLINDS, BlockInit.MANGROVE_BLINDS);
		progression(provider, consumer, "bookstack", "root", BlockInit.BOOKSTACK, BlockInit.BOOKSTACK);
		progression(provider, consumer, "candelabra", "root", BlockInit.LAMP_CANDELABRA, BlockInit.LAMP_CANDELABRA);
		progression(provider, consumer, "hanging_pot", "rope", BlockInit.HANGING_POT, BlockInit.HANGING_POT);
		progression(provider, consumer, "light", "root", BlockInit.LAMP_LIGHT_BULB,
				BlockInit.LAMP_JAR, BlockInit.LAMP_BAMBOO, BlockInit.LAMP_LIGHT_BULB);
		progression(provider, consumer, "rope", "root", BlockInit.ROPE, BlockInit.ROPE);
	}

	private static void recipeUnlock(Consumer<AdvancementHolder> consumer, String name, ItemLike item, List<Block> recipes) {
		Advancement.Builder builder = Advancement.Builder.recipeAdvancement()
				.parent(Identifier.parse("minecraft:recipes/root"))
				.addCriterion("requirement", InventoryChangeTrigger.TriggerInstance.hasItems(item));
		AdvancementRewards.Builder rewards = new AdvancementRewards.Builder();
		for (Block recipe : recipes) {
			//? if >=1.21.11
			rewards.addRecipe(ResourceKey.create(Registries.RECIPE, Beautify.id(recipePath(recipe))));
			//? if <1.21.11
			//rewards.addRecipe(Beautify.id(recipePath(recipe)));
		}
		consumer.accept(builder.rewards(rewards).build(Beautify.id(name)));
	}

	private static void progressionRoot(Consumer<AdvancementHolder> consumer) {
		//? if >=26.3 {
		consumer.accept(Advancement.Builder.advancement()
				.rootDisplay(Items.ROSE_BUSH,
						Component.translatable("advancements.beautify.root.title"),
						Component.translatable("advancements.beautify.root.description"),
						Identifier.parse("minecraft:block/spruce_planks"),
						AdvancementType.TASK, false, false, false)
				.addCriterion("Install", PlayerTrigger.TriggerInstance.tick())
				.build(Beautify.id("progression/root")));
		//?} else {
		/*consumer.accept(Advancement.Builder.advancement()
				.display(Items.ROSE_BUSH,
						Component.translatable("advancements.beautify.root.title"),
						Component.translatable("advancements.beautify.root.description"),
						Identifier.parse("minecraft:block/spruce_planks"),
						AdvancementType.TASK, false, false, false)
				.addCriterion("Install", PlayerTrigger.TriggerInstance.tick())
				.build(Beautify.id("progression/root")));
		*///?}
	}

	private static void progression(HolderLookup.Provider provider, Consumer<AdvancementHolder> consumer, String name,
			String parent, ItemLike icon, ItemLike... items) {
		ItemLike[] itemLikes = new ItemLike[items.length];
		for (int i = 0; i < items.length; i++) {
			itemLikes[i] = items[i];
		}
		ItemPredicate.Builder predicate = ItemPredicate.Builder.item();
		//? if >=1.21.11
		predicate.of(provider.lookupOrThrow(Registries.ITEM), itemLikes);
		//? if <1.21.11
		//predicate.of(itemLikes);
		consumer.accept(Advancement.Builder.advancement()
				.parent(Identifier.parse("beautify:progression/" + parent))
				//? if >=26.3 {
				.display(icon.asItem(),
						Component.translatable("advancements.beautify." + name + ".title"),
						Component.translatable("advancements.beautify." + name + ".description"),
						AdvancementType.TASK, true, true, false)
				//?} else {
				/*.display(icon.asItem(),
						Component.translatable("advancements.beautify." + name + ".title"),
						Component.translatable("advancements.beautify." + name + ".description"),
						null, AdvancementType.TASK, true, true, false)
				*///?}
				.addCriterion("obtain", InventoryChangeTrigger.TriggerInstance.hasItems(predicate.build()))
				.build(Beautify.id("progression/" + name)));
	}

	private static String recipePath(Block block) {
		String path = BuiltInRegistries.BLOCK.getKey(block).getPath();
		if (path.startsWith("lamp_candelabra")) path = path.substring(5);
		return path;
	}

	private static ItemLike whiteWool() {
		//? if >=26.2 {
		return Items.WOOL.pick(DyeColor.WHITE);
		//?} else {
		//return Items.WHITE_WOOL;
		//?}
	}

	private static Item item(String id) {
		//? if >=1.21.2
		return BuiltInRegistries.ITEM.getValue(Identifier.parse(id));
		//? if <1.21.2
		//return BuiltInRegistries.ITEM.get(Identifier.parse(id));
	}
}
//?}
