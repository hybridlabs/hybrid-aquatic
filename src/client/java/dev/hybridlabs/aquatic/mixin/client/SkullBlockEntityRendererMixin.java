package dev.hybridlabs.aquatic.mixin.client;

import com.google.common.collect.ImmutableMap;
import dev.hybridlabs.aquatic.block.PlushieBlock;
import dev.hybridlabs.aquatic.client.model.HybridAquaticEntityModelLayers;
import kotlin.enums.EnumEntries;
import net.minecraft.block.SkullBlock;
import net.minecraft.client.render.block.entity.SkullBlockEntityModel;
import net.minecraft.client.render.block.entity.SkullBlockEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLoader;
import net.minecraft.util.Identifier;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;

import java.util.HashMap;
import java.util.Map;

@Mixin(SkullBlockEntityRenderer.class)
public class SkullBlockEntityRendererMixin {
    /**
     * Injects custom skull textures into the skull renderer.
     * @implNote {@code method_3580} is the anonymous method that creates the {@code TEXTURES} map
     */
    @Inject(method = "method_3580", at = @At("TAIL"))
    private static void injectTextures(HashMap<SkullBlock.SkullType, Identifier> map, CallbackInfo info) {
        // inject plushie variants
        EnumEntries<PlushieBlock.Variant> variants = PlushieBlock.Variant.getEntries();
        for (PlushieBlock.Variant variant : variants) {
            map.put(variant, variant.getTextureLocation());
        }
    }

    /**
     * Injects custom skull models into the skull renderer.
     */
    @Inject(
            method = "getModels",
            at = @At(
                    value = "INVOKE",
                    target = "Lcom/google/common/collect/ImmutableMap$Builder;build()Lcom/google/common/collect/ImmutableMap;",
                    shift = At.Shift.BEFORE,
                    remap = false
            ),
            locals = LocalCapture.CAPTURE_FAILEXCEPTION
    )
    private static void injectModels(EntityModelLoader modelLoader, CallbackInfoReturnable<Map<SkullBlock.SkullType, SkullBlockEntityModel>> cir, ImmutableMap.Builder<SkullBlock.SkullType, SkullBlockEntityModel> builder) {
        // inject plushie variants
        HybridAquaticEntityModelLayers.INSTANCE.injectModels(modelLoader, builder);
    }
}
