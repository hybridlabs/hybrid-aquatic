package dev.hybridlabs.aquatic.platform.services;

import dev.hybridlabs.aquatic.item.armor.ForgeBrownHatxolotlArmorItem;
import dev.hybridlabs.aquatic.item.armor.BrownHatxolotlArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;

public class ForgeBrownHatxolotlArmorProvider implements BrownHatxolotlArmorProvider {
    public BrownHatxolotlArmorItem create(ArmorItem.Type type, Item.Properties settings) {
        return new ForgeBrownHatxolotlArmorItem(type, settings);
    }
}