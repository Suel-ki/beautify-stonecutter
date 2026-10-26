//? if fabric {
package io.github.suel_ki.beautify.platform.fabric.datagen;

import io.github.suel_ki.beautify.core.init.BlockInit;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
//? if >=26.1.2 {
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
//?} else {
//import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
//?}
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

//? if >=26.1.2 {
public class BeautifyBlockTagProvider extends FabricTagsProvider.BlockTagsProvider {
//?} else {
//public class BeautifyBlockTagProvider extends FabricTagProvider.BlockTagProvider {
//?}

	public BeautifyBlockTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
		super(output, registries);
	}

	private static ResourceKey<Block> key(Block block) {
		return block.builtInRegistryHolder().key();
	}


	@Override
	@SuppressWarnings("unchecked")
	protected void addTags(HolderLookup.Provider provider) {
		//? if >=1.21.8 {
		var axe = builder(BlockTags.MINEABLE_WITH_AXE);
		var pickaxe = builder(BlockTags.MINEABLE_WITH_PICKAXE);
		var stone = builder(BlockTags.NEEDS_STONE_TOOL);
		var climbable = builder(BlockTags.CLIMBABLE);
		var enchantProvider = builder(BlockTags.ENCHANTMENT_POWER_PROVIDER);
		var enchantTransmitter = builder(BlockTags.ENCHANTMENT_POWER_TRANSMITTER);
		//?} else {
		/*var axe = getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_AXE);
		var pickaxe = getOrCreateTagBuilder(BlockTags.MINEABLE_WITH_PICKAXE);
		var stone = getOrCreateTagBuilder(BlockTags.NEEDS_STONE_TOOL);
		var climbable = getOrCreateTagBuilder(BlockTags.CLIMBABLE);
		var enchantProvider = getOrCreateTagBuilder(BlockTags.ENCHANTMENT_POWER_PROVIDER);
		var enchantTransmitter = getOrCreateTagBuilder(BlockTags.ENCHANTMENT_POWER_TRANSMITTER);
		*///?}
		axe.add(key(BlockInit.OAK_BLINDS), key(BlockInit.BIRCH_BLINDS), key(BlockInit.SPRUCE_BLINDS), key(BlockInit.DARK_OAK_BLINDS),
				key(BlockInit.WARPED_BLINDS), key(BlockInit.CRIMSON_BLINDS), key(BlockInit.CHERRY_BLINDS), key(BlockInit.ACACIA_BLINDS),
				key(BlockInit.JUNGLE_BLINDS), key(BlockInit.MANGROVE_BLINDS), key(BlockInit.BAMBOO_BLINDS));
		//? if >=1.21.11 {
		axe.add(key(BlockInit.PALE_OAK_BLINDS));
		//?}
		axe.add(key(BlockInit.OAK_TRELLIS), key(BlockInit.SPRUCE_TRELLIS), key(BlockInit.BIRCH_TRELLIS), key(BlockInit.JUNGLE_TRELLIS),
				key(BlockInit.ACACIA_TRELLIS), key(BlockInit.DARK_OAK_TRELLIS), key(BlockInit.MANGROVE_TRELLIS),
				key(BlockInit.CRIMSON_TRELLIS), key(BlockInit.CHERRY_TRELLIS), key(BlockInit.WARPED_TRELLIS),
				key(BlockInit.BAMBOO_TRELLIS));
		//? if >=1.21.11 {
		axe.add(key(BlockInit.PALE_OAK_TRELLIS));
		//?}

		pickaxe.add(key(BlockInit.IRON_BLINDS), key(BlockInit.LAMP_CANDELABRA), key(BlockInit.LAMP_CANDELABRA_LIGHT_BLUE),
				key(BlockInit.LAMP_CANDELABRA_LIGHT_GRAY), key(BlockInit.LAMP_CANDELABRA_BLACK), key(BlockInit.LAMP_CANDELABRA_BLUE),
				key(BlockInit.LAMP_CANDELABRA_BROWN), key(BlockInit.LAMP_CANDELABRA_CYAN), key(BlockInit.LAMP_CANDELABRA_GRAY),
				key(BlockInit.LAMP_CANDELABRA_GREEN), key(BlockInit.LAMP_CANDELABRA_LIME), key(BlockInit.LAMP_CANDELABRA_MAGENTA),
				key(BlockInit.LAMP_CANDELABRA_ORANGE), key(BlockInit.LAMP_CANDELABRA_PINK), key(BlockInit.LAMP_CANDELABRA_PURPLE),
				key(BlockInit.LAMP_CANDELABRA_RED), key(BlockInit.LAMP_CANDELABRA_WHITE), key(BlockInit.LAMP_CANDELABRA_YELLOW));

		stone.add(key(BlockInit.IRON_BLINDS));

		climbable.add(key(BlockInit.ROPE));
		climbable.add(key(BlockInit.OAK_TRELLIS), key(BlockInit.SPRUCE_TRELLIS), key(BlockInit.BIRCH_TRELLIS), key(BlockInit.JUNGLE_TRELLIS),
				key(BlockInit.ACACIA_TRELLIS), key(BlockInit.DARK_OAK_TRELLIS), key(BlockInit.MANGROVE_TRELLIS),
				key(BlockInit.CRIMSON_TRELLIS), key(BlockInit.CHERRY_TRELLIS), key(BlockInit.WARPED_TRELLIS),
				key(BlockInit.BAMBOO_TRELLIS));
		//? if >=1.21.11 {
		climbable.add(key(BlockInit.PALE_OAK_TRELLIS));
		//?}

		enchantProvider.add(key(BlockInit.BOOKSTACK));
		enchantTransmitter.add(key(BlockInit.BOOKSTACK));
	}
}
//?}
