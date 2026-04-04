package dev.hybridlabs.aquatic.entity

import dev.hybridlabs.aquatic.utils.HASpawnGroup
import net.minecraft.world.entity.MobCategory

object ForgeSpawnGroupRegistry {
    fun createHybridAquaticSpawnGroups() {
        // Extend the MobCategory enum with our spawn groups
        HASpawnGroup.entries.toTypedArray().forEach {
            MobCategory.create(
                it.name,
                it.gName,
                it.spawnCap,
                it.peaceful,
                it.rare,
                it.immediateDespawnRange
            )
        }
    }
}