package dev.hybridlabs.aquatic.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import dev.hybridlabs.aquatic.entity.misc.ArgonautEntity;
import net.minecraft.client.renderer.entity.LivingEntityRenderer;
import net.minecraft.world.entity.LivingEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntityRenderer.class)
public class LivingEntityRendererMixin<T extends LivingEntity> {

    @Inject(method = "setupRotations", at = @At("TAIL"))
    public void render(T entity, PoseStack matrices, float animationProgress, float bodyYaw, float tickDelta, CallbackInfo ci) {
        if (entity.getRootVehicle() != entity && entity.getRootVehicle() instanceof ArgonautEntity argonaut) {
            matrices.mulPose(Axis.XP.rotationDegrees(-argonaut.getViewXRot(tickDelta)));
        }
    }
}
