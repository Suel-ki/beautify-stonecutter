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
/*public class Blinds extends HorizontalDirectionalBlock implements SimpleWaterloggedBlock {
*///?}
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

	//? if >=1.21.1 && <26.3 {
	/*public static final MapCodec<Blinds> CODEC = simpleCodec(Blinds::new);
	*///?}

	// constructor
	public Blinds(Properties properties) {
		super(properties);
		this.registerDefaultState(this.defaultBlockState().setValue(OPEN, false).setValue(FACING, Direction.NORTH)
				.setValue(HIDDEN, false).setValue(WATERLOGGED, false));
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
		return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite())
				.setValue(OPEN, false).setValue(HIDDEN, false)
				.setValue(WATERLOGGED, context.getLevel().getFluidState(context.getClickedPos()).getType() == Fluids.WATER);
	}

	// creating blockstates
	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(OPEN, FACING, HIDDEN, WATERLOGGED);
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

		// stores last value of blind
		final boolean currentlyOpen = state.getValue(OPEN);

		// if the blinds open from the root
		// search for the position of the topmost blind and set the pos
		if (BeautifyConfig.opensFromRoot()) {
			int step = 1;
			while (sameBlindType(level, pos.above(step), state)) {
				++step;
			}
			pos = pos.above(step - 1);
		}

		{
			// changes clicked blind: open <-> closed
			level.setBlock(pos, state.setValue(OPEN, !currentlyOpen), 3);

			// CODE BELOW IS DISABLED IF SEARCHRADIUS = 0
			// checks for blinds below clicked blind: open <-> closed, hidden=true
			if (BeautifyConfig.searchRadius() > 0) {
				for (int offsetDown = 1; offsetDown <= BeautifyConfig.searchRadius(); ++offsetDown) {
					if (sameBlindType(level, pos.below(offsetDown), state)) {
						switchOpenUpdateHidden(level, pos.below(offsetDown), state, false);
					} else {
						break;
					}
				}
			}

			if (BeautifyConfig.searchRadius() > 0) {
				// FOR BLINDS ON NORTH-SOUTH AXIS
				if (state.getValue(FACING) == Direction.NORTH || state.getValue(FACING) == Direction.SOUTH) {

					// checks blinds east of clicked blind
					for (int offsetEast = 1; offsetEast <= BeautifyConfig.searchRadius() / 2; ++offsetEast) {
						if (sameBlindType(level, pos.east(offsetEast), state)) {
							// changes east blinds: open <-> closed
							level.setBlock(pos.east(offsetEast), state.setValue(OPEN, !currentlyOpen), 3);
							// checks for blinds below east blinds: open <-> closed, hidden=true
							for (int offsetDown = 1; offsetDown <= BeautifyConfig.searchRadius(); ++offsetDown) {
								if (sameBlindType(level, pos.below(offsetDown).east(offsetEast), state)) {
									switchOpenUpdateHidden(level, pos.below(offsetDown).east(offsetEast), state,
											false);
								} else {
									break;
								}
							}
						} else {
							break;
						}
					}

					// checks blinds west of clicked blind
					for (int offsetWest = 1; offsetWest <= BeautifyConfig.searchRadius() / 2; ++offsetWest) {
						if (sameBlindType(level, pos.west(offsetWest), state)) {
							// changes west blinds: open <-> closed
							level.setBlock(pos.west(offsetWest), state.setValue(OPEN, !currentlyOpen), 3);
							// checks for blinds below west blinds: open <-> closed, hidden=true
							for (int offsetDown = 1; offsetDown <= BeautifyConfig.searchRadius(); ++offsetDown) {
								if (sameBlindType(level, pos.below(offsetDown).west(offsetWest), state)) {
									switchOpenUpdateHidden(level, pos.below(offsetDown).west(offsetWest), state,
											false);
								} else {
									break;
								}
							}
						} else {
							break;
						}
					}
				}

				// FOR BLINDS ON EAST-WEST AXIS
				if (state.getValue(FACING) == Direction.EAST || state.getValue(FACING) == Direction.WEST) {

					// checks blinds north of clicked blind
					for (int offsetNorth = 1; offsetNorth <= BeautifyConfig.searchRadius() / 2; ++offsetNorth) {
						if (sameBlindType(level, pos.north(offsetNorth), state)) {
							// changes north blinds: open <-> closed
							level.setBlock(pos.north(offsetNorth), state.setValue(OPEN, !currentlyOpen), 3);
							// checks for blinds below north blinds: open <-> closed, hidden=true
							for (int offsetDown = 1; offsetDown <= BeautifyConfig.searchRadius(); ++offsetDown) {
								if (sameBlindType(level, pos.below(offsetDown).north(offsetNorth), state)) {
									switchOpenUpdateHidden(level, pos.below(offsetDown).north(offsetNorth),
											state, false);
								} else {
									break;
								}
							}
						} else {
							break;
						}
					}

					// checks blinds south of clicked blind
					for (int offsetSouth = 1; offsetSouth <= BeautifyConfig.searchRadius() / 2; ++offsetSouth) {
						if (sameBlindType(level, pos.south(offsetSouth), state)) {
							// changes south blinds: open <-> closed
							level.setBlock(pos.south(offsetSouth), state.setValue(OPEN, !currentlyOpen), 3);
							// checks for blinds below south blinds: open <-> closed, hidden=true
							for (int offsetDown = 1; offsetDown <= BeautifyConfig.searchRadius(); ++offsetDown) {
								if (sameBlindType(level, pos.below(offsetDown).south(offsetSouth), state)) {
									switchOpenUpdateHidden(level, pos.below(offsetDown).south(offsetSouth),
											state, false);
								} else {
									break;
								}
							}
						} else {
							break;
						}
					}
				}
				level.playSound(null, pos,
						currentlyOpen ? SoundInit.get(SoundInit.BLINDS_CLOSE) : SoundInit.get(SoundInit.BLINDS_OPEN),
						SoundSource.BLOCKS, 1, 1);
				return InteractionResult.SUCCESS;
			}
		}
		return InteractionResult.SUCCESS;
	}
	@Override
	public InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
										   Player player, InteractionHand hand, BlockHitResult hit) {
		useWithoutItem(state, level, pos, player, hit);
		return InteractionResult.SUCCESS;
	}

