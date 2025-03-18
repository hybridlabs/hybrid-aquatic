package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.MessageInABottleBlock
import dev.hybridlabs.aquatic.block.MessageInABottleBlock.Variant
import dev.hybridlabs.aquatic.block.entity.MessageInABottleBlockEntity
import net.minecraft.item.ItemStack

/**
 * Represents the Message in a Bottle block item.
 * @see MessageInABottleBlock
 */
class MessageInABottleItem(settings: Settings) : PlaceableInWaterItem(HybridAquaticBlocks.MESSAGE_IN_A_BOTTLE, settings) {
    override fun getTranslationKey(stack: ItemStack): String {
        // custom variant translation keys
        val id = stack.getSubNbt(BLOCK_ENTITY_TAG_KEY)?.getString(MessageInABottleBlockEntity.VARIANT_KEY) ?: ""
        val variant = Variant.byId(id)
        val key = translationKey
        return when (variant) {
            Variant.JAR -> "$key.jar"
            Variant.LONGNECK -> "$key.longneck"
            else -> key
        }
    }
}
