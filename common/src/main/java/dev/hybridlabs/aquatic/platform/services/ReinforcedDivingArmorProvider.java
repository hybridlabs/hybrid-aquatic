package dev.hybridlabs.aquatic.platform.services;

import dev.hybridlabs.aquatic.item.armor.ReinforcedDivingArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;

public interface ReinforcedDivingArmorProvider {
    ReinforcedDivingArmorItem create(ArmorItem.Type type, Item.Properties settings);
}