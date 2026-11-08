//? if >=1.20.1 {
package io.github.suel_ki.beautify.common.block;

import com.google.common.collect.ImmutableMap;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
//? if >=1.21.1 && <1.21.11
//import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;

import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.tags.BlockTags;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;

import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import io.github.suel_ki.beautify.common.block.entity.MultiFlowerPotBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
//? if >=26.3
import net.minecraft.util.Prediction;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Map;

public class MultiFlowerPotBlock extends HorizontalDirectionalBlock implements EntityBlock {

	//? if >=1.21.1 && <26.3
	//public static final MapCodec<MultiFlowerPotBlock> CODEC = simpleCodec(MultiFlowerPotBlock::new);
	public static final IntegerProperty POTS = IntegerProperty.create("pots", 2, 4);
	public static final IntegerProperty VARIANT = IntegerProperty.create("variant", 0, 1);

	public record Slot(double centerX, double centerZ, double soil, double scale) {
	}

	// slot layout per variant: 0 = spread, 1 = compact
	public static final Slot[][] SLOTS = {
			{
					new Slot(5.0 / 16, 5.0 / 16, 4.0 / 16, 1.0),
					new Slot(11.4 / 16, 5.2 / 16, 3.36 / 16, 0.84),
					new Slot(5.2 / 16, 11.4 / 16, 2.88 / 16, 0.72),
					new Slot(11.6 / 16, 11.0 / 16, 2.48 / 16, 0.62)
			},
			{
					new Slot(5.0 / 16, 5.0 / 16, 4.0 / 16, 1.0),
					new Slot(10.52 / 16, 5.0 / 16, 3.36 / 16, 0.84),
					new Slot(5.0 / 16, 10.16 / 16, 2.88 / 16, 0.72),
					new Slot(8.6 / 16, 9.1 / 16, 2.01 / 16, 0.605)
			}
	};

	private static final VoxelShape SHAPE_SYMMETRIC = Block.box(2, 0, 2, 14, 6, 14);

	private static final Map<Direction, VoxelShape> SHAPES_TWO_SPREAD = ImmutableMap.of(
			Direction.NORTH, Block.box(2, 0, 2, 14, 6, 8),
			Direction.EAST, Block.box(8, 0, 2, 14, 6, 14),
			Direction.SOUTH, Block.box(2, 0, 8, 14, 6, 14),
			Direction.WEST, Block.box(2, 0, 2, 8, 6, 14)
	);

	private static final Map<Direction, VoxelShape> SHAPES_TWO_COMPACT = ImmutableMap.of(
			Direction.NORTH, Block.box(2, 0, 2, 13, 6, 8),
			Direction.EAST, Block.box(8, 0, 2, 14, 6, 13),
			Direction.SOUTH, Block.box(3, 0, 8, 14, 6, 14),
			Direction.WEST, Block.box(2, 0, 3, 8, 6, 14)
	);

	private static final Map<Direction, VoxelShape> SHAPES_FULL_SPREAD = ImmutableMap.of(
			Direction.NORTH, SHAPE_SYMMETRIC,
			Direction.EAST, SHAPE_SYMMETRIC,
			Direction.SOUTH, SHAPE_SYMMETRIC,
			Direction.WEST, SHAPE_SYMMETRIC
	);

	private static final Map<Direction, VoxelShape> SHAPES_FULL_COMPACT = ImmutableMap.of(
			Direction.NORTH, Block.box(2, 0, 2, 13, 6, 12),
			Direction.EAST, Block.box(4, 0, 2, 14, 6, 13),
			Direction.SOUTH, Block.box(3, 0, 4, 14, 6, 14),
			Direction.WEST, Block.box(2, 0, 3, 12, 6, 14)
	);

	private static VoxelShape shape(BlockState state) {
		boolean compact = state.getValue(VARIANT) == 1;
		Map<Direction, VoxelShape> shapes = state.getValue(POTS) == 2
				? (compact ? SHAPES_TWO_COMPACT : SHAPES_TWO_SPREAD)
				: (compact ? SHAPES_FULL_COMPACT : SHAPES_FULL_SPREAD);
		return shapes.get(state.getValue(FACING));
	}

	public MultiFlowerPotBlock(Properties properties) {
		super(properties);
		registerDefaultState(stateDefinition.any().setValue(POTS, 2).setValue(VARIANT, 0).setValue(FACING, Direction.NORTH));
	}

