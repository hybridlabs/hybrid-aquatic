package dev.hybridlabs.aquatic.mixin;

import dev.hybridlabs.aquatic.Constants;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
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

    @Mutable
    @Shadow
    @Final
    private String path;

    @Unique
    private static final Logger LOGGER = LoggerFactory.getLogger("ResourceLocation-DFU-Hybrid");

    @Inject(method = "<init>(Ljava/lang/String;Ljava/lang/String;)V", at = @At("TAIL"))
    private void onInit(String namespace, String path, CallbackInfo ci) {
        // because DFU can suck my-
        if (Objects.equals(namespace, "hybrid-aquatic")) {
            this.namespace = Constants.MOD_ID;
        }

        // DATAFIX hybrid aquatic
        if (Objects.equals(this.namespace, Constants.MOD_ID)) {
            ResourceLocation newLoc = processPath(path);
            if (newLoc != null) {
                LOGGER.info("Datafixing {}:{} to {}", namespace, path, newLoc);

                this.namespace = newLoc.getNamespace();
                this.path = newLoc.getPath();
            }
        }
    }

    @Unique
    private ResourceLocation processPath(@NotNull String path) {
        // FISHING NET -> CREATURE NET
        if (path.equals("fishing_net")) return ResourceLocation.tryBuild(dev.hybridlabs.hapi.Constants.MOD_ID, "creature_net");

        return null;
    }
}
