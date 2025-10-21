package dev.hybridlabs.aquatic.client.render.block.entity

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.math.Axis
import dev.hybridlabs.aquatic.block.entity.StrawberryAnemoneBlockEntity
import dev.hybridlabs.aquatic.client.model.block.entity.StrawberryAnemoneBlockEntityModel
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider.Context
import net.minecraft.core.Direction
import net.minecraft.world.level.block.HorizontalDirectionalBlock
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.properties.AttachFace
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import software.bernie.geckolib.renderer.GeoBlockRenderer

class StrawberryAnemoneBlockEntityRenderer(context: Context) :
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
        val facing = state.getValue(HorizontalDirectionalBlock.FACING)
        val face = state.getValue(BlockStateProperties.ATTACH_FACE)

        if (face == AttachFace.WALL) {
            val yaw = when (facing) {
                Direction.NORTH -> 180f
                Direction.SOUTH -> 0f
                Direction.WEST -> -90f
                Direction.EAST -> 90f
                else -> 0f
            }
            poseStack.mulPose(Axis.YP.rotationDegrees(yaw))
        }

        poseStack.pushPose()

        when (face) {
            AttachFace.CEILING -> {
                poseStack.mulPose(Axis.XP.rotationDegrees(180f))
                poseStack.translate(0.0, -1.0, -1.0)
            }

            AttachFace.WALL -> {
                poseStack.mulPose(Axis.XP.rotationDegrees(90f))
                if (facing == Direction.NORTH) {
                    poseStack.translate(-1.0, -1.0, -1.0)
                }
                if (facing == Direction.SOUTH) {
                    poseStack.translate(0.0, 0.0, -1.0)
                }
                if (facing == Direction.EAST) {
                    poseStack.translate(-1.0, 0.0, -1.0)
                }
                if (facing == Direction.WEST) {
                    poseStack.translate(0.0, -1.0, -1.0)
                }
            }

            AttachFace.FLOOR -> {
                poseStack.mulPose(Axis.XP.rotationDegrees(0f))
            }
        }

        super.render(animatable, partialTick, poseStack, bufferSource, packedLight, packedOverlay)
        poseStack.popPose()
    }
}