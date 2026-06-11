package dev.hybridlabs.aquatic.platform.services;

import dev.hybridlabs.aquatic.item.armor.SeashellArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;

public interface SeashellArmorProvider {
    SeashellArmorItem create(ArmorItem.Type type, Item.Properties settings);
}
