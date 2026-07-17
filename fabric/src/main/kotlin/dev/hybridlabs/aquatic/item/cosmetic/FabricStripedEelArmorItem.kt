package dev.hybridlabs.aquatic.item.cosmetic

import dev.emi.trinkets.api.Trinket
import dev.emi.trinkets.api.TrinketsApi
import dev.hybridlabs.aquatic.client.render.item.GeoRenderProviderStorage
import software.bernie.geckolib.animatable.GeoItem
import java.util.function.Consumer
import java.util.function.Supplier

class FabricStripedEelArmorItem(settings: Properties) :
    Trinket, StripedEelScarfItem(settings) {
    init {
        TrinketsApi.registerTrinket(this, this)
    }

    private val renderProvider: Supplier<Any> = GeoItem.makeRenderer(this)

    override fun createRenderer(consumer: Consumer<Any>) {
        consumer.accept(GeoRenderProviderStorage.stripedEelArmorRenderProvider.invoke())
    }

    override fun getRenderProvider(): Supplier<Any> {
        return renderProvider
    }
}
