package dev.hybridlabs.aquatic.datagen.server

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.world.gen.structure.BuiltinSpawnModifiers
import dev.hybridlabs.aquatic.world.gen.structure.StructureSpawnModifier
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.ResourceKey
import net.neoforged.neoforge.common.world.StructureModifier
import net.neoforged.neoforge.registries.NeoForgeRegistries.Keys.STRUCTURE_MODIFIERS

class StructureModifierProvider(context: BootstrapContext<StructureModifier>) {
    init {
        registerStructureSpawnModifiers(context)
    }

    /**
     * Create Forge structure spawn modifiers.
     */
    private fun registerStructureSpawnModifiers(context: BootstrapContext<StructureModifier>) {
        for (structureModifier in BuiltinSpawnModifiers) {
            val key = ResourceKey.create(
                STRUCTURE_MODIFIERS,
                CommonClass.locate(structureModifier.id)
            )
            context.register(
                key,
                StructureSpawnModifier(structureModifier)
            )
        }
    }
}