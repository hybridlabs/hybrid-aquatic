package dev.hybridlabs.aquatic.platform.services;

import dev.hybridlabs.aquatic.item.cosmetic.EelScarfItem;

import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;

public interface EelArmorProvider {
    EelScarfItem create(ArmorItem.Type type, Item.Properties settings);
}
