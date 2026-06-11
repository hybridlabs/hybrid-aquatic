package dev.hybridlabs.aquatic.platform.services;

import dev.hybridlabs.aquatic.item.armor.ForgeMoonJellyfishHatItem;
import dev.hybridlabs.aquatic.item.cosmetic.MoonJellyfishHatItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;

public class ForgeMoonJellyfishArmorProvider implements MoonJellyfishArmorProvider {
    public MoonJellyfishHatItem create(Item.Properties settings) {
        return new ForgeMoonJellyfishHatItem(settings);
    }
}
