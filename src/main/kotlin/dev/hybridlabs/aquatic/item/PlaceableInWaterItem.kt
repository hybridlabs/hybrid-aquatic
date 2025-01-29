package dev.hybridlabs.aquatic.item

import net.minecraft.block.Block
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.BlockItem
import net.minecraft.item.ItemUsageContext
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.world.RaycastContext.FluidHandling
import net.minecraft.world.World

open class PlaceableInWaterItem(block: Block, settings: Settings): BlockItem(block, settings) {
    override fun useOnBlock(context: ItemUsageContext): ActionResult {
        return ActionResult.PASS
    }

    override fun use(world: World, user: PlayerEntity, hand: Hand): ActionResult {
        val blockHitResult = raycast(world, user, FluidHandling.SOURCE_ONLY)
        return super.useOnBlock(ItemUsageContext(user, hand, blockHitResult))
    }
}
