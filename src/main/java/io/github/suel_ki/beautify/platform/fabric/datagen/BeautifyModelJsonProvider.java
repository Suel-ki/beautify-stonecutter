//? if fabric {
package io.github.suel_ki.beautify.platform.fabric.datagen;

import com.google.gson.JsonObject;
import io.github.suel_ki.beautify.common.block.HangingPot;
import io.github.suel_ki.beautify.common.block.PictureFrame;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class BeautifyModelJsonProvider implements DataProvider {

	private static final String[] FRAME_WOODS = {
			"oak", "spruce", "birch", "jungle", "acacia", "dark_oak", "mangrove", "crimson", "warped", "cherry", "quartz", "bamboo"
			//? if >=1.21.11 {
			, "pale_oak"
			//?}
	};

	private static final String[] BLINDS_WOODS = {
			"spruce", "birch", "jungle", "acacia", "dark_oak", "mangrove", "crimson", "warped", "cherry", "bamboo"
			//? if >=1.21.11 {
			, "pale_oak"
			//?}
			, "iron"
	};

	private static final String[] CANDELABRA_COLORS = {
			"", "white", "orange", "magenta", "light_blue", "yellow", "lime", "pink", "gray", "light_gray",
			"cyan", "purple", "blue", "brown", "green", "red", "black"
	};

	private static final String[] TRELLIS_ITEM_WOODS = {
			"oak", "spruce", "birch", "jungle", "acacia", "dark_oak", "crimson", "warped", "cherry", "mangrove", "bamboo"
			//? if >=1.21.11 {
			, "pale_oak"
			//?}
	};

	private static final String[] BLINDS_ITEM_WOODS = {
			"oak", "spruce", "birch", "jungle", "acacia", "dark_oak", "crimson", "warped", "cherry", "mangrove", "bamboo"
			//? if >=1.21.11 {
			, "pale_oak"
			//?}
			, "iron"
	};

	private static final String[] FRAME_ITEM_WOODS = {
			"oak", "spruce", "birch", "jungle", "acacia", "dark_oak", "crimson", "warped", "cherry", "mangrove", "quartz", "bamboo"
			//? if >=1.21.11 {
			, "pale_oak"
			//?}
	};

	private static final String[] CANDELABRA_ITEM_COLORS = {
			"", "white", "orange", "magenta", "light_blue", "yellow", "lime", "pink", "gray", "light_gray",
			"cyan", "purple", "blue", "brown", "green", "red", "black"
	};
	private final FabricPackOutput output;

	public BeautifyModelJsonProvider(FabricPackOutput output) {
		this.output = output;
	}

	@Override
	public CompletableFuture<?> run(CachedOutput cache) {
		List<CompletableFuture<?>> futures = new ArrayList<>();
		for (int potflower = 1; potflower < HangingPot.POT_MODELS.size(); potflower++) {
			String entry = HangingPot.POT_MODELS.get(potflower);
			String folder = entry.substring(0, entry.indexOf('/'));
			String name = entry.substring(entry.indexOf('/') + 1);
			boolean flat = name.startsWith("glow_lichen") || name.startsWith("vine");
			for (boolean grown : new boolean[]{false, true}) {
				String suffix = grown ? "_grown" : "";
				String texture = "beautify:block/pots/" + name + suffix;
				Map<String, String> textures = new LinkedHashMap<>();
				if (flat) {
					textures.put("1", texture);
					textures.put("2", "beautify:block/pots/" + name + "_overlay" + suffix);
				} else {
					textures.put("0", texture);
				}
				textures.put("particle", "minecraft:block/terracotta");
				futures.add(save(cache, "block/pots/" + folder + "/" + name + suffix,
						"beautify:block/pots/base/" + (flat ? "hanging_pot_flat" : "hanging_pot") + suffix, textures));
				futures.add(save(cache, "block/pots/" + folder + "/standing_" + name + suffix,
						"beautify:block/pots/base/" + (flat ? "standing_pot_flat" : "standing_pot") + suffix, textures));
			}
		}
		for (String wood : FRAME_WOODS) {
			String frameTexture = wood.equals("pale_oak") ? "pale" : wood;
			for (String motive : PictureFrame.MOTIVE_MODELS) {
				for (boolean diagonal : new boolean[]{false, true}) {
					Map<String, String> textures = new LinkedHashMap<>();
					textures.put("0", "beautify:block/motive_" + motive);
					textures.put("1", "beautify:block/" + frameTexture + "_frame_texture");
					textures.put("particle", "beautify:block/" + frameTexture + "_frame_texture");
					futures.add(save(cache, "block/pictureframes/" + wood + "_" + motive + "_picture_frame" + (diagonal ? "_diagonal" : ""),
							"beautify:block/pictureframes/base_picture_frame" + (diagonal ? "_diagonal" : ""), textures));
				}
			}
		}
		for (String wood : BLINDS_WOODS) {
			String planks = wood.equals("iron") ? "minecraft:block/iron_block" : "minecraft:block/" + wood + "_planks";
			for (String state : new String[]{"up", "down"}) {
				Map<String, String> textures = new LinkedHashMap<>();
				textures.put("0", planks);
				textures.put("particle", planks);
				futures.add(save(cache, "block/blinds/" + wood + "_blinds_" + state,
						"beautify:block/blinds/oak_blinds_" + state, textures));
			}
		}

		for (String color : CANDELABRA_COLORS) {
			for (boolean hanging : new boolean[]{false, true}) {
				for (boolean on : new boolean[]{false, true}) {
					if (color.isEmpty() && on) {
						continue;
					}
					String base = hanging ? "lamp_candelabra" : "standing_lamp_candelabra";
					String name = base + (on ? "" : "_off") + (color.isEmpty() ? "" : "_" + color);
					Map<String, String> textures = new LinkedHashMap<>();
					textures.put("1", "minecraft:block/anvil");
					textures.put("2", "minecraft:block/" + (color.isEmpty() ? "" : color + "_") + "candle" + (on ? "_lit" : ""));
					textures.put("particle", "minecraft:block/anvil");
					futures.add(save(cache, "block/lamps/candelabras/" + name,
							"beautify:block/lamps/candelabras/" + base, textures));
				}
			}
		}
		Map<String, String> itemModels = new LinkedHashMap<>();
		for (String wood : TRELLIS_ITEM_WOODS) {
			itemModels.put(wood + "_trellis", "beautify:block/trellis/" + wood + "/closed_trellis");
		}
		for (String wood : BLINDS_ITEM_WOODS) {
			itemModels.put(wood + "_blinds", "beautify:block/blinds/" + wood + "_blinds_down");
		}
		for (String wood : FRAME_ITEM_WOODS) {
			itemModels.put(wood + "_picture_frame", "beautify:block/pictureframes/" + wood + "_flower_picture_frame");
		}
		for (String color : CANDELABRA_ITEM_COLORS) {
			String suffix = color.isEmpty() ? "" : "_" + color;
			itemModels.put("lamp_candelabra" + suffix, "beautify:block/lamps/candelabras/standing_lamp_candelabra_off" + suffix);
		}
		itemModels.put("lamp_bamboo", "beautify:block/lamps/lamp_bamboo_off");
		itemModels.put("lamp_jar", "beautify:block/lamps/standing_lamp_jar");
		itemModels.put("lamp_light_bulb", "beautify:block/lamps/light_bulb");
		itemModels.put("bookstack", "beautify:block/bookstacks/bookstack_4");
		itemModels.put("hanging_pot", "beautify:block/pots/base/hanging_pot");

		for (Map.Entry<String, String> entry : itemModels.entrySet()) {
			if (entry.getValue().equals("beautify:block/" + entry.getKey())) {
				continue;
			}
			futures.add(saveAlias(cache, entry.getKey(), entry.getValue()));
		}
		futures.add(saveRopeItemModel(cache));
		//? if >=1.21.11 {
		futures.add(saveRopeItemDefinition(cache));
		//?}
		return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
	}

	private CompletableFuture<?> saveAlias(CachedOutput cache, String id, String parent) {
		JsonObject json = new JsonObject();
		json.addProperty("parent", parent);
		Path target = this.output.getOutputFolder(PackOutput.Target.RESOURCE_PACK)
				.resolve("beautify/models/block/" + id + ".json");
		return DataProvider.saveStable(cache, json, target);
	}
	private CompletableFuture<?> saveRopeItemModel(CachedOutput cache) {
		JsonObject json = new JsonObject();
		json.addProperty("parent", "item/generated");
		JsonObject textures = new JsonObject();
		textures.addProperty("layer0", "beautify:item/rope");
		json.add("textures", textures);
		Path target = this.output.getOutputFolder(PackOutput.Target.RESOURCE_PACK)
				.resolve("beautify/models/item/rope.json");
		return DataProvider.saveStable(cache, json, target);
	}

	private CompletableFuture<?> saveRopeItemDefinition(CachedOutput cache) {
		JsonObject model = new JsonObject();
		model.addProperty("type", "minecraft:model");
		model.addProperty("model", "beautify:item/rope");
		JsonObject json = new JsonObject();
		json.add("model", model);
		Path target = this.output.getOutputFolder(PackOutput.Target.RESOURCE_PACK)
				.resolve("beautify/items/rope.json");
		try {
			Files.createDirectories(target.getParent());
			Files.writeString(target, json.toString());
		} catch (IOException e) {
			throw new UncheckedIOException(e);
		}
		return CompletableFuture.completedFuture(null);
	}

	private CompletableFuture<?> save(CachedOutput cache, String modelPath, String parent, Map<String, String> textures) {
		JsonObject json = new JsonObject();
		json.addProperty("parent", parent);
		JsonObject textureJson = new JsonObject();
		textures.forEach(textureJson::addProperty);
		json.add("textures", textureJson);
		json.addProperty("render_type", "cutout");
		Path target = this.output.getOutputFolder(PackOutput.Target.RESOURCE_PACK)
				.resolve("beautify/models/" + modelPath + ".json");
		return DataProvider.saveStable(cache, json, target);
	}

	@Override
	public String getName() {
		return "Beautify model JSON";
	}
}
//?}
