package io.github.suel_ki.beautify.common.block;

import io.github.suel_ki.beautify.client.tooltip.LegacyTooltips;


import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.Arrays;
import java.util.List;

//? if >=1.21.11 {
import com.mojang.serialization.Codec;
import io.github.suel_ki.beautify.client.tooltip.TooltipLore;
import io.github.suel_ki.beautify.common.tooltip.BlockTooltip;
import io.github.suel_ki.beautify.core.init.ComponentInit;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.level.redstone.Orientation;
import org.jetbrains.annotations.Nullable;
//?}

//? if >=1.21.1 && <1.21.11
//import net.minecraft.world.ItemInteractionResult;

//? if <1.21.11 {
/*import net.minecraft.ChatFormatting;
import net.minecraft.world.item.TooltipFlag;
*///?}

//? if >=1.21.11 {
public class HangingPot extends LanternBlock implements BlockTooltip {
//?} else {
//public class HangingPot extends LanternBlock {
//?}
	public static final List<Item> VALID_FLOWERS = Arrays.asList(Items.AIR, Items.ROSE_BUSH, Items.LILAC,
			Items.BLUE_ORCHID, Items.VINE, Items.SUNFLOWER, Items.PEONY, Items.AZURE_BLUET, Items.RED_TULIP,
			Items.ORANGE_TULIP, Items.WHITE_TULIP, Items.PINK_TULIP, Items.ALLIUM, Items.DANDELION, Items.POPPY,
			Items.GLOW_LICHEN, Items.OXEYE_DAISY, Items.LILY_OF_THE_VALLEY, Items.CORNFLOWER, Items.WEEPING_VINES,
			//? if >=1.21.1
			Items.TWISTING_VINES, Items.WITHER_ROSE, Items.GLOW_BERRIES, Items.SWEET_BERRIES, Items.SHORT_GRASS, Items.FERN);
			//? if <1.21.1
			//Items.TWISTING_VINES, Items.WITHER_ROSE, Items.GLOW_BERRIES, Items.SWEET_BERRIES, Items.GRASS, Items.FERN);


	// POTFLOWER indicates which index of the flowers List below is active
	public static final IntegerProperty POTFLOWER = IntegerProperty.create("potflower", 0, VALID_FLOWERS.size() - 1);

	public static final List<String> POT_MODELS = List.of(
			"base/hanging_pot",
			"rose/rose_pot",
			"lilac/lilac_pot",
			"orchid/orchid_pot",
			"vine/vine_pot",
			"sunflower/sunflower_pot",
			"peony/peony_pot",
			"azure_bluet/azure_bluet_pot",
			"red_tulip/red_tulip_pot",
			"orange_tulip/orange_tulip_pot",
			"white_tulip/white_tulip_pot",
			"pink_tulip/pink_tulip_pot",
			"allium/allium_pot",
			"dandelion/dandelion_pot",
			"poppy/poppy_pot",
			"glow_lichen/glow_lichen_pot",
			"oxeye_daisy/oxeye_daisy_pot",
			"lily/lily_pot",
			"cornflower/cornflower_pot",
			"weeping_vines/weeping_vines_pot",
			"twisting_vines/twisting_vines_pot",
			"wither_rose/wither_rose_pot",
			"glowberry/glowberry_pot",
			"berry/berry_pot",
			"grass/grass_pot",
			"fern/fern_pot"
	);

	public static String potModel(int potflower, boolean hanging, boolean grown) {
		if (potflower == 0) {
			return "block/pots/base/" + (hanging ? "hanging_pot" : "standing_pot");
		}
		String entry = POT_MODELS.get(potflower);
		String folder = entry.substring(0, entry.indexOf('/'));
		String name = entry.substring(entry.indexOf('/') + 1);
		return "block/pots/" + folder + "/" + (hanging ? name : "standing_" + name) + (grown ? "_grown" : "");
	}

	public static final BooleanProperty GROWN = BooleanProperty.create("grown");

	public HangingPot(Properties properties) {
		super(properties);
		this.registerDefaultState(this.defaultBlockState().setValue(POTFLOWER, 0).setValue(GROWN, false));
	}

