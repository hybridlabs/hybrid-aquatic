package dev.hybridlabs.aquatic.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.hybridlabs.aquatic.item.HybridAquaticItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.tag.FluidTags;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Entity.class)
public class EntityMixin {
    @ModifyReturnValue(method = "getStepHeight", at = @At("RETURN"))
    private float onGetStepHeight(float original) {
        // Allows player to walk in the water without jumping
        Entity entity = (Entity) (Object) this;
        if (entity instanceof PlayerEntity player) {
            ItemStack stack = player.getEquippedStack(EquipmentSlot.FEET);
            if (stack.isOf(HybridAquaticItems.INSTANCE.getDIVING_BOOTS()) && player.isSubmergedIn(FluidTags.WATER)) {
                return original * 1.67f;
            }
        }

        return original;
    }
}
