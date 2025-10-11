package dev.hybridlabs.aquatic.block

import dev.hybridlabs.aquatic.block.wood.HybridAquaticPlatformBlocks
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.util.RandomSource
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.TallSeagrassBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf

class DunegrassBlock(settings: Properties): BaseDunegrassBlock(settings){
    override fun getCloneItemStack(world: LevelReader, pos: BlockPos, state: BlockState): ItemStack {
        return ItemStack(HybridAquaticPlatformBlocks.DUNEGRASS.get())
    }
    override fun performBonemeal(level: ServerLevel, random: RandomSource, pos: BlockPos, state: BlockState) {
        val blockState = HybridAquaticPlatformBlocks.TALL_DUNEGRASS.get().defaultBlockState()
        val blockStateUpper = blockState.setValue(TallSeagrassBlock.HALF, DoubleBlockHalf.UPPER)
        val blockPosAbove = pos.above()

        if (level.isEmptyBlock(blockPosAbove)) {
            level.setBlock(pos, blockState, 2)
            level.setBlock(blockPosAbove, blockStateUpper, 2)
        }
    }
    override fun isFlammable(state: BlockState, level: BlockGetter, pos: BlockPos, direction: Direction): Boolean {
        return true
    }

    override fun getFlammability(state: BlockState, level: BlockGetter, pos: BlockPos, direction: Direction): Int {
        return 5
    }

    override fun getFireSpreadSpeed(
        state: BlockState, level: BlockGetter, pos: BlockPos, direction: Direction
    ): Int {
        return 5
    }
}