package dev.hybridlabs.aquatic

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.PlushieBlock
import dev.hybridlabs.aquatic.block.SeaMessage
import dev.hybridlabs.aquatic.block.entity.HybridAquaticBlockEntityTypes
import dev.hybridlabs.aquatic.block.wood.HybridAquaticWoodBlocks
import dev.hybridlabs.aquatic.config.HybridAquaticConfig
import dev.hybridlabs.aquatic.config.HybridAquaticConfigHandler
import dev.hybridlabs.aquatic.effect.HybridAquaticMobEffects
import dev.hybridlabs.aquatic.enchantment.HybridAquaticEnchantments
import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import dev.hybridlabs.aquatic.entity.SpawnRestrictionRegistry
import dev.hybridlabs.aquatic.item.HybridAquaticItemGroups
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import dev.hybridlabs.aquatic.item.HybridAquaticWoodItems
import dev.hybridlabs.aquatic.loot.LootTableModifications
import dev.hybridlabs.aquatic.loot.entry.HybridAquaticLootPoolEntryTypes
import dev.hybridlabs.aquatic.network.HybridAquaticNetworking
import dev.hybridlabs.aquatic.potions.HybridAquaticPotions
import dev.hybridlabs.aquatic.registry.HybridAquaticRegistryKeys
import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import dev.hybridlabs.aquatic.utils.HybridAquaticCustomTrades.registerCustomTrades
import dev.hybridlabs.aquatic.world.gen.feature.FeatureBiomeModifications
import dev.hybridlabs.aquatic.world.gen.feature.HybridAquaticConfiguredFeatures
import dev.hybridlabs.aquatic.world.gen.feature.HybridAquaticFeatures
import dev.hybridlabs.aquatic.world.gen.feature.HybridAquaticPlacedFeatures
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.biome.v1.BiomeModifications
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors
import net.fabricmc.fabric.api.event.registry.DynamicRegistries
import net.fabricmc.fabric.api.`object`.builder.v1.trade.TradeOfferHelper
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.entity.npc.VillagerTrades
import net.minecraft.world.item.BlockItem
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.nio.file.Path
import kotlin.io.path.notExists

object HybridAquatic : ModInitializer {
    const val MOD_ID: String = "hybrid-aquatic"
    const val MOD_NAME: String = "Hybrid Aquatic"

    private val logger: Logger = LoggerFactory.getLogger(MOD_NAME)

    val configFile: Path = FabricLoader.getInstance().configDir.resolve("$MOD_ID.json")
    val configHandler = HybridAquaticConfigHandler(configFile.toFile())

    @Suppress("UnusedExpression")
    override fun onInitialize() {
        logger.info("Initializing $MOD_NAME")
        CommonClass.init()

        HybridAquaticBlocks
        HybridAquaticWoodBlocks
        HybridAquaticEntityTypes
        HybridAquaticBlockEntityTypes

        HybridAquaticBiomeTags

        HybridAquaticMobEffects
        HybridAquaticPotions

        HybridAquaticItems
        HybridAquaticWoodItems
        HybridAquaticItemGroups

        HybridAquaticEnchantments

        HybridAquaticFeatures
        HybridAquaticPlacedFeatures
        HybridAquaticConfiguredFeatures

        HybridAquaticNetworking.registerNetworking()

        HybridAquaticLootPoolEntryTypes
        LootTableModifications.registerLootModifications()

        FeatureBiomeModifications.registerBiomeModifications()

        SpawnRestrictionRegistry.registerSpawnRestrictions()

        initializeConfig()

        registerDynamicRegistries()
        registerWanderingTraderTrades()
        registerCustomTrades()
        registerFlammables(FlammableBlockRegistry.getDefaultInstance())
        registerStrippables()
        registerBiomeModifications(configHandler.config)
    }

    private fun initializeConfig() {
        val configFile: Path = FabricLoader.getInstance().configDir.resolve("$MOD_ID.json")
        val configHandler = HybridAquaticConfigHandler(configFile.toFile())
        if (configFile.notExists()) {
            logger.info("$MOD_NAME config file did not exist, creating one")
            configHandler.save()
        } else {
            logger.info("Loading $MOD_NAME config file")
            configHandler.load()

            // check config data version, if updated then reset
            val defaultConfig = configHandler.defaultConfig
            val config = configHandler.config
            if (config.dataVersion < defaultConfig.dataVersion) {
                logger.info("Old $MOD_NAME config file found, upgrading")

                configHandler.backup()

                configHandler.config = defaultConfig
                configHandler.save()

                logger.info("$MOD_NAME config reset, the old config has been backed up to \"${configHandler.backupFile}\"")
            }
        }
    }

    private fun registerDynamicRegistries() {
        DynamicRegistries.registerSynced(HybridAquaticRegistryKeys.SEA_MESSAGE, SeaMessage.CODEC)
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
        registry.add(HybridAquaticWoodBlocks.DRIFTWOOD_LOG.get(), 5, 5)
        registry.add(HybridAquaticWoodBlocks.STRIPPED_DRIFTWOOD_LOG.get(), 5, 5)
        registry.add(HybridAquaticWoodBlocks.DRIFTWOOD_WOOD.get(), 5, 5)
        registry.add(HybridAquaticWoodBlocks.STRIPPED_DRIFTWOOD_WOOD.get(), 5, 5)
        registry.add(HybridAquaticWoodBlocks.DRIFTWOOD_PLANKS.get(), 5, 20)
        registry.add(HybridAquaticWoodBlocks.DRIFTWOOD_SLAB.get(), 5, 20)
        registry.add(HybridAquaticWoodBlocks.DRIFTWOOD_FENCE.get(), 5, 20)
        registry.add(HybridAquaticWoodBlocks.DRIFTWOOD_FENCE_GATE.get(), 5, 20)

        registry.add(HybridAquaticWoodBlocks.STRIPPED_DRIFTWOOD_WOOD.get(), 5, 5)
    }

    private fun registerStrippables() {
        StrippableBlockRegistry.register(
            HybridAquaticWoodBlocks.DRIFTWOOD_LOG.get(),
            HybridAquaticWoodBlocks.STRIPPED_DRIFTWOOD_LOG.get()
        )
        StrippableBlockRegistry.register(
            HybridAquaticWoodBlocks.DRIFTWOOD_WOOD.get(),
            HybridAquaticWoodBlocks.STRIPPED_DRIFTWOOD_WOOD.get()
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
