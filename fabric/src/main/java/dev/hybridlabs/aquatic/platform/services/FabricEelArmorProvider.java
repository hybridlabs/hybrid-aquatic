package dev.hybridlabs.aquatic.platform.services;

import dev.hybridlabs.aquatic.item.cosmetic.FabricEelArmorItem;
import dev.hybridlabs.aquatic.item.cosmetic.EelScarfItem;

import net.minecraft.world.item.Item;

public class FabricEelArmorProvider implements EelArmorProvider {
    public EelScarfItem create(Item.Properties settings) {
        return new FabricEelArmorItem(settings);
    }
}
