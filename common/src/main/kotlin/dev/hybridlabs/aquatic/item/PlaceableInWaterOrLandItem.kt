package dev.hybridlabs.aquatic.item

import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.context.UseOnContext
import net.minecraft.world.level.ClipContext
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block

open class PlaceableInWaterOrLandItem(block: Block, settings: Properties) : BlockItem(block, settings) {
    override fun useOn(context: UseOnContext): InteractionResult {
        return InteractionResult.PASS
    }

    override fun use(world: Level, user: Player, hand: InteractionHand): InteractionResultHolder<ItemStack> {
        var placePos = getPlayerPOVHitResult(world, user, ClipContext.Fluid.SOURCE_ONLY)
        var actionResult = super.useOn(UseOnContext(user, hand, placePos))

        if (actionResult.consumesAction()) {
            return InteractionResultHolder(actionResult, user.getItemInHand(hand))
        }

        placePos = getPlayerPOVHitResult(world, user, ClipContext.Fluid.NONE)
        actionResult = super.useOn(UseOnContext(user, hand, placePos))

        return InteractionResultHolder(actionResult, user.getItemInHand(hand))
    }
}