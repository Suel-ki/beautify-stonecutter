//? if >=1.20.1 && <1.21.11 {
/*package io.github.suel_ki.beautify.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import io.github.suel_ki.beautify.common.block.MultiFlowerPotBlock;
import io.github.suel_ki.beautify.common.block.entity.MultiFlowerPotBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;

public class MultiFlowerPotRenderer implements BlockEntityRenderer<MultiFlowerPotBlockEntity> {

	@Override
	public void render(MultiFlowerPotBlockEntity pot, float partialTick, PoseStack pose, MultiBufferSource buffers, int light, int overlay) {
		BlockState state = pot.getBlockState();
		int pots = state.getValue(MultiFlowerPotBlock.POTS);
		int variant = state.getValue(MultiFlowerPotBlock.VARIANT);
		float facing = MultiFlowerPotBlock.yaw(state.getValue(MultiFlowerPotBlock.FACING));
		for (int i = 0; i < pots; i++) {
			ItemStack stack = pot.getPlant(i);
			if (stack.isEmpty() || !(stack.getItem() instanceof BlockItem blockItem)) {
				continue;
			}
			MultiFlowerPotBlock.Slot slot = MultiFlowerPotBlock.SLOTS[variant][i];
			float plantScale = (float) (0.75 * slot.scale() * MultiFlowerPotBlock.plantRenderScale(blockItem.getBlock()));
			pose.pushPose();
			pose.translate(0.5, 0, 0.5);
			pose.mulPose(Axis.YP.rotationDegrees(-facing));
			pose.translate(-0.5, 0, -0.5);
			pose.translate(slot.centerX() - 0.5 * plantScale, slot.soil(), slot.centerZ() - 0.5 * plantScale);
			pose.scale(plantScale, plantScale, plantScale);
			Minecraft.getInstance().getBlockRenderer().renderSingleBlock(blockItem.getBlock().defaultBlockState(), pose, buffers, light, OverlayTexture.NO_OVERLAY);
			pose.popPose();
		}
	}
}
*///?}
