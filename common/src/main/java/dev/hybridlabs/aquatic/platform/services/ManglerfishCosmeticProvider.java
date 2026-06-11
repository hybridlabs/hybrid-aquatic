package dev.hybridlabs.aquatic.platform.services;

import dev.hybridlabs.aquatic.item.armor.ManglerfishCosmeticItem;

import net.minecraft.world.item.Item;

public interface ManglerfishCosmeticProvider {
    ManglerfishCosmeticItem create(Item.Properties settings);
}