	//? if >=1.21.1 && <26.3 {
	/*@Override
	protected MapCodec<? extends HorizontalDirectionalBlock> codec() {
		return CODEC;
	}
	*///?}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		builder.add(POTS, VARIANT, FACING);
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return shape(state);
	}

	@Override
	public VoxelShape getCollisionShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return shape(state);
	}

	@Override
	public VoxelShape getInteractionShape(BlockState state, BlockGetter level, BlockPos pos) {
		return shape(state);
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		return defaultBlockState().setValue(FACING, context.getHorizontalDirection());
	}

	@Override
	public BlockState rotate(BlockState state, Rotation rotation) {
		return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
	}

	@Override
	public BlockState mirror(BlockState state, Mirror mirror) {
		return state.rotate(mirror.getRotation(state.getValue(FACING)));
	}

	@Nullable
	@Override
	public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new MultiFlowerPotBlockEntity(pos, state);
	}

	//? if <1.21.1 {
	/*@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
		ItemStack stack = player.getItemInHand(hand);
		if (stack.isEmpty() && player.isShiftKeyDown()) {
			if (!level.isClientSide()) {
				int variant = state.getValue(VARIANT);
				level.setBlock(pos, state.setValue(VARIANT, variant == 0 ? 1 : 0), 3);
			}
			return InteractionResult.SUCCESS;
		}
		return interact(state, level, pos, player, stack, hit) ? InteractionResult.SUCCESS : InteractionResult.PASS;
	}
	*///?}
	//? if >=1.21.1 && <1.21.11 {
	/*@Override
	public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
										   Player player, InteractionHand hand, BlockHitResult hit) {
		if (stack.isEmpty()) {
			return ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
		}
		return interact(state, level, pos, player, stack, hit) ? ItemInteractionResult.SUCCESS
				: ItemInteractionResult.PASS_TO_DEFAULT_BLOCK_INTERACTION;
	}
	*///?}
	//? if >=1.21.11 {
	@Override
	public InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
									   Player player, InteractionHand hand, BlockHitResult hit) {
		if (stack.isEmpty()) {
			return InteractionResult.TRY_WITH_EMPTY_HAND;
		}
		return interact(state, level, pos, player, stack, hit) ? InteractionResult.SUCCESS : InteractionResult.PASS;
	}
	//?}

	//? if >=1.21.1 {
	@Override
	public InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hit) {
		if (!level.isClientSide() && player.isShiftKeyDown()) {
			int variant = state.getValue(VARIANT);
			level.setBlock(pos, state.setValue(VARIANT, variant == 0 ? 1 : 0), 3);
			return InteractionResult.SUCCESS;
		}
		return interact(state, level, pos, player, ItemStack.EMPTY, hit) ? InteractionResult.SUCCESS : InteractionResult.PASS;
	}
	//?}

	private boolean interact(BlockState state, Level level, BlockPos pos, Player player, ItemStack stack, BlockHitResult hit) {
		if (!(level.getBlockEntity(pos) instanceof MultiFlowerPotBlockEntity pot)) {
			return false;
		}
		if (!stack.isEmpty() && stack.is(Items.FLOWER_POT)) {
			int pots = state.getValue(POTS);
			if (pots >= 4) {
				return false;
			}
			if (!level.isClientSide()) {
				level.setBlockAndUpdate(pos, state.setValue(POTS, pots + 1));
				if (!player.getAbilities().instabuild) {
					stack.shrink(1);
				}
			}
			return true;
		}
		int slot = slotFromHit(state, pos, hit);
		if (slot < 0 || slot >= state.getValue(POTS)) {
			slot = firstEmpty(pot, state.getValue(POTS));
		}
		if (slot < 0) {
			return false;
		}
		if (!stack.isEmpty() && stack.getItem() instanceof BlockItem blockItem && isPlant(blockItem)) {
			if (!pot.getPlant(slot).isEmpty()) {
				return false;
			}
			if (!level.isClientSide()) {
				pot.setPlant(slot, stack.copyWithCount(1));
				if (!player.getAbilities().instabuild) {
					stack.shrink(1);
				}
			}
			return true;
		}
		if (stack.isEmpty()) {
			ItemStack plant = pot.getPlant(slot);
			if (plant.isEmpty()) {
				return false;
			}
			if (!level.isClientSide()) {
				pot.setPlant(slot, ItemStack.EMPTY);
				if (!player.addItem(plant)) {
					//? if <26.3
					//player.drop(plant, false);
					//? if >=26.3
					player.drop(plant, false, Prediction.PREDICTED);
				}
			}
			return true;
		}
		return false;
	}

	public static int yaw(Direction facing) {
		return switch (facing) {
			case EAST -> 90;
			case SOUTH -> 180;
			case WEST -> 270;
			default -> 0;
		};
	}

	private static int firstEmpty(MultiFlowerPotBlockEntity pot, int pots) {
		for (int i = 0; i < pots; i++) {
			if (pot.getPlant(i).isEmpty()) {
				return i;
			}
		}
		return -1;
	}

	private static List<ItemStack> plantableStacks;

	public static List<ItemStack> plantableStacks() {
		if (plantableStacks == null) {
			plantableStacks = BuiltInRegistries.BLOCK.stream()
					.map(Block::asItem)
					.filter(item -> item instanceof BlockItem blockItem && isPlant(blockItem)
							&& !(blockItem.getBlock() instanceof FlowerPotBlock))
					.map(ItemStack::new)
					.toList();
		}
		return plantableStacks;
	}

	public static double plantRenderScale(Block block) {
		if (block == Blocks.AZALEA || block == Blocks.FLOWERING_AZALEA) {
			return 0.6;
		}
		if (block == Blocks.CACTUS) {
			return 0.35;
		}
		return 1.0;
	}

	//? if >=1.21.1
	private static final TagKey<Block> SAPLINGS = TagKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath("minecraft", "saplings"));
	//? if <1.21.1
	//private static final TagKey<Block> SAPLINGS = TagKey.create(Registries.BLOCK, new Identifier("minecraft", "saplings"));

	private static boolean isPlant(BlockItem item) {
		Block block = item.getBlock();

		BlockState state = block.defaultBlockState();
		return state.is(BlockTags.SMALL_FLOWERS)
				|| state.is(SAPLINGS)
				|| block == Blocks.FERN
				|| block == Blocks.DEAD_BUSH
				|| block == Blocks.BROWN_MUSHROOM
				|| block == Blocks.RED_MUSHROOM
				|| block == Blocks.CRIMSON_FUNGUS
				|| block == Blocks.WARPED_FUNGUS
				|| block == Blocks.CRIMSON_ROOTS
				|| block == Blocks.WARPED_ROOTS
				|| block == Blocks.CACTUS
				|| block == Blocks.AZALEA
				|| block == Blocks.FLOWERING_AZALEA
				|| block == Blocks.BAMBOO;
	}

	private static int slotFromHit(BlockState state, BlockPos pos, BlockHitResult hit) {
		Vec3 local = hit.getLocation().subtract(pos.getX(), pos.getY(), pos.getZ());
		double angle = Math.toRadians(yaw(state.getValue(FACING)));
		double dx = local.x - 0.5;
		double dz = local.z - 0.5;
		double x = 0.5 + dx * Math.cos(angle) + dz * Math.sin(angle);
		double z = 0.5 - dx * Math.sin(angle) + dz * Math.cos(angle);
		int best = -1;
		double bestDistance = 0.19 * 0.19;
		for (int i = 0; i < 4; i++) {
			Slot slot = SLOTS[state.getValue(VARIANT)][i];
			double slotDx = x - slot.centerX();
			double slotDz = z - slot.centerZ();
			double distance = slotDx * slotDx + slotDz * slotDz;
			if (distance < bestDistance) {
				bestDistance = distance;
				best = i;
			}
		}
		return best;
	}

	private static void dropPlants(Level level, BlockPos pos) {
		if (level.isClientSide() || !(level.getBlockEntity(pos) instanceof MultiFlowerPotBlockEntity pot)) {
			return;
		}
		for (int i = 0; i < 4; i++) {
			ItemStack stack = pot.getPlant(i);
			if (!stack.isEmpty()) {
				popResource(level, pos, stack);
			}
		}
	}

	//? if <1.21.1 {
	/*@Override
	public void playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
		dropPlants(level, pos);
		super.playerWillDestroy(level, pos, state, player);
	}
	*///?}
	//? if >=1.21.1 {
	@Override
	public BlockState playerWillDestroy(Level level, BlockPos pos, BlockState state, Player player) {
		dropPlants(level, pos);
		return super.playerWillDestroy(level, pos, state, player);
	}
	//?}
}
//?}
