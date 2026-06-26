package dev.hybridlabs.aquatic.data.structure_spawn_modifier

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.registry.HybridAquaticRegistryKeys
import dev.hybridlabs.aquatic.world.gen.structure.BuiltinSpawnModifiers
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider
import net.minecraft.core.HolderLookup
import net.minecraft.resources.ResourceKey
import java.util.concurrent.CompletableFuture

class StructureSpawnModifierProvider(
    output: FabricDataOutput,
    registriesFuture: CompletableFuture<HolderLookup.Provider>
) :
    FabricDynamicRegistryProvider(output, registriesFuture) {
    override fun configure(registries: HolderLookup.Provider, entries: Entries) {
        BuiltinSpawnModifiers.forEach { modifier ->
            val key = ResourceKey.create(
                HybridAquaticRegistryKeys.STRUCTURE_SPAWN_MODIFIER,
                CommonClass.locate(modifier.id)
            )
            entries.add(key, modifier)
        }
    }

    override fun getName(): String {
        return "Structure Spawn Modifiers"
    }
}
