package dev.hybridlabs.aquatic.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import com.llamalad7.mixinextras.sugar.Local;
import dev.hybridlabs.aquatic.access.CustomFishingBobberEntityData;
import dev.hybridlabs.aquatic.tag.HAItemTags;
import dev.hybridlabs.aquatic.utils.HandUtils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FishingRodItem.class)
public abstract class FishingRodItemMixin {

    /**
     * Tags the freshly spawned bobber with the lure item held in the opposite hand, so hook items can
     * modify the fishing behaviour.
     */
    @WrapOperation(
            method = "use",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/entity/projectile/Projectile;spawnProjectile(" +
                            "Lnet/minecraft/world/entity/projectile/Projectile;" +
                            "Lnet/minecraft/server/level/ServerLevel;" +
                            "Lnet/minecraft/world/item/ItemStack;)" +
                            "Lnet/minecraft/world/entity/projectile/Projectile;"))
    private <T extends Projectile> T hybrid$attachLure(
            T projectile, ServerLevel level, ItemStack spawnedFrom, Operation<T> original,
            @Local(argsOnly = true) Player user, @Local(argsOnly = true) InteractionHand hand) {
        ItemStack opposingHandItemStack = user.getItemInHand(HandUtils.getOpposingHand(hand));

        if (opposingHandItemStack.is(HAItemTags.INSTANCE.getLURE_ITEMS())
                && projectile instanceof CustomFishingBobberEntityData bobber) {
            bobber.setLureItem(opposingHandItemStack.copyAndClear());
        }

        return original.call(projectile, level, spawnedFrom);
    }
}
