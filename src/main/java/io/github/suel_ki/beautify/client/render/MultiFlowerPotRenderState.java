//? if >=1.21.11 {
package io.github.suel_ki.beautify.client.render;

//? if <26.1.2 {
//import net.minecraft.client.renderer.block.model.BlockStateModel;
//import net.minecraft.client.renderer.rendertype.RenderType;
//?}
//? if >=26.1.2
import net.minecraft.client.renderer.block.BlockModelRenderState;
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;

import java.util.ArrayList;
import java.util.List;

public class MultiFlowerPotRenderState extends BlockEntityRenderState {

	public int pots;
	public int variant;
	public float yaw;
	//? if <26.1.2 {
	//public final List<BlockStateModel> models = new ArrayList<>();
	//public final List<RenderType> renderTypes = new ArrayList<>();
	//?}
	//? if >=26.1.2
	public final List<BlockModelRenderState> models = new ArrayList<>();
	public final List<Boolean> occupied = new ArrayList<>();
	public final List<Double> extra = new ArrayList<>();
}
//?}
