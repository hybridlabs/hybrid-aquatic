package dev.hybridlabs.aquatic.block

import dev.hybridlabs.aquatic.block.entity.GiantGreenAnemoneBlockEntity
import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState

class GiantGreenAnemoneBlock(settings: Properties) : BaseAnemoneBlock(settings) {

    override fun newBlockEntity(blockPos: BlockPos, blockState: BlockState): BlockEntity {
        return GiantGreenAnemoneBlockEntity(blockPos, blockState)
    }
}