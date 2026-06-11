package dev.hybridlabs.aquatic.datagen.server

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.world.gen.structure.BuiltinSpawnModifiers
import dev.hybridlabs.aquatic.world.gen.structure.StructureSpawnModifier
import net.minecraft.data.worldgen.BootstapContext
import net.minecraft.resources.ResourceKey
import net.minecraftforge.common.world.StructureModifier
import net.minecraftforge.registries.ForgeRegistries.Keys.STRUCTURE_MODIFIERS

class StructureModifierProvider(context: BootstapContext<StructureModifier>) {
    init {
        registerStructureSpawnModifiers(context)
    }

    /**
     * Create Forge structure spawn modifiers.
     */
    private fun registerStructureSpawnModifiers(context: BootstapContext<StructureModifier>) {
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