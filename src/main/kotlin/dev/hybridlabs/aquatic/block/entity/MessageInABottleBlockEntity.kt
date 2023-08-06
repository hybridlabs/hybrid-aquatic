package dev.hybridlabs.aquatic.block.entity

import dev.hybridlabs.aquatic.block.MessageInABottleBlock
import net.minecraft.block.BlockState
import net.minecraft.block.entity.BlockEntity
import net.minecraft.nbt.NbtCompound
import net.minecraft.network.packet.s2c.play.BlockEntityUpdateS2CPacket
import net.minecraft.util.math.BlockPos

/**
 * Represents the block entity for Message in a Bottle blocks.
 * @see MessageInABottleBlock
 */
class MessageInABottleBlockEntity(pos: BlockPos, state: BlockState) : BlockEntity(HybridAquaticBlockEntityTypes.MESSAGE_IN_A_BOTTLE, pos, state) {
    override fun toInitialChunkDataNbt(): NbtCompound {
        return createNbt()
    }

    override fun toUpdatePacket(): BlockEntityUpdateS2CPacket {
        return BlockEntityUpdateS2CPacket.create(this)
    }
}
