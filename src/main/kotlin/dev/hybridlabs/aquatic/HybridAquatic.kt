package dev.hybridlabs.aquatic

import dev.hybridlabs.aquatic.block.BlahajPlushieBlock
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.entity.HybridAquaticBlockEntityTypes
import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import dev.hybridlabs.aquatic.item.HybridAquaticItemGroups
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import dev.hybridlabs.aquatic.resource.data.SeaMessageLoader
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.`object`.builder.v1.trade.TradeOfferHelper
import net.fabricmc.fabric.api.resource.ResourceManagerHelper
import net.minecraft.item.BlockItem
import net.minecraft.registry.Registries
import net.minecraft.resource.ResourceType
import net.minecraft.village.TradeOffers.SellItemFactory
import org.slf4j.LoggerFactory

object HybridAquatic : ModInitializer {
    const val MOD_ID: String = "hybrid-aquatic"
    private val LOGGER = LoggerFactory.getLogger(MOD_ID)

    override fun onInitialize() {
        LOGGER.info("Initializing $MOD_ID")

        HybridAquaticBlocks
        HybridAquaticBlockEntityTypes

        HybridAquaticEntityTypes

        HybridAquaticItems
        HybridAquaticItemGroups

        registerWanderingTraderTrades()
        registerResourceManagers(ResourceManagerHelper.get(ResourceType.SERVER_DATA))
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

    private fun registerResourceManagers(dataResourceManager: ResourceManagerHelper) {
        dataResourceManager.registerReloadListener(SeaMessageLoader())
    }
}
