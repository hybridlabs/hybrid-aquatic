package dev.hybridlabs.aquatic.platform.services;

import dev.hybridlabs.aquatic.item.cosmetic.ForgeStripedEelScarfItem;
import dev.hybridlabs.aquatic.item.cosmetic.StripedEelScarfItem;
import net.minecraft.world.item.Item;

public class ForgeStripedEelArmorProvider implements StripedEelArmorProvider {
    public StripedEelScarfItem create(Item.Properties settings) {
        return new ForgeStripedEelScarfItem(settings);
    }
}