//? if fabric {
package io.github.suel_ki.beautify.client;

//? if <26.1.2
//import io.github.suel_ki.beautify.client.block.ClientBlockInit;
//? if <1.21.11
//import io.github.suel_ki.beautify.client.model.DiagonalFrameModels;
import io.github.suel_ki.beautify.client.tooltip.ClientPlantableItemStackTooltip;
import io.github.suel_ki.beautify.particle.ParticleInit;
//? if >=1.20.1 && <1.21.11
//import io.github.suel_ki.beautify.client.render.MultiFlowerPotRenderer;
//? if >=1.21.11
import io.github.suel_ki.beautify.client.render.MultiFlowerPotRendererModern;
//? if fabric && >=1.20.1
import io.github.suel_ki.beautify.core.init.BlockInit;
import net.fabricmc.fabric.api.client.rendering.v1.BlockEntityRendererRegistry;
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
        //? if >=1.20.1 && <1.21.11 {
        /*BlockEntityRendererRegistry.register(
                BlockInit.MULTI_FLOWER_POT_BE, context -> new MultiFlowerPotRenderer());
        *///?}
        //? if >=1.21.11 {
        BlockEntityRendererRegistry.register(
                BlockInit.MULTI_FLOWER_POT_BE, context -> new MultiFlowerPotRendererModern(context));
        //?}
        //? if <1.21.11
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
import io.github.suel_ki.beautify.core.init.BlockInit;
import io.github.suel_ki.beautify.client.tooltip.ClientPlantableItemStackTooltip;
//? if <1.21.11
//import io.github.suel_ki.beautify.client.render.MultiFlowerPotRenderer;
//? if >=1.21.11
//import io.github.suel_ki.beautify.client.render.MultiFlowerPotRendererModern;
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
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
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

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        //? if <1.21.11
        //event.registerBlockEntityRenderer(BlockInit.MULTI_FLOWER_POT_BE.get(), context -> new MultiFlowerPotRenderer());
        //? if >=1.21.11
        //event.registerBlockEntityRenderer(BlockInit.MULTI_FLOWER_POT_BE.get(), MultiFlowerPotRendererModern::new);
    }
}
*///?}
