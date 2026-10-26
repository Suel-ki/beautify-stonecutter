//? if fabric && <1.21.11 {
/*package io.github.suel_ki.beautify.client.model;

import com.mojang.math.Transformation;
import io.github.suel_ki.beautify.Beautify;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelModifier;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.Material;
import net.minecraft.client.resources.model.ModelBaker;
//? if >=1.21.1
import net.minecraft.client.resources.model.ModelIdentifier;
import net.minecraft.client.resources.model.ModelState;
import net.minecraft.client.resources.model.UnbakedModel;
import net.minecraft.resources.Identifier;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;

import java.util.Collection;
import java.util.function.Function;

@Environment(EnvType.CLIENT)
public class DiagonalFrameModels implements ModelLoadingPlugin {
    private static final String DIAGONAL_SUFFIX = "_picture_frame_diagonal";
    private static final String DIAGONAL_BASE_PATH = "base_picture_frame_diagonal";
    private static final float YAW_DEGREES = -45.0F;

    @Override
    public void onInitializeModelLoader(Context pluginContext) {
        // before bake, not on load: the wrapper must never end up being some model's parent
        pluginContext.modifyModelBeforeBake().register(ModelModifier.WRAP_PHASE, (model, context) ->
                //? if >=1.21.1 {
                isDiagonalFrame(context) ? new TurnedModel(model, YAW_DEGREES) : model);
                //?} else {
                //isDiagonalFrame(context.id()) ? new TurnedModel(model, YAW_DEGREES) : model);
                //?}
    }

    //? if >=1.21.1 {
    private static boolean isDiagonalFrame(ModelModifier.BeforeBake.Context context) {
        if (isDiagonalFrame(context.resourceId())) {
            return true;
        }
        ModelIdentifier topLevel = context.topLevelId();
        return topLevel != null && isDiagonalFrame(topLevel.id());
    }
    //?}


    private static boolean isDiagonalFrame(Identifier id) {
        if (id == null || !Beautify.MODID.equals(id.getNamespace())) {
            return false;
        }
        String path = id.getPath();
        return path.endsWith(DIAGONAL_SUFFIX) && !path.endsWith(DIAGONAL_BASE_PATH);
    }

    private record TurnedModel(UnbakedModel wrapped, float yaw) implements UnbakedModel {
        @Override
        public @NotNull Collection<Identifier> getDependencies() {
            return wrapped.getDependencies();
        }

        @Override
        public void resolveParents(Function<Identifier, UnbakedModel> resolver) {
            wrapped.resolveParents(resolver);
        }

        //? if >=1.21.1 {
        @Override
        public BakedModel bake(ModelBaker baker,
                               Function<Material, TextureAtlasSprite> spriteGetter,
                               ModelState state) {
            return wrapped.bake(baker, spriteGetter, new TurnedState(state, yaw));
        }
        //?} else {
        /^@Override
        public BakedModel bake(ModelBaker baker,
                               Function<Material, TextureAtlasSprite> spriteGetter,
                               ModelState state,
                               Identifier modelId) {
            return wrapped.bake(baker, spriteGetter, new TurnedState(state, yaw), modelId);
        }
        ^///?}
    }

    /^* the delegate's blockstate rotation, with our turn applied first (in model space) ^/
    private record TurnedState(ModelState delegate, float yaw) implements ModelState {
        @Override
        public Transformation getRotation() {
            Matrix4f turn = new Matrix4f().rotateY((float) Math.toRadians(yaw));
            return new Transformation(new Matrix4f(delegate.getRotation().getMatrix()).mul(turn));
        }

        @Override
        public boolean isUvLocked() {
            return delegate.isUvLocked();
        }
    }
}
*///?} else if neoforge && <1.21.11 {
/*package io.github.suel_ki.beautify.client.model;

import io.github.suel_ki.beautify.Beautify;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.ItemOverrides;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelIdentifier;
import net.minecraft.core.Direction;
import net.minecraft.resources.Identifier;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ModelEvent;

import java.util.ArrayList;
import java.util.List;

@EventBusSubscriber(modid = Beautify.MODID, value = Dist.CLIENT, bus = EventBusSubscriber.Bus.MOD)
public final class DiagonalFrameModels {
    private static final String DIAGONAL_SUFFIX = "_picture_frame_diagonal";
    private static final String DIAGONAL_BASE_PATH = "base_picture_frame_diagonal";
    private static final float YAW_DEGREES = -45.0F;

    private DiagonalFrameModels() {}

    @SubscribeEvent
    public static void onModifyBakingResult(ModelEvent.ModifyBakingResult event) {
        event.getModels().replaceAll((id, model) -> isDiagonalVariant(id) ? new TurnedModel(model) : model);
    }

    private static boolean isDiagonalVariant(ModelIdentifier id) {
        String variant = id.variant();
        return variant != null && variant.contains("diagonal=true") || isDiagonalFrame(id.id());
    }

    private static boolean isDiagonalFrame(Identifier id) {
        if (id == null || !Beautify.MODID.equals(id.getNamespace())) {
            return false;
        }
        String path = id.getPath();
        return path.endsWith(DIAGONAL_SUFFIX) && !path.endsWith(DIAGONAL_BASE_PATH);
    }

    private record TurnedModel(BakedModel wrapped) implements BakedModel {
        @Override
        public List<BakedQuad> getQuads(BlockState state, Direction side, RandomSource rand) {
            return turn(wrapped.getQuads(state, side, rand));
        }

        private static List<BakedQuad> turn(List<BakedQuad> quads) {
            List<BakedQuad> out = new ArrayList<>(quads.size());
            for (BakedQuad quad : quads) {
                out.add(turn(quad));
            }
            return out;
        }

        private static BakedQuad turn(BakedQuad quad) {
            int[] vertices = quad.getVertices().clone();
            float cos = (float) Math.cos(Math.toRadians(-YAW_DEGREES));
            float sin = (float) Math.sin(Math.toRadians(-YAW_DEGREES));
            for (int i = 0; i < vertices.length; i += 8) {
                float x = Float.intBitsToFloat(vertices[i]) - 0.5F;
                float z = Float.intBitsToFloat(vertices[i + 2]) - 0.5F;
                vertices[i] = Float.floatToRawIntBits(0.5F + x * cos - z * sin);
                vertices[i + 2] = Float.floatToRawIntBits(0.5F + x * sin + z * cos);
            }
            return new BakedQuad(vertices, quad.getTintIndex(), quad.getDirection(), quad.getSprite(), quad.isShade());
        }

        @Override
        public boolean useAmbientOcclusion() {
            return wrapped.useAmbientOcclusion();
        }

        @Override
        public boolean isGui3d() {
            return wrapped.isGui3d();
        }

        @Override
        public boolean usesBlockLight() {
            return wrapped.usesBlockLight();
        }

        @Override
        public boolean isCustomRenderer() {
            return wrapped.isCustomRenderer();
        }

        @Override
        public TextureAtlasSprite getParticleIcon() {
            return wrapped.getParticleIcon();
        }

        @Override
        public ItemOverrides getOverrides() {
            return wrapped.getOverrides();
        }

        @Override
        public ItemTransforms getTransforms() {
            return wrapped.getTransforms();
        }
    }
}
*///?}
