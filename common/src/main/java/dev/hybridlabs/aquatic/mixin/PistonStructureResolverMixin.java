package dev.hybridlabs.aquatic.mixin;

import dev.hybridlabs.aquatic.Constants;
import dev.hybridlabs.aquatic.block.impl.StickyBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.piston.PistonStructureResolver;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PistonStructureResolver.class)
public class PistonStructureResolverMixin {

	@Inject(method = "isSticky", at = @At("HEAD"), cancellable = true)
	private static void changeIsSticky(BlockState state, CallbackInfoReturnable<Boolean> cir) {
		if (state.getBlock() instanceof StickyBlock block) cir.setReturnValue(block.isSticky());
	}

	@Inject(method = "canStickToEachOther", at = @At("HEAD"), cancellable = true)
	private static void addCanStickToEachOther1 (BlockState state1, BlockState state2, CallbackInfoReturnable<Boolean> cir) {
		if (state1.getBlock() instanceof StickyBlock self) {
			cir.setReturnValue(self.isStickyToNeighbor(state2));
		} else if ((state1.is(Blocks.HONEY_BLOCK) || state1.is(Blocks.SLIME_BLOCK)) && state2.getBlock() instanceof StickyBlock) {
			cir.setReturnValue(false);
		}
	}
	
}