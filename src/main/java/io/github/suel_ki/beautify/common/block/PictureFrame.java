package io.github.suel_ki.beautify.common.block;

import io.github.suel_ki.beautify.client.tooltip.LegacyTooltips;

import java.util.List;

import com.mojang.serialization.Codec;
import it.unimi.dsi.fastutil.HashCommon;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
//? if >=1.21.11 {
import net.minecraft.world.level.ScheduledTickAccess;
//?}
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;


//? if >=1.21.11 {
import io.github.suel_ki.beautify.client.tooltip.TooltipLore;
import io.github.suel_ki.beautify.common.tooltip.BlockTooltip;
import io.github.suel_ki.beautify.core.init.ComponentInit;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
//?}

//? if >=1.21.1 && <26.3
//import com.mojang.serialization.MapCodec;

//? if >=1.21.11 {
public class PictureFrame extends HorizontalDirectionalBlock implements BlockTooltip, SimpleWaterloggedBlock {
//?} else {
/*public class PictureFrame extends HorizontalDirectionalBlock implements SimpleWaterloggedBlock {
*///?}
	private static final int MODELCOUNT = 13; // number of models the frame has
	public static final List<String> MOTIVE_MODELS = List.of(
			"sunset",
			"flower",
			"wasteland",
			"plant",
			"kebab",
			"aztec2",
			"aztec",
			"alban",
			"bomb",
			"shroom",
			"river",
			"beach",
			"frog"
	);
	public static final IntegerProperty FRAME_MOTIVE = IntegerProperty.create("frame_motive", 0, MODELCOUNT - 1);
	/** true when the frame stands turned 45 degrees past FACING */
	public static final BooleanProperty DIAGONAL = BooleanProperty.create("diagonal");
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	protected static final VoxelShape SHAPE = Block.box(5, 0, 5, 11, 8, 11);

	//? if >=1.21.1 && <26.3 {
	/*public static final MapCodec<PictureFrame> CODEC = simpleCodec(PictureFrame::new);
	*///?}

	public PictureFrame(Properties properties) {
		super(properties);
		this.registerDefaultState(this.defaultBlockState()
                .setValue(FRAME_MOTIVE, 0)
                .setValue(FACING, Direction.NORTH)
                .setValue(DIAGONAL, false)
                .setValue(WATERLOGGED, false));
	}

	//? if >=1.21.1 && <26.3 {
	/*@Override
	protected MapCodec<PictureFrame> codec() {
		return CODEC;
	}
	*///?}

	// changing the model of the picture frame by shift-rightclicking
	//? if >=1.21.1 {
	@Override
	public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player,
											BlockHitResult result) {
	//?} else {
	/*@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand,
			BlockHitResult result) {
	*///?}
		if (!level.isClientSide() && player.isShiftKeyDown()) {
			//? if <1.21.1 {
			/*if (hand != InteractionHand.MAIN_HAND || !player.getItemInHand(hand).isEmpty()) {
				return InteractionResult.SUCCESS;
			}
			*///?}
			int currentModel = state.getValue(FRAME_MOTIVE); // current index
			// reset if it surpasses the number of possible models
			if (currentModel + 1 > MODELCOUNT - 1) {
				level.setBlock(pos, state.setValue(FRAME_MOTIVE, 0), 3);
			} else { // increases index
				level.setBlock(pos, state.setValue(FRAME_MOTIVE, currentModel + 1), 3);
			}
			level.playSound(null, pos, SoundEvents.PAINTING_PLACE, SoundSource.BLOCKS, 1, 1);
			return InteractionResult.SUCCESS;
		}
		return InteractionResult.SUCCESS;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos,
			CollisionContext context) {
		return SHAPE;
	}

	//? if >=1.21.11 {
	@Override
	public VoxelShape getOcclusionShape(BlockState blockState) {
		return Shapes.empty();
	}
//?} else {
	/*public VoxelShape getOcclusionShape(BlockState state, BlockGetter level, BlockPos pos) {
		return Shapes.empty();
	}

*///?}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		// getRotation() is the player's yaw: 0 = south, increasing clockwise, so the 45 degree steps
		// are 0 = south, 1 = south-west, ... Standing signs place themselves from the same value.
		int step = Math.round(context.getRotation() / 45.0F) & 7;
		int front8 = (step + 4) & 7;      // the frame faces back at the player
		int base = front8 - (front8 & 1); // even step: cardinal, odd step: 45 degrees past it
		return this.defaultBlockState()
                .setValue(FACING, Direction.fromYRot(base * 45.0))
				.setValue(DIAGONAL, (front8 & 1) == 1)
				.setValue(FRAME_MOTIVE, randomMotive(context))
				.setValue(WATERLOGGED, context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER);
	}

	// getStateForPlacement runs on both sides; a Random pick made the client's prediction and the
	// server's state disagree, so the motive is derived from values both sides know instead
	private static int randomMotive(BlockPlaceContext context) {
		long key = context.getClickedPos().asLong();
		Player player = context.getPlayer();
		if (player != null) {
			key ^= (long) player.getUUID().hashCode() << 32;
		}
		key ^= context.getHorizontalDirection().get2DDataValue();
		return Math.floorMod(HashCommon.murmurHash3(key), MODELCOUNT);
	}

	// creates blockstate
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FRAME_MOTIVE, FACING, DIAGONAL, WATERLOGGED);
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

    //? if >=1.21.11 {
    @Override
    public DataComponentType<TooltipLore> getTooltipType() {
        return ComponentInit.get(ComponentInit.PICTURE_FRAME_TOOLTIP);
    }

    @Override
    public TooltipLore getTooltipComponent() {
        return TooltipComponent.INSTANCE;
    }

    public static final class TooltipComponent {
        public static final TooltipLore INSTANCE = TooltipLore.of("tooltip.beautify.picture_frame.1", "tooltip.beautify.picture_frame.2");
    }

//?} else {
	/*@Override
	//? if >=1.21.1
	public void appendHoverText(ItemStack stack, Item.TooltipContext tooltipContext, List<Component> component, TooltipFlag flag) {
	//? if <1.21.1
	//public void appendHoverText(ItemStack stack, BlockGetter tooltipContext, List<Component> component, TooltipFlag flag) {
		LegacyTooltips.shiftOnly(component, "tooltip.beautify.picture_frame.1", "tooltip.beautify.picture_frame.2");
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
