package dev.hybridlabs.aquatic.platform.services;

import dev.hybridlabs.aquatic.item.DivingArmorItem;
import dev.hybridlabs.aquatic.item.armor.FabricDivingArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;

public class FabricDivingArmorProvider implements DivingArmorProvider {
    public DivingArmorItem create(ArmorItem.Type type, Item.Properties settings) {
        return new FabricDivingArmorItem(type, settings);
    }
}