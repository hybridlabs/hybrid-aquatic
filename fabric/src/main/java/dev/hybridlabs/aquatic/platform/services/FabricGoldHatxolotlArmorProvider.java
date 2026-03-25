package dev.hybridlabs.aquatic.platform.services;

import dev.hybridlabs.aquatic.item.cosmetic.FabricGoldHatxolotlArmorItem;
import dev.hybridlabs.aquatic.item.armor.GoldHatxolotlArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;

public class FabricGoldHatxolotlArmorProvider implements GoldHatxolotlArmorProvider {
    public GoldHatxolotlArmorItem create(ArmorItem.Type type, Item.Properties settings) {
        return new FabricGoldHatxolotlArmorItem(settings);
    }
}