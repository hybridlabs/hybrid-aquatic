package dev.hybridlabs.aquatic

import dev.hybridlabs.aquatic.block.HABlocks
import dev.hybridlabs.aquatic.block.HAPlatformBlocks
import dev.hybridlabs.aquatic.block.entity.HABlockEntityTypes
import dev.hybridlabs.aquatic.block.property.FlammableProperty
import dev.hybridlabs.aquatic.block.property.StrippableProperty
import dev.hybridlabs.aquatic.config.ConfigHelper
import dev.hybridlabs.aquatic.config.HAConfig
import dev.hybridlabs.aquatic.effect.HAMobEffects
import dev.hybridlabs.aquatic.entity.HAEntityTypes
import dev.hybridlabs.aquatic.entity.SpawnRestrictionRegistry
import dev.hybridlabs.aquatic.fluid.HAPlatformFluids
import dev.hybridlabs.aquatic.item.HAItemGroups
import dev.hybridlabs.aquatic.item.HAItems
import dev.hybridlabs.aquatic.item.HAPlatformItems
import dev.hybridlabs.aquatic.loot.LootTableModifications
import dev.hybridlabs.aquatic.loot.entry.HybridAquaticLootPoolEntryTypes
import dev.hybridlabs.aquatic.network.HybridAquaticFabricNetworking
import dev.hybridlabs.aquatic.painting.HAPaintings
import dev.hybridlabs.aquatic.particle.HAParticleTypes
import dev.hybridlabs.aquatic.potions.HAPotions
import dev.hybridlabs.aquatic.registry.HARegistryKeys
import dev.hybridlabs.aquatic.sound.HASoundEvents
import dev.hybridlabs.aquatic.tag.*
import dev.hybridlabs.aquatic.world.gen.biome.HABiomeInjectors
import dev.hybridlabs.aquatic.world.gen.biome.HABiomes
import dev.hybridlabs.aquatic.world.gen.densityfunction.DensityFunctionWrappers
import dev.hybridlabs.aquatic.world.gen.densityfunction.TrenchCarver
import dev.hybridlabs.aquatic.world.gen.feature.*
import dev.hybridlabs.aquatic.world.gen.structure.FabricSpawnModifiers
import dev.hybridlabs.aquatic.world.gen.structure.SpawnModifier
import dev.hybridlabs.aquatic.world.inventory.HAMenuTypes
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents.SERVER_STARTING
import net.fabricmc.fabric.api.event.registry.DynamicRegistries
import net.fabricmc.fabric.api.registry.FabricPotionBrewingBuilder
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration

object HybridAquatic : ModInitializer {
    val DUNEGRASS_PATCH = HAFeatures.register("dunegrass_patch", DunegrassFeature(ProbabilityFeatureConfiguration.CODEC))
    val CATTAIL_PATCH = HAFeatures.register("cattail_patch", CattailFeature(ProbabilityFeatureConfiguration.CODEC))
    val PAPYRUS_PATCH = HAFeatures.register("papyrus_patch", PapyrusFeature(ProbabilityFeatureConfiguration.CODEC))

    private val logger = Constants.LOGGER

    @Suppress("UnusedExpression")
    override fun onInitialize() {
        val configHandler = ConfigHelper.initializeConfig(CommonClass.CONFIG_FILE)
        logger.info("Initializing ${Constants.MOD_NAME}")
        CommonClass.init()

        HABlocks
        HAPlatformFluids
        HAPlatformBlocks
        HASoundEvents
        HAEntityTypes
        HABlockEntityTypes
        HAPaintings
        HAParticleTypes

        HABlockTags
        HAFluidTags
        HAInstrumentTags
        HAItemTags
        HAPaintingTags

        HAMobEffects
        HAPotions
        registerBrewingRecipes()

        HAItems
        HAPlatformItems
        HAItemGroups

        HAFeatures
        DensityFunctionWrappers
        TrenchCarver
        HABiomeInjectors
        HAPlacedFeatures
        HAConfiguredFeatures

        HAMenuTypes

        HybridAquaticFabricNetworking.registerNetworking()

        HybridAquaticLootPoolEntryTypes
        LootTableModifications.registerLootModifications()

        FeatureBiomeModifications.registerBiomeModifications()

        SpawnRestrictionRegistry.registerSpawnRestrictions()


        registerDynamicRegistries()
        FlammableProperty
        StrippableProperty

        registerBiomeModifications(configHandler.config)

        SERVER_STARTING.register { server ->
            FabricSpawnModifiers.load(server)
        }
    }

    private fun registerDynamicRegistries() {
        DynamicRegistries.register(HARegistryKeys.STRUCTURE_SPAWN_MODIFIER, SpawnModifier.CODEC)
    }


    private fun registerFlammables(registry: FlammableBlockRegistry) {
        // same as vanilla grass
        registry.add(HAPlatformBlocks.DUNEGRASS.get(), 60, 100)
        registry.add(HAPlatformBlocks.TALL_DUNEGRASS.get(), 60, 100)
        registry.add(HAPlatformBlocks.CATTAIL.get(), 60, 100)
        registry.add(HAPlatformBlocks.PAPYRUS.get(), 60, 100)
        // same as vanilla logs
        registry.add(HAPlatformBlocks.DRIFTWOOD_LOG.get(), 5, 5)
        registry.add(HAPlatformBlocks.STRIPPED_DRIFTWOOD_LOG.get(), 5, 5)
        registry.add(HAPlatformBlocks.DRIFTWOOD_WOOD.get(), 5, 5)
        registry.add(HAPlatformBlocks.STRIPPED_DRIFTWOOD_WOOD.get(), 5, 5)
        // same as vanilla cut wood
        registry.add(HAPlatformBlocks.DRIFTWOOD_PLANKS.get(), 5, 20)
        registry.add(HAPlatformBlocks.DRIFTWOOD_SLAB.get(), 5, 20)
        registry.add(HAPlatformBlocks.DRIFTWOOD_FENCE.get(), 5, 20)
        registry.add(HAPlatformBlocks.DRIFTWOOD_FENCE_GATE.get(), 5, 20)
    }

    private fun registerStrippables() {
        StrippableBlockRegistry.register(
            HAPlatformBlocks.DRIFTWOOD_LOG.get(),
            HAPlatformBlocks.STRIPPED_DRIFTWOOD_LOG.get()
        )
        StrippableBlockRegistry.register(
            HAPlatformBlocks.DRIFTWOOD_WOOD.get(),
            HAPlatformBlocks.STRIPPED_DRIFTWOOD_WOOD.get()
        )
    }

    private fun registerBiomeModifications(config: HAConfig) {
        config.entitySpawnConfig.forEach { config ->
            BiomeModifications.addSpawn(
                BiomeSelectors.tag(config.biomes),
                config.group,
                config.type,
                config.weight,
                config.minGroupSize,
                config.maxGroupSize
            )
        }
    }

    private fun registerBrewingRecipes() {
        FabricPotionBrewingBuilder.BUILD.register { builder ->
            for (recipe in HAPotions.recipes.get()) {
                builder.registerPotionRecipe(
                    recipe.inputPotion,
                    Ingredient.of(recipe.addition),
                    recipe.outputPotion
                )
            }
        }
    }
}
