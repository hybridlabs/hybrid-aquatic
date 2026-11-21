package dev.hybridlabs.aquatic.mixin.client;

import dev.hybridlabs.aquatic.block.PlushieBlock;
import dev.hybridlabs.aquatic.client.model.HAEntityModelLayers;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.model.object.skull.SkullModelBase;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.client.renderer.rendertype.RenderType;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.SkullBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(SkullBlockRenderer.class)
public abstract class SkullBlockEntityMixin {

    /**
     * Supplies the plushie model for plushie skull types.
     */
    @Inject(method = "createModel", at = @At("HEAD"), cancellable = true)
    private static void hybrid$createPlushieModel(
            EntityModelSet modelSet, SkullBlock.Type type, CallbackInfoReturnable<SkullModelBase> cir) {
        SkullModelBase model = HAEntityModelLayers.INSTANCE.createSkullModel(modelSet, type);

        if (model != null) {
            cir.setReturnValue(model);
        }
    }

    /**
     * Uses the plushie texture for plushie skull types.
     */
    @Inject(method = "getSkullRenderType", at = @At("HEAD"), cancellable = true)
    private static void hybrid$plushieRenderType(
            SkullBlock.Type skullType, Identifier texture, CallbackInfoReturnable<RenderType> cir) {
        if (skullType instanceof PlushieBlock.Variant variant) {
            cir.setReturnValue(RenderTypes.entityCutoutNoCullZOffset(variant.getTextureLocation()));
        }
    }
}
