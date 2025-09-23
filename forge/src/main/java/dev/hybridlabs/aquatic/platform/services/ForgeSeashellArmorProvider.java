package dev.hybridlabs.aquatic.platform.services;

import dev.hybridlabs.aquatic.item.armor.ForgeSeashellArmorItem;
import dev.hybridlabs.aquatic.item.armor.SeashellArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;

public class ForgeSeashellArmorProvider implements SeashellArmorProvider {
    public SeashellArmorItem create(ArmorItem.Type type, Item.Properties settings) {
        return new ForgeSeashellArmorItem(type, settings);
    }
}