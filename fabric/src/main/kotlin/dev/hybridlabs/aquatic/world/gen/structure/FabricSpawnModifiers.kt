package dev.hybridlabs.aquatic.world.gen.structure

import dev.hybridlabs.aquatic.Constants
import dev.hybridlabs.aquatic.platform.Services
import dev.hybridlabs.aquatic.registry.HARegistryKeys
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.server.MinecraftServer
import net.minecraft.util.random.Weighted
import net.minecraft.util.random.WeightedList
import net.minecraft.world.level.levelgen.structure.Structure
import net.minecraft.world.level.levelgen.structure.StructureSpawnOverride
import java.util.function.Supplier

object FabricSpawnModifiers {
    fun register(server: MinecraftServer, modifiers: Map<ResourceKey<Structure>, Supplier<SpawnModifier>>) {
        val registry = server.registryAccess().lookupOrThrow(Registries.STRUCTURE)
        for ((structureKey, modifier) in modifiers.entries) {
            val structure = registry.getValueOrThrow(structureKey)
            modify(structure, modifier.get())
        }
    }

    fun load(server: MinecraftServer) {
        val modifierRegistry =
            server.registryAccess().lookupOrThrow(HARegistryKeys.STRUCTURE_SPAWN_MODIFIER)
        val structureRegistry = server.registryAccess().lookupOrThrow(Registries.STRUCTURE)
        modifierRegistry.forEach {
            structureRegistry.get(it.structure.identifier()).ifPresent { structure ->
                Constants.LOGGER.info("Loaded structure spawn modifier for: {}", it.structure)
                modify(structure.value(), it)
            }
        }
    }

    fun modify(structure: Structure, modifier: SpawnModifier) {
        val settings = structure.settings
        val overrides = structure.spawnOverrides().toMutableMap()

        modifier.spawns.forEach { (categoryName, spawns) ->
            val category = Services.PLATFORM.getHybridMobCategoryByName(categoryName)
            if (category != null) {
                var oSpawns = overrides[category]?.spawns?.unwrap()
                if (oSpawns.isNullOrEmpty()) {
                    oSpawns = spawns.map { Weighted(it, 1) }.toMutableList()
                    overrides[category] =
                        StructureSpawnOverride(modifier.boundingBoxType, WeightedList.of(oSpawns))
                }
            }
        }
        structure.settings = Structure.StructureSettings(
            settings.biomes,
            overrides,
            settings.step,
            settings.terrainAdaptation
        )
    }
}
