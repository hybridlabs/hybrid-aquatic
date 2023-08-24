package dev.hybridlabs.aquatic.utils;

import net.minecraft.entity.SpawnGroup;

public enum HybridAquaticSpawnGroups {
  FISH("ha_fish", 18, true, false, 64),
  FISH_UNDERGROUND("ha_fish_underground", 6, true, false, 64),
  JELLY("ha_jelly", 3, false, false, 64),
  SHARK("ha_shark", 3, false, false, 64),
  CRITTER("ha_critter", 6, true, false, 64);
  
  public SpawnGroup spawnGroup;
  public final String name;
  public final int spawnCap;
  public final boolean peaceful;
  public final boolean rare;
  public final int immediateDespawnRange;
  
  HybridAquaticSpawnGroups(String name, int spawnCap, boolean peaceful, boolean rare, int immediateDespawnRange) {
    this.name = name;
    this.spawnCap = spawnCap;
    this.peaceful = peaceful;
    this.rare = rare;
    this.immediateDespawnRange = immediateDespawnRange;
  }
}
