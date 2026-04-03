package dev.hybridlabs.aquatic.block

import dev.hybridlabs.aquatic.block.wood.HAPlatformBlocks
import net.minecraft.core.BlockPos
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.BlockGetter
import net.minecraft.world.level.block.state.BlockState

class CattailBlock(settings: Properties): BaseCattailBlock(settings){
    override fun getCloneItemStack(world: BlockGetter, pos: BlockPos, state: BlockState): ItemStack {
        return ItemStack(HAPlatformBlocks.CATTAIL.get())
    }
}