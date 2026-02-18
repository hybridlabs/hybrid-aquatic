package dev.hybridlabs.aquatic.block.entity

import dev.hybridlabs.aquatic.block.OysterBlock
import net.minecraft.core.BlockPos
import net.minecraft.nbt.CompoundTag
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState

class OysterBlockEntity(
    pos: BlockPos,
    state: BlockState
) : BlockEntity(
    HybridAquaticBlockEntityTypes.OYSTER.get(),
    pos,
    state
) {

    var pearlTimer: Int = 0

    fun closeAndStartCooldown() {
        val level = level as? ServerLevel ?: return

        if (blockState.getValue(OysterBlock.STATE) == OysterBlock.OysterState.OPEN) {
            level.setBlock(
                worldPosition,
                blockState.setValue(OysterBlock.STATE, OysterBlock.OysterState.CLOSED),
                3
            )

            pearlTimer = PEARL_TIMER
            setChanged()
        }
    }

    private fun tickServer() {
        if (pearlTimer > 0) {
            pearlTimer--

            if (pearlTimer <= 0) {
                reopenClam()
            }

            setChanged()
        }
    }

    private fun reopenClam() {
        val level = level as? ServerLevel ?: return

        if (blockState.getValue(OysterBlock.WATERLOGGED)) {
            level.setBlock(
                worldPosition,
                blockState.setValue(OysterBlock.STATE, OysterBlock.OysterState.OPEN),
                3
            )
        }
    }

    override fun saveAdditional(tag: CompoundTag) {
        super.saveAdditional(tag)
        tag.putInt("pearl_timer", pearlTimer)
    }

    override fun load(tag: CompoundTag) {
        super.load(tag)
        pearlTimer = tag.getInt("pearl_timer")
    }

    companion object {
        const val PEARL_TIMER = 6000

        fun tick(level: Level, pos: BlockPos, state: BlockState, be: OysterBlockEntity) {
            if (!level.isClientSide) {
                be.tickServer()
            }
        }
    }
}
