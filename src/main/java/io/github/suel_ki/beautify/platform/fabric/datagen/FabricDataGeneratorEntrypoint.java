//? if fabric {
package io.github.suel_ki.beautify.platform.fabric.datagen;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class FabricDataGeneratorEntrypoint implements DataGeneratorEntrypoint {

	@Override
	public void onInitializeDataGenerator(FabricDataGenerator generator) {
		FabricDataGenerator.Pack pack = generator.createPack();
		pack.addProvider(BeautifyBlockTagProvider::new);
		pack.addProvider(BeautifyPoiTagProvider::new);
		pack.addProvider(BeautifyModelProvider::new);
		pack.addProvider(BeautifyModelJsonProvider::new);
		//? if >=1.21.1 && <26.1.2 {
		//pack.addProvider(BeautifyChestLootProvider::new);
		//?}
		//? if >=26.1.2 {
		pack.addProvider(BeautifyModernChestLootProvider::new);
		//?}
		//? if >=1.21.1 {
		pack.addProvider(BeautifyBlockLootProvider::new);
		pack.addProvider(BeautifyRecipeProvider::new);
		pack.addProvider(BeautifyAdvancementProvider::new);
		//?}
		//? if <1.21.1 {
		/*pack.addProvider(BeautifyLegacyAdvancementProvider::new);
		pack.addProvider(BeautifyLegacyRecipeProvider::new);
		pack.addProvider(BeautifyLegacyLootProvider::new);
		pack.addProvider(BeautifyLegacyChestLootProvider::new);
		*///?}
	}
}
//?}
