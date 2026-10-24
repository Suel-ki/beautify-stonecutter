//? if fabric && >=1.21.1 {
package io.github.suel_ki.beautify.platform.fabric.datagen;

//? if >=26.3 {
import net.minecraft.world.level.storage.loot.providers.number.ints.ConstantValue;
//?} else {
/*import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
*///?}
import io.github.suel_ki.beautify.Beautify;
import io.github.suel_ki.beautify.common.block.HangingPot;
import io.github.suel_ki.beautify.core.init.BlockInit;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
//? if >=26.1.2 {
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
//?} else {
/*import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
*///?}
//? if >=26.2 {
import net.minecraft.advancements.predicates.StatePropertiesPredicate;
//?} else if >=1.21.11 {
/*import net.minecraft.advancements.criterion.StatePropertiesPredicate;
*///?} else {
/*import net.minecraft.advancements.critereon.StatePropertiesPredicate;
*///?}
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
//? if >=26.3 {
import net.minecraft.core.Holder;
import net.minecraft.world.level.storage.loot.predicates.MatchBlock;
//?} else {
/*import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
*///?}

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

//? if >=26.1.2 {
public class BeautifyBlockLootProvider extends FabricBlockLootSubProvider {
//?} else {
/*public class BeautifyBlockLootProvider extends FabricBlockLootTableProvider {
*///?}

	private final HolderGetter<Block> blockGetter;
	private final Map<String, LootTable> leaves = new HashMap<>();

