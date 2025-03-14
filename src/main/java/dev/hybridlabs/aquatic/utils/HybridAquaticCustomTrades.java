package dev.hybridlabs.aquatic.utils;

import dev.hybridlabs.aquatic.item.HybridAquaticItems;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.VillagerProfession;

public class HybridAquaticCustomTrades {
    public static void registerCustomTrades() {
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FISHERMAN, 1,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 1),
                            new ItemStack(HybridAquaticItems.INSTANCE.getCOCONUT_CRAB_CLAW(), 1),
                            4, 5, 0.05f));
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 1),
                            new ItemStack(HybridAquaticItems.INSTANCE.getDUNGENESS_CRAB_CLAW(), 1),
                            4, 5, 0.05f));
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 1),
                            new ItemStack(HybridAquaticItems.INSTANCE.getFLOWER_CRAB_CLAW(), 1),
                            4, 5, 0.05f));
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 1),
                            new ItemStack(HybridAquaticItems.INSTANCE.getFIDDLER_CRAB_CLAW(), 1),
                            4, 5, 0.05f));
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 1),
                            new ItemStack(HybridAquaticItems.INSTANCE.getGHOST_CRAB_CLAW(), 1),
                            4, 5, 0.05f));
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 1),
                            new ItemStack(HybridAquaticItems.INSTANCE.getLIGHTFOOT_CRAB_CLAW(), 1),
                            4, 5, 0.05f));
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 1),
                            new ItemStack(HybridAquaticItems.INSTANCE.getYETI_CRAB_CLAW(), 1),
                            4, 5, 0.05f));
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 1),
                            new ItemStack(HybridAquaticItems.INSTANCE.getVAMPIRE_CRAB_CLAW(), 1),
                            4, 5, 0.05f));
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 1),
                            new ItemStack(HybridAquaticItems.INSTANCE.getSPIDER_CRAB_CLAW(), 1),
                            4, 5, 0.05f));
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 1),
                            new ItemStack(HybridAquaticItems.INSTANCE.getRAW_FISH_MEAT(), 4),
                            4, 5, 0.05f));
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 1),
                            new ItemStack(HybridAquaticItems.INSTANCE.getRAW_FISH_STEAK(), 2),
                            4, 5, 0.05f));
                });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FISHERMAN, 2,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 6),
                            new ItemStack(HybridAquaticItems.INSTANCE.getFISHING_NET(), 1),
                            1, 10, 0.05f));
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 3),
                            new ItemStack(HybridAquaticItems.INSTANCE.getBARBED_HOOK(), 1),
                            1, 10, 0.05f));
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 3),
                            new ItemStack(HybridAquaticItems.INSTANCE.getGLOWING_HOOK(), 1),
                            1, 10, 0.05f));
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(Items.EMERALD, 3),
                            new ItemStack(HybridAquaticItems.INSTANCE.getMAGNETIC_HOOK(), 1),
                            1, 10, 0.05f));
                });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FISHERMAN, 3,
                factories -> {
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(HybridAquaticItems.INSTANCE.getPEARL(), 1),
                            new ItemStack(Items.EMERALD, 2),
                            6, 15, 0.05f));
                    factories.add((entity, random) -> new TradeOffer(
                            new ItemStack(HybridAquaticItems.INSTANCE.getBLACK_PEARL(), 1),
                            new ItemStack(Items.EMERALD, 5),
                            3, 15, 0.05f));
                });
    }
}
