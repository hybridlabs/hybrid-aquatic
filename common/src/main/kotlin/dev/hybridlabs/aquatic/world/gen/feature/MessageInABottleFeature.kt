package dev.hybridlabs.aquatic.world.gen.feature

import com.mojang.serialization.Codec
import dev.hybridlabs.aquatic.block.MessageInABottleBlock
import dev.hybridlabs.aquatic.block.entity.MessageInABottleBlockEntity
import dev.hybridlabs.aquatic.registry.HybridAquaticRegistryKeys
import net.minecraft.core.Direction
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.properties.BlockStateProperties.WATERLOGGED
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext

/**
 * A feature for placing Message in a Bottle blocks in the world.
 */
class MessageInABottleFeature(codec: Codec<MessageInABottleFeatureConfig>) :
    Feature<MessageInABottleFeatureConfig>(codec) {
    override fun place(context: FeaturePlaceContext<MessageInABottleFeatureConfig>): Boolean {
        val config = context.config()
        val world = context.level()
        val origin = context.origin()
        val random = context.random()
        val placedState = config.toPlace.getState(random, origin)
        val originalState = world.getBlockState(origin.mutable())

        if (originalState.isSolid || placedState.block !is MessageInABottleBlock) {
            return false
        }

        val pos = context.origin().mutable()

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

            // set random message
            val registryManager = world.registryAccess()
            val registry = registryManager.registryOrThrow(HybridAquaticRegistryKeys.SEA_MESSAGE)
            registry.getRandom(random).ifPresent { messageEntry ->
                blockEntity.messageId = messageEntry.key().location()
            }
        } else {
            return false
        }

        return true
    }
}
