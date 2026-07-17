package dev.hybridlabs.aquatic.mixin.client;

import com.llamalad7.mixinextras.sugar.Local;
import com.mojang.blaze3d.vertex.VertexConsumer;
import dev.hybridlabs.aquatic.CommonClass;
import dev.hybridlabs.aquatic.tag.HAPaintingTags;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.PaintingRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.decoration.Painting;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;
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
	
	@ModifyArg(
			method = "render(Lnet/minecraft/world/entity/decoration/Painting;FFLcom/mojang/blaze3d/vertex/PoseStack;Lnet/minecraft/client/renderer/MultiBufferSource;I)V",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/entity/PaintingRenderer;renderPainting(Lcom/mojang/blaze3d/vertex/PoseStack;Lcom/mojang/blaze3d/vertex/VertexConsumer;Lnet/minecraft/world/entity/decoration/Painting;IILnet/minecraft/client/renderer/texture/TextureAtlasSprite;Lnet/minecraft/client/renderer/texture/TextureAtlasSprite;)V"),
			index = 6
	)
	private TextureAtlasSprite removeBackTextureIfTransparent(TextureAtlasSprite original, @Local(argsOnly = true) Painting entity) {
		var texture = Minecraft.getInstance().getPaintingTextures().getSprite(CommonClass.locate("blank"));
		return entity.getVariant().is(HAPaintingTags.INSTANCE.getTRANSPARENT_PAINTING()) ? texture : original;
	}
}
