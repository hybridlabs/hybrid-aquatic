package dev.hybridlabs.aquatic.world.gen.structure

import net.minecraft.world.entity.MobCategory
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride.BoundingBoxType

data class SpawnModifier(
    val category: MobCategory,
    val spawns: List<SpawnerData>,
    val boundingBoxType: BoundingBoxType = BoundingBoxType.STRUCTURE
) {
}