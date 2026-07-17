package dev.hybridlabs.aquatic.registry

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.world.gen.structure.SpawnModifier
import net.minecraft.core.Registry
import net.minecraft.resources.ResourceKey

/**
 * Registry keys for Hybrid Aquatic.
 */
object HARegistryKeys {

    val STRUCTURE_SPAWN_MODIFIER: ResourceKey<Registry<SpawnModifier>> =
        ResourceKey.createRegistryKey(CommonClass.locate("structure_spawn_modifier"))
}