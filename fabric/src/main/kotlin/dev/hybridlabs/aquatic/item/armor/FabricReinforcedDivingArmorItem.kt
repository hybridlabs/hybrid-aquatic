package dev.hybridlabs.aquatic.item.armor

import dev.hybridlabs.aquatic.client.render.GeoRenderProviderStorage
import software.bernie.geckolib.animatable.GeoItem
import java.util.function.Consumer
import java.util.function.Supplier

class FabricReinforcedDivingArmorItem(type: Type, settings: Properties) :
    ReinforcedDivingArmorItem(type, settings),
    GeoItem {
    private val renderProvider: Supplier<Any> = GeoItem.makeRenderer(this)

    override fun createRenderer(consumer: Consumer<Any>) {
        consumer.accept(GeoRenderProviderStorage.reinforcedDivingArmorRenderProvider.invoke())
    }

    override fun getRenderProvider(): Supplier<Any> {
        return renderProvider
    }
}
