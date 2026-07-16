package dev.hybridlabs.aquatic.mixin.client

import com.mojang.blaze3d.vertex.PoseStack
import com.mojang.blaze3d.vertex.VertexConsumer
import net.minecraft.client.renderer.MultiBufferSource
import net.minecraft.client.renderer.RenderType
import net.minecraft.client.renderer.entity.PaintingRenderer
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.decoration.Painting
import org.spongepowered.asm.mixin.Mixin
import org.spongepowered.asm.mixin.Shadow
import org.spongepowered.asm.mixin.injection.At
import org.spongepowered.asm.mixin.injection.ModifyVariable

@Mixin(PaintingRenderer::class)
abstract class PaintingTransparentMixin {
    @Shadow
    abstract fun getTextureLocation(painting: Painting?): ResourceLocation?

    @ModifyVariable(
        method = ["render(Lnet/minecraft/world/entity/decoration/Painting;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V"],
        at = At("STORE"),
        ordinal = 0
    )
    private fun injected(
        original: VertexConsumer?,
        painting: Painting?,
        yaw: Float,
        partialTick: Float,
        poseStack: PoseStack?,
        buffer: MultiBufferSource,
        packedLight: Int,
    ): VertexConsumer {
        return buffer.getBuffer(RenderType.entityTranslucent(getTextureLocation(painting)))
    }
}