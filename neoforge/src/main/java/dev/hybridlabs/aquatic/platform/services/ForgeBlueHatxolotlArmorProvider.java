package dev.hybridlabs.aquatic.platform.services;

import dev.hybridlabs.aquatic.item.armor.ForgeBlueHatxolotlArmorItem;
import dev.hybridlabs.aquatic.item.cosmetic.BlueHatxolotlArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;

public class ForgeBlueHatxolotlArmorProvider implements BlueHatxolotlArmorProvider {
    public BlueHatxolotlArmorItem create(ArmorItem.Type type, Item.Properties settings) {
        return new ForgeBlueHatxolotlArmorItem(settings);
    }
}