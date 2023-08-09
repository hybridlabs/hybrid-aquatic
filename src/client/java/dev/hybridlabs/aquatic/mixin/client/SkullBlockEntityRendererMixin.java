package dev.hybridlabs.aquatic.mixin.client;

import com.google.common.collect.ImmutableMap;
import dev.hybridlabs.aquatic.HybridAquatic;
import dev.hybridlabs.aquatic.block.BlahajPlushieBlock;
import dev.hybridlabs.aquatic.client.model.HybridAquaticEntityModelLayers;
import dev.hybridlabs.aquatic.client.model.block.entity.blahaj_plushie.*;
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
        // inject blahaj plushie variants
        BlahajPlushieBlock.Variant[] variants = BlahajPlushieBlock.Variant.values();
        for (BlahajPlushieBlock.Variant variant : variants) {
            String id = variant.getId();
            map.put(variant, new Identifier(HybridAquatic.MOD_ID, "textures/entity/block/blahaj_plushie/%s_blahaj_plushie.png".formatted(id)));
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
        // inject blahaj plushie variants
        HybridAquaticEntityModelLayers layers = HybridAquaticEntityModelLayers.INSTANCE;
        builder.put(BlahajPlushieBlock.Variant.BASKING_SHARK, new BaskingSharkBlahajPlushieModel(modelLoader.getModelPart(layers.getBASKING_SHARK_BLAHAJ_PLUSHIE())));
        builder.put(BlahajPlushieBlock.Variant.BULL_SHARK, new BullSharkBlahajPlushieModel(modelLoader.getModelPart(layers.getBULL_SHARK_BLAHAJ_PLUSHIE())));
        builder.put(BlahajPlushieBlock.Variant.FRILLED_SHARK, new FrilledSharkBlahajPlushieModel(modelLoader.getModelPart(layers.getFRILLED_SHARK_BLAHAJ_PLUSHIE())));
        builder.put(BlahajPlushieBlock.Variant.GREAT_WHITE_SHARK, new GreatWhiteSharkBlahajPlushieModel(modelLoader.getModelPart(layers.getGREAT_WHITE_SHARK_BLAHAJ_PLUSHIE())));
        builder.put(BlahajPlushieBlock.Variant.HAMMERHEAD_SHARK, new HammerheadSharkBlahajPlushieModel(modelLoader.getModelPart(layers.getHAMMERHEAD_SHARK_BLAHAJ_PLUSHIE())));
        builder.put(BlahajPlushieBlock.Variant.THRESHER_SHARK, new ThresherSharkBlahajPlushieModel(modelLoader.getModelPart(layers.getTHRESHER_SHARK_BLAHAJ_PLUSHIE())));
        builder.put(BlahajPlushieBlock.Variant.TIGER_SHARK, new TigerSharkBlahajPlushieModel(modelLoader.getModelPart(layers.getTIGER_SHARK_BLAHAJ_PLUSHIE())));
        builder.put(BlahajPlushieBlock.Variant.WHALE_SHARK, new WhaleSharkBlahajPlushieModel(modelLoader.getModelPart(layers.getWHALE_SHARK_BLAHAJ_PLUSHIE())));
    }
}
