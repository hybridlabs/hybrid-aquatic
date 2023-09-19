package dev.hybridlabs.aquatic.access;

import net.minecraft.item.Item;

public interface CustomFishingBobberEntityData {
  default void hybrid_aquatic$setLureItem(Item value) {
    throw new AssertionError();
  }
  
  default Item hybrid_aquatic$getLureItem() {
    throw new AssertionError();
  }
}
