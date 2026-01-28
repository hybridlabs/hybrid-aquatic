package dev.hybridlabs.aquatic.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.hybridlabs.aquatic.Constants;
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks;
import net.minecraft.world.entity.ai.goal.BreathAirGoal;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BreathAirGoal.class)
public class BreathAirGoalMixin {
	@WrapOperation(
			method = "givesAir",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z")
	)
	private boolean decorativeBubbleColumn_givesAirParity(BlockState instance, Block block, Operation<Boolean> original) {
		return instance.is(HybridAquaticBlocks.INSTANCE.getDECORATIVE_BUBBLE_COLUMN().get()) ? block == Blocks.BUBBLE_COLUMN : original.call(instance, block);
	}
}
