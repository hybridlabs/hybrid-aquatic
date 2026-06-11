package dev.hybridlabs.aquatic.item.armor

import dev.emi.trinkets.api.Trinket
import dev.emi.trinkets.api.TrinketsApi
import dev.hybridlabs.aquatic.client.render.item.GeoRenderProviderStorage
import dev.hybridlabs.aquatic.item.cosmetic.EelArmorItem
import software.bernie.geckolib.animatable.GeoItem
import java.util.function.Consumer
import java.util.function.Supplier

class FabricEelArmorItem(settings: Properties) :
    Trinket, EelArmorItem(settings) {
    init {
        TrinketsApi.registerTrinket(this, this)
    }

    private val renderProvider: Supplier<Any> = GeoItem.makeRenderer(this)

    override fun createRenderer(consumer: Consumer<Any>) {
        consumer.accept(GeoRenderProviderStorage.eelArmorRenderProvider.invoke())
    }

    override fun getRenderProvider(): Supplier<Any> {
        return renderProvider
    }
}
