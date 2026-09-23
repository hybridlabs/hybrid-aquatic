package dev.hybridlabs.aquatic.mixin;

import dev.hybridlabs.aquatic.world.gen.densityfunction.DensityFunctionWrappers;
import net.minecraft.server.MinecraftServer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftServer.class)
public abstract class MinecraftServerMixin {
    // Levels build their noise routers from the density function registry, so wrappers must be bound first
    @Inject(method = "loadLevel", at = @At("HEAD"))
    private void wrapDensityFunctions(CallbackInfo ci) {
        DensityFunctionWrappers.apply((MinecraftServer) (Object) this);
    }
}
