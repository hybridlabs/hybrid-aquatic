package dev.hybridlabs.aquatic.utils;

import net.minecraft.world.entity.MobCategory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum HybridAquaticSpawnGroup {
    FISH("fish", 8, true, false, 64),

    FISH_UNDERGROUND("fish_underground", 8, true, true, 64),

    DOLPHIN("dolphin", 4, true, true, 64),

    CEPHALOPOD("cephalopod", 5, true, false, 64),

    JELLY("jelly", 5, true, false, 64),

    JELLY_UNDERGROUND("jelly_underground", 5, true, true, 64),

    SHARK("shark", 5, true, true, 128),

    SHARK_UNDERGROUND("shark_underground", 5, true, true, 128),

    CRUSTACEAN("crustacean", 5, true, false, 64),

    CRUSTACEAN_UNDERGROUND("crustacean_underground", 5, true, false, 64),

    CRITTER("critter", 5, true, false, 64),

    MINIBOSS("miniboss", 10, false, true, 128);

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
