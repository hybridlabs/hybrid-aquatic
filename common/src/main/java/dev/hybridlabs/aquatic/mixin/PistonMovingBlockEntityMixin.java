package dev.hybridlabs.aquatic.mixin;

import dev.hybridlabs.aquatic.block.HABlocks;
import net.minecraft.world.level.block.piston.PistonMovingBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PistonMovingBlockEntity.class)
public class PistonMovingBlockEntityMixin {

    @Shadow
    private BlockState movedState;

    @Inject(method = "isStickyForEntities", at = @At("RETURN"), cancellable = true)
    private void ha$includeCustomSticky(CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValueZ()) {
            return;
        }

        // extend sticky behavior
        if (this.movedState.is(HABlocks.INSTANCE.getHAGSLIME_BLOCK().get())) {
            cir.setReturnValue(true);
        }
    }
}
