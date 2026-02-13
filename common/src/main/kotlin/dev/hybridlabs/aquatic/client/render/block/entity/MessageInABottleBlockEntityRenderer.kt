@file:Suppress("DEPRECATION")

package dev.hybridlabs.aquatic.client.render.block.entity

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import com.mojang.math.Axis
import dev.hybridlabs.aquatic.block.MessageInABottleBlock
import dev.hybridlabs.aquatic.block.entity.MessageInABottleBlockEntity
import dev.hybridlabs.aquatic.client.model.block.entity.MessageInABottleBlockEntityModel
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider
import net.minecraft.util.Mth
import net.minecraft.util.RandomSource
import net.minecraft.world.level.block.state.properties.BlockStateProperties.WATERLOGGED
import software.bernie.geckolib.cache.`object`.BakedGeoModel
import software.bernie.geckolib.renderer.GeoBlockRenderer

class MessageInABottleBlockEntityRenderer(context: BlockEntityRendererProvider.Context) :
    GeoBlockRenderer<MessageInABottleBlockEntity>(MessageInABottleBlockEntityModel()) {
    private val random = RandomSource.create()

    override fun preRender(
        matrices: PoseStack,
        blockEntity: MessageInABottleBlockEntity,
        model: BakedGeoModel,
        consumerProvider: MultiBufferSource?,
        consumer: VertexConsumer?,
        isReRender: Boolean,
        partialTick: Float,
        packedLight: Int,
        packedOverlay: Int,
        red: Float,
        green: Float,
        blue: Float,
        alpha: Float
    ) {
        val pos = blockEntity.blockPos

        // random rotation
        val hashCode = Mth.getSeed(pos.x, 0, pos.z)
        random.setSeed(hashCode)
        matrices.translate(0.5, 0.0, 0.5)
        matrices.mulPose(Axis.YP.rotationDegrees(random.nextFloat() * 360.0f + (ROTATION_PER * (blockEntity.variant.ordinal + 1))))
        matrices.translate(-0.5, 0.0, -0.5)

        // translate in water
        if (blockEntity.blockState.getValue(WATERLOGGED)) {
            matrices.translate(0.0, 0.8, 0.0)
        } else {
            matrices.translate(0.0, 0.001, 0.0)
        }

        // super
        super.preRender(
            matrices,
            blockEntity,
            model,
            consumerProvider,
            consumer,
            isReRender,
            partialTick,
            packedLight,
            packedOverlay,
            red,
            green,
            blue,
            alpha
        )
    }

    companion object {
        val ROTATION_PER = 360.0f / MessageInABottleBlock.Variant.entries.size
    }
}
