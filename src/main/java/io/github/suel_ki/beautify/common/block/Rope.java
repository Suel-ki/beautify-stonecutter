package io.github.suel_ki.beautify.common.block;

import io.github.suel_ki.beautify.client.tooltip.LegacyTooltips;


//? if >=1.21.11 {
import com.mojang.serialization.Codec;
import io.github.suel_ki.beautify.client.tooltip.TooltipLore;
import io.github.suel_ki.beautify.common.tooltip.BlockTooltip;
import io.github.suel_ki.beautify.core.init.ComponentInit;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
//?} else {
/*import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.PushReaction;
*///?}
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.world.level.block.ChainBlock;

import java.util.List;

//? if >=1.21.11 {
public class Rope extends ChainBlock implements BlockTooltip {
//?} else {
/*public class Rope extends ChainBlock {
*///?}
	public Rope(Properties properties) {
		super(properties);
		this.registerDefaultState(this.stateDefinition.any().setValue(WATERLOGGED, Boolean.FALSE)
				.setValue(AXIS, Direction.Axis.Y));
	}

	//? if >=1.21.11 {
	@Override
	public DataComponentType<TooltipLore> getTooltipType() {
		return ComponentInit.get(ComponentInit.ROPE_TOOLTIP);
	}

	@Override
	public TooltipLore getTooltipComponent() {
		return TooltipComponent.INSTANCE;
	}

	public static final class TooltipComponent {
	    public static final TooltipLore INSTANCE = TooltipLore.of("tooltip.beautify.rope.1", "tooltip.beautify.rope.2");
	}
	//?} else {
	/*public PushReaction getPistonPushReaction(BlockState blockState) {
		return PushReaction.DESTROY;
	}

	@Override
	//? if >=1.21.1
	public void appendHoverText(ItemStack stack, Item.TooltipContext tooltipContext, List<Component> component, TooltipFlag flag) {
	//? if <1.21.1
	//public void appendHoverText(ItemStack stack, BlockGetter tooltipContext, List<Component> component, TooltipFlag flag) {
		LegacyTooltips.shiftOnly(component, "tooltip.beautify.rope.1", "tooltip.beautify.rope.2");
		//? if >=1.21.1
		super.appendHoverText(stack, tooltipContext, component, flag);
	}
	*///?}
}
