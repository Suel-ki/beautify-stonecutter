//? if fabric {
//? if >=26.1.2 {
package io.github.suel_ki.beautify.core.init;

import io.github.suel_ki.beautify.Beautify;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.trading.TradeSet;

public class TradesInit {
    
    public static final ResourceKey<TradeSet> BOTANIST_LEVEL_1 = resourceKey("botanist/level_1");
    public static final ResourceKey<TradeSet> BOTANIST_LEVEL_2 = resourceKey("botanist/level_2");
    public static final ResourceKey<TradeSet> BOTANIST_LEVEL_3 = resourceKey("botanist/level_3");
    public static final ResourceKey<TradeSet> BOTANIST_LEVEL_4 = resourceKey("botanist/level_4");
    public static final ResourceKey<TradeSet> BOTANIST_LEVEL_5 = resourceKey("botanist/level_5");

    public static ResourceKey<TradeSet> resourceKey(final String path) {
        return ResourceKey.create(Registries.TRADE_SET, Beautify.id(path));
    }

    public static void init() {}
}
//?} else if >=1.21.11 {
/*package io.github.suel_ki.beautify.core.init;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.world.entity.npc.villager.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Item;

public class TradesInit {
	private static final Item[] TRELLIS_ITEMS = {ItemInitFabric.OAK_TRELLIS_ITEM, ItemInitFabric.SPRUCE_TRELLIS_ITEM, ItemInitFabric.BIRCH_TRELLIS_ITEM, ItemInitFabric.JUNGLE_TRELLIS_ITEM, ItemInitFabric.ACACIA_TRELLIS_ITEM, ItemInitFabric.DARK_OAK_TRELLIS_ITEM, ItemInitFabric.MANGROVE_TRELLIS_ITEM, ItemInitFabric.CRIMSON_TRELLIS_ITEM, ItemInitFabric.CHERRY_TRELLIS_ITEM, ItemInitFabric.WARPED_TRELLIS_ITEM};

    public static void addCustomTrades() {
        // lvl1
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 1, factories -> factories
                .add(new VillagerTrades.ItemsForEmeralds(
                                        new ItemStack(ItemInitFabric.HANGING_POT_ITEM), 2, 1, 16, 6, 0.02F)));
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 1, factories -> factories
                .add(new VillagerTrades.ItemsForEmeralds(
                                        new ItemStack(Items.FLOWER_POT), 3, 2,12, 5, 0.02F)));
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 1, factories -> factories
                .add(new VillagerTrades.ItemsForEmeralds(
                                        new ItemStack(Items.VINE), 2, 3,16, 4, 0.02F)));

        // lvl2
		for (Item trellis : TRELLIS_ITEMS) {
			TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 2, factories -> factories
					.add(new VillagerTrades.ItemsForEmeralds(new ItemStack(trellis), 1, 2, 16, 3, 0.02F)));
		}
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 2, factories -> factories
                .add(new VillagerTrades.ItemsForEmeralds(
                        new ItemStack(Items.BIG_DRIPLEAF), 4, 3, 6, 9, 0.02F)));
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 2, factories -> factories
                .add(new VillagerTrades.ItemsForEmeralds(
                        new ItemStack(Items.SMALL_DRIPLEAF), 3, 4, 6, 8, 0.02F)));

        // lvl3
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 3, factories -> factories
                .add(new VillagerTrades.ItemsForEmeralds(
                        new ItemStack(Items.LILY_PAD), 1, 4, 8, 5, 0.02F)));
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 3, factories -> factories
                .add(new VillagerTrades.ItemsForEmeralds(
                        new ItemStack(Items.SPORE_BLOSSOM), 3, 1, 12, 10, 0.02F)));

        // lvl4
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 4, factories -> factories
                .add(new VillagerTrades.ItemsForEmeralds(
                        new ItemStack(Items.MOSS_BLOCK), 1, 2, 48, 3, 0.02F)));
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 4, factories -> factories
                .add(new VillagerTrades.ItemsForEmeralds(
                        new ItemStack(Items.FLOWERING_AZALEA), 2, 1, 16, 7, 0.02F)));

        // lvl 5
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 5, factories -> factories
                .add(new VillagerTrades.ItemsForEmeralds(
                        new ItemStack(Items.ROOTED_DIRT), 1, 4, 24, 3, 0.02F)));
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 5, factories -> factories
                .add(new VillagerTrades.ItemsForEmeralds(
                        new ItemStack(Items.HANGING_ROOTS), 1, 3, 10, 7, 0.02F)));
    }
}
*///?} else {
/*package io.github.suel_ki.beautify.core.init;

import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
//? if >=1.21.1
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.minecraft.world.item.Item;

public class TradesInit {
	private static final Item[] TRELLIS_ITEMS = {ItemInitFabric.OAK_TRELLIS_ITEM, ItemInitFabric.SPRUCE_TRELLIS_ITEM, ItemInitFabric.BIRCH_TRELLIS_ITEM, ItemInitFabric.JUNGLE_TRELLIS_ITEM, ItemInitFabric.ACACIA_TRELLIS_ITEM, ItemInitFabric.DARK_OAK_TRELLIS_ITEM, ItemInitFabric.MANGROVE_TRELLIS_ITEM, ItemInitFabric.CRIMSON_TRELLIS_ITEM, ItemInitFabric.CHERRY_TRELLIS_ITEM, ItemInitFabric.WARPED_TRELLIS_ITEM};

    public static void addCustomTrades() {
        // lvl1
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 1, factories -> factories
                .add((trader, rand) ->
                                new MerchantOffer(
                                        //? if >=1.21.1
                                        new ItemCost(Items.EMERALD, 2),
                                        //? if <1.21.1
                                        //new ItemStack(Items.EMERALD, 2),
                                        new ItemStack(ItemInitFabric.HANGING_POT_ITEM, 1), 16, 6, 0.02F)));

        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 1, factories -> factories
                .add((trader, rand) ->
                                new MerchantOffer(
                                        //? if >=1.21.1
                                        new ItemCost(Items.EMERALD, 3),
                                        //? if <1.21.1
                                        //new ItemStack(Items.EMERALD, 3),
                                        new ItemStack(Items.FLOWER_POT, 2), 12, 5, 0.02F)));
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 1, factories -> factories
                .add((trader, rand) ->
                                new MerchantOffer(
                                        //? if >=1.21.1
                                        new ItemCost(Items.EMERALD, 2),
                                        //? if <1.21.1
                                        //new ItemStack(Items.EMERALD, 2),
                                        new ItemStack(Items.VINE, 3), 16, 4, 0.02F)));

        // lvl2
		for (Item trellis : TRELLIS_ITEMS) {
			TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 2, factories -> factories
					.add((trader, rand) -> new MerchantOffer(
							//? if >=1.21.1
							new ItemCost(Items.EMERALD, 1),
							//? if <1.21.1
							//new ItemStack(Items.EMERALD, 1),
							new ItemStack(trellis, 2), 16, 3, 0.02F)));
		}
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 2, factories -> factories
                .add((trader, rand) ->
                        new MerchantOffer(
                                //? if >=1.21.1
                                new ItemCost(Items.EMERALD, 4),
                                //? if <1.21.1
                                //new ItemStack(Items.EMERALD, 4),
                                new ItemStack(Items.BIG_DRIPLEAF, 3), 6, 9, 0.02F)));
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 2, factories -> factories
                .add((trader, rand) ->
                        new MerchantOffer(
                                //? if >=1.21.1
                                new ItemCost(Items.EMERALD, 3),
                                //? if <1.21.1
                                //new ItemStack(Items.EMERALD, 3),
                                new ItemStack(Items.SMALL_DRIPLEAF, 4), 6, 8, 0.02F)));

        // lvl3
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 3, factories -> factories
                .add((trader, rand) ->
                        new MerchantOffer(
                                //? if >=1.21.1
                                new ItemCost(Items.EMERALD, 1),
                                //? if <1.21.1
                                //new ItemStack(Items.EMERALD, 1),
                                new ItemStack(Items.LILY_PAD, 4), 8, 5, 0.02F)));
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 3, factories -> factories
                .add((trader, rand) ->
                        new MerchantOffer(
                                //? if >=1.21.1
                                new ItemCost(Items.EMERALD, 3),
                                //? if <1.21.1
                                //new ItemStack(Items.EMERALD, 3),
                                new ItemStack(Items.SPORE_BLOSSOM, 1), 12, 10, 0.02F)));

        // lvl4
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 4, factories -> factories
                .add((trader, rand) ->
                        new MerchantOffer(
                                //? if >=1.21.1
                                new ItemCost(Items.EMERALD, 1),
                                //? if <1.21.1
                                //new ItemStack(Items.EMERALD, 1),
                                new ItemStack(Items.MOSS_BLOCK, 2), 48, 3, 0.02F)));
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 4, factories -> factories
                .add((trader, rand) ->
                        new MerchantOffer(
                                //? if >=1.21.1
                                new ItemCost(Items.EMERALD, 2),
                                //? if <1.21.1
                                //new ItemStack(Items.EMERALD, 2),
                                new ItemStack(Items.FLOWERING_AZALEA, 1), 16, 7, 0.02F)));

        // lvl 5
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 5, factories -> factories
                .add((trader, rand) ->
                        new MerchantOffer(
                                //? if >=1.21.1
                                new ItemCost(Items.EMERALD, 1),
                                //? if <1.21.1
                                //new ItemStack(Items.EMERALD, 1),
                                new ItemStack(Items.ROOTED_DIRT, 4), 24, 3, 0.02F)));
        TradeOfferHelper.registerVillagerOffers(ModVillagers.BOTANIST, 5, factories -> factories
                .add((trader, rand) ->
                        new MerchantOffer(
                                //? if >=1.21.1
                                new ItemCost(Items.EMERALD, 1),
                                //? if <1.21.1
                                //new ItemStack(Items.EMERALD, 1),
                                new ItemStack(Items.HANGING_ROOTS, 3), 10, 7, 0.02F)));
    }
}
*///?}
//?} else {
/*//? if >=26.1.2 {
package io.github.suel_ki.beautify.core.init;

import io.github.suel_ki.beautify.Beautify;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.trading.TradeSet;
import net.neoforged.fml.common.EventBusSubscriber;

@EventBusSubscriber(modid = Beautify.MODID)
public class TradesInit {
    public static final ResourceKey<TradeSet> BOTANIST_LEVEL_1 = resourceKey("botanist/level_1");
    public static final ResourceKey<TradeSet> BOTANIST_LEVEL_2 = resourceKey("botanist/level_2");
    public static final ResourceKey<TradeSet> BOTANIST_LEVEL_3 = resourceKey("botanist/level_3");
    public static final ResourceKey<TradeSet> BOTANIST_LEVEL_4 = resourceKey("botanist/level_4");
    public static final ResourceKey<TradeSet> BOTANIST_LEVEL_5 = resourceKey("botanist/level_5");

    public static ResourceKey<TradeSet> resourceKey(final String path) {
        return ResourceKey.create(Registries.TRADE_SET, Beautify.id(path));
    }

    public static void init() {}
}
//?} else if >=1.21.11 {
/^package io.github.suel_ki.beautify.core.init;

import io.github.suel_ki.beautify.Beautify;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.villager.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;

import java.util.List;
import net.minecraft.world.item.BlockItem;
import net.neoforged.neoforge.registries.DeferredItem;


@EventBusSubscriber(modid = Beautify.MODID)
public class TradesInit {
	private static final List<DeferredItem<BlockItem>> TRELLIS_ITEMS = List.of(ItemInitNeoForge.OAK_TRELLIS_ITEM, ItemInitNeoForge.SPRUCE_TRELLIS_ITEM, ItemInitNeoForge.BIRCH_TRELLIS_ITEM, ItemInitNeoForge.JUNGLE_TRELLIS_ITEM, ItemInitNeoForge.ACACIA_TRELLIS_ITEM, ItemInitNeoForge.DARK_OAK_TRELLIS_ITEM, ItemInitNeoForge.MANGROVE_TRELLIS_ITEM, ItemInitNeoForge.CRIMSON_TRELLIS_ITEM, ItemInitNeoForge.CHERRY_TRELLIS_ITEM, ItemInitNeoForge.WARPED_TRELLIS_ITEM);

    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        // BOTANIST
        // lvl1
        if (event.getType() == ModVillagers.BOTANIST) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ItemInitNeoForge.HANGING_POT_ITEM.get(), 1);
            int villagerLevel = 1;

            trades.get(villagerLevel)
                    .add((level, trader, rand) -> new MerchantOffer(new ItemCost(Items.EMERALD, 2), stack, 16, 6, 0.02F));
        }

        if (event.getType() == ModVillagers.BOTANIST) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(Items.FLOWER_POT, 2);
            int villagerLevel = 1;

            trades.get(villagerLevel)
                    .add((level, trader, rand) -> new MerchantOffer(new ItemCost(Items.EMERALD, 3), stack, 12, 5, 0.02F));
        }

        if (event.getType() == ModVillagers.BOTANIST) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(Items.VINE, 3);
            int villagerLevel = 1;

            trades.get(villagerLevel)
                    .add((level, trader, rand) -> new MerchantOffer(new ItemCost(Items.EMERALD, 2), stack, 16, 4, 0.02F));
        }

        // lvl2
		for (DeferredItem<BlockItem> trellis : TRELLIS_ITEMS) {
			Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
			ItemStack stack = new ItemStack(trellis.get(), 2);
			int villagerLevel = 2;

			trades.get(villagerLevel)
					.add((level, trader, rand) -> new MerchantOffer(new ItemCost(Items.EMERALD, 1), stack, 16, 3, 0.02F));
		}


        if (event.getType() == ModVillagers.BOTANIST) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(Items.BIG_DRIPLEAF, 3);
            int villagerLevel = 2;

            trades.get(villagerLevel)
                    .add((level, trader, rand) -> new MerchantOffer(new ItemCost(Items.EMERALD, 4), stack, 6, 9, 0.02F));
        }

        if (event.getType() == ModVillagers.BOTANIST) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(Items.SMALL_DRIPLEAF, 4);
            int villagerLevel = 2;

            trades.get(villagerLevel)
                    .add((level, trader, rand) -> new MerchantOffer(new ItemCost(Items.EMERALD, 3), stack, 6, 8, 0.02F));
        }

        // lvl3
        if (event.getType() == ModVillagers.BOTANIST) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(Items.LILY_PAD, 4);
            int villagerLevel = 3;

            trades.get(villagerLevel)
                    .add((level, trader, rand) -> new MerchantOffer(new ItemCost(Items.EMERALD, 1), stack, 8, 5, 0.02F));
        }

        if (event.getType() == ModVillagers.BOTANIST) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(Items.SPORE_BLOSSOM, 1);
            int villagerLevel = 3;

            trades.get(villagerLevel)
                    .add((level, trader, rand) -> new MerchantOffer(new ItemCost(Items.EMERALD, 3), stack, 12, 10, 0.02F));
        }

        // lvl 4
        if (event.getType() == ModVillagers.BOTANIST) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(Items.MOSS_BLOCK, 2);
            int villagerLevel = 4;

            trades.get(villagerLevel)
                    .add((level, trader, rand) -> new MerchantOffer(new ItemCost(Items.EMERALD, 1), stack, 48, 3, 0.02F));
        }

        if (event.getType() == ModVillagers.BOTANIST) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(Items.FLOWERING_AZALEA, 1);
            int villagerLevel = 4;

            trades.get(villagerLevel)
                    .add((level, trader, rand) -> new MerchantOffer(new ItemCost(Items.EMERALD, 2), stack, 16, 7, 0.02F));
        }

        // lvl 5
        if (event.getType() == ModVillagers.BOTANIST) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(Items.ROOTED_DIRT, 4);
            int villagerLevel = 5;

            trades.get(villagerLevel)
                    .add((level, trader, rand) -> new MerchantOffer(new ItemCost(Items.EMERALD, 1), stack, 24, 3, 0.02F));
        }

        if (event.getType() == ModVillagers.BOTANIST) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(Items.HANGING_ROOTS, 3);
            int villagerLevel = 5;

            trades.get(villagerLevel)
                    .add((level, trader, rand) -> new MerchantOffer(new ItemCost(Items.EMERALD, 1), stack, 10, 7, 0.02F));
        }
    }
}
^///?} else {
/^package io.github.suel_ki.beautify.core.init;

import io.github.suel_ki.beautify.Beautify;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.minecraft.world.entity.npc.VillagerTrades;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.ItemCost;
import net.minecraft.world.item.trading.MerchantOffer;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.village.VillagerTradesEvent;

import java.util.List;
import net.minecraft.world.item.BlockItem;
import net.neoforged.neoforge.registries.DeferredItem;


@EventBusSubscriber(modid = Beautify.MODID)
public class TradesInit {
	private static final List<DeferredItem<BlockItem>> TRELLIS_ITEMS = List.of(ItemInitNeoForge.OAK_TRELLIS_ITEM, ItemInitNeoForge.SPRUCE_TRELLIS_ITEM, ItemInitNeoForge.BIRCH_TRELLIS_ITEM, ItemInitNeoForge.JUNGLE_TRELLIS_ITEM, ItemInitNeoForge.ACACIA_TRELLIS_ITEM, ItemInitNeoForge.DARK_OAK_TRELLIS_ITEM, ItemInitNeoForge.MANGROVE_TRELLIS_ITEM, ItemInitNeoForge.CRIMSON_TRELLIS_ITEM, ItemInitNeoForge.CHERRY_TRELLIS_ITEM, ItemInitNeoForge.WARPED_TRELLIS_ITEM);

    @SubscribeEvent
    public static void addCustomTrades(VillagerTradesEvent event) {
        // BOTANIST
        // lvl1
        if (event.getType() == ModVillagers.BOTANIST.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(ItemInitNeoForge.HANGING_POT_ITEM.get(), 1);
            int villagerLevel = 1;

            trades.get(villagerLevel)
                    .add((trader, rand) -> new MerchantOffer(new ItemCost(Items.EMERALD, 2), stack, 16, 6, 0.02F));
        }

        if (event.getType() == ModVillagers.BOTANIST.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(Items.FLOWER_POT, 2);
            int villagerLevel = 1;

            trades.get(villagerLevel)
                    .add((trader, rand) -> new MerchantOffer(new ItemCost(Items.EMERALD, 3), stack, 12, 5, 0.02F));
        }

        if (event.getType() == ModVillagers.BOTANIST.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(Items.VINE, 3);
            int villagerLevel = 1;

            trades.get(villagerLevel)
                    .add((trader, rand) -> new MerchantOffer(new ItemCost(Items.EMERALD, 2), stack, 16, 4, 0.02F));
        }

        // lvl2
		for (DeferredItem<BlockItem> trellis : TRELLIS_ITEMS) {
			Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
			ItemStack stack = new ItemStack(trellis.get(), 2);
			int villagerLevel = 2;

			trades.get(villagerLevel)
					.add((trader, rand) -> new MerchantOffer(new ItemCost(Items.EMERALD, 1), stack, 16, 3, 0.02F));
		}


        if (event.getType() == ModVillagers.BOTANIST.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(Items.BIG_DRIPLEAF, 3);
            int villagerLevel = 2;

            trades.get(villagerLevel)
                    .add((trader, rand) -> new MerchantOffer(new ItemCost(Items.EMERALD, 4), stack, 6, 9, 0.02F));
        }

        if (event.getType() == ModVillagers.BOTANIST.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(Items.SMALL_DRIPLEAF, 4);
            int villagerLevel = 2;

            trades.get(villagerLevel)
                    .add((trader, rand) -> new MerchantOffer(new ItemCost(Items.EMERALD, 3), stack, 6, 8, 0.02F));
        }

        // lvl3
        if (event.getType() == ModVillagers.BOTANIST.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(Items.LILY_PAD, 4);
            int villagerLevel = 3;

            trades.get(villagerLevel)
                    .add((trader, rand) -> new MerchantOffer(new ItemCost(Items.EMERALD, 1), stack, 8, 5, 0.02F));
        }

        if (event.getType() == ModVillagers.BOTANIST.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(Items.SPORE_BLOSSOM, 1);
            int villagerLevel = 3;

            trades.get(villagerLevel)
                    .add((trader, rand) -> new MerchantOffer(new ItemCost(Items.EMERALD, 3), stack, 12, 10, 0.02F));
        }

        // lvl 4
        if (event.getType() == ModVillagers.BOTANIST.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(Items.MOSS_BLOCK, 2);
            int villagerLevel = 4;

            trades.get(villagerLevel)
                    .add((trader, rand) -> new MerchantOffer(new ItemCost(Items.EMERALD, 1), stack, 48, 3, 0.02F));
        }

        if (event.getType() == ModVillagers.BOTANIST.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(Items.FLOWERING_AZALEA, 1);
            int villagerLevel = 4;

            trades.get(villagerLevel)
                    .add((trader, rand) -> new MerchantOffer(new ItemCost(Items.EMERALD, 2), stack, 16, 7, 0.02F));
        }

        // lvl 5
        if (event.getType() == ModVillagers.BOTANIST.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(Items.ROOTED_DIRT, 4);
            int villagerLevel = 5;

            trades.get(villagerLevel)
                    .add((trader, rand) -> new MerchantOffer(new ItemCost(Items.EMERALD, 1), stack, 24, 3, 0.02F));
        }

        if (event.getType() == ModVillagers.BOTANIST.get()) {
            Int2ObjectMap<List<VillagerTrades.ItemListing>> trades = event.getTrades();
            ItemStack stack = new ItemStack(Items.HANGING_ROOTS, 3);
            int villagerLevel = 5;

            trades.get(villagerLevel)
                    .add((trader, rand) -> new MerchantOffer(new ItemCost(Items.EMERALD, 1), stack, 10, 7, 0.02F));
        }
    }
}
^///?}
*///?}
