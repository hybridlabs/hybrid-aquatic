package dev.hybridlabs.aquatic.block

import dev.hybridlabs.aquatic.CommonClass
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.state.BlockBehaviour

/**
 * Block ids are no longer assigned by the registry, they have to be set on
 * [BlockBehaviour.Properties] before the block is constructed. Registration goes through
 * [HABlocks.register], which exposes the id being registered here so block factories can build
 * their properties without threading it through every call site.
 */
object HABlockProperties {
    private val currentId = ThreadLocal<ResourceKey<Block>?>()

    fun <T> withId(id: String, factory: () -> T): T {
        val previous = currentId.get()
        currentId.set(ResourceKey.create(Registries.BLOCK, CommonClass.locate(id)))
        try {
            return factory()
        } finally {
            currentId.set(previous)
        }
    }

    private fun id(): ResourceKey<Block> {
        return checkNotNull(currentId.get()) { "Block properties must be created during block registration" }
    }

    fun of(): BlockBehaviour.Properties = BlockBehaviour.Properties.of().setId(id())

    fun ofFullCopy(block: Block): BlockBehaviour.Properties =
        BlockBehaviour.Properties.ofFullCopy(block).setId(id())

    fun ofLegacyCopy(block: Block): BlockBehaviour.Properties =
        BlockBehaviour.Properties.ofLegacyCopy(block).setId(id())

    fun copy(properties: BlockBehaviour.Properties): BlockBehaviour.Properties = properties.setId(id())
}
