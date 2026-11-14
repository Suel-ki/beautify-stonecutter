package io.github.suel_ki.beautify.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.VoxelShape;

//? if fabric {
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
//?}

//? if >=1.21.11 {
import io.github.suel_ki.beautify.client.tooltip.TooltipLore;
import io.github.suel_ki.beautify.core.init.ComponentInit;
import net.minecraft.core.component.DataComponentType;
//?}

public class LampLightBulb extends AbstractLamp {
	private static final VoxelShape SHAPE_HANGING = Block.box(5, 3, 5, 11, 16, 11);
	private static final VoxelShape SHAPE_STANDING = Block.box(4, 0, 4, 12, 13, 12);

	public LampLightBulb(Properties properties) {
		super(properties, SHAPE_HANGING, SHAPE_STANDING);
	}

	//? if fabric
	@Environment(EnvType.CLIENT)
	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rand) {
		double d0 = (double) pos.getX() + 0.5D;
		double d1 = (double) pos.getY() + 0.7D;
		double d2 = (double) pos.getZ() + 0.5D;

		if (rand.nextBoolean() && state.getValue(ON)) {
			if (state.getValue(HANGING)) {
				level.addParticle(ParticleTypes.SMOKE, d0, d1 - 0.3, d2, 0.0D, 0.0D, 0.0D);
			} else {
				level.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
			}
		}
	}

	//? if >=1.21.11 {
	@Override
	public DataComponentType<TooltipLore> getTooltipType() {
		return ComponentInit.get(ComponentInit.LAMP_LIGHTBULB_TOOLTIP);
	}
	//?}
}
