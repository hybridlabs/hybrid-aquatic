package dev.hybridlabs.aquatic.mixin.client;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import dev.hybridlabs.aquatic.entity.misc.ArgonautEntity;
import net.minecraft.client.Camera;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(Camera.class)
public abstract class CameraMixin {
	
	@Shadow
	private Entity entity;
	
	@Shadow
	protected abstract double getMaxZoom(double startingDistance);
	
	@ModifyArg(
		method = "setup",
		at = @At(value = "INVOKE", target = "Lnet/minecraft/client/Camera;getMaxZoom(D)D"), index = 0
	)
	private double changeCameraDistance(double original) {
		return entity.getRootVehicle() != entity && entity.getRootVehicle() instanceof ArgonautEntity ? 7.5d : original;
	}
	
	@ModifyExpressionValue(
		method = "setup",
		slice = @Slice(
			from = @At(value = "INVOKE", target = "Lnet/minecraft/client/Camera;getMaxZoom(D)D", ordinal = 0),
			to = @At(value = "TAIL")),
		at = @At(value = "CONSTANT", args = "doubleValue=0.0", ordinal = 0)
	)
	private double changeCameraHeight(double original) {
		return entity.getRootVehicle() != entity && entity.getRootVehicle() instanceof ArgonautEntity ? getMaxZoom(3.0) : original;
	}
}
