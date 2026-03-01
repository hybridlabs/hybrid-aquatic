package dev.hybridlabs.aquatic

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.PlushieBlock
import dev.hybridlabs.aquatic.block.entity.HybridAquaticBlockEntityTypes
import dev.hybridlabs.aquatic.block.wood.HybridAquaticPlatformBlocks
import dev.hybridlabs.aquatic.client.model.HybridAquaticEntityModelLayers.BASKING_SHARK_PLUSHIE
import dev.hybridlabs.aquatic.client.model.HybridAquaticEntityModelLayers.BULL_SHARK_PLUSHIE
import dev.hybridlabs.aquatic.client.model.HybridAquaticEntityModelLayers.FRILLED_SHARK_PLUSHIE
import dev.hybridlabs.aquatic.client.model.HybridAquaticEntityModelLayers.GREAT_WHITE_SHARK_PLUSHIE
import dev.hybridlabs.aquatic.client.model.HybridAquaticEntityModelLayers.HAMMERHEAD_SHARK_PLUSHIE
import dev.hybridlabs.aquatic.client.model.HybridAquaticEntityModelLayers.THRESHER_SHARK_PLUSHIE
import dev.hybridlabs.aquatic.client.model.HybridAquaticEntityModelLayers.TIGER_SHARK_PLUSHIE
import dev.hybridlabs.aquatic.client.model.HybridAquaticEntityModelLayers.WHALE_SHARK_PLUSHIE
import dev.hybridlabs.aquatic.client.model.block.entity.plushie.*
import dev.hybridlabs.aquatic.client.render.block.HybridAquaticBlockRenderers
import dev.hybridlabs.aquatic.client.render.block.entity.*
import dev.hybridlabs.aquatic.effect.HybridAquaticMobEffects
import dev.hybridlabs.aquatic.entity.ForgeSpawnGroupRegistry
import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import dev.hybridlabs.aquatic.fluid.HybridAquaticFluids
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
import dev.hybridlabs.aquatic.utils.HybridAquaticCustomTrades
import dev.hybridlabs.aquatic.world.gen.feature.DunegrassFeature
import dev.hybridlabs.aquatic.world.gen.feature.HybridAquaticConfiguredFeatures
import dev.hybridlabs.aquatic.world.gen.feature.HybridAquaticFeatures
import dev.hybridlabs.aquatic.world.gen.feature.HybridAquaticPlacedFeatures
import dev.hybridlabs.aquatic.world.gen.structure.StructureSpawnModifier
import net.minecraft.core.BlockPos
import net.minecraft.tags.FluidTags
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration
import net.minecraftforge.client.event.EntityRenderersEvent
import net.minecraftforge.common.MinecraftForge.EVENT_BUS
import net.minecraftforge.event.entity.living.LivingBreatheEvent
import net.minecraftforge.fml.common.Mod
import thedarkcolour.kotlinforforge.forge.FORGE_BUS


@Suppress("UnusedExpression")
@Mod(Constants.FORGE_MOD_ID)
object HybridAquaticForge {
    private val logger = Constants.LOG!!

    init {
        CommonClass.init()

        ForgeSpawnGroupRegistry.createHybridAquaticSpawnGroups()
        StructureSpawnModifier.registerHybridAquaticStructureModifiers()
        HybridAquaticGlobalLootModifier.registerGlobalLootModifiers()

        HybridAquaticBlocks
        HybridAquaticFluids
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

        EVENT_BUS.addListener(::makeBlocksBreatheable)

    }

    private fun makeBlocksBreatheable(event: LivingBreatheEvent) {
        val entity = event.entity
        val world = entity.level()

        if(entity.isEyeInFluid(FluidTags.WATER) &&
            world.getBlockState(BlockPos.containing(entity.x, entity.eyeY, entity.z))
                .`is`(HybridAquaticBlocks.DECORATIVE_BUBBLE_COLUMN.get())) {
            event.setCanBreathe(true)
            event.setCanRefillAir(true)
        }
    }
}
