package dev.hybridlabs.aquatic.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.hybridlabs.aquatic.item.HookItem;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Enchantment.class)
public abstract class EnchantmentMixin {
  // Does not remove enchantments that override this method, at least makes it so you cant apply mending to the hooks
  @ModifyReturnValue(method = "isAcceptableItem", at = @At(
    value = "RETURN"
  ))
  private boolean removeEnchantmentsFromHooks(boolean original, ItemStack stack) {
    if(stack.getItem() instanceof HookItem) return false;
    else return original;
  }
}
