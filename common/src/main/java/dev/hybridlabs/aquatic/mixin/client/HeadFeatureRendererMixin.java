package dev.hybridlabs.aquatic.mixin.client;

import com.mojang.blaze3d.vertex.PoseStack;
import dev.hybridlabs.aquatic.block.PlushieBlock;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.layers.CustomHeadLayer;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CustomHeadLayer.class)
public abstract class HeadFeatureRendererMixin<T extends LivingEntity> {
    /**
     * Translates the Plushie models up when on the player's head.
     */
    @Inject(method =
           "Lnet/minecraft/client/renderer/entity/layers/CustomHeadLayer;render(Lcom/mojang/blaze3d/vertex/PoseStack;" +
                   "Lnet/minecraft/client/renderer/MultiBufferSource;ILnet/minecraft/world/entity/LivingEntity;FFFFFF)V" ,
            at = @At(value = "INVOKE", target =
                    "Lnet/minecraft/client/renderer/entity/layers/CustomHeadLayer;translateToHead" +
                            "(Lcom/mojang/blaze3d/vertex/PoseStack;Z)V",
                     ordinal = 0, shift = At.Shift.BEFORE), remap=false)
    private void translatePlushieUp(PoseStack matrices, MultiBufferSource vertices, int light, T entity,
                                    float limbAngle, float limbDistance, float tickDelta, float animationProgress,
                                    float headYaw, float headPitch, CallbackInfo ci) {
        ItemStack stack = entity.getItemBySlot(EquipmentSlot.HEAD);
        if (stack.getItem() instanceof BlockItem blockItem && blockItem.getBlock() instanceof PlushieBlock) {
            matrices.translate(0.0, 6.75 / 16.0, 0.0);
        }
    }
}
