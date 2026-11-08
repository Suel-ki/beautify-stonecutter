//? if >=26.1.2 && <26.2 {
/*package io.github.suel_ki.beautify.compat.every_compat;

import io.github.suel_ki.beautify.common.block.Blinds;
import io.github.suel_ki.beautify.common.item.TooltipBlockItems;
import io.github.suel_ki.beautify.common.block.PictureFrame;
import io.github.suel_ki.beautify.common.block.Trellis;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.modules.EveryCompatModule;
import net.mehvahdjukaar.moonlight.api.platform.PlatHelper;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;

import java.util.function.Supplier;

import static net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodChildKeys.SLAB;

public class BeautifyModuleModern extends EveryCompatModule {

    public final SimpleEntrySet<WoodType, Block> trellis;
    public final SimpleEntrySet<WoodType, Block> blinds;
    public final SimpleEntrySet<WoodType, Block> pictureFrames;

    public BeautifyModuleModern() {
        this("beautify");
    }

    public BeautifyModuleModern(String modId) {
        super(modId, "bd", modId);
		setProviderId(PlatHelper.getPlatform().isFabric() ? "Beautify Refabricated" : "Beautify Refoxed");

        Supplier<CreativeModeTab> tab = getModTab("group");

        trellis = SimpleEntrySet.builder(WoodType.class, "trellis",
                        getModBlock("oak_trellis"), () -> VanillaWoodTypes.OAK,
                        (woodType, properties) -> new Trellis(properties
                                .strength(0.3F, 0.3F)
                                .sound(SoundType.BAMBOO).noOcclusion())
                )
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTag(BlockTags.CLIMBABLE, Registries.BLOCK)
                .setTab(tab)
                .defaultRecipe()
                .excludedWoodTypes()
                .includeModelsBlock(true, modRes("block/oak_trellis"))
                .addCustomItem((type, block, properties) -> TooltipBlockItems.create(block, properties))
                .build();
        this.addEntry(trellis);

        blinds = SimpleEntrySet.builder(WoodType.class, "blinds",
                        getModBlock("oak_blinds"), () -> VanillaWoodTypes.OAK,
                        (woodType, properties) -> new Blinds(properties
                                .noOcclusion().strength(0.4F, 0.4F)
                                .sound(SoundType.WOOD))
                )
                .requiresChildren(SLAB)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .includeModelsBlock(true, modRes("block/oak_blinds"))
                .addCustomItem((type, block, properties) -> TooltipBlockItems.create(block, properties))
                .setTab(tab)
                .defaultRecipe()
                .build();
        this.addEntry(blinds);

        pictureFrames = SimpleEntrySet.builder(WoodType.class, "picture_frame",
                        getModBlock("oak_picture_frame"), () -> VanillaWoodTypes.OAK,
                        (woodType, properties) -> new PictureFrame(properties
                                .noOcclusion().strength(0.1F, 0.1F)
                                .sound(SoundType.WOOD))
                )
                .requiresChildren(SLAB)
                .addTexture(modRes("block/oak_frame_texture"))
                .includeModelsBlock(true, modRes("block/oak_picture_frame"))
                .addCustomItem((type, block, properties) -> TooltipBlockItems.create(block, properties))
                .setTab(tab)
                .defaultRecipe()
                .build();
        this.addEntry(pictureFrames);
    }
}
*///?}
