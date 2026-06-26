package dev.hybridlabs.aquatic.platform.services;

import dev.hybridlabs.aquatic.item.armor.ManglerfishArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;

public interface ManglerfishArmorProvider {
    ManglerfishArmorItem create(ArmorItem.Type type, Item.Properties settings);
}