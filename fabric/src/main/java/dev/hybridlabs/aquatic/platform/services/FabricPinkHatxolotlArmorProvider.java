package dev.hybridlabs.aquatic.platform.services;

import dev.hybridlabs.aquatic.item.armor.FabricPinkHatxolotlArmorItem;
import dev.hybridlabs.aquatic.item.armor.PinkHatxolotlArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;

public class FabricPinkHatxolotlArmorProvider implements PinkHatxolotlArmorProvider {
    public PinkHatxolotlArmorItem create(ArmorItem.Type type, Item.Properties settings) {
        return new FabricPinkHatxolotlArmorItem(type, settings);
    }
}