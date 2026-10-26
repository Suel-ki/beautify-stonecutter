//? if fabric {
package io.github.suel_ki.beautify.core.init;

import io.github.suel_ki.beautify.Beautify;
import io.github.suel_ki.beautify.common.block.BookStack;
import io.github.suel_ki.beautify.common.block.BotanistWorkbench;
import io.github.suel_ki.beautify.common.block.HangingPot;
import io.github.suel_ki.beautify.common.block.LampBamboo;
import io.github.suel_ki.beautify.common.block.LampCandelabra;
import io.github.suel_ki.beautify.common.block.LampJar;
import io.github.suel_ki.beautify.common.block.LampLightBulb;
import io.github.suel_ki.beautify.common.block.Blinds;
import io.github.suel_ki.beautify.common.block.PictureFrame;
import io.github.suel_ki.beautify.common.block.Trellis;
import io.github.suel_ki.beautify.common.block.Rope;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
//? if >=1.21.11
import net.minecraft.core.registries.Registries;
//? if >=1.21.11
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;

import java.util.function.Function;

public class BlockInit {

    // BLOCKS
    public static final BookStack BOOKSTACK = register("bookstack",
            BookStack::new, BlockBehaviour.Properties.of().mapColor(MapColor.NONE)
                    .strength(0.2F, 0.2F).sound(SoundInit.BOOKSTACK_SOUNDS).noOcclusion());

    public static final Rope ROPE = register("rope",
            Rope::new, BlockBehaviour.Properties.of().mapColor(MapColor.NONE)
                    .strength(0.2F, 0.2F).sound(SoundType.WOOL)
                    //? if >=26.3 {
                    .pushReaction(PushReaction.POPPED).noOcclusion());
                    //?} else if >=1.21.11 {
                    //.pushReaction(PushReaction.DESTROY).noOcclusion());
                    //?} else {
                    //.noOcclusion());
                    //?}

