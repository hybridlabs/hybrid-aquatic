package dev.hybridlabs.aquatic.mixin;

import dev.hybridlabs.aquatic.item.HookItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Enchantment.class)
public class EnchantmentMixin {
  // Does not remove enchantments that override this method, at least makes it so you cant apply mending to the hooks
  @Inject(method = "isAcceptableItem", cancellable = true, at = @At(
    value = "RETURN"
  ))
  private void removeEnchantmentsFromHooks(ItemStack stack, CallbackInfoReturnable<Boolean> cir) {
    if(stack.getItem() instanceof HookItem) cir.setReturnValue(false);
  }
}
