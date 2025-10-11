package dev.hybridlabs.aquatic.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.hybridlabs.aquatic.item.HookItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Enchantment.class)
public abstract class EnchantmentMixin {
    // Does not remove enchantments that override this method, at least makes it so you cant apply mending to the hooks
    @ModifyReturnValue(method = "canEnchant", at = @At("RETURN"),remap = false)
    private boolean removeEnchantmentsFromHooks(boolean original, ItemStack stack) {
        return !(stack.getItem() instanceof HookItem) && original;
    }

}
