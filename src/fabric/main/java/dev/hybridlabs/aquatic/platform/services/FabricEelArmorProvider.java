package dev.hybridlabs.aquatic.platform.services;

import dev.hybridlabs.aquatic.item.armor.EelArmorItem;
import dev.hybridlabs.aquatic.item.armor.FabricEelArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;

public class FabricEelArmorProvider implements EelArmorProvider {
    public EelArmorItem create(ArmorItem.Type type, Item.Properties settings) {
        return new FabricEelArmorItem(type, settings);
    }
}