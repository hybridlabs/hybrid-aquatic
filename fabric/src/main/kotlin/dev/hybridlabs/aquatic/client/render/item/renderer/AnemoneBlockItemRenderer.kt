package dev.hybridlabs.aquatic.client.render.item.renderer

import com.mojang.blaze3d.vertex.PoseStack
import dev.hybridlabs.aquatic.block.BlockEntityHelper.Companion.createBlockEntityRendererProviderContext
import dev.hybridlabs.aquatic.block.HABlocks
import dev.hybridlabs.aquatic.block.entity.AnemoneBlockEntity
import dev.hybridlabs.aquatic.client.render.block.entity.AnemoneBlockEntityRenderer
import net.minecraft.client.Minecraft
import net.minecraft.client.model.geom.EntityModelSet
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher
import net.minecraft.core.BlockPos
import net.minecraft.world.item.ItemDisplayContext
import net.minecraft.world.item.ItemStack

/**
 * Renders the Anemone block entity as an item.
 */
class AnemoneBlockItemRenderer(
    blockEntityRenderDispatcher: BlockEntityRenderDispatcher,
    entityModelSet: EntityModelSet
) :
    BlockEntityWithoutLevelRenderer(
        blockEntityRenderDispatcher, entityModelSet
    ) {

    constructor() : this(client.blockEntityRenderDispatcher, client.entityModels)

    val anemoneBlockEntity by lazy {
        AnemoneBlockEntity(BlockPos.ZERO, HABlocks.ANEMONE.get().defaultBlockState())
    }

    override fun renderByItem(
        stack: ItemStack, displayContext: ItemDisplayContext,
        poseStack: PoseStack, buffer: MultiBufferSource, packedLight: Int,
        packedOverlay: Int
    ) {
        RENDERER.render(anemoneBlockEntity, 1.0f, poseStack, buffer, packedLight, packedOverlay)
    }

    companion object {
        private val RENDERER: AnemoneBlockEntityRenderer =
            AnemoneBlockEntityRenderer(createBlockEntityRendererProviderContext())
        private val client: Minecraft = Minecraft.getInstance()
    }
}