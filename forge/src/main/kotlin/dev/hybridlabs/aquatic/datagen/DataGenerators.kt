package datagen

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.Constants
import dev.hybridlabs.aquatic.config.HybridAquaticConfigHandler
import dev.hybridlabs.aquatic.initializeConfig
import net.minecraft.core.RegistrySetBuilder
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.level.biome.MobSpawnSettings
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

        val configFile = CommonClass.CONFIG_FILE
        val configHandler = HybridAquaticConfigHandler(configFile.toFile())
        initializeConfig(configFile, configHandler)

        val builder: RegistrySetBuilder = RegistrySetBuilder().add(
            ForgeRegistries.Keys.BIOME_MODIFIERS
        ) { context ->
            val biomeRegistry = context.lookup(Registries.BIOME)
            configHandler.config.entitySpawnConfig.forEach {
                val location = "${it.type.toShortString()}_${it.biomes.location.path}"
                val key = ResourceKey.create(
                    ForgeRegistries.Keys.BIOME_MODIFIERS,
                    ResourceLocation(Constants.MOD_ID, location)
                )
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
