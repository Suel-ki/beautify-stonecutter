//? if fabric && <1.21.1 {
/*package io.github.suel_ki.beautify.platform.fabric.datagen;

//? if >=26.3 {
import net.minecraft.world.level.storage.loot.providers.number.ints.ConstantValue;
//?} else {
//import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
//?}
import io.github.suel_ki.beautify.common.block.HangingPot;
import io.github.suel_ki.beautify.core.init.BlockInit;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.advancements.critereon.StatePropertiesPredicate;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootTableReference;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;

import java.util.function.BiConsumer;

public class BeautifyLegacyLootProvider extends FabricBlockLootTableProvider {

	private BiConsumer<Identifier, LootTable.Builder> out;

	public BeautifyLegacyLootProvider(FabricPackOutput output) {
		super(output);
	}

	@Override
	public void generate(BiConsumer<Identifier, LootTable.Builder> consumer) {
		this.out = consumer;
		super.generate(consumer);
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
				potLoot(flower);
			}
		}
		trellisLoot(BlockInit.BIRCH_TRELLIS);
		trellisLoot(BlockInit.CHERRY_TRELLIS);
		trellisLoot(BlockInit.CRIMSON_TRELLIS);
		trellisLoot(BlockInit.DARK_OAK_TRELLIS);
		trellisLoot(BlockInit.JUNGLE_TRELLIS);
		for (int i = 0; i < LAMP_JAR_LEVELS.length; i++) {
			out.accept(id("blocks/lamp_jar" + (i + 1)), LootTable.lootTable()
					.withPool(pool(BlockInit.LAMP_JAR))
					.withPool(pool(Items.GLOWSTONE_DUST)));
		}
		trellisLoot(BlockInit.MANGROVE_TRELLIS);
		trellisLoot(BlockInit.OAK_TRELLIS);
		trellisLoot(BlockInit.SPRUCE_TRELLIS);
		trellisLoot(BlockInit.WARPED_TRELLIS);
		trellisLoot(BlockInit.BAMBOO_TRELLIS);
		add(BlockInit.HANGING_POT, hangingPot());
		add(BlockInit.LAMP_JAR, lampJar());
	}

	private static final ItemLike[] TRELLIS_FLOWERS = {
			Items.ROSE_BUSH, Items.SUNFLOWER, Items.PEONY, Items.LILAC,
			Items.VINE, Items.WEEPING_VINES, Items.TWISTING_VINES, Items.GLOW_LICHEN
	};

	private void trellisLoot(Block block) {
		for (ItemLike flower : TRELLIS_FLOWERS) {
			out.accept(id("blocks/" + leafKey(block, flower)), LootTable.lootTable()
					.withPool(pool(flower))
					.withPool(pool(block)));
		}
		add(block, trellis(block, TRELLIS_FLOWERS));
	}

	private void potLoot(ItemLike flower) {
		out.accept(id("blocks/" + potKey(flower)), LootTable.lootTable()
				.withPool(pool(BlockInit.HANGING_POT))
				.withPool(pool(flower))
				.withPool(pool(flower).when(match(BlockInit.HANGING_POT, propsBool(BlockInit.HANGING_POT, "grown", true)))));
	}

	private static final int[] LAMP_JAR_LEVELS = {5, 10, 15};

	private void drops(Block... blocks) {
		for (Block block : blocks) {
			dropSelf(block);
		}
	}

	private LootTable.Builder trellis(Block block, ItemLike... flowers) {
		LootPool.Builder pool = LootPool.lootPool().setRolls(ConstantValue.exactly(1))
				.add(LootItem.lootTableItem(block).when(match(block, propsInt(block, "flowers", 0))));
		for (int i = 0; i < flowers.length; i++) {
			pool.add(ref(leafKey(block, flowers[i])).when(match(block, propsInt(block, "flowers", i + 1))));
		}
		return LootTable.lootTable().withPool(pool);
	}

	private LootTable.Builder hangingPot() {
		Block block = BlockInit.HANGING_POT;
		LootPool.Builder pool = LootPool.lootPool().setRolls(ConstantValue.exactly(1))
				.add(LootItem.lootTableItem(block).when(match(block, propsInt(block, "potflower", 0))));
		for (int i = 1; i < HangingPot.VALID_FLOWERS.size(); i++) {
			pool.add(ref(potKey(HangingPot.VALID_FLOWERS.get(i))).when(match(block, propsInt(block, "potflower", i))));
		}
		return LootTable.lootTable().withPool(pool);
	}

	private LootTable.Builder lampJar() {
		Block block = BlockInit.LAMP_JAR;
		LootPool.Builder pool = LootPool.lootPool().setRolls(ConstantValue.exactly(1))
				.add(LootItem.lootTableItem(block).when(match(block, propsInt(block, "fill_level", 0))));
		for (int i = 0; i < LAMP_JAR_LEVELS.length; i++) {
			pool.add(ref("lamp_jar" + (i + 1)).when(match(block, propsInt(block, "fill_level", LAMP_JAR_LEVELS[i]))));
		}
		return LootTable.lootTable().withPool(pool);
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

	private static LootPool.Builder pool(ItemLike item) {
		return LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(item));
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	private static LootTableReference.Builder<?> ref(String id) {
		return LootTableReference.lootTableReference(new Identifier("beautify:blocks/" + id));
	}

	private static LootItemCondition.Builder match(Block block, StatePropertiesPredicate.Builder props) {
		return LootItemBlockStatePropertyCondition.hasBlockStateProperties(block).setProperties(props);
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

	private static Identifier id(String path) {
		return new Identifier("beautify:" + path);
	}

	private static Block block(String id) {
		return BuiltInRegistries.BLOCK.get(new Identifier("beautify:" + id));
	}

	private static ItemLike grassItem() {
		//? if >=1.21.1
		return Items.SHORT_GRASS;
		//? if <1.21.1
		//return Items.GRASS;
	}

	private static Item item(String id) {
		return BuiltInRegistries.ITEM.get(new Identifier(id));
	}
}
*///?}
