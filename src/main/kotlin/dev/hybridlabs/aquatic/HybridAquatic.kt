package dev.hybridlabs.aquatic

import dev.hybridlabs.aquatic.block.BlahajPlushieBlock
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.SeaMessage
import dev.hybridlabs.aquatic.block.entity.HybridAquaticBlockEntityTypes
import dev.hybridlabs.aquatic.enchantment.HybridAquaticEnchantments
import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import dev.hybridlabs.aquatic.entity.SpawnRestrictionRegistry
import dev.hybridlabs.aquatic.item.HybridAquaticItemGroups
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import dev.hybridlabs.aquatic.registry.HybridAquaticRegistryKeys
import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import dev.hybridlabs.aquatic.world.EntityBiomeModifications
import dev.hybridlabs.aquatic.world.gen.feature.FeatureBiomeModifications
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.event.registry.DynamicRegistries
import net.fabricmc.fabric.api.`object`.builder.v1.trade.TradeOfferHelper
import net.minecraft.item.BlockItem
import net.minecraft.registry.Registries
import net.minecraft.village.TradeOffers.SellItemFactory
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object HybridAquatic : ModInitializer {
    const val MOD_ID: String = "hybrid-aquatic"
    private val logger: Logger = LoggerFactory.getLogger(MOD_ID)

    override fun onInitialize() {
        logger.info("Initializing $MOD_ID")

        HybridAquaticBlocks
        HybridAquaticBlockEntityTypes

        HybridAquaticBiomeTags

        HybridAquaticEntityTypes

        HybridAquaticItems
        HybridAquaticItemGroups

        HybridAquaticEnchantments

        FeatureBiomeModifications
        EntityBiomeModifications

        SpawnRestrictionRegistry

        registerDynamicRegistries()
        registerWanderingTraderTrades()
    }

    private fun registerDynamicRegistries() {
        DynamicRegistries.registerSynced(HybridAquaticRegistryKeys.SEA_MESSAGE, SeaMessage.CODEC)
    }

    private fun registerWanderingTraderTrades() {
        // blahaj plushies
        Registries.ITEM
            .filter { it is BlockItem && it.block is BlahajPlushieBlock }
            .forEach { block ->
                TradeOfferHelper.registerWanderingTraderOffers(2) { list ->
                    list.add(SellItemFactory(block, 8, 1, 2, 2))
                }
            }
    }
}
