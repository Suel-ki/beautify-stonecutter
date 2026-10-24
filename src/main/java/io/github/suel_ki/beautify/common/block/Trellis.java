package io.github.suel_ki.beautify.common.block;

import io.github.suel_ki.beautify.client.tooltip.LegacyTooltips;


import com.google.common.collect.ImmutableMap;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
//? if >=1.21.11 {
import net.minecraft.world.level.ScheduledTickAccess;
//?}
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Arrays;
import java.util.Map;
import java.util.List;

//? if >=1.21.11 {
import com.mojang.serialization.Codec;
import io.github.suel_ki.beautify.client.tooltip.TooltipLore;
import io.github.suel_ki.beautify.common.tooltip.BlockTooltip;
import io.github.suel_ki.beautify.core.init.ComponentInit;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
//?}

//? if >=1.21.1 && <1.21.11 {
/*import net.minecraft.world.ItemInteractionResult;
*///?}

//? if <1.21.11 {
/*import net.minecraft.ChatFormatting;
import net.minecraft.world.item.TooltipFlag;
*///?}

//? if >=1.21.1 && <26.3 {
/*import com.mojang.serialization.MapCodec;
*///?}

//? if >=1.21.11 {
public class Trellis extends HorizontalDirectionalBlock implements BlockTooltip, SimpleWaterloggedBlock {
//?} else {
/*public class Trellis extends HorizontalDirectionalBlock implements SimpleWaterloggedBlock {
*///?}
	public static final List<Item> VALID_FLOWERS = Arrays.asList(Items.AIR, Items.ROSE_BUSH, Items.SUNFLOWER,
			Items.PEONY, Items.LILAC, Items.VINE, Items.WEEPING_VINES, Items.TWISTING_VINES, Items.GLOW_LICHEN);

	// FLOWERS indicates which index of the flowers List below is active
	public static final List<String> FLOWER_SUFFIXES = List.of(
			"", "_rose", "_sunflower", "_peony", "_lilac", "_vine", "_weeping_vines", "_twisting_vines", "_lichen");

public static final IntegerProperty FLOWERS = IntegerProperty.create("flowers", 0, VALID_FLOWERS.size() - 1);

	public static final BooleanProperty CEILLING = BooleanProperty.create("ceilling");
	public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;
	private static final VoxelShape SHAPE_CEILLING = Block.box(0, 14, 0, 16, 16, 16);
	private static final Map<Direction, VoxelShape> SHAPES_FOR_MODEL = ImmutableMap.of(
			Direction.NORTH, Block.box(0, 0, 14, 16, 16, 16),
			Direction.SOUTH, Block.box(0, 0, 0, 16, 16, 2),
			Direction.WEST, Block.box(14, 0, 0, 16, 16, 16),
			Direction.EAST, Block.box(0, 0, 0, 2, 16, 16)
	);

	//? if >=1.21.1 && <26.3 {
	/*public static final MapCodec<Trellis> CODEC = simpleCodec(Trellis::new);
	*///?}

	public Trellis(Properties properties) {
		super(properties);
		this.registerDefaultState(this.defaultBlockState().setValue(CEILLING, false).setValue(FACING, Direction.NORTH)
				.setValue(WATERLOGGED, false));
	}

	//? if >=1.21.1 && <26.3 {
	/*@Override
	protected MapCodec<Trellis> codec() {
		return CODEC;
	}
	*///?}

