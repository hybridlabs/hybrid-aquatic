package dev.hybridlabs.aquatic.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.sugar.Local;
import dev.hybridlabs.aquatic.block.HABlocks;
import net.minecraft.world.level.block.SeagrassBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(SeagrassBlock.class)
public class SeagrassBlockMixin {
	@ModifyReturnValue(
			method = "mayPlaceOn",
			at = @At(value = "RETURN")
	)
	private boolean addSeaGrass(boolean original, @Local(ordinal = 0, argsOnly = true) BlockState blockState) {
		if(blockState.is(HABlocks.INSTANCE.getAERATED_SAND().get())) return false;
		if(blockState.is(HABlocks.INSTANCE.getBUBBLE_GEYSER().get())) return false;
		else return original;
	}
}
