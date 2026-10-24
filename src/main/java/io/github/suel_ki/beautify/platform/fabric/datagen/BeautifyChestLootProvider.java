//? if fabric && >=1.21.1 && <26.1.2 {
/*package io.github.suel_ki.beautify.platform.fabric.datagen;

//? if >=26.3 {
import net.minecraft.world.level.storage.loot.providers.number.ints.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.ints.UniformGenerator;
//?} else {
/^import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
^///?}
import io.github.suel_ki.beautify.Beautify;
import io.github.suel_ki.beautify.core.init.BlockInit;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.SimpleFabricLootTableProvider;
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

public class BeautifyChestLootProvider extends SimpleFabricLootTableProvider {

	public BeautifyChestLootProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
		super(output, registries, LootContextParamSets.CHEST);
	}

	@Override
	public void generate(BiConsumer<ResourceKey<LootTable>, LootTable.Builder> consumer) {
		consumer.accept(key("chests/botanist/botanist_chest"), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(Items.IRON_HOE).apply(EnchantRandomlyFunction.randomEnchantment())))
				.withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1, 2)).add(LootItem.lootTableItem(Items.CORNFLOWER).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 7)))).add(LootItem.lootTableItem(Items.DANDELION).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 7)))).add(LootItem.lootTableItem(Items.OAK_SAPLING).setWeight(8).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 8)))).add(LootItem.lootTableItem(Items.BIRCH_SAPLING).setWeight(8).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 8)))))
				.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(BlockInit.HANGING_POT).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))).add(LootItem.lootTableItem(Items.FLOWER_POT).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))).add(LootItem.lootTableItem(BlockInit.OAK_TRELLIS).setWeight(7).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 8)))).add(LootItem.lootTableItem(BlockInit.BIRCH_TRELLIS).setWeight(7).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 8)))))
				.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(Items.BONE_MEAL).apply(SetItemCountFunction.setCount(UniformGenerator.between(5, 25))))));
		consumer.accept(key("chests/botanist/botanist_chest_desert"), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(Items.IRON_HOE).apply(EnchantRandomlyFunction.randomEnchantment())))
				.withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1, 2)).add(LootItem.lootTableItem(Items.DEAD_BUSH).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 7)))).add(LootItem.lootTableItem(Items.CACTUS).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 7)))).add(LootItem.lootTableItem(Items.ACACIA_SAPLING).setWeight(8).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 8)))).add(LootItem.lootTableItem(Items.SUGAR_CANE).setWeight(8).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 8)))))
				.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(BlockInit.HANGING_POT).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))).add(LootItem.lootTableItem(Items.FLOWER_POT).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))).add(LootItem.lootTableItem(BlockInit.JUNGLE_TRELLIS).setWeight(7).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 8)))))
				.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(Items.BONE_MEAL).apply(SetItemCountFunction.setCount(UniformGenerator.between(5, 25))))));
		consumer.accept(key("chests/botanist/botanist_chest_savanna"), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(Items.IRON_HOE).apply(EnchantRandomlyFunction.randomEnchantment())))
				.withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1, 2)).add(LootItem.lootTableItem(Items.POPPY).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 7)))).add(LootItem.lootTableItem(Items.DANDELION).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 7)))).add(LootItem.lootTableItem(Items.ACACIA_SAPLING).setWeight(8).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 8)))).add(LootItem.lootTableItem(Items.SUGAR_CANE).setWeight(8).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 8)))))
				.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(BlockInit.HANGING_POT).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))).add(LootItem.lootTableItem(Items.FLOWER_POT).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))).add(LootItem.lootTableItem(BlockInit.ACACIA_TRELLIS).setWeight(7).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 8)))))
				.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(Items.BONE_MEAL).apply(SetItemCountFunction.setCount(UniformGenerator.between(5, 25))))));
		consumer.accept(key("chests/botanist/botanist_chest_snowy"), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(Items.IRON_HOE).apply(EnchantRandomlyFunction.randomEnchantment())))
				.withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1, 2)).add(LootItem.lootTableItem(Items.LILY_OF_THE_VALLEY).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 7)))).add(LootItem.lootTableItem(Items.POPPY).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 7)))).add(LootItem.lootTableItem(Items.SPRUCE_SAPLING).setWeight(8).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 8)))))
				.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(BlockInit.HANGING_POT).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))).add(LootItem.lootTableItem(Items.FLOWER_POT).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))).add(LootItem.lootTableItem(BlockInit.SPRUCE_TRELLIS).setWeight(7).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 8)))))
				.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(Items.BONE_MEAL).apply(SetItemCountFunction.setCount(UniformGenerator.between(5, 25))))));
		consumer.accept(key("chests/botanist/botanist_chest_taiga"), LootTable.lootTable()
				.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(Items.IRON_HOE).apply(EnchantRandomlyFunction.randomEnchantment())))
				.withPool(LootPool.lootPool().setRolls(UniformGenerator.between(1, 2)).add(LootItem.lootTableItem(Items.SWEET_BERRIES).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 7)))).add(LootItem.lootTableItem(Items.POPPY).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 7)))).add(LootItem.lootTableItem(Items.SPRUCE_SAPLING).setWeight(8).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 8)))))
				.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(BlockInit.HANGING_POT).setWeight(15).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))).add(LootItem.lootTableItem(Items.FLOWER_POT).setWeight(10).apply(SetItemCountFunction.setCount(UniformGenerator.between(1, 3)))).add(LootItem.lootTableItem(BlockInit.SPRUCE_TRELLIS).setWeight(7).apply(SetItemCountFunction.setCount(UniformGenerator.between(2, 8)))))
				.withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1)).add(LootItem.lootTableItem(Items.BONE_MEAL).apply(SetItemCountFunction.setCount(UniformGenerator.between(5, 25))))));
	}

	private static ResourceKey<LootTable> key(String path) {
		return ResourceKey.create(Registries.LOOT_TABLE, Beautify.id(path));
	}

	private static Item item(String id) {
		//? if >=1.21.11
		return BuiltInRegistries.ITEM.getValue(Identifier.parse(id));
		//? if <1.21.11
		//return BuiltInRegistries.ITEM.get(Identifier.parse(id));
	}
}
*///?}
