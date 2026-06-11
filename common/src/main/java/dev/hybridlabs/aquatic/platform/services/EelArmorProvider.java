package dev.hybridlabs.aquatic.platform.services;

import dev.hybridlabs.aquatic.item.cosmetic.EelScarfItem;

import net.minecraft.world.item.Item;

public interface EelArmorProvider {
    EelScarfItem create(Item.Properties settings);
}
