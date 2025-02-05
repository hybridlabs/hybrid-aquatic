package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes.THROWING_STAR
import dev.hybridlabs.aquatic.entity.miscellaneous.ThrowingStarEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.stat.Stats
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World

class ThrowingStarItem(settings: Settings?) : Item(settings) {

    override fun use(world: World?, user: PlayerEntity?, hand: Hand?): TypedActionResult<ItemStack> {
        val thrownStack = user!!.getStackInHand(hand); // This will literally never be null??
        world!!.playSound(null as PlayerEntity?, user.x, user.y, user.z, SoundEvents.BLOCK_DISPENSER_LAUNCH, SoundCategory.NEUTRAL, 0.5f, 0.4f / (world!!.getRandom().nextFloat() * 0.4f + 0.8f))

        // It's crazy how optimal the snowball throwing code is for literally everything
        if (!world.isClient) {
            val throwingStar = ThrowingStarEntity(THROWING_STAR, world);
            throwingStar.DisplayItem = thrownStack.copy();
            throwingStar.setVelocity(user, user.pitch, user.yaw, 0.0f, 1.5f, 1.0f)
            world.spawnEntity(throwingStar)
        }

        user.incrementStat(Stats.USED.getOrCreateStat(this))
        if (!user.abilities.creativeMode) {
            thrownStack.decrement(1)
        }

        return TypedActionResult.success(thrownStack, world.isClient)
    }

}