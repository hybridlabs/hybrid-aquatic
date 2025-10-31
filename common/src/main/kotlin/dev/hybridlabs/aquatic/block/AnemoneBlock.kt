package dev.hybridlabs.aquatic.block

import dev.hybridlabs.aquatic.block.entity.AnemoneBlockEntity
import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState

class AnemoneBlock(settings: Properties) : BaseAnemoneBlock(settings) {

    override fun newBlockEntity(pos: BlockPos, state: BlockState): BlockEntity {
        return AnemoneBlockEntity(pos, state)
    }
}