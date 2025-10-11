package dev.hybridlabs.aquatic.mixin;

import dev.hybridlabs.aquatic.access.CustomFishingBobberEntityData;
import dev.hybridlabs.aquatic.tag.HybridAquaticItemTags;
import dev.hybridlabs.aquatic.utils.HandUtils;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.FishingRodItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FishingRodItem.class)
public abstract class FishingRodItemMixin {
    @Inject(method = "use", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;addFreshEntity" +
            "(Lnet" + "/minecraft/world/entity/Entity;)Z"), cancellable = true, remap = false)
    private void redirectFix(Level world, Player user, InteractionHand hand,
                             CallbackInfoReturnable<InteractionResultHolder<ItemStack>> cir) {
        if (world instanceof ServerLevel serverLevel) {
            ItemStack mainHandItemStack = user.getItemInHand(hand);
            ItemStack opposingHandItemStack = user.getItemInHand(HandUtils.getOpposingHand(hand));

            if (opposingHandItemStack.is(HybridAquaticItemTags.INSTANCE.getLURE_ITEMS())) {
                float lureLevel =
                        EnchantmentHelper.getFishingTimeReduction(serverLevel, mainHandItemStack, user);
                float luckLevel =
                        EnchantmentHelper.getFishingLuckBonus(serverLevel,mainHandItemStack, user);
                FishingHook customBobber = new FishingHook(user, world, (int)lureLevel, (int)luckLevel);

                ((CustomFishingBobberEntityData) customBobber)
                        .hybrid_aquatic$setLureItem(opposingHandItemStack.copyAndClear());
                world.addFreshEntity(customBobber);

                user.awardStat(Stats.ITEM_USED.get(((FishingRodItem) (Object) this)));
                user.gameEvent(GameEvent.ITEM_INTERACT_START);
                cir.setReturnValue(InteractionResultHolder.success(mainHandItemStack));
            }
        }
    }
}