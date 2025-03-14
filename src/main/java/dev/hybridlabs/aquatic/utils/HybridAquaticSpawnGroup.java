package dev.hybridlabs.aquatic.utils;

import net.minecraft.entity.SpawnGroup;

public enum HybridAquaticSpawnGroup {
    FISH("ha_fish", 8, true, false, 64),

    FISH_UNDERGROUND("ha_fish_underground", 8, true, true, 64),

    CEPHALOPOD("ha_cephalopod", 5, true, false, 64),

    JELLY("ha_jelly", 5, true, false, 64),

    JELLY_UNDERGROUND("ha_jelly_underground", 5, true, true, 64),

    SHARK("ha_shark", 5, true, true, 128),

    SHARK_UNDERGROUND("ha_shark_underground", 5, true, true, 128),

    CRUSTACEAN("crustacean", 5, true, false, 64),

    CRUSTACEAN_UNDERGROUND("crustacean_underground", 5, true, false, 64),

    CRITTER("ha_critter", 5, true, false, 64),

    MINIBOSS("ha_miniboss", 10, false, true, 128);

    public SpawnGroup spawnGroup;
    public final String name;
    public final int spawnCap;
    public final boolean peaceful;
    public final boolean rare;
    public final int immediateDespawnRange;

    HybridAquaticSpawnGroup(String name, int spawnCap, boolean peaceful, boolean rare, int immediateDespawnRange) {
        this.name = name;
        this.spawnCap = spawnCap;
        this.peaceful = peaceful;
        this.rare = rare;
        this.immediateDespawnRange = immediateDespawnRange;
    }
}
