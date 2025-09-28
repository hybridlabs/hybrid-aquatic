package dev.hybridlabs.aquatic.world.gen.structure

import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.server.MinecraftServer
import net.minecraft.util.random.WeightedRandomList
import net.minecraft.world.level.biome.MobSpawnSettings.SpawnerData
import net.minecraft.world.level.levelgen.structure.Structure
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride
import java.util.function.Supplier

object SpawnModifiers {
    fun register(server: MinecraftServer, modifiers: Map<ResourceKey<Structure>, Supplier<SpawnModifier>>) {
        val registry = server.registryAccess().registryOrThrow(Registries.STRUCTURE)
        for ((structureKey, modifier) in modifiers.entries) {
            val structure = registry.getOrThrow(structureKey)
            modify(structure, modifier.get())
        }
    }

    fun modify(structure: Structure, modifier: SpawnModifier) {
        val settings = structure.settings
        val overrides = structure.spawnOverrides().toMutableMap()
        var spawns = overrides[modifier.category]?.spawns?.unwrap()
        if (spawns.isNullOrEmpty()) {
            spawns = mutableListOf<SpawnerData>()
        }
        spawns.addAll(modifier.spawns)

        overrides[modifier.category] =
            StructureSpawnOverride(modifier.boundingBoxType, WeightedRandomList.create(spawns))

        structure.settings = Structure.StructureSettings(
            settings.biomes,
            overrides,
            settings.step,
            settings.terrainAdaptation
        )
    }
}