//? if >=1.21.11 {
package io.github.suel_ki.beautify.common.item;

import io.github.suel_ki.beautify.client.util.ClientKeys;
import io.github.suel_ki.beautify.common.tooltip.BlockTooltip;
import io.github.suel_ki.beautify.common.tooltip.PlantableItemStackTooltip;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class TooltipBlockItems {

	public static BlockItem create(Block block, Item.Properties properties) {
		if (block instanceof BlockTooltip hasTooltip) {
			properties = properties.component(hasTooltip.getTooltipType(), hasTooltip.getTooltipComponent());
		}
		Item.Properties finalProperties = properties;
		return new BlockItem(block, finalProperties) {
			@Override
			public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> consumer, TooltipFlag flag) {
				if (block instanceof BlockTooltip hasTooltip) {
					stack.addToTooltip(hasTooltip.getTooltipType(), context, display, consumer, flag);
				}
			}

			@Override
			public Optional<TooltipComponent> getTooltipImage(@NotNull ItemStack stack) {
				if (block instanceof BlockTooltip hasTooltip && ClientKeys.hasControlDown()) {
					List<ItemStack> plants = hasTooltip.getValidFlowers().stream()
							.filter(item -> item != Items.AIR)
							.map(ItemStack::new)
							.toList();
					if (!plants.isEmpty()) {
						return Optional.of(new PlantableItemStackTooltip(plants));
					}
				}
				return super.getTooltipImage(stack);
			}
		};
	}

}
//?}
