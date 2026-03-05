package dev.hybridlabs.aquatic.client.render.entity.crustacean.layer

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import dev.hybridlabs.aquatic.client.render.entity.crustacean.HybridAquaticCrustaceanEntityRenderer
import dev.hybridlabs.aquatic.entity.crustacean.HybridAquaticCrustaceanEntity
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.texture.OverlayTexture
import net.minecraft.world.item.ItemDisplayContext
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import software.bernie.geckolib.cache.`object`.BakedGeoModel
import software.bernie.geckolib.renderer.layer.GeoRenderLayer

class HybridAquaticShellLayer<T: HybridAquaticCrustaceanEntity>(
    renderer: HybridAquaticCrustaceanEntityRenderer<T>,
) : GeoRenderLayer<T>(renderer) {
    override fun render(
        poseStack: PoseStack,
        animatable: T,
        bakedModel: BakedGeoModel,
        renderType: RenderType,
        bufferSource: MultiBufferSource,
        buffer: VertexConsumer,
        partialTick: Float,
        packedLight: Int,
        packedOverlay: Int
    ) {
        val itemRenderer = Minecraft.getInstance().itemRenderer
        poseStack.pushPose()
        poseStack.translate(0.0, 0.0, 0.0)
        itemRenderer.renderStatic(ItemStack(Items.STONE), ItemDisplayContext.FIXED, packedLight,
            OverlayTexture.NO_OVERLAY, poseStack, bufferSource, animatable.level(), animatable.id)
        poseStack.popPose()

        super.render(poseStack, animatable, bakedModel, renderType, bufferSource, buffer, partialTick, packedLight, packedOverlay)
    }
}