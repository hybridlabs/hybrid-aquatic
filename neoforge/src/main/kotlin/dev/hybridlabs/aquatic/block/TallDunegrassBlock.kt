package dev.hybridlabs.aquatic.block

import dev.hybridlabs.aquatic.block.wood.HybridAquaticPlatformBlocks
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.DoublePlantBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf
import net.minecraft.world.level.block.state.properties.EnumProperty
import net.minecraft.world.phys.shapes.VoxelShape

class TallDunegrassBlock(settings: Properties) : BaseTallDunegrassBlock(settings) {
    override fun getCloneItemStack(world: LevelReader, pos: BlockPos, state: BlockState): ItemStack {
        return ItemStack(HybridAquaticPlatformBlocks.TALL_DUNEGRASS.get())
    }

    override fun isFlammable(state: BlockState, level: BlockGetter, pos: BlockPos, direction: Direction): Boolean {
        return true
    }

    override fun getFlammability(state: BlockState, level: BlockGetter, pos: BlockPos, direction: Direction): Int {
        return 60
    }

    override fun getFireSpreadSpeed(
        state: BlockState, level: BlockGetter, pos: BlockPos, direction: Direction
    ): Int {
        return 100
    }

    companion object {
        val HALF: EnumProperty<DoubleBlockHalf> = DoublePlantBlock.HALF
        private val SHAPE: VoxelShape = box(2.0, 0.0, 2.0, 14.0, 16.0, 14.0)
    }
}