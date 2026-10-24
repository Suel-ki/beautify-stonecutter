//? if fabric {
package io.github.suel_ki.beautify.core.init;

import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
//? if >=26.1.2
import net.fabricmc.fabric.api.creativetab.v1.FabricCreativeModeTab;
//? if <26.1.2
//import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;

public class CreativeModeTabInit {
	//? if >=26.1.2 {
	public static final CreativeModeTab BEAUTIFY_TAB = FabricCreativeModeTab.builder()
			.title(Component.translatable("itemGroup.beautify.group"))
			.icon(ItemInitFabric.HANGING_POT_ITEM::getDefaultInstance)
			.displayItems((enabledFeatures, entries) -> entries.acceptAll(ItemInitFabric.ITEMS.keySet().stream().map(ItemStack::new).toList()))
			.build();
	//?} else {
	/*public static final CreativeModeTab BEAUTIFY_TAB = FabricItemGroup.builder()
			.title(Component.translatable("itemGroup.beautify.group"))
			.icon(ItemInitFabric.HANGING_POT_ITEM::getDefaultInstance)
			.displayItems((enabledFeatures, entries) -> entries.acceptAll(ItemInitFabric.ITEMS.keySet().stream().map(ItemStack::new).toList()))
			.build();
	*///?}
}
//?} else {
/*package io.github.suel_ki.beautify.core.init;

import io.github.suel_ki.beautify.Beautify;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class CreativeModeTabInit {
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Beautify.MODID);

    // TAB
    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> BEAUTIFY_TAB = CREATIVE_MODE_TABS.register("beautify", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.beautify.group"))
            .icon(ItemInitNeoForge.HANGING_POT_ITEM.get()::getDefaultInstance)
            .displayItems((enabledFeatures, entries) -> entries.acceptAll(ItemInitNeoForge.ITEMS.getEntries().stream().map(ItemStack::new).toList())) // adds items to itemgroup
            .build());

}
*///?}
