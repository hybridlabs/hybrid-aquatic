package dev.hybridlabs.aquatic

import com.mojang.serialization.Codec
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
import dev.hybridlabs.aquatic.entity.ForgeSpawnGroupRegistry
import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import dev.hybridlabs.aquatic.entity.SpawnRestrictionRegistry
import dev.hybridlabs.aquatic.fluid.HybridAquaticFluids
import dev.hybridlabs.aquatic.item.HybridAquaticItemGroups
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import dev.hybridlabs.aquatic.item.HybridAquaticPlatformItems
import dev.hybridlabs.aquatic.loot.HAGlobalLootModifier
import dev.hybridlabs.aquatic.loot.entry.HybridAquaticLootPoolEntryTypes
import dev.hybridlabs.aquatic.network.HybridAquaticNetworking
import dev.hybridlabs.aquatic.painting.HybridAquaticPaintings
import dev.hybridlabs.aquatic.potions.HybridAquaticPotions
import dev.hybridlabs.aquatic.registry.HybridAquaticRegistryKeys
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
import net.minecraftforge.common.loot.IGlobalLootModifier
import net.minecraftforge.common.world.StructureModifier
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent
import net.minecraftforge.event.entity.living.LivingBreatheEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLDedicatedServerSetupEvent
import net.minecraftforge.registries.DataPackRegistryEvent
import net.minecraftforge.registries.DeferredRegister
import net.minecraftforge.registries.ForgeRegistries
import thedarkcolour.kotlinforforge.forge.FORGE_BUS
import thedarkcolour.kotlinforforge.forge.MOD_BUS
import thedarkcolour.kotlinforforge.forge.runForDist


@Suppress("UnusedExpression")
@Mod(Constants.FORGE_MOD_ID)
object HybridAquaticForge {
    private val logger = Constants.LOG!!

    init {
        CommonClass.init()

        ForgeSpawnGroupRegistry.createHybridAquaticSpawnGroups()
        registerStructureModifiers()
        registerGlobalLootModifiers()

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

        MOD_BUS.addListener(::loadSeaMessages)
        MOD_BUS.addListener(::registerPotionsRecipes)
        MOD_BUS.addListener(::registerSpawnPlacements)
        FORGE_BUS.addListener(HybridAquaticCustomTrades::registerWandererTrades)
        FORGE_BUS.addListener(HybridAquaticCustomTrades::registerCustomTrades)
        EVENT_BUS.addListener(::makeBlocksBreatheable)

        runForDist(
            clientTarget = {
                MOD_BUS.addListener(::onClientSetup)
                MOD_BUS.addListener(::registerModelLayers)
                MOD_BUS.addListener(::registerSkullModels)
                MOD_BUS.addListener(::registerBlockEntityRenderers)
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

    private fun registerSpawnPlacements(event: SpawnPlacementRegisterEvent) {
        SpawnRestrictionRegistry.registerSpawnRestrictions()
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

    private fun registerModelLayers(event: EntityRenderersEvent.RegisterLayerDefinitions) {
        event.registerLayerDefinition(BASKING_SHARK_PLUSHIE, BaskingSharkPlushieModel.Companion::createModelData)
        event.registerLayerDefinition(BULL_SHARK_PLUSHIE, BullSharkPlushieModel.Companion::createModelData)
        event.registerLayerDefinition(FRILLED_SHARK_PLUSHIE, FrilledSharkPlushieModel.Companion::createModelData)
        event.registerLayerDefinition(GREAT_WHITE_SHARK_PLUSHIE, GreatWhiteSharkPlushieModel.Companion::createModelData)
        event.registerLayerDefinition(HAMMERHEAD_SHARK_PLUSHIE, HammerheadSharkPlushieModel.Companion::createModelData)
        event.registerLayerDefinition(THRESHER_SHARK_PLUSHIE, ThresherSharkPlushieModel.Companion::createModelData)
        event.registerLayerDefinition(TIGER_SHARK_PLUSHIE, TigerSharkPlushieModel.Companion::createModelData)
        event.registerLayerDefinition(WHALE_SHARK_PLUSHIE, WhaleSharkPlushieModel.Companion::createModelData)
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
    private fun onClientSetup(event: FMLClientSetupEvent) {
        logger.info("Initializing client...")
    }

    /**
     * Fired on the global Forge bus.
     */
    private fun onServerSetup(event: FMLDedicatedServerSetupEvent) {
        logger.info("Server starting...")
    }

    private fun registerPotionsRecipes(event: FMLCommonSetupEvent) {
        event.enqueueWork {
            HybridAquaticPotions.registerPotionRecipes()
        }
    }

    private fun registerStructureModifiers() {
        val structureModifiers: DeferredRegister<Codec<out StructureModifier?>?> =
            DeferredRegister.create(ForgeRegistries.Keys.STRUCTURE_MODIFIER_SERIALIZERS, Constants.MOD_ID)
        structureModifiers.register(MOD_BUS)
        structureModifiers.register<Codec<out StructureModifier?>?>(
            "ha_structure_spawns",
            StructureSpawnModifier::makeCodec
        )
    }

    private fun registerGlobalLootModifiers(){
        val lootModifiers = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Constants.MOD_ID)
        lootModifiers.register(MOD_BUS)
        lootModifiers.register<Codec<out IGlobalLootModifier>>("ha_loot_modifier", HAGlobalLootModifier::CODEC)
    }
}
