package dev.hybridlabs.aquatic.datagen

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.Constants
import dev.hybridlabs.aquatic.config.ConfigHelper.initializeConfig
import dev.hybridlabs.aquatic.utils.NaughtyRegistrySetBuilder
import dev.hybridlabs.aquatic.world.gen.feature.BiomeFeatureAddition
import dev.hybridlabs.aquatic.world.gen.feature.HybridAquaticPlacedFeatures
import dev.hybridlabs.aquatic.world.gen.structure.BuiltinSpawnModifiers
import dev.hybridlabs.aquatic.world.gen.structure.StructureSpawnModifier
import net.minecraft.core.HolderSet
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.biome.MobSpawnSettings
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider
import net.minecraftforge.common.world.BiomeModifier
import net.minecraftforge.common.world.ForgeBiomeModifiers
import net.minecraftforge.common.world.StructureModifier
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
@Suppress("Unused", "UnusedExpression")
@EventBusSubscriber(modid = Constants.MOD_ID, bus = EventBusSubscriber.Bus.MOD)
object DataGenerators {

    @SubscribeEvent
    fun gatherData(event: GatherDataEvent) {

        HybridAquaticPlacedFeatures

        val generator = event.generator
        val packOutput = generator.packOutput

        val builder = NaughtyRegistrySetBuilder()

        val lookupProvider = event.lookupProvider

        builder.add(BIOME_MODIFIERS)
        { context ->
            registerBiomeSpawns(context)
            registerFeatures(context)
        }

        builder.add(STRUCTURE_MODIFIERS)
        { context ->
            registerStructureSpawnModifiers(context)
        }

        generator.addProvider(
            event.includeServer(), DatapackBuiltinEntriesProvider(
                packOutput,
                lookupProvider,
                builder,
                setOf(Constants.MOD_ID)
            )
        )
        generator.addProvider(
            event.includeServer(), HAGlobalLootModifierProvider(packOutput)
        )
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

    /**
     * Create Forge biome modifiers to add placed features.
     */
    private fun registerFeatures(
        context: BootstapContext<BiomeModifier>,
    ) {
        val biomeRegistry = context.lookup(Registries.BIOME)
        val featureRegistry = context.lookup(Registries.PLACED_FEATURE)
        for (addition in BiomeFeatureAddition.builtIn) {

            val location = "${addition.placedFeature.location().path}_${addition.biomeTag.location.path}"
            val key = ResourceKey.create(
                BIOME_MODIFIERS,
                CommonClass.locate(location)
            )
            context.register(
                key, ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                    biomeRegistry.getOrThrow(addition.biomeTag),
                    HolderSet.direct(featureRegistry.getOrThrow(addition.placedFeature)),
                    addition.step
                )
            )
        }
    }

    /**
     * Create Forge biome modifiers to add mob spawns based on the config.
     */
    private fun registerBiomeSpawns(
        context: BootstapContext<BiomeModifier>,
    ) {
        val configHandler = initializeConfig(CommonClass.CONFIG_FILE)
        val biomeRegistry = context.lookup(Registries.BIOME)
        for (spawnConfig in configHandler.defaultConfig.entitySpawnConfig) {
            val location = "${spawnConfig.type.toShortString()}_${spawnConfig.biomes.location.path}"
            val key = ResourceKey.create(
                BIOME_MODIFIERS, CommonClass.locate(location)
            )

            context.register(
                key, ForgeBiomeModifiers.AddSpawnsBiomeModifier(
                    biomeRegistry.get(spawnConfig.biomes).get(),
                    listOf(
                        MobSpawnSettings.SpawnerData(
                            spawnConfig.type,
                            spawnConfig.weight,
                            spawnConfig.minGroupSize,
                            spawnConfig.maxGroupSize
                        )
                    )
                )
            )

        }
    }
}
