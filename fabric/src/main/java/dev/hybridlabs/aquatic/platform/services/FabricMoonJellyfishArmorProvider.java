package dev.hybridlabs.aquatic.platform.services;

import dev.hybridlabs.aquatic.item.armor.MoonJellyfishArmorItem;
import dev.hybridlabs.aquatic.item.armor.FabricMoonJellyfishArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;

public class FabricMoonJellyfishArmorProvider implements MoonJellyfishArmorProvider {
    public MoonJellyfishArmorItem create(ArmorItem.Type type, Item.Properties settings) {
        return new FabricMoonJellyfishArmorItem(type, settings);
    }
}