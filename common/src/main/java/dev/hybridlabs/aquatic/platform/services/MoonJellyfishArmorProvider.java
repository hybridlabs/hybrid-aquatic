package dev.hybridlabs.aquatic.platform.services;

import dev.hybridlabs.aquatic.item.cosmetic.MoonJellyfishArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;

public interface MoonJellyfishArmorProvider {
    MoonJellyfishArmorItem create(ArmorItem.Type type, Item.Properties settings);
}