	// hitbox for the HangingPot
	private static final VoxelShape HANGING_SHAPE = Shapes.or(box(5, 0, 5, 11, 4, 11),
			box(3.75, 4, 3.75, 12.25, 8, 12.25), box(5, 8, 5, 11, 16, 11));
	private static final VoxelShape STANDING_SHAPE = Shapes.or(box(5, 0, 5, 11, 5, 11), box(4, 5, 4, 12, 8, 12));

	public List<Item> getValidFlowers() {
		return VALID_FLOWERS;
	}

	@Override
	public VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
		return state.getValue(HANGING) ? HANGING_SHAPE : STANDING_SHAPE;
	}

	//? if >=1.21.11 {
	@Override
	public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block,
								@Nullable Orientation orientation, boolean bool) {
		//if the plant is grown long
		if (state.getValue(GROWN)) {
			//if the neighbour is a model that clips into the pot
			if (!level.getBlockState(pos.below()).isAir()
					&& level.getBlockState(pos.below()).isFaceSturdy(level, pos.below(), Direction.UP)) {
				//make pot ungrown again
				level.setBlock(pos, state.setValue(GROWN, false), 3);
				//spawn item from plant inside
				ItemEntity Item = new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(),
						new ItemStack(VALID_FLOWERS.get(state.getValue(POTFLOWER))));
				level.addFreshEntity(Item);
				//play breaking sound
				level.playSound(null, pos, SoundEvents.HANGING_ROOTS_BREAK, SoundSource.BLOCKS, 1, 1);
			}
		}
		super.neighborChanged(state, level, pos, block, orientation, bool);
	}
//?} else {
	/*//? if <1.21.1
	//@SuppressWarnings("deprecation")
	@Override
	public void neighborChanged(BlockState state, Level level, BlockPos pos, Block block,
			BlockPos blockPos, boolean bool) {
		//if the plant is grown long
		if (state.getValue(GROWN)) {
			//if the neighbour is a model that clips into the pot
			if (blockPos.equals(pos.below())
					&& level.getBlockState(pos.below()).isFaceSturdy(level, pos.below(), Direction.UP)) {
				//make pot ungrown again
				level.setBlock(pos, state.setValue(GROWN, false), 3);
				//spawn item from plant inside
				ItemEntity Item = new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(),
						new ItemStack(VALID_FLOWERS.get(state.getValue(POTFLOWER))));
				level.addFreshEntity(Item);
				//play breaking sound
				level.playSound(null, pos, SoundEvents.HANGING_ROOTS_BREAK, SoundSource.BLOCKS, 1, 1);
			}
		}
		super.neighborChanged(state, level, pos, block, blockPos, bool);
	}

*///?}

	//? if >=1.21.11 {
	@Override
	public InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand,
									   BlockHitResult result) {

		if (!level.isClientSide()) {
			ItemStack playerStack = player.getItemInHand(hand); // saving ItemStack

			// growing plant
			// block below must not be sturdy to prevent clipping models
			if (playerStack.getItem().equals(Items.BONE_MEAL) && state.getValue(POTFLOWER) != 0
					&& !level.getBlockState(pos.below()).isFaceSturdy(level, pos.below(), Direction.UP)) {
				if (!state.getValue(GROWN)) {
					level.levelEvent(1505, result.getBlockPos(), 0);
					//level.playSound(null, pos, SoundEvents.BONE_MEAL_USE, SoundSource.BLOCKS, 1, 1);
					if (!player.getAbilities().instabuild) {
						playerStack.shrink(1);
					}
					level.setBlock(pos, state.setValue(GROWN, true), 3);
					return InteractionResult.SUCCESS;
				}
			}

			// when plant is grown -> using shears cuts it down
			if (playerStack.getItem() instanceof ShearsItem && state.getValue(POTFLOWER) != 0
					&& state.getValue(GROWN)) {
				level.playSound(null, pos, SoundEvents.SHEEP_SHEAR, SoundSource.BLOCKS, 1, 1);
				//? if >=26.3
				player.swing(hand, net.minecraft.world.item.component.SwingAnimation.DEFAULT, true);
				//? if <26.3
				//player.swing(hand, true);
				level.setBlock(pos, state.setValue(GROWN, false), 3);
				ItemEntity Item = new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(),
						new ItemStack(VALID_FLOWERS.get(state.getValue(POTFLOWER))));
				level.addFreshEntity(Item);
				return InteractionResult.SUCCESS;
			}

			// if there is a flower
			if (state.getValue(POTFLOWER) != 0) {
				// giving flower and clearing pot if hand empty
				if (playerStack.isEmpty()) {
					player.setItemInHand(hand, new ItemStack(VALID_FLOWERS.get(state.getValue(POTFLOWER)),
							state.getValue(GROWN) ? 2 : 1)); // giving 1 or 2 of plant (grown or not)
					level.setBlock(pos, state.setValue(POTFLOWER, 0).setValue(GROWN, false), 3); // emptying the pot
					level.playSound(null, pos, SoundEvents.COMPOSTER_READY, SoundSource.BLOCKS, 1, 1);
					//? if >=26.3
					player.swing(hand, net.minecraft.world.item.component.SwingAnimation.DEFAULT, true);
					//? if <26.3
					//player.swing(hand, true);
					return InteractionResult.SUCCESS;
				} else if (playerStack.is(VALID_FLOWERS.get(state.getValue(POTFLOWER)))
						&& playerStack.getCount() < playerStack.getMaxStackSize()) {
					playerStack.grow(state.getValue(GROWN) ? 2 : 1); // giving 1 or 2 of plant (grown or not)
					level.setBlock(pos, state.setValue(POTFLOWER, 0).setValue(GROWN, false), 3);
					level.playSound(null, pos, SoundEvents.AZALEA_LEAVES_BREAK, SoundSource.BLOCKS, 1, 1);
					//? if >=26.3
					player.swing(hand, net.minecraft.world.item.component.SwingAnimation.DEFAULT, true);
					//? if <26.3
					//player.swing(hand, true);
					return InteractionResult.SUCCESS;
				}

				// else just return
			} else { // if there is no flower

				// checks if the flower in hand matches the available types
				for (Item flower : VALID_FLOWERS) {
					if (playerStack.getItem().equals(flower)) {
						level.setBlock(pos, state.setValue(POTFLOWER, VALID_FLOWERS.indexOf(flower)), 3);
						player.awardStat(Stats.POT_FLOWER);
						if (!flower.equals(Items.AIR)) {
							level.playSound(null, pos, SoundEvents.AZALEA_PLACE, SoundSource.BLOCKS, 1, 1);
							//? if >=26.3
							player.swing(hand, net.minecraft.world.item.component.SwingAnimation.DEFAULT, true);
							//? if <26.3
							//player.swing(hand, true);
						}
						if (!player.getAbilities().instabuild) {
							playerStack.shrink(1);
						}
						return InteractionResult.SUCCESS;
					}
				}
				// if the flower is not a valid one
			}
			return InteractionResult.CONSUME;
		}
		// end of statement
		return InteractionResult.CONSUME;
	}
