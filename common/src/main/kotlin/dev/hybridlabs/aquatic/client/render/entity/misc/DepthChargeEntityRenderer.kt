package dev.hybridlabs.aquatic.client.render.entity.misc

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.math.Axis
import dev.hybridlabs.aquatic.block.HABlocks
import dev.hybridlabs.aquatic.entity.misc.PrimedDepthChargeEntity
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.block.BlockRenderDispatcher
import net.minecraft.client.renderer.entity.EntityRenderer
import net.minecraft.client.renderer.entity.EntityRendererProvider
import net.minecraft.client.renderer.entity.TntMinecartRenderer
import net.minecraft.client.renderer.texture.TextureAtlas
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.Mth

class DepthChargeEntityRenderer(context: EntityRendererProvider.Context) :
    EntityRenderer<PrimedDepthChargeEntity>(context) {
    private val blockRenderer: BlockRenderDispatcher

    init {
        this.shadowRadius = 0.5f
        this.blockRenderer = context.blockRenderDispatcher
    }

    override fun render(
        entity: PrimedDepthChargeEntity,
        entityYaw: Float,
        partialTicks: Float,
        poseStack: PoseStack,
        buffer: MultiBufferSource,
        packedLight: Int,
    ) {
        poseStack.pushPose()
        poseStack.translate(0.0f, 0.5f, 0.0f)
        val i = entity.fuse
        if (i.toFloat() - partialTicks + 1.0f < 10.0f) {
            var f = 1.0f - (i.toFloat() - partialTicks + 1.0f) / 10.0f
            f = Mth.clamp(f, 0.0f, 1.0f)
            f *= f
            f *= f
            val f1 = 1.0f + f * 0.3f
            poseStack.scale(f1, f1, f1)
        }

        poseStack.mulPose(Axis.YP.rotationDegrees(-90.0f))
        poseStack.translate(-0.5f, -0.5f, 0.5f)
        poseStack.mulPose(Axis.YP.rotationDegrees(90.0f))
        TntMinecartRenderer.renderWhiteSolidBlock(
            this.blockRenderer,
            HABlocks.DEPTH_CHARGE.get().defaultBlockState(),
            poseStack,
            buffer,
            packedLight,
            i / 5 % 2 == 0
        )
        poseStack.popPose()
        super.render(entity, entityYaw, partialTicks, poseStack, buffer, packedLight)
    }

    override fun getTextureLocation(p0: PrimedDepthChargeEntity): ResourceLocation {
        return TextureAtlas.LOCATION_BLOCKS
    }
}