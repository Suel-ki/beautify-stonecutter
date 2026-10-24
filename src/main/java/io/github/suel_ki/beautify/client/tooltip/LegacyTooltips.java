package io.github.suel_ki.beautify.client.tooltip;

import io.github.suel_ki.beautify.client.util.ClientKeys;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;

import java.util.List;

public final class LegacyTooltips {

    private LegacyTooltips() {
    }

    public static void shiftOnly(List<Component> lines, String... keys) {
        if (!ClientKeys.hasShiftDown()) {
            lines.add(Component.translatable("tooltip.beautify.shift").withStyle(ChatFormatting.YELLOW));
            return;
        }
        for (String key : keys) {
            lines.add(Component.translatable(key).withStyle(ChatFormatting.GRAY));
        }
    }

    public static void shiftAndPlantList(List<Component> lines, String... keys) {
        if (!ClientKeys.hasShiftDown() && !ClientKeys.hasControlDown()) {
            lines.add(Component.translatable("tooltip.beautify.shift").withStyle(ChatFormatting.YELLOW));
            lines.add(Component.translatable("tooltip.beautify.plantlist").withStyle(ChatFormatting.YELLOW));
        }
        if (ClientKeys.hasShiftDown()) {
            for (String key : keys) {
                lines.add(Component.translatable(key).withStyle(ChatFormatting.GRAY));
            }
        }
    }
}
