//? if >=1.21.11 {
package io.github.suel_ki.beautify.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import io.github.suel_ki.beautify.common.block.MultiFlowerPotBlock;
import io.github.suel_ki.beautify.common.block.entity.MultiFlowerPotBlockEntity;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.feature.ModelFeatureRenderer;
//? if <26.1.2 {
//import net.minecraft.client.renderer.state.CameraRenderState;
//?}
//? if >=26.1.2
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.Vec3;
//? if <26.1.2 {
//import net.minecraft.client.Minecraft;
//import net.minecraft.client.renderer.ItemBlockRenderTypes;
//import net.minecraft.client.renderer.block.model.BlockStateModel;
//import net.minecraft.client.renderer.rendertype.RenderType;
//import net.minecraft.world.level.block.state.BlockState;
//?}
//? if >=26.1.2 {
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.block.model.BlockDisplayContext;
//?}

public class MultiFlowerPotRendererModern implements BlockEntityRenderer<MultiFlowerPotBlockEntity, MultiFlowerPotRenderState> {

	//? if >=26.1.2
	private final BlockModelResolver resolver;

	public MultiFlowerPotRendererModern(BlockEntityRendererProvider.Context context) {
		//? if >=26.1.2
		this.resolver = context.blockModelResolver();
	}

	@Override
	public MultiFlowerPotRenderState createRenderState() {
		return new MultiFlowerPotRenderState();
	}

	@Override
	public void extractRenderState(MultiFlowerPotBlockEntity pot, MultiFlowerPotRenderState state, float partialTick, Vec3 cameraPos, ModelFeatureRenderer.CrumblingOverlay overlay) {
		BlockEntityRenderState.extractBase(pot, state, overlay);
		state.pots = pot.getBlockState().getValue(MultiFlowerPotBlock.POTS);
		state.variant = pot.getBlockState().getValue(MultiFlowerPotBlock.VARIANT);
		state.yaw = MultiFlowerPotBlock.yaw(pot.getBlockState().getValue(MultiFlowerPotBlock.FACING));
		state.models.clear();
		//? if <26.1.2
		//state.renderTypes.clear();
		for (int i = 0; i < state.pots; i++) {
			ItemStack stack = pot.getPlant(i);
			boolean occupied = false;
			//? if <26.1.2 {
			//BlockStateModel model = null;
			//RenderType renderType = null;
			//if (!stack.isEmpty() && stack.getItem() instanceof BlockItem blockItem) {
			//	BlockState plantState = blockItem.getBlock().defaultBlockState();
			//	model = Minecraft.getInstance().getBlockRenderer().getBlockModelShaper().getBlockModel(plantState);
			//	renderType = ItemBlockRenderTypes.getRenderType(plantState);
			//	occupied = true;
			//}
			//state.models.add(model);
			//state.renderTypes.add(renderType);
			//?} else {
			BlockModelRenderState model = new BlockModelRenderState();
			if (!stack.isEmpty() && stack.getItem() instanceof BlockItem blockItem) {
				resolver.update(model, blockItem.getBlock().defaultBlockState(), BlockDisplayContext.create());
				occupied = true;
			}
			state.models.add(model);
			//?}
			state.occupied.add(occupied);
			state.extra.add(stack.isEmpty() || !(stack.getItem() instanceof BlockItem bi) ? 1.0 : MultiFlowerPotBlock.plantRenderScale(bi.getBlock()));
		}
	}

	@Override
	public void submit(MultiFlowerPotRenderState state, PoseStack pose, SubmitNodeCollector collector, CameraRenderState camera) {
		for (int i = 0; i < state.models.size(); i++) {
			if (!state.occupied.get(i)) {
				continue;
			}
			MultiFlowerPotBlock.Slot slot = MultiFlowerPotBlock.SLOTS[state.variant][i];
			float plantScale = (float) (0.75 * slot.scale() * state.extra.get(i));
			pose.pushPose();
			pose.translate(0.5, 0, 0.5);
			//? if <26.3
			//pose.mulPose(Axis.YP.rotationDegrees(-state.yaw));
			//? if >=26.3
			pose.rotateDegrees(Axis.YP, -state.yaw);
			pose.translate(-0.5, 0, -0.5);
			pose.translate(slot.centerX() - 0.5 * plantScale, slot.soil(), slot.centerZ() - 0.5 * plantScale);
			pose.scale(plantScale, plantScale, plantScale);
			//? if <26.1.2
			//collector.submitBlockModel(pose, state.renderTypes.get(i), state.models.get(i), 1.0f, 1.0f, 1.0f, state.lightCoords, OverlayTexture.NO_OVERLAY, 0);
			//? if >=26.1.2
			state.models.get(i).submit(pose, collector, state.lightCoords, OverlayTexture.NO_OVERLAY, 0);
			pose.popPose();
		}
	}
}
//?}
