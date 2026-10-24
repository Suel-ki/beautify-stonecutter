package io.github.suel_ki.beautify.common.block;

import io.github.suel_ki.beautify.client.tooltip.LegacyTooltips;


import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
//? if >=1.21.11
import net.minecraft.world.level.redstone.Orientation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;

//? if fabric {
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
//?}

//? if >=1.21.11 {
import io.github.suel_ki.beautify.client.tooltip.TooltipLore;
import io.github.suel_ki.beautify.common.tooltip.BlockTooltip;
import io.github.suel_ki.beautify.core.init.ComponentInit;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
//?}

//? if <1.21.11 {
/*import net.minecraft.ChatFormatting;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
*///?}

//? if >=1.21.11 {
public class LampLightBulb extends LanternBlock implements BlockTooltip {
//?} else {
/*public class LampLightBulb extends LanternBlock {
*///?}
	public static final BooleanProperty ON = BooleanProperty.create("on");
	public static final BooleanProperty POWERED = BooleanProperty.create("powered");

	private static final VoxelShape SHAPE_HANGING = Block.box(5, 3, 5, 11, 16, 11);
	private static final VoxelShape SHAPE_STANDING = Block.box(4, 0, 4, 12, 13, 12);

	public LampLightBulb(Properties properties) {
		super(properties);
		this.registerDefaultState(this.defaultBlockState().setValue(ON, true).setValue(POWERED, false));
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return state.getValue(HANGING) ? SHAPE_HANGING : SHAPE_STANDING;
	}

	//? if >=1.21.1 {
	@Override
	public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player,
											BlockHitResult result) {
	//?} else {
	/*@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand,
			BlockHitResult result) {
	*///?}
		//? if >=1.21.1
		if (!level.isClientSide()) {
		//? if <1.21.1
		//if (!level.isClientSide() && hand == InteractionHand.MAIN_HAND && player.getItemInHand(hand).isEmpty()) {
			level.setBlock(pos, state.setValue(ON, !state.getValue(ON)), 3);
			float f = state.getValue(ON) ? 0.5F : 0.6F;
			level.playSound(null, pos, SoundEvents.LEVER_CLICK, SoundSource.BLOCKS, 0.25F, f);
		}

		return InteractionResult.SUCCESS;
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

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(ON, POWERED);
	}

	//? if >=1.21.11 {
	@Override
	public void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, Orientation orientation, boolean movedByPiston) {
		updateRedstone(state, level, pos);
	}
	//?} else {
	/*@Override
	public void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos, boolean movedByPiston) {
		updateRedstone(state, level, pos);
	}
	*///?}

	private void updateRedstone(BlockState state, Level level, BlockPos pos) {
		if (level.isClientSide()) {
			return;
		}
		boolean powered = level.hasNeighborSignal(pos);
		if (powered != state.getValue(POWERED)) {
			level.setBlock(pos, state.setValue(POWERED, powered).setValue(ON, powered), 3);
		}
	}

    //? if >=1.21.11 {
    @Override
    public DataComponentType<TooltipLore> getTooltipType() {
        return ComponentInit.get(ComponentInit.LAMP_LIGHTBULB_TOOLTIP);
    }

    @Override
    public TooltipLore getTooltipComponent() {
        return TooltipComponent.INSTANCE;
    }

    public static final class TooltipComponent {
        public static final TooltipLore INSTANCE = TooltipLore.of("tooltip.beautify.lamp.1", "tooltip.beautify.lamp.2");
    }

//?} else {
	/*//? if >=1.21.1 {
	@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext tooltipContext, List<Component> component, TooltipFlag flag) {
	//?} else {
	/^@Override
	public void appendHoverText(ItemStack stack, BlockGetter getter, List<Component> component, TooltipFlag flag) {
	^///?}
		LegacyTooltips.shiftOnly(component, "tooltip.beautify.lamp.1", "tooltip.beautify.lamp.2");
		//? if >=1.21.1
		super.appendHoverText(stack, tooltipContext, component, flag);
		//? if <1.21.1
		//super.appendHoverText(stack, getter, component, flag);
	}
*///?}
}
