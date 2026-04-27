package dev.hybridlabs.aquatic.mixin.compat.carpet;

import carpet.fakes.BlockBehaviourInterface;
import dev.hybridlabs.aquatic.block.impl.StickyBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(BlockBehaviour.class)
abstract class StickyBlockCarpetHACompatMixin implements BlockBehaviourInterface {
	@Inject(
			method = "isSticky",
				   at = @At("HEAD"),
				   cancellable = true
	)
	private void isStickyHACompat(BlockState state, CallbackInfoReturnable<Boolean> cir) {
		if (state.getBlock() instanceof StickyBlock stickyBlock) {
			cir.setReturnValue(stickyBlock.isSticky());
		}
	}
	
	@Inject(
			method = "isStickyToNeighbor",
			at = @At("HEAD"),
			cancellable = true
	)
	private void isStickyToNeighborHACompat(Level level, BlockPos pos, BlockState state, BlockPos neighborPos, BlockState neighborState, Direction dir, Direction moveDir, CallbackInfoReturnable<Boolean> cir) {
		if (state.getBlock() instanceof StickyBlock stickyBlock) {
			cir.setReturnValue(stickyBlock.isStickyToNeighbor(neighborState));
		}
	}
}
