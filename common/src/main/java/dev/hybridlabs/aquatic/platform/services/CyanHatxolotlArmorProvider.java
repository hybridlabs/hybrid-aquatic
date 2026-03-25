package dev.hybridlabs.aquatic.platform.services;

import dev.hybridlabs.aquatic.item.cosmetic.CyanHatxolotlArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;

public interface CyanHatxolotlArmorProvider {
    CyanHatxolotlArmorItem create(ArmorItem.Type type, Item.Properties settings);
}