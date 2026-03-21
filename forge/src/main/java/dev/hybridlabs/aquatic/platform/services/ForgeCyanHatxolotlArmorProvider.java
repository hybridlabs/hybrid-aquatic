package dev.hybridlabs.aquatic.platform.services;

import dev.hybridlabs.aquatic.item.armor.ForgeCyanHatxolotlArmorItem;
import dev.hybridlabs.aquatic.item.armor.CyanHatxolotlArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;

public class ForgeCyanHatxolotlArmorProvider implements CyanHatxolotlArmorProvider {
    public CyanHatxolotlArmorItem create(ArmorItem.Type type, Item.Properties settings) {
        return new ForgeCyanHatxolotlArmorItem(type, settings);
    }
}