package dev.hybridlabs.aquatic.platform.services;

import dev.hybridlabs.aquatic.item.cosmetic.StripedEelScarfItem;
import net.minecraft.world.item.Item;

public interface StripedEelArmorProvider {
    StripedEelScarfItem create(Item.Properties settings);
}
