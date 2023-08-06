package dev.hybridlabs.aquatic.mixin;

import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractFireBlock.class)
public class AbstractFireBlockMixin {
    @Inject(method = "getState", at = @At("HEAD"), cancellable = true)
    private static void onGetState(BlockView world, BlockPos pos, CallbackInfoReturnable<BlockState> cir) {
        // when on coral block, set fire to acacia fence
        if (world.getBlockState(pos.down()).isIn(BlockTags.CORAL_BLOCKS)) {
            cir.setReturnValue(Blocks.ACACIA_FENCE.getDefaultState());
        }
    }
}
