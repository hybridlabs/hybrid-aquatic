package dev.hybridlabs.aquatic

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.PlushieBlock
import dev.hybridlabs.aquatic.block.SeaMessage
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
import dev.hybridlabs.aquatic.client.render.entity.HybridAquaticEntityRenderers
import dev.hybridlabs.aquatic.effect.HybridAquaticMobEffects
import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import dev.hybridlabs.aquatic.entity.SpawnRestrictionRegistry
import dev.hybridlabs.aquatic.item.HybridAquaticItemGroups
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import dev.hybridlabs.aquatic.item.HybridAquaticPlatformItems
import dev.hybridlabs.aquatic.loot.LootTableModifications
import dev.hybridlabs.aquatic.loot.entry.HybridAquaticLootPoolEntryTypes
import dev.hybridlabs.aquatic.network.HybridAquaticNetworkingForge
import dev.hybridlabs.aquatic.painting.HybridAquaticPaintings
import dev.hybridlabs.aquatic.potions.HybridAquaticPotions
import dev.hybridlabs.aquatic.registry.HybridAquaticRegistryKeys
import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import dev.hybridlabs.aquatic.utils.HybridAquaticCustomTrades
import dev.hybridlabs.aquatic.world.gen.feature.DunegrassFeature
import dev.hybridlabs.aquatic.world.gen.feature.HybridAquaticConfiguredFeatures
import dev.hybridlabs.aquatic.world.gen.feature.HybridAquaticFeatures
import dev.hybridlabs.aquatic.world.gen.feature.HybridAquaticPlacedFeatures
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration
import net.neoforged.bus.api.SubscribeEvent
import net.neoforged.fml.common.Mod
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent
import net.neoforged.fml.event.lifecycle.FMLDedicatedServerSetupEvent
import net.neoforged.neoforge.client.event.EntityRenderersEvent
import net.neoforged.neoforge.event.brewing.RegisterBrewingRecipesEvent
import net.neoforged.neoforge.event.entity.RegisterSpawnPlacementsEvent
import net.neoforged.neoforge.registries.DataPackRegistryEvent
import thedarkcolour.kotlinforforge.neoforge.forge.FORGE_BUS
import thedarkcolour.kotlinforforge.neoforge.forge.MOD_BUS
import thedarkcolour.kotlinforforge.neoforge.forge.runForDist


@Suppress("UnusedExpression")
@Mod(Constants.FORGE_MOD_ID)
object HybridAquaticForge {
    private val logger = Constants.LOG!!

    init {
        CommonClass.init()


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


        HybridAquaticFeatures
        HybridAquaticFeatures.register("dunegrass_patch", DunegrassFeature(ProbabilityFeatureConfiguration.CODEC))
        HybridAquaticPlacedFeatures
        HybridAquaticConfiguredFeatures

        MOD_BUS.addListener(HybridAquaticNetworkingForge::register)
        HybridAquaticLootPoolEntryTypes
        LootTableModifications

        MOD_BUS.addListener(::loadSeaMessages)
        FORGE_BUS.addListener(HybridAquaticCustomTrades::registerWandererTrades)
        FORGE_BUS.addListener(HybridAquaticCustomTrades::registerCustomTrades)
        FORGE_BUS.addListener(::registerBrewingRecipes)
        HybridAquaticPotions
        runForDist(
            clientTarget = {
                MOD_BUS.addListener(::onClientSetup)
                MOD_BUS.addListener(::registerModelLayers)
                MOD_BUS.addListener(::registerSkullModels)
                MOD_BUS.addListener(::registerBlockEntityRenderers)
                MOD_BUS.addListener(::registerSpawnPlacements)
                HybridAquaticEntityRenderers
            },
            serverTarget = {
                MOD_BUS.addListener(::onServerSetup)
            })

    }

    fun loadSeaMessages(event: DataPackRegistryEvent.NewRegistry) {
        event.dataPackRegistry(
            HybridAquaticRegistryKeys.SEA_MESSAGE,
            SeaMessage.CODEC,
            SeaMessage.CODEC,
        )
    }

    @Suppress("UNUSED_PARAMETER")
    private fun registerSpawnPlacements(event: RegisterSpawnPlacementsEvent) {
        SpawnRestrictionRegistry.registerSpawnRestrictions()
    }


