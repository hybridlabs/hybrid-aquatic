package dev.hybridlabs.aquatic.platform.services;

import dev.hybridlabs.aquatic.item.armor.FabricManglerfishCosmeticItem;
import dev.hybridlabs.aquatic.item.armor.ManglerfishCosmeticItem;
import net.minecraft.world.item.Item;

public class FabricManglerfishCosmeticProvider implements ManglerfishCosmeticProvider {
    public ManglerfishCosmeticItem create(Item.Properties settings) {
        return new FabricManglerfishCosmeticItem(settings);
    }
}
