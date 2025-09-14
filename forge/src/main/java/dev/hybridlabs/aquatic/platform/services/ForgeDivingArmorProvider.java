package dev.hybridlabs.aquatic.platform.services;

import dev.hybridlabs.aquatic.item.DivingArmorItem;
import dev.hybridlabs.aquatic.item.armor.ForgeDivingArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;

public class ForgeDivingArmorProvider implements DivingArmorProvider {
    public DivingArmorItem create(ArmorItem.Type type, Item.Properties settings) {
        return new ForgeDivingArmorItem(type, settings);
    }
}