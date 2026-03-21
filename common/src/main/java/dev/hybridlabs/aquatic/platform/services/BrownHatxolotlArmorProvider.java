package dev.hybridlabs.aquatic.platform.services;

import dev.hybridlabs.aquatic.item.armor.BrownHatxolotlArmorItem;
import dev.hybridlabs.aquatic.item.armor.PinkHatxolotlArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;

public interface BrownHatxolotlArmorProvider {
    BrownHatxolotlArmorItem create(ArmorItem.Type type, Item.Properties settings);
}