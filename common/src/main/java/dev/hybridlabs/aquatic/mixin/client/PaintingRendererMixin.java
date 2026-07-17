package dev.hybridlabs.aquatic.mixin.client;

import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.hybridlabs.aquatic.tag.HAPaintingTags;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.PaintingRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.decoration.Painting;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(PaintingRenderer.class)
@Debug(export = true)
public abstract class PaintingRendererMixin {
	@Shadow
	public abstract ResourceLocation getTextureLocation(Entity par1);
	
	@ModifyVariable(
			method = "render(Lnet/minecraft/world/entity/decoration/Painting;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
			at = @At(value = "STORE"),
			ordinal = 0
	)
	private VertexConsumer changeRenderType(VertexConsumer original, @Local(argsOnly = true) Painting entity, @Local(argsOnly = true) MultiBufferSource buffer) {
		if (!entity.getVariant().is(HAPaintingTags.INSTANCE.getTRANSPARENT_PAINTING())) return original;
		
		return buffer.getBuffer(RenderType.entityTranslucent(getTextureLocation(entity)));
	}
}
