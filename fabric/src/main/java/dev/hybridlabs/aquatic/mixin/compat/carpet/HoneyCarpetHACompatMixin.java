package dev.hybridlabs.aquatic.mixin.compat.carpet;

import carpet.fakes.BlockBehaviourInterface;
import dev.hybridlabs.aquatic.block.impl.StickyBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.HoneyBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(HoneyBlock.class)
abstract class HoneyCarpetHACompatMixin implements BlockBehaviourInterface {
	
	@Inject(
			method = "isStickyToNeighbor",
			at = @At("HEAD"),
			cancellable = true
	)
	private void isStickyToNeighborHACompat(Level level, BlockPos pos, BlockState state, BlockPos neighborPos, BlockState neighborState, Direction dir, Direction moveDir, CallbackInfoReturnable<Boolean> cir) {
		if (neighborState.getBlock() instanceof StickyBlock) {
			cir.setReturnValue(false);
		}
	}
}
