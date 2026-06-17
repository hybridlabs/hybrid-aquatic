package dev.hybridlabs.aquatic.mixin;

import net.minecraft.resources.ResourceLocation;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Objects;

@Mixin(ResourceLocation.class)
public class ResourceLocationMixin {
    @Mutable
    @Shadow
    @Final
    private String namespace;

    @Inject(method = "<init>(Ljava/lang/String;Ljava/lang/String;)V", at = @At("TAIL"))
    private void onInit(String namespace, String path, CallbackInfo ci) {
        // because DFU can suck my-
        if (Objects.equals(namespace, "hybrid-aquatic")) {
            this.namespace = "hybrid_aquatic";
        }
    }
}