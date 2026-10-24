package io.github.suel_ki.beautify.particle.custom;

//? if fabric {
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
//?}
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.core.particles.SimpleParticleType;

//? if >=1.21.11 {
import net.minecraft.client.particle.SingleQuadParticle;
import net.minecraft.util.RandomSource;
import org.jetbrains.annotations.NotNull;
//?}

//? if <1.21.11 {
/*import net.minecraft.client.particle.ParticleRenderType;
import net.minecraft.client.particle.TextureSheetParticle;
*///?}

//? if fabric
@Environment(EnvType.CLIENT)
//? if >=1.21.11 {
public class GlowEssenceParticles extends SingleQuadParticle {
//?} else {
/*public class GlowEssenceParticles extends TextureSheetParticle {
*///?}

	private static final float size = 0.07f;

	//? if >=1.21.11 {
	protected GlowEssenceParticles(ClientLevel level, double xCoord, double yCoord, double zCoord,
			double xd, double yd, double zd, SpriteSet spriteSet) {
		super(level, xCoord, yCoord, zCoord, xd, yd, zd, spriteSet.first());
//?} else {
	/*protected GlowEssenceParticles(ClientLevel level, double xCoord, double yCoord, double zCoord, SpriteSet spriteSet,
			double xd, double yd, double zd) {
		super(level, xCoord, yCoord, zCoord, xd, yd, zd);
*///?}

		this.friction = 0.8F;
		this.xd = xd;
		this.yd = yd;
		this.zd = zd;
		this.quadSize = 0;
		this.lifetime = (int) (60 * (0.5 + Math.random() / 2));
		this.setSpriteFromAge(spriteSet);

		this.rCol = 1f;
		this.gCol = 1f;
		this.bCol = 1f;
	}

		//? if >=1.21.11 {
		@Override
		protected @NotNull Layer getLayer() {
			return Layer.OPAQUE;
		}
//?} else {
		/*@Override
		public ParticleRenderType getRenderType() {
			//? if fabric
			//return ParticleRenderType.PARTICLE_SHEET_LIT;
			//? if neoforge
			return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
		}
*///?}

	private void fadeOut() {
		float fadeValue = (float) Math.sin(Math.PI * ((float) this.age / this.lifetime));

		this.alpha = 1 * fadeValue;
		this.quadSize = size * fadeValue;
	}

	private void move() {
		if (Math.random() <= 0.05) {
			this.xd = (Math.random() * 2 - 1) / 70;
		}
		if (Math.random() <= 0.05) {
			this.yd = (Math.random() * 2 - 1) / 70;
		}
		if (Math.random() <= 0.05) {
			this.zd = (Math.random() * 2 - 1) / 70;
		}
	}

	@Override
	public void tick() {
		super.tick();

		this.fadeOut();
		this.move();
	}

	//? if fabric
	@Environment(EnvType.CLIENT)
	public static class Provider implements ParticleProvider<SimpleParticleType> {
		private final SpriteSet sprites;

		public Provider(SpriteSet spriteSet) {
			this.sprites = spriteSet;
		}

		public Particle createParticle(SimpleParticleType particleType, ClientLevel level, double x, double y, double z,
				//? if >=1.21.11 {
				double dx, double dy, double dz, RandomSource randomSource) {
			return new GlowEssenceParticles(level, x, y, z, dx, dy, dz, this.sprites);
//?} else {
				/*double dx, double dy, double dz) {
			return new GlowEssenceParticles(level, x, y, z, this.sprites, dx, dy, dz);
*///?}
		}
	}
}
