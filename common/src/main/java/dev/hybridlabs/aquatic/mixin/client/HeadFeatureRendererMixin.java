package dev.hybridlabs.aquatic.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.hybridlabs.aquatic.block.PlushieBlock;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CustomHeadLayer.class)
public abstract class HeadFeatureRendererMixin {

    /**
     * Translates plushie models up so they sit correctly on the player's head.
     */
    @Inject(
            method = "submit",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/model/HeadedModel;translateToHead(Lcom/mojang/blaze3d/vertex/PoseStack;)V",
                    shift = At.Shift.AFTER))
    private void hybrid$translatePlushieUp(
            PoseStack poseStack, SubmitNodeCollector nodeCollector, int light, LivingEntityRenderState renderState,
            float limbAngle, float limbDistance, CallbackInfo ci) {
        if (renderState.wornHeadType instanceof PlushieBlock.Variant) {
            poseStack.translate(0.0, 6.75 / 16.0, 0.0);
        }
    }
}
