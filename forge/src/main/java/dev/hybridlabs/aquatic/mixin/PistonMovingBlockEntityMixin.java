package dev.hybridlabs.aquatic.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.hybridlabs.aquatic.block.HAPlatformBlocks;
import net.minecraft.world.level.block.piston.PistonMovingBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PistonMovingBlockEntity.class)
public class PistonMovingBlockEntityMixin {

    @Shadow
    private BlockState movedState;

    @ModifyReturnValue(method = "isStickyForEntities", at = @At("RETURN"))
    private boolean addHAStickiness(boolean original) {
        return movedState.is(HAPlatformBlocks.INSTANCE.getHAGSLIME_BLOCK().get()) || original;
    }
}
