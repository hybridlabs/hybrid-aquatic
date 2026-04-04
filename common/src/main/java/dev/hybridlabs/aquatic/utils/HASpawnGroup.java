package dev.hybridlabs.aquatic.utils;

import dev.hybridlabs.aquatic.Constants;
import net.minecraft.world.entity.MobCategory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum HASpawnGroup {
    HYBRID_AQUATIC_FISH("fish", 10, true, false, 64),
    HYBRID_AQUATIC_RIVER_FISH("river_fish", 6, true, false, 64),
    HYBRID_AQUATIC_CEPHALOPOD("cephalopod", 4, true, false, 64),
    HYBRID_AQUATIC_JELLY("jelly", 4, true, false, 64),
    HYBRID_AQUATIC_SHARK("shark", 4, true, true, 128),
    HYBRID_AQUATIC_CRUSTACEAN("crustacean", 4, true, false, 64),
    HYBRID_AQUATIC_CRITTER("critter", 4, true, false, 64),
    HYBRID_AQUATIC_MINIBOSS("miniboss", 4, false, true, 128),
    HYBRID_AQUATIC_MINION("minion", 8, false, true, 64),
    HYBRID_AQUATIC_MAMMAL("mammal", 2, true, true, 128);
    
    public MobCategory spawnGroup;
    public final String gName;
    public final int spawnCap;
    public final boolean peaceful;
    public final boolean rare;
    public final int immediateDespawnRange;

    HASpawnGroup(String name, int spawnCap, boolean peaceful, boolean rare, int immediateDespawnRange) {
        this.gName = Constants.MOD_ID + ":" + name;
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