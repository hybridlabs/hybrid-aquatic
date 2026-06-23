package dev.hybridlabs.aquatic.client.render.item.renderer

import com.mojang.blaze3d.vertex.PoseStack
import dev.hybridlabs.aquatic.block.BlockEntityHelper.Companion.createBlockEntityRendererProviderContext
import dev.hybridlabs.aquatic.block.HABlocks
import dev.hybridlabs.aquatic.block.MessageInABottleBlock
import dev.hybridlabs.aquatic.block.entity.MessageInABottleBlockEntity
import dev.hybridlabs.aquatic.client.render.block.entity.MessageInABottleBlockEntityRenderer
import net.minecraft.client.Minecraft
import net.minecraft.client.model.geom.EntityModelSet
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher
import net.minecraft.core.BlockPos
import net.minecraft.world.item.ItemDisplayContext
import net.minecraft.world.item.ItemStack

class MessageInABottleBlockItemRenderer(
    blockEntityRenderDispatcher: BlockEntityRenderDispatcher,
    entityModelSet: EntityModelSet
) :
    BlockEntityWithoutLevelRenderer(
        blockEntityRenderDispatcher, entityModelSet
    ) {

    constructor() : this(client.blockEntityRenderDispatcher, client.entityModels)

    val blockEntity: MessageInABottleBlockEntity by lazy {
        MessageInABottleBlockEntity(BlockPos.ZERO, HABlocks.MESSAGE_IN_A_BOTTLE.get().defaultBlockState())
    }

    override fun renderByItem(
        stack: ItemStack, displayContext: ItemDisplayContext,
        poseStack: PoseStack, buffer: MultiBufferSource, packedLight: Int,
        packedOverlay: Int
    ) {
        val id = stack.components[DataComponents.BLOCK_ENTITY_DATA]?.copyTag()
            ?.getString(MessageInABottleBlockEntity.VARIANT_KEY) ?: ""
        blockEntity.variant = MessageInABottleBlock.Variant.byId(id)

        @Suppress("UnstableApiUsage")
        RENDERER.render(blockEntity, 1.0f, poseStack, buffer, packedLight, packedOverlay)
    }

    companion object {
        private val RENDERER =
            MessageInABottleBlockEntityRenderer(createBlockEntityRendererProviderContext())
        private val client: Minecraft = Minecraft.getInstance()
    }
}