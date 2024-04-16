package dev.hybridlabs.aquatic

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.PlushieBlock
import dev.hybridlabs.aquatic.block.SeaMessage
import dev.hybridlabs.aquatic.block.entity.HybridAquaticBlockEntityTypes
import dev.hybridlabs.aquatic.effect.HybridAquaticStatusEffects
import dev.hybridlabs.aquatic.enchantment.HybridAquaticEnchantments
import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import dev.hybridlabs.aquatic.entity.SpawnRestrictionRegistry
import dev.hybridlabs.aquatic.item.HybridAquaticItemGroups
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import dev.hybridlabs.aquatic.loot.LootTableModifications
import dev.hybridlabs.aquatic.loot.entry.HybridAquaticLootPoolEntryTypes
import dev.hybridlabs.aquatic.network.HybridAquaticNetworking
import dev.hybridlabs.aquatic.potions.HybridAquaticPotions
import dev.hybridlabs.aquatic.registry.HybridAquaticRegistryKeys
import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import dev.hybridlabs.aquatic.utils.HybridAquaticCustomTrades.registerCustomTrades
import dev.hybridlabs.aquatic.world.EntityBiomeModifications
import dev.hybridlabs.aquatic.world.gen.feature.FeatureBiomeModifications
import dev.hybridlabs.aquatic.world.gen.feature.HybridAquaticFeatures
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.event.registry.DynamicRegistries
import net.fabricmc.fabric.api.`object`.builder.v1.trade.TradeOfferHelper
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry
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

        StrippableBlockRegistry.register(HybridAquaticBlocks.DRIFTWOOD_LOG, HybridAquaticBlocks.STRIPPED_DRIFTWOOD_LOG)
        StrippableBlockRegistry.register(HybridAquaticBlocks.DRIFTWOOD_WOOD, HybridAquaticBlocks.STRIPPED_DRIFTWOOD_WOOD)

        FlammableBlockRegistry.getDefaultInstance().add(HybridAquaticBlocks.DRIFTWOOD_LOG, 5, 5)
        FlammableBlockRegistry.getDefaultInstance().add(HybridAquaticBlocks.STRIPPED_DRIFTWOOD_LOG, 5, 5)
        FlammableBlockRegistry.getDefaultInstance().add(HybridAquaticBlocks.DRIFTWOOD_WOOD, 5, 5)
        FlammableBlockRegistry.getDefaultInstance().add(HybridAquaticBlocks.STRIPPED_DRIFTWOOD_WOOD, 5, 5)
        FlammableBlockRegistry.getDefaultInstance().add(HybridAquaticBlocks.DRIFTWOOD_PLANKS, 5, 20)
        FlammableBlockRegistry.getDefaultInstance().add(HybridAquaticBlocks.DRIFTWOOD_SLAB, 5, 20)
        FlammableBlockRegistry.getDefaultInstance().add(HybridAquaticBlocks.DRIFTWOOD_FENCE, 5, 20)
        FlammableBlockRegistry.getDefaultInstance().add(HybridAquaticBlocks.DRIFTWOOD_FENCE_GATE, 5, 20)

        HybridAquaticBiomeTags

        HybridAquaticEntityTypes

        HybridAquaticPotions
        HybridAquaticStatusEffects

        HybridAquaticItems
        HybridAquaticItemGroups

        HybridAquaticEnchantments

        HybridAquaticFeatures

        HybridAquaticNetworking

        HybridAquaticLootPoolEntryTypes
        LootTableModifications

        FeatureBiomeModifications
        EntityBiomeModifications

        SpawnRestrictionRegistry

        registerDynamicRegistries()
        registerWanderingTraderTrades()
        registerCustomTrades()
    }

    private fun registerDynamicRegistries() {
        DynamicRegistries.registerSynced(HybridAquaticRegistryKeys.SEA_MESSAGE, SeaMessage.CODEC)
    }

    private fun registerWanderingTraderTrades() {
        // plushies
        Registries.ITEM
            .filter { it is BlockItem && it.block is PlushieBlock }
            .forEach { block ->
                TradeOfferHelper.registerWanderingTraderOffers(2) { list ->
                    list.add(SellItemFactory(block, 8, 1, 2, 2))
                }
            }
    }
}
