package io.github.suel_ki.beautify.common.world;

import com.mojang.datafixers.util.Pair;
import io.github.suel_ki.beautify.util.BeautifyConfig;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.structure.pools.SinglePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructurePoolElement;
import net.minecraft.world.level.levelgen.structure.pools.StructureTemplatePool;
import net.minecraft.world.level.levelgen.structure.templatesystem.StructureProcessorList;

import java.util.ArrayList;
import java.util.List;

public final class VillageAdditions {

	private static final ResourceKey<StructureProcessorList> EMPTY_PROCESSOR_LIST_KEY = key("empty");

	private static ResourceKey<StructureProcessorList> key(String path) {
		//? if >=1.21
		return ResourceKey.create(Registries.PROCESSOR_LIST, Identifier.withDefaultNamespace(path));
		//? if <1.21
		//return ResourceKey.create(Registries.PROCESSOR_LIST, new Identifier("minecraft", path));
	}

	private VillageAdditions() {
	}

	public static void addBotanistHouses(RegistryAccess access) {
		//? if >=1.21.11 {
		Registry<StructureTemplatePool> pools = access.lookupOrThrow(Registries.TEMPLATE_POOL);
		Registry<StructureProcessorList> processors = access.lookupOrThrow(Registries.PROCESSOR_LIST);
		//?} else {
		/*Registry<StructureTemplatePool> pools = access.registry(Registries.TEMPLATE_POOL).orElseThrow();
		Registry<StructureProcessorList> processors = access.registry(Registries.PROCESSOR_LIST).orElseThrow();
		*///?}

		int weight = BeautifyConfig.botanistSpawnWeight();

		//? if >=1.21 {
		add(pools, processors, Identifier.withDefaultNamespace("village/plains/houses"), "beautify:botanist_house_plains", weight);
		add(pools, processors, Identifier.withDefaultNamespace("village/snowy/houses"), "beautify:botanist_house_snowy", weight);
		add(pools, processors, Identifier.withDefaultNamespace("village/savanna/houses"), "beautify:botanist_house_savanna", weight);
		add(pools, processors, Identifier.withDefaultNamespace("village/taiga/houses"), "beautify:botanist_house_taiga", weight);
		//?} else {
		/*add(pools, processors, new Identifier("minecraft", "village/plains/streets"), "beautify:botanist_house_plains", weight);
		add(pools, processors, new Identifier("minecraft", "village/snowy/streets"), "beautify:botanist_house_snowy", weight);
		add(pools, processors, new Identifier("minecraft", "village/savanna/streets"), "beautify:botanist_house_savanna", weight);
		add(pools, processors, new Identifier("minecraft", "village/taiga/streets"), "beautify:botanist_house_taiga", weight);
		*///?}
	}

	private static void add(Registry<StructureTemplatePool> pools, Registry<StructureProcessorList> processors,
			Identifier poolId, String piece, int weight) {
		//? if >=1.21.11
		Holder<StructureProcessorList> empty = processors.getOrThrow(EMPTY_PROCESSOR_LIST_KEY);
		//? if <1.21.11
		//Holder<StructureProcessorList> empty = processors.getHolderOrThrow(EMPTY_PROCESSOR_LIST_KEY);

		//? if >=1.21.2
		StructureTemplatePool pool = pools.getValue(poolId);
		//? if <1.21.2
		//StructureTemplatePool pool = pools.get(poolId);
		if (pool == null) {
			return;
		}

		SinglePoolElement element = SinglePoolElement.legacy(piece, empty).apply(StructureTemplatePool.Projection.RIGID);
		for (int i = 0; i < weight; i++) {
			pool.templates.add(element);
		}

		List<Pair<StructurePoolElement, Integer>> entries = new ArrayList<>(pool.rawTemplates);
		entries.add(new Pair<>(element, weight));
		pool.rawTemplates = entries;
	}
}
