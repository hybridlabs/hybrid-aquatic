package dev.hybridlabs.aquatic.client.render.entity.misc

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.math.Axis
import dev.hybridlabs.aquatic.entity.misc.SmallTNTEntity
import net.minecraft.client.renderer.SubmitNodeCollector
import net.minecraft.client.renderer.entity.EntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.TntMinecartRenderer
import net.minecraft.client.renderer.entity.TntRenderer
import net.minecraft.client.renderer.entity.state.TntRenderState
import net.minecraft.client.renderer.state.level.CameraRenderState
import net.minecraft.util.Mth
import net.minecraft.world.level.block.Blocks

class SmallTNTEntityRenderer(context: EntityRendererProvider.Context) :
    EntityRenderer<SmallTNTEntity, TntRenderState>(context) {

    private val blockModelResolver = context.blockModelResolver

    init {
        this.shadowRadius = 0.25f
    }

    override fun createRenderState(): TntRenderState = TntRenderState()

    override fun extractRenderState(entity: SmallTNTEntity, state: TntRenderState, partialTick: Float) {
        super.extractRenderState(entity, state, partialTick)
        state.fuseRemainingInTicks = entity.fuse - partialTick + 1.0f

    }

    override fun submit(
        state: TntRenderState,
        poseStack: PoseStack,
        nodeCollector: SubmitNodeCollector,
        cameraState: CameraRenderState
    ) {
        poseStack.pushPose()
        poseStack.translate(0.0f, 0.5f, 0.0f)

        if (state.fuseRemainingInTicks < 10.0f) {
            var f = 1.0f - state.fuseRemainingInTicks / 10.0f
            f = Mth.clamp(f, 0.0f, 1.0f)
            f *= f
            f *= f
            val f1 = 1.0f + f * 0.3f
            poseStack.scale(f1, f1, f1)
        }

        poseStack.mulPose(Axis.YP.rotationDegrees(-90.0f))
        poseStack.translate(-0.25f, -0.5f, 0.25f)
        poseStack.mulPose(Axis.YP.rotationDegrees(90.0f))
        poseStack.scale(0.5f, 0.5f, 0.5f)

        blockModelResolver.update(state.blockState, Blocks.TNT.defaultBlockState(), TntRenderer.BLOCK_DISPLAY_CONTEXT)
        TntMinecartRenderer.submitWhiteSolidBlock(
            state.blockState,
            poseStack,
            nodeCollector,
            state.lightCoords,
            state.fuseRemainingInTicks.toInt() / 5 % 2 == 0,
            state.outlineColor
        )

        poseStack.popPose()
        super.submit(state, poseStack, nodeCollector, cameraState)
    }
}
