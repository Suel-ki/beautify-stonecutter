package io.github.suel_ki.beautify.common.block;

import io.github.suel_ki.beautify.client.tooltip.LegacyTooltips;


import com.google.common.collect.ImmutableMap;
import io.github.suel_ki.beautify.Beautify;
import io.github.suel_ki.beautify.core.init.SoundInit;
import io.github.suel_ki.beautify.util.BeautifyConfig;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Explosion;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
//? if >=1.21.11 {
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.redstone.Orientation;
//?}
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.Map;

//? if >=1.21.11 {
import com.mojang.serialization.Codec;
import io.github.suel_ki.beautify.client.tooltip.TooltipLore;
import io.github.suel_ki.beautify.common.tooltip.BlockTooltip;
import io.github.suel_ki.beautify.core.init.ComponentInit;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.server.level.ServerLevel;
//?}

//? if >=1.21.1 && <1.21.11
//import net.minecraft.world.ItemInteractionResult;

//? if <1.21.11 {
/*import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
*///?}

//? if >=1.21.1 && <26.3
//import com.mojang.serialization.MapCodec;

//? if >=1.21.11 {
public class Blinds extends HorizontalDirectionalBlock implements BlockTooltip, SimpleWaterloggedBlock {
//?} else {
//public class Blinds extends HorizontalDirectionalBlock implements SimpleWaterloggedBlock {
//?}
	// Voxelshapes; Hidden = Blind not visible
	private static final Map<Direction, VoxelShape> CLOSED_SHAPES = ImmutableMap.of(
			Direction.NORTH, Block.box(0, 13, 13, 16, 16, 16),
			Direction.EAST, Block.box(0, 13, 0, 3, 16, 16),
			Direction.SOUTH, Block.box(0, 13, 0, 16, 16, 3),
			Direction.WEST, Block.box(13, 13, 0, 16, 16, 16)
	);

	private static final Map<Direction, VoxelShape> OPEN_SHAPES = ImmutableMap.of(
			Direction.NORTH, Block.box(0, 0, 13, 16, 16, 16),
			Direction.EAST, Block.box(0, 0, 0, 3, 16, 16),
			Direction.SOUTH, Block.box(0, 0, 0, 16, 16, 2),
			Direction.WEST, Block.box(13, 0, 0, 16, 16, 16)
	);

	private static final VoxelShape SHAPE_HIDDEN = Shapes.empty();
	// Open = true if blinds are down; Hidden = true if blinds are closed and there
	// is a blind above
	public static final BooleanProperty OPEN = BooleanProperty.create("open");
	public static final BooleanProperty HIDDEN = BooleanProperty.create("hidden");
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	public static final BooleanProperty POWERED = BooleanProperty.create("powered");

	//? if >=1.21.1 && <26.3 {
	//public static final MapCodec<Blinds> CODEC = simpleCodec(Blinds::new);
	//?}

	// constructor
	public Blinds(Properties properties) {
		super(properties);
		this.registerDefaultState(this.defaultBlockState().setValue(OPEN, false).setValue(FACING, Direction.NORTH)
				.setValue(HIDDEN, false).setValue(POWERED, false).setValue(WATERLOGGED, false));
	}

	//? if >=1.21.1 && <26.3 {
	/*@Override
	protected MapCodec<Blinds> codec() {
		return CODEC;
	}
	*///?}

	@Override
	public boolean isCollisionShapeFullBlock(BlockState state, BlockGetter level, BlockPos pos) {
		return false;
	}

	@Override
	public VoxelShape getBlockSupportShape(BlockState state, BlockGetter level, BlockPos pos) {
		return Shapes.empty();
	}

	//? if >=1.21.11 {
	@Override
	public boolean propagatesSkylightDown(BlockState state) {
		return true;
	}
//?} else {
	/*@Override
	public boolean propagatesSkylightDown(BlockState state, BlockGetter level, BlockPos pos) {
		return true;
	}
*///?}

