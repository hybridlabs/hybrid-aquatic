package dev.hybridlabs.aquatic.utils;

import net.minecraft.world.entity.MobCategory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum HybridAquaticSpawnGroup {
    FISH("FISH", 8, true, false, 64),

    FISH_UNDERGROUND("FISH_UNDERGROUND", 8, true, false, 64),

    CEPHALOPOD("CEPHALOPOD", 8, true, false, 64),

    JELLY("JELLY", 5, true, false, 64),

    JELLY_UNDERGROUND("JELLY_UNDERGROUND", 5, true, false, 64),

    SHARK("SHARK", 8, true, true, 128),

    SHARK_UNDERGROUND("SHARK_UNDERGROUND", 8, true, false, 128),

    CRUSTACEAN("CRUSTACEAN", 8, true, false, 64),

    CRUSTACEAN_UNDERGROUND("CRUSTACEAN_UNDERGROUND", 8, true, false, 64),

    CRITTER("CRITTER", 8, true, false, 64),

    MINIBOSS("MINIBOSS", 10, false, true, 128),

    MINION("MINION", 12, false, true, 128);

    public MobCategory spawnGroup;
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

    public static final Map<String, MobCategory> BY_NAME = new ConcurrentHashMap<>();

    public static MobCategory byName(String name) {
        return BY_NAME.get(name);
    }
}
