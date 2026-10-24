//? if fabric {
package io.github.suel_ki.beautify.platform.fabric;

import io.github.suel_ki.beautify.client.BeautifyClient;
import net.fabricmc.api.ClientModInitializer;

public class FabricClientEntrypoint implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		new BeautifyClient().onInitializeClient();
	}
}
//?}
