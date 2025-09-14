package dev.hybridlabs.aquatic.platform.services;

import dev.hybridlabs.aquatic.item.TurtleArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;

public interface TurtleArmorProvider {
    TurtleArmorItem create(ArmorItem.Type type, Item.Properties settings);
}