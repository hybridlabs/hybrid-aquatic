package dev.hybridlabs.aquatic.platform.services;

import dev.hybridlabs.aquatic.item.SeashellArmorItem;
import dev.hybridlabs.aquatic.item.armor.FabricSeashellArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;

public class FabricSeashellArmorProvider implements SeashellArmorProvider {
    public SeashellArmorItem create(ArmorItem.Type type, Item.Properties settings) {
        return new FabricSeashellArmorItem(type, settings);
    }
}