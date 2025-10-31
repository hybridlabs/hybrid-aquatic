package dev.hybridlabs.aquatic.block

import dev.hybridlabs.aquatic.block.entity.StrawberryAnemoneBlockEntity
import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState

class StrawberryAnemoneBlock(settings: Properties) : BaseAnemoneBlock(settings) {

    override fun newBlockEntity(pos: BlockPos, state: BlockState): BlockEntity {
        return StrawberryAnemoneBlockEntity(pos, state)
    }
}