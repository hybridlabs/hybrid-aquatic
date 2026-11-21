package dev.hybridlabs.aquatic.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.hybridlabs.aquatic.block.HABlocks;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FlowingFluid;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FlowingFluid.class)
public class FlowingFluidMixin {
	@WrapOperation(
			method = "canHoldAnyFluid",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/tags/TagKey;)Z")
	)
	private static boolean decorativeBubbleColumn_givesAirParity(BlockState instance, TagKey<Block> tag, Operation<Boolean> original) {
		return instance.is(HABlocks.INSTANCE.getDECORATIVE_BUBBLE_COLUMN().get())
				? Blocks.BUBBLE_COLUMN.defaultBlockState().is(tag)
				: original.call(instance, tag);
	}
}
