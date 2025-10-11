package dev.hybridlabs.aquatic.block

import dev.hybridlabs.aquatic.block.wood.HybridAquaticPlatformBlocks
import net.minecraft.core.BlockPos
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.block.state.BlockState

class DunegrassBlock(settings: Properties) : BaseDunegrassBlock(settings) {
    override fun getCloneItemStack(world: LevelReader, pos: BlockPos, state: BlockState): ItemStack {
        return ItemStack(HybridAquaticPlatformBlocks.DUNEGRASS.get())
    }
}