package dev.hybridlabs.aquatic.mixin.compat;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Lithostitched 1.7.2 iterates its parameter map while writing the remapped keys back into the same
 * map, which throws a {@link java.util.ConcurrentModificationException} on the first biome injector
 * with climate parameters. Iterating over a snapshot keeps the behaviour and avoids the crash.
 */
@Mixin(targets = "dev.worldgen.lithostitched.impl.worldgen.biomeinjector.internal.ParameterMap", remap = false)
public class LithostitchedParameterMapMixin {

    @Redirect(
            method = "mapAll",
            at = @At(value = "INVOKE", target = "Ljava/util/Map;entrySet()Ljava/util/Set;"),
            remap = false)
    private Set<?> hybridAquatic$snapshotEntries(Map<?, ?> parameters) {
        return new HashSet<>(parameters.entrySet());
    }
}
