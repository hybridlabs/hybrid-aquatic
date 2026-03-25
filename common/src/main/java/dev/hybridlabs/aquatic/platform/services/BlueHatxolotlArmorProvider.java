package dev.hybridlabs.aquatic.platform.services;

import dev.hybridlabs.aquatic.item.cosmetic.BlueHatxolotlArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;

public interface BlueHatxolotlArmorProvider {
    BlueHatxolotlArmorItem create(ArmorItem.Type type, Item.Properties settings);
}