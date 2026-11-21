package dev.hybridlabs.aquatic.client.render.block.entity

import com.mojang.math.Axis
import dev.hybridlabs.aquatic.block.MessageInABottleBlock
import dev.hybridlabs.aquatic.block.entity.MessageInABottleBlockEntity
import dev.hybridlabs.aquatic.client.model.block.entity.MessageInABottleBlockEntityModel
import dev.hybridlabs.hapi.client.model.HAPIDataTickets
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState
import net.minecraft.core.BlockPos
import net.minecraft.util.Mth
import net.minecraft.util.RandomSource
import net.minecraft.world.level.block.state.properties.BlockStateProperties.WATERLOGGED
import com.geckolib.constant.DataTickets
import com.geckolib.constant.dataticket.DataTicket
import com.geckolib.renderer.base.RenderPassInfo
import com.geckolib.renderer.GeoBlockRenderer

class MessageInABottleBlockEntityRenderer(context: BlockEntityRendererProvider.Context) :
    GeoBlockRenderer<MessageInABottleBlockEntity, HABlockEntityRenderState>(context, MessageInABottleBlockEntityModel()) {
    private val random = RandomSource.create()


    override fun createRenderState(): HABlockEntityRenderState = HABlockEntityRenderState()

    override fun addRenderData(
        animatable: MessageInABottleBlockEntity,
        relatedObject: Void?,
        renderState: HABlockEntityRenderState,
        partialTick: Float
    ) {
        super.addRenderData(animatable, relatedObject, renderState, partialTick)
        renderState.addGeckolibData(HAPIDataTickets.VARIANT, animatable.variant.ordinal)
        renderState.addGeckolibData(WATERLOGGED_KEY, animatable.blockState.getValue(WATERLOGGED))
        renderState.addGeckolibData(POSITION_KEY, animatable.blockPos)
    }

    override fun adjustRenderPose(renderPassInfo: RenderPassInfo<HABlockEntityRenderState>) {
        val poseStack = renderPassInfo.poseStack()
        val variant = renderPassInfo.getGeckolibData(HAPIDataTickets.VARIANT) ?: 0
        val waterlogged = renderPassInfo.getGeckolibData(WATERLOGGED_KEY) ?: true
        val pos = renderPassInfo.getGeckolibData(POSITION_KEY) ?: return

        poseStack.translate(-0.5, 0.0, -0.5)

        // random rotation
        random.setSeed(Mth.getSeed(pos.x, 0, pos.z))
        poseStack.translate(0.5, 0.0, 0.5)
        poseStack.mulPose(Axis.YP.rotationDegrees(random.nextFloat() * 360.0f + (ROTATION_PER * (variant + 1))))
        poseStack.translate(-0.5, 0.0, -0.5)

        // translate in water
        if (waterlogged) {
            poseStack.translate(0.0, 0.8, 0.0)
        } else {
            poseStack.translate(0.0, 0.001, 0.0)
        }
    }

    companion object {
        val ROTATION_PER = 360.0f / MessageInABottleBlock.Variant.entries.size

        private val WATERLOGGED_KEY = DataTicket.create("bottle_waterlogged", Boolean::class.java)
        private val POSITION_KEY = DataTicket.create("bottle_position", BlockPos::class.java)
    }
}