	// check for facing placement, hidden is false per default
	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Level level = context.getLevel();
		BlockPos pos = context.getClickedPos();
		boolean powered = level.hasNeighborSignal(pos) && (level.isClientSide() || BeautifyConfig.redstone());
		return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite())
				.setValue(OPEN, powered).setValue(HIDDEN, false).setValue(POWERED, powered)
				.setValue(WATERLOGGED, level.getFluidState(pos).getType() == Fluids.WATER);
	}

	// creating blockstates
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(OPEN, FACING, HIDDEN, POWERED, WATERLOGGED);
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


	// INTERACTION
	// changes blockstates:
	// OPEN: open <-> closed
	// HIDDEN: false <-> true if below root
	//? if >=1.21.11 {
	@Override
	public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player,
			BlockHitResult result) {
		if (level.isClientSide()) {
			return InteractionResult.SUCCESS;
		}
		if (!BeautifyConfig.interactWhenPowered() && state.getValue(POWERED)) {
			return InteractionResult.PASS;
		}
		setGroupOpen(level, pos, state, !state.getValue(OPEN));
		return InteractionResult.SUCCESS;
	}

	@Override
	public InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
			Player player, InteractionHand hand, BlockHitResult hit) {
		return useWithoutItem(state, level, pos, player, hit);
	}