//?} else {
	/*//? if >=1.21.1 {
	@Override
	public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos,
										Player player, InteractionHand hand, BlockHitResult hit) {
		toggle(state, level, pos);
		return ItemInteractionResult.SUCCESS;
	}
	//?} else {
	/^@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand,
			BlockHitResult result) {
        if (level.isClientSide()) {
            return InteractionResult.SUCCESS;
        }

        // stores last value of blind
        final boolean currentlyOpen = state.getValue(OPEN);

        // if the blinds open from the root
        // search for the position of the topmost blind and set the pos
        if (BeautifyConfig.opensFromRoot()) {
            int step = 1;
            while (sameBlindType(level, pos.above(step), state)) {
                ++step;
            }
            pos = pos.above(step - 1);
        }

        {
            // changes clicked blind: open <-> closed
            level.setBlock(pos, state.setValue(OPEN, !currentlyOpen), 3);

            // CODE BELOW IS DISABLED IF SEARCHRADIUS = 0
            // checks for blinds below clicked blind: open <-> closed, hidden=true
            if (BeautifyConfig.searchRadius() > 0) {
                for (int offsetDown = 1; offsetDown <= BeautifyConfig.searchRadius(); ++offsetDown) {
                    if (sameBlindType(level, pos.below(offsetDown), state)) {
                        switchOpenUpdateHidden(level, pos.below(offsetDown), state, false);
                    } else {
                        break;
                    }
                }
            }

            if (BeautifyConfig.searchRadius() > 0) {
                // FOR BLINDS ON NORTH-SOUTH AXIS
                if (state.getValue(FACING) == Direction.NORTH || state.getValue(FACING) == Direction.SOUTH) {

                    // checks blinds east of clicked blind
                    for (int offsetEast = 1; offsetEast <= BeautifyConfig.searchRadius() / 2; ++offsetEast) {
                        if (sameBlindType(level, pos.east(offsetEast), state)) {
                            // changes east blinds: open <-> closed
                            level.setBlock(pos.east(offsetEast), state.setValue(OPEN, !currentlyOpen), 3);
                            // checks for blinds below east blinds: open <-> closed, hidden=true
                            for (int offsetDown = 1; offsetDown <= BeautifyConfig.searchRadius(); ++offsetDown) {
                                if (sameBlindType(level, pos.below(offsetDown).east(offsetEast), state)) {
                                    switchOpenUpdateHidden(level, pos.below(offsetDown).east(offsetEast), state,
                                            false);
                                } else {
                                    break;
                                }
                            }
                        } else {
                            break;
                        }
                    }

                    // checks blinds west of clicked blind
                    for (int offsetWest = 1; offsetWest <= BeautifyConfig.searchRadius() / 2; ++offsetWest) {
                        if (sameBlindType(level, pos.west(offsetWest), state)) {
                            // changes west blinds: open <-> closed
                            level.setBlock(pos.west(offsetWest), state.setValue(OPEN, !currentlyOpen), 3);
                            // checks for blinds below west blinds: open <-> closed, hidden=true
                            for (int offsetDown = 1; offsetDown <= BeautifyConfig.searchRadius(); ++offsetDown) {
                                if (sameBlindType(level, pos.below(offsetDown).west(offsetWest), state)) {
                                    switchOpenUpdateHidden(level, pos.below(offsetDown).west(offsetWest), state,
                                            false);
                                } else {
                                    break;
                                }
                            }
                        } else {
                            break;
                        }
                    }
                }

                // FOR BLINDS ON EAST-WEST AXIS
                if (state.getValue(FACING) == Direction.EAST || state.getValue(FACING) == Direction.WEST) {

                    // checks blinds north of clicked blind
                    for (int offsetNorth = 1; offsetNorth <= BeautifyConfig.searchRadius() / 2; ++offsetNorth) {
                        if (sameBlindType(level, pos.north(offsetNorth), state)) {
                            // changes north blinds: open <-> closed
                            level.setBlock(pos.north(offsetNorth), state.setValue(OPEN, !currentlyOpen), 3);
                            // checks for blinds below north blinds: open <-> closed, hidden=true
                            for (int offsetDown = 1; offsetDown <= BeautifyConfig.searchRadius(); ++offsetDown) {
                                if (sameBlindType(level, pos.below(offsetDown).north(offsetNorth), state)) {
                                    switchOpenUpdateHidden(level, pos.below(offsetDown).north(offsetNorth),
                                            state, false);
                                } else {
                                    break;
                                }
                            }
                        } else {
                            break;
                        }
                    }

                    // checks blinds south of clicked blind
                    for (int offsetSouth = 1; offsetSouth <= BeautifyConfig.searchRadius() / 2; ++offsetSouth) {
                        if (sameBlindType(level, pos.south(offsetSouth), state)) {
                            // changes south blinds: open <-> closed
                            level.setBlock(pos.south(offsetSouth), state.setValue(OPEN, !currentlyOpen), 3);
                            // checks for blinds below south blinds: open <-> closed, hidden=true
                            for (int offsetDown = 1; offsetDown <= BeautifyConfig.searchRadius(); ++offsetDown) {
                                if (sameBlindType(level, pos.below(offsetDown).south(offsetSouth), state)) {
                                    switchOpenUpdateHidden(level, pos.below(offsetDown).south(offsetSouth),
                                            state, false);
                                } else {
                                    break;
                                }
                            }
                        } else {
                            break;
                        }
                    }
                }
                level.playSound(null, pos,
                        currentlyOpen ? SoundInit.get(SoundInit.BLINDS_CLOSE) : SoundInit.get(SoundInit.BLINDS_OPEN),
                        SoundSource.BLOCKS, 1, 1);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.CONSUME;
    }
	^///?}
*///?}

	//? if <1.21.11 {
	/*private InteractionResult toggle(BlockState state, Level level, BlockPos pos) {
		if (level.isClientSide()) {
			return InteractionResult.SUCCESS;
		}

		// stores last value of blind
		final boolean currentlyOpen = state.getValue(OPEN);

		// if the blinds open from the root
		// search for the position of the topmost blind and set the pos
		if (BeautifyConfig.opensFromRoot()) {
			int step = 1;
			while (sameBlindType(level, pos.above(step), state)) {
				++step;
			}
			pos = pos.above(step - 1);
		}

		// changes clicked blind: open <-> closed
		level.setBlock(pos, state.setValue(OPEN, !currentlyOpen), 3);

		// CODE BELOW IS DISABLED IF SEARCHRADIUS = 0
		// checks for blinds below clicked blind: open <-> closed, hidden=true
		if (BeautifyConfig.searchRadius() > 0) {
			for (int offsetDown = 1; offsetDown <= BeautifyConfig.searchRadius(); ++offsetDown) {
				if (sameBlindType(level, pos.below(offsetDown), state)) {
					switchOpenUpdateHidden(level, pos.below(offsetDown), state, false);
				} else {
					break;
				}
			}
		}

		if (BeautifyConfig.searchRadius() > 0) {
			// FOR BLINDS ON NORTH-SOUTH AXIS
			if (state.getValue(FACING) == Direction.NORTH || state.getValue(FACING) == Direction.SOUTH) {

				// checks blinds east of clicked blind
				for (int offsetEast = 1; offsetEast <= BeautifyConfig.searchRadius() / 2; ++offsetEast) {
					if (sameBlindType(level, pos.east(offsetEast), state)) {
						// changes east blinds: open <-> closed
						level.setBlock(pos.east(offsetEast), state.setValue(OPEN, !currentlyOpen), 3);
						// checks for blinds below east blinds: open <-> closed, hidden=true
						for (int offsetDown = 1; offsetDown <= BeautifyConfig.searchRadius(); ++offsetDown) {
							if (sameBlindType(level, pos.below(offsetDown).east(offsetEast), state)) {
								switchOpenUpdateHidden(level, pos.below(offsetDown).east(offsetEast), state, false);
							} else {
								break;
							}
						}
					} else {
						break;
					}
				}

				// checks blinds west of clicked blind
				for (int offsetWest = 1; offsetWest <= BeautifyConfig.searchRadius() / 2; ++offsetWest) {
					if (sameBlindType(level, pos.west(offsetWest), state)) {
						// changes west blinds: open <-> closed
						level.setBlock(pos.west(offsetWest), state.setValue(OPEN, !currentlyOpen), 3);
						// checks for blinds below west blinds: open <-> closed, hidden=true
						for (int offsetDown = 1; offsetDown <= BeautifyConfig.searchRadius(); ++offsetDown) {
							if (sameBlindType(level, pos.below(offsetDown).west(offsetWest), state)) {
								switchOpenUpdateHidden(level, pos.below(offsetDown).west(offsetWest), state, false);
							} else {
								break;
							}
						}
					} else {
						break;
					}
				}
			}

			// FOR BLINDS ON EAST-WEST AXIS
			if (state.getValue(FACING) == Direction.EAST || state.getValue(FACING) == Direction.WEST) {

				// checks blinds north of clicked blind
				for (int offsetNorth = 1; offsetNorth <= BeautifyConfig.searchRadius() / 2; ++offsetNorth) {
					if (sameBlindType(level, pos.north(offsetNorth), state)) {
						// changes north blinds: open <-> closed
						level.setBlock(pos.north(offsetNorth), state.setValue(OPEN, !currentlyOpen), 3);
						// checks for blinds below north blinds: open <-> closed, hidden=true
						for (int offsetDown = 1; offsetDown <= BeautifyConfig.searchRadius(); ++offsetDown) {
							if (sameBlindType(level, pos.below(offsetDown).north(offsetNorth), state)) {
								switchOpenUpdateHidden(level, pos.below(offsetDown).north(offsetNorth), state, false);
							} else {
								break;
							}
						}
					} else {
						break;
					}
				}

				// checks blinds south of clicked blind
				for (int offsetSouth = 1; offsetSouth <= BeautifyConfig.searchRadius() / 2; ++offsetSouth) {
					if (sameBlindType(level, pos.south(offsetSouth), state)) {
						// changes south blinds: open <-> closed
						level.setBlock(pos.south(offsetSouth), state.setValue(OPEN, !currentlyOpen), 3);
						// checks for blinds below south blinds: open <-> closed, hidden=true
						for (int offsetDown = 1; offsetDown <= BeautifyConfig.searchRadius(); ++offsetDown) {
							if (sameBlindType(level, pos.below(offsetDown).south(offsetSouth), state)) {
								switchOpenUpdateHidden(level, pos.below(offsetDown).south(offsetSouth), state, false);
							} else {
								break;
							}
						}
					} else {
						break;
					}
				}
			}
		}

		level.playSound(null, pos,
				currentlyOpen ? SoundInit.get(SoundInit.BLINDS_CLOSE) : SoundInit.get(SoundInit.BLINDS_OPEN),
				SoundSource.BLOCKS, 1, 1);

		return InteractionResult.CONSUME;
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

	// method for changing the blockstates of blinds
	// if updateOnly is true, only HIDDEN is changed
	// if updateOnly is false, the blind will also open or close

	private void switchOpenUpdateHidden(LevelAccessor level, BlockPos pos, BlockState state, boolean updateOnly) {
		if (updateOnly) {
			level.setBlock(pos, state.setValue(HIDDEN, false), 3);
			return;
		}

		if (!state.getValue(OPEN)) {
			level.setBlock(pos, state.setValue(OPEN, true).setValue(HIDDEN, false), 3);
		} else {
			level.setBlock(pos, state.setValue(OPEN, false).setValue(HIDDEN, true), 3);
		}
	}

	// method to prevent hidden blinds from being unaccessible
	// after root block is destroyed.
	// updates blind below the destroyed block.
	@Override
	public void destroy(LevelAccessor level, BlockPos pos, BlockState state) {
		if (sameBlindType(level, pos.below(), state)) {
			switchOpenUpdateHidden(level, pos.below(), state, true);
		}
	}

	@Override
	//? if >=1.21.11
	public void wasExploded(ServerLevel level, BlockPos pos, Explosion explosion) {
	//? if <1.21.11
	//public void wasExploded(Level level, BlockPos pos, Explosion explosion) {
		BlockState state = level.getBlockState(pos);
		if (sameBlindType(level, pos.below(), state)) {
			switchOpenUpdateHidden(level, pos.below(), state, true);
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
