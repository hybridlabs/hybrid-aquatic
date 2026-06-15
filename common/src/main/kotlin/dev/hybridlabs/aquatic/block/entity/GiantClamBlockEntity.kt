package dev.hybridlabs.aquatic.block.entity

import dev.hybridlabs.aquatic.block.GiantClamBlock
import net.minecraft.core.BlockPos
import net.minecraft.core.HolderLookup
import net.minecraft.nbt.CompoundTag
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState

class GiantClamBlockEntity(
    pos: BlockPos,
    state: BlockState
) : BlockEntity(
    HABlockEntityTypes.GIANT_CLAM.get(),
    pos,
    state
) {

    var pearlTimer: Int = 0

    fun closeAndStartCooldown() {
        val level = level as? ServerLevel ?: return

        if (blockState.getValue(GiantClamBlock.STATE) == GiantClamBlock.GiantClamState.OPEN) {
            level.setBlock(
                worldPosition,
                blockState.setValue(GiantClamBlock.STATE, GiantClamBlock.GiantClamState.CLOSED),
                3
            )

            level.playSound(
                null,
                worldPosition,
                SoundEvents.SHULKER_CLOSE,
                SoundSource.BLOCKS,
                1.0f,
                1.0f
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

        if (blockState.getValue(GiantClamBlock.WATERLOGGED)) {
            level.setBlock(
                worldPosition,
                blockState.setValue(GiantClamBlock.STATE, GiantClamBlock.GiantClamState.OPEN),
                3
            )
        }
    }

    override fun saveAdditional(tag: CompoundTag, registries: HolderLookup.Provider) {
        super.saveAdditional(tag, registries)
        tag.putInt("pearl_timer", pearlTimer)
    }

    override fun loadAdditional(tag: CompoundTag, registries: HolderLookup.Provider) {
        TODO("MIGRATE TO COMPONENTS")
        super.loadAdditional(tag, registries)
        pearlTimer = tag.getInt("pearl_timer")
    }

    companion object {
        const val PEARL_TIMER = 6000

        fun tick(level: Level, pos: BlockPos, state: BlockState, be: GiantClamBlockEntity) {
            if (!level.isClientSide) {
                be.tickServer()
            }
        }
    }
}
