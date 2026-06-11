package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.block.HABlocks
import dev.hybridlabs.aquatic.block.MessageInABottleBlock
import dev.hybridlabs.aquatic.block.MessageInABottleBlock.Variant
import dev.hybridlabs.aquatic.block.entity.MessageInABottleBlockEntity
import dev.hybridlabs.aquatic.client.render.item.MessageInABottleBlockItemRenderer
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer
import net.minecraft.core.component.DataComponents
import net.minecraft.world.item.ItemStack
import software.bernie.geckolib.animatable.GeoItem
import software.bernie.geckolib.animatable.client.GeoRenderProvider
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.animation.AnimatableManager
import software.bernie.geckolib.util.GeckoLibUtil
import java.util.function.Consumer


/**
 * Represents the Message in a Bottle block item.
 * @see MessageInABottleBlock
 */
class MessageInABottleItem(settings: Properties) :
    PlaceableInWaterItem(HABlocks.MESSAGE_IN_A_BOTTLE.get(), settings), GeoItem {

    private val cache: AnimatableInstanceCache? = GeckoLibUtil.createInstanceCache(this)

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

    override fun createGeoRenderer(consumer: Consumer<GeoRenderProvider?>) {
        consumer.accept(object : GeoRenderProvider {
            private var renderer: MessageInABottleBlockItemRenderer? = null

            override fun getGeoItemRenderer(): BlockEntityWithoutLevelRenderer? {
                if (this.renderer == null) this.renderer = MessageInABottleBlockItemRenderer()

                return this.renderer
            }
        })
    }

    override fun registerControllers(p0: AnimatableManager.ControllerRegistrar?) {
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache? {
        return this.cache
    }

}
