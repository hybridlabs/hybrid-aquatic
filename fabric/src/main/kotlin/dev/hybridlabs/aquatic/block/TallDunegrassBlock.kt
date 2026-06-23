package dev.hybridlabs.aquatic.block

import net.minecraft.core.BlockPos
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.DoublePlantBlock
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf
import net.minecraft.world.level.block.state.properties.EnumProperty
import net.minecraft.world.phys.shapes.VoxelShape

class TallDunegrassBlock(settings: Properties) : BaseTallDunegrassBlock(settings) {
    override fun getCloneItemStack(world: LevelReader, pos: BlockPos, state: BlockState): ItemStack {
        return ItemStack(HAPlatformBlocks.TALL_DUNEGRASS.get())
    }

    companion object {
        val HALF: EnumProperty<DoubleBlockHalf> = DoublePlantBlock.HALF
        private val SHAPE: VoxelShape = box(2.0, 0.0, 2.0, 14.0, 16.0, 14.0)
    }
}