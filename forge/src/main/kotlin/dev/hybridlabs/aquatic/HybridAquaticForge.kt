package dev.hybridlabs.aquatic

import dev.hybridlabs.aquatic.block.HABlocks
import dev.hybridlabs.aquatic.block.entity.HABlockEntityTypes
import dev.hybridlabs.aquatic.block.wood.HAPlatformBlocks
import dev.hybridlabs.aquatic.effect.HAMobEffects
import dev.hybridlabs.aquatic.entity.ForgeSpawnGroupRegistry
import dev.hybridlabs.aquatic.entity.HAEntityTypes
import dev.hybridlabs.aquatic.forge.HybridAquaticEventBusEvents
import dev.hybridlabs.aquatic.forge.HybridAquaticForgeBusEvents
import dev.hybridlabs.aquatic.forge.HybridAquaticModBusEvents
import dev.hybridlabs.aquatic.item.HAItemGroups
import dev.hybridlabs.aquatic.item.HAItems
import dev.hybridlabs.aquatic.item.HAPlatformItems
import dev.hybridlabs.aquatic.loot.HAGlobalLootModifier
import dev.hybridlabs.aquatic.loot.entry.HybridAquaticLootPoolEntryTypes
import dev.hybridlabs.aquatic.network.HANetworking
import dev.hybridlabs.aquatic.painting.HAPaintings
import dev.hybridlabs.aquatic.potions.HAPotions
import dev.hybridlabs.aquatic.sound.HASoundEvents
import dev.hybridlabs.aquatic.tag.HABiomeTags
import dev.hybridlabs.aquatic.tag.HybridAquaticInstrumentTags
import dev.hybridlabs.aquatic.world.gen.feature.DunegrassFeature
import dev.hybridlabs.aquatic.world.gen.feature.HAConfiguredFeatures
import dev.hybridlabs.aquatic.world.gen.feature.HAFeatures
import dev.hybridlabs.aquatic.world.gen.feature.HAPlacedFeatures
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
        HAGlobalLootModifier.registerGlobalLootModifiers()

        HABlocks
        HAPlatformBlocks
        HASoundEvents
        HAEntityTypes
        HABlockEntityTypes
        HAPaintings

        HABiomeTags
        HybridAquaticInstrumentTags

        HAMobEffects
        HAPotions

        HAItems
        HAPlatformItems
        HAItemGroups

        HAFeatures
        HAFeatures.register("dunegrass_patch", DunegrassFeature(ProbabilityFeatureConfiguration.CODEC))
        HAPlacedFeatures
        HAConfiguredFeatures

        HANetworking.registerPackets()
        HybridAquaticLootPoolEntryTypes

        HybridAquaticModBusEvents
        HybridAquaticForgeBusEvents
        HybridAquaticEventBusEvents
    }
}
