package dev.hybridlabs.aquatic.platform.services;

import dev.hybridlabs.aquatic.item.cosmetic.FabricPinkHatxolotlArmorItem;
import dev.hybridlabs.aquatic.item.cosmetic.PinkHatxolotlArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;

public class FabricPinkHatxolotlArmorProvider implements PinkHatxolotlArmorProvider {
    public PinkHatxolotlArmorItem create(ArmorItem.Type type, Item.Properties settings) {
        return new FabricPinkHatxolotlArmorItem(settings);
    }
}