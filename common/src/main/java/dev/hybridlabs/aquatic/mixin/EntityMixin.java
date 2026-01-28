package dev.hybridlabs.aquatic.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import dev.hybridlabs.aquatic.Constants;
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks;
import dev.hybridlabs.aquatic.item.HybridAquaticItems;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Slice;

@Mixin(Entity.class)
public class EntityMixin {
    @ModifyReturnValue(method = "maxUpStep", at = @At("RETURN"))
    private float onGetStepHeight(float original) {
        // Allows player to walk in the water without jumping
        Entity entity = (Entity) (Object) this;
        if (entity instanceof Player player) {
            ItemStack stack = player.getItemBySlot(EquipmentSlot.FEET);
            if (stack.is(HybridAquaticItems.INSTANCE.getDIVING_BOOTS().get()) && player.isEyeInFluid(FluidTags.WATER)) {
                return original * 1.67f;
            }
        }

        return original;
    }
	
	@WrapOperation(
			method = "getBlockSpeedFactor",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z")
	)
	private boolean decorativeBubbleColumn_getBlockSpeedFactorParity(BlockState instance, Block block, Operation<Boolean> original) {
		Constants.LOG.info("{}: {} {} {}", "E.getBlockSpeedFactor", instance, block, original);
		return instance.is(HybridAquaticBlocks.INSTANCE.getDECORATIVE_BUBBLE_COLUMN().get()) ? block == Blocks.BUBBLE_COLUMN : original.call(instance, block);
	}
	
	@WrapOperation(
			method = "isInBubbleColumn",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/world/level/block/state/BlockState;is(Lnet/minecraft/world/level/block/Block;)Z")
	)
	private boolean decorativeBubbleColumn_isInBubbleColumnParity(BlockState instance, Block block, Operation<Boolean> original) {
		Constants.LOG.info("{}: {} {} {}", "E.isInBubbleColumn", instance, block, original);
		return instance.is(HybridAquaticBlocks.INSTANCE.getDECORATIVE_BUBBLE_COLUMN().get()) ? block == Blocks.BUBBLE_COLUMN : original.call(instance, block);
	}
}
