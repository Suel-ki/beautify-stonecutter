//? if fabric && >=26.1.2 {
package io.github.suel_ki.beautify.platform.fabric.datagen;

import io.github.suel_ki.beautify.common.block.PictureFrame;

import io.github.suel_ki.beautify.common.block.HangingPot;

import com.mojang.math.Quadrant;
import io.github.suel_ki.beautify.Beautify;
import io.github.suel_ki.beautify.common.block.Blinds;
import io.github.suel_ki.beautify.common.block.BookStack;
import io.github.suel_ki.beautify.common.block.LampBamboo;
import io.github.suel_ki.beautify.common.block.LampCandelabra;
import io.github.suel_ki.beautify.common.block.Trellis;
import io.github.suel_ki.beautify.common.block.LampCandelabra;
import io.github.suel_ki.beautify.common.block.Trellis;
import io.github.suel_ki.beautify.common.block.LampLightBulb;
import io.github.suel_ki.beautify.core.init.BlockInit;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.renderer.block.dispatch.Variant;
import net.minecraft.client.renderer.block.dispatch.VariantMutator;
import net.minecraft.core.Direction;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class BeautifyModelProvider extends FabricModelProvider {


	public BeautifyModelProvider(FabricPackOutput output) {
		super(output);
	}

	@Override
	public void generateBlockStateModels(BlockModelGenerators generator) {
		generator.blockStateOutput.accept(MultiVariantGenerator.dispatch(BlockInit.ROPE, model("block/rope"))
				.with(PropertyDispatch.modify(BlockStateProperties.AXIS)
						.select(Direction.Axis.Y, BlockModelGenerators.NOP)
						.select(Direction.Axis.X, VariantMutator.X_ROT.withValue(Quadrant.R90).then(VariantMutator.Y_ROT.withValue(Quadrant.R90)))
						.select(Direction.Axis.Z, VariantMutator.X_ROT.withValue(Quadrant.R90))));

		generator.blockStateOutput.accept(MultiVariantGenerator.dispatch(BlockInit.BOTANIST_WORKBENCH, model("block/botanist_workbench"))
				.with(PropertyDispatch.modify(BlockStateProperties.HORIZONTAL_FACING)
						.select(Direction.NORTH, BlockModelGenerators.NOP)
						.select(Direction.EAST, VariantMutator.Y_ROT.withValue(Quadrant.R90))
						.select(Direction.SOUTH, VariantMutator.Y_ROT.withValue(Quadrant.R180))
						.select(Direction.WEST, VariantMutator.Y_ROT.withValue(Quadrant.R270))));

		generator.blockStateOutput.accept(MultiVariantGenerator.dispatch(BlockInit.LAMP_JAR, model("block/lamps/lamp_jar"))
				.with(PropertyDispatch.modify(BlockStateProperties.HANGING)
						.select(true, BlockModelGenerators.NOP)
						.select(false, VariantMutator.MODEL.withValue(Beautify.id("block/lamps/standing_lamp_jar")))));

		generator.blockStateOutput.accept(MultiVariantGenerator.dispatch(BlockInit.LAMP_BAMBOO, model("block/lamps/lamp_bamboo"))
				.with(PropertyDispatch.modify(BlockStateProperties.HANGING, LampBamboo.ON)
						.select(true, true, BlockModelGenerators.NOP)
						.select(false, true, VariantMutator.MODEL.withValue(Beautify.id("block/lamps/standing_lamp_bamboo")))
						.select(true, false, VariantMutator.MODEL.withValue(Beautify.id("block/lamps/lamp_bamboo_off")))
						.select(false, false, VariantMutator.MODEL.withValue(Beautify.id("block/lamps/standing_lamp_bamboo_off")))));

		generator.blockStateOutput.accept(MultiVariantGenerator.dispatch(BlockInit.LAMP_LIGHT_BULB, model("block/lamps/light_bulb"))
				.with(PropertyDispatch.modify(BlockStateProperties.HANGING, LampLightBulb.ON)
						.select(true, true, BlockModelGenerators.NOP)
						.select(false, true, VariantMutator.MODEL.withValue(Beautify.id("block/lamps/standing_light_bulb")))
						.select(true, false, VariantMutator.MODEL.withValue(Beautify.id("block/lamps/light_bulb_off")))
						.select(false, false, VariantMutator.MODEL.withValue(Beautify.id("block/lamps/standing_light_bulb_off")))));

		var bookstack = PropertyDispatch.modify(BookStack.BOOKSTACK_MODEL, BlockStateProperties.HORIZONTAL_FACING);
		for (int model = 0; model < 7; model++) {
			for (Direction facing : Direction.Plane.HORIZONTAL) {
				VariantMutator mutator = VariantMutator.MODEL.withValue(Beautify.id("block/bookstacks/bookstack_" + model));
				if (facing != Direction.NORTH) {
					mutator = mutator.then(VariantMutator.Y_ROT.withValue(quadrant(facing)));
				}
				bookstack = bookstack.select(model, facing, mutator);
			}
		}
		generator.blockStateOutput.accept(MultiVariantGenerator.dispatch(BlockInit.BOOKSTACK, model("block/bookstacks/bookstack_0"))
				.with(bookstack));

		blinds(generator, BlockInit.OAK_BLINDS, "oak_blinds");
		blinds(generator, BlockInit.SPRUCE_BLINDS, "spruce_blinds");
		blinds(generator, BlockInit.BIRCH_BLINDS, "birch_blinds");
		blinds(generator, BlockInit.JUNGLE_BLINDS, "jungle_blinds");
		blinds(generator, BlockInit.ACACIA_BLINDS, "acacia_blinds");
		blinds(generator, BlockInit.DARK_OAK_BLINDS, "dark_oak_blinds");
		blinds(generator, BlockInit.CRIMSON_BLINDS, "crimson_blinds");
		blinds(generator, BlockInit.WARPED_BLINDS, "warped_blinds");
		blinds(generator, BlockInit.CHERRY_BLINDS, "cherry_blinds");
		blinds(generator, BlockInit.MANGROVE_BLINDS, "mangrove_blinds");
		blinds(generator, BlockInit.BAMBOO_BLINDS, "bamboo_blinds");
		//? if >=1.21.11 {
		blinds(generator, BlockInit.PALE_OAK_BLINDS, "pale_oak_blinds");
		//?}
		blinds(generator, BlockInit.IRON_BLINDS, "iron_blinds");

		candelabra(generator, BlockInit.LAMP_CANDELABRA, "");
		candelabra(generator, BlockInit.LAMP_CANDELABRA_LIGHT_BLUE, "_light_blue");
		candelabra(generator, BlockInit.LAMP_CANDELABRA_LIGHT_GRAY, "_light_gray");
		candelabra(generator, BlockInit.LAMP_CANDELABRA_BLACK, "_black");
		candelabra(generator, BlockInit.LAMP_CANDELABRA_BLUE, "_blue");
		candelabra(generator, BlockInit.LAMP_CANDELABRA_BROWN, "_brown");
		candelabra(generator, BlockInit.LAMP_CANDELABRA_CYAN, "_cyan");
		candelabra(generator, BlockInit.LAMP_CANDELABRA_GRAY, "_gray");
		candelabra(generator, BlockInit.LAMP_CANDELABRA_GREEN, "_green");
		candelabra(generator, BlockInit.LAMP_CANDELABRA_LIME, "_lime");
		candelabra(generator, BlockInit.LAMP_CANDELABRA_MAGENTA, "_magenta");
		candelabra(generator, BlockInit.LAMP_CANDELABRA_ORANGE, "_orange");
		candelabra(generator, BlockInit.LAMP_CANDELABRA_PINK, "_pink");
		candelabra(generator, BlockInit.LAMP_CANDELABRA_PURPLE, "_purple");
		candelabra(generator, BlockInit.LAMP_CANDELABRA_RED, "_red");
		candelabra(generator, BlockInit.LAMP_CANDELABRA_WHITE, "_white");
		candelabra(generator, BlockInit.LAMP_CANDELABRA_YELLOW, "_yellow");

		pot(generator);
		frame(generator, BlockInit.OAK_PICTURE_FRAME, "oak");
		frame(generator, BlockInit.SPRUCE_PICTURE_FRAME, "spruce");
		frame(generator, BlockInit.BIRCH_PICTURE_FRAME, "birch");
		frame(generator, BlockInit.JUNGLE_PICTURE_FRAME, "jungle");
		frame(generator, BlockInit.ACACIA_PICTURE_FRAME, "acacia");
		frame(generator, BlockInit.DARK_OAK_PICTURE_FRAME, "dark_oak");
		frame(generator, BlockInit.MANGROVE_PICTURE_FRAME, "mangrove");
		frame(generator, BlockInit.CRIMSON_PICTURE_FRAME, "crimson");
		frame(generator, BlockInit.WARPED_PICTURE_FRAME, "warped");
		frame(generator, BlockInit.CHERRY_PICTURE_FRAME, "cherry");
		frame(generator, BlockInit.QUARTZ_PICTURE_FRAME, "quartz");
		frame(generator, BlockInit.BAMBOO_PICTURE_FRAME, "bamboo");
		//? if >=1.21.11 {
		frame(generator, BlockInit.PALE_OAK_PICTURE_FRAME, "pale_oak");
		//?}

		trellis(generator, BlockInit.OAK_TRELLIS, "oak");
		trellis(generator, BlockInit.SPRUCE_TRELLIS, "spruce");
		trellis(generator, BlockInit.BIRCH_TRELLIS, "birch");
		trellis(generator, BlockInit.JUNGLE_TRELLIS, "jungle");
		trellis(generator, BlockInit.ACACIA_TRELLIS, "acacia");
		trellis(generator, BlockInit.DARK_OAK_TRELLIS, "dark_oak");
		trellis(generator, BlockInit.CRIMSON_TRELLIS, "crimson");
		trellis(generator, BlockInit.WARPED_TRELLIS, "warped");
		trellis(generator, BlockInit.CHERRY_TRELLIS, "cherry");
		trellis(generator, BlockInit.MANGROVE_TRELLIS, "mangrove");
		trellis(generator, BlockInit.BAMBOO_TRELLIS, "bamboo");
		//? if >=1.21.11 {
		trellis(generator, BlockInit.PALE_OAK_TRELLIS, "pale_oak");
		//?}
	}

	private static void blinds(BlockModelGenerators generator, Block block, String prefix) {
		var dispatch = PropertyDispatch.modify(Blinds.OPEN, BlockStateProperties.HORIZONTAL_FACING, Blinds.HIDDEN);
		for (boolean open : new boolean[]{false, true}) {
			for (Direction facing : Direction.Plane.HORIZONTAL) {
				for (boolean hidden : new boolean[]{false, true}) {
					VariantMutator mutator = VariantMutator.MODEL.withValue(Beautify.id(hidden
							? "block/blinds/none"
							: "block/blinds/" + prefix + (open ? "_down" : "_up")));
					if (!hidden && facing != Direction.NORTH) {
						mutator = mutator.then(VariantMutator.Y_ROT.withValue(quadrant(facing)));
					}
					dispatch = dispatch.select(open, facing, hidden, mutator);
				}
			}
		}
		generator.blockStateOutput.accept(MultiVariantGenerator.dispatch(block, model("block/blinds/" + prefix + "_up")).with(dispatch));
	}
	private static void candelabra(BlockModelGenerators generator, Block block, String suffix) {
		var dispatch = PropertyDispatch.modify(LampCandelabra.ON, BlockStateProperties.HORIZONTAL_FACING, BlockStateProperties.HANGING);
		for (boolean on : new boolean[]{false, true}) {
			for (Direction facing : Direction.Plane.HORIZONTAL) {
				for (boolean hanging : new boolean[]{false, true}) {
					String model = (hanging ? "lamp_candelabra" : "standing_lamp_candelabra") + (on ? "" : "_off") + suffix;
					VariantMutator mutator = VariantMutator.MODEL.withValue(Beautify.id("block/lamps/candelabras/" + model));
					if (facing != Direction.NORTH) {
						mutator = mutator.then(VariantMutator.Y_ROT.withValue(quadrant(facing)));
					}
					dispatch = dispatch.select(on, facing, hanging, mutator);
				}
			}
		}
		generator.blockStateOutput.accept(MultiVariantGenerator.dispatch(block, model("block/lamps/candelabras/standing_lamp_candelabra_off" + suffix)).with(dispatch));
	}
	private static void frame(BlockModelGenerators generator, Block block, String wood) {
		var dispatch = PropertyDispatch.modify(PictureFrame.FRAME_MOTIVE, BlockStateProperties.HORIZONTAL_FACING, PictureFrame.DIAGONAL);
		for (int motive = 0; motive < PictureFrame.MOTIVE_MODELS.size(); motive++) {
			for (Direction facing : Direction.Plane.HORIZONTAL) {
				for (boolean diagonal : new boolean[]{false, true}) {
					String model = "block/pictureframes/" + wood + "_" + PictureFrame.MOTIVE_MODELS.get(motive) + "_picture_frame" + (diagonal ? "_diagonal" : "");
					VariantMutator mutator = VariantMutator.MODEL.withValue(Beautify.id(model));
					if (facing != Direction.NORTH) {
						mutator = mutator.then(VariantMutator.Y_ROT.withValue(quadrant(facing)));
					}
					dispatch = dispatch.select(motive, facing, diagonal, mutator);
				}
			}
		}
		generator.blockStateOutput.accept(MultiVariantGenerator.dispatch(block, model("block/pictureframes/" + wood + "_sunset_picture_frame")).with(dispatch));
	}
	private static void pot(BlockModelGenerators generator) {
		var dispatch = PropertyDispatch.modify(HangingPot.POTFLOWER, BlockStateProperties.HANGING, HangingPot.GROWN);
		for (int potflower = 0; potflower < HangingPot.POT_MODELS.size(); potflower++) {
			for (boolean hanging : new boolean[]{false, true}) {
				for (boolean grown : new boolean[]{false, true}) {
					dispatch = dispatch.select(potflower, hanging, grown,
							VariantMutator.MODEL.withValue(Beautify.id(HangingPot.potModel(potflower, hanging, grown))));
				}
			}
		}
		generator.blockStateOutput.accept(MultiVariantGenerator.dispatch(BlockInit.HANGING_POT, model(HangingPot.potModel(0, true, false))).with(dispatch));
	}
	private static void trellis(BlockModelGenerators generator, Block block, String wood) {
		var dispatch = PropertyDispatch.modify(BlockStateProperties.HORIZONTAL_FACING, Trellis.CEILLING, Trellis.FLOWERS);
		for (Direction facing : Direction.Plane.HORIZONTAL) {
			for (boolean ceiling : new boolean[]{false, true}) {
				for (int flowers = 0; flowers < Trellis.FLOWER_SUFFIXES.size(); flowers++) {
					String model = "block/trellis/" + wood + "/" + (ceiling ? "open_trellis" : "closed_trellis") + Trellis.FLOWER_SUFFIXES.get(flowers);
					VariantMutator mutator = VariantMutator.MODEL.withValue(Beautify.id(model));
					if (!ceiling) {
						mutator = mutator.then(VariantMutator.Y_ROT.withValue(closedQuadrant(facing)));
					}
					dispatch = dispatch.select(facing, ceiling, flowers, mutator);
				}
			}
		}
		generator.blockStateOutput.accept(MultiVariantGenerator.dispatch(block, model("block/trellis/" + wood + "/closed_trellis")).with(dispatch));
	}

	private static Quadrant closedQuadrant(Direction facing) {
		return switch (facing) {
			case EAST -> Quadrant.R0;
			case SOUTH -> Quadrant.R90;
			case WEST -> Quadrant.R180;
			default -> Quadrant.R270;
		};
	}
	private static Quadrant quadrant(Direction facing) {
		return switch (facing) {
			case EAST -> Quadrant.R90;
			case SOUTH -> Quadrant.R180;
			case WEST -> Quadrant.R270;
			default -> Quadrant.R0;
		};
	}

	private static MultiVariant model(String path) {
		return new MultiVariant(WeightedList.of(new Variant(Beautify.id(path))));
	}

	@Override
	public void generateItemModels(ItemModelGenerators generator) {
	}
}
//?}
//? if fabric && >=1.21.11 && <26.1.2 {
/*package io.github.suel_ki.beautify.platform.fabric.datagen;

import io.github.suel_ki.beautify.common.block.PictureFrame;

import io.github.suel_ki.beautify.common.block.HangingPot;

import com.mojang.math.Quadrant;
import io.github.suel_ki.beautify.Beautify;
import io.github.suel_ki.beautify.common.block.Blinds;
import io.github.suel_ki.beautify.common.block.BookStack;
import io.github.suel_ki.beautify.common.block.LampBamboo;
import io.github.suel_ki.beautify.common.block.LampCandelabra;
import io.github.suel_ki.beautify.common.block.Trellis;
import io.github.suel_ki.beautify.common.block.LampCandelabra;
import io.github.suel_ki.beautify.common.block.Trellis;
import io.github.suel_ki.beautify.common.block.LampLightBulb;
import io.github.suel_ki.beautify.core.init.BlockInit;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.renderer.block.model.Variant;
import net.minecraft.client.renderer.block.model.VariantMutator;
import net.minecraft.core.Direction;
import net.minecraft.util.random.WeightedList;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class BeautifyModelProvider extends FabricModelProvider {


	public BeautifyModelProvider(FabricPackOutput output) {
		super(output);
	}

	@Override
	public void generateBlockStateModels(BlockModelGenerators generator) {
		generator.blockStateOutput.accept(MultiVariantGenerator.dispatch(BlockInit.ROPE, model("block/rope"))
				.with(PropertyDispatch.modify(BlockStateProperties.AXIS)
						.select(Direction.Axis.Y, BlockModelGenerators.NOP)
						.select(Direction.Axis.X, VariantMutator.X_ROT.withValue(Quadrant.R90).then(VariantMutator.Y_ROT.withValue(Quadrant.R90)))
						.select(Direction.Axis.Z, VariantMutator.X_ROT.withValue(Quadrant.R90))));

		generator.blockStateOutput.accept(MultiVariantGenerator.dispatch(BlockInit.BOTANIST_WORKBENCH, model("block/botanist_workbench"))
				.with(PropertyDispatch.modify(BlockStateProperties.HORIZONTAL_FACING)
						.select(Direction.NORTH, BlockModelGenerators.NOP)
						.select(Direction.EAST, VariantMutator.Y_ROT.withValue(Quadrant.R90))
						.select(Direction.SOUTH, VariantMutator.Y_ROT.withValue(Quadrant.R180))
						.select(Direction.WEST, VariantMutator.Y_ROT.withValue(Quadrant.R270))));

		generator.blockStateOutput.accept(MultiVariantGenerator.dispatch(BlockInit.LAMP_JAR, model("block/lamps/lamp_jar"))
				.with(PropertyDispatch.modify(BlockStateProperties.HANGING)
						.select(true, BlockModelGenerators.NOP)
						.select(false, VariantMutator.MODEL.withValue(Beautify.id("block/lamps/standing_lamp_jar")))));

		generator.blockStateOutput.accept(MultiVariantGenerator.dispatch(BlockInit.LAMP_BAMBOO, model("block/lamps/lamp_bamboo"))
				.with(PropertyDispatch.modify(BlockStateProperties.HANGING, LampBamboo.ON)
						.select(true, true, BlockModelGenerators.NOP)
						.select(false, true, VariantMutator.MODEL.withValue(Beautify.id("block/lamps/standing_lamp_bamboo")))
						.select(true, false, VariantMutator.MODEL.withValue(Beautify.id("block/lamps/lamp_bamboo_off")))
						.select(false, false, VariantMutator.MODEL.withValue(Beautify.id("block/lamps/standing_lamp_bamboo_off")))));

		generator.blockStateOutput.accept(MultiVariantGenerator.dispatch(BlockInit.LAMP_LIGHT_BULB, model("block/lamps/light_bulb"))
				.with(PropertyDispatch.modify(BlockStateProperties.HANGING, LampLightBulb.ON)
						.select(true, true, BlockModelGenerators.NOP)
						.select(false, true, VariantMutator.MODEL.withValue(Beautify.id("block/lamps/standing_light_bulb")))
						.select(true, false, VariantMutator.MODEL.withValue(Beautify.id("block/lamps/light_bulb_off")))
						.select(false, false, VariantMutator.MODEL.withValue(Beautify.id("block/lamps/standing_light_bulb_off")))));

		var bookstack = PropertyDispatch.modify(BookStack.BOOKSTACK_MODEL, BlockStateProperties.HORIZONTAL_FACING);
		for (int model = 0; model < 7; model++) {
			for (Direction facing : Direction.Plane.HORIZONTAL) {
				VariantMutator mutator = VariantMutator.MODEL.withValue(Beautify.id("block/bookstacks/bookstack_" + model));
				if (facing != Direction.NORTH) {
					mutator = mutator.then(VariantMutator.Y_ROT.withValue(quadrant(facing)));
				}
				bookstack = bookstack.select(model, facing, mutator);
			}
		}
		generator.blockStateOutput.accept(MultiVariantGenerator.dispatch(BlockInit.BOOKSTACK, model("block/bookstacks/bookstack_0"))
				.with(bookstack));

		blinds(generator, BlockInit.OAK_BLINDS, "oak_blinds");
		blinds(generator, BlockInit.SPRUCE_BLINDS, "spruce_blinds");
		blinds(generator, BlockInit.BIRCH_BLINDS, "birch_blinds");
		blinds(generator, BlockInit.JUNGLE_BLINDS, "jungle_blinds");
		blinds(generator, BlockInit.ACACIA_BLINDS, "acacia_blinds");
		blinds(generator, BlockInit.DARK_OAK_BLINDS, "dark_oak_blinds");
		blinds(generator, BlockInit.CRIMSON_BLINDS, "crimson_blinds");
		blinds(generator, BlockInit.WARPED_BLINDS, "warped_blinds");
		blinds(generator, BlockInit.CHERRY_BLINDS, "cherry_blinds");
		blinds(generator, BlockInit.MANGROVE_BLINDS, "mangrove_blinds");
		blinds(generator, BlockInit.BAMBOO_BLINDS, "bamboo_blinds");
		//? if >=1.21.11 {
		blinds(generator, BlockInit.PALE_OAK_BLINDS, "pale_oak_blinds");
		//?}
		blinds(generator, BlockInit.IRON_BLINDS, "iron_blinds");

		candelabra(generator, BlockInit.LAMP_CANDELABRA, "");
		candelabra(generator, BlockInit.LAMP_CANDELABRA_LIGHT_BLUE, "_light_blue");
		candelabra(generator, BlockInit.LAMP_CANDELABRA_LIGHT_GRAY, "_light_gray");
		candelabra(generator, BlockInit.LAMP_CANDELABRA_BLACK, "_black");
		candelabra(generator, BlockInit.LAMP_CANDELABRA_BLUE, "_blue");
		candelabra(generator, BlockInit.LAMP_CANDELABRA_BROWN, "_brown");
		candelabra(generator, BlockInit.LAMP_CANDELABRA_CYAN, "_cyan");
		candelabra(generator, BlockInit.LAMP_CANDELABRA_GRAY, "_gray");
		candelabra(generator, BlockInit.LAMP_CANDELABRA_GREEN, "_green");
		candelabra(generator, BlockInit.LAMP_CANDELABRA_LIME, "_lime");
		candelabra(generator, BlockInit.LAMP_CANDELABRA_MAGENTA, "_magenta");
		candelabra(generator, BlockInit.LAMP_CANDELABRA_ORANGE, "_orange");
		candelabra(generator, BlockInit.LAMP_CANDELABRA_PINK, "_pink");
		candelabra(generator, BlockInit.LAMP_CANDELABRA_PURPLE, "_purple");
		candelabra(generator, BlockInit.LAMP_CANDELABRA_RED, "_red");
		candelabra(generator, BlockInit.LAMP_CANDELABRA_WHITE, "_white");
		candelabra(generator, BlockInit.LAMP_CANDELABRA_YELLOW, "_yellow");

		pot(generator);
		frame(generator, BlockInit.OAK_PICTURE_FRAME, "oak");
		frame(generator, BlockInit.SPRUCE_PICTURE_FRAME, "spruce");
		frame(generator, BlockInit.BIRCH_PICTURE_FRAME, "birch");
		frame(generator, BlockInit.JUNGLE_PICTURE_FRAME, "jungle");
		frame(generator, BlockInit.ACACIA_PICTURE_FRAME, "acacia");
		frame(generator, BlockInit.DARK_OAK_PICTURE_FRAME, "dark_oak");
		frame(generator, BlockInit.MANGROVE_PICTURE_FRAME, "mangrove");
		frame(generator, BlockInit.CRIMSON_PICTURE_FRAME, "crimson");
		frame(generator, BlockInit.WARPED_PICTURE_FRAME, "warped");
		frame(generator, BlockInit.CHERRY_PICTURE_FRAME, "cherry");
		frame(generator, BlockInit.QUARTZ_PICTURE_FRAME, "quartz");
		frame(generator, BlockInit.BAMBOO_PICTURE_FRAME, "bamboo");
		//? if >=1.21.11 {
		frame(generator, BlockInit.PALE_OAK_PICTURE_FRAME, "pale_oak");
		//?}

		trellis(generator, BlockInit.OAK_TRELLIS, "oak");
		trellis(generator, BlockInit.SPRUCE_TRELLIS, "spruce");
		trellis(generator, BlockInit.BIRCH_TRELLIS, "birch");
		trellis(generator, BlockInit.JUNGLE_TRELLIS, "jungle");
		trellis(generator, BlockInit.ACACIA_TRELLIS, "acacia");
		trellis(generator, BlockInit.DARK_OAK_TRELLIS, "dark_oak");
		trellis(generator, BlockInit.CRIMSON_TRELLIS, "crimson");
		trellis(generator, BlockInit.WARPED_TRELLIS, "warped");
		trellis(generator, BlockInit.CHERRY_TRELLIS, "cherry");
		trellis(generator, BlockInit.MANGROVE_TRELLIS, "mangrove");
		trellis(generator, BlockInit.BAMBOO_TRELLIS, "bamboo");
		//? if >=1.21.11 {
		trellis(generator, BlockInit.PALE_OAK_TRELLIS, "pale_oak");
		//?}
	}

	private static void blinds(BlockModelGenerators generator, Block block, String prefix) {
		var dispatch = PropertyDispatch.modify(Blinds.OPEN, BlockStateProperties.HORIZONTAL_FACING, Blinds.HIDDEN);
		for (boolean open : new boolean[]{false, true}) {
			for (Direction facing : Direction.Plane.HORIZONTAL) {
				for (boolean hidden : new boolean[]{false, true}) {
					VariantMutator mutator = VariantMutator.MODEL.withValue(Beautify.id(hidden
							? "block/blinds/none"
							: "block/blinds/" + prefix + (open ? "_down" : "_up")));
					if (!hidden && facing != Direction.NORTH) {
						mutator = mutator.then(VariantMutator.Y_ROT.withValue(quadrant(facing)));
					}
					dispatch = dispatch.select(open, facing, hidden, mutator);
				}
			}
		}
		generator.blockStateOutput.accept(MultiVariantGenerator.dispatch(block, model("block/blinds/" + prefix + "_up")).with(dispatch));
	}
	private static void candelabra(BlockModelGenerators generator, Block block, String suffix) {
		var dispatch = PropertyDispatch.modify(LampCandelabra.ON, BlockStateProperties.HORIZONTAL_FACING, BlockStateProperties.HANGING);
		for (boolean on : new boolean[]{false, true}) {
			for (Direction facing : Direction.Plane.HORIZONTAL) {
				for (boolean hanging : new boolean[]{false, true}) {
					String model = (hanging ? "lamp_candelabra" : "standing_lamp_candelabra") + (on ? "" : "_off") + suffix;
					VariantMutator mutator = VariantMutator.MODEL.withValue(Beautify.id("block/lamps/candelabras/" + model));
					if (facing != Direction.NORTH) {
						mutator = mutator.then(VariantMutator.Y_ROT.withValue(quadrant(facing)));
					}
					dispatch = dispatch.select(on, facing, hanging, mutator);
				}
			}
		}
		generator.blockStateOutput.accept(MultiVariantGenerator.dispatch(block, model("block/lamps/candelabras/standing_lamp_candelabra_off" + suffix)).with(dispatch));
	}
	private static void frame(BlockModelGenerators generator, Block block, String wood) {
		var dispatch = PropertyDispatch.modify(PictureFrame.FRAME_MOTIVE, BlockStateProperties.HORIZONTAL_FACING, PictureFrame.DIAGONAL);
		for (int motive = 0; motive < PictureFrame.MOTIVE_MODELS.size(); motive++) {
			for (Direction facing : Direction.Plane.HORIZONTAL) {
				for (boolean diagonal : new boolean[]{false, true}) {
					String model = "block/pictureframes/" + wood + "_" + PictureFrame.MOTIVE_MODELS.get(motive) + "_picture_frame" + (diagonal ? "_diagonal" : "");
					VariantMutator mutator = VariantMutator.MODEL.withValue(Beautify.id(model));
					if (facing != Direction.NORTH) {
						mutator = mutator.then(VariantMutator.Y_ROT.withValue(quadrant(facing)));
					}
					dispatch = dispatch.select(motive, facing, diagonal, mutator);
				}
			}
		}
		generator.blockStateOutput.accept(MultiVariantGenerator.dispatch(block, model("block/pictureframes/" + wood + "_sunset_picture_frame")).with(dispatch));
	}
	private static void pot(BlockModelGenerators generator) {
		var dispatch = PropertyDispatch.modify(HangingPot.POTFLOWER, BlockStateProperties.HANGING, HangingPot.GROWN);
		for (int potflower = 0; potflower < HangingPot.POT_MODELS.size(); potflower++) {
			for (boolean hanging : new boolean[]{false, true}) {
				for (boolean grown : new boolean[]{false, true}) {
					dispatch = dispatch.select(potflower, hanging, grown,
							VariantMutator.MODEL.withValue(Beautify.id(HangingPot.potModel(potflower, hanging, grown))));
				}
			}
		}
		generator.blockStateOutput.accept(MultiVariantGenerator.dispatch(BlockInit.HANGING_POT, model(HangingPot.potModel(0, true, false))).with(dispatch));
	}
	private static void trellis(BlockModelGenerators generator, Block block, String wood) {
		var dispatch = PropertyDispatch.modify(BlockStateProperties.HORIZONTAL_FACING, Trellis.CEILLING, Trellis.FLOWERS);
		for (Direction facing : Direction.Plane.HORIZONTAL) {
			for (boolean ceiling : new boolean[]{false, true}) {
				for (int flowers = 0; flowers < Trellis.FLOWER_SUFFIXES.size(); flowers++) {
					String model = "block/trellis/" + wood + "/" + (ceiling ? "open_trellis" : "closed_trellis") + Trellis.FLOWER_SUFFIXES.get(flowers);
					VariantMutator mutator = VariantMutator.MODEL.withValue(Beautify.id(model));
					if (!ceiling) {
						mutator = mutator.then(VariantMutator.Y_ROT.withValue(closedQuadrant(facing)));
					}
					dispatch = dispatch.select(facing, ceiling, flowers, mutator);
				}
			}
		}
		generator.blockStateOutput.accept(MultiVariantGenerator.dispatch(block, model("block/trellis/" + wood + "/closed_trellis")).with(dispatch));
	}

	private static Quadrant closedQuadrant(Direction facing) {
		return switch (facing) {
			case EAST -> Quadrant.R0;
			case SOUTH -> Quadrant.R90;
			case WEST -> Quadrant.R180;
			default -> Quadrant.R270;
		};
	}
	private static Quadrant quadrant(Direction facing) {
		return switch (facing) {
			case EAST -> Quadrant.R90;
			case SOUTH -> Quadrant.R180;
			case WEST -> Quadrant.R270;
			default -> Quadrant.R0;
		};
	}

	private static MultiVariant model(String path) {
		return new MultiVariant(WeightedList.of(new Variant(Beautify.id(path))));
	}

	@Override
	public void generateItemModels(ItemModelGenerators generator) {
	}
}
*///?}
//? if fabric && <1.21.11 {
/*package io.github.suel_ki.beautify.platform.fabric.datagen;

import io.github.suel_ki.beautify.common.block.PictureFrame;

import io.github.suel_ki.beautify.common.block.HangingPot;

import io.github.suel_ki.beautify.Beautify;
import io.github.suel_ki.beautify.common.block.Blinds;
import io.github.suel_ki.beautify.common.block.BookStack;
import io.github.suel_ki.beautify.common.block.LampBamboo;
import io.github.suel_ki.beautify.common.block.LampCandelabra;
import io.github.suel_ki.beautify.common.block.Trellis;
import io.github.suel_ki.beautify.common.block.LampCandelabra;
import io.github.suel_ki.beautify.common.block.Trellis;
import io.github.suel_ki.beautify.common.block.LampLightBulb;
import io.github.suel_ki.beautify.core.init.BlockInit;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.data.models.BlockModelGenerators;
import net.minecraft.data.models.ItemModelGenerators;
import net.minecraft.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.data.models.blockstates.PropertyDispatch;
import net.minecraft.data.models.blockstates.Variant;
import net.minecraft.data.models.blockstates.VariantProperties;
import net.minecraft.core.Direction;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class BeautifyModelProvider extends FabricModelProvider {


	public BeautifyModelProvider(FabricPackOutput output) {
		super(output);
	}

	@Override
	public void generateBlockStateModels(BlockModelGenerators generator) {
		generator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(BlockInit.ROPE)
				.with(PropertyDispatch.property(BlockStateProperties.AXIS)
						.select(Direction.Axis.Y, variant("block/rope"))
						.select(Direction.Axis.X, variant("block/rope").with(VariantProperties.X_ROT, VariantProperties.Rotation.R90)
								.with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
						.select(Direction.Axis.Z, variant("block/rope").with(VariantProperties.X_ROT, VariantProperties.Rotation.R90))));

		generator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(BlockInit.BOTANIST_WORKBENCH)
				.with(PropertyDispatch.property(BlockStateProperties.HORIZONTAL_FACING)
						.select(Direction.NORTH, variant("block/botanist_workbench"))
						.select(Direction.EAST, variant("block/botanist_workbench").with(VariantProperties.Y_ROT, VariantProperties.Rotation.R90))
						.select(Direction.SOUTH, variant("block/botanist_workbench").with(VariantProperties.Y_ROT, VariantProperties.Rotation.R180))
						.select(Direction.WEST, variant("block/botanist_workbench").with(VariantProperties.Y_ROT, VariantProperties.Rotation.R270))));

		generator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(BlockInit.LAMP_JAR)
				.with(PropertyDispatch.property(BlockStateProperties.HANGING)
						.select(true, variant("block/lamps/lamp_jar"))
						.select(false, variant("block/lamps/standing_lamp_jar"))));

		generator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(BlockInit.LAMP_BAMBOO)
				.with(PropertyDispatch.properties(BlockStateProperties.HANGING, LampBamboo.ON)
						.select(true, true, variant("block/lamps/lamp_bamboo"))
						.select(false, true, variant("block/lamps/standing_lamp_bamboo"))
						.select(true, false, variant("block/lamps/lamp_bamboo_off"))
						.select(false, false, variant("block/lamps/standing_lamp_bamboo_off"))));

		generator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(BlockInit.LAMP_LIGHT_BULB)
				.with(PropertyDispatch.properties(BlockStateProperties.HANGING, LampLightBulb.ON)
						.select(true, true, variant("block/lamps/light_bulb"))
						.select(false, true, variant("block/lamps/standing_light_bulb"))
						.select(true, false, variant("block/lamps/light_bulb_off"))
						.select(false, false, variant("block/lamps/standing_light_bulb_off"))));

		var bookstack = PropertyDispatch.properties(BookStack.BOOKSTACK_MODEL, BlockStateProperties.HORIZONTAL_FACING);
		for (int model = 0; model < 7; model++) {
			for (Direction facing : Direction.Plane.HORIZONTAL) {
				bookstack = bookstack.select(model, facing, facing == Direction.NORTH
						? variant("block/bookstacks/bookstack_" + model)
						: variant("block/bookstacks/bookstack_" + model).with(VariantProperties.Y_ROT, rotation(facing)));
			}
		}
		generator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(BlockInit.BOOKSTACK).with(bookstack));

		blinds(generator, BlockInit.OAK_BLINDS, "oak_blinds");
		blinds(generator, BlockInit.SPRUCE_BLINDS, "spruce_blinds");
		blinds(generator, BlockInit.BIRCH_BLINDS, "birch_blinds");
		blinds(generator, BlockInit.JUNGLE_BLINDS, "jungle_blinds");
		blinds(generator, BlockInit.ACACIA_BLINDS, "acacia_blinds");
		blinds(generator, BlockInit.DARK_OAK_BLINDS, "dark_oak_blinds");
		blinds(generator, BlockInit.CRIMSON_BLINDS, "crimson_blinds");
		blinds(generator, BlockInit.WARPED_BLINDS, "warped_blinds");
		blinds(generator, BlockInit.CHERRY_BLINDS, "cherry_blinds");
		blinds(generator, BlockInit.MANGROVE_BLINDS, "mangrove_blinds");
		blinds(generator, BlockInit.BAMBOO_BLINDS, "bamboo_blinds");
		//? if >=1.21.11 {
		blinds(generator, BlockInit.PALE_OAK_BLINDS, "pale_oak_blinds");
		//?}
		blinds(generator, BlockInit.IRON_BLINDS, "iron_blinds");

		candelabra(generator, BlockInit.LAMP_CANDELABRA, "");
		candelabra(generator, BlockInit.LAMP_CANDELABRA_LIGHT_BLUE, "_light_blue");
		candelabra(generator, BlockInit.LAMP_CANDELABRA_LIGHT_GRAY, "_light_gray");
		candelabra(generator, BlockInit.LAMP_CANDELABRA_BLACK, "_black");
		candelabra(generator, BlockInit.LAMP_CANDELABRA_BLUE, "_blue");
		candelabra(generator, BlockInit.LAMP_CANDELABRA_BROWN, "_brown");
		candelabra(generator, BlockInit.LAMP_CANDELABRA_CYAN, "_cyan");
		candelabra(generator, BlockInit.LAMP_CANDELABRA_GRAY, "_gray");
		candelabra(generator, BlockInit.LAMP_CANDELABRA_GREEN, "_green");
		candelabra(generator, BlockInit.LAMP_CANDELABRA_LIME, "_lime");
		candelabra(generator, BlockInit.LAMP_CANDELABRA_MAGENTA, "_magenta");
		candelabra(generator, BlockInit.LAMP_CANDELABRA_ORANGE, "_orange");
		candelabra(generator, BlockInit.LAMP_CANDELABRA_PINK, "_pink");
		candelabra(generator, BlockInit.LAMP_CANDELABRA_PURPLE, "_purple");
		candelabra(generator, BlockInit.LAMP_CANDELABRA_RED, "_red");
		candelabra(generator, BlockInit.LAMP_CANDELABRA_WHITE, "_white");
		candelabra(generator, BlockInit.LAMP_CANDELABRA_YELLOW, "_yellow");

		pot(generator);
		frame(generator, BlockInit.OAK_PICTURE_FRAME, "oak");
		frame(generator, BlockInit.SPRUCE_PICTURE_FRAME, "spruce");
		frame(generator, BlockInit.BIRCH_PICTURE_FRAME, "birch");
		frame(generator, BlockInit.JUNGLE_PICTURE_FRAME, "jungle");
		frame(generator, BlockInit.ACACIA_PICTURE_FRAME, "acacia");
		frame(generator, BlockInit.DARK_OAK_PICTURE_FRAME, "dark_oak");
		frame(generator, BlockInit.MANGROVE_PICTURE_FRAME, "mangrove");
		frame(generator, BlockInit.CRIMSON_PICTURE_FRAME, "crimson");
		frame(generator, BlockInit.WARPED_PICTURE_FRAME, "warped");
		frame(generator, BlockInit.CHERRY_PICTURE_FRAME, "cherry");
		frame(generator, BlockInit.QUARTZ_PICTURE_FRAME, "quartz");
		frame(generator, BlockInit.BAMBOO_PICTURE_FRAME, "bamboo");
		//? if >=1.21.11 {
		frame(generator, BlockInit.PALE_OAK_PICTURE_FRAME, "pale_oak");
		//?}

		trellis(generator, BlockInit.OAK_TRELLIS, "oak");
		trellis(generator, BlockInit.SPRUCE_TRELLIS, "spruce");
		trellis(generator, BlockInit.BIRCH_TRELLIS, "birch");
		trellis(generator, BlockInit.JUNGLE_TRELLIS, "jungle");
		trellis(generator, BlockInit.ACACIA_TRELLIS, "acacia");
		trellis(generator, BlockInit.DARK_OAK_TRELLIS, "dark_oak");
		trellis(generator, BlockInit.CRIMSON_TRELLIS, "crimson");
		trellis(generator, BlockInit.WARPED_TRELLIS, "warped");
		trellis(generator, BlockInit.CHERRY_TRELLIS, "cherry");
		trellis(generator, BlockInit.MANGROVE_TRELLIS, "mangrove");
		trellis(generator, BlockInit.BAMBOO_TRELLIS, "bamboo");
		//? if >=1.21.11 {
		trellis(generator, BlockInit.PALE_OAK_TRELLIS, "pale_oak");
		//?}
	}

	private static void blinds(BlockModelGenerators generator, Block block, String prefix) {
		var dispatch = PropertyDispatch.properties(Blinds.OPEN, BlockStateProperties.HORIZONTAL_FACING, Blinds.HIDDEN);
		for (boolean open : new boolean[]{false, true}) {
			for (Direction facing : Direction.Plane.HORIZONTAL) {
				for (boolean hidden : new boolean[]{false, true}) {
					Variant variant = hidden
							? variant("block/blinds/none")
							: variant("block/blinds/" + prefix + (open ? "_down" : "_up"));
					if (!hidden && facing != Direction.NORTH) {
						variant = variant.with(VariantProperties.Y_ROT, rotation(facing));
					}
					dispatch = dispatch.select(open, facing, hidden, variant);
				}
			}
		}
		generator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block).with(dispatch));
	}
	private static void candelabra(BlockModelGenerators generator, Block block, String suffix) {
		var dispatch = PropertyDispatch.properties(LampCandelabra.ON, BlockStateProperties.HORIZONTAL_FACING, BlockStateProperties.HANGING);
		for (boolean on : new boolean[]{false, true}) {
			for (Direction facing : Direction.Plane.HORIZONTAL) {
				for (boolean hanging : new boolean[]{false, true}) {
					String model = (hanging ? "lamp_candelabra" : "standing_lamp_candelabra") + (on ? "" : "_off") + suffix;
					Variant variant = variant("block/lamps/candelabras/" + model);
					if (facing != Direction.NORTH) {
						variant = variant.with(VariantProperties.Y_ROT, rotation(facing));
					}
					dispatch = dispatch.select(on, facing, hanging, variant);
				}
			}
		}
		generator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block).with(dispatch));
	}
	private static void frame(BlockModelGenerators generator, Block block, String wood) {
		var dispatch = PropertyDispatch.properties(PictureFrame.FRAME_MOTIVE, BlockStateProperties.HORIZONTAL_FACING, PictureFrame.DIAGONAL);
		for (int motive = 0; motive < PictureFrame.MOTIVE_MODELS.size(); motive++) {
			for (Direction facing : Direction.Plane.HORIZONTAL) {
				for (boolean diagonal : new boolean[]{false, true}) {
					String model = "block/pictureframes/" + wood + "_" + PictureFrame.MOTIVE_MODELS.get(motive) + "_picture_frame" + (diagonal ? "_diagonal" : "");
					Variant variant = variant(model);
					if (facing != Direction.NORTH) {
						variant = variant.with(VariantProperties.Y_ROT, rotation(facing));
					}
					dispatch = dispatch.select(motive, facing, diagonal, variant);
				}
			}
		}
		generator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block).with(dispatch));
	}
	private static void pot(BlockModelGenerators generator) {
		var dispatch = PropertyDispatch.properties(HangingPot.POTFLOWER, BlockStateProperties.HANGING, HangingPot.GROWN);
		for (int potflower = 0; potflower < HangingPot.POT_MODELS.size(); potflower++) {
			for (boolean hanging : new boolean[]{false, true}) {
				for (boolean grown : new boolean[]{false, true}) {
					dispatch = dispatch.select(potflower, hanging, grown, variant(HangingPot.potModel(potflower, hanging, grown)));
				}
			}
		}
		generator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(BlockInit.HANGING_POT).with(dispatch));
	}
	private static void trellis(BlockModelGenerators generator, Block block, String wood) {
		var dispatch = PropertyDispatch.properties(BlockStateProperties.HORIZONTAL_FACING, Trellis.CEILLING, Trellis.FLOWERS);
		for (Direction facing : Direction.Plane.HORIZONTAL) {
			for (boolean ceiling : new boolean[]{false, true}) {
				for (int flowers = 0; flowers < Trellis.FLOWER_SUFFIXES.size(); flowers++) {
					String model = "block/trellis/" + wood + "/" + (ceiling ? "open_trellis" : "closed_trellis") + Trellis.FLOWER_SUFFIXES.get(flowers);
					Variant variant = variant(model);
					if (!ceiling && facing != Direction.EAST) {
						variant = variant.with(VariantProperties.Y_ROT, closedRotation(facing));
					}
					dispatch = dispatch.select(facing, ceiling, flowers, variant);
				}
			}
		}
		generator.blockStateOutput.accept(MultiVariantGenerator.multiVariant(block).with(dispatch));
	}

	private static VariantProperties.Rotation closedRotation(Direction facing) {
		return switch (facing) {
			case EAST -> VariantProperties.Rotation.R0;
			case SOUTH -> VariantProperties.Rotation.R90;
			case WEST -> VariantProperties.Rotation.R180;
			default -> VariantProperties.Rotation.R270;
		};
	}
	private static VariantProperties.Rotation rotation(Direction facing) {
		return switch (facing) {
			case EAST -> VariantProperties.Rotation.R90;
			case SOUTH -> VariantProperties.Rotation.R180;
			case WEST -> VariantProperties.Rotation.R270;
			default -> VariantProperties.Rotation.R0;
		};
	}

	private static Variant variant(String model) {
		return Variant.variant().with(VariantProperties.MODEL, Beautify.id(model));
	}

	@Override
	public void generateItemModels(ItemModelGenerators generator) {
	}
}
*///?}
