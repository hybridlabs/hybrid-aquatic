package dev.hybridlabs.aquatic.access;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public interface CustomFishingBobberEntityData {
  default void hybrid_aquatic$setLureItem(ItemStack value) {
    throw new AssertionError();
  }
  
  default ItemStack hybrid_aquatic$getLureItem() {
    throw new AssertionError();
  }
}
