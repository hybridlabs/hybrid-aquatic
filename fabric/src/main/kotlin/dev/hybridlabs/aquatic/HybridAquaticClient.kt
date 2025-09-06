@file:Suppress("UNUSED_PARAMETER")

package dev.hybridlabs.aquatic

import com.mojang.brigadier.CommandDispatcher
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.entity.HybridAquaticBlockEntityTypes
import dev.hybridlabs.aquatic.client.command.RandomFishCommand
import dev.hybridlabs.aquatic.client.item.tooltip.FishingNetTooltip
import dev.hybridlabs.aquatic.client.model.HybridAquaticEntityModelLayers
import dev.hybridlabs.aquatic.client.network.HybridAquaticClientNetworking
import dev.hybridlabs.aquatic.client.render.GeoRenderProviderStorage
import dev.hybridlabs.aquatic.client.render.armor.*
import dev.hybridlabs.aquatic.client.render.block.entity.*
import dev.hybridlabs.aquatic.client.render.entity.HybridAquaticEntityRenderers
import dev.hybridlabs.aquatic.client.render.item.AnemoneBlockItemRenderer
import dev.hybridlabs.aquatic.client.render.item.BuoyBlockItemRenderer
import dev.hybridlabs.aquatic.client.render.item.GiantGreenAnemoneBlockItemRenderer
import dev.hybridlabs.aquatic.client.render.item.MessageInABottleBlockItemRenderer
import dev.hybridlabs.aquatic.client.renderer.item.StrawberryAnemoneBlockItemRenderer
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import dev.hybridlabs.aquatic.platform.ClientServices
import net.fabricmc.api.ClientModInitializer
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback
import net.fabricmc.fabric.api.client.command.v2.FabricClientCommandSource
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry
import net.minecraft.client.Minecraft
import net.minecraft.client.model.HumanoidModel
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers
import net.minecraft.commands.CommandBuildContext
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.ItemStack
import software.bernie.geckolib.animatable.client.RenderProvider
import software.bernie.geckolib.renderer.GeoArmorRenderer

