//? if >=1.20.1 {
package io.github.suel_ki.beautify.common.block.entity;

import io.github.suel_ki.beautify.core.init.BlockInit;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
//? if >=1.21.11 {
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
//?}

public class MultiFlowerPotBlockEntity extends BlockEntity {

	private final ItemStack[] plants = new ItemStack[]{ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY, ItemStack.EMPTY};

	public MultiFlowerPotBlockEntity(BlockPos pos, BlockState state) {
		//? if fabric
		super(BlockInit.MULTI_FLOWER_POT_BE, pos, state);
		//? if neoforge
		//super(BlockInit.MULTI_FLOWER_POT_BE.get(), pos, state);
	}

	public ItemStack getPlant(int slot) {
		return slot >= 0 && slot < plants.length ? plants[slot] : ItemStack.EMPTY;
	}

	public void setPlant(int slot, ItemStack stack) {
		if (slot >= 0 && slot < plants.length) {
			plants[slot] = stack;
			setChanged();
			if (level != null && !level.isClientSide()) {
				level.sendBlockUpdated(worldPosition, getBlockState(), getBlockState(), 3);
			}
		}
	}

	//? if <1.21.1 {
	/*@Override
	protected void saveAdditional(CompoundTag tag) {
		super.saveAdditional(tag);
		for (int i = 0; i < plants.length; i++) {
			if (!plants[i].isEmpty()) {
				tag.put("Plant" + i, plants[i].save(new CompoundTag()));
			}
		}
	}

	@Override
	public void load(CompoundTag tag) {
		super.load(tag);
		for (int i = 0; i < plants.length; i++) {
			plants[i] = tag.contains("Plant" + i) ? ItemStack.of(tag.getCompound("Plant" + i)) : ItemStack.EMPTY;
		}
	}

	@Override
	public CompoundTag getUpdateTag() {
		return saveWithoutMetadata();
	}
	*///?}
	//? if >=1.21.1 && <1.21.11 {
	/*@Override
	protected void saveAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		super.saveAdditional(tag, registries);
		for (int i = 0; i < plants.length; i++) {
			if (!plants[i].isEmpty()) {
				tag.put("Plant" + i, plants[i].save(registries));
			}
		}
	}

	@Override
	protected void loadAdditional(CompoundTag tag, HolderLookup.Provider registries) {
		super.loadAdditional(tag, registries);
		for (int i = 0; i < plants.length; i++) {
			plants[i] = tag.contains("Plant" + i) ? ItemStack.parse(registries, tag.getCompound("Plant" + i)).orElse(ItemStack.EMPTY) : ItemStack.EMPTY;
		}
	}

	@Override
	public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
		return saveWithoutMetadata(registries);
	}
	*///?}
	//? if >=1.21.11 {
	@Override
	protected void saveAdditional(ValueOutput output) {
		super.saveAdditional(output);
		for (int i = 0; i < plants.length; i++) {
			output.store("Plant" + i, ItemStack.OPTIONAL_CODEC, plants[i]);
		}
	}

	@Override
	protected void loadAdditional(ValueInput input) {
		super.loadAdditional(input);
		for (int i = 0; i < plants.length; i++) {
			plants[i] = input.read("Plant" + i, ItemStack.OPTIONAL_CODEC).orElse(ItemStack.EMPTY);
		}
	}

	@Override
	public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
		return saveWithoutMetadata(registries);
	}
	//?}

	@Override
	public ClientboundBlockEntityDataPacket getUpdatePacket() {
		return ClientboundBlockEntityDataPacket.create(this);
	}
}
//?}
