package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.MessageInABottleBlock
import dev.hybridlabs.aquatic.component.HybridAquaticDataComponentTypes
import net.minecraft.item.ItemStack

/**
 * Represents the Message in a Bottle block item.
 * @see MessageInABottleBlock
 */
class MessageInABottleItem(settings: Settings) : PlaceableInWaterItem(HybridAquaticBlocks.MESSAGE_IN_A_BOTTLE, settings) {
    override fun getTranslationKey(stack: ItemStack): String {
        val key = translationKey

        stack.get(HybridAquaticDataComponentTypes.BOTTLE)?.let { component ->
            val variant = component.variant
            return variant.appendTranslationSuffix(key)
        }

        return key
    }
}
