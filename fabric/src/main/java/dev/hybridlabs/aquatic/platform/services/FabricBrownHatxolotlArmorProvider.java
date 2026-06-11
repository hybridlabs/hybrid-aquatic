package dev.hybridlabs.aquatic.platform.services;

import dev.hybridlabs.aquatic.item.cosmetic.FabricBrownHatxolotlArmorItem;
import dev.hybridlabs.aquatic.item.cosmetic.BrownHatxolotlArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;

public class FabricBrownHatxolotlArmorProvider implements BrownHatxolotlArmorProvider {
    public BrownHatxolotlArmorItem create(ArmorItem.Type type, Item.Properties settings) {
        return new FabricBrownHatxolotlArmorItem(settings);
    }
}