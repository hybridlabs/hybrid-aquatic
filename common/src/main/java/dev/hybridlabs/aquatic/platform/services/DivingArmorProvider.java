package dev.hybridlabs.aquatic.platform.services;

import dev.hybridlabs.aquatic.item.armor.DivingArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;

public interface DivingArmorProvider {
    DivingArmorItem create(ArmorItem.Type type, Item.Properties settings);
}