package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.client.render.item.StrawberryAnemoneBlockItemRenderer
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer
import net.minecraft.world.item.BlockItem
import net.minecraft.world.level.block.Block
import software.bernie.geckolib.animatable.GeoItem
import software.bernie.geckolib.animatable.client.GeoRenderProvider
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.animation.AnimatableManager
import software.bernie.geckolib.util.GeckoLibUtil
import java.util.function.Consumer

class StrawberryAnemoneBlockItem(block: Block, properties: Properties) : BlockItem(block, properties), GeoItem {

    private val cache: AnimatableInstanceCache? = GeckoLibUtil.createInstanceCache(this)

    override fun registerControllers(p0: AnimatableManager.ControllerRegistrar?) {
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache? {
        return this.cache
    }

    // Utilise our own render hook to define our custom renderer
    override fun createGeoRenderer(consumer: Consumer<GeoRenderProvider?>) {
        consumer.accept(object : GeoRenderProvider {
            private var renderer: StrawberryAnemoneBlockItemRenderer? = null

            override fun getGeoItemRenderer(): BlockEntityWithoutLevelRenderer? {
                if (this.renderer == null) this.renderer = StrawberryAnemoneBlockItemRenderer()

                return this.renderer
            }
        })
    }

}