//? if neoforge {
/*package io.github.suel_ki.beautify.client.config;

import io.github.suel_ki.beautify.util.BeautifyConfig;

import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.neoforged.fml.ModContainer;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.jetbrains.annotations.NotNull;

import java.util.function.Supplier;

public class ConfigScreen {

    public static void register(ModContainer container) {
        container.
                registerExtensionPoint(IConfigScreenFactory.class,
                        (Supplier<IConfigScreenFactory>) () -> new IConfigScreenFactory() {
                            @Override
                            public @NotNull Screen createScreen(@NotNull ModContainer modContainer, @NotNull Screen parent) {
                                return getConfigScreen(parent);
                            }
                        });
    }

    public static Screen getConfigScreen(Screen parent) {
        ConfigBuilder builder = ConfigBuilder.
                create().
                setParentScreen(parent).
                setTitle(Component.translatable("text.autoconfig.beautify.title")).
                transparentBackground();

        if (Minecraft.getInstance().level == null) {
            ConfigEntryBuilder eb = builder.entryBuilder();
            ConfigCategory error = builder.getOrCreateCategory(Component.literal("error"));
            error.addEntry(eb.startStrField(Component.literal("Create/Load a World First"), "World Not Loaded").build());
            builder.setEditable(false);
            return builder.build();
        }
        //? if >=26.2 {
        builder.setEditable(Minecraft.getInstance().hasSingleplayerServer());
        //?} else {
        /^builder.setEditable(Minecraft.getInstance().isSingleplayer());
        ^///?}

        ConfigEntryBuilder eb = builder.entryBuilder();

        ConfigCategory blinds = builder.getOrCreateCategory(Component.translatable("text.autoconfig.beautify.option.blinds"));

        ModConfigSpec.IntValue searchRadius = BeautifyConfig.SEARCH_RADIUS;
        ModConfigSpec.BooleanValue opensFromRoot = BeautifyConfig.OPENS_FROM_ROOT;

        blinds.addEntry(eb
                .startIntField(
                        Component.translatable("text.autoconfig.beautify.option.blinds.searchRadius"),
                        searchRadius.get())
                .setTooltip(Component.translatable("text.autoconfig.beautify.option.blinds.searchRadius.@Tooltip"))
                .setDefaultValue(searchRadius.getDefault())
                .setSaveConsumer(searchRadius::set)
                .build());

        blinds.addEntry(eb
                    .startBooleanToggle(
                        Component.translatable("text.autoconfig.beautify.option.blinds.opensFromRoot"),
                        opensFromRoot.get())
                .setTooltip(Component.translatable("text.autoconfig.beautify.option.blinds.opensFromRoot.@Tooltip"))
                .setDefaultValue(opensFromRoot.getDefault())
                .setSaveConsumer(opensFromRoot::set)
                .build());

        ConfigCategory houses = builder.getOrCreateCategory(Component.translatable("text.autoconfig.beautify.option.houses"));
        ModConfigSpec.IntValue botanistSpawnWeight= BeautifyConfig.BOTANIST_SPAWN_WEIGHT;

        houses.addEntry(eb
                .startIntField(
                        Component.translatable("text.autoconfig.beautify.option.houses.botanistSpawnWeight"),
                        botanistSpawnWeight.get())
                .setTooltip(Component.translatable("text.autoconfig.beautify.option.houses.botanistSpawnWeight.@Tooltip"))
                .setDefaultValue(botanistSpawnWeight.getDefault())
                .setSaveConsumer(botanistSpawnWeight::set)
                .build());

        return builder.build();

    }

}
*///?}