	public BeautifyBlockLootProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
		super(output, registries);
		this.blockGetter = registries.join().lookupOrThrow(Registries.BLOCK);
	}

	@Override
	public void generate() {
		drops(BlockInit.ACACIA_BLINDS, BlockInit.ACACIA_PICTURE_FRAME, BlockInit.BIRCH_BLINDS,
				BlockInit.BIRCH_PICTURE_FRAME, BlockInit.CHERRY_BLINDS, BlockInit.CHERRY_PICTURE_FRAME,
				BlockInit.CRIMSON_BLINDS, BlockInit.CRIMSON_PICTURE_FRAME, BlockInit.DARK_OAK_BLINDS,
				BlockInit.DARK_OAK_PICTURE_FRAME, BlockInit.JUNGLE_BLINDS, BlockInit.JUNGLE_PICTURE_FRAME,
				BlockInit.MANGROVE_BLINDS, BlockInit.MANGROVE_PICTURE_FRAME, BlockInit.OAK_BLINDS,
				BlockInit.OAK_PICTURE_FRAME, BlockInit.SPRUCE_BLINDS, BlockInit.SPRUCE_PICTURE_FRAME,
				BlockInit.WARPED_BLINDS, BlockInit.WARPED_PICTURE_FRAME, BlockInit.BAMBOO_BLINDS,
				BlockInit.BAMBOO_PICTURE_FRAME, BlockInit.IRON_BLINDS, BlockInit.QUARTZ_PICTURE_FRAME);
		//? if >=1.21.11 {
		drops(BlockInit.PALE_OAK_BLINDS, BlockInit.PALE_OAK_PICTURE_FRAME);
		//?}
		drops(BlockInit.BOOKSTACK, BlockInit.BOTANIST_WORKBENCH, BlockInit.ROPE, BlockInit.LAMP_BAMBOO,
				BlockInit.LAMP_LIGHT_BULB);
		drops(BlockInit.LAMP_CANDELABRA, BlockInit.LAMP_CANDELABRA_BLACK, BlockInit.LAMP_CANDELABRA_BLUE,
				BlockInit.LAMP_CANDELABRA_BROWN, BlockInit.LAMP_CANDELABRA_CYAN, BlockInit.LAMP_CANDELABRA_GRAY,
				BlockInit.LAMP_CANDELABRA_GREEN, BlockInit.LAMP_CANDELABRA_LIGHT_BLUE,
				BlockInit.LAMP_CANDELABRA_LIGHT_GRAY, BlockInit.LAMP_CANDELABRA_LIME,
				BlockInit.LAMP_CANDELABRA_MAGENTA, BlockInit.LAMP_CANDELABRA_ORANGE,
				BlockInit.LAMP_CANDELABRA_PINK, BlockInit.LAMP_CANDELABRA_PURPLE, BlockInit.LAMP_CANDELABRA_RED,
				BlockInit.LAMP_CANDELABRA_WHITE, BlockInit.LAMP_CANDELABRA_YELLOW);
		trellisLoot(BlockInit.ACACIA_TRELLIS);
		for (ItemLike flower : HangingPot.VALID_FLOWERS) {
			if (flower != Items.AIR) {
				leaves.put(potKey(flower), potTable(flower));
			}
		}
		trellisLoot(BlockInit.BIRCH_TRELLIS);
		trellisLoot(BlockInit.CHERRY_TRELLIS);
		trellisLoot(BlockInit.CRIMSON_TRELLIS);
		trellisLoot(BlockInit.DARK_OAK_TRELLIS);
		trellisLoot(BlockInit.JUNGLE_TRELLIS);
		for (int i = 0; i < LAMP_JAR_LEVELS.length; i++) {
			leaves.put("lamp_jar" + (i + 1), LootTable.lootTable()
					.withPool(pool(BlockInit.LAMP_JAR))
					.withPool(pool(Items.GLOWSTONE_DUST)).build());
		}
		trellisLoot(BlockInit.MANGROVE_TRELLIS);
		trellisLoot(BlockInit.OAK_TRELLIS);
		trellisLoot(BlockInit.SPRUCE_TRELLIS);
		trellisLoot(BlockInit.WARPED_TRELLIS);
		trellisLoot(BlockInit.BAMBOO_TRELLIS);
		//? if >=1.21.11 {
			trellisLoot(BlockInit.PALE_OAK_TRELLIS);
		//?}
		add(BlockInit.HANGING_POT, hangingPot());
		add(BlockInit.LAMP_JAR, lampJar());
	}

	private static final ItemLike[] TRELLIS_FLOWERS = {
			Items.ROSE_BUSH, Items.SUNFLOWER, Items.PEONY, Items.LILAC,
			Items.VINE, Items.WEEPING_VINES, Items.TWISTING_VINES, Items.GLOW_LICHEN
	};

	private void trellisLoot(Block block) {
		for (ItemLike flower : TRELLIS_FLOWERS) {
			leaves.put(leafKey(block, flower), LootTable.lootTable()
					.withPool(pool(flower))
					.withPool(pool(block)).build());
		}
		add(block, trellis(block, TRELLIS_FLOWERS));
	}

	private LootTable potTable(ItemLike flower) {
		return LootTable.lootTable()
				.withPool(pool(BlockInit.HANGING_POT))
				.withPool(pool(flower))
				.withPool(pool(flower).when(match(BlockInit.HANGING_POT, propsBool(BlockInit.HANGING_POT, "grown", true)))).build();
	}

	private static final int[] LAMP_JAR_LEVELS = {5, 10, 15};

	private void drops(Block... blocks) {
		for (Block block : blocks) {
			dropSelf(block);
		}
	}

	private LootTable.Builder trellis(Block block, ItemLike... flowers) {
		LootPool.Builder pool = rolls();
		pool.add(LootItem.lootTableItem(block).when(match(block, propsInt(block, "flowers", 0))));
		for (int i = 0; i < flowers.length; i++) {
			pool.add(NestedLootTable.inlineLootTable(leaves.get(leafKey(block, flowers[i])))
					.when(match(block, propsInt(block, "flowers", i + 1))));
		}
		return LootTable.lootTable().withPool(pool);
	}

	private LootTable.Builder hangingPot() {
		Block block = BlockInit.HANGING_POT;
		LootPool.Builder pool = rolls();
		pool.add(LootItem.lootTableItem(block).when(match(block, propsInt(block, "potflower", 0))));
		for (int i = 1; i < HangingPot.VALID_FLOWERS.size(); i++) {
			pool.add(NestedLootTable.inlineLootTable(leaves.get(potKey(HangingPot.VALID_FLOWERS.get(i))))
					.when(match(block, propsInt(block, "potflower", i))));
		}
		return LootTable.lootTable().withPool(pool);
	}

	private LootTable.Builder lampJar() {
		Block block = BlockInit.LAMP_JAR;
		LootPool.Builder pool = rolls();
		pool.add(LootItem.lootTableItem(block).when(match(block, propsInt(block, "fill_level", 0))));
		for (int i = 0; i < LAMP_JAR_LEVELS.length; i++) {
			pool.add(NestedLootTable.inlineLootTable(leaves.get("lamp_jar" + (i + 1)))
					.when(match(block, propsInt(block, "fill_level", LAMP_JAR_LEVELS[i]))));
		}
		return LootTable.lootTable().withPool(pool);
	}

	private LootItemCondition.Builder match(Block block, StatePropertiesPredicate.Builder props) {
		//? if >=26.3
		return MatchBlock.blockMatches(blockGetter, block, props);
		//? if <26.3
		//return LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(props);
	}

	private static String leafKey(Block block, ItemLike flower) {
		String wood = BuiltInRegistries.BLOCK.getKey(block).getPath().replace("_trellis", "");
		return wood + "_" + flowerSuffix(flower) + "_trellis";
	}

	private static String flowerSuffix(ItemLike flower) {
		String path = BuiltInRegistries.ITEM.getKey(flower.asItem()).getPath();
		if (path.equals("rose_bush")) return "rose";
		if (path.equals("glow_lichen")) return "lichen";
		return path;
	}

	private static String potKey(ItemLike flower) {
		return flowerSuffix(flower) + "_pot";
	}

	private static LootPool.Builder rolls() {
		//? if >=26.3
		return LootPool.lootPool().setRolls(Holder.direct(new ConstantValue(1)));
		//? if <26.3
		//return LootPool.lootPool().setRolls(ConstantValue.exactly(1));
	}

	private static LootPool.Builder pool(ItemLike item) {
		return rolls().add(LootItem.lootTableItem(item));
	}

	@SuppressWarnings("unchecked")
	private static StatePropertiesPredicate.Builder propsInt(Block block, String name, int value) {
		Property<Integer> property = (Property<Integer>) block.getStateDefinition().getProperties().stream().filter(p -> p.getName().equals(name)).findFirst().orElseThrow();
		return StatePropertiesPredicate.Builder.properties().hasProperty(property, value);
	}

	@SuppressWarnings("unchecked")
	private static StatePropertiesPredicate.Builder propsBool(Block block, String name, boolean value) {
		Property<Boolean> property = (Property<Boolean>) block.getStateDefinition().getProperties().stream().filter(p -> p.getName().equals(name)).findFirst().orElseThrow();
		return StatePropertiesPredicate.Builder.properties().hasProperty(property, value);
	}

	private static Block block(String id) {
		//? if >=1.21.2
		return BuiltInRegistries.BLOCK.getValue(Identifier.parse("beautify:" + id));
		//? if <1.21.2
		//return BuiltInRegistries.BLOCK.get(Identifier.fromNamespaceAndPath("beautify", id));
	}

	private static ItemLike grassItem() {
		//? if >=1.21.1
		return Items.SHORT_GRASS;
		//? if <1.21.1
		//return Items.GRASS;
	}

	private static Item item(String id) {
		//? if >=1.21.2
		return BuiltInRegistries.ITEM.getValue(Identifier.parse(id));
		//? if <1.21.2
		//return BuiltInRegistries.ITEM.get(Identifier.parse(id));
	}
}
//?}
