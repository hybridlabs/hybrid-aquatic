package dev.hybridlabs.aquatic.platform.services;

import dev.hybridlabs.aquatic.item.armor.FabricReinforcedDivingArmorItem;
import dev.hybridlabs.aquatic.item.armor.ReinforcedDivingArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;

public class FabricReinforcedDivingArmorProvider implements ReinforcedDivingArmorProvider {
    public ReinforcedDivingArmorItem create(ArmorItem.Type type, Item.Properties settings) {
        return new FabricReinforcedDivingArmorItem(type, settings);
    }
}