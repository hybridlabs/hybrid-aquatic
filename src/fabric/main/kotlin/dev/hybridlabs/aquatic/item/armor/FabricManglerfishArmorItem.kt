package dev.hybridlabs.aquatic.item.armor

import dev.hybridlabs.aquatic.client.render.GeoRenderProviderStorage
import software.bernie.geckolib.animatable.GeoItem
import java.util.function.Consumer
import java.util.function.Supplier

class FabricManglerfishArmorItem(type: Type, settings: Properties) :
    ManglerfishArmorItem(type, settings), GeoItem {
    private val renderProvider: Supplier<Any> = GeoItem.makeRenderer(this)

    override fun createRenderer(consumer: Consumer<Any>) {
        consumer.accept(GeoRenderProviderStorage.manglerfishArmorRenderProvider.invoke())
    }

    override fun getRenderProvider(): Supplier<Any> {
        return renderProvider
    }
}
