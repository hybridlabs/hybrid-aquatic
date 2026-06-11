package dev.hybridlabs.aquatic.platform.services;

import dev.hybridlabs.aquatic.item.armor.FabricEelArmorItem;
import dev.hybridlabs.aquatic.item.cosmetic.EelArmorItem;

import net.minecraft.world.item.Item;

public class FabricEelArmorProvider implements EelArmorProvider {
    public EelArmorItem create(Item.Properties settings) {
        return new FabricEelArmorItem(settings);
    }
}
