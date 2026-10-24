//? if fabric {
package io.github.suel_ki.beautify.platform.fabric.datagen;

import io.github.suel_ki.beautify.Beautify;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
//? if >=26.1.2 {
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
//?} else {
/*import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
*///?}
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.Identifier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.ai.village.poi.PoiType;

import java.util.concurrent.CompletableFuture;

//? if >=26.1.2 {
public class BeautifyPoiTagProvider extends FabricTagsProvider<PoiType> {
//?} else {
/*public class BeautifyPoiTagProvider extends FabricTagProvider<PoiType> {
*///?}

	private static final TagKey<PoiType> ACQUIRABLE_JOB_SITE =
			TagKey.create(Registries.POINT_OF_INTEREST_TYPE,
					//? if >=1.21.1
					Identifier.fromNamespaceAndPath("minecraft", "acquirable_job_site"));
					//? if <1.21.1
					//new Identifier("minecraft", "acquirable_job_site"));

	private static final ResourceKey<PoiType> BOTANIST_WORKBENCH_POI =
			ResourceKey.create(Registries.POINT_OF_INTEREST_TYPE, Beautify.id("botanist_workbench_poi"));

	public BeautifyPoiTagProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
		super(output, Registries.POINT_OF_INTEREST_TYPE, registries);
	}

	@Override
	protected void addTags(HolderLookup.Provider provider) {
		//? if >=1.21.8
		builder(ACQUIRABLE_JOB_SITE).add(BOTANIST_WORKBENCH_POI);
		//? if <1.21.8
		//getOrCreateTagBuilder(ACQUIRABLE_JOB_SITE).add(BOTANIST_WORKBENCH_POI);
	}
}
//?}