//?} else {
	/*//? if >=1.21.1 {
	@Override
	public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand,
										   BlockHitResult result) {

		if (!level.isClientSide()) {
			ItemStack playerStack = player.getItemInHand(hand); // saving ItemStack

			// growing plant
			// block below must not be sturdy to prevent clipping models
			if (playerStack.getItem().equals(Items.BONE_MEAL) && state.getValue(POTFLOWER) != 0
					&& !level.getBlockState(pos.below()).isFaceSturdy(level, pos.below(), Direction.UP)) {
				if (!state.getValue(GROWN)) {
					level.levelEvent(1505, result.getBlockPos(), 0);
					//level.playSound(null, pos, SoundEvents.BONE_MEAL_USE, SoundSource.BLOCKS, 1, 1);
					if (!player.getAbilities().instabuild) {
						playerStack.shrink(1);
					}
					level.setBlock(pos, state.setValue(GROWN, true), 3);
					return ItemInteractionResult.SUCCESS;
				}
			}

			// when plant is grown -> using shears cuts it down
			if (playerStack.getItem() instanceof ShearsItem && state.getValue(POTFLOWER) != 0
					&& state.getValue(GROWN)) {
				level.playSound(null, pos, SoundEvents.SHEEP_SHEAR, SoundSource.BLOCKS, 1, 1);
				player.swing(hand, true);
				level.setBlock(pos, state.setValue(GROWN, false), 3);
				ItemEntity Item = new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(),
						new ItemStack(VALID_FLOWERS.get(state.getValue(POTFLOWER))));
				level.addFreshEntity(Item);
				return ItemInteractionResult.SUCCESS;
			}

			// if there is a flower
			if (state.getValue(POTFLOWER) != 0) {
				// giving flower and clearing pot if hand empty
				if (playerStack.isEmpty()) {
					player.setItemInHand(hand, new ItemStack(VALID_FLOWERS.get(state.getValue(POTFLOWER)),
							state.getValue(GROWN) ? 2 : 1)); // giving 1 or 2 of plant (grown or not)
					level.setBlock(pos, state.setValue(POTFLOWER, 0).setValue(GROWN, false), 3); // emptying the pot
					level.playSound(null, pos, SoundEvents.COMPOSTER_READY, SoundSource.BLOCKS, 1, 1);
					player.swing(hand, true);
					return ItemInteractionResult.SUCCESS;
				} else if (playerStack.is(VALID_FLOWERS.get(state.getValue(POTFLOWER)))
						&& playerStack.getCount() < playerStack.getMaxStackSize()) {
					playerStack.grow(state.getValue(GROWN) ? 2 : 1); // giving 1 or 2 of plant (grown or not)
					level.setBlock(pos, state.setValue(POTFLOWER, 0).setValue(GROWN, false), 3);
					level.playSound(null, pos, SoundEvents.AZALEA_LEAVES_BREAK, SoundSource.BLOCKS, 1, 1);
					player.swing(hand, true);
					return ItemInteractionResult.SUCCESS;
				}

				// else just return
			} else { // if there is no flower

				// checks if the flower in hand matches the available types
				for (Item flower : VALID_FLOWERS) {
					if (playerStack.getItem().equals(flower)) {
						level.setBlock(pos, state.setValue(POTFLOWER, VALID_FLOWERS.indexOf(flower)), 3);
						player.awardStat(Stats.POT_FLOWER);
						if (!flower.equals(Items.AIR)) {
							level.playSound(null, pos, SoundEvents.AZALEA_PLACE, SoundSource.BLOCKS, 1, 1);
							player.swing(hand, true);
						}
						if (!player.getAbilities().instabuild) {
							playerStack.shrink(1);
						}
						return ItemInteractionResult.SUCCESS;
					}
				}
				// if the flower is not a valid one
			}
			return ItemInteractionResult.CONSUME;
		}
		// end of statement
		return ItemInteractionResult.CONSUME;
	}
//?} else {
	/^@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand,
			BlockHitResult result) {

		if (!level.isClientSide() && hand == InteractionHand.MAIN_HAND) {
			ItemStack playerStack = player.getItemInHand(hand); // saving ItemStack

			// growing plant
			// block below must not be sturdy to prevent clipping models
			if (playerStack.getItem().equals(Items.BONE_MEAL) && state.getValue(POTFLOWER) != 0
					/^&& !level.getBlockState(pos.below()).isFaceSturdy(level, pos.below(), Direction.UP)^/) {
				if (!state.getValue(GROWN)) {
					level.levelEvent(1505, result.getBlockPos(), 0);
					//level.playSound(null, pos, SoundEvents.BONE_MEAL_USE, SoundSource.BLOCKS, 1, 1);
					if (!player.getAbilities().instabuild) {
						playerStack.shrink(1);
					}
					level.setBlock(pos, state.setValue(GROWN, true), 3);
					return InteractionResult.SUCCESS;
				}
			}

			// when plant is grown -> using shears cuts it down
			if (playerStack.getItem() instanceof ShearsItem && state.getValue(POTFLOWER) != 0
					&& state.getValue(GROWN)) {
				level.playSound(null, pos, SoundEvents.SHEEP_SHEAR, SoundSource.BLOCKS, 1, 1);
				player.swing(hand, true);
				level.setBlock(pos, state.setValue(GROWN, false), 3);
				ItemEntity Item = new ItemEntity(level, pos.getX(), pos.getY(), pos.getZ(),
						new ItemStack(VALID_FLOWERS.get(state.getValue(POTFLOWER))));
				level.addFreshEntity(Item);
				return InteractionResult.SUCCESS;
			}

			// if there is a flower
			if (state.getValue(POTFLOWER) != 0) {
				// giving flower and clearing pot if hand empty
				if (playerStack.isEmpty()) {
					player.setItemInHand(hand, new ItemStack(VALID_FLOWERS.get(state.getValue(POTFLOWER)),
							state.getValue(GROWN) ? 2 : 1)); // giving 1 or 2 of plant (grown or not)
					level.setBlock(pos, state.setValue(POTFLOWER, 0).setValue(GROWN, false), 3); // emptying the pot
					level.playSound(null, pos, SoundEvents.COMPOSTER_READY, SoundSource.BLOCKS, 1, 1);
					player.swing(hand, true);
					return InteractionResult.SUCCESS;
				} else if (playerStack.is(VALID_FLOWERS.get(state.getValue(POTFLOWER)))
						&& playerStack.getCount() < playerStack.getMaxStackSize()) {
					playerStack.grow(state.getValue(GROWN) ? 2 : 1); // giving 1 or 2 of plant (grown or not)
					level.setBlock(pos, state.setValue(POTFLOWER, 0).setValue(GROWN, false), 3);
					level.playSound(null, pos, SoundEvents.AZALEA_LEAVES_BREAK, SoundSource.BLOCKS, 1, 1);
					player.swing(hand, true);
					return InteractionResult.SUCCESS;
				}

				// else just return
			} else { // if there is no flower

				// checks if the flower in hand matches the available types
				for (Item flower : VALID_FLOWERS) {
					if (playerStack.getItem().equals(flower)) {
						level.setBlock(pos, state.setValue(POTFLOWER, VALID_FLOWERS.indexOf(flower)), 3);
						player.awardStat(Stats.POT_FLOWER);
						if (!flower.equals(Items.AIR)) {
							level.playSound(null, pos, SoundEvents.AZALEA_PLACE, SoundSource.BLOCKS, 1, 1);
							player.swing(hand, true);
						}
						if (!player.getAbilities().instabuild) {
							playerStack.shrink(1);
						}
						return InteractionResult.SUCCESS;
					}
				}
				// if the flower is not a valid one
			}
			return InteractionResult.CONSUME;
		}
		// end of statement
		return InteractionResult.CONSUME;
	}
^///?}
*///?}

	//? if >=1.21.11 {
	@Override
	public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state, boolean bl) {
		return this.isFlowerless(state) ? super.getCloneItemStack(level, pos, state, bl) : new ItemStack(getContent(state));
	}
