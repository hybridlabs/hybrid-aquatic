package dev.hybridlabs.aquatic.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.hybridlabs.aquatic.tag.HABlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.piston.PistonMovingBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PistonMovingBlockEntity.class)
public class FabricPistonMovingBlockEntityMixin {
	@WrapOperation(
			method = "moveCollidedEntities",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z"
			))
	private static boolean addCustomSlimeBlocks(BlockState blockState, Block block, Operation<Boolean> original) {
		return blockState.is(HABlockTags.INSTANCE.getIS_SLIMELIKE()) || original.call(blockState, block);
	}
}
