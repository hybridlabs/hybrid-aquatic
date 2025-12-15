package dev.hybridlabs.aquatic.utils;

import dev.hybridlabs.aquatic.Constants;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum HybridAquaticSpawnGroup {
    HYBRID_AQUATIC_FISH("fish", 16, true, false, 64),
    HYBRID_AQUATIC_CEPHALOPOD("cephalopod", 4, true, false, 64),
    HYBRID_AQUATIC_JELLY("jelly", 4, true, false, 64),
    HYBRID_AQUATIC_SHARK("shark", 8, true, true, 128),
    HYBRID_AQUATIC_CRUSTACEAN("crustacean", 8, true, false, 64),
    HYBRID_AQUATIC_CRITTER("critter", 8, true, false, 64),
    HYBRID_AQUATIC_MINIBOSS("miniboss", 4, false, true, 128),
    HYBRID_AQUATIC_MINION("minion", 8, false, true, 128),
    HYBRID_AQUATIC_MAMMAL("mammal", 2, true, false, 128);
    
    public MobCategory spawnGroup;
    public final ResourceLocation location;
    public final int spawnCap;
    public final boolean peaceful;
    public final boolean rare;
    public final int immediateDespawnRange;

    HybridAquaticSpawnGroup(String id, int spawnCap, boolean peaceful, boolean rare, int immediateDespawnRange) {
        this.location = new ResourceLocation(Constants.MOD_ID, id);
        this.spawnCap = spawnCap;
        this.peaceful = peaceful;
        this.rare = rare;
        this.immediateDespawnRange = immediateDespawnRange;
    }

    public static final Map<String, MobCategory> BY_NAME = new ConcurrentHashMap<>();

    public static MobCategory byName(String id) {
        ResourceLocation location = new ResourceLocation(Constants.MOD_ID, id);
        return BY_NAME.get(location.toString());
    }
}
