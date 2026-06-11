package dev.hybridlabs.aquatic.access;


import net.minecraft.world.item.ItemStack;

public interface CustomFishingBobberEntityData {
    default void setLureItem(ItemStack value) {
        throw new AssertionError();
    }

    default ItemStack getLureItem() {
        throw new AssertionError();
    }
}
