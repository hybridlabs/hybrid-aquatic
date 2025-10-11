package dev.hybridlabs.aquatic.utils;

import net.minecraft.world.entity.MobCategory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum HybridAquaticSpawnGroup {
    HYBRID_AQUATIC_FISH("HYBRID_AQUATIC_FISH", 8, true, false, 64),

    HYBRID_AQUATIC_FISH_UNDERGROUND("HYBRID_AQUATIC_FISH_UNDERGROUND", 8, true, false, 64),

    HYBRID_AQUATIC_CEPHALOPOD("HYBRID_AQUATIC_CEPHALOPOD", 8, true, false, 64),

    HYBRID_AQUATIC_JELLY("HYBRID_AQUATIC_JELLY", 5, true, false, 64),

    HYBRID_AQUATIC_JELLY_UNDERGROUND("HYBRID_AQUATIC_JELLY_UNDERGROUND", 5, true, false, 64),

    HYBRID_AQUATIC_SHARK("HYBRID_AQUATIC_SHARK", 8, true, true, 128),

    HYBRID_AQUATIC_SHARK_UNDERGROUND("HYBRID_AQUATIC_SHARK_UNDERGROUND", 8, true, false, 128),

    HYBRID_AQUATIC_CRUSTACEAN("HYBRID_AQUATIC_CRUSTACEAN", 8, true, false, 64),

    HYBRID_AQUATIC_CRUSTACEAN_UNDERGROUND("HYBRID_AQUATIC_CRUSTACEAN_UNDERGROUND", 8, true, false, 64),

    HYBRID_AQUATIC_CRITTER("HYBRID_AQUATIC_CRITTER", 8, true, false, 64),

    HYBRID_AQUATIC_MINIBOSS("HYBRID_AQUATIC_MINIBOSS", 10, false, true, 128),

    HYBRID_AQUATIC_MINION("HYBRID_AQUATIC_MINION", 12, false, true, 128);

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
