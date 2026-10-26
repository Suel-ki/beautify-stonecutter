package io.github.suel_ki.beautify;

import io.github.suel_ki.beautify.common.world.VillageAdditions;
import io.github.suel_ki.beautify.util.BeautifyConfig;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//? if fabric {
import io.github.suel_ki.beautify.core.init.*;
import io.github.suel_ki.beautify.particle.ParticleInit;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
//? if <1.21.11 {
/*import io.github.suel_ki.beautify.compat.every_compat.EveryCompatIntegration;
import net.fabricmc.loader.api.FabricLoader;
*///?}
//?} else {
/*import io.github.suel_ki.beautify.core.init.*;
import io.github.suel_ki.beautify.particle.ParticleInit;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerAboutToStartEvent;
//? if <1.21.11 {
/^import io.github.suel_ki.beautify.compat.every_compat.EveryCompatIntegration;
import net.neoforged.fml.ModList;
^///?}
*///?}

//? if neoforge
//@Mod(Beautify.MODID)
public class Beautify
//? if fabric {
		implements ModInitializer
//?}
{

	public static final String MODID = "beautify";

	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

//? if fabric {
	public static BeautifyConfig CONFIG;

	@Override
	public void onInitialize() {
		AutoConfig.register(BeautifyConfig.class, GsonConfigSerializer::new);
		CONFIG = AutoConfig.getConfigHolder(BeautifyConfig.class).getConfig();

		//? if >=1.21.11
		ComponentInit.init();

		//? if <26.1.2
		//ItemInitFabric.registerFuel();

		//? if <26.1.2
		//TradesInit.addCustomTrades();

		BlockInit.registerFlammableBlock();

		//? if >=1.21.11
		ParticleInit.ensureLoadedServerside();

		//? if >=26.1.2
		ModVillagers.init();

		//? if <1.21.11 {
		/*if (FabricLoader.getInstance().isModLoaded("everycomp")) {
			EveryCompatIntegration.register();
		}
		*///?}

		Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, Beautify.id("group"), CreativeModeTabInit.BEAUTIFY_TAB);
		ServerLifecycleEvents.SERVER_STARTED.register(server -> VillageAdditions.addBotanistHouses(server.registryAccess()));
	}
//?}

//? if neoforge {
	/*public Beautify(IEventBus bus, ModContainer modContainer) {
		//? if <1.21.11 {
		/^if (ModList.get().isLoaded("everycomp")) {
			EveryCompatIntegration.register();
		}
		^///?}
		BlockInit.BLOCKS.register(bus);
		ItemInitNeoForge.ITEMS.register(bus);
		ModVillagers.POI_TYPES.register(bus);
		ModVillagers.VILLAGER_PROFESSIONS.register(bus);
		SoundInit.SOUND_EVENTS.register(bus);
		ParticleInit.PARTICLE_TYPES.register(bus);
		CreativeModeTabInit.CREATIVE_MODE_TABS.register(bus);
		//? if >=1.21.11
		ComponentInit.DATA_COMPONENT_TYPE.register(bus);

		//? if >=26.3
		modContainer.registerConfig(ModConfig.Type.SYNCED, BeautifyConfig.SPEC);
		//? if <26.3
		//modContainer.registerConfig(ModConfig.Type.SERVER, BeautifyConfig.SPEC);
		NeoForge.EVENT_BUS.addListener((ServerAboutToStartEvent event) -> VillageAdditions.addBotanistHouses(event.getServer().registryAccess()));
	}
*///?}

	public static Identifier id(String name) {
		//? if fabric && <1.21.1
		//return new Identifier(MODID, name);
		//? if neoforge || >=1.21.1
		return Identifier.fromNamespaceAndPath(MODID, name);
	}
}
