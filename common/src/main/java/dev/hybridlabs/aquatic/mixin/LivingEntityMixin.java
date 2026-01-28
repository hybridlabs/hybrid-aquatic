package dev.hybridlabs.aquatic.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.hybridlabs.aquatic.Constants;
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(LivingEntity.class)
public class LivingEntityMixin {
	@WrapOperation(
			method = "baseTick",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z"),
			slice = @Slice(
					from = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;isEyeInFluid(Lnet/minecraft/tags/TagKey;)Z"),
					to = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/LivingEntity;canBreatheUnderwater()Z"))
	)
	private boolean decorativeBubbleColumn_baseTickParity(BlockState instance, Block block, Operation<Boolean> original) {
		return instance.is(HybridAquaticBlocks.INSTANCE.getDECORATIVE_BUBBLE_COLUMN().get()) ? block == Blocks.BUBBLE_COLUMN : original.call(instance, block);
	}
}
