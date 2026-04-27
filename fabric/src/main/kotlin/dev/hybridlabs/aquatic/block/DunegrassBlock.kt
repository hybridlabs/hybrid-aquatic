package dev.hybridlabs.aquatic.block

import net.minecraft.core.BlockPos
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.state.BlockState

class DunegrassBlock(settings: Properties) : BaseDunegrassBlock(settings) {
    override fun getCloneItemStack(world: BlockGetter, pos: BlockPos, state: BlockState): ItemStack {
        return ItemStack(HAPlatformBlocks.DUNEGRASS.get())
    }
}