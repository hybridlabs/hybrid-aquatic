package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.MessageInABottleBlock
import dev.hybridlabs.aquatic.block.MessageInABottleBlock.Variant
import dev.hybridlabs.aquatic.block.entity.MessageInABottleBlockEntity
import net.minecraft.world.item.ItemStack

/**
 * Represents the Message in a Bottle block item.
 * @see MessageInABottleBlock
 */
class MessageInABottleItem(settings: Properties) :
    PlaceableInWaterItem(HybridAquaticBlocks.MESSAGE_IN_A_BOTTLE.get(), settings) {
    override fun getDescriptionId(stack: ItemStack): String {
        // custom variant translation keys
        val id = stack.getTagElement(BLOCK_ENTITY_TAG)?.getString(MessageInABottleBlockEntity.VARIANT_KEY) ?: ""
        val variant = Variant.byId(id)
        val key = descriptionId
        return when (variant) {
            Variant.JAR -> "$key.jar"
            Variant.LONGNECK -> "$key.longneck"
            else -> key
        }
    }
}
