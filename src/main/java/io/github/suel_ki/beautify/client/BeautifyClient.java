//? if fabric {
package io.github.suel_ki.beautify.client;

//? if <26.1.2
//import io.github.suel_ki.beautify.client.block.ClientBlockInit;
//? if <1.21.11
//import io.github.suel_ki.beautify.client.model.DiagonalFrameModels;
import io.github.suel_ki.beautify.client.tooltip.ClientPlantableItemStackTooltip;
import io.github.suel_ki.beautify.particle.ParticleInit;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
//? if <1.21.11
//import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
//? if >=26.1.2 {
import net.fabricmc.fabric.api.client.rendering.v1.ClientTooltipComponentCallback;
//?} else {
//import net.fabricmc.fabric.api.client.rendering.v1.TooltipComponentCallback;
//?}

@Environment(EnvType.CLIENT)
public class BeautifyClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        ParticleInit.registerParticle();
        //? if <26.1.2
        //ClientBlockInit.registerBlockRenderLayer();
        //? if >=26.1.2 {
        ClientTooltipComponentCallback.EVENT.register(ClientPlantableItemStackTooltip::get);
        //?} else {
        //TooltipComponentCallback.EVENT.register(ClientPlantableItemStackTooltip::get);
        //?}
        //? if <1.21.11
        //ModelLoadingPlugin.register(new DiagonalFrameModels());
    }
}
//?} else {
/*package io.github.suel_ki.beautify.client;

import io.github.suel_ki.beautify.Beautify;
import io.github.suel_ki.beautify.client.tooltip.ClientPlantableItemStackTooltip;
import io.github.suel_ki.beautify.common.tooltip.PlantableItemStackTooltip;
import io.github.suel_ki.beautify.particle.ParticleInit;
import io.github.suel_ki.beautify.particle.custom.GlowEssenceParticles;
import io.github.suel_ki.beautify.client.config.ConfigScreen;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModList;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.RegisterClientTooltipComponentFactoriesEvent;
import net.neoforged.neoforge.client.event.RegisterParticleProvidersEvent;

@Mod(value = Beautify.MODID, dist = Dist.CLIENT)
@EventBusSubscriber(modid = Beautify.MODID, value = Dist.CLIENT)
public class BeautifyClient  {

    public BeautifyClient(ModContainer container) {
        if (ModList.get().isLoaded("cloth_config")) ConfigScreen.register(container);
    }

    @SubscribeEvent
    public static void registerParticleFactories(RegisterParticleProvidersEvent event){
        event.registerSpriteSet(ParticleInit.get(ParticleInit.GLOWESSENCE_PARTICLES), GlowEssenceParticles.Provider::new);
    }

    @SubscribeEvent
    public static void registerTooltipComponent(RegisterClientTooltipComponentFactoriesEvent event) {
        event.register(PlantableItemStackTooltip.class, ClientPlantableItemStackTooltip::get);
    }
}
*///?}
