package dev.hybridlabs.aquatic.platform.services;

import dev.hybridlabs.aquatic.item.armor.EelArmorItem;
import dev.hybridlabs.aquatic.item.armor.ForgeEelArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;

public class ForgeEelArmorProvider implements EelArmorProvider {
    public EelArmorItem create(ArmorItem.Type type, Item.Properties settings) {
        return new ForgeEelArmorItem(type, settings);
    }
}