    public static final HangingPot HANGING_POT = register("hanging_pot",
            HangingPot::new, BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN)
                    .noOcclusion().strength(0.1f, 0.1f).sound(SoundType.STONE).lightLevel((state) -> {
                        if (state.getValue(HangingPot.POTFLOWER) == 15) {
                            return 7;
                        } else if (state.getValue(HangingPot.POTFLOWER) == 22) {
                            return 14;
                        } else {
                            return 0;
                        }
                    }));

    // trellis
    public static final Trellis OAK_TRELLIS = registerTrellis("oak_trellis");

    public static final Trellis SPRUCE_TRELLIS = registerTrellis("spruce_trellis");

    public static final Trellis BIRCH_TRELLIS = registerTrellis("birch_trellis");

    public static final Trellis JUNGLE_TRELLIS = registerTrellis("jungle_trellis");

    public static final Trellis ACACIA_TRELLIS = registerTrellis("acacia_trellis");

    public static final Trellis DARK_OAK_TRELLIS = registerTrellis("dark_oak_trellis");

    public static final Trellis MANGROVE_TRELLIS = registerTrellis("mangrove_trellis");

    public static final Trellis CRIMSON_TRELLIS = registerTrellis("crimson_trellis");

    public static final Trellis CHERRY_TRELLIS = registerTrellis("cherry_trellis");

    public static final Trellis WARPED_TRELLIS = registerTrellis("warped_trellis");

    public static final Trellis BAMBOO_TRELLIS = registerTrellis("bamboo_trellis");

    //? if >=1.21.11 {
    public static final Trellis PALE_OAK_TRELLIS = registerTrellis("pale_oak_trellis");
    //?}

    // lamps
    public static final LampLightBulb LAMP_LIGHT_BULB = register("lamp_light_bulb",
            LampLightBulb::new, BlockBehaviour.Properties.of().mapColor(MapColor.METAL).noOcclusion()
                    .strength(0.2f, 0.2f).sound(SoundType.LANTERN).lightLevel((state) -> {
                        if (state.getValue(LampLightBulb.ON)) {
                            return 14;
                        } else {
                            return 0;
                        }
                    }));

    public static final LampBamboo LAMP_BAMBOO = register("lamp_bamboo",
            LampBamboo::new, BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).noOcclusion()
                    .strength(0.1f, 0.1f).sound(SoundType.SCAFFOLDING).lightLevel((state) -> {
                        if (state.getValue(LampBamboo.ON)) {
                            return 14;
                        } else {
                            return 0;
                        }
                    }));

    public static final LampJar LAMP_JAR = register("lamp_jar",
            LampJar::new, BlockBehaviour.Properties.of().mapColor(MapColor.NONE).noOcclusion()
                    .strength(0.05f, 0.05f).sound(SoundType.GLASS).lightLevel((state) -> {
                        final int fill = state.getValue(LampJar.FILL_LEVEL);
                        return switch (fill) {
                            case 5 -> 8;
                            case 10 -> 11;
                            case 15 -> 14;
                            default -> 0;
                        };
                    }));

    // candelabras
    public static final LampCandelabra LAMP_CANDELABRA = registerLampCandelabra("lamp_candelabra");

    public static final LampCandelabra LAMP_CANDELABRA_LIGHT_BLUE = registerLampCandelabra("lamp_candelabra_light_blue");

    public static final LampCandelabra LAMP_CANDELABRA_LIGHT_GRAY = registerLampCandelabra("lamp_candelabra_light_gray");

    public static final LampCandelabra LAMP_CANDELABRA_BLACK = registerLampCandelabra("lamp_candelabra_black");

    public static final LampCandelabra LAMP_CANDELABRA_BLUE = registerLampCandelabra("lamp_candelabra_blue");

    public static final LampCandelabra LAMP_CANDELABRA_BROWN = registerLampCandelabra("lamp_candelabra_brown");

    public static final LampCandelabra LAMP_CANDELABRA_CYAN = registerLampCandelabra("lamp_candelabra_cyan");

    public static final LampCandelabra LAMP_CANDELABRA_GRAY = registerLampCandelabra("lamp_candelabra_gray");

    public static final LampCandelabra LAMP_CANDELABRA_GREEN = registerLampCandelabra("lamp_candelabra_green");

    public static final LampCandelabra LAMP_CANDELABRA_LIME = registerLampCandelabra("lamp_candelabra_lime");

    public static final LampCandelabra LAMP_CANDELABRA_MAGENTA = registerLampCandelabra("lamp_candelabra_magenta");

    public static final LampCandelabra LAMP_CANDELABRA_ORANGE = registerLampCandelabra("lamp_candelabra_orange");

    public static final LampCandelabra LAMP_CANDELABRA_PINK = registerLampCandelabra("lamp_candelabra_pink");

    public static final LampCandelabra LAMP_CANDELABRA_PURPLE = registerLampCandelabra("lamp_candelabra_purple");

    public static final LampCandelabra LAMP_CANDELABRA_RED = registerLampCandelabra("lamp_candelabra_red");

    public static final LampCandelabra LAMP_CANDELABRA_WHITE = registerLampCandelabra("lamp_candelabra_white");

    public static final LampCandelabra LAMP_CANDELABRA_YELLOW = registerLampCandelabra("lamp_candelabra_yellow");

    // blinds
    public static final Blinds SPRUCE_BLINDS = registerBlinds(
            "spruce_blinds", MapColor.WOOD, SoundType.WOOD);

    public static final Blinds DARK_OAK_BLINDS = registerBlinds(
            "dark_oak_blinds", MapColor.WOOD, SoundType.WOOD);

    public static final Blinds CRIMSON_BLINDS = registerBlinds(
            "crimson_blinds", MapColor.WOOD, SoundType.WOOD);

    public static final Blinds CHERRY_BLINDS = registerBlinds(
            "cherry_blinds", MapColor.WOOD, SoundType.WOOD);

    public static final Blinds ACACIA_BLINDS = registerBlinds(
            "acacia_blinds", MapColor.WOOD, SoundType.WOOD);

    public static final Blinds JUNGLE_BLINDS = registerBlinds(
            "jungle_blinds", MapColor.WOOD, SoundType.WOOD);

    public static final Blinds OAK_BLINDS = registerBlinds(
            "oak_blinds", MapColor.WOOD, SoundType.WOOD);

    public static final Blinds BIRCH_BLINDS = registerBlinds(
            "birch_blinds", MapColor.WOOD, SoundType.WOOD);

    public static final Blinds WARPED_BLINDS = registerBlinds(
            "warped_blinds", MapColor.WOOD, SoundType.WOOD);

    public static final Blinds MANGROVE_BLINDS = registerBlinds(
            "mangrove_blinds", MapColor.WOOD, SoundType.WOOD);

    public static final Blinds BAMBOO_BLINDS = registerBlinds(
            "bamboo_blinds", MapColor.WOOD, SoundType.WOOD);

    //? if >=1.21.11 {
    public static final Blinds PALE_OAK_BLINDS = registerBlinds(
            "pale_oak_blinds", MapColor.WOOD, SoundType.WOOD);
    //?}

    public static final Blinds IRON_BLINDS = registerBlinds(
            "iron_blinds", MapColor.METAL, SoundType.CHAIN);

    // picture frames
    public static final PictureFrame SPRUCE_PICTURE_FRAME = registerPictureFrame(
            "spruce_picture_frame", MapColor.WOOD, SoundType.WOOD);

    public static final PictureFrame DARK_OAK_PICTURE_FRAME = registerPictureFrame(
            "dark_oak_picture_frame", MapColor.WOOD, SoundType.WOOD);

    public static final PictureFrame CRIMSON_PICTURE_FRAME = registerPictureFrame(
            "crimson_picture_frame", MapColor.WOOD, SoundType.WOOD);

    public static final PictureFrame CHERRY_PICTURE_FRAME = registerPictureFrame(
            "cherry_picture_frame", MapColor.WOOD, SoundType.WOOD);

    public static final PictureFrame ACACIA_PICTURE_FRAME = registerPictureFrame(
            "acacia_picture_frame", MapColor.WOOD, SoundType.WOOD);

    public static final PictureFrame JUNGLE_PICTURE_FRAME = registerPictureFrame(
            "jungle_picture_frame", MapColor.WOOD, SoundType.WOOD);

    public static final PictureFrame OAK_PICTURE_FRAME = registerPictureFrame(
            "oak_picture_frame", MapColor.WOOD, SoundType.WOOD);

    public static final PictureFrame BIRCH_PICTURE_FRAME = registerPictureFrame(
            "birch_picture_frame", MapColor.WOOD, SoundType.WOOD);

    public static final PictureFrame WARPED_PICTURE_FRAME = registerPictureFrame(
            "warped_picture_frame", MapColor.WOOD, SoundType.WOOD);

    public static final PictureFrame MANGROVE_PICTURE_FRAME = registerPictureFrame(
            "mangrove_picture_frame", MapColor.WOOD, SoundType.WOOD);

    public static final PictureFrame QUARTZ_PICTURE_FRAME = registerPictureFrame(
            "quartz_picture_frame", MapColor.QUARTZ, SoundType.STONE);

    public static final PictureFrame BAMBOO_PICTURE_FRAME = registerPictureFrame(
            "bamboo_picture_frame", MapColor.WOOD, SoundType.WOOD);

    //? if >=1.21.11 {
    public static final PictureFrame PALE_OAK_PICTURE_FRAME = registerPictureFrame(
            "pale_oak_picture_frame", MapColor.WOOD, SoundType.WOOD);
    //?}

    // workbench
    public static final BotanistWorkbench BOTANIST_WORKBENCH = register("botanist_workbench",
            //? if >=1.21.1 {
            BotanistWorkbench::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CRAFTING_TABLE));
            //?} else {
            //BotanistWorkbench::new, BlockBehaviour.Properties.copy(Blocks.CRAFTING_TABLE));
            //?}

    private static <T extends Block> T register(String name, Function<Block.Properties, T> factory, BlockBehaviour.Properties properties) {
        //? if >=1.21.11 {
        ResourceKey<Block> key = ResourceKey.create(Registries.BLOCK, Beautify.id(name));
        T block = factory.apply(properties.setId(key));
        return Registry.register(BuiltInRegistries.BLOCK, key, block);
        //?} else {
        //return Registry.register(BuiltInRegistries.BLOCK, Beautify.id(name), factory.apply(properties));
        //?}
    }

    private static LampCandelabra registerLampCandelabra(String name) {
        return register(name, LampCandelabra::new, BlockBehaviour.Properties.of().mapColor(MapColor.METAL).noOcclusion()
                .strength(0.2f, 0.2f).sound(SoundType.LANTERN).lightLevel((state) -> {
                    if (state.getValue(LampCandelabra.ON)) {
                        return 14;
                    } else {
                        return 0;
                    }
                }));
    }

    private static Trellis registerTrellis(String name) {
        return register(name, Trellis::new, BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).mapColor(MapColor.WOOD)
                .strength(0.3F, 0.3F).sound(SoundType.BAMBOO).noOcclusion());
    }

    private static PictureFrame registerPictureFrame(String name, MapColor color, SoundType type) {
        return register(name, PictureFrame::new, BlockBehaviour.Properties.of().mapColor(color).noOcclusion()
                //? if >=26.3 {
                .strength(0.1f, 0.1f).sound(type).noOcclusion().pushReaction(PushReaction.POPPED));
                //?} else if >=1.21.11 {
                //.strength(0.1f, 0.1f).sound(type).noOcclusion().pushReaction(PushReaction.DESTROY));
                //?} else {
                //.strength(0.1f, 0.1f).sound(type).noOcclusion());
                //?}
    }

    private static Blinds registerBlinds(String name, MapColor color, SoundType type) {
        return register(name, Blinds::new, BlockBehaviour.Properties.of().mapColor(color).noOcclusion()
                .strength(0.4f, 0.4f).sound(type));
    }

    public static void registerFlammableBlock() {
        FlammableBlockRegistry flammableRegistry = FlammableBlockRegistry.getDefaultInstance();
        flammableRegistry.add(OAK_TRELLIS, 30, 20);
        flammableRegistry.add(SPRUCE_TRELLIS, 30, 20);
        flammableRegistry.add(BIRCH_TRELLIS, 30, 20);
        flammableRegistry.add(JUNGLE_TRELLIS, 30, 20);
        flammableRegistry.add(ACACIA_TRELLIS, 30, 20);
        flammableRegistry.add(DARK_OAK_TRELLIS, 30, 20);
        flammableRegistry.add(MANGROVE_TRELLIS, 30, 20);
        flammableRegistry.add(CHERRY_TRELLIS, 30, 20);
        flammableRegistry.add(BAMBOO_TRELLIS, 30, 20);
        //? if >=1.21.11 {
        flammableRegistry.add(PALE_OAK_TRELLIS, 30, 20);
        //?}

        // blinds
        flammableRegistry.add(SPRUCE_BLINDS, 30, 20);
        flammableRegistry.add(DARK_OAK_BLINDS, 30, 20);
        flammableRegistry.add(CRIMSON_BLINDS, 30, 20);
        flammableRegistry.add(CHERRY_BLINDS, 30, 20);
        flammableRegistry.add(ACACIA_BLINDS, 30, 20);
        flammableRegistry.add(JUNGLE_BLINDS, 30, 20);
        flammableRegistry.add(OAK_BLINDS, 30, 20);
        flammableRegistry.add(BIRCH_BLINDS, 30, 20);
        flammableRegistry.add(WARPED_BLINDS, 30, 20);
        flammableRegistry.add(MANGROVE_BLINDS, 30, 20);
        flammableRegistry.add(BAMBOO_BLINDS, 30, 20);
        //? if >=1.21.11 {
        flammableRegistry.add(PALE_OAK_BLINDS, 30, 20);
        //?}

        // picture frames
        flammableRegistry.add(SPRUCE_PICTURE_FRAME, 30, 20);
        flammableRegistry.add(DARK_OAK_PICTURE_FRAME, 30, 20);
        flammableRegistry.add(CRIMSON_PICTURE_FRAME, 30, 20);
        flammableRegistry.add(CHERRY_PICTURE_FRAME, 30, 20);
        flammableRegistry.add(ACACIA_PICTURE_FRAME, 30, 20);
        flammableRegistry.add(JUNGLE_PICTURE_FRAME, 30, 20);
        flammableRegistry.add(OAK_PICTURE_FRAME, 30, 20);
        flammableRegistry.add(BIRCH_PICTURE_FRAME, 30, 20);
        flammableRegistry.add(WARPED_PICTURE_FRAME, 30, 20);
        flammableRegistry.add(MANGROVE_PICTURE_FRAME, 30, 20);
        flammableRegistry.add(BAMBOO_PICTURE_FRAME, 30, 20);
        //? if >=1.21.11 {
        flammableRegistry.add(PALE_OAK_PICTURE_FRAME, 30, 20);
        //?}
    }
}
//?} else {
/*package io.github.suel_ki.beautify.core.init;

import io.github.suel_ki.beautify.Beautify;
import io.github.suel_ki.beautify.common.block.BookStack;
import io.github.suel_ki.beautify.common.block.BotanistWorkbench;
import io.github.suel_ki.beautify.common.block.HangingPot;
import io.github.suel_ki.beautify.common.block.LampBamboo;
import io.github.suel_ki.beautify.common.block.LampCandelabra;
import io.github.suel_ki.beautify.common.block.LampJar;
import io.github.suel_ki.beautify.common.block.LampLightBulb;
import io.github.suel_ki.beautify.common.block.Blinds;
import io.github.suel_ki.beautify.common.block.PictureFrame;
import io.github.suel_ki.beautify.common.block.Trellis;
import io.github.suel_ki.beautify.common.block.Rope;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Function;
import java.util.function.Supplier;

public class BlockInit {

    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(Beautify.MODID);

    // BLOCKS
    public static final DeferredBlock<BookStack> BOOKSTACK = register("bookstack",
            BookStack::new,
            () -> BlockBehaviour.Properties.of().mapColor(MapColor.NONE)
                    .strength(0.2F, 0.2F).sound(SoundInit.BOOKSTACK_SOUNDS).noOcclusion());

    public static final DeferredBlock<Rope> ROPE = register("rope",
            Rope::new,
            () -> BlockBehaviour.Properties.of().mapColor(MapColor.NONE)
                    //? if >=26.3 {
                    .strength(0.2F, 0.2F).sound(SoundType.WOOL).pushReaction(PushReaction.POPPED).noOcclusion());
                    //?} else {
                    //.strength(0.2F, 0.2F).sound(SoundType.WOOL).pushReaction(PushReaction.DESTROY).noOcclusion());
                    //?}

    public static final DeferredBlock<HangingPot> HANGING_POT = register("hanging_pot",
            HangingPot::new,
            () -> BlockBehaviour.Properties.of().mapColor(MapColor.TERRACOTTA_BROWN)
                    .noOcclusion().strength(0.1f, 0.1f).sound(SoundType.STONE).lightLevel((state) -> {
                        if (state.getValue(HangingPot.POTFLOWER) == 15) {
                            return 7;
                        } else if (state.getValue(HangingPot.POTFLOWER) == 22) {
                            return 14;
                        } else {
                            return 0;
                        }
                    }));

    // trellis
    public static final DeferredBlock<Trellis> OAK_TRELLIS = registerTrellis("oak_trellis");

    public static final DeferredBlock<Trellis> SPRUCE_TRELLIS = registerTrellis("spruce_trellis");

    public static final DeferredBlock<Trellis> BIRCH_TRELLIS = registerTrellis("birch_trellis");

    public static final DeferredBlock<Trellis> JUNGLE_TRELLIS = registerTrellis("jungle_trellis");

    public static final DeferredBlock<Trellis> ACACIA_TRELLIS = registerTrellis("acacia_trellis");

    public static final DeferredBlock<Trellis> DARK_OAK_TRELLIS = registerTrellis("dark_oak_trellis");

    public static final DeferredBlock<Trellis> MANGROVE_TRELLIS = registerTrellis("mangrove_trellis");

    public static final DeferredBlock<Trellis> CRIMSON_TRELLIS = registerTrellis("crimson_trellis");

    public static final DeferredBlock<Trellis> CHERRY_TRELLIS = registerTrellis("cherry_trellis");

    public static final DeferredBlock<Trellis> WARPED_TRELLIS = registerTrellis("warped_trellis");

    public static final DeferredBlock<Trellis> BAMBOO_TRELLIS = registerTrellis("bamboo_trellis");

    //? if >=1.21.11 {
    public static final DeferredBlock<Trellis> PALE_OAK_TRELLIS = registerTrellis("pale_oak_trellis");
    //?}

    // lamps
    public static final DeferredBlock<LampLightBulb> LAMP_LIGHT_BULB = register("lamp_light_bulb",
            LampLightBulb::new,
            () -> BlockBehaviour.Properties.of().mapColor(MapColor.METAL).noOcclusion()
                    .strength(0.2f, 0.2f).sound(SoundType.LANTERN).lightLevel((state) -> {
                        if (state.getValue(LampLightBulb.ON)) {
                            return 14;
                        } else {
                            return 0;
                        }
                    }));

    public static final DeferredBlock<LampBamboo> LAMP_BAMBOO = register("lamp_bamboo",
            LampBamboo::new,
            () -> BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).noOcclusion()
                    .strength(0.1f, 0.1f).sound(SoundType.SCAFFOLDING).lightLevel((state) -> {
                        if (state.getValue(LampBamboo.ON)) {
                            return 14;
                        } else {
                            return 0;
                        }
                    }));

    public static final DeferredBlock<LampJar> LAMP_JAR = register("lamp_jar",
            LampJar::new,
            () -> BlockBehaviour.Properties.of().mapColor(MapColor.NONE).noOcclusion()
                    .strength(0.05f, 0.05f).sound(SoundType.GLASS).lightLevel((state) -> {
                        final int fill = state.getValue(LampJar.FILL_LEVEL);
                        return switch (fill) {
                            case 5 -> 8;
                            case 10 -> 11;
                            case 15 -> 14;
                            default -> 0;
                        };
                    }));

    // candelabras
    public static final DeferredBlock<LampCandelabra> LAMP_CANDELABRA = registerLampCandelabra("lamp_candelabra");

    public static DeferredBlock<LampCandelabra> LAMP_CANDELABRA_LIGHT_BLUE = registerLampCandelabra("lamp_candelabra_light_blue");

    public static DeferredBlock<LampCandelabra> LAMP_CANDELABRA_LIGHT_GRAY = registerLampCandelabra("lamp_candelabra_light_gray");

    public static DeferredBlock<LampCandelabra> LAMP_CANDELABRA_BLACK = registerLampCandelabra("lamp_candelabra_black");

    public static DeferredBlock<LampCandelabra> LAMP_CANDELABRA_BLUE = registerLampCandelabra("lamp_candelabra_blue");

    public static DeferredBlock<LampCandelabra> LAMP_CANDELABRA_BROWN = registerLampCandelabra("lamp_candelabra_brown");

    public static DeferredBlock<LampCandelabra> LAMP_CANDELABRA_CYAN = registerLampCandelabra("lamp_candelabra_cyan");

    public static DeferredBlock<LampCandelabra> LAMP_CANDELABRA_GRAY = registerLampCandelabra("lamp_candelabra_gray");

    public static DeferredBlock<LampCandelabra> LAMP_CANDELABRA_GREEN = registerLampCandelabra("lamp_candelabra_green");

    public static DeferredBlock<LampCandelabra> LAMP_CANDELABRA_LIME = registerLampCandelabra("lamp_candelabra_lime");

    public static DeferredBlock<LampCandelabra> LAMP_CANDELABRA_MAGENTA = registerLampCandelabra("lamp_candelabra_magenta");

    public static DeferredBlock<LampCandelabra> LAMP_CANDELABRA_ORANGE = registerLampCandelabra("lamp_candelabra_orange");

    public static DeferredBlock<LampCandelabra> LAMP_CANDELABRA_PINK = registerLampCandelabra("lamp_candelabra_pink");

    public static DeferredBlock<LampCandelabra> LAMP_CANDELABRA_PURPLE = registerLampCandelabra("lamp_candelabra_purple");

    public static DeferredBlock<LampCandelabra> LAMP_CANDELABRA_RED = registerLampCandelabra("lamp_candelabra_red");

    public static DeferredBlock<LampCandelabra> LAMP_CANDELABRA_WHITE = registerLampCandelabra("lamp_candelabra_white");

    public static DeferredBlock<LampCandelabra> LAMP_CANDELABRA_YELLOW = registerLampCandelabra("lamp_candelabra_yellow");

    // blinds
    public static final DeferredBlock<Blinds> SPRUCE_BLINDS = registerBlinds(
            "spruce_blinds", MapColor.WOOD, SoundType.WOOD);

    public static final DeferredBlock<Blinds> DARK_OAK_BLINDS = registerBlinds(
            "dark_oak_blinds", MapColor.WOOD, SoundType.WOOD);

    public static final DeferredBlock<Blinds> CRIMSON_BLINDS = registerBlinds(
            "crimson_blinds", MapColor.WOOD, SoundType.WOOD);

    public static final DeferredBlock<Blinds> CHERRY_BLINDS = registerBlinds(
            "cherry_blinds", MapColor.WOOD, SoundType.WOOD);

    public static final DeferredBlock<Blinds> ACACIA_BLINDS = registerBlinds(
            "acacia_blinds", MapColor.WOOD, SoundType.WOOD);

    public static final DeferredBlock<Blinds> JUNGLE_BLINDS = registerBlinds(
            "jungle_blinds", MapColor.WOOD, SoundType.WOOD);

    public static final DeferredBlock<Blinds> OAK_BLINDS = registerBlinds(
            "oak_blinds", MapColor.WOOD, SoundType.WOOD);

    public static final DeferredBlock<Blinds> BIRCH_BLINDS = registerBlinds(
            "birch_blinds", MapColor.WOOD, SoundType.WOOD);

    public static final DeferredBlock<Blinds> WARPED_BLINDS = registerBlinds(
            "warped_blinds", MapColor.WOOD, SoundType.WOOD);

    public static final DeferredBlock<Blinds> MANGROVE_BLINDS = registerBlinds(
            "mangrove_blinds", MapColor.WOOD, SoundType.WOOD);

    public static final DeferredBlock<Blinds> BAMBOO_BLINDS = registerBlinds(
            "bamboo_blinds", MapColor.WOOD, SoundType.WOOD);

    //? if >=1.21.11 {
    public static final DeferredBlock<Blinds> PALE_OAK_BLINDS = registerBlinds(
            "pale_oak_blinds", MapColor.WOOD, SoundType.WOOD);
    //?}

    public static final DeferredBlock<Blinds> IRON_BLINDS = registerBlinds(
            "iron_blinds", MapColor.METAL, SoundType.CHAIN);

    // picture frames
    public static final DeferredBlock<PictureFrame> SPRUCE_PICTURE_FRAME = registerPictureFrame(
            "spruce_picture_frame", MapColor.WOOD, SoundType.WOOD);

    public static final DeferredBlock<PictureFrame> DARK_OAK_PICTURE_FRAME = registerPictureFrame(
            "dark_oak_picture_frame", MapColor.WOOD, SoundType.WOOD);

    public static final DeferredBlock<PictureFrame> CRIMSON_PICTURE_FRAME = registerPictureFrame(
            "crimson_picture_frame", MapColor.WOOD, SoundType.WOOD);

    public static final DeferredBlock<PictureFrame> CHERRY_PICTURE_FRAME = registerPictureFrame(
            "cherry_picture_frame", MapColor.WOOD, SoundType.WOOD);

    public static final DeferredBlock<PictureFrame> ACACIA_PICTURE_FRAME = registerPictureFrame(
            "acacia_picture_frame", MapColor.WOOD, SoundType.WOOD);

    public static final DeferredBlock<PictureFrame> JUNGLE_PICTURE_FRAME = registerPictureFrame(
            "jungle_picture_frame", MapColor.WOOD, SoundType.WOOD);

    public static final DeferredBlock<PictureFrame> OAK_PICTURE_FRAME = registerPictureFrame(
            "oak_picture_frame", MapColor.WOOD, SoundType.WOOD);

    public static final DeferredBlock<PictureFrame> BIRCH_PICTURE_FRAME = registerPictureFrame(
            "birch_picture_frame", MapColor.WOOD, SoundType.WOOD);

    public static final DeferredBlock<PictureFrame> WARPED_PICTURE_FRAME = registerPictureFrame(
            "warped_picture_frame", MapColor.WOOD, SoundType.WOOD);

    public static final DeferredBlock<PictureFrame> MANGROVE_PICTURE_FRAME = registerPictureFrame(
            "mangrove_picture_frame", MapColor.WOOD, SoundType.WOOD);

    public static final DeferredBlock<PictureFrame> QUARTZ_PICTURE_FRAME = registerPictureFrame(
            "quartz_picture_frame", MapColor.QUARTZ, SoundType.STONE);

    public static final DeferredBlock<PictureFrame> BAMBOO_PICTURE_FRAME = registerPictureFrame(
            "bamboo_picture_frame", MapColor.WOOD, SoundType.WOOD);

    //? if >=1.21.11 {
    public static final DeferredBlock<PictureFrame> PALE_OAK_PICTURE_FRAME = registerPictureFrame(
            "pale_oak_picture_frame", MapColor.WOOD, SoundType.WOOD);
    //?}

    // workbench
    public static final DeferredBlock<BotanistWorkbench> BOTANIST_WORKBENCH = register("botanist_workbench",
            BotanistWorkbench::new,
            //? if >=1.21.1 {
            () -> BlockBehaviour.Properties.ofFullCopy(Blocks.CRAFTING_TABLE));
            //?} else {
            //() -> BlockBehaviour.Properties.copy(Blocks.CRAFTING_TABLE));
            //?}

    private static <T extends Block> DeferredBlock<T> register(String name, Function<BlockBehaviour.Properties, ? extends T> func, Supplier<BlockBehaviour.Properties> props) {
        //? if >=1.21.11 {
        return BLOCKS.registerBlock(name, func, props);
        //?} else {
        //return BLOCKS.registerBlock(name, func, props.get());
        //?}
    }

    private static DeferredBlock<LampCandelabra> registerLampCandelabra(String name) {
        return register(name, LampCandelabra::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.METAL).noOcclusion()
                .strength(0.2f, 0.2f).sound(SoundType.LANTERN).lightLevel((state) -> {
                    if (state.getValue(LampCandelabra.ON)) {
                        return 14;
                    } else {
                        return 0;
                    }
                }));
    }

    private static DeferredBlock<Trellis> registerTrellis(String name) {
        return register(name, Trellis::new, () -> BlockBehaviour.Properties.of().mapColor(MapColor.WOOD).mapColor(MapColor.WOOD)
                .strength(0.3F, 0.3F).sound(SoundType.BAMBOO).noOcclusion());
    }

    private static DeferredBlock<PictureFrame> registerPictureFrame(String name, MapColor color, SoundType type) {
        return register(name, PictureFrame::new, () -> BlockBehaviour.Properties.of().mapColor(color).noOcclusion()
                //? if >=26.3 {
                .strength(0.1f, 0.1f).sound(type).noOcclusion().pushReaction(PushReaction.POPPED));
                //?} else if >=1.21.11 {
                //.strength(0.1f, 0.1f).sound(type).noOcclusion().pushReaction(PushReaction.DESTROY));
                //?} else {
                //.strength(0.1f, 0.1f).sound(type).noOcclusion());
                //?}
    }

    private static DeferredBlock<Blinds> registerBlinds(String name, MapColor color, SoundType type) {
        return register(name, Blinds::new, () -> BlockBehaviour.Properties.of().mapColor(color).noOcclusion()
                .strength(0.4f, 0.4f).sound(type));
    }
}
*///?}
