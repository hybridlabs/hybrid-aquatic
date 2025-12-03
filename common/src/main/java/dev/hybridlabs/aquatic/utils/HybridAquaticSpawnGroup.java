package dev.hybridlabs.aquatic.utils;

import dev.hybridlabs.aquatic.Constants;
import net.minecraft.world.entity.MobCategory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum HybridAquaticSpawnGroup {
    HYBRID_AQUATIC_FISH("fish", 8, true, false, 64),
    HYBRID_AQUATIC_FISH_UNDERGROUND("fish_underground", 8, true, false, 64),
    HYBRID_AQUATIC_CEPHALOPOD("cephalopod", 8, true, false, 64),
    HYBRID_AQUATIC_JELLY("jelly", 5, true, false, 64),
    HYBRID_AQUATIC_JELLY_UNDERGROUND("jelly_underground", 5, true, false, 64),
    HYBRID_AQUATIC_SHARK("shark", 8, true, true, 128),
    HYBRID_AQUATIC_SHARK_UNDERGROUND("shark_underground", 8, true, false, 128),
    HYBRID_AQUATIC_CRUSTACEAN("crustacean", 8, true, false, 64),
    HYBRID_AQUATIC_CRUSTACEAN_UNDERGROUND("crustacean_underground", 8, true, false, 64),
    HYBRID_AQUATIC_CRITTER("critter", 8, true, false, 64),
    HYBRID_AQUATIC_MINIBOSS("miniboss", 10, false, true, 128),
    HYBRID_AQUATIC_MINION("minion", 12, false, true, 128),
    HYBRID_AQUATIC_MAMMAL("mammal", 5, true, false, 64);
    
    public MobCategory spawnGroup;
    public final String name;
    public final int spawnCap;
    public final boolean peaceful;
    public final boolean rare;
    public final int immediateDespawnRange;

    HybridAquaticSpawnGroup(String name, int spawnCap, boolean peaceful, boolean rare, int immediateDespawnRange) {
        this.name = Constants.MOD_ID + ":" + name;
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
