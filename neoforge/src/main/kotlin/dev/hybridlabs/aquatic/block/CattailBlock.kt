package dev.hybridlabs.aquatic.block

import dev.hybridlabs.aquatic.block.wood.HybridAquaticPlatformBlocks
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.state.BlockState

class CattailBlock(settings: Properties): BaseCattailBlock(settings){
    override fun getCloneItemStack(world: BlockGetter, pos: BlockPos, state: BlockState): ItemStack {
        return ItemStack(HybridAquaticPlatformBlocks.CATTAIL.get())
    }
    override fun isFlammable(state: BlockState?, level: BlockGetter?, pos: BlockPos?, direction: Direction?): Boolean {
        return true
    }

    override fun getFlammability(state: BlockState?, level: BlockGetter?, pos: BlockPos?, direction: Direction?): Int {
        return 5
    }

    override fun getFireSpreadSpeed(
        state: BlockState?, level: BlockGetter?, pos: BlockPos?, direction: Direction?
    ): Int {
        return 5
    }
}