//?} else {
	/*//? if >=1.21.1 {
	@Override
	public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
										Player player, InteractionHand hand, BlockHitResult hit) {
		InteractionResult result = toggle(state, level, pos);
		return result == InteractionResult.SUCCESS ? ItemInteractionResult.SUCCESS
				: ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
	}
	//?} else {
	/^@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand,
			BlockHitResult result) {
		return toggle(state, level, pos);
	}
	^///?}
*///?}

	//? if <1.21.11 {
	/*private InteractionResult toggle(BlockState state, Level level, BlockPos pos) {
		if (level.isClientSide()) {
			return InteractionResult.SUCCESS;
		}
		if (!BeautifyConfig.interactWhenPowered() && state.getValue(POWERED)) {
			return InteractionResult.PASS;
		}
		setGroupOpen(level, pos, state, !state.getValue(OPEN));
		return InteractionResult.SUCCESS;
	}
*///?}

	// returns: true/false if
	// block in level at pos is the same kind of blind
	// and facing is the same as state
	private boolean sameBlindType(LevelAccessor level, BlockPos pos, BlockState state) {
		BlockState blindsState = level.getBlockState(pos);
		Block block = blindsState.getBlock();

		if (block == this && state.hasProperty(FACING)) {
			return blindsState.getValue(FACING) == state.getValue(FACING);
		}

		return false;
	}

	//? if >=1.21.11 {
	@Override
	public void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock,
			Orientation orientation, boolean movedByPiston) {
		updateRedstone(state, level, pos);
	}
	//?} else {
	/*@Override
	public void neighborChanged(BlockState state, Level level, BlockPos pos, Block neighborBlock, BlockPos neighborPos,
			boolean movedByPiston) {
		updateRedstone(state, level, pos);
	}
	*///?}

	@Override
	public void onPlace(BlockState state, Level level, BlockPos pos, BlockState oldState, boolean movedByPiston) {
		updateRedstone(state, level, pos);
	}

	private void updateRedstone(BlockState state, Level level, BlockPos pos) {
		if (level.isClientSide()) {
			return;
		}
		if (!BeautifyConfig.redstone()) {
			if (state.getValue(POWERED)) {
				level.setBlock(pos, state.setValue(POWERED, false), 3);
			}
			return;
		}
		boolean powered = level.hasNeighborSignal(pos);
		if (powered == state.getValue(POWERED)) {
			return;
		}
		setGroupOpen(level, pos, state, powered);
	}

	// powered blinds are down
	private void setGroupOpen(Level level, BlockPos pos, BlockState state, boolean open) {
		boolean wasOpen = state.getValue(OPEN);
		BlockPos root = pos;
		if (BeautifyConfig.opensFromRoot()) {
			int step = 1;
			while (sameBlindType(level, pos.above(step), state)) {
				++step;
			}
			root = pos.above(step - 1);
		}

		applyOpen(level, root, open, true);
		applyColumn(level, root, state, open);

		int radius = BeautifyConfig.searchRadius();
		if (radius > 0) {
			Direction facing = state.getValue(FACING);
			Direction[] sides = facing == Direction.NORTH || facing == Direction.SOUTH
					? new Direction[]{Direction.EAST, Direction.WEST}
					: new Direction[]{Direction.NORTH, Direction.SOUTH};
			for (Direction side : sides) {
				for (int offset = 1; offset <= radius / 2; ++offset) {
					BlockPos sidePos = root.relative(side, offset);
					if (!sameBlindType(level, sidePos, state)) {
						break;
					}
					applyOpen(level, sidePos, open, true);
					applyColumn(level, sidePos, state, open);
				}
			}
		}

		if (wasOpen != open) {
			level.playSound(null, root,
					open ? SoundInit.get(SoundInit.BLINDS_OPEN) : SoundInit.get(SoundInit.BLINDS_CLOSE),
					SoundSource.BLOCKS, 1, 1);
		}
	}

	private void applyColumn(Level level, BlockPos top, BlockState state, boolean open) {
		for (int offset = 1; offset <= BeautifyConfig.searchRadius(); ++offset) {
			BlockPos below = top.below(offset);
			if (!sameBlindType(level, below, state)) {
				break;
			}
			applyOpen(level, below, open, false);
		}
	}

	private void applyOpen(Level level, BlockPos pos, boolean open, boolean top) {
		BlockState current = level.getBlockState(pos);
		BlockState next = current.setValue(OPEN, open).setValue(HIDDEN, !open && !top)
				.setValue(POWERED, BeautifyConfig.redstone() && level.hasNeighborSignal(pos));
		if (next != current) {
			level.setBlock(pos, next, 3);
		}
	}

	private void reveal(LevelAccessor level, BlockPos pos) {
		BlockState state = level.getBlockState(pos);
		level.setBlock(pos, state.setValue(HIDDEN, false), 3);
	}

	// method to prevent hidden blinds from being unaccessible
	// after root block is destroyed.
	// updates blind below the destroyed block.
	@Override
	public void destroy(LevelAccessor level, BlockPos pos, BlockState state) {
		if (sameBlindType(level, pos.below(), state)) {
			reveal(level, pos.below());
		}
	}

	@Override
	//? if >=1.21.11
	public void wasExploded(ServerLevel level, BlockPos pos, Explosion explosion) {
	//? if <1.21.11
	//public void wasExploded(Level level, BlockPos pos, Explosion explosion) {
		BlockState state = level.getBlockState(pos);
		if (sameBlindType(level, pos.below(), state)) {
			reveal(level, pos.below());
		}
		super.wasExploded(level, pos, explosion);
	}
	// hidden = invisible model
	// OPEN_X = models of blinds that are down
	// CLOSED_X= models of blinds that are up
	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		if (state.getValue(HIDDEN)) {
			return SHAPE_HIDDEN;
		}

		Direction facing = state.getValue(FACING);
		boolean isOpen = state.getValue(OPEN);

		Map<Direction, VoxelShape> shapes = isOpen ? OPEN_SHAPES : CLOSED_SHAPES;

		return shapes.get(facing);
	}

    //? if >=1.21.11 {
    @Override
    public DataComponentType<TooltipLore> getTooltipType() {
            return ComponentInit.get(ComponentInit.BLINDS_TOOLTIP);
    }

    @Override
    public TooltipLore getTooltipComponent() {
        return TooltipComponent.INSTANCE;
    }

    public static final class TooltipComponent {
        public static final TooltipLore INSTANCE = TooltipLore.of("tooltip.beautify.blinds.1", "tooltip.beautify.blinds.2");
    }

//?} else {
	/*@Override
	//? if >=1.21.1
	public void appendHoverText(ItemStack stack, Item.TooltipContext tooltipContext, List<Component> component, TooltipFlag flag) {
	//? if <1.21.1
	//public void appendHoverText(ItemStack stack, BlockGetter tooltipContext, List<Component> component, TooltipFlag flag) {
		LegacyTooltips.shiftOnly(component, "tooltip.beautify.blinds.1", "tooltip.beautify.blinds.2");
		//? if >=1.21.1
		super.appendHoverText(stack, tooltipContext, component, flag);
	}
*///?}

	//? if neoforge {
	/*@Override
	public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
		return true;
	}

	@Override
	public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
		return 30;
	}

	@Override
	public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
		return 20;
	}
	*///?}
}
