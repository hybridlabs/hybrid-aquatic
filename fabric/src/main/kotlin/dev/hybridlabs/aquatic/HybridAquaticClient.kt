@file:Suppress("UNUSED_PARAMETER")

package dev.hybridlabs.aquatic

import com.mojang.brigadier.CommandDispatcher
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.entity.HybridAquaticBlockEntityTypes
import dev.hybridlabs.aquatic.client.GeoRenderProviderStorage
import dev.hybridlabs.aquatic.client.command.RandomFishCommand
import dev.hybridlabs.aquatic.client.item.tooltip.FishingNetTooltip
import dev.hybridlabs.aquatic.client.model.HybridAquaticEntityModelLayers
import dev.hybridlabs.aquatic.client.network.HybridAquaticClientNetworking
import dev.hybridlabs.aquatic.client.renderer.armor.*
import dev.hybridlabs.aquatic.client.renderer.block.entity.*
import dev.hybridlabs.aquatic.client.renderer.entity.HybridAquaticEntityRenderers
import dev.hybridlabs.aquatic.client.renderer.item.*
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderTypeMap
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry
import net.minecraft.client.MinecraftClient
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.block.entity.BlockEntityRendererFactories
import net.minecraft.client.renderer.block.entity.Bloc.EntityRendererProvider
import net.minecraft.client.renderer.entity.model.HumanoidModel
import net.minecraft.command.CommandRegistryAccess
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.item.ItemStack
import software.bernie.geckolib.animatable.client.RenderProvider
import software.bernie.geckolib.renderer.GeoArmorRenderer

object HybridAquaticClient : ClientModInitializer {
    override fun onInitializeClient() {
        HybridAquaticEntityModelLayers
        HybridAquaticClientNetworking

        registerBlockRenderTypes()
        registerBlockEntityRenderers()
        registerBuiltinItemRenderers()
        registerEntityRenderers()
        registerWeatherRenderers()
        registerTooltips()
        registerGeoRenderers()

        ClientCommandRegistrationCallback.EVENT.register(::registerCommands)
    }

    private fun registerGeoRenderers() {
        GeoRenderProviderStorage.divingArmorRenderProvider = createBasicRenderProvider(::DivingArmorRenderer)
        GeoRenderProviderStorage.seashellArmorRenderProvider = createBasicRenderProvider(::SeashellArmorRenderer)
        GeoRenderProviderStorage.manglerfishArmorRenderProvider = createBasicRenderProvider(::ManglerfishArmorRenderer)
        GeoRenderProviderStorage.turtleArmorRenderProvider = createBasicRenderProvider(::TurtleArmorRenderer)
        GeoRenderProviderStorage.eelArmorRenderProvider = createBasicRenderProvider(::EelArmorRenderer)
        GeoRenderProviderStorage.moonjellyfishArmorRenderProvider = createBasicRenderProvider(::MoonJellyfishArmorRenderer)
    }

