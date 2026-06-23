package dev.hybridlabs.aquatic.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.hybridlabs.aquatic.block.HABlocks;
import dev.hybridlabs.aquatic.effect.HAMobEffects;
import dev.hybridlabs.aquatic.item.HAItems;
import dev.hybridlabs.aquatic.tag.HAFluidTags;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.FluidState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Entity.class)
public class EntityMixin {
    @ModifyReturnValue(method = "maxUpStep", at = @At("RETURN"))
    private float onGetStepHeight(float original) {
        // Allows player to walk in the water without jumping
        Entity entity = (Entity) (Object) this;
        if (entity instanceof Player player) {
            ItemStack stack = player.getItemBySlot(EquipmentSlot.FEET);
            var isDivingBoots =
                    stack.is(HAItems.INSTANCE.getDIVING_BOOTS().get()) ||
                    stack.is(HAItems.INSTANCE.getREINFORCED_DIVING_BOOTS().get()) ||
                    stack.is(HAItems.INSTANCE.getGLOWING_DIVING_BOOTS().get());
            if (isDivingBoots && player.isEyeInFluid(FluidTags.WATER)) {
                return original * 1.67f;
            }
        }

        return original;
    }

    @WrapOperation(
            method = "getBlockSpeedFactor",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z")
    )
    private boolean decorativeBubbleColumn_getBlockSpeedFactorParity(BlockState instance, Block block, Operation<Boolean> original) {
        return instance.is(HABlocks.INSTANCE.getDECORATIVE_BUBBLE_COLUMN().get()) ? block == Blocks.BUBBLE_COLUMN : original.call(instance, block);
    }

    @WrapOperation(
            method = "isInBubbleColumn",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z")
    )
    private boolean decorativeBubbleColumn_isInBubbleColumnParity(BlockState instance, Block block, Operation<Boolean> original) {
        return instance.is(HABlocks.INSTANCE.getDECORATIVE_BUBBLE_COLUMN().get()) ? block == Blocks.BUBBLE_COLUMN : original.call(instance, block);
    }

    @Inject(
            method = "updateFluidHeightAndDoFluidPushing",
            at = @At("HEAD")
    )

    private void hybridAquatic$applyBrineEffects(TagKey<Fluid> fluidTag, double motionScale, CallbackInfoReturnable<Boolean> cir) {
        Entity self = (Entity) (Object) this;

        if (!(self instanceof LivingEntity entity)) {
            return;
        }

        BlockPos pos = self.blockPosition();
        FluidState state = self.level().getFluidState(pos);

        if (state.isEmpty()) {
            return;
        }

        if (state.is(HAFluidTags.INSTANCE.getBRINE())) {

                //with reinforced diving suit
            if (isWearingReinforcedDivingSet(entity)) {
                entity.addEffect(new MobEffectInstance(
                        HAMobEffects.INSTANCE.getCORROSION().asHolder(),
                        120,
                        0
                ));

                //with normal diving suit
            } else if (isWearingDivingSet(entity)) {
                entity.addEffect(new MobEffectInstance(
                        MobEffects.POISON,
                        120,
                        0
                ));

                entity.addEffect(new MobEffectInstance(
                        HAMobEffects.INSTANCE.getCORROSION().asHolder(),
                        120,
                        0
                ));

                //without diving suit
            } else {
                entity.addEffect(new MobEffectInstance(
                        MobEffects.POISON,
                        120,
                        1
                ));

                entity.addEffect(new MobEffectInstance(
                        HAMobEffects.INSTANCE.getCORROSION().asHolder(),
                        120,
                        0
                ));
            }
        }
    }

    @Unique
    private boolean isWearingDivingSet(LivingEntity entity) {
        ItemStack helmet = entity.getItemBySlot(EquipmentSlot.HEAD);
        ItemStack chest = entity.getItemBySlot(EquipmentSlot.CHEST);
        ItemStack legs = entity.getItemBySlot(EquipmentSlot.LEGS);
        ItemStack boots = entity.getItemBySlot(EquipmentSlot.FEET);

        return helmet.is(HAItems.INSTANCE.getDIVING_HELMET().get())
                && chest.is(HAItems.INSTANCE.getDIVING_SUIT().get())
                && legs.is(HAItems.INSTANCE.getDIVING_LEGGINGS().get())
                && boots.is(HAItems.INSTANCE.getDIVING_BOOTS().get());
    }

    @Unique
    private boolean isWearingReinforcedDivingSet(LivingEntity entity) {
        ItemStack helmet = entity.getItemBySlot(EquipmentSlot.HEAD);
        ItemStack chest = entity.getItemBySlot(EquipmentSlot.CHEST);
        ItemStack legs = entity.getItemBySlot(EquipmentSlot.LEGS);
        ItemStack boots = entity.getItemBySlot(EquipmentSlot.FEET);

        return helmet.is(HAItems.INSTANCE.getREINFORCED_DIVING_HELMET().get())
                && chest.is(HAItems.INSTANCE.getREINFORCED_DIVING_SUIT().get())
                && legs.is(HAItems.INSTANCE.getREINFORCED_DIVING_LEGGINGS().get())
                && boots.is(HAItems.INSTANCE.getREINFORCED_DIVING_BOOTS().get());
    }
}
