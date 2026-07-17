package dev.hybridlabs.aquatic.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.decoration.Painting;
import net.minecraft.world.item.HangingEntityItem;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import java.util.Optional;

@Mixin(HangingEntityItem.class)
public class HangingEntityItemMixin {
	@WrapOperation(
			method = "useOn",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/world/entity/decoration/Painting;create(Lnet/minecraft/world/level/Level;Lnet/minecraft/core/BlockPos;Lnet/minecraft/core/Direction;)Ljava/util/Optional;")
	)
	private Optional<Painting> test(Level level, BlockPos pos, Direction direction, Operation<Optional<Painting>> originalOperation, @Local(argsOnly = true) UseOnContext context) {
		var original = originalOperation.call(level, pos, direction);
		if (original.isEmpty() && !level.isClientSide() && !context.getPlayer().isCreative())
			context.getPlayer().containerMenu.broadcastFullState();
		
		return original;
	}
}
