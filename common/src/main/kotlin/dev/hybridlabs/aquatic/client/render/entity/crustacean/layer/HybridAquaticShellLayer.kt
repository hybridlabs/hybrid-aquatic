package dev.hybridlabs.aquatic.client.render.entity.crustacean.layer

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import com.mojang.math.Axis
import dev.hybridlabs.aquatic.client.render.entity.crustacean.HACrustaceanEntityRenderer
import dev.hybridlabs.aquatic.entity.crustacean.HACrustaceanEntity
import dev.hybridlabs.aquatic.item.HAAquaticItems
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.RenderType
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemDisplayContext
import software.bernie.geckolib.cache.`object`.GeoBone
import software.bernie.geckolib.renderer.layer.GeoRenderLayer

class HybridAquaticShellLayer<T: HACrustaceanEntity>(
    renderer: HACrustaceanEntityRenderer<T>,
    val defaultItem: Item
) : GeoRenderLayer<T>(renderer) {

    override fun renderForBone(
        poseStack: PoseStack,
        animatable: T,
        bone: GeoBone,
        renderType: RenderType,
        bufferSource: MultiBufferSource,
        buffer: VertexConsumer,
        partialTick: Float,
        packedLight: Int,
        packedOverlay: Int
    ) {
        super.renderForBone(poseStack, animatable, bone, renderType, bufferSource, buffer, partialTick, packedLight, packedOverlay)
        if (!bone.name.equals("shell")) return
        if (animatable.shellItem.`is`(defaultItem) ||
            animatable.shellItem.`is`(HAAquaticItems.OMINOUS_CONCH.get())
        ) return

        val itemRenderer = Minecraft.getInstance().itemRenderer

        poseStack.pushPose()
        poseStack.rotateAround(Axis.XP.rotationDegrees(32.5f), 0.0F, 0.0F, 0.0F)
        poseStack.translate(0.0, 0.25, -0.1)

        itemRenderer.renderStatic(
            animatable.shellItem,
            ItemDisplayContext.FIXED,
            packedLight,
            packedOverlay,
            poseStack, bufferSource, animatable.level(), animatable.id)
        
        poseStack.popPose()
        bufferSource.getBuffer(renderType)
    }
}