package dev.hybridlabs.aquatic.mixin;

import dev.hybridlabs.aquatic.block.HABlocks;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.piston.PistonStructureResolver;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PistonStructureResolver.class)
public class PistonStructureResolverMixin {

    private static final Block HAGSLIME = HABlocks.INSTANCE.getHAGSLIME_BLOCK().get();
    private static final Block GLOWSLIME = HABlocks.INSTANCE.getGLOWSLIME_BLOCK().get();

    @Inject(method = "isSticky", at = @At("RETURN"), cancellable = true)
    private static void ha$addSticky(BlockState state, CallbackInfoReturnable<Boolean> cir) {
        if (cir.getReturnValueZ()) return;

        Block block = state.getBlock();

        if (block == HAGSLIME || block == GLOWSLIME) {
            cir.setReturnValue(true);
        }
    }

    @Inject(method = "canStickToEachOther", at = @At("HEAD"), cancellable = true)
    private static void ha$canStickToEachOther(BlockState state1, BlockState state2, CallbackInfoReturnable<Boolean> cir) {

        if (state1.is(GLOWSLIME) && state2.is(Blocks.SLIME_BLOCK)) {
            cir.setReturnValue(false);
        } else if (state1.is(GLOWSLIME) && state2.is(Blocks.HONEY_BLOCK)) {
            cir.setReturnValue(false);
        } else if (state1.is(GLOWSLIME) && state2.is(HAGSLIME)) {
            cir.setReturnValue(false);
        } else if (state1.is(HAGSLIME) && state2.is(Blocks.SLIME_BLOCK)) {
            cir.setReturnValue(false);
        } else if (state1.is(HAGSLIME) && state2.is(Blocks.HONEY_BLOCK)) {
            cir.setReturnValue(false);
        }
    }
}