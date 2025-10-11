package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.MessageInABottleBlock
import dev.hybridlabs.aquatic.block.MessageInABottleBlock.Variant
import dev.hybridlabs.aquatic.block.entity.MessageInABottleBlockEntity
import dev.hybridlabs.aquatic.client.render.item.MessageInABottleBlockItemRenderer
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer
import net.minecraft.core.component.DataComponents
import net.minecraft.world.item.ItemStack
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions
import java.util.function.Consumer

/**
 * Represents the Message in a Bottle block item.
 * @see MessageInABottleBlock
 */
class MessageInABottleItem(settings: Properties) :
    PlaceableInWaterItem(HybridAquaticBlocks.MESSAGE_IN_A_BOTTLE.get(), settings) {
    override fun getDescriptionId(stack: ItemStack): String {
        // custom variant translation keys
        val id = stack.components[DataComponents.BLOCK_ENTITY_DATA]?.copyTag()?.getString(MessageInABottleBlockEntity.VARIANT_KEY) ?: ""
        val variant = Variant.byId(id)
        val key = descriptionId
        return when (variant) {
            Variant.JAR -> "$key.jar"
            Variant.LONGNECK -> "$key.longneck"
            else -> key
        }
    }

    override fun initializeClient(consumer: Consumer<IClientItemExtensions?>) {
        consumer.accept(object : IClientItemExtensions {
            override fun getCustomRenderer(): BlockEntityWithoutLevelRenderer {
                return MessageInABottleBlockItemRenderer()
            }
        })
    }
}
