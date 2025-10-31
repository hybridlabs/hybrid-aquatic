package dev.hybridlabs.aquatic.client.render.block.entity

import com.mojang.blaze3d.vertex.PoseStack
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider
import net.minecraft.core.Direction
import software.bernie.geckolib.renderer.GeoBlockRenderer
import com.mojang.math.Axis
import dev.hybridlabs.aquatic.block.entity.StrawberryAnemoneBlockEntity
import dev.hybridlabs.aquatic.client.model.block.entity.StrawberryAnemoneBlockEntityModel
import net.minecraft.world.level.block.DirectionalBlock
import net.minecraft.world.level.block.entity.BlockEntity

class StrawberryAnemoneBlockEntityRenderer(context: BlockEntityRendererProvider.Context) :
    GeoBlockRenderer<StrawberryAnemoneBlockEntity>(StrawberryAnemoneBlockEntityModel()) {

    override fun render(
        animatable: BlockEntity,
        partialTick: Float,
        poseStack: PoseStack,
        bufferSource: MultiBufferSource,
        packedLight: Int,
        packedOverlay: Int
    ) {
        val state = animatable.blockState
        val facing = state.getValue(DirectionalBlock.FACING)

        when(facing) {
            Direction.NORTH -> poseStack.mulPose(Axis.YP.rotationDegrees(180f))
            Direction.SOUTH -> poseStack.mulPose(Axis.YP.rotationDegrees(0f))
            Direction.WEST  -> poseStack.mulPose(Axis.YP.rotationDegrees(-90f))
            Direction.EAST  -> poseStack.mulPose(Axis.YP.rotationDegrees(90f))
            else -> {}
        }

        poseStack.pushPose()
        when (facing) {
            Direction.DOWN -> {
                poseStack.mulPose(Axis.XP.rotationDegrees(-90f))
                poseStack.translate(0.0, -0.5, 0.5)
            }
            Direction.UP -> {
                poseStack.mulPose(Axis.XP.rotationDegrees(-90f))
                poseStack.translate(0.0, -0.5, -0.5)
            }
            Direction.NORTH -> {
                poseStack.mulPose(Axis.XP.rotationDegrees(90f))
                poseStack.translate(-1.0, -1.0, -1.0)
            }
            Direction.SOUTH -> {
                poseStack.mulPose(Axis.XP.rotationDegrees(90f))
                poseStack.translate(0.0, 0.0, -1.0)
            }
            Direction.WEST -> {
                poseStack.mulPose(Axis.XP.rotationDegrees(90f))
                poseStack.translate(0.0, -1.0, -1.0)
            }
            Direction.EAST -> {
                poseStack.mulPose(Axis.XP.rotationDegrees(90f))
                poseStack.translate(-1.0, 0.0, -1.0)
            }

            else -> throw IncompatibleClassChangeError()
        }

        super.render(animatable, partialTick, poseStack, bufferSource, packedLight, packedOverlay)
        poseStack.popPose()
    }
}