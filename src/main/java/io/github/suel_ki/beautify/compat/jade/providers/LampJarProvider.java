package io.github.suel_ki.beautify.compat.jade.providers;

import io.github.suel_ki.beautify.Beautify;
import io.github.suel_ki.beautify.common.block.LampJar;
import net.minecraft.network.chat.Component;
//? if >=1.21.11 {
import net.minecraft.resources.Identifier;
//?} else {
/*import net.minecraft.resources.Identifier;
*///?}
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import snownee.jade.api.BlockAccessor;
import snownee.jade.api.IBlockComponentProvider;
import snownee.jade.api.ITooltip;
import snownee.jade.api.config.IPluginConfig;
import snownee.jade.api.ui.IDisplayHelper;
//? if >=1.21.11 {
import snownee.jade.api.ui.JadeUI;
//?} else {
/*import snownee.jade.api.ui.IElementHelper;
*///?}

public enum  LampJarProvider implements IBlockComponentProvider {

    INSTANCE;

    @Override
    public void appendTooltip(ITooltip tooltip, BlockAccessor accessor, IPluginConfig config) {
        if (accessor.getBlock() instanceof LampJar) {
            BlockState state = accessor.getBlockState();
            final int currentLevel = state.getValue(LampJar.FILL_LEVEL);
            if (currentLevel != 0) {
                ItemStack icon = Items.GLOWSTONE_DUST.getDefaultInstance();
                if (icon.isEmpty())
                    return;
                int count = currentLevel / 5;
                //? if >=1.21.11 {
                tooltip.add(JadeUI.smallItem(icon));
                //?} else {
                /*tooltip.add(IElementHelper.get().smallItem(icon));
                *///?}
                tooltip.append(IDisplayHelper.get().stripColor(Component.literal(count + "× ")));
                tooltip.append(IDisplayHelper.get().stripColor(icon.getHoverName()));
            }
        }
    }

    @Override
    //? if >=1.21.11 {
    public Identifier getUid() {
    //?} else {
    /*public Identifier getUid() {
    *///?}
        return Beautify.id("lamp_jar.glowstone_dust");
    }
}
