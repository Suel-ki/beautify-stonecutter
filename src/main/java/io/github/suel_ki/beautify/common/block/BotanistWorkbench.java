package io.github.suel_ki.beautify.common.block;

import io.github.suel_ki.beautify.client.tooltip.LegacyTooltips;


import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.Codec;
//? if <26.3 {
/*import com.mojang.serialization.MapCodec;
*///?}
//? if <1.21.11 {
/*import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
*///?}
//? if >=1.21.11 {
import io.github.suel_ki.beautify.client.tooltip.TooltipLore;
//?}
//? if >=1.21.11 {
import io.github.suel_ki.beautify.common.tooltip.BlockTooltip;
//?}
//? if >=1.21.11 {
import io.github.suel_ki.beautify.core.init.ComponentInit;
//?}
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
//? if >=1.21.11 {
import net.minecraft.core.component.DataComponentType;
//?}
//? if >=1.21.11 {
import net.minecraft.network.RegistryFriendlyByteBuf;
//?}
import net.minecraft.network.chat.Component;
//? if >=1.21.11 {
import net.minecraft.network.codec.StreamCodec;
//?}
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
//? if >=1.21.11 {
import net.minecraft.world.level.ScheduledTickAccess;
//?}
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;
import java.util.Map;

//? if <1.21.11 {
/*public class BotanistWorkbench extends HorizontalDirectionalBlock implements SimpleWaterloggedBlock {
*///?} else {
public class BotanistWorkbench extends HorizontalDirectionalBlock implements BlockTooltip, SimpleWaterloggedBlock {
//?}
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

	//Map of hitboxes for direction the model can be facing
	private static final Map<Direction, VoxelShape> SHAPES_FOR_MODEL = ImmutableMap.of(
			Direction.NORTH, Shapes.or(box(2, 0, 0, 16, 12, 14.25),
					box(9.5, 12, 8.5, 13.5, 16, 12.5)),
			Direction.SOUTH, Shapes.or(box(0, 0, 1.75, 14, 12, 16),
					box(2.5, 12, 3.5, 6.5, 16, 7.5)),
			Direction.WEST, Shapes.or(box(0, 0, 0, 14.25, 12, 14),
					box(8.5, 12, 2.5, 12.5, 16, 6.5)),
			Direction.EAST, Shapes.or(box(1.75, 0, 2, 16, 12, 16),
					box(3.5, 12, 9.5, 7.5, 16, 13.5))
	);

	//? if >=1.21.1 && <26.3 {
	/*public static final MapCodec<BotanistWorkbench> CODEC = simpleCodec(BotanistWorkbench::new);

	@Override
	protected MapCodec<BotanistWorkbench> codec() {
		return CODEC;
	}
*///?}
	public BotanistWorkbench(Properties properties) {
		super(properties);
		this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH).setValue(WATERLOGGED, false));
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite())
				.setValue(WATERLOGGED, context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext context) {
		return SHAPES_FOR_MODEL.get(state.getValue(FACING));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
		super.createBlockStateDefinition(pBuilder);
		pBuilder.add(FACING, WATERLOGGED);
	}

	@Override
	public FluidState getFluidState(BlockState state) {
		return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
	}

	//? if >=1.21.11 {
	@Override
	public BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess scheduledTickAccess,
			BlockPos pos, Direction direction, BlockPos neighborPos, BlockState neighborState, RandomSource random) {
		if (state.getValue(WATERLOGGED)) {
			scheduledTickAccess.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
		}
		return super.updateShape(state, level, scheduledTickAccess, pos, direction, neighborPos, neighborState, random);
	}
	//?} else {
	/*@Override
	public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState, LevelAccessor level,
			BlockPos pos, BlockPos neighborPos) {
		if (state.getValue(WATERLOGGED)) {
			level.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
		}
		return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
	}
	*///?}

    @Override
	//? if <1.21.11 {
	/*//? if >=1.21.1 {
	public void appendHoverText(ItemStack stack, Item.TooltipContext ctx, List<Component> component, TooltipFlag flag) {
	//?} else {
	/^public void appendHoverText(ItemStack stack, BlockGetter ctx, List<Component> component, TooltipFlag flag) {
	^///?}
		LegacyTooltips.shiftOnly(component, "tooltip.beautify.botanist_workbench.1");
		super.appendHoverText(stack, ctx, component, flag);
	}
	*///?} else {
    public DataComponentType<TooltipLore> getTooltipType() {
		//? if fabric {
		return ComponentInit.get(ComponentInit.BOTANIST_WORKBENCH_TOOLTIP);
	//?} else {
		/*return ComponentInit.get(ComponentInit.BOTANIST_WORKBENCH_TOOLTIP);
	*///?}
    }

    @Override
    public TooltipLore getTooltipComponent() {
        return BotanistWorkbench.TooltipComponent.INSTANCE;
    }

    public static final class TooltipComponent {
        public static final TooltipLore INSTANCE = TooltipLore.of("tooltip.beautify.botanist_workbench.1");
    }
	//?}
}
