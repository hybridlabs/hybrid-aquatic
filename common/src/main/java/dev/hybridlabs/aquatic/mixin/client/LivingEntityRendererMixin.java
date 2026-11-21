package dev.hybridlabs.aquatic.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import dev.hybridlabs.aquatic.client.render.ArgonautPassengerRenderState;
import dev.hybridlabs.aquatic.entity.misc.ArgonautEntity;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.client.renderer.state.CameraRenderState;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntityRenderer.class)
public class LivingEntityRendererMixin {

    /**
     * Captures the Argonaut's view pitch on the render state and drops the vanilla head pitch, since
     * the whole model is tilted instead.
     */
    @Inject(method = "extractRenderState", at = @At("TAIL"))
    private void hybrid$storeArgonautPitch(
            LivingEntity entity, LivingEntityRenderState renderState, float partialTick, CallbackInfo ci) {
        if (entity.getRootVehicle() != entity && entity.getRootVehicle() instanceof ArgonautEntity argonaut) {
            ((ArgonautPassengerRenderState) renderState).setArgonautPitch(argonaut.getViewXRot(partialTick));
            renderState.xRot = 0.0F;
        }
    }

    /**
     * Tilts a passenger's model with the Argonaut, matching how the vehicle itself is pitched.
     */
    @Inject(
            method = "submit",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/renderer/entity/LivingEntityRenderer;setupRotations(" +
                            "Lnet/minecraft/client/renderer/entity/state/LivingEntityRenderState;" +
                            "Lcom/mojang/blaze3d/vertex/PoseStack;FF)V",
                    shift = At.Shift.AFTER))
    private void hybrid$rotateInArgonaut(
            LivingEntityRenderState renderState, PoseStack poseStack, SubmitNodeCollector nodeCollector,
            CameraRenderState cameraState, CallbackInfo ci) {
        float pitch = ((ArgonautPassengerRenderState) renderState).getArgonautPitch();

        if (!Float.isNaN(pitch)) {
            poseStack.scale(-1.0F, -1.0F, 1.0F);
            poseStack.mulPose(Axis.XP.rotationDegrees(-pitch));
            poseStack.scale(-1.0F, -1.0F, 1.0F);
        }
    }
}
