//? if >=1.21.11 {
package io.github.suel_ki.beautify.common.tooltip;

import io.github.suel_ki.beautify.client.tooltip.TooltipLore;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.world.item.Item;

import java.util.List;

public interface BlockTooltip {
	DataComponentType<TooltipLore> getTooltipType();

	TooltipLore getTooltipComponent();

	default List<Item> getValidFlowers() {
		return List.of();
	}
}
//?}
