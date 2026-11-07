//? if >=26.1.2 && <26.2 {
/*package io.github.suel_ki.beautify.compat.every_compat;

import io.github.suel_ki.beautify.Beautify;
import net.mehvahdjukaar.every_compat.api.ECPlugin;
import net.mehvahdjukaar.every_compat.api.EveryCompatPlugin;
import net.mehvahdjukaar.every_compat.api.PluginEvent;

@ECPlugin
public final class BeautifyCompatPlugin implements EveryCompatPlugin {

    @Override
    public void register(PluginEvent event) {
        event.addModule(Beautify.MODID, () -> BeautifyModuleModern.class);
    }
}
*///?}
