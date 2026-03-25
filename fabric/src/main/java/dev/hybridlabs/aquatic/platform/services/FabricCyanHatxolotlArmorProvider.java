package dev.hybridlabs.aquatic.platform.services;

import dev.hybridlabs.aquatic.item.cosmetic.FabricCyanHatxolotlArmorItem;
import dev.hybridlabs.aquatic.item.cosmetic.CyanHatxolotlArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;

public class FabricCyanHatxolotlArmorProvider implements CyanHatxolotlArmorProvider {
    public CyanHatxolotlArmorItem create(ArmorItem.Type type, Item.Properties settings) {
        return new FabricCyanHatxolotlArmorItem(settings);
    }
}