object HybridAquaticClient : ClientModInitializer {
    override fun onInitializeClient() {
        HybridAquaticEntityModelLayers
        HybridAquaticClientNetworking

        registerRenderShapes()
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
        GeoRenderProviderStorage.moonjellyfishArmorRenderProvider =
            createBasicRenderProvider(::MoonJellyfishArmorRenderer)
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
                    return renderer as HumanoidModel<LivingEntity>
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

    private fun registerRenderShapes() {
        ClientServices.PLATFORM.registerBlockRenderers(
            RenderType.translucent(),
            HybridAquaticBlocks.ANEMONE.get(),
            HybridAquaticBlocks.GIANT_GREEN_ANEMONE.get(),
            HybridAquaticBlocks.STRAWBERRY_ANEMONE.get(),
            HybridAquaticBlocks.MESSAGE_IN_A_BOTTLE.get(),
        )
        ClientServices.PLATFORM.registerBlockRenderers(
            RenderType.cutout(),
            HybridAquaticBlocks.RED_ALGAE.get(),
            HybridAquaticBlocks.TALL_RED_ALGAE.get(),

            HybridAquaticBlocks.BULL_KELP.get(),
            HybridAquaticBlocks.BULL_KELP_PLANT.get(),

            HybridAquaticBlocks.SARGASSUM.get(),
            HybridAquaticBlocks.SARGASSUM_PLANT.get(),
            HybridAquaticBlocks.FLOATING_SARGASSUM.get(),

            HybridAquaticBlocks.WATER_LETTUCE.get(),
            HybridAquaticBlocks.JUNGLE_LILY_PAD.get(),

            HybridAquaticBlocks.GLOWING_PLANKTON.get(),

            HybridAquaticBlocks.SEA_LETTUCE.get(),
            HybridAquaticBlocks.TALL_SEA_LETTUCE.get(),

            HybridAquaticBlocks.CRAB_POT.get(),
            HybridAquaticBlocks.GIANT_CLAM.get(),
            HybridAquaticBlocks.TUBE_WORM.get(),

            HybridAquaticBlocks.LOPHELIA_CORAL.get(),
            HybridAquaticBlocks.LOPHELIA_CORAL_FAN.get(),
            HybridAquaticBlocks.LOPHELIA_CORAL_WALL_FAN.get(),
            HybridAquaticBlocks.DEAD_LOPHELIA_CORAL.get(),
            HybridAquaticBlocks.DEAD_LOPHELIA_CORAL_FAN.get(),
            HybridAquaticBlocks.DEAD_LOPHELIA_CORAL_WALL_FAN.get(),

            HybridAquaticBlocks.SUN_CORAL.get(),
            HybridAquaticBlocks.SUN_CORAL_FAN.get(),
            HybridAquaticBlocks.SUN_CORAL_WALL_FAN.get(),
            HybridAquaticBlocks.DEAD_SUN_CORAL.get(),
            HybridAquaticBlocks.DEAD_SUN_CORAL_FAN.get(),
            HybridAquaticBlocks.DEAD_SUN_CORAL_WALL_FAN.get(),

            HybridAquaticBlocks.BUTTON_CORAL.get(),
            HybridAquaticBlocks.BUTTON_CORAL_FAN.get(),
            HybridAquaticBlocks.BUTTON_CORAL_WALL_FAN.get(),
            HybridAquaticBlocks.DEAD_BUTTON_CORAL.get(),
            HybridAquaticBlocks.DEAD_BUTTON_CORAL_FAN.get(),
            HybridAquaticBlocks.DEAD_BUTTON_CORAL_WALL_FAN.get(),

            HybridAquaticBlocks.THORN_CORAL.get(),
            HybridAquaticBlocks.THORN_CORAL_FAN.get(),
            HybridAquaticBlocks.THORN_CORAL_WALL_FAN.get(),
            HybridAquaticBlocks.DEAD_THORN_CORAL.get(),
            HybridAquaticBlocks.DEAD_THORN_CORAL_FAN.get(),
            HybridAquaticBlocks.DEAD_THORN_CORAL_WALL_FAN.get(),

            HybridAquaticBlocks.DRIFTWOOD_DOOR.get(),
            HybridAquaticBlocks.DRIFTWOOD_TRAPDOOR.get(),
            HybridAquaticBlocks.GLOWSTICK.get(),
            HybridAquaticBlocks.WALL_GLOWSTICK.get(),
        )
    }

    private fun registerBlockEntityRenderers() {
        BlockEntityRenderers.register(HybridAquaticBlockEntityTypes.ANEMONE.get(), ::AnemoneBlockEntityRenderer)
        BlockEntityRenderers.register(
            HybridAquaticBlockEntityTypes.GIANT_GREEN_ANEMONE.get(),
            ::GiantGreenAnemoneBlockEntityRenderer
        )
        BlockEntityRenderers.register(
            HybridAquaticBlockEntityTypes.STRAWBERRY_ANEMONE.get(),
            ::StrawberryAnemoneBlockEntityRenderer
        )
        BlockEntityRenderers.register(
            HybridAquaticBlockEntityTypes.MESSAGE_IN_A_BOTTLE.get(),
            ::MessageInABottleBlockEntityRenderer
        )
        BlockEntityRenderers.register(HybridAquaticBlockEntityTypes.BUOY.get(), ::BuoyBlockEntityRenderer)
    }

    private fun registerEntityRenderers() {
        HybridAquaticEntityRenderers
    }

    private fun registerBuiltinItemRenderers(registry: BuiltinItemRendererRegistry = BuiltinItemRendererRegistry.INSTANCE) {
        registry.register(HybridAquaticItems.ANEMONE.get(), AnemoneBlockItemRenderer())
        registry.register(HybridAquaticItems.GIANT_GREEN_ANEMONE.get(), GiantGreenAnemoneBlockItemRenderer())
        registry.register(HybridAquaticItems.STRAWBERRY_ANEMONE.get(), StrawberryAnemoneBlockItemRenderer())
        registry.register(HybridAquaticItems.BUOY.get(), BuoyBlockItemRenderer())
        registry.register(HybridAquaticItems.MESSAGE_IN_A_BOTTLE.get(), MessageInABottleBlockItemRenderer())
    }

    fun createBlockEntityRendererProviderContext(): BlockEntityRendererProvider.Context {
        val client =
            Minecraft.getInstance()
        return BlockEntityRendererProvider.Context(
            client.blockEntityRenderDispatcher,
            client.blockRenderer,
            client.itemRenderer,
            client.entityRenderDispatcher,
            client.entityModels,
            client.font
        )
    }

    private fun registerCommands(
        dispatcher: CommandDispatcher<FabricClientCommandSource>,
        access: CommandBuildContext
    ) {
        RandomFishCommand.register(dispatcher)
    }
}