    private fun createBasicRenderProvider(rendererProvider: () -> GeoArmorRenderer<*>): () -> RenderProvider {
        return {
            object : RenderProvider {
                private val renderer: GeoArmorRenderer<*> by lazy(rendererProvider)

                override fun getHumanoidArmorModel(
                    livingEntity: LivingEntity,
                    itemStack: ItemStack,
                    equipmentSlot: EquipmentSlot,
                    original: HumanoidModel<LivingEntity>
                ): HumanoidModel<LivingEntity> {
                    renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original)
                    return renderer
                }
            }
        }
    }

    private fun registerWeatherRenderers() {
        // TODO: hook up renderer to make this thing easier
    }

    private fun registerTooltips() {
        ItemTooltipCallback.EVENT.register(FishingNetTooltip())
    }

    private fun registerBlockRenderTypes(registry: BlockRenderTypeMap = BlockRenderTypeMap.INSTANCE) {
        registry.putBlocks(
            RenderType.getTranslucent(),
            HybridAquaticBlocks.ANEMONE,
            HybridAquaticBlocks.GIANT_GREEN_ANEMONE,
            HybridAquaticBlocks.STRAWBERRY_ANEMONE,
            HybridAquaticBlocks.MESSAGE_IN_A_BOTTLE,
        )
        registry.putBlocks(
            RenderType.getCutout(),
            HybridAquaticBlocks.RED_ALGAE,
            HybridAquaticBlocks.TALL_RED_ALGAE,

            HybridAquaticBlocks.BULL_KELP,
            HybridAquaticBlocks.BULL_KELP_PLANT,

            HybridAquaticBlocks.SARGASSUM,
            HybridAquaticBlocks.SARGASSUM_PLANT,
            HybridAquaticBlocks.FLOATING_SARGASSUM,

            HybridAquaticBlocks.WATER_LETTUCE,
            HybridAquaticBlocks.JUNGLE_LILY_PAD,

            HybridAquaticBlocks.GLOWING_PLANKTON,

            HybridAquaticBlocks.SEA_LETTUCE,
            HybridAquaticBlocks.TALL_SEA_LETTUCE,

            HybridAquaticBlocks.CRAB_POT,
            HybridAquaticBlocks.GIANT_CLAM,
            HybridAquaticBlocks.TUBE_WORM,

            HybridAquaticBlocks.LOPHELIA_CORAL,
            HybridAquaticBlocks.LOPHELIA_CORAL_FAN,
            HybridAquaticBlocks.LOPHELIA_CORAL_WALL_FAN,
            HybridAquaticBlocks.DEAD_LOPHELIA_CORAL,
            HybridAquaticBlocks.DEAD_LOPHELIA_CORAL_FAN,
            HybridAquaticBlocks.DEAD_LOPHELIA_CORAL_WALL_FAN,

            HybridAquaticBlocks.SUN_CORAL,
            HybridAquaticBlocks.SUN_CORAL_FAN,
            HybridAquaticBlocks.SUN_CORAL_WALL_FAN,
            HybridAquaticBlocks.DEAD_SUN_CORAL,
            HybridAquaticBlocks.DEAD_SUN_CORAL_FAN,
            HybridAquaticBlocks.DEAD_SUN_CORAL_WALL_FAN,

            HybridAquaticBlocks.BUTTON_CORAL,
            HybridAquaticBlocks.BUTTON_CORAL_FAN,
            HybridAquaticBlocks.BUTTON_CORAL_WALL_FAN,
            HybridAquaticBlocks.DEAD_BUTTON_CORAL,
            HybridAquaticBlocks.DEAD_BUTTON_CORAL_FAN,
            HybridAquaticBlocks.DEAD_BUTTON_CORAL_WALL_FAN,

            HybridAquaticBlocks.THORN_CORAL,
            HybridAquaticBlocks.THORN_CORAL_FAN,
            HybridAquaticBlocks.THORN_CORAL_WALL_FAN,
            HybridAquaticBlocks.DEAD_THORN_CORAL,
            HybridAquaticBlocks.DEAD_THORN_CORAL_FAN,
            HybridAquaticBlocks.DEAD_THORN_CORAL_WALL_FAN,

            HybridAquaticBlocks.DRIFTWOOD_DOOR,
            HybridAquaticBlocks.DRIFTWOOD_TRAPDOOR,
            HybridAquaticBlocks.GLOWSTICK,
            HybridAquaticBlocks.WALL_GLOWSTICK,
        )
    }

    private fun registerBlockEntityRenderers() {
        BlockEntityRendererFactories.register(HybridAquaticBlockEntityTypes.ANEMONE, ::AnemoneBlockEntityRenderer)
        BlockEntityRendererFactories.register(HybridAquaticBlockEntityTypes.GIANT_GREEN_ANEMONE, ::GiantGreenAnemoneBlockEntityRenderer)
        BlockEntityRendererFactories.register(HybridAquaticBlockEntityTypes.STRAWBERRY_ANEMONE, ::StrawberryAnemoneBlockEntityRenderer)
        BlockEntityRendererFactories.register(HybridAquaticBlockEntityTypes.MESSAGE_IN_A_BOTTLE, ::MessageInABottleBlockEntityRenderer)
        BlockEntityRendererFactories.register(HybridAquaticBlockEntityTypes.BUOY, ::BuoyBlockEntityRenderer)
    }

    private fun registerEntityRenderers() {
        HybridAquaticEntityRenderers
    }

    private fun registerBuiltinItemRenderers(registry: BuiltinItemRendererRegistry = BuiltinItemRendererRegistry.INSTANCE) {
        registry.register(HybridAquaticItems.ANEMONE, AnemoneBlockItemRenderer())
        registry.register(HybridAquaticItems.GIANT_GREEN_ANEMONE, GiantGreenAnemoneBlockItemRenderer())
        registry.register(HybridAquaticItems.STRAWBERRY_ANEMONE, StrawberryAnemoneBlockItemRenderer())
        registry.register(HybridAquaticItems.BUOY, BuoyBlockItemRenderer())
        registry.register(HybridAquaticItems.MESSAGE_IN_A_BOTTLE, MessageInABottleBlockItemRenderer())
    }

    fun createBloc.EntityRendererProviderContext(): Bloc.EntityRendererProvider.Context {
        val client = MinecraftClient.getInstance()
        return Bloc.EntityRendererProvider.Context(
            client.blockEntityRenderDispatcher,
            client.blockRenderManager,
            client.itemRenderer,
            client.entityRenderDispatcher,
            client.entityModelLoader,
            client.textRenderer
        )
    }

    private fun registerCommands(dispatcher: CommandDispatcher<FabricClientCommandSource>, access: CommandRegistryAccess) {
        RandomFishCommand.register(dispatcher)
    }
}
