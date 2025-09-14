package dev.hybridlabs.aquatic.platform.services;

import dev.hybridlabs.aquatic.item.MoonJellyfishArmorItem;
import dev.hybridlabs.aquatic.item.armor.ForgeMoonJellyfishArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;

public class ForgeMoonJellyfishArmorProvider implements MoonJellyfishArmorProvider {
    public MoonJellyfishArmorItem create(ArmorItem.Type type, Item.Properties settings) {
        return new ForgeMoonJellyfishArmorItem(type, settings);
    }
}