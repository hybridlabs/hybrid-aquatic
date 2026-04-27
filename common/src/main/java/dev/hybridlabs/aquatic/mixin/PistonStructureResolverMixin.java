package dev.hybridlabs.aquatic.mixin;

import dev.hybridlabs.aquatic.Constants;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.piston.PistonStructureResolver;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Debug;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Debug(export = true)
@Mixin(value = PistonStructureResolver.class, priority = 800)
public class PistonStructureResolverMixin {
	// TODO: carpet incompatibility
	//  https://github.com/gnembon/fabric-carpet/blob/1.4.112/src/main/java/carpet/mixins/PistonStructureResolver_customStickyMixin.java
	
//	@Inject(method = "isSticky", at = @At("HEAD"), cancellable = true)
//	private static void changeIsSticky(BlockState state, CallbackInfoReturnable<Boolean> cir) {
//		cir.setReturnValue(!state.is(Blocks.AIR));
//	}
//
//	@Inject(method = "canStickToEachOther", at = @At("HEAD"), cancellable = true)
//	private static void addCanStickToEachOther1(BlockState state1, BlockState state2, CallbackInfoReturnable<Boolean> cir) {
//		Constants.LOG.info("test2: {} {}", state1, state2);
//		cir.setReturnValue(!state1.is(Blocks.AIR));
//	}
	
}