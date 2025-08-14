package dev.hybridlabs.aquatic.mixin;

import dev.hybridlabs.aquatic.access.CustomFishingBobberEntityData;
import dev.hybridlabs.aquatic.tag.HybridAquaticItemTags;
import dev.hybridlabs.aquatic.utils.HandUtils;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FishingBobberEntity;
import net.minecraft.item.FishingRodItem;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.stat.Stats;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static net.minecraft.enchantment.Enchantments.LUCK_OF_THE_SEA;
import static net.minecraft.enchantment.Enchantments.LURE;

@Mixin(FishingRodItem.class)
public abstract class FishingRodItemMixin {
    @Inject(
            method = "use",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/World;spawnEntity(Lnet/minecraft/entity/Entity;)Z"
            ),
            cancellable = true
    )
    private void redirectFix(World world, PlayerEntity user, Hand hand, CallbackInfoReturnable<TypedActionResult<ItemStack>> cir) {
        ItemStack mainHandItemStack = user.getStackInHand(hand);
        ItemStack opposingHandItemStack = user.getStackInHand(HandUtils.getOpposingHand(hand));

        if (opposingHandItemStack.isIn(HybridAquaticItemTags.INSTANCE.getLURE_ITEMS())) {
            int lureLevel = EnchantmentHelper.getLevel((RegistryEntry<Enchantment>) LURE, mainHandItemStack);
            int luckLevel = EnchantmentHelper.getLevel((RegistryEntry<Enchantment>) LUCK_OF_THE_SEA, mainHandItemStack);
            FishingBobberEntity customBobber = new FishingBobberEntity(user, world, lureLevel, luckLevel);

            ((CustomFishingBobberEntityData) customBobber).hybrid_aquatic$setLureItem(opposingHandItemStack.copyAndEmpty());
            world.spawnEntity(customBobber);

            user.incrementStat(Stats.USED.getOrCreateStat(((FishingRodItem) (Object) this)));
            user.emitGameEvent(GameEvent.ITEM_INTERACT_START);
            cir.setReturnValue(TypedActionResult.success(mainHandItemStack, world.isClient()));
        }
    }
}