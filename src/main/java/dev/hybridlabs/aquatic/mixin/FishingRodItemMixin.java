package dev.hybridlabs.aquatic.mixin;

import dev.hybridlabs.aquatic.access.CustomFishingBobberEntityData;
import dev.hybridlabs.aquatic.tag.HybridAquaticItemTags;
import dev.hybridlabs.aquatic.utils.HandUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FishingRodItem.class)
public abstract class FishingRodItemMixin {
    @Unique
    private PlayerEntity usedPlayer;

    @Unique
    private Hand usedHand;

    // Gets all the required objects
    @Inject(method = "use", at = @At("HEAD"))
    private void playerGetter(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        this.usedPlayer = user;
        this.usedHand = hand;
    }

    // If item in the opposite hand has lure item, it gets put in the fishing rod
    @Redirect(
            method = "use",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/World;spawnEntity(Lnet/minecraft/entity/Entity;)Z"
            )
    )
    private boolean spawnEntityRedirect(World world, Entity entity) {
        FishingBobberEntity bobber = (FishingBobberEntity) entity;
        Hand opposingHand = HandUtils.getOpposingHand(usedHand);
        ItemStack opposingHandItemStack = usedPlayer.getStackInHand(opposingHand);

        if (opposingHandItemStack.isIn(HybridAquaticItemTags.INSTANCE.getLURE_ITEMS())) {
            ((CustomFishingBobberEntityData) bobber).hybrid_aquatic$setLureItem(opposingHandItemStack.copy());
            opposingHandItemStack.setCount(0);
        }

        return world.spawnEntity(bobber);
    }
}
