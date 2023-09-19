package dev.hybridlabs.aquatic.mixin.client;

import net.minecraft.client.render.entity.FishingBobberEntityRenderer;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(FishingBobberEntityRenderer.class)
public class FishingBobberEntityRendererMixin {
  //TODO: I don't know if we want to make lure render on the bobber
  //      Also I cant do renderers and math for them
}
