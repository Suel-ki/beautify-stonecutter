//? if >=1.21.11 {
//? if fabric {
package io.github.suel_ki.beautify.core.init;

import com.mojang.serialization.Codec;
import io.github.suel_ki.beautify.Beautify;

import io.github.suel_ki.beautify.client.tooltip.TooltipLore;
import io.github.suel_ki.beautify.common.block.*;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;

public class ComponentInit {
    public static final DataComponentType<TooltipLore> BLINDS_TOOLTIP = register(
            "blinds_tooltip",
            TooltipLore.CODEC,
            StreamCodec.unit(Blinds.TooltipComponent.INSTANCE)
    );

    public static final DataComponentType<TooltipLore> BOOKSTACK_TOOLTIP = register(
            "bookstack_tooltip",
            TooltipLore.CODEC,
            StreamCodec.unit(BookStack.TooltipComponent.INSTANCE)
    );

    public static final DataComponentType<TooltipLore> BOTANIST_WORKBENCH_TOOLTIP = register(
            "botanist_workbench_tooltip",
            TooltipLore.CODEC,
            StreamCodec.unit(BotanistWorkbench.TooltipComponent.INSTANCE)
    );

    public static final DataComponentType<TooltipLore> HANGING_POT_TOOLTIP = register(
            "hanging_pot_tooltip",
            TooltipLore.CODEC,
            StreamCodec.unit(HangingPot.TooltipComponent.INSTANCE)
    );

    public static final DataComponentType<TooltipLore> LAMP_BAMBOO_TOOLTIP = register(
            "lamp_bamboo_tooltip",
            TooltipLore.CODEC,
            StreamCodec.unit(LampBamboo.TooltipComponent.INSTANCE)
    );

    public static final DataComponentType<TooltipLore> LAMP_CANDELABRA_TOOLTIP = register(
            "lamp_candelabra_tooltip",
            TooltipLore.CODEC,
            StreamCodec.unit(LampCandelabra.TooltipComponent.INSTANCE)
    );

    public static final DataComponentType<TooltipLore> LAMP_JAR_TOOLTIP = register(
            "lamp_jar_tooltip",
            TooltipLore.CODEC,
            StreamCodec.unit(LampJar.TooltipComponent.INSTANCE)
    );

    public static final DataComponentType<TooltipLore> LAMP_LIGHTBULB_TOOLTIP = register(
            "lamp_lightbulb_tooltip",
            TooltipLore.CODEC,
            StreamCodec.unit(LampLightBulb.TooltipComponent.INSTANCE)
    );

    public static final DataComponentType<TooltipLore> PICTURE_FRAME_TOOLTIP = register(
            "picture_frame_tooltip",
            TooltipLore.CODEC,
            StreamCodec.unit(PictureFrame.TooltipComponent.INSTANCE)
    );

    public static final DataComponentType<TooltipLore> ROPE_TOOLTIP = register(
            "rope_tooltip",
            TooltipLore.CODEC,
            StreamCodec.unit(Rope.TooltipComponent.INSTANCE)
    );

    public static final DataComponentType<TooltipLore> TRELLIS_TOOLTIP = register(
            "trellis_tooltip",
            TooltipLore.CODEC,
            StreamCodec.unit(Trellis.TooltipComponent.INSTANCE)
    );

    private static DataComponentType<TooltipLore> register(
            String id, Codec<TooltipLore> codec, StreamCodec<RegistryFriendlyByteBuf, TooltipLore> streamCodec
    ) {
        return Registry.register(
                BuiltInRegistries.DATA_COMPONENT_TYPE,
                Beautify.id(id),
                DataComponentType.<TooltipLore>builder()
                        .persistent(codec)
                        .networkSynchronized(streamCodec)
                        .build()
        );
    }


    public static <T> DataComponentType<T> get(DataComponentType<T> component) {
        return component;
    }

    public static void init() {}
}
//?} else {
/*package io.github.suel_ki.beautify.core.init;

import com.mojang.serialization.Codec;
import io.github.suel_ki.beautify.Beautify;
import io.github.suel_ki.beautify.client.tooltip.TooltipLore;
import io.github.suel_ki.beautify.common.block.*;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ComponentInit {
    public static final DeferredRegister<DataComponentType<?>> DATA_COMPONENT_TYPE = DeferredRegister
            .create(BuiltInRegistries.DATA_COMPONENT_TYPE, Beautify.MODID);

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<TooltipLore>> BLINDS_TOOLTIP = register(
            "blinds_tooltip",
            TooltipLore.CODEC,
            TooltipLore.STREAM_CODEC
    );

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<TooltipLore>> BOOKSTACK_TOOLTIP = register(
            "bookstack_tooltip",
            TooltipLore.CODEC,
            StreamCodec.unit(BookStack.TooltipComponent.INSTANCE)
    );

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<TooltipLore>> BOTANIST_WORKBENCH_TOOLTIP = register(
            "botanist_workbench_tooltip",
            TooltipLore.CODEC,
            StreamCodec.unit(BotanistWorkbench.TooltipComponent.INSTANCE)
    );

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<TooltipLore>> HANGING_POT_TOOLTIP = register(
            "hanging_pot_tooltip",
            TooltipLore.CODEC,
            StreamCodec.unit(HangingPot.TooltipComponent.INSTANCE)
    );

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<TooltipLore>> LAMP_BAMBOO_TOOLTIP = register(
            "lamp_bamboo_tooltip",
            TooltipLore.CODEC,
            StreamCodec.unit(LampBamboo.TooltipComponent.INSTANCE)
    );

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<TooltipLore>> LAMP_CANDELABRA_TOOLTIP = register(
            "lamp_candelabra_tooltip",
            TooltipLore.CODEC,
            StreamCodec.unit(LampCandelabra.TooltipComponent.INSTANCE)
    );

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<TooltipLore>> LAMP_JAR_TOOLTIP = register(
            "lamp_jar_tooltip",
            TooltipLore.CODEC,
            StreamCodec.unit(LampJar.TooltipComponent.INSTANCE)
    );

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<TooltipLore>> LAMP_LIGHTBULB_TOOLTIP = register(
            "lamp_lightbulb_tooltip",
            TooltipLore.CODEC,
            StreamCodec.unit(LampLightBulb.TooltipComponent.INSTANCE)
    );

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<TooltipLore>> PICTURE_FRAME_TOOLTIP = register(
            "picture_frame_tooltip",
            TooltipLore.CODEC,
            StreamCodec.unit(PictureFrame.TooltipComponent.INSTANCE)
    );

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<TooltipLore>> ROPE_TOOLTIP = register(
            "rope_tooltip",
            TooltipLore.CODEC,
            StreamCodec.unit(Rope.TooltipComponent.INSTANCE)
    );

    public static final DeferredHolder<DataComponentType<?>, DataComponentType<TooltipLore>> TRELLIS_TOOLTIP = register(
            "trellis_tooltip",
            TooltipLore.CODEC,
            StreamCodec.unit(Trellis.TooltipComponent.INSTANCE)
    );

    private static <T extends TooltipLore> DeferredHolder<DataComponentType<?>, DataComponentType<T>> register(
            String id, Codec<T> codec, StreamCodec<RegistryFriendlyByteBuf, T> streamCodec
    ) {
        return DATA_COMPONENT_TYPE.register(
                id,
                () -> DataComponentType.<T>builder()
                        .persistent(codec)
                        .networkSynchronized(streamCodec)
                        .build()
        );
    }

    public static <T> DataComponentType<T> get(DeferredHolder<DataComponentType<?>, DataComponentType<T>> component) {
        return component.get();
    }
    
}
*///?}
//?}
