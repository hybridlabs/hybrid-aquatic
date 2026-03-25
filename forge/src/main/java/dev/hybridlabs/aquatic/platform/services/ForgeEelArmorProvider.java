package dev.hybridlabs.aquatic.platform.services;

import dev.hybridlabs.aquatic.item.armor.ForgeEelScarfItem;
import dev.hybridlabs.aquatic.item.cosmetic.EelScarfItem;
import net.minecraft.world.item.Item;

public class ForgeEelArmorProvider implements EelArmorProvider {
    public EelScarfItem create(Item.Properties settings) {
        return new ForgeEelScarfItem(settings);
    }
}