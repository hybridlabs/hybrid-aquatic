package dev.hybridlabs.aquatic.world.gen.feature

import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import dev.hybridlabs.aquatic.block.MessageInABottleBlock
import dev.hybridlabs.aquatic.block.entity.MessageInABottleBlockEntity
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.properties.BlockStateProperties.WATERLOGGED
import net.minecraft.util.RandomSource
import net.minecraft.world.level.WorldGenLevel
import net.minecraft.world.level.chunk.ChunkGenerator
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider

/**
 * A feature for placing Message in a Bottle blocks in the world.
 */
data class MessageInABottleFeature(val toPlace: BlockStateProvider) : Feature {
    override fun codec(): MapCodec<MessageInABottleFeature> = CODEC

    override fun place(
        world: WorldGenLevel,
        chunkGenerator: ChunkGenerator,
        random: RandomSource,
        origin: BlockPos,
    ): Boolean {
        val placedState = this.toPlace.getState(world, random, origin)
        val originalState = world.getBlockState(origin.mutable())

        if (originalState.isSolid || placedState.block !is MessageInABottleBlock) {
            return false
        }

        val pos = origin.mutable()

        if (!placedState.canSurvive(world, pos)) {
            // if it can spawn below, move down
            // most likely called when a bottle spawns on water
            if (placedState.canSurvive(world, pos.below())) {
                pos.move(Direction.DOWN)
            } else {
                return false
            }
        }

        // set state
        world.setBlock(pos, placedState.setValue(WATERLOGGED, world.isWaterAt(pos)), Block.UPDATE_CLIENTS)

        val blockEntity = world.getBlockEntity(pos)
        if (blockEntity is MessageInABottleBlockEntity) {
            // set random variant
            val entries = MessageInABottleBlock.Variant.entries
            blockEntity.variant = entries[random.nextInt(entries.size)]
        } else {
            return false
        }

        return true
    }

    companion object {
        val CODEC: MapCodec<MessageInABottleFeature> = RecordCodecBuilder.mapCodec { instance ->
            instance.group(
                BlockStateProvider.DIRECT_CODEC.fieldOf("to_place")
                    .forGetter(MessageInABottleFeature::toPlace)
            ).apply(instance, ::MessageInABottleFeature)
        }
    }
}
