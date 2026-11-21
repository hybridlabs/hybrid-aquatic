package dev.hybridlabs.aquatic.datagen

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.Constants
import dev.hybridlabs.aquatic.datagen.server.HABiomeModifierProvider
import dev.hybridlabs.aquatic.datagen.server.HADataMapProvider
import dev.hybridlabs.aquatic.datagen.server.HAGlobalLootModifierProvider
import dev.hybridlabs.aquatic.world.gen.structure.BuiltinSpawnModifiers
import net.minecraft.core.HolderSet
import net.minecraft.core.RegistrySetBuilder
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.util.random.Weighted
import net.minecraft.util.random.WeightedList
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.EventBusSubscriber
import net.neoforged.neoforge.common.data.DatapackBuiltinEntriesProvider
import net.neoforged.neoforge.common.world.StructureModifier
import net.neoforged.neoforge.common.world.StructureModifiers
import net.neoforged.neoforge.data.event.GatherDataEvent
import net.neoforged.neoforge.registries.NeoForgeRegistries.Keys.STRUCTURE_MODIFIERS

/**
 * Datagen for Forge specific data resources like biome modifiers.
 *
 * The rest of the generated resources are imported from the output of the Fabric project's runDatagen task.
 */
@Suppress("Unused", "UnusedExpression")
@EventBusSubscriber(modid = Constants.MOD_ID)
object DataGenerators {

    @SubscribeEvent
    fun gatherData(event: GatherDataEvent.Server) {
        val packOutput = event.generator.packOutput
        val lookupProvider = event.worldLookupProvider

        val builder = RegistrySetBuilder()
        builder.add(STRUCTURE_MODIFIERS) { context ->
            registerStructureSpawnModifiers(context)
        }

        event.addProvider(
            DatapackBuiltinEntriesProvider.forWorldLayer(
                packOutput,
                Constants.MOD_ID,
                lookupProvider,
                builder,
                setOf(Constants.MOD_ID)
            )
        )

        event.addProvider(
            HAGlobalLootModifierProvider(
                packOutput,
                lookupProvider
            )
        )

        event.addProvider(
            HADataMapProvider(
                packOutput,
                lookupProvider
            )
        )

        event.addProvider(
            HABiomeModifierProvider(packOutput)
        )
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
            val reg = context.lookup(Registries.STRUCTURE)
            val structure = reg.getOrThrow(structureModifier.structure)
            structureModifier.spawns.values.flatten().let { spawns ->
                context.register(
                    key,
                    StructureModifiers.AddSpawnsStructureModifier(
                        HolderSet.direct(structure),
                        WeightedList.of(spawns.map { spawn -> Weighted(spawn, 1) })
                    )
                )
            }
        }
    }
}
