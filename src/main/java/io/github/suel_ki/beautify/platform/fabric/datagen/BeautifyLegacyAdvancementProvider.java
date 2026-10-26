//? if fabric && <1.21.1 {
/*package io.github.suel_ki.beautify.platform.fabric.datagen;

import net.minecraft.world.item.DyeColor;
import io.github.suel_ki.beautify.core.init.BlockInit;

import io.github.suel_ki.beautify.Beautify;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.PlayerTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

public class BeautifyLegacyAdvancementProvider extends FabricAdvancementProvider {

	public BeautifyLegacyAdvancementProvider(FabricPackOutput output) {
		super(output);
	}

	@Override
	public void generateAdvancement(Consumer<Advancement> consumer) {
		recipeUnlock(consumer, "blinds", Items.STICK, List.of(BlockInit.OAK_BLINDS, BlockInit.SPRUCE_BLINDS, BlockInit.BIRCH_BLINDS, BlockInit.DARK_OAK_BLINDS, BlockInit.JUNGLE_BLINDS, BlockInit.CRIMSON_BLINDS, BlockInit.CHERRY_BLINDS, BlockInit.ACACIA_BLINDS, BlockInit.WARPED_BLINDS, BlockInit.MANGROVE_BLINDS, BlockInit.BAMBOO_BLINDS));
		recipeUnlock(consumer, "bookstack", Items.BOOK, List.of(BlockInit.BOOKSTACK));
		recipeUnlock(consumer, "botanist_workbench", Items.FLOWER_POT, List.of(BlockInit.BOTANIST_WORKBENCH));
		recipeUnlock(consumer, "candelabras", Items.IRON_NUGGET, List.of(BlockInit.LAMP_CANDELABRA, BlockInit.LAMP_CANDELABRA_LIGHT_BLUE, BlockInit.LAMP_CANDELABRA_LIGHT_GRAY, BlockInit.LAMP_CANDELABRA_BLACK, BlockInit.LAMP_CANDELABRA_BLUE, BlockInit.LAMP_CANDELABRA_BROWN, BlockInit.LAMP_CANDELABRA_CYAN, BlockInit.LAMP_CANDELABRA_GRAY, BlockInit.LAMP_CANDELABRA_GREEN, BlockInit.LAMP_CANDELABRA_LIME, BlockInit.LAMP_CANDELABRA_MAGENTA, BlockInit.LAMP_CANDELABRA_ORANGE, BlockInit.LAMP_CANDELABRA_PINK, BlockInit.LAMP_CANDELABRA_PURPLE, BlockInit.LAMP_CANDELABRA_RED, BlockInit.LAMP_CANDELABRA_WHITE, BlockInit.LAMP_CANDELABRA_YELLOW));
		recipeUnlock(consumer, "frames", whiteWool(), List.of(BlockInit.OAK_PICTURE_FRAME, BlockInit.SPRUCE_PICTURE_FRAME, BlockInit.BIRCH_PICTURE_FRAME, BlockInit.DARK_OAK_PICTURE_FRAME, BlockInit.JUNGLE_PICTURE_FRAME, BlockInit.ACACIA_PICTURE_FRAME, BlockInit.WARPED_PICTURE_FRAME, BlockInit.CRIMSON_PICTURE_FRAME, BlockInit.MANGROVE_PICTURE_FRAME, BlockInit.QUARTZ_PICTURE_FRAME, BlockInit.BAMBOO_PICTURE_FRAME));
		recipeUnlock(consumer, "iron_blinds", Items.IRON_INGOT, List.of(BlockInit.IRON_BLINDS));
		recipeUnlock(consumer, "lamps", Items.COPPER_INGOT, List.of(BlockInit.LAMP_LIGHT_BULB, BlockInit.LAMP_BAMBOO));
		recipeUnlock(consumer, "lamp_jar", Items.GLOWSTONE_DUST, List.of(BlockInit.LAMP_JAR));
		recipeUnlock(consumer, "rope", Items.STRING, List.of(BlockInit.ROPE));
		recipeUnlock(consumer, "trellis", Items.STICK, List.of(BlockInit.OAK_TRELLIS, BlockInit.SPRUCE_TRELLIS, BlockInit.BIRCH_TRELLIS, BlockInit.JUNGLE_TRELLIS, BlockInit.ACACIA_TRELLIS, BlockInit.DARK_OAK_TRELLIS, BlockInit.MANGROVE_TRELLIS, BlockInit.CRIMSON_TRELLIS, BlockInit.CHERRY_TRELLIS, BlockInit.WARPED_TRELLIS, BlockInit.BAMBOO_TRELLIS));
		recipeUnlock(consumer, "hanging_pot", BlockInit.ROPE, List.of(BlockInit.HANGING_POT));

		progressionRoot(consumer);
		progression(consumer, "blinds", "root", BlockInit.OAK_BLINDS, BlockInit.OAK_BLINDS, BlockInit.SPRUCE_BLINDS, BlockInit.DARK_OAK_BLINDS, BlockInit.BIRCH_BLINDS, BlockInit.ACACIA_BLINDS, BlockInit.JUNGLE_BLINDS, BlockInit.WARPED_BLINDS, BlockInit.CRIMSON_BLINDS, BlockInit.CHERRY_BLINDS, BlockInit.IRON_BLINDS, BlockInit.MANGROVE_BLINDS, BlockInit.BAMBOO_BLINDS);
		progression(consumer, "bookstack", "root", BlockInit.BOOKSTACK, BlockInit.BOOKSTACK);
		progression(consumer, "candelabra", "root", BlockInit.LAMP_CANDELABRA, BlockInit.LAMP_CANDELABRA);
		progression(consumer, "hanging_pot", "rope", BlockInit.HANGING_POT, BlockInit.HANGING_POT);
		progression(consumer, "light", "root", BlockInit.LAMP_LIGHT_BULB, BlockInit.LAMP_JAR, BlockInit.LAMP_BAMBOO, BlockInit.LAMP_LIGHT_BULB);
		progression(consumer, "rope", "root", BlockInit.ROPE, BlockInit.ROPE);
	}

	private static Advancement parent(String id) {
		return new Advancement(new Identifier(id), null, null, AdvancementRewards.EMPTY, Map.of(), new String[0][], false);
	}

	private static void recipeUnlock(Consumer<Advancement> consumer, String name, ItemLike item, List<Block> recipes) {
		Advancement.Builder builder = Advancement.Builder.recipeAdvancement()
				.parent(parent("minecraft:recipes/root"))
				.addCriterion("requirement", InventoryChangeTrigger.TriggerInstance.hasItems(item));
		AdvancementRewards.Builder rewards = new AdvancementRewards.Builder();
		for (Block recipe : recipes) {
			rewards.addRecipe(Beautify.id(recipePath(recipe)));
		}
		consumer.accept(builder.rewards(rewards).build(new Identifier("beautify:" + name)));
	}

	private static void progressionRoot(Consumer<Advancement> consumer) {
		consumer.accept(Advancement.Builder.advancement()
				.display(new ItemStack(Items.ROSE_BUSH),
						Component.translatable("advancements.beautify.root.title"),
						Component.translatable("advancements.beautify.root.description"),
						new Identifier("minecraft:textures/block/spruce_planks.png"),
						FrameType.TASK, false, false, false)
				.addCriterion("Install", PlayerTrigger.TriggerInstance.tick())
				.build(new Identifier("beautify:progression/root")));
	}

	private static void progression(Consumer<Advancement> consumer, String name, String parentId, ItemLike icon, ItemLike... items) {
		ItemLike[] itemLikes = new ItemLike[items.length];
		for (int i = 0; i < items.length; i++) itemLikes[i] = items[i];
		ItemPredicate.Builder predicate = ItemPredicate.Builder.item();
		predicate.of(itemLikes);
		consumer.accept(Advancement.Builder.advancement()
				.parent(parent("beautify:progression/" + parentId))
				.display(new ItemStack(icon.asItem()),
						Component.translatable("advancements.beautify." + name + ".title"),
						Component.translatable("advancements.beautify." + name + ".description"),
						null, FrameType.TASK, true, true, false)
				.addCriterion("obtain", InventoryChangeTrigger.TriggerInstance.hasItems(predicate.build()))
				.build(new Identifier("beautify:progression/" + name)));
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
		return BuiltInRegistries.ITEM.get(new Identifier(id));
	}
}
*///?}
