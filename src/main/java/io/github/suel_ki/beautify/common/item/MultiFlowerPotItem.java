//? if >=1.20.1 {
package io.github.suel_ki.beautify.common.item;

import io.github.suel_ki.beautify.client.util.ClientKeys;
import io.github.suel_ki.beautify.common.block.MultiFlowerPotBlock;
import io.github.suel_ki.beautify.common.tooltip.PlantableItemStackTooltip;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;

//? if <1.21.11
//import io.github.suel_ki.beautify.client.tooltip.LegacyTooltips;
//? if >=1.21.11
import net.minecraft.ChatFormatting;
//? if >=1.21.11
import java.util.function.Consumer;
//? if >=1.21.11
import net.minecraft.world.item.component.TooltipDisplay;

public class MultiFlowerPotItem extends BlockItem {

	public MultiFlowerPotItem(Block block, Properties properties) {
		super(block, properties);
	}

	//? if <1.21.1 {
	/*@Override
	public void appendHoverText(ItemStack stack, Level level, List<Component> tooltip, TooltipFlag flag) {
		LegacyTooltips.shiftAndPlantList(tooltip, "tooltip.beautify.multi_flower_pot");
	}
	*///?}
	//? if >=1.21.1 && <1.21.11 {
	/*@Override
	public void appendHoverText(ItemStack stack, Item.TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		LegacyTooltips.shiftAndPlantList(tooltip, "tooltip.beautify.multi_flower_pot");
	}
	*///?}
	//? if >=1.21.11 {
	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> consumer, TooltipFlag flag) {
		if (!ClientKeys.hasShiftDown() && !ClientKeys.hasControlDown()) {
			consumer.accept(Component.translatable("tooltip.beautify.shift").withStyle(ChatFormatting.YELLOW));
			consumer.accept(Component.translatable("tooltip.beautify.plantlist").withStyle(ChatFormatting.YELLOW));
		}
		if (ClientKeys.hasShiftDown()) {
			consumer.accept(Component.translatable("tooltip.beautify.multi_flower_pot").withStyle(ChatFormatting.GRAY));
		}
	}
	//?}

	@Override
	public Optional<TooltipComponent> getTooltipImage(@NotNull ItemStack stack) {
		if (ClientKeys.hasControlDown()) {
			List<ItemStack> plants = MultiFlowerPotBlock.plantableStacks();
			if (!plants.isEmpty()) {
				return Optional.of(new PlantableItemStackTooltip(plants));
			}
		}
		return super.getTooltipImage(stack);
	}
}
//?}
