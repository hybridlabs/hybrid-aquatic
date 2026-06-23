package dev.hybridlabs.aquatic.block

import net.minecraft.core.BlockPos
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.state.BlockState

class CattailBlock(settings: Properties) : BaseCattailBlock(settings) {
    override fun getCloneItemStack(world: LevelReader, pos: BlockPos, state: BlockState): ItemStack {
        return ItemStack(HAPlatformBlocks.CATTAIL.get())
    }
}