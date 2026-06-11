package dev.hybridlabs.aquatic.platform.services;

import dev.hybridlabs.aquatic.item.armor.FabricMoonJellyfishHatItem;
import dev.hybridlabs.aquatic.item.cosmetic.MoonJellyfishArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;

public class FabricMoonJellyfishArmorProvider implements MoonJellyfishArmorProvider {
    public MoonJellyfishArmorItem create(ArmorItem.Type type, Item.Properties settings) {
        return new FabricMoonJellyfishHatItem(settings);
    }
}
