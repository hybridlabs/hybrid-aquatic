package dev.hybridlabs.aquatic.platform.services;

import dev.hybridlabs.aquatic.item.armor.GlowingDivingArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;

public interface GlowingDivingArmorProvider {
    GlowingDivingArmorItem create(ArmorItem.Type type, Item.Properties settings);
}