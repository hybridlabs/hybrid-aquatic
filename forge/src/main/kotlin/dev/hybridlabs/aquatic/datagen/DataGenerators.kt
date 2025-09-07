package dev.hybridlabs.aquatic.datagen

import dev.hybridlabs.aquatic.Constants
import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import dev.hybridlabs.aquatic.world.gen.feature.HybridAquaticPlacedFeatures
import net.minecraft.core.HolderGetter
import net.minecraft.core.HolderSet
import net.minecraft.core.RegistrySetBuilder
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.biome.Biome
import net.minecraft.world.level.levelgen.GenerationStep
import net.minecraft.world.level.levelgen.placement.PlacedFeature
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider
import net.minecraftforge.common.world.ForgeBiomeModifiers
import net.minecraftforge.data.event.GatherDataEvent
import net.minecraftforge.eventbus.api.SubscribeEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.registries.ForgeRegistries


@Mod.EventBusSubscriber(modid = Constants.FORGE_MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
object DataGenerators {

    @SubscribeEvent
    fun gatherData(event: GatherDataEvent) {
        val generator = event.generator
        val packOutput = generator.packOutput
        val lookupProvider = event.lookupProvider

        /*
        val configFile = Constants.CONFIG_FILE
        val configHandler = HybridBirdsConfigHandler(configFile.toFile())
        initializeConfig(configFile, configHandler)
        */

        val builder: RegistrySetBuilder = RegistrySetBuilder().add(
            ForgeRegistries.Keys.BIOME_MODIFIERS
        ) { context ->
            val key =
                ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation(Constants.MOD_ID, "test"))
            val biomes: HolderGetter<Biome> = context.lookup(Registries.BIOME)
            val placedFeatures: HolderGetter<PlacedFeature> = context.lookup(Registries.PLACED_FEATURE)


            val feature = placedFeatures.getOrThrow(HybridAquaticPlacedFeatures.WATER_LETTUCE)
            val biome = biomes.getOrThrow(HybridAquaticBiomeTags.SWAMP)
            context.register(
                key, ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                    biome,
                    HolderSet.direct(feature),
                    GenerationStep.Decoration.VEGETAL_DECORATION
                )
            )
        }
        /*
        configHandler.config.entitySpawnConfig.forEach {
            val key = ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS, EntityType.getKey(it.type))
            context.register(
                key, ForgeBiomeModifiers.AddSpawnsBiomeModifier(
                    biomeRegistry.get(it.biomes).get(),
                    listOf(
                        MobSpawnSettings.SpawnerData(it.type, it.weight, it.minGroupSize, it.maxGroupSize)
                    )

                )
            )
        }
    }
     */

        generator.addProvider(
            event.includeServer(), DatapackBuiltinEntriesProvider(
                packOutput,
                lookupProvider,
                builder,
                setOf(Constants.MOD_ID)
            )
        )
    }
}