//?} else {
	/*//? if >=1.21.1 {
	@Override
	public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
		return this.isFlowerless(state) ? super.getCloneItemStack(level, pos, state) : new ItemStack(getContent(state));
	}
//?} else {
	/^@Override
	public ItemStack getCloneItemStack(BlockGetter level, BlockPos pos, BlockState state) {
		return this.isFlowerless(state) ? super.getCloneItemStack(level, pos, state) : new ItemStack(getContent(state));
	}
^///?}
*///?}

	private boolean isFlowerless(BlockState state) {
		return state.getValue(POTFLOWER) == 0;
	}

	public Block getContent(BlockState state) {
		return Block.byItem(VALID_FLOWERS.get(state.getValue(POTFLOWER)));
	}

	// creating Blockstates
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
		super.createBlockStateDefinition(pBuilder);
		pBuilder.add(POTFLOWER, GROWN);
	}

    //? if >=1.21.11 {
    @Override
    public DataComponentType<TooltipLore> getTooltipType() {
        return ComponentInit.get(ComponentInit.HANGING_POT_TOOLTIP);
    }

    @Override
    public TooltipLore getTooltipComponent() {
        return HangingPot.TooltipComponent.INSTANCE;
    }

    public static final class TooltipComponent {
        public static final TooltipLore INSTANCE = TooltipLore.create(
        		List.of(Component.translatable("tooltip.beautify.shift"), Component.translatable("tooltip.beautify.plantlist")),
        		List.of(Component.translatable("tooltip.beautify.hanging_pot.1"), Component.translatable("tooltip.beautify.hanging_pot.2"), Component.translatable("tooltip.beautify.hanging_pot.3")),
        		List.of());
    }

//?} else {
	/*@Override
	//? if >=1.21.1
	public void appendHoverText(ItemStack stack, Item.TooltipContext tooltipContext, List<Component> component, TooltipFlag flag) {
	//? if <1.21.1
	//public void appendHoverText(ItemStack stack, BlockGetter tooltipContext, List<Component> component, TooltipFlag flag) {
		LegacyTooltips.shiftAndPlantList(component, "tooltip.beautify.hanging_pot.1", "tooltip.beautify.hanging_pot.2", "tooltip.beautify.hanging_pot.3");
		//? if >=1.21.1
		super.appendHoverText(stack, tooltipContext, component, flag);
	}
*///?}
}

