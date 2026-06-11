package dev.hybridlabs.aquatic.platform.services;

import dev.hybridlabs.aquatic.item.cosmetic.MoonJellyfishHatItem;
import net.minecraft.world.item.Item;

public interface MoonJellyfishArmorProvider {
    MoonJellyfishHatItem create(Item.Properties settings);
}