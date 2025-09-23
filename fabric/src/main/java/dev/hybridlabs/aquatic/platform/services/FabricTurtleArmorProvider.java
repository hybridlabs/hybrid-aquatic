package dev.hybridlabs.aquatic.platform.services;

import dev.hybridlabs.aquatic.item.armor.TurtleArmorItem;
import dev.hybridlabs.aquatic.item.armor.FabricTurtleArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;

public class FabricTurtleArmorProvider implements TurtleArmorProvider {
    public TurtleArmorItem create(ArmorItem.Type type, Item.Properties settings) {
        return new FabricTurtleArmorItem(type, settings);
    }
}