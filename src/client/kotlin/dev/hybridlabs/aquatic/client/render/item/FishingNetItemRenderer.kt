package dev.hybridlabs.aquatic.client.render.item

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.client.model.HybridAquaticEntityModelLayers
import dev.hybridlabs.aquatic.client.model.item.FishingNetItemModel
import net.fabricmc.fabric.api.client.rendering.v1.BuiltinItemRendererRegistry
import net.fabricmc.fabric.api.resource.ResourceReloadListenerKeys
import net.fabricmc.fabric.api.resource.SimpleSynchronousResourceReloadListener
import net.minecraft.client.MinecraftClient
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.item.ItemRenderer
import net.minecraft.client.render.model.BakedModel
import net.minecraft.client.render.model.json.ModelTransformationMode
import net.minecraft.client.util.ModelIdentifier
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.item.ItemStack
import net.minecraft.resource.ResourceManager
import net.minecraft.util.Identifier

object FishingNetItemRenderer : BuiltinItemRendererRegistry.DynamicItemRenderer, SimpleSynchronousResourceReloadListener {
    private val TEXTURE_ID = Identifier(HybridAquatic.MOD_ID, "textures/item/fishing_net.png")
    val INVENTORY_MODEL_ID = Identifier(HybridAquatic.MOD_ID, "item/fishing_net_in_inventory")
    private val INVENTORY_MODEL_IDENTIFIER = ModelIdentifier(INVENTORY_MODEL_ID, "inventory")

    private val LISTENER_ID = Identifier(HybridAquatic.MOD_ID, "fishing_net_item_model")

    private lateinit var model: FishingNetItemModel
    private lateinit var inventoryModel: BakedModel
    private lateinit var itemRenderer: ItemRenderer

    override fun render(
        stack: ItemStack,
        mode: ModelTransformationMode,
        matrices: MatrixStack,
        vertices: VertexConsumerProvider,
        light: Int,
        overlay: Int
    ) {
        itemRenderer.let { renderer ->
            if (mode == ModelTransformationMode.GUI || mode == ModelTransformationMode.GROUND || mode == ModelTransformationMode.FIXED) {
                matrices.push()
                renderer.renderItem(
                    stack,
                    mode,
                    false,
                    matrices,
                    vertices,
                    light,
                    overlay,
                    inventoryModel
                )
                matrices.pop()
            } else {
                matrices.push()
                matrices.scale(1.0f, -1.0f, -1.0f)
                val vertex = ItemRenderer.getDirectItemGlintConsumer(
                    vertices,
                    model.getLayer(TEXTURE_ID),
                    false,
                    stack.hasGlint()
                )
                model.render(matrices, vertex, light, overlay, 1.0f, 1.0f, 1.0f, 1.0f)
                matrices.pop()
            }
        }
    }

    override fun reload(manager: ResourceManager) {
        val client = MinecraftClient.getInstance()
        client.itemRenderer.let { renderer ->
            itemRenderer = renderer

            val entityModelLoader = client.entityModelLoader
            val bakedModelManager = client.bakedModelManager

            model = FishingNetItemModel(entityModelLoader.getModelPart(HybridAquaticEntityModelLayers.FISHING_NET))
            inventoryModel = bakedModelManager.getModel(INVENTORY_MODEL_IDENTIFIER)
        }
    }

    override fun getFabricId(): Identifier {
        return LISTENER_ID
    }

    override fun getFabricDependencies(): Collection<Identifier> {
        return listOf(ResourceReloadListenerKeys.MODELS)
    }
}
