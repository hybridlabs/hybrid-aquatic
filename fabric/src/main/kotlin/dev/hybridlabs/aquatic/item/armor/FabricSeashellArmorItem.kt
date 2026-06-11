package dev.hybridlabs.aquatic.item.armor

import dev.hybridlabs.aquatic.client.render.item.GeoRenderProviderStorage
import software.bernie.geckolib.animatable.GeoItem
import java.util.function.Consumer
import java.util.function.Supplier

class FabricSeashellArmorItem(type: Type, settings: Properties) :
    SeashellArmorItem(type, settings), GeoItem {
    private val renderProvider: Supplier<Any> = GeoItem.makeRenderer(this)

    override fun createRenderer(consumer: Consumer<Any>) {
        consumer.accept(GeoRenderProviderStorage.seashellArmorRenderProvider.invoke())
    }

    override fun getRenderProvider(): Supplier<Any> {
        return renderProvider
    }
}
