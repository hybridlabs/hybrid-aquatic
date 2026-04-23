package dev.hybridlabs.aquatic.datagen

import dev.hybridlabs.aquatic.Constants
import dev.hybridlabs.aquatic.datagen.server.BiomeModifierProvider
import dev.hybridlabs.aquatic.datagen.server.HAGlobalLootModifierProvider
import dev.hybridlabs.aquatic.datagen.server.StructureModifierProvider
import dev.hybridlabs.aquatic.utils.NaughtyRegistrySetBuilder
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider
import net.minecraftforge.data.event.GatherDataEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod.EventBusSubscriber
import net.minecraftforge.registries.ForgeRegistries.Keys.BIOME_MODIFIERS
import net.minecraftforge.registries.ForgeRegistries.Keys.STRUCTURE_MODIFIERS

/**
 * Datagen for Forge specific data resources like biome modifiers.
 *
 * The rest of the generated resources are imported from the output of the Fabric project's runDatagen task.
 */
@Suppress("Unused")
@EventBusSubscriber(modid = Constants.FORGE_MOD_ID, bus = EventBusSubscriber.Bus.MOD)
object DataGenerators {

    @SubscribeEvent
    fun gatherData(event: GatherDataEvent) {
        val generator = event.generator
        val packOutput = generator.packOutput

        val builder = NaughtyRegistrySetBuilder()
        val lookupProvider = event.lookupProvider

        builder.add(BIOME_MODIFIERS, ::BiomeModifierProvider)
        builder.add(STRUCTURE_MODIFIERS, ::StructureModifierProvider)

        generator.addProvider(event.includeServer(), DatapackBuiltinEntriesProvider(packOutput, lookupProvider, builder, setOf(Constants.MOD_ID)))
        generator.addProvider(event.includeServer(), HAGlobalLootModifierProvider(packOutput))
        // generator.addProvider(event.includeClient(), HASoundDefinitionsProvider(packOutput, event.existingFileHelper))
    }
}
