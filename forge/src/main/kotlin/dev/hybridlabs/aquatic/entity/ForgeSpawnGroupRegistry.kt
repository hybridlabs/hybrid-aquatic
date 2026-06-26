package dev.hybridlabs.aquatic.entity

import dev.hybridlabs.aquatic.utils.HASpawnGroup
import net.minecraft.world.entity.MobCategory

object ForgeSpawnGroupRegistry {
    fun createHybridAquaticSpawnGroups() {
        // Extend the MobCategory enum with our spawn groups
        for (group in HASpawnGroup.values()) {
            MobCategory.create(
                group.location.path,
                group.location.toString(),
                group.spawnCap,
                group.peaceful,
                group.rare,
                group.immediateDespawnRange
            )
        }
    }
}