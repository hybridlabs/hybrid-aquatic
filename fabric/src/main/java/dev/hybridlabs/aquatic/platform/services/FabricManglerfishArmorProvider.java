package dev.hybridlabs.aquatic.platform.services;

import dev.hybridlabs.aquatic.item.ManglerfishArmorItem;
import dev.hybridlabs.aquatic.item.armor.FabricManglerfishArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;

public class FabricManglerfishArmorProvider implements ManglerfishArmorProvider {
    public ManglerfishArmorItem create(ArmorItem.Type type, Item.Properties settings) {
        return new FabricManglerfishArmorItem(type, settings);
    }
}