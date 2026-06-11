package dev.hybridlabs.aquatic.platform.services;

import dev.hybridlabs.aquatic.item.cosmetic.PinkHatxolotlArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;

public interface PinkHatxolotlArmorProvider {
    PinkHatxolotlArmorItem create(ArmorItem.Type type, Item.Properties settings);
}