//? if neoforge {
/*package io.github.suel_ki.beautify.core.init;

import io.github.suel_ki.beautify.client.util.ClientKeys;
import io.github.suel_ki.beautify.Beautify;
import io.github.suel_ki.beautify.common.block.HangingPot;
import io.github.suel_ki.beautify.common.block.Trellis;
//? if >=1.21.11
import io.github.suel_ki.beautify.common.tooltip.BlockTooltip;
import io.github.suel_ki.beautify.common.tooltip.PlantableItemStackTooltip;
import net.minecraft.core.Holder;
//? if >=1.21.11
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.tooltip.TooltipComponent;
import net.minecraft.world.item.*;
//? if >=1.21.11
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.crafting.RecipeType;
//? if >=26.3 {
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.component.CookingFuel;
import net.minecraft.world.level.storage.loot.providers.number.floats.ResolvableFloat;
import net.minecraft.world.level.storage.loot.providers.number.ints.ResolvableInt;
//?}
import net.minecraft.world.level.block.Block;
//? if >=1.21.2 && <26.3 {
//import net.minecraft.world.level.block.entity.FuelValues;
//?}
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
//? if >=1.21.11
import java.util.function.Consumer;
import java.util.function.Function;

public final class ItemInitNeoForge {

    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Beautify.MODID);

	// trellis
	public static final DeferredItem<BlockItem> OAK_TRELLIS_ITEM = registerTrellis(BlockInit.OAK_TRELLIS);

	public static final DeferredItem<BlockItem> SPRUCE_TRELLIS_ITEM = registerTrellis(BlockInit.SPRUCE_TRELLIS);

	public static final DeferredItem<BlockItem> BIRCH_TRELLIS_ITEM = registerTrellis(BlockInit.BIRCH_TRELLIS);

	public static final DeferredItem<BlockItem> JUNGLE_TRELLIS_ITEM = registerTrellis(BlockInit.JUNGLE_TRELLIS);

	public static final DeferredItem<BlockItem> ACACIA_TRELLIS_ITEM = registerTrellis(BlockInit.ACACIA_TRELLIS);

	public static final DeferredItem<BlockItem> DARK_OAK_TRELLIS_ITEM = registerTrellis( BlockInit.DARK_OAK_TRELLIS);

	public static final DeferredItem<BlockItem> MANGROVE_TRELLIS_ITEM = registerTrellis(BlockInit.MANGROVE_TRELLIS);

	public static final DeferredItem<BlockItem> CRIMSON_TRELLIS_ITEM = registerTrellis(BlockInit.CRIMSON_TRELLIS);

	public static final DeferredItem<BlockItem> CHERRY_TRELLIS_ITEM = registerTrellis(BlockInit.CHERRY_TRELLIS);

	public static final DeferredItem<BlockItem> WARPED_TRELLIS_ITEM = registerTrellis(BlockInit.WARPED_TRELLIS);

	public static final DeferredItem<BlockItem> BAMBOO_TRELLIS_ITEM = registerTrellis(BlockInit.BAMBOO_TRELLIS);

	//? if >=1.21.11 {
	public static final DeferredItem<BlockItem> PALE_OAK_TRELLIS_ITEM = registerTrellis(BlockInit.PALE_OAK_TRELLIS);
	//?}

	// blinds
	public static final DeferredItem<BlockItem> OAK_BLINDS_ITEM = registerBurnBlockItem(BlockInit.OAK_BLINDS, 300);

	public static final DeferredItem<BlockItem> SPRUCE_BLINDS_ITEM = registerBurnBlockItem(BlockInit.SPRUCE_BLINDS, 300);

	public static final DeferredItem<BlockItem> BIRCH_BLINDS_ITEM = registerBurnBlockItem(BlockInit.BIRCH_BLINDS, 300);

	public static final DeferredItem<BlockItem> JUNGLE_BLINDS_ITEM = registerBurnBlockItem(BlockInit.JUNGLE_BLINDS, 300);

	public static final DeferredItem<BlockItem> ACACIA_BLINDS_ITEM = registerBurnBlockItem(BlockInit.ACACIA_BLINDS, 300);

	public static final DeferredItem<BlockItem> DARK_OAK_BLINDS_ITEM = registerBurnBlockItem(BlockInit.DARK_OAK_BLINDS, 300);

	public static final DeferredItem<BlockItem> CRIMSON_BLINDS_ITEM = registerBurnBlockItem(BlockInit.CRIMSON_BLINDS, 300);

	public static final DeferredItem<BlockItem> CHERRY_BLINDS_ITEM = registerBurnBlockItem(BlockInit.CHERRY_BLINDS, 300);

	public static final DeferredItem<BlockItem> WARPED_BLINDS_ITEM = registerBurnBlockItem(BlockInit.WARPED_BLINDS, 300);

	public static final DeferredItem<BlockItem> MANGROVE_BLINDS_ITEM = registerBurnBlockItem(BlockInit.MANGROVE_BLINDS, 300);

	public static final DeferredItem<BlockItem> BAMBOO_BLINDS_ITEM = registerBurnBlockItem(BlockInit.BAMBOO_BLINDS, 300);

	//? if >=1.21.11 {
	public static final DeferredItem<BlockItem> PALE_OAK_BLINDS_ITEM = registerBurnBlockItem(BlockInit.PALE_OAK_BLINDS, 300);
	//?}

	public static final DeferredItem<BlockItem> IRON_BLINDS_ITEM = registerBlockItem(BlockInit.IRON_BLINDS);

	// picture frame
	public static final DeferredItem<BlockItem> OAK_PICTURE_FRAME_ITEM = registerBurnBlockItem( BlockInit.OAK_PICTURE_FRAME, 300);

	public static final DeferredItem<BlockItem> SPRUCE_PICTURE_FRAME_ITEM = registerBurnBlockItem(BlockInit.SPRUCE_PICTURE_FRAME, 300);

	public static final DeferredItem<BlockItem> BIRCH_PICTURE_FRAME_ITEM = registerBurnBlockItem(BlockInit.BIRCH_PICTURE_FRAME, 300);

	public static final DeferredItem<BlockItem> JUNGLE_PICTURE_FRAME_ITEM = registerBurnBlockItem( BlockInit.JUNGLE_PICTURE_FRAME, 300);

	public static final DeferredItem<BlockItem> ACACIA_PICTURE_FRAME_ITEM = registerBurnBlockItem(BlockInit.ACACIA_PICTURE_FRAME, 300);

	public static final DeferredItem<BlockItem> DARK_OAK_PICTURE_FRAME_ITEM = registerBurnBlockItem(BlockInit.DARK_OAK_PICTURE_FRAME, 300);

	public static final DeferredItem<BlockItem> CRIMSON_PICTURE_FRAME_ITEM = registerBurnBlockItem(BlockInit.CRIMSON_PICTURE_FRAME, 300);

	public static final DeferredItem<BlockItem> CHERRY_PICTURE_FRAME_ITEM = registerBurnBlockItem(BlockInit.CHERRY_PICTURE_FRAME, 300);

	public static final DeferredItem<BlockItem> WARPED_PICTURE_FRAME_ITEM = registerBurnBlockItem(BlockInit.WARPED_PICTURE_FRAME, 300);

	public static final DeferredItem<BlockItem> MANGROVE_PICTURE_FRAME_ITEM = registerBurnBlockItem(BlockInit.MANGROVE_PICTURE_FRAME, 300);

	public static final DeferredItem<BlockItem> QUARTZ_PICTURE_FRAME_ITEM = registerBurnBlockItem(BlockInit.QUARTZ_PICTURE_FRAME, 300);

	public static final DeferredItem<BlockItem> BAMBOO_PICTURE_FRAME_ITEM = registerBurnBlockItem(BlockInit.BAMBOO_PICTURE_FRAME, 300);

	//? if >=1.21.11 {
	public static final DeferredItem<BlockItem> PALE_OAK_PICTURE_FRAME_ITEM = registerBurnBlockItem(BlockInit.PALE_OAK_PICTURE_FRAME, 300);
	//?}

	public static final DeferredItem<BlockItem> ROPE_ITEM = registerBurnBlockItem(BlockInit.ROPE, 100);

	public static final DeferredItem<BlockItem> HANGING_POT_ITEM = registerBlockItem(BlockInit.HANGING_POT,
            (properties) -> new BlockItem(BlockInit.HANGING_POT.get(),
					properties
					//? if >=1.21.11
					.component(ComponentInit.get(ComponentInit.HANGING_POT_TOOLTIP), HangingPot.TooltipComponent.INSTANCE)
			) {
                //? if >=1.21.11 {
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> consumer, TooltipFlag flag) {
                    stack.addToTooltip(ComponentInit.get(ComponentInit.HANGING_POT_TOOLTIP), context, display, consumer, flag);
                }
                //?}

				@Override
				public Optional<TooltipComponent> getTooltipImage(@NotNull ItemStack stack) {
					if (ClientKeys.hasControlDown()) {
						List<ItemStack> plants = HangingPot.VALID_FLOWERS
								.stream()
								.filter(item -> item != Items.AIR)
								.map(ItemStack::new)
								.toList();
						return Optional.of(new PlantableItemStackTooltip(plants));
					} else {
						return super.getTooltipImage(stack);
					}
				}

			});

	public static final DeferredItem<BlockItem> BOOKSTACK_ITEM = registerBlockItem(BlockInit.BOOKSTACK);

	public static final DeferredItem<BlockItem> LAMP_LIGHT_BULB_ITEM = registerBlockItem(BlockInit.LAMP_LIGHT_BULB);

	public static final DeferredItem<BlockItem> LAMP_BAMBOO_ITEM = registerBlockItem(BlockInit.LAMP_BAMBOO);

	public static final DeferredItem<BlockItem> LAMP_JAR_ITEM = registerBlockItem( BlockInit.LAMP_JAR);

	// candelabras
	public static final DeferredItem<BlockItem> LAMP_CANDELABRA_ITEM = registerBlockItem(BlockInit.LAMP_CANDELABRA);

	public static final DeferredItem<BlockItem> LAMP_CANDELABRA_LIGHT_BLUE_ITEM = registerBlockItem(BlockInit.LAMP_CANDELABRA_LIGHT_BLUE);

	public static final DeferredItem<BlockItem> LAMP_CANDELABRA_LIGHT_GRAY_ITEM = registerBlockItem(BlockInit.LAMP_CANDELABRA_LIGHT_GRAY);

	public static final DeferredItem<BlockItem> LAMP_CANDELABRA_BLACK_ITEM =registerBlockItem(BlockInit.LAMP_CANDELABRA_BLACK);

	public static final DeferredItem<BlockItem> LAMP_CANDELABRA_BLUE_ITEM = registerBlockItem(BlockInit.LAMP_CANDELABRA_BLUE);

	public static final DeferredItem<BlockItem> LAMP_CANDELABRA_BROWN_ITEM = registerBlockItem(BlockInit.LAMP_CANDELABRA_BROWN);

	public static final DeferredItem<BlockItem> LAMP_CANDELABRA_CYAN_ITEM = registerBlockItem(BlockInit.LAMP_CANDELABRA_CYAN);

	public static final DeferredItem<BlockItem> LAMP_CANDELABRA_GRAY_ITEM = registerBlockItem(BlockInit.LAMP_CANDELABRA_GRAY);

	public static final DeferredItem<BlockItem> LAMP_CANDELABRA_GREEN_ITEM = registerBlockItem(BlockInit.LAMP_CANDELABRA_GREEN);

	public static final DeferredItem<BlockItem> LAMP_CANDELABRA_LIME_ITEM = registerBlockItem(BlockInit.LAMP_CANDELABRA_LIME);

	public static final DeferredItem<BlockItem> LAMP_CANDELABRA_MAGENTA_ITEM = registerBlockItem(BlockInit.LAMP_CANDELABRA_MAGENTA);

	public static final DeferredItem<BlockItem> LAMP_CANDELABRA_ORANGE_ITEM = registerBlockItem(BlockInit.LAMP_CANDELABRA_ORANGE);

	public static final DeferredItem<BlockItem> LAMP_CANDELABRA_PINK_ITEM = registerBlockItem( BlockInit.LAMP_CANDELABRA_PINK);

	public static final DeferredItem<BlockItem> LAMP_CANDELABRA_PURPLE_ITEM = registerBlockItem(BlockInit.LAMP_CANDELABRA_PURPLE);

	public static final DeferredItem<BlockItem> LAMP_CANDELABRA_RED_ITEM = registerBlockItem(BlockInit.LAMP_CANDELABRA_RED);

	public static final DeferredItem<BlockItem> LAMP_CANDELABRA_WHITE_ITEM = registerBlockItem(BlockInit.LAMP_CANDELABRA_WHITE);

	public static final DeferredItem<BlockItem> LAMP_CANDELABRA_YELLOW_ITEM = registerBlockItem(BlockInit.LAMP_CANDELABRA_YELLOW);

	// workbench
	public static final DeferredItem<BlockItem> BOTANIST_WORKBENCH_ITEM = registerBlockItem(BlockInit.BOTANIST_WORKBENCH);

    private static DeferredItem<BlockItem> registerBlockItem(Holder<Block> holder, Function<Item.Properties, BlockItem> func) {
        //? if >=1.21.11
        var key = holder.unwrapKey().orElseThrow().identifier().getPath();
        //? if <1.21.11
        //var key = holder.unwrapKey().orElseThrow().identifier().getPath();
        //? if >=1.21.11
        return ITEMS.registerItem(key, func, () -> new Item.Properties().useBlockDescriptionPrefix());
        //? if <1.21.11
        //return ITEMS.registerItem(key, func, new Item.Properties());
    }

	private static DeferredItem<BlockItem> registerBlockItem(Holder<Block> holder) {
        return registerBlockItem(holder, properties -> blockItem(holder, properties));
	}

    private static BlockItem blockItem(Holder<Block> holder, Item.Properties properties) {
        //? if >=1.21.11 {
        if (holder.value() instanceof BlockTooltip tooltipBlock) {
            return new BlockItem(holder.value(), properties.component((DataComponentType) tooltipBlock.getTooltipType(), tooltipBlock.getTooltipComponent())) {
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> consumer, TooltipFlag flag) {
                    stack.addToTooltip(tooltipBlock.getTooltipType(), context, display, consumer, flag);
                }
            };
        }
        //?}
        return new BlockItem(holder.value(), properties);
    }

    private static DeferredItem<BlockItem> registerBurnBlockItem(Holder<Block> holder, int burnTime) {
        return registerBlockItem(holder, properties -> burnBlockItem(holder, properties, burnTime));
    }

    private static BlockItem burnBlockItem(Holder<Block> holder, Item.Properties properties, int burnTime) {
        //? if >=26.3 {
        var fuelProperties = properties
                .component(DataComponents.COOKING_FUEL, new CookingFuel(new ResolvableInt.Constant(burnTime), new ResolvableFloat.Constant(1.0f)));
        if (holder.value() instanceof BlockTooltip tooltipBlock) {
            return new BlockItem(holder.value(), fuelProperties
                    .component((DataComponentType) tooltipBlock.getTooltipType(), tooltipBlock.getTooltipComponent())) {
                @Override
                public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> consumer, TooltipFlag flag) {
                    stack.addToTooltip(tooltipBlock.getTooltipType(), context, display, consumer, flag);
                }
            };
        }
        return new BlockItem(holder.value(), fuelProperties);
        //?} else {
        /^return new BlockItem(holder.value(), properties
                //? if >=1.21.11
                .component((DataComponentType) ((BlockTooltip) holder.value()).getTooltipType(), ((BlockTooltip) holder.value()).getTooltipComponent())
        ) {
            //? if >=1.21.11 {
            @Override
            public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> consumer, TooltipFlag flag) {
                stack.addToTooltip(((BlockTooltip) holder.value()).getTooltipType(), context, display, consumer, flag);
            }
            //?}

            @Override
            //? if >=1.21.2 {
            public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType, FuelValues fuelValues) {
            //?} else {
            //public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
            //?}
                return burnTime;
            }
        };
        ^///?}
    }

	private static DeferredItem<BlockItem> registerTrellis(Holder<Block> holder) {
        return registerBlockItem(holder,
                (properties) -> new BlockItem(holder.value(), properties
                        //? if >=1.21.11
                        .component(ComponentInit.get(ComponentInit.TRELLIS_TOOLTIP), Trellis.TooltipComponent.INSTANCE)
                        //? if >=26.3
                        .component(DataComponents.COOKING_FUEL, new CookingFuel(new ResolvableInt.Constant(300), new ResolvableFloat.Constant(1.0f)))
                ) {
                    //? if >=1.21.11 {
                    @Override
                    public void appendHoverText(ItemStack stack, TooltipContext context, TooltipDisplay display, Consumer<Component> consumer, TooltipFlag flag) {
                        stack.addToTooltip(ComponentInit.get(ComponentInit.TRELLIS_TOOLTIP), context, display, consumer, flag);
                    }
                    //?}

                    @Override
                    public Optional<TooltipComponent> getTooltipImage(@NotNull ItemStack stack) {
                        if (ClientKeys.hasControlDown()) {
                            List<ItemStack> plants = Trellis.VALID_FLOWERS
                                    .stream()
                                    .filter(item -> item != Items.AIR)
                                    .map(ItemStack::new)
                                    .toList();
                            return Optional.of(new PlantableItemStackTooltip(plants));
                        }
                        return super.getTooltipImage(stack);
                    }

                    //? if <26.3 {
                    /^@Override
                    //? if >=1.21.2 {
                    public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType, FuelValues fuelValues) {
                    //?} else {
                    //public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
                    //?}
                        return 300;
                    }
                    ^///?}
                });
    }
}

*///?}