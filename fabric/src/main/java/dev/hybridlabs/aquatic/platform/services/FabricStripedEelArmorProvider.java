package dev.hybridlabs.aquatic.platform.services;

import dev.hybridlabs.aquatic.item.cosmetic.FabricStripedEelArmorItem;
import dev.hybridlabs.aquatic.item.cosmetic.StripedEelScarfItem;
import net.minecraft.world.item.Item;

public class FabricStripedEelArmorProvider implements StripedEelArmorProvider {
    public StripedEelScarfItem create(Item.Properties settings) {
        return new FabricStripedEelArmorItem(settings);
    }
}
