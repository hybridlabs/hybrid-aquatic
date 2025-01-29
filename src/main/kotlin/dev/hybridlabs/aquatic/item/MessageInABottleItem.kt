package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.MessageInABottleBlock
import dev.hybridlabs.aquatic.block.MessageInABottleBlock.Variant
import dev.hybridlabs.aquatic.component.HybridAquaticComponentTypes
import net.minecraft.item.ItemStack
import net.minecraft.text.Text

/**
 * Represents the Message in a Bottle block item.
 * @see MessageInABottleBlock
 */
class MessageInABottleItem(settings: Settings) : PlaceableInWaterItem(HybridAquaticBlocks.MESSAGE_IN_A_BOTTLE, settings) {

    override fun getName(stack: ItemStack): Text {
        stack[HybridAquaticComponentTypes.BOTTLE_VARIANT]?.also { variant ->
            val key = translationKey
            val fullKey = when (variant) {
                Variant.JAR -> "$key.jar"
                Variant.LONGNECK -> "$key.longneck"
                else -> key
            }

            return Text.translatable(fullKey)
        }

        return super.getName(stack)
    }
}