    private fun registerModelLayers(event: EntityRenderersEvent.RegisterLayerDefinitions) {
        event.registerLayerDefinition(BASKING_SHARK_PLUSHIE, BaskingSharkPlushieModel::createModelData)
        event.registerLayerDefinition(BULL_SHARK_PLUSHIE, BullSharkPlushieModel::createModelData)
        event.registerLayerDefinition(FRILLED_SHARK_PLUSHIE, FrilledSharkPlushieModel::createModelData)
        event.registerLayerDefinition(GREAT_WHITE_SHARK_PLUSHIE, GreatWhiteSharkPlushieModel::createModelData)
        event.registerLayerDefinition(HAMMERHEAD_SHARK_PLUSHIE, HammerheadSharkPlushieModel::createModelData)
        event.registerLayerDefinition(THRESHER_SHARK_PLUSHIE, ThresherSharkPlushieModel::createModelData)
        event.registerLayerDefinition(TIGER_SHARK_PLUSHIE, TigerSharkPlushieModel::createModelData)
        event.registerLayerDefinition(WHALE_SHARK_PLUSHIE, WhaleSharkPlushieModel::createModelData)
    }

    private fun registerBlockEntityRenderers(event: EntityRenderersEvent.RegisterRenderers) {
        event.registerBlockEntityRenderer(HybridAquaticBlockEntityTypes.ANEMONE.get(), ::AnemoneBlockEntityRenderer)
        event.registerBlockEntityRenderer(
            HybridAquaticBlockEntityTypes.STRAWBERRY_ANEMONE.get(),
            ::StrawberryAnemoneBlockEntityRenderer
        )
        event.registerBlockEntityRenderer(
            HybridAquaticBlockEntityTypes.GIANT_GREEN_ANEMONE.get(),
            ::GiantGreenAnemoneBlockEntityRenderer
        )
        event.registerBlockEntityRenderer(
            HybridAquaticBlockEntityTypes.MESSAGE_IN_A_BOTTLE.get(),
            ::MessageInABottleBlockEntityRenderer
        )
        event.registerBlockEntityRenderer(HybridAquaticBlockEntityTypes.BUOY.get(), ::BuoyBlockEntityRenderer)
        HybridAquaticBlockRenderers.registerRenderShapes()
    }


    private fun registerSkullModels(event: EntityRenderersEvent.CreateSkullModels) {
        val modelLoader = event.entityModelSet

        event.registerSkullModel(
            PlushieBlock.Variant.BASKING_SHARK,
            BaskingSharkPlushieModel(modelLoader.bakeLayer(BASKING_SHARK_PLUSHIE))
        )

        event.registerSkullModel(
            PlushieBlock.Variant.BULL_SHARK,
            BullSharkPlushieModel(modelLoader.bakeLayer(BULL_SHARK_PLUSHIE))
        )
        event.registerSkullModel(
            PlushieBlock.Variant.FRILLED_SHARK,
            BullSharkPlushieModel(modelLoader.bakeLayer(FRILLED_SHARK_PLUSHIE))
        )
        event.registerSkullModel(
            PlushieBlock.Variant.GREAT_WHITE_SHARK,
            BullSharkPlushieModel(modelLoader.bakeLayer(GREAT_WHITE_SHARK_PLUSHIE))
        )
        event.registerSkullModel(
            PlushieBlock.Variant.HAMMERHEAD_SHARK,
            BullSharkPlushieModel(modelLoader.bakeLayer(HAMMERHEAD_SHARK_PLUSHIE))
        )
        event.registerSkullModel(
            PlushieBlock.Variant.THRESHER_SHARK,
            BullSharkPlushieModel(modelLoader.bakeLayer(THRESHER_SHARK_PLUSHIE))
        )
        event.registerSkullModel(
            PlushieBlock.Variant.TIGER_SHARK,
            BullSharkPlushieModel(modelLoader.bakeLayer(TIGER_SHARK_PLUSHIE))
        )
        event.registerSkullModel(
            PlushieBlock.Variant.WHALE_SHARK,
            BullSharkPlushieModel(modelLoader.bakeLayer(WHALE_SHARK_PLUSHIE))
        )
    }

    /**
     * This is used for initializing client specific
     * things such as renderers and keymaps
     * Fired on the mod specific event bus.
     */
    @Suppress("UNUSED_PARAMETER")
    private fun onClientSetup(event: FMLClientSetupEvent) {
        logger.info("Initializing client...")
    }

    /**
     * Fired on the global Forge bus.
     */
    @Suppress("UNUSED_PARAMETER")
    private fun onServerSetup(event: FMLDedicatedServerSetupEvent) {
        logger.info("Server starting...")
    }

    @SubscribeEvent
    fun registerBrewingRecipes(event: RegisterBrewingRecipesEvent) {
        for (recipe in HybridAquaticPotions.recipes.get()) {
            event.builder.addMix(
                recipe.inputPotion,
                recipe.addition,
                recipe.outputPotion
            )
        }
    }

}