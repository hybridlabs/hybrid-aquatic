package dev.hybridlabs.aquatic.platform.services;

import dev.hybridlabs.aquatic.item.armor.ForgeManglerfishCosmeticItem;
import dev.hybridlabs.aquatic.item.armor.ManglerfishCosmeticItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;

public class ForgeManglerfishCosmeticProvider implements ManglerfishCosmeticProvider {
    public ManglerfishCosmeticItem create(Item.Properties settings) {
        return new ForgeManglerfishCosmeticItem(settings);
    }
}
