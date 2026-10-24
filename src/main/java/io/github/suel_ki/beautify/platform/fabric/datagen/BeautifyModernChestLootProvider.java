//? if fabric && >=26.1.2 {
package io.github.suel_ki.beautify.platform.fabric.datagen;

//? if >=26.3 {
import net.minecraft.world.level.storage.loot.providers.number.ints.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.ints.UniformGenerator;
//?} else {
/*import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
*///?}
import io.github.suel_ki.beautify.core.init.BlockInit;

import io.github.suel_ki.beautify.Beautify;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableSubProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.EnchantRandomlyFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;

import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class BeautifyModernChestLootProvider extends SimpleFabricLootTableSubProvider {

	public BeautifyModernChestLootProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
		super(output, registries, LootContextParamSets.CHEST);
	}

//? if >=26.3 {
	@Override
	public void run() {
	}
//?}

	@Override
	public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {
		consumer.accept(key("chests/botanist/botanist_chest"), LootTable.lootTable()
				.withPool(chestAdd(chestPool(1), Items.IRON_HOE, 0, 0, 0, true))
				.withPool(chestAdd(chestAdd(chestAdd(chestAdd(chestPool(1, 2), Items.CORNFLOWER, 15, 2, 7, false), Items.DANDELION, 15, 2, 7, false), Items.OAK_SAPLING, 8, 2, 8, false), Items.BIRCH_SAPLING, 8, 2, 8, false))
				.withPool(chestAdd(chestAdd(chestAdd(chestAdd(chestPool(1), BlockInit.HANGING_POT, 15, 1, 3, false), Items.FLOWER_POT, 10, 1, 3, false), BlockInit.OAK_TRELLIS, 7, 2, 8, false), BlockInit.BIRCH_TRELLIS, 7, 2, 8, false))
				.withPool(chestAdd(chestPool(1), Items.BONE_MEAL, 0, 5, 25, false)));
		consumer.accept(key("chests/botanist/botanist_chest_desert"), LootTable.lootTable()
				.withPool(chestAdd(chestPool(1), Items.IRON_HOE, 0, 0, 0, true))
				.withPool(chestAdd(chestAdd(chestAdd(chestAdd(chestPool(1, 2), Items.DEAD_BUSH, 15, 2, 7, false), Items.CACTUS, 15, 2, 7, false), Items.ACACIA_SAPLING, 8, 2, 8, false), Items.SUGAR_CANE, 8, 2, 8, false))
				.withPool(chestAdd(chestAdd(chestAdd(chestPool(1), BlockInit.HANGING_POT, 15, 1, 3, false), Items.FLOWER_POT, 10, 1, 3, false), BlockInit.JUNGLE_TRELLIS, 7, 2, 8, false))
				.withPool(chestAdd(chestPool(1), Items.BONE_MEAL, 0, 5, 25, false)));
		consumer.accept(key("chests/botanist/botanist_chest_savanna"), LootTable.lootTable()
				.withPool(chestAdd(chestPool(1), Items.IRON_HOE, 0, 0, 0, true))
				.withPool(chestAdd(chestAdd(chestAdd(chestAdd(chestPool(1, 2), Items.POPPY, 15, 2, 7, false), Items.DANDELION, 15, 2, 7, false), Items.ACACIA_SAPLING, 8, 2, 8, false), Items.SUGAR_CANE, 8, 2, 8, false))
				.withPool(chestAdd(chestAdd(chestAdd(chestPool(1), BlockInit.HANGING_POT, 15, 1, 3, false), Items.FLOWER_POT, 10, 1, 3, false), BlockInit.ACACIA_TRELLIS, 7, 2, 8, false))
				.withPool(chestAdd(chestPool(1), Items.BONE_MEAL, 0, 5, 25, false)));
		consumer.accept(key("chests/botanist/botanist_chest_snowy"), LootTable.lootTable()
				.withPool(chestAdd(chestPool(1), Items.IRON_HOE, 0, 0, 0, true))
				.withPool(chestAdd(chestAdd(chestAdd(chestPool(1, 2), Items.LILY_OF_THE_VALLEY, 15, 2, 7, false), Items.POPPY, 15, 2, 7, false), Items.SPRUCE_SAPLING, 8, 2, 8, false))
				.withPool(chestAdd(chestAdd(chestAdd(chestPool(1), BlockInit.HANGING_POT, 15, 1, 3, false), Items.FLOWER_POT, 10, 1, 3, false), BlockInit.SPRUCE_TRELLIS, 7, 2, 8, false))
				.withPool(chestAdd(chestPool(1), Items.BONE_MEAL, 0, 5, 25, false)));
		consumer.accept(key("chests/botanist/botanist_chest_taiga"), LootTable.lootTable()
				.withPool(chestAdd(chestPool(1), Items.IRON_HOE, 0, 0, 0, true))
				.withPool(chestAdd(chestAdd(chestAdd(chestPool(1, 2), Items.SWEET_BERRIES, 15, 2, 7, false), Items.POPPY, 15, 2, 7, false), Items.SPRUCE_SAPLING, 8, 2, 8, false))
				.withPool(chestAdd(chestAdd(chestAdd(chestPool(1), BlockInit.HANGING_POT, 15, 1, 3, false), Items.FLOWER_POT, 10, 1, 3, false), BlockInit.SPRUCE_TRELLIS, 7, 2, 8, false))
				.withPool(chestAdd(chestPool(1), Items.BONE_MEAL, 0, 5, 25, false)));
	}

	private static LootPool.Builder chestPool(int rolls) {
		//? if >=26.3 {
		return LootPool.lootPool().setRolls(Holder.direct(new ConstantValue(rolls)));
		//?} else {
		/*return LootPool.lootPool().setRolls(ConstantValue.exactly(rolls));
		*///?}
	}

	private static LootPool.Builder chestPool(int min, int max) {
		//? if >=26.3 {
		return LootPool.lootPool().setRolls(Holder.direct(new UniformGenerator(
				Holder.direct(new ConstantValue(min)),
				Holder.direct(new ConstantValue(max)))));
		//?} else {
		/*return LootPool.lootPool().setRolls(UniformGenerator.between(min, max));
		*///?}
	}

	private static LootPool.Builder chestAdd(LootPool.Builder pool, ItemLike item, int weight, int countMin, int countMax, boolean enchant) {
		var entry = LootItem.lootTableItem(item);
		if (weight > 0) entry.setWeight(weight);
		if (countMax > 0) {
			//? if >=26.3 {
			entry.apply(SetItemCountFunction.setCount(Holder.direct(new UniformGenerator(
					Holder.direct(new ConstantValue(countMin)),
					Holder.direct(new ConstantValue(countMax))))));
			//?} else {
			/*entry.apply(SetItemCountFunction.setCount(UniformGenerator.between(countMin, countMax)));
			*///?}
		}
		if (enchant) {
			entry.apply(EnchantRandomlyFunction.randomEnchantment());
		}
		return pool.add(entry);
	}

	private static ResourceKey<LootTable> key(String path) {
		return ResourceKey.create(Registries.LOOT_TABLE, Beautify.id(path));
	}

	private static Item item(String id) {
		return BuiltInRegistries.ITEM.getValue(Identifier.parse(id));
	}
}
//?}
