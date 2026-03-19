package dev.hybridlabs.aquatic

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.entity.HybridAquaticBlockEntityTypes
import dev.hybridlabs.aquatic.block.wood.HybridAquaticPlatformBlocks
import dev.hybridlabs.aquatic.effect.HybridAquaticMobEffects
import dev.hybridlabs.aquatic.entity.ForgeSpawnGroupRegistry
import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import dev.hybridlabs.aquatic.forge.HybridAquaticEventBusEvents
import dev.hybridlabs.aquatic.forge.HybridAquaticForgeBusEvents
import dev.hybridlabs.aquatic.forge.HybridAquaticModBusEvents
import dev.hybridlabs.aquatic.item.HybridAquaticItemGroups
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import dev.hybridlabs.aquatic.item.HybridAquaticPlatformItems
import dev.hybridlabs.aquatic.loot.HybridAquaticGlobalLootModifier
import dev.hybridlabs.aquatic.loot.entry.HybridAquaticLootPoolEntryTypes
import dev.hybridlabs.aquatic.network.HybridAquaticNetworking
import dev.hybridlabs.aquatic.painting.HybridAquaticPaintings
import dev.hybridlabs.aquatic.potions.HybridAquaticPotions
import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import dev.hybridlabs.aquatic.world.gen.feature.DunegrassFeature
import dev.hybridlabs.aquatic.world.gen.feature.HybridAquaticConfiguredFeatures
import dev.hybridlabs.aquatic.world.gen.feature.HybridAquaticFeatures
import dev.hybridlabs.aquatic.world.gen.feature.HybridAquaticPlacedFeatures
import dev.hybridlabs.aquatic.world.gen.structure.StructureSpawnModifier
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration
import net.minecraftforge.fml.common.Mod

@Suppress("UnusedExpression")
@Mod(Constants.FORGE_MOD_ID)
object HybridAquaticForge {

    init {
        CommonClass.init()

        ForgeSpawnGroupRegistry.createHybridAquaticSpawnGroups()
        StructureSpawnModifier.registerHybridAquaticStructureModifiers()
        HybridAquaticGlobalLootModifier.registerGlobalLootModifiers()

        HybridAquaticBlocks
        HybridAquaticPlatformBlocks
        HybridAquaticEntityTypes
        HybridAquaticBlockEntityTypes
        HybridAquaticPaintings

        HybridAquaticBiomeTags

        HybridAquaticMobEffects

        HybridAquaticItems
        HybridAquaticPlatformItems
        HybridAquaticItemGroups

        HybridAquaticPotions

        HybridAquaticFeatures
        HybridAquaticFeatures.register("dunegrass_patch", DunegrassFeature(ProbabilityFeatureConfiguration.CODEC))
        HybridAquaticPlacedFeatures
        HybridAquaticConfiguredFeatures

        HybridAquaticNetworking.registerPackets()
        HybridAquaticLootPoolEntryTypes

        HybridAquaticModBusEvents
        HybridAquaticForgeBusEvents
        HybridAquaticEventBusEvents
    }
}
