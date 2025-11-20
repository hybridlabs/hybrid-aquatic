package dev.hybridlabs.aquatic.platform.services;

import dev.hybridlabs.aquatic.item.armor.ForgeManglerfishArmorItem;
import dev.hybridlabs.aquatic.item.armor.ManglerfishArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;

public class ForgeManglerfishArmorProvider implements ManglerfishArmorProvider {
    public ManglerfishArmorItem create(ArmorItem.Type type, Item.Properties settings) {
        return new ForgeManglerfishArmorItem(type, settings);
    }
}
