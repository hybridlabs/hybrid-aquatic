package dev.hybridlabs.aquatic.mixin.client;

import dev.hybridlabs.aquatic.fog.ClarityFogModifier;
import dev.hybridlabs.aquatic.fog.ThalassophobiaFogModifier;
import net.minecraft.client.renderer.fog.FogRenderer;
import net.minecraft.client.renderer.fog.environment.FogEnvironment;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

/**
 * Adds Hybrid Aquatic's mob effect fogs to vanilla's fixed list of fog environments.
 */
@Mixin(FogRenderer.class)
public class FogRendererMixin {

    @Shadow
    @Final
    private static List<FogEnvironment> FOG_ENVIRONMENTS;

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void hybrid$registerFogEnvironments(CallbackInfo ci) {
        FOG_ENVIRONMENTS.add(new ClarityFogModifier());
        FOG_ENVIRONMENTS.add(new ThalassophobiaFogModifier());
    }
}
