package dev.hybridlabs.aquatic.client.render.block.entity

import com.mojang.math.Axis
import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState
import net.minecraft.core.Direction
import net.minecraft.world.level.block.entity.BlockEntity
import com.geckolib.animatable.GeoAnimatable
import com.geckolib.model.GeoModel
import com.geckolib.renderer.GeoBlockRenderer
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider
import com.geckolib.renderer.base.RenderPassInfo

/**
 * Block entity renderer for models that attach to any face of a block. The models are authored
 * upright with their origin at the bottom centre, so each facing only needs an attachment
 * transform; GeckoLib has already centred the model on the block in `preRenderPass`.
 */
abstract class HADirectionalBlockEntityRenderer<T>(
    context: BlockEntityRendererProvider.Context,
    model: GeoModel<T>
) : GeoBlockRenderer<T, HABlockEntityRenderState>(context, model)
    where T : BlockEntity, T : GeoAnimatable {


    override fun createRenderState(): HABlockEntityRenderState = HABlockEntityRenderState()

    override fun adjustRenderPose(renderPassInfo: RenderPassInfo<HABlockEntityRenderState>) {
        val facing = renderPassInfo.getGeckolibData(GeoBlockRenderer.DIRECTION_FACING) ?: return
        applyFacingTransform(renderPassInfo.poseStack(), facing)
    }
}

/**
 * Pose for a model that is authored upright with its origin at the bottom centre and is attached
 * to one face of a block. GeckoLib centres the model on the block before this is applied.
 *
 * Blocks that store `BlockPlaceContext.clickedFace` (like the anemones) have `FACING` pointing
 * away from the supporting block, so the model extends along `FACING`.
 */
fun applyFacingTransform(poseStack: com.mojang.blaze3d.vertex.PoseStack, facing: Direction) {
    when (facing) {
        // Floor placement; the model is already upright and centred.
        Direction.UP -> {}
        // Ceiling placement; hang the model from the top of the block.
        Direction.DOWN -> {
            poseStack.translate(0.0, 1.0, 0.0)
            poseStack.mulPose(org.joml.Matrix4f().rotation(Axis.XP.rotationDegrees(180f)))
        }
        // Wall placements; the model extends along FACING and attaches to the opposite face.
        Direction.NORTH -> {
            poseStack.translate(0.0, 0.5, 0.5)
            poseStack.mulPose(org.joml.Matrix4f().rotation(Axis.XN.rotationDegrees(90f)))
        }
        Direction.SOUTH -> {
            poseStack.translate(0.0, 0.5, -0.5)
            poseStack.mulPose(org.joml.Matrix4f().rotation(Axis.XP.rotationDegrees(90f)))
        }
        Direction.WEST -> {
            poseStack.translate(0.5, 0.5, 0.0)
            poseStack.mulPose(org.joml.Matrix4f().rotation(Axis.ZP.rotationDegrees(90f)))
        }
        Direction.EAST -> {
            poseStack.translate(-0.5, 0.5, 0.0)
            poseStack.mulPose(org.joml.Matrix4f().rotation(Axis.ZN.rotationDegrees(90f)))
        }
    }
}
