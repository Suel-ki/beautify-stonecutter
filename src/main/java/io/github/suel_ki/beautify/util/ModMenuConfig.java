//? if fabric {
package io.github.suel_ki.beautify.util;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
//? if >=1.21.11
import me.shedaniel.autoconfig.AutoConfigClient;
//? if <1.21.11
//import me.shedaniel.autoconfig.AutoConfig;

public class ModMenuConfig implements ModMenuApi {

	@Override
	public ConfigScreenFactory<?> getModConfigScreenFactory() {
		//? if >=1.21.11
		return parent -> AutoConfigClient.getConfigScreen(BeautifyConfig.class, parent).get();
		//? if <1.21.11
		//return parent -> AutoConfig.getConfigScreen(BeautifyConfig.class, parent).get();
	}
}
//?}
