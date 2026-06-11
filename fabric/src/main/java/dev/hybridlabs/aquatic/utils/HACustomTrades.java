package dev.hybridlabs.aquatic.utils;

import dev.hybridlabs.aquatic.item.HAItems;
import net.fabricmc.fabric.api.object.builder.v1.trade.TradeOfferHelper;
import net.minecraft.world.entity.npc.VillagerProfession;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.trading.MerchantOffer;

public class HybridAquaticCustomTrades {
    public static void registerCustomTrades() {
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FISHERMAN, 1, factories -> {
            factories.add((entity, random) -> new MerchantOffer(new ItemCost(Items.EMERALD, 1),
                    new ItemStack(HAItems.INSTANCE.getCOCONUT_CRAB_CLAW().get(), 1), 4, 5, 0.05f));
            factories.add((entity, random) -> new MerchantOffer(new ItemCost(Items.EMERALD, 1),
                    new ItemStack(HAItems.INSTANCE.getDUNGENESS_CRAB_CLAW().get(), 1), 4, 5, 0.05f));
            factories.add((entity, random) -> new MerchantOffer(new ItemCost(Items.EMERALD, 1),
                    new ItemStack(HAItems.INSTANCE.getFLOWER_CRAB_CLAW().get(), 1), 4, 5, 0.05f));
            factories.add((entity, random) -> new MerchantOffer(new ItemCost(Items.EMERALD, 1),
                    new ItemStack(HAItems.INSTANCE.getFIDDLER_CRAB_CLAW().get(), 1), 4, 5, 0.05f));
            factories.add((entity, random) -> new MerchantOffer(new ItemCost(Items.EMERALD, 1),
                    new ItemStack(HAItems.INSTANCE.getGHOST_CRAB_CLAW().get(), 1), 4, 5, 0.05f));
            factories.add((entity, random) -> new MerchantOffer(new ItemCost(Items.EMERALD, 1),
                    new ItemStack(HAItems.INSTANCE.getLIGHTFOOT_CRAB_CLAW().get(), 1), 4, 5, 0.05f));
            factories.add((entity, random) -> new MerchantOffer(new ItemCost(Items.EMERALD, 1),
                    new ItemStack(HAItems.INSTANCE.getYETI_CRAB_CLAW().get(), 1), 4, 5, 0.05f));
            factories.add((entity, random) -> new MerchantOffer(new ItemCost(Items.EMERALD, 1),
                    new ItemStack(HAItems.INSTANCE.getVAMPIRE_CRAB_CLAW().get(), 1), 4, 5, 0.05f));
            factories.add((entity, random) -> new MerchantOffer(new ItemCost(Items.EMERALD, 1),
                    new ItemStack(HAItems.INSTANCE.getSPIDER_CRAB_CLAW().get(), 1), 4, 5, 0.05f));
            factories.add((entity, random) -> new MerchantOffer(new ItemCost(Items.EMERALD, 1),
                    new ItemStack(HAItems.INSTANCE.getRAW_FISH_MEAT().get(), 4), 4, 5, 0.05f));
            factories.add((entity, random) -> new MerchantOffer(new ItemCost(Items.EMERALD, 1),
                    new ItemStack(HAItems.INSTANCE.getRAW_FISH_STEAK().get(), 2), 4, 5, 0.05f));
        });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FISHERMAN, 2, factories -> {
            factories.add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 6),
                    new ItemStack(HAItems.INSTANCE.getFISHING_NET().get(), 1), 1, 10, 0.05f));
            factories.add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 3),
                    new ItemStack(HAItems.INSTANCE.getBARBED_HOOK().get(), 1), 1, 10, 0.05f));
            factories.add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 3),
                    new ItemStack(HAItems.INSTANCE.getGLOWING_HOOK().get(), 1), 1, 10, 0.05f));
            factories.add((entity, random) -> new MerchantOffer(new ItemStack(Items.EMERALD, 3),
                    new ItemStack(HAItems.INSTANCE.getMAGNETIC_HOOK().get(), 1), 1, 10, 0.05f));
        });
        TradeOfferHelper.registerVillagerOffers(VillagerProfession.FISHERMAN, 3, factories -> {
            factories.add((entity, random) -> new MerchantOffer(new ItemStack(HAItems.INSTANCE.getPEARL().get(), 1), new ItemStack(Items.EMERALD, 2), 6, 15, 0.05f));
            factories.add((entity, random) -> new MerchantOffer(new ItemStack(HAItems.INSTANCE.getBLACK_PEARL().get(), 1), new ItemStack(Items.EMERALD, 5), 3, 15, 0.05f));
import dev.hybridlabs.aquatic.item.HAItems;
import net.minecraft.world.item.trading.ItemCost;
public class HACustomTrades {
        });
    }
}
