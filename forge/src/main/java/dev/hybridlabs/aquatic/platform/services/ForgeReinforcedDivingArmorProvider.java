package dev.hybridlabs.aquatic.platform.services;

import dev.hybridlabs.aquatic.item.armor.ReinforcedDivingArmorItem;
import dev.hybridlabs.aquatic.item.armor.ForgeReinforcedDivingArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;

public class ForgeReinforcedDivingArmorProvider implements ReinforcedDivingArmorProvider {
    public ReinforcedDivingArmorItem create(ArmorItem.Type type, Item.Properties settings) {
        return new ForgeReinforcedDivingArmorItem(type, settings);
    }
}