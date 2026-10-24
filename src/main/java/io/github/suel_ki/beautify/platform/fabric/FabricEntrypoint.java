//? if fabric {
package io.github.suel_ki.beautify.platform.fabric;

import io.github.suel_ki.beautify.Beautify;
import net.fabricmc.api.ModInitializer;

public class FabricEntrypoint implements ModInitializer {

	@Override
	public void onInitialize() {
		new Beautify().onInitialize();
	}
}
//?}
