package io.github.suel_ki.beautify.common.block;

import io.github.suel_ki.beautify.client.tooltip.LegacyTooltips;


import com.mojang.serialization.Codec;
import io.github.suel_ki.beautify.particle.ParticleInit;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.LanternBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.BlockHitResult;

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

//? if >=1.21.1 && <1.21.11 {
/*import net.minecraft.world.ItemInteractionResult;
*///?}

//? if <1.21.11 {
/*import net.minecraft.ChatFormatting;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
*///?}

//? if >=1.21.11 {
public class LampJar extends LanternBlock implements BlockTooltip {
//?} else {
/*public class LampJar extends LanternBlock {
*///?}
	private static final int maxLevel = 15;
	public static final IntegerProperty FILL_LEVEL = IntegerProperty.create("fill_level", 0, maxLevel);

	public LampJar(Properties p_153465_) {
		super(p_153465_);
		this.registerDefaultState(this.defaultBlockState().setValue(FILL_LEVEL, 0));
	}

	// Fill
	//? if >=1.21.11 {
	@Override
	public InteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand,
										   BlockHitResult result) {
		if (!level.isClientSide()) {

			ItemStack playerStack = player.getItemInHand(hand);

			final int increase = 5;
			final int currentLevel = state.getValue(FILL_LEVEL);

			// decreasing
			if (playerStack.isEmpty() && currentLevel > 0) {
				player.setItemInHand(hand, new ItemStack(Items.GLOWSTONE_DUST, currentLevel / increase));
				level.setBlock(pos, state.setValue(FILL_LEVEL, 0), 3);
				level.playSound(null, pos, SoundEvents.AMETHYST_CLUSTER_BREAK, SoundSource.BLOCKS, 0.5F,
						0.5f);
				return InteractionResult.SUCCESS;
			}

			// increasing
			if (playerStack.is(Items.GLOWSTONE_DUST) && currentLevel + increase <= maxLevel) {
				//? if >=1.21.2 {
				playerStack.consume(1, player);
				//?} else {
				/*if (!player.isCreative()) {
					playerStack.shrink(1);
				}*///?}
				level.setBlock(pos, state.setValue(FILL_LEVEL, currentLevel + increase), 3);
				level.playSound(null, pos, SoundEvents.AMETHYST_BLOCK_HIT, SoundSource.BLOCKS, 0.5F, 0.5f);
				return InteractionResult.SUCCESS;
			}
		}
		return InteractionResult.SUCCESS;
	}
