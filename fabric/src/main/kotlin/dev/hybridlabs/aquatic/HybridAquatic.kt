package dev.hybridlabs.aquatic

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.PlushieBlock
import dev.hybridlabs.aquatic.block.SeaMessage
import dev.hybridlabs.aquatic.block.entity.HybridAquaticBlockEntityTypes
import dev.hybridlabs.aquatic.block.wood.HybridAquaticPlatformBlocks
import dev.hybridlabs.aquatic.config.ConfigHelper
import dev.hybridlabs.aquatic.config.HybridAquaticConfig
import dev.hybridlabs.aquatic.effect.HybridAquaticMobEffects
import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import dev.hybridlabs.aquatic.entity.SpawnRestrictionRegistry
import dev.hybridlabs.aquatic.fluid.HybridAquaticFluids
import dev.hybridlabs.aquatic.item.HybridAquaticItemGroups
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import dev.hybridlabs.aquatic.item.HybridAquaticPlatformItems
import dev.hybridlabs.aquatic.loot.LootTableModifications
import dev.hybridlabs.aquatic.loot.entry.HybridAquaticLootPoolEntryTypes
import dev.hybridlabs.aquatic.network.HybridAquaticNetworking
import dev.hybridlabs.aquatic.painting.HybridAquaticPaintings
import dev.hybridlabs.aquatic.particle.HybridAquaticParticleTypes
import dev.hybridlabs.aquatic.potions.HybridAquaticPotions
import dev.hybridlabs.aquatic.registry.HybridAquaticRegistryKeys
import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import dev.hybridlabs.aquatic.utils.HybridAquaticCustomTrades.registerCustomTrades
import dev.hybridlabs.aquatic.world.gen.biome.HybridAquaticBiomes
import dev.hybridlabs.aquatic.world.gen.feature.DunegrassFeature
import dev.hybridlabs.aquatic.world.gen.feature.FeatureBiomeModifications
import dev.hybridlabs.aquatic.world.gen.feature.HybridAquaticConfiguredFeatures
import dev.hybridlabs.aquatic.world.gen.feature.HybridAquaticFeatures
import dev.hybridlabs.aquatic.world.gen.feature.HybridAquaticPlacedFeatures
import dev.hybridlabs.aquatic.world.gen.structure.FabricSpawnModifiers
import dev.hybridlabs.aquatic.world.gen.structure.SpawnModifier
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents.SERVER_STARTING
import net.fabricmc.fabric.api.event.registry.DynamicRegistries
import net.fabricmc.fabric.api.`object`.builder.v1.trade.TradeOfferHelper
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.entity.npc.VillagerTrades
import net.minecraft.world.item.BlockItem
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration

object HybridAquatic : ModInitializer {
    val DUNEGRASS_PATCH = HybridAquaticFeatures.register("dunegrass_patch", DunegrassFeature(ProbabilityFeatureConfiguration.CODEC))

    private val logger = Constants.LOG

    @Suppress("UnusedExpression")
    override fun onInitialize() {
        logger.info("Initializing ${Constants.MOD_NAME}")
        CommonClass.init()

        HybridAquaticBlocks
        HybridAquaticFluids
        HybridAquaticPlatformBlocks
        HybridAquaticEntityTypes
        HybridAquaticBlockEntityTypes
        HybridAquaticPaintings

        HybridAquaticBiomes.addBiomes()

        HybridAquaticBiomeTags

        HybridAquaticMobEffects
        HybridAquaticPotions.registerPotionRecipes()

        HybridAquaticItems
        HybridAquaticPlatformItems
        HybridAquaticItemGroups

        HybridAquaticFeatures
        HybridAquaticPlacedFeatures
        HybridAquaticConfiguredFeatures

        HybridAquaticNetworking.registerNetworking()

        HybridAquaticLootPoolEntryTypes
        LootTableModifications.registerLootModifications()

        FeatureBiomeModifications.registerBiomeModifications()

        SpawnRestrictionRegistry.registerSpawnRestrictions()

        //HybridAquaticParticleTypes

        registerDynamicRegistries()
        registerWanderingTraderTrades()
        registerCustomTrades()
        registerFlammables(FlammableBlockRegistry.getDefaultInstance())
        registerStrippables()

        val configHandler = ConfigHelper.initializeConfig(CommonClass.CONFIG_FILE)
        registerBiomeModifications(configHandler.config)

        SERVER_STARTING.register { server ->
            FabricSpawnModifiers.load(server)
        }
    }

    private fun registerDynamicRegistries() {
        DynamicRegistries.registerSynced(HybridAquaticRegistryKeys.SEA_MESSAGE, SeaMessage.CODEC)
        DynamicRegistries.register(HybridAquaticRegistryKeys.STRUCTURE_SPAWN_MODIFIER, SpawnModifier.CODEC)
    }

    private fun registerWanderingTraderTrades() {
        // plushies
        BuiltInRegistries.ITEM
            .filter { it is BlockItem && it.block is PlushieBlock }
            .forEach { block ->
                TradeOfferHelper.registerWanderingTraderOffers(2) { list ->
                    list.add(VillagerTrades.ItemsForEmeralds(block, 8, 1, 2, 2))
                }
            }
    }


    private fun registerFlammables(registry: FlammableBlockRegistry) {
        // same as vanilla grass
        registry.add(HybridAquaticPlatformBlocks.DUNEGRASS.get(), 60, 100)
        registry.add(HybridAquaticPlatformBlocks.TALL_DUNEGRASS.get(), 60, 100)
        registry.add(HybridAquaticPlatformBlocks.CATTAIL.get(), 60, 100)
        // same as vanilla logs
        registry.add(HybridAquaticPlatformBlocks.DRIFTWOOD_LOG.get(), 5, 5)
        registry.add(HybridAquaticPlatformBlocks.STRIPPED_DRIFTWOOD_LOG.get(), 5, 5)
        registry.add(HybridAquaticPlatformBlocks.DRIFTWOOD_WOOD.get(), 5, 5)
        registry.add(HybridAquaticPlatformBlocks.STRIPPED_DRIFTWOOD_WOOD.get(), 5, 5)
        // same as vanilla cut wood
        registry.add(HybridAquaticPlatformBlocks.DRIFTWOOD_PLANKS.get(), 5, 20)
        registry.add(HybridAquaticPlatformBlocks.DRIFTWOOD_SLAB.get(), 5, 20)
        registry.add(HybridAquaticPlatformBlocks.DRIFTWOOD_FENCE.get(), 5, 20)
        registry.add(HybridAquaticPlatformBlocks.DRIFTWOOD_FENCE_GATE.get(), 5, 20)
    }

    private fun registerStrippables() {
        StrippableBlockRegistry.register(
            HybridAquaticPlatformBlocks.DRIFTWOOD_LOG.get(),
            HybridAquaticPlatformBlocks.STRIPPED_DRIFTWOOD_LOG.get()
        )
        StrippableBlockRegistry.register(
            HybridAquaticPlatformBlocks.DRIFTWOOD_WOOD.get(),
            HybridAquaticPlatformBlocks.STRIPPED_DRIFTWOOD_WOOD.get()
        )
    }

    private fun registerBiomeModifications(config: HybridAquaticConfig) {
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
}
