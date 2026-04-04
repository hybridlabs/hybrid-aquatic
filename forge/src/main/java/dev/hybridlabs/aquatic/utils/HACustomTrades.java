package dev.hybridlabs.aquatic.utils;


import dev.hybridlabs.aquatic.block.PlushieBlock;
import dev.hybridlabs.aquatic.item.HAItems;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.common.BasicItemListing;
import net.minecraftforge.event.village.VillagerTradesEvent;
import net.minecraftforge.event.village.WandererTradesEvent;

import java.util.Arrays;

public class HACustomTrades {
    public static void registerCustomTrades(VillagerTradesEvent event) {
        if (event.getType() == VillagerProfession.FISHERMAN) {
            var trades = event.getTrades();
            trades.get(1).addAll(trades.get(1).size() - 1,
                    Arrays.asList(new BasicItemListing(new ItemStack(Items.EMERALD, 1),
                            new ItemStack(HAItems.INSTANCE.getCOCONUT_CRAB_CLAW().get(), 1), 4, 5, 0.05f),
                            new BasicItemListing(new ItemStack(Items.EMERALD, 1),
                                    new ItemStack(HAItems.INSTANCE.getDUNGENESS_CRAB_CLAW().get(), 1), 4,
                                    5, 0.05f), new BasicItemListing(new ItemStack(Items.EMERALD, 1),
                                    new ItemStack(HAItems.INSTANCE.getFLOWER_CRAB_CLAW().get(), 1), 4, 5,
                                    0.05f), new BasicItemListing(new ItemStack(Items.EMERALD, 1),
                                    new ItemStack(HAItems.INSTANCE.getFIDDLER_CRAB_CLAW().get(), 1), 4, 5,
                                    0.05f), new BasicItemListing(new ItemStack(Items.EMERALD, 1),
                                    new ItemStack(HAItems.INSTANCE.getGHOST_CRAB_CLAW().get(), 1), 4, 5,
                                    0.05f), new BasicItemListing(new ItemStack(Items.EMERALD, 1),
                                    new ItemStack(HAItems.INSTANCE.getLIGHTFOOT_CRAB_CLAW().get(), 1), 4,
                                    5, 0.05f), new BasicItemListing(new ItemStack(Items.EMERALD, 1),
                                    new ItemStack(HAItems.INSTANCE.getYETI_CRAB_CLAW().get(), 1), 4, 5,
                                    0.05f), new BasicItemListing(new ItemStack(Items.EMERALD, 1),
                                    new ItemStack(HAItems.INSTANCE.getVAMPIRE_CRAB_CLAW().get(), 1), 4, 5,
                                    0.05f), new BasicItemListing(new ItemStack(Items.EMERALD, 1),
                                    new ItemStack(HAItems.INSTANCE.getSPIDER_CRAB_CLAW().get(), 1), 4, 5,
                                    0.05f), new BasicItemListing(new ItemStack(Items.EMERALD, 1),
                                    new ItemStack(HAItems.INSTANCE.getRAW_FISH_MEAT().get(), 4), 4, 5,
                                    0.05f), new BasicItemListing(new ItemStack(Items.EMERALD, 1),
                                    new ItemStack(HAItems.INSTANCE.getRAW_FISH_STEAK().get(), 2), 4, 5,
                                    0.05f)));

            trades.get(2).addAll(trades.get(2).size() - 1,
                    Arrays.asList(new BasicItemListing(new ItemStack(Items.EMERALD, 6),
                            new ItemStack(HAItems.INSTANCE.getFISHING_NET().get(), 1), 1, 10, 0.05f),
                            new BasicItemListing(new ItemStack(Items.EMERALD, 3),
                                    new ItemStack(HAItems.INSTANCE.getBARBED_HOOK().get(), 1), 1, 10,
                                    0.05f), new BasicItemListing(new ItemStack(Items.EMERALD, 3),
                                    new ItemStack(HAItems.INSTANCE.getGLOWING_HOOK().get(), 1), 1, 10,
                                    0.05f), new BasicItemListing(new ItemStack(Items.EMERALD, 3),
                                    new ItemStack(HAItems.INSTANCE.getMAGNETIC_HOOK().get(), 1), 1, 10,
                                    0.05f)

            ));

            trades.get(3).addAll(trades.get(3).size() - 1,
                    Arrays.asList(new BasicItemListing(new ItemStack(HAItems.INSTANCE.getPEARL().get(), 1)
                            , new ItemStack(Items.EMERALD, 2), 6, 15, 0.05f),
                            new BasicItemListing(new ItemStack(HAItems.INSTANCE.getBLACK_PEARL().get(), 1)
                                    , new ItemStack(Items.EMERALD, 5), 3, 15, 0.05f)));

        }
    }

    public static void registerWandererTrades(WandererTradesEvent event) {
        var rareTrades = event.getRareTrades();
        BuiltInRegistries.ITEM.stream().filter((item) -> item instanceof BlockItem && ((BlockItem) item).getBlock() instanceof PlushieBlock).forEach((block) -> rareTrades.add(new BasicItemListing(8, new ItemStack(block), 1, 2, 2f)));
    }
}

