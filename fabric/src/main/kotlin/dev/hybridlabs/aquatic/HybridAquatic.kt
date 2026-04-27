package dev.hybridlabs.aquatic

import dev.hybridlabs.aquatic.block.HABlocks
import dev.hybridlabs.aquatic.block.HAPlatformBlocks
import dev.hybridlabs.aquatic.block.PlushieBlock
import dev.hybridlabs.aquatic.block.SeaMessage
import dev.hybridlabs.aquatic.block.entity.HABlockEntityTypes
import dev.hybridlabs.aquatic.config.ConfigHelper
import dev.hybridlabs.aquatic.config.HAConfig
import dev.hybridlabs.aquatic.effect.HAMobEffects
import dev.hybridlabs.aquatic.entity.HAEntityTypes
import dev.hybridlabs.aquatic.entity.SpawnRestrictionRegistry
import dev.hybridlabs.aquatic.item.HAItemGroups
import dev.hybridlabs.aquatic.item.HAItems
import dev.hybridlabs.aquatic.item.HAPlatformItems
import dev.hybridlabs.aquatic.item.instrument.HAInstruments
import dev.hybridlabs.aquatic.loot.LootTableModifications
import dev.hybridlabs.aquatic.loot.entry.HybridAquaticLootPoolEntryTypes
import dev.hybridlabs.aquatic.network.HybridAquaticNetworking
import dev.hybridlabs.aquatic.painting.HAPaintings
import dev.hybridlabs.aquatic.particle.HAFabricParticleTypes
import dev.hybridlabs.aquatic.potions.HAPotions
import dev.hybridlabs.aquatic.registry.HARegistryKeys
import dev.hybridlabs.aquatic.sound.HASoundEvents
import dev.hybridlabs.aquatic.tag.HABiomeTags
import dev.hybridlabs.aquatic.utils.HACustomTrades.registerCustomTrades
import dev.hybridlabs.aquatic.world.gen.biome.HABiomes
import dev.hybridlabs.aquatic.world.gen.feature.*
import dev.hybridlabs.aquatic.world.gen.structure.FabricSpawnModifiers
import dev.hybridlabs.aquatic.world.gen.structure.SpawnModifier
import dev.hybridlabs.aquatic.world.inventory.HAMenuTypes
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
    val DUNEGRASS_PATCH = HAFeatures.register("dunegrass_patch", DunegrassFeature(ProbabilityFeatureConfiguration.CODEC))

    private val logger = Constants.LOG

    @Suppress("UnusedExpression")
    override fun onInitialize() {
        val configHandler = ConfigHelper.initializeConfig(CommonClass.CONFIG_FILE)
        logger.info("Initializing ${Constants.MOD_NAME}")
        CommonClass.init()

        HABlocks
        HAPlatformBlocks
        HASoundEvents
        HAInstruments
        HAEntityTypes
        HABlockEntityTypes
        HAPaintings
        HAFabricParticleTypes

        if (configHandler.config.biomeConfig.enableBiomes) {
            HABiomes.addBiomes()
        }

        HABiomeTags

        HAMobEffects
        HAPotions.registerPotionRecipes()

        HAItems
        HAPlatformItems
        HAItemGroups

        HAFeatures
        HAPlacedFeatures
        HAConfiguredFeatures

        HAMenuTypes

        HybridAquaticNetworking.registerNetworking()

        HybridAquaticLootPoolEntryTypes
        LootTableModifications.registerLootModifications()

        FeatureBiomeModifications.registerBiomeModifications()

        SpawnRestrictionRegistry.registerSpawnRestrictions()

        //HybridAquaticParticleTypes

        registerDynamicRegistries()
        if (configHandler.config.enableWanderingTraderTrades) {
            registerWanderingTraderTrades()
        }
        if (configHandler.config.enableWanderingTraderTrades) {
            registerCustomTrades()
        }

        registerFlammables(FlammableBlockRegistry.getDefaultInstance())
        registerStrippables()

        registerBiomeModifications(configHandler.config)

        SERVER_STARTING.register { server ->
            FabricSpawnModifiers.load(server)
        }
    }

    private fun registerDynamicRegistries() {
        DynamicRegistries.registerSynced(HARegistryKeys.SEA_MESSAGE, SeaMessage.CODEC)
        DynamicRegistries.register(HARegistryKeys.STRUCTURE_SPAWN_MODIFIER, SpawnModifier.CODEC)
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
        registry.add(HAPlatformBlocks.DUNEGRASS.get(), 60, 100)
        registry.add(HAPlatformBlocks.TALL_DUNEGRASS.get(), 60, 100)
        registry.add(HAPlatformBlocks.CATTAIL.get(), 60, 100)
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
}
