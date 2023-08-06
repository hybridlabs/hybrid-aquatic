package dev.hybridlabs.aquatic.mixin.client;

import dev.hybridlabs.aquatic.block.BlahajPlushieBlock;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.feature.HeadFeatureRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HeadFeatureRenderer.class)
public class HeadFeatureRendererMixin<T extends LivingEntity> {
    /**
     * Translates the Blahaj Plushie models up when on the player's head.
     * @implNote The wonderful underlying Mojank code makes it so that
     *           we can't use the display feature in the item model
     */
    @Inject(
            method = "render(Lnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;ILnet/minecraft/entity/LivingEntity;FFFFFF)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/util/math/MatrixStack;translate(DDD)V",
                    ordinal = 0,
                    shift = At.Shift.BEFORE
            )
    )
    private void translateBlahajPlushieUp(MatrixStack matrices, VertexConsumerProvider vertices, int light, T entity, float limbAngle, float limbDistance, float tickDelta, float animationProgress, float headYaw, float headPitch, CallbackInfo ci) {
        ItemStack stack = entity.getEquippedStack(EquipmentSlot.HEAD);
        if (stack.getItem() instanceof BlockItem blockItem && blockItem.getBlock() instanceof BlahajPlushieBlock) {
            matrices.translate(0.0, 6.75 / 16.0, 0.0);
        }
    }
}
