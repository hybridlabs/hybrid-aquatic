package dev.hybridlabs.aquatic.client.render.block.entity

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.entity.FishingPlaqueBlockEntity
import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import dev.hybridlabs.aquatic.entity.fish.BarreleyeEntity
import net.minecraft.block.BlockState
import net.minecraft.client.MinecraftClient
import net.minecraft.client.render.VertexConsumerProvider
import net.minecraft.client.render.block.entity.BlockEntityRenderer
import net.minecraft.client.render.block.entity.BlockEntityRendererFactory
import net.minecraft.client.render.block.entity.EnchantingTableBlockEntityRenderer
import net.minecraft.client.render.entity.EntityRenderDispatcher
import net.minecraft.client.util.math.MatrixStack
import net.minecraft.entity.EntityType
import net.minecraft.state.property.Properties
import net.minecraft.util.math.Direction
import net.minecraft.util.math.RotationAxis

class FishingPlaqueBlockEntityRenderer(ctx : BlockEntityRendererFactory.Context) : BlockEntityRenderer<FishingPlaqueBlockEntity> {

    private val dispatcher: EntityRenderDispatcher;

    init {
        dispatcher = ctx.entityRenderDispatcher;
    }

    override fun render(
        entity: FishingPlaqueBlockEntity?,
        tickDelta: Float,
        matrices: MatrixStack?,
        vertexConsumers: VertexConsumerProvider?,
        light: Int,
        overlay: Int
    ) {
        val state = entity?.world?.getBlockState(entity.pos)
        if(state!!.isOf(HybridAquaticBlocks.FISHING_PLAQUE) && entity.getStoredEntity() != null) {
            applyTransformation(state, matrices!!)

            dispatcher.render(entity.getStoredEntity(), 0.0, 0.0, 0.0, 0F, tickDelta, matrices, vertexConsumers, light)
        }
    }

    private fun applyTransformation(state: BlockState?, stack: MatrixStack?) {
        when(state!!.get(Properties.HORIZONTAL_FACING)) {
            Direction.NORTH -> {
                stack!!.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(90.0f))
                stack.translate(-0.75,0.25,0.45)
            }
            Direction.SOUTH -> {
                stack!!.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-90.0f))
            }
            Direction.EAST -> {

            }
            Direction.UP -> {

            }
            Direction.DOWN -> {

            }
            else -> {
                println("Somehow, some way you achieved an impossible state. Good job.")
            }
        }
    }
}