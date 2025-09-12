package dev.hybridlabs.aquatic.mixin.client;

import dev.hybridlabs.aquatic.block.PlushieBlock;
import kotlin.enums.EnumEntries;
import net.minecraft.client.renderer.blockentity.SkullBlockRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.SkullBlock;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.HashMap;

@Mixin(SkullBlockRenderer.class)
public abstract class SkullBlockEntityTextureMixin {
    /**
     * Injects custom skull textures into the skull renderer.
     * We still need to do this on Forge because we don't override
     * looking up the texture in SKIN_BY_TYPE.
     */
    @Inject(method = {"lambda$static$0", "m_260765_"}, at = @At("TAIL"))
    private static void injectTextures(HashMap<SkullBlock.Type, ResourceLocation> map, CallbackInfo info) {
        // inject plushie variants
        EnumEntries<PlushieBlock.Variant> variants = PlushieBlock.Variant.getEntries();
        for (PlushieBlock.Variant variant : variants) {
            map.put(variant, variant.getTextureLocation());
        }
    }
}
