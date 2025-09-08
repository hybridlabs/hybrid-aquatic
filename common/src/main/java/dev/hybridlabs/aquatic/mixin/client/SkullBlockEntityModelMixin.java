package dev.hybridlabs.aquatic.mixin.client;

import com.google.common.collect.ImmutableMap;
import com.llamalad7.mixinextras.sugar.Local;
import dev.hybridlabs.aquatic.block.PlushieBlock;
import dev.hybridlabs.aquatic.client.model.HybridAquaticEntityModelLayers;
import kotlin.enums.EnumEntries;
import net.minecraft.client.model.SkullModelBase;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.SkullBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.HashMap;
import java.util.Map;

@Mixin(SkullBlockRenderer.class)
public abstract class SkullBlockEntityModelMixin {
    /**
     * Injects custom skull textures into the skull renderer.
     */
    @Inject(method = "method_3580", at = @At("TAIL"))
    private static void injectTextures(HashMap<SkullBlock.Type, ResourceLocation> map, CallbackInfo info) {
        // inject plushie variants
        EnumEntries<PlushieBlock.Variant> variants = PlushieBlock.Variant.getEntries();
        for (PlushieBlock.Variant variant : variants) {
            map.put(variant, variant.getTextureLocation());
        }
    }

    /**
     * Injects custom skull models into the skull renderer.
     */
    @Inject(method = "createSkullRenderers", at = @At(value = "INVOKE", target = "Lcom/google/common/collect" +
            "/ImmutableMap$Builder;build()Lcom/google/common/collect/ImmutableMap;", shift = At.Shift.BEFORE, remap =
            false))
    private static void injectModels(EntityModelSet modelLoader, CallbackInfoReturnable<Map<SkullBlock.Type,
            SkullModelBase>> cir, @Local ImmutableMap.Builder<SkullBlock.Type, SkullModelBase> builder) {
        // inject plushie variants
        HybridAquaticEntityModelLayers.INSTANCE.injectModels(modelLoader, builder);
    }
}
