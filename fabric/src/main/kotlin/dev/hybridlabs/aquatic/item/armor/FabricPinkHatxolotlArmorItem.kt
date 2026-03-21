package dev.hybridlabs.aquatic.item.armor

import dev.hybridlabs.aquatic.client.render.GeoRenderProviderStorage
import software.bernie.geckolib.animatable.GeoItem
import java.util.function.Consumer
import java.util.function.Supplier

class FabricPinkHatxolotlArmorItem(type: Type, settings: Properties) :
    PinkHatxolotlArmorItem(type, settings) {
    private val renderProvider: Supplier<Any> = GeoItem.makeRenderer(this)

    override fun createRenderer(consumer: Consumer<Any>) {
        consumer.accept(GeoRenderProviderStorage.pinkHatxolotlArmorRenderProvider.invoke())
    }

    override fun getRenderProvider(): Supplier<Any> {
        return renderProvider
    }
}
