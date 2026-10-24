//? if fabric {
package io.github.suel_ki.beautify.particle;

import io.github.suel_ki.beautify.Beautify;
import io.github.suel_ki.beautify.particle.custom.GlowEssenceParticles;
//? if >=26.1.2
import net.fabricmc.fabric.api.client.particle.v1.ParticleProviderRegistry;
//? if <26.1.2
//import net.fabricmc.fabric.api.client.particle.v1.ParticleFactoryRegistry;
import net.fabricmc.fabric.api.particle.v1.FabricParticleTypes;
import net.minecraft.core.Registry;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.BuiltInRegistries;

public class ParticleInit {

	public static final SimpleParticleType GLOWESSENCE_PARTICLES = register("glowessence_particles",
			FabricParticleTypes.simple());

	private static <T extends ParticleType<?>> T register(String name, T particle) {
		return Registry.register(BuiltInRegistries.PARTICLE_TYPE, Beautify.id(name), particle);
	}

	public static SimpleParticleType get(SimpleParticleType particle) {
		return particle;
	}

	//? if >=1.21.11 {
	public static void ensureLoadedServerside() {
	}
	//?}

	public static void registerParticle() {
		//? if >=26.1.2
		ParticleProviderRegistry.getInstance().register(GLOWESSENCE_PARTICLES, GlowEssenceParticles.Provider::new);
		//? if <26.1.2
		//ParticleFactoryRegistry.getInstance().register(GLOWESSENCE_PARTICLES, GlowEssenceParticles.Provider::new);
	}

}
//?} else {
/*package io.github.suel_ki.beautify.particle;

import io.github.suel_ki.beautify.Beautify;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ParticleInit {
	public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES = DeferredRegister
			.create(Registries.PARTICLE_TYPE, Beautify.MODID);

	public static final DeferredHolder<ParticleType<?>, SimpleParticleType> GLOWESSENCE_PARTICLES = PARTICLE_TYPES
			.register("glowessence_particles", () -> new SimpleParticleType(true));

	public static SimpleParticleType get(DeferredHolder<ParticleType<?>, SimpleParticleType> particle) {
		return particle.get();
	}
}
*///?}
