package io.github.suel_ki.beautify.client.tooltip;

import io.github.suel_ki.beautify.common.tooltip.PlantableItemStackTooltip;
import net.minecraft.ChatFormatting;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.screens.inventory.tooltip.ClientTooltipComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;

import java.util.List;

//? if >=26.1.2
import net.minecraft.client.gui.GuiGraphicsExtractor;

//? if >=1.21.11
import net.minecraft.client.renderer.RenderPipelines;

//? if <26.1.2
//import net.minecraft.client.gui.GuiGraphics;

public class ClientPlantableItemStackTooltip implements ClientTooltipComponent {

    //? if >=26.1.2
    private static final Identifier TEXTURE_LOCATION = Identifier.withDefaultNamespace("container/bundle/slot_background");
    //? if >=1.21.1 && <26.1.2
    //private static final Identifier TEXTURE_LOCATION = Identifier.withDefaultNamespace("container/bundle/slot");
    //? if <1.21.1
    //private static final Identifier TEXTURE_LOCATION = new Identifier("textures/gui/container/bundle.png");

    //? if >=26.1.2
    private static final int SLOT_SIZE = 24;
    //? if <26.1.2
    //private static final int SLOT_SIZE = 18;

    private static final Component TEXT = Component.translatable("tooltip.beautify.plantable").withStyle(ChatFormatting.GREEN);

    private final int columns;
    private final List<ItemStack> plants;

    public ClientPlantableItemStackTooltip(PlantableItemStackTooltip tooltip) {
        this.columns = tooltip.getColumns();
        this.plants = tooltip.plants();
    }

    @Nullable
    public static ClientTooltipComponent get(TooltipComponent component) {
        return component instanceof PlantableItemStackTooltip tooltip ? new ClientPlantableItemStackTooltip(tooltip) : null;
    }

    //? if >=26.1.2 {
    @Override
    public void extractImage(Font font, int tooltipX, int tooltipY, int k, int l, GuiGraphicsExtractor graphics) {
        paintSlots(font, tooltipX, tooltipY, (x, y, plant) -> {
            graphics.blitSprite(RenderPipelines.GUI_TEXTURED, TEXTURE_LOCATION, x - 1, y - 1, SLOT_SIZE, SLOT_SIZE);
            graphics.item(plant, x + 3, y + 3);
            graphics.itemDecorations(font, plant, x + 3, y + 3);
        });
        graphics.text(font, TEXT, tooltipX, tooltipY, -1);
    }
    //?} else {
    /*@Override
    public void renderImage(Font font, int tooltipX, int tooltipY,
            //? if >=1.21.11
            int k, int l,
            GuiGraphics graphics) {
        paintSlots(font, tooltipX, tooltipY, (x, y, plant) -> {
            //? if >=1.21.11
            graphics.blitSprite(RenderPipelines.GUI_TEXTURED, TEXTURE_LOCATION, x - 1, y - 1, 0, 18, 20);
            //? if >=1.21.1 && <1.21.11
            //graphics.blitSprite(TEXTURE_LOCATION, x - 1, y - 1, 0, 18, 20);
            //? if <1.21.1
            //graphics.blit(TEXTURE_LOCATION, x - 1, y - 1, 0, 0.f, 0.f, 18, 18, 128, 128);
            graphics.renderItem(plant, x, y);
            graphics.renderItemDecorations(font, plant, x, y);
        });
        //? if >=1.21.11
        int color = -1;
        //? if <1.21.11
        //int color = 0;
        graphics.drawString(font, TEXT, tooltipX, tooltipY, color);
    }
    *///?}

    @Override
    public int getHeight(
            //? if >=1.21.11
            Font font
    ) {
        int rows = (this.plants.size() + this.columns - 1) / this.columns;
        return rows * SLOT_SIZE + 15;
    }

    @Override
    public int getWidth(Font font) {
        return Math.max(this.columns * SLOT_SIZE, font.width(TEXT));
    }

    private void paintSlots(Font font, int tooltipX, int tooltipY, SlotPainter painter) {
        int x = tooltipX;
        int y = tooltipY + font.lineHeight + 3;
        for (ItemStack plant : this.plants) {
            painter.paint(x, y, plant);
            x += SLOT_SIZE;
            if (x >= tooltipX + SLOT_SIZE * this.columns) {
                x = tooltipX;
                y += SLOT_SIZE;
            }
        }
    }

    @FunctionalInterface
    private interface SlotPainter {
        void paint(int x, int y, ItemStack plant);
    }
}
