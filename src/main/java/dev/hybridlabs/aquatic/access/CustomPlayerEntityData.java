package dev.hybridlabs.aquatic.access;

public interface CustomPlayerEntityData {
  default void hybrid_aquatic$setHurtTime(int value) { }
  default int hybrid_aquatic$getHurtTime() { return 0; }
}
