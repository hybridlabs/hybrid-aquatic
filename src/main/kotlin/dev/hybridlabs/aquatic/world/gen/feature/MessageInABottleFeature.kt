package dev.hybridlabs.aquatic.world.gen.feature

import com.mojang.serialization.Codec
import dev.hybridlabs.aquatic.block.MessageInABottleBlock
import dev.hybridlabs.aquatic.block.entity.MessageInABottleBlockEntity
import dev.hybridlabs.aquatic.registry.HybridAquaticRegistryKeys
import net.minecraft.block.Block
import net.minecraft.fluid.Fluids
import net.minecraft.state.property.Properties
import net.minecraft.util.math.Direction
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.util.FeatureContext

/**
 * A feature for placing Message in a Bottle blocks in the world.
 */
class MessageInABottleFeature(codec: Codec<MessageInABottleFeatureConfig>) : Feature<MessageInABottleFeatureConfig>(codec) {
    override fun generate(context: FeatureContext<MessageInABottleFeatureConfig>): Boolean {
        val config = context.config
        val world = context.world
        val origin = context.origin
        val random = context.random
        val state = config.toPlace[context.random, origin]

        if (state.block !is MessageInABottleBlock) {
            return false
        }

        val pos = context.origin.mutableCopy()

        if (!state.canPlaceAt(world, pos)) {
            // if can spawn below, move down
            // most likely called when a bottle spawns on water
            if (state.canPlaceAt(world, pos.down())) {
                pos.move(Direction.DOWN)
            } else {
                return false
            }
        }

        // set state
        world.setBlockState(pos, state.with(Properties.WATERLOGGED, world.getFluidState(pos).fluid == Fluids.WATER), Block.NOTIFY_LISTENERS)

        val blockEntity = world.getBlockEntity(pos)
        if (blockEntity is MessageInABottleBlockEntity) {
            // set random variant
            val entries = MessageInABottleBlock.Variant.entries
            blockEntity.variant = entries[random.nextInt(entries.size)]

            // set random message
            val registry = world.registryManager.get(HybridAquaticRegistryKeys.SEA_MESSAGE)
            registry.getRandom(random).ifPresent { messageEntry ->
                val message = messageEntry.value()
                val stack = message.createBookItemStack()
                blockEntity.messageItemStack = stack
            }
        } else {
            return false
        }

        return true
    }
}
