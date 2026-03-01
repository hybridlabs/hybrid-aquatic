package dev.hybridlabs.aquatic.platform.services;

import dev.hybridlabs.aquatic.item.armor.FabricGlowingDivingArmorItem;
import dev.hybridlabs.aquatic.item.armor.GlowingDivingArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;

public class FabricGlowingDivingArmorProvider implements GlowingDivingArmorProvider {
    public GlowingDivingArmorItem create(ArmorItem.Type type, Item.Properties settings) {
        return new FabricGlowingDivingArmorItem(type, settings);
    }
}