package io.github.suel_ki.beautify.compat.jade.providers;

import io.github.suel_ki.beautify.Beautify;
import io.github.suel_ki.beautify.common.block.MultiFlowerPotBlock;
import io.github.suel_ki.beautify.common.block.entity.MultiFlowerPotBlockEntity;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;
import snownee.jade.api.ui.IDisplayHelper;
//? if >=1.21.11 {
import snownee.jade.api.ui.JadeUI;
//?} else {
//import snownee.jade.api.ui.IElementHelper;
//?}

public enum MultiFlowerPotProvider implements IBlockComponentProvider {

	INSTANCE;

	@Override
	public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
		if (!(accessor.getBlockEntity() instanceof MultiFlowerPotBlockEntity pot)) {
			return;
		}
		int pots = accessor.getBlockState().getValue(MultiFlowerPotBlock.POTS);
		for (int slot = 0; slot < pots; slot++) {
			ItemStack plant = pot.getPlant(slot);
			if (plant.isEmpty()) {
				continue;
			}
			//? if >=1.21.11 {
			tooltip.add(JadeUI.smallItem(plant));
			//?} else {
			//tooltip.add(IElementHelper.get().smallItem(plant));
			//?}
			tooltip.append(IDisplayHelper.get().stripColor(plant.getHoverName()));
		}
	}

	@Override
	public Identifier getUid() {
		return Beautify.id("multi_flower_pot.plants");
	}
}