	public List<Item> getValidFlowers() {
		return VALID_FLOWERS;
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		// if the trellis placed on underside of block -> hanging from the ceilling
		boolean waterlogged = context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER;
		if (context.getClickedFace() == Direction.DOWN) {
			return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite())
					.setValue(CEILLING, true).setValue(WATERLOGGED, waterlogged);
		}
		// otherwise on a wall
		return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite())
				.setValue(CEILLING, false).setValue(WATERLOGGED, waterlogged);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		if (state.getValue(CEILLING)) {
			return SHAPE_CEILLING;
		}

		return SHAPES_FOR_MODEL.get(state.getValue(FACING));
	}

	//? if >=1.21.11 {
	@Override
	public InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand,
										   BlockHitResult result) {

		if (!level.isClientSide() && hand == InteractionHand.MAIN_HAND) {

			ItemStack playerStack = player.getItemInHand(hand);

			// if there is a flower
			if (state.getValue(FLOWERS) != 0) {

				// giving flower and clearing pot if hand empty or same stack
				if (playerStack.isEmpty()) {
					player.setItemInHand(hand, new ItemStack(VALID_FLOWERS.get(state.getValue(FLOWERS))));
					level.setBlock(pos, state.setValue(FLOWERS, 0), 3);
					level.playSound(null, pos, SoundEvents.AZALEA_LEAVES_BREAK, SoundSource.BLOCKS, 1, 1);
					//? if >=26.3
					player.swing(hand, net.minecraft.world.item.component.SwingAnimation.DEFAULT, true);
					//? if <26.3
					//player.swing(hand, true);
					return InteractionResult.SUCCESS;
				} else if (playerStack.is(VALID_FLOWERS.get(state.getValue(FLOWERS)))
						&& playerStack.getCount() < playerStack.getMaxStackSize()) {
					playerStack.grow(1);
					level.setBlock(pos, state.setValue(FLOWERS, 0), 3);
					level.playSound(null, pos, SoundEvents.AZALEA_LEAVES_BREAK, SoundSource.BLOCKS, 1, 1);
					//? if >=26.3
					player.swing(hand, net.minecraft.world.item.component.SwingAnimation.DEFAULT, true);
					//? if <26.3
					//player.swing(hand, true);
					return InteractionResult.SUCCESS;
				}

				// else just return
				return InteractionResult.CONSUME;
			} else { // if there is no flower

				// checks if the flower in hand matches the available types
				for (Item flower : VALID_FLOWERS) {
					if (playerStack.getItem().equals(flower)) {
						level.setBlock(pos, state.setValue(FLOWERS, VALID_FLOWERS.indexOf(flower)), 3);
						player.awardStat(Stats.POT_FLOWER);
						if (!flower.equals(Items.AIR)) {
							level.playSound(null, pos, SoundEvents.AZALEA_PLACE, SoundSource.BLOCKS, 1, 1);
							//? if >=26.3
							player.swing(hand, net.minecraft.world.item.component.SwingAnimation.DEFAULT, true);
							//? if <26.3
							//player.swing(hand, true);
						}
						//? if >=1.21.2 {
						playerStack.consume(1, player);
						//?} else {
						/*if (!player.isCreative()) {
							playerStack.shrink(1);
						}*///?}
						return InteractionResult.SUCCESS;
					}
				}
				// if the flower is not a valid one
				return InteractionResult.CONSUME;
			}
		}
		// end of statement
		return InteractionResult.CONSUME;
	}
//?} else {
	/*//? if >=1.21.1 {
	@Override
	public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand,
//?} else {
	/^@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand,
^///?}
										   BlockHitResult result) {

		if (!level.isClientSide() && hand == InteractionHand.MAIN_HAND) {

			ItemStack playerStack = player.getItemInHand(hand);

			// if there is a flower
			if (state.getValue(FLOWERS) != 0) {

				// giving flower and clearing pot if hand empty or same stack
				if (playerStack.isEmpty()) {
					player.setItemInHand(hand, new ItemStack(VALID_FLOWERS.get(state.getValue(FLOWERS))));
					level.setBlock(pos, state.setValue(FLOWERS, 0), 3);
					level.playSound(null, pos, SoundEvents.AZALEA_LEAVES_BREAK, SoundSource.BLOCKS, 1, 1);
					player.swing(hand, true);
					//? if >=1.21.1
					return ItemInteractionResult.SUCCESS;
					//? if <1.21.1
					//return InteractionResult.SUCCESS;
				} else if (playerStack.is(VALID_FLOWERS.get(state.getValue(FLOWERS)))
						&& playerStack.getCount() < playerStack.getMaxStackSize()) {
					playerStack.grow(1);
					level.setBlock(pos, state.setValue(FLOWERS, 0), 3);
					level.playSound(null, pos, SoundEvents.AZALEA_LEAVES_BREAK, SoundSource.BLOCKS, 1, 1);
					player.swing(hand, true);
					//? if >=1.21.1
					return ItemInteractionResult.SUCCESS;
					//? if <1.21.1
					//return InteractionResult.SUCCESS;
				}

				// else just return
				//? if >=1.21.1
				return ItemInteractionResult.CONSUME;
				//? if <1.21.1
				//return InteractionResult.CONSUME;
			} else { // if there is no flower

				// checks if the flower in hand matches the available types
				for (Item flower : VALID_FLOWERS) {
					if (playerStack.getItem().equals(flower)) {
						level.setBlock(pos, state.setValue(FLOWERS, VALID_FLOWERS.indexOf(flower)), 3);
						player.awardStat(Stats.POT_FLOWER);
						if (!flower.equals(Items.AIR)) {
							level.playSound(null, pos, SoundEvents.AZALEA_PLACE, SoundSource.BLOCKS, 1, 1);
							player.swing(hand, true);
						}
						if (!player.isCreative()) {
							playerStack.shrink(1);
						}
						//? if >=1.21.1
						return ItemInteractionResult.SUCCESS;
						//? if <1.21.1
						//return InteractionResult.SUCCESS;
					}
				}
				// if the flower is not a valid one
				//? if >=1.21.1
				return ItemInteractionResult.CONSUME;
				//? if <1.21.1
				//return InteractionResult.CONSUME;
			}
		}
		// end of statement
		//? if >=1.21.1
		return ItemInteractionResult.CONSUME;
		//? if <1.21.1
		//return InteractionResult.CONSUME;
	}

*///?}

	//? if >=1.21.11 {
	@Override
	public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean bl) {
		return this.isFlowerless(state) ? super.getCloneItemStack(level, pos, state, bl) : new ItemStack(getContent(state));
	}
//?} else {
	/*@Override
	//? if >=1.21.1
	public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
	//? if <1.21.1
	//public ItemStack getCloneItemStack(BlockGetter level, BlockPos pos, BlockState state) {
		return this.isFlowerless(state) ? super.getCloneItemStack(level, pos, state) : new ItemStack(getContent(state));
	}
*///?}

	private boolean isFlowerless(BlockState state) {
		return state.getValue(FLOWERS) == 0;
	}

	public Block getContent(BlockState state) {
		return Block.byItem(VALID_FLOWERS.get(state.getValue(FLOWERS)));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(FACING, CEILLING, FLOWERS, WATERLOGGED);
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
        return ComponentInit.get(ComponentInit.TRELLIS_TOOLTIP);
    }

    @Override
    public TooltipLore getTooltipComponent() {
        return TooltipComponent.INSTANCE;
    }

    public static final class TooltipComponent {
        public static final TooltipLore INSTANCE = TooltipLore.create(
        		List.of(Component.translatable("tooltip.beautify.shift"), Component.translatable("tooltip.beautify.plantlist")),
        		List.of(Component.translatable("tooltip.beautify.trellis.1"), Component.translatable("tooltip.beautify.trellis.2")),
        		List.of());
    }

//?} else {
	/*@Override
	//? if >=1.21.1
	public void appendHoverText(ItemStack stack, Item.TooltipContext tooltipContext, List<Component> component, TooltipFlag flag) {
	//? if <1.21.1
	//public void appendHoverText(ItemStack stack, BlockGetter tooltipContext, List<Component> component, TooltipFlag flag) {
		LegacyTooltips.shiftAndPlantList(component, "tooltip.beautify.trellis.1", "tooltip.beautify.trellis.2");
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
