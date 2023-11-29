package dev.hybridlabs.aquatic.client.render.block.entity

import dev.hybridlabs.aquatic.block.entity.HydrothermalVentBlockEntity
import dev.hybridlabs.aquatic.client.model.block.entity.HydrothermalVentBlockEntityModel
import net.minecraft.client.render.VertexConsumer
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory.Context
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.util.math.MathHelper
import net.minecraft.util.math.RotationAxis
import net.minecraft.util.math.random.Random
import software.bernie.geckolib.cache.`object`.BakedGeoModel
import software.bernie.geckolib.renderer.GeoBlockRenderer

class HydrothermalVentBlockEntityRenderer(context: Context) : GeoBlockRenderer<HydrothermalVentBlockEntity>(HydrothermalVentBlockEntityModel()) {
    private val random = Random.create()

    override fun preRender(
        matrices: MatrixStack,
        blockEntity: HydrothermalVentBlockEntity,
        model: BakedGeoModel,
        consumerProvider: VertexConsumerProvider?,
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
        val pos = blockEntity.pos

        // random rotation
        val hashCode = MathHelper.hashCode(pos.x, 0, pos.z)
        random.setSeed(hashCode)
        matrices.translate(0.5, 0.0, 0.5)
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(random.nextFloat() * 360.0f))
        matrices.translate(-0.5, 0.0, -0.5)

        // super
        super.preRender(matrices, blockEntity, model, consumerProvider, consumer, isReRender, partialTick, packedLight, packedOverlay, red, green, blue, alpha)
    }
}