//? if fabric && <26.1.2 {
/*package io.github.suel_ki.beautify.client.block;

import io.github.suel_ki.beautify.core.init.BlockInit;
//? if >=1.21.11 {
import net.fabricmc.fabric.api.client.rendering.v1.BlockRenderLayerMap;
//?} else {
/^import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
^///?}
//? if >=1.21.11 {
import net.minecraft.client.renderer.chunk.ChunkSectionLayer;
//?} else {
/^import net.minecraft.client.renderer.RenderType;
^///?}

public class ClientBlockInit {

    public static void registerBlockRenderLayer() {
        //? if >=1.21.11 {
        BlockRenderLayerMap.putBlocks(ChunkSectionLayer.CUTOUT,
        //?} else {
        /^BlockRenderLayerMap.INSTANCE.putBlocks(RenderType.cutout(),
        ^///?}
                // BLOCKS
                BlockInit.BOOKSTACK,
                BlockInit.HANGING_POT,
                BlockInit.ROPE,
                BlockInit.BOTANIST_WORKBENCH,

                // LAMPS
                BlockInit.LAMP_JAR,
                BlockInit.LAMP_BAMBOO,
                BlockInit.LAMP_LIGHT_BULB,
                BlockInit.LAMP_CANDELABRA,
                BlockInit.LAMP_CANDELABRA_LIGHT_BLUE,
                BlockInit.LAMP_CANDELABRA_LIGHT_GRAY,
                BlockInit.LAMP_CANDELABRA_BLACK,
                BlockInit.LAMP_CANDELABRA_BLUE,
                BlockInit.LAMP_CANDELABRA_BROWN,
                BlockInit.LAMP_CANDELABRA_CYAN,
                BlockInit.LAMP_CANDELABRA_GRAY,
                BlockInit.LAMP_CANDELABRA_GREEN,
                BlockInit.LAMP_CANDELABRA_LIME,
                BlockInit.LAMP_CANDELABRA_MAGENTA,
                BlockInit.LAMP_CANDELABRA_ORANGE,
                BlockInit.LAMP_CANDELABRA_PINK,
                BlockInit.LAMP_CANDELABRA_PURPLE,
                BlockInit.LAMP_CANDELABRA_RED,
                BlockInit.LAMP_CANDELABRA_WHITE,
                BlockInit.LAMP_CANDELABRA_YELLOW,

                // TRELLIS
                BlockInit.OAK_TRELLIS,
                BlockInit.SPRUCE_TRELLIS,
                BlockInit.BIRCH_TRELLIS,
                BlockInit.JUNGLE_TRELLIS,
                BlockInit.ACACIA_TRELLIS,
                BlockInit.DARK_OAK_TRELLIS,
                BlockInit.MANGROVE_TRELLIS,
                BlockInit.CRIMSON_TRELLIS,
                BlockInit.CHERRY_TRELLIS,
                BlockInit.WARPED_TRELLIS,

                // BLINDS
                BlockInit.SPRUCE_BLINDS,
                BlockInit.DARK_OAK_BLINDS,
                BlockInit.CRIMSON_BLINDS,
                BlockInit.CHERRY_BLINDS,
                BlockInit.ACACIA_BLINDS,
                BlockInit.JUNGLE_BLINDS,
                BlockInit.OAK_BLINDS,
                BlockInit.BIRCH_BLINDS,
                BlockInit.WARPED_BLINDS,
                BlockInit.MANGROVE_BLINDS,
                BlockInit.IRON_BLINDS,

                // FRAMES
                BlockInit.SPRUCE_PICTURE_FRAME,
                BlockInit.DARK_OAK_PICTURE_FRAME,
                BlockInit.CRIMSON_PICTURE_FRAME,
                BlockInit.CHERRY_PICTURE_FRAME,
                BlockInit.ACACIA_PICTURE_FRAME,
                BlockInit.JUNGLE_PICTURE_FRAME,
                BlockInit.OAK_PICTURE_FRAME,
                BlockInit.BIRCH_PICTURE_FRAME,
                BlockInit.WARPED_PICTURE_FRAME,
                BlockInit.MANGROVE_PICTURE_FRAME,
                BlockInit.QUARTZ_PICTURE_FRAME);
    }
}
*///?}

