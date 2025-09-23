package dev.hybridlabs.aquatic.platform.services;

import dev.hybridlabs.aquatic.item.ForgeTurtleArmorItem;
import dev.hybridlabs.aquatic.item.armor.TurtleArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;

public class ForgeTurtleArmorProvider implements TurtleArmorProvider {
    public TurtleArmorItem create(ArmorItem.Type type, Item.Properties settings) {
        return new ForgeTurtleArmorItem(type, settings);
    }
}