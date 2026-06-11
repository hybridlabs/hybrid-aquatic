package dev.hybridlabs.aquatic.platform.services;

import dev.hybridlabs.aquatic.item.armor.ForgeGlowingDivingArmorItem;
import dev.hybridlabs.aquatic.item.armor.GlowingDivingArmorItem;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;

public class ForgeGlowingDivingArmorProvider implements GlowingDivingArmorProvider {
    public GlowingDivingArmorItem create(ArmorItem.Type type, Item.Properties settings) {
        return new ForgeGlowingDivingArmorItem(type, settings);
    }
}