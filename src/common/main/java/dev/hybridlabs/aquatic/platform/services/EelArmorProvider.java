package dev.hybridlabs.aquatic.platform.services;

import dev.hybridlabs.aquatic.item.armor.EelArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;

public interface EelArmorProvider {
    EelArmorItem create(ArmorItem.Type type, Item.Properties settings);
}