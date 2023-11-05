package dev.hybridlabs.aquatic.item

import net.minecraft.block.Block
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemUsageContext
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.RaycastContext
import net.minecraft.world.World

class PlaceableInWaterItem(block: Block, settings: Settings): BlockItem(block, settings) {
    override fun useOnBlock(context: ItemUsageContext?): ActionResult {
        return ActionResult.PASS
    }

    override fun use(world: World?, user: PlayerEntity, hand: Hand?): TypedActionResult<ItemStack> {
        val blockHitResult = Item.raycast(world, user, RaycastContext.FluidHandling.SOURCE_ONLY)
        val actionResult = super.useOnBlock(ItemUsageContext(user, hand, blockHitResult))

        return TypedActionResult(actionResult, user.getStackInHand(hand))
    }
}