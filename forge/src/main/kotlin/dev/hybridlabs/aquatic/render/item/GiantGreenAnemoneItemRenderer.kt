package dev.hybridlabs.aquatic.render.item

import com.mojang.blaze3d.vertex.PoseStack
import dev.hybridlabs.aquatic.block.BlockEntityHelper.Companion.createBlockEntityRendererProviderContext
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.entity.GiantGreenAnemoneBlockEntity
import dev.hybridlabs.aquatic.client.render.block.entity.GiantGreenAnemoneBlockEntityRenderer
import net.minecraft.client.Minecraft
import net.minecraft.client.model.geom.EntityModelSet
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher
import net.minecraft.core.BlockPos
import net.minecraft.world.item.ItemDisplayContext
import net.minecraft.world.item.ItemStack

class GiantGreenAnemoneItemRenderer(
    blockEntityRenderDispatcher: BlockEntityRenderDispatcher,
    entityModelSet: EntityModelSet
) :
    BlockEntityWithoutLevelRenderer(
        blockEntityRenderDispatcher, entityModelSet
    ) {

    constructor() : this(client.blockEntityRenderDispatcher, client.entityModels)

    val blockEntity by lazy {
        GiantGreenAnemoneBlockEntity(BlockPos.ZERO, HybridAquaticBlocks.GIANT_GREEN_ANEMONE.get().defaultBlockState())
    }

    override fun renderByItem(
        stack: ItemStack, displayContext: ItemDisplayContext,
        poseStack: PoseStack, buffer: MultiBufferSource, packedLight: Int,
        packedOverlay: Int
    ) {
        RENDERER.render(blockEntity, 1.0f, poseStack, buffer, packedLight, packedOverlay)
    }

    companion object {
        private val RENDERER: GiantGreenAnemoneBlockEntityRenderer =
            GiantGreenAnemoneBlockEntityRenderer(createBlockEntityRendererProviderContext())
        private val client: Minecraft = Minecraft.getInstance()
    }
}