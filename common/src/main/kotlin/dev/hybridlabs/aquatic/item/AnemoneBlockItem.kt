package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.client.render.item.AnemoneBlockItemRenderer
import net.minecraft.world.item.BlockItem
import net.minecraft.world.level.block.Block
import com.geckolib.animatable.GeoItem
import com.geckolib.animatable.client.GeoRenderProvider
import com.geckolib.animatable.instance.AnimatableInstanceCache
import com.geckolib.animatable.manager.AnimatableManager
import com.geckolib.renderer.GeoItemRenderer
import com.geckolib.util.GeckoLibUtil
import java.util.function.Consumer

class AnemoneBlockItem(block: Block, properties: Properties) : BlockItem(block, properties), GeoItem {

    private val cache: AnimatableInstanceCache = GeckoLibUtil.createInstanceCache(this)

    override fun registerControllers(p0: AnimatableManager.ControllerRegistrar) {
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return this.cache
    }

    // Utilise our own render hook to define our custom renderer
    override fun createGeoRenderer(consumer: Consumer<GeoRenderProvider>) {
        consumer.accept(object : GeoRenderProvider {
            private var renderer: AnemoneBlockItemRenderer? = null

            override fun getGeoItemRenderer(): GeoItemRenderer<*>? {
                if (this.renderer == null) this.renderer = AnemoneBlockItemRenderer()

                return this.renderer
            }
        })
    }

}