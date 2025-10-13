package dev.hybridlabs.aquatic.mixin;

import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import dev.hybridlabs.aquatic.item.HybridAquaticItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(Entity.class)
public class EntityMixin {
    @ModifyReturnValue(method = "maxUpStep", at = @At("RETURN"),remap = false)
    private float onGetStepHeight(float original) {
        // Allows player to walk in the water without jumping
        Entity entity = (Entity) (Object) this;
        if (entity instanceof Player player) {
            ItemStack stack = player.getItemBySlot(EquipmentSlot.FEET);
            if (stack.is(HybridAquaticItems.INSTANCE.getDIVING_BOOTS().get()) && player.isUnderWater()) {
                return original * 2.0f;
            }
        }
        return original;
    }
}
