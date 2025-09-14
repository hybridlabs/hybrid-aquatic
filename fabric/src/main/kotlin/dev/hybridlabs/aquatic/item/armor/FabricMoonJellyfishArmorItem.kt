package dev.hybridlabs.aquatic.item.armor

import dev.hybridlabs.aquatic.client.render.GeoRenderProviderStorage
import dev.hybridlabs.aquatic.item.MoonJellyfishArmorItem
import software.bernie.geckolib.animatable.GeoItem
import java.util.function.Consumer
import java.util.function.Supplier

class FabricMoonJellyfishArmorItem(type: Type, settings: Properties) :
    MoonJellyfishArmorItem(type, settings), GeoItem {
    private val renderProvider: Supplier<Any> = GeoItem.makeRenderer(this)

    override fun createRenderer(consumer: Consumer<Any>) {
        consumer.accept(GeoRenderProviderStorage.moonjellyfishArmorRenderProvider.invoke())
    }

    override fun getRenderProvider(): Supplier<Any> {
        return renderProvider
    }
}
