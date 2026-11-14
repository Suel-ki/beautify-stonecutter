package io.github.suel_ki.beautify.common.block;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.phys.shapes.VoxelShape;

//? if >=1.21.11 {
import io.github.suel_ki.beautify.client.tooltip.TooltipLore;
import io.github.suel_ki.beautify.core.init.ComponentInit;
import net.minecraft.core.component.DataComponentType;
//?}

public class LampBamboo extends AbstractLamp {
	private static final VoxelShape SHAPE_HANGING = Block.box(2, 1, 2, 14, 12, 14);
	private static final VoxelShape SHAPE_STANDING = Block.box(4, 0, 4, 12, 13, 12);

	public LampBamboo(Properties properties) {
		super(properties, SHAPE_HANGING, SHAPE_STANDING);
	}

	//? if >=1.21.11 {
	@Override
	public DataComponentType<TooltipLore> getTooltipType() {
		return ComponentInit.get(ComponentInit.LAMP_BAMBOO_TOOLTIP);
	}
	//?}
}