//?} else {
	/*//? if >=1.21.1 {
	@Override
	public ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand,
										   BlockHitResult result) {
		if (!level.isClientSide()) {

			ItemStack playerStack = player.getItemInHand(hand);

			final int increase = 5;
			final int currentLevel = state.getValue(FILL_LEVEL);

			// decreasing
			if (playerStack.isEmpty() && currentLevel > 0) {
				player.setItemInHand(hand, new ItemStack(Items.GLOWSTONE_DUST, currentLevel / increase));
				level.setBlock(pos, state.setValue(FILL_LEVEL, 0), 3);
				level.playSound(null, pos, SoundEvents.AMETHYST_CLUSTER_BREAK, SoundSource.BLOCKS, 0.5F,
						0.5f);
				return ItemInteractionResult.SUCCESS;
			}

			// increasing
			if (playerStack.is(Items.GLOWSTONE_DUST) && currentLevel + increase <= maxLevel) {
				if (!player.isCreative()) {
					playerStack.shrink(1);
				}
				level.setBlock(pos, state.setValue(FILL_LEVEL, currentLevel + increase), 3);
				level.playSound(null, pos, SoundEvents.AMETHYST_BLOCK_HIT, SoundSource.BLOCKS, 0.5F, 0.5f);
				return ItemInteractionResult.SUCCESS;
			}
		}
		return ItemInteractionResult.SUCCESS;
	}
//?} else {
	/^@Override
	public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand,
			BlockHitResult result) {
		if (!level.isClientSide() && hand == InteractionHand.MAIN_HAND) {

			ItemStack playerStack = player.getItemInHand(hand);

			final int increase = 5;
			final int currentLevel = state.getValue(FILL_LEVEL);

			// decreasing
			if (playerStack.isEmpty() && currentLevel > 0) {
				player.setItemInHand(hand, new ItemStack(Items.GLOWSTONE_DUST, currentLevel / increase));
				level.setBlock(pos, state.setValue(FILL_LEVEL, 0), 3);
				level.playSound(null, pos, SoundEvents.AMETHYST_CLUSTER_BREAK, SoundSource.BLOCKS, 0.5F,
						0.5f);
				return InteractionResult.SUCCESS;
			}

			// increasing
			if (playerStack.is(Items.GLOWSTONE_DUST) && currentLevel + increase <= maxLevel) {
				if (!player.isCreative()) {
					playerStack.shrink(1);
				}
				level.setBlock(pos, state.setValue(FILL_LEVEL, currentLevel + increase), 3);
				level.playSound(null, pos, SoundEvents.AMETHYST_BLOCK_HIT, SoundSource.BLOCKS, 0.5F, 0.5f);
				return InteractionResult.SUCCESS;
			}
		}
		return InteractionResult.SUCCESS;
	}
^///?}
*///?}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FILL_LEVEL);
	}

	//? if fabric {
	@Environment(EnvType.CLIENT)
	//?}
	@Override
	public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource rand) {
		final int particleProbability = 5;

		double posX = (pos.getX() + 0.35) + rand.nextDouble() / 3.5;
		double posY = (pos.getY() + 0.1) + rand.nextDouble() / 3.5;
		double posZ = (pos.getZ() + 0.35) + rand.nextDouble() / 3.5;

		if (state.getValue(FILL_LEVEL) >= 5 && state.getValue(FILL_LEVEL) < 10) {
			if (rand.nextBoolean()) {
				level.addParticle(
						ParticleInit.get(ParticleInit.GLOWESSENCE_PARTICLES), posX, posY, posZ, randomDir(rand), 0.01d, randomDir(rand));
			}
		} else if (state.getValue(FILL_LEVEL) >= 10 && state.getValue(FILL_LEVEL) < 15) {
			if (rand.nextBoolean()) {
				level.addParticle(
						ParticleInit.get(ParticleInit.GLOWESSENCE_PARTICLES), posX, posY, posZ, randomDir(rand), 0.01, randomDir(rand));
			}
		} else if (state.getValue(FILL_LEVEL) == 15) {
			if (rand.nextBoolean()) {
				level.addParticle(
						ParticleInit.get(ParticleInit.GLOWESSENCE_PARTICLES), posX, posY, posZ, randomDir(rand), 0.01, randomDir(rand));
			}
			posX = (pos.getX() + 0.35) + rand.nextDouble() / 3.5;
			posY = (pos.getY() + 0.1) + rand.nextDouble() / 3.5;
			posZ = (pos.getZ() + 0.35) + rand.nextDouble() / 3.5;
			level.addParticle(ParticleInit.get(ParticleInit.GLOWESSENCE_PARTICLES), posX, posY, posZ, randomDir(rand), 0.01, randomDir(rand));
		}

	}

	private static double randomDir(RandomSource rand) {
		return (rand.nextIntBetweenInclusive(0, 2) - 1) * rand.nextFloat() / 34;
	}

    //? if >=1.21.11 {
    @Override
    public DataComponentType<TooltipLore> getTooltipType() {
        return ComponentInit.get(ComponentInit.LAMP_JAR_TOOLTIP);
    }

    @Override
    public TooltipLore getTooltipComponent() {
        return TooltipComponent.INSTANCE;
    }

    public static final class TooltipComponent {
        public static final TooltipLore INSTANCE = TooltipLore.of("tooltip.beautify.lamp_jar.1", "tooltip.beautify.lamp_jar.2", "tooltip.beautify.lamp_jar.3");
    }

//?} else {
	/*@Override
	//? if >=1.21.1
	public void appendHoverText(ItemStack stack, Item.TooltipContext tooltipContext, List<Component> component, TooltipFlag flag) {
	//? if <1.21.1
	//public void appendHoverText(ItemStack stack, BlockGetter tooltipContext, List<Component> component, TooltipFlag flag) {
		LegacyTooltips.shiftOnly(component, "tooltip.beautify.lamp_jar.1", "tooltip.beautify.lamp_jar.2", "tooltip.beautify.lamp_jar.3");
		//? if >=1.21.1
		super.appendHoverText(stack, tooltipContext, component, flag);
	}
*///?}
}
