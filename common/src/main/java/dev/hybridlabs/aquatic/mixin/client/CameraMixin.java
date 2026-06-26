package dev.hybridlabs.aquatic.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import dev.hybridlabs.aquatic.entity.misc.ArgonautEntity;
import net.minecraft.client.Camera;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.BlockGetter;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Slice;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Camera.class)
public abstract class CameraMixin {
	
	@Shadow
	private Entity entity;
	
	@Shadow
	protected abstract float getMaxZoom(float startingDistance);
	
	@Shadow
	protected abstract void move(float distanceOffset, float verticalOffset, float horizontalOffset);
	
	@Inject(
		method = "setup",
		at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Camera;setPosition(DDD)V", shift = At.Shift.AFTER)
	)
	private void changeCameraPosInArgonaut(BlockGetter level, Entity entity, boolean detached, boolean thirdPersonReverse, float partialTick, CallbackInfo ci) {
		if (!detached && entity.getRootVehicle() != entity && entity.getRootVehicle() instanceof ArgonautEntity argonaut) {
			var argonautRotationRadX = Math.toRadians(argonaut.getRotationVector().x);
			this.move((float)(argonautRotationRadX * 0.25), (float)(Math.abs(argonautRotationRadX * 0.25)), 0.0f);
		}
	}
	
	@ModifyArg(
		method = "setup",
		at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Camera;getMaxZoom(F)F"), index = 0
	)
	private float changeCameraDistance(float original) {
		return entity.getRootVehicle() != entity && entity.getRootVehicle() instanceof ArgonautEntity ? 7.5f : original;
	}
	
	@ModifyExpressionValue(
		method = "setup",
		slice = @Slice(
			from = @At(value = "INVOKE", target = "Lnet/minecraft/client/Camera;getMaxZoom(F)F", ordinal = 0),
			to = @At(value = "TAIL")),
		at = @At(value = "CONSTANT", args = "floatValue=0.0", ordinal = 0)
	)
	private float changeCameraHeight(float original) {
		return entity.getRootVehicle() != entity && entity.getRootVehicle() instanceof ArgonautEntity ? getMaxZoom(3.0f) : original;
	}
}
