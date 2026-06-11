package dev.hybridlabs.aquatic.item.cosmetic

import dev.hybridlabs.aquatic.client.render.item.GeoRenderProviderStorage
import dev.hybridlabs.aquatic.item.armor.GoldHatxolotlArmorItem
import software.bernie.geckolib.animatable.GeoItem
import java.util.function.Consumer
import java.util.function.Supplier

class FabricGoldHatxolotlArmorItem(settings: Properties) :
    GoldHatxolotlArmorItem(settings) {
    private val renderProvider: Supplier<Any> = GeoItem.makeRenderer(this)

    override fun createRenderer(consumer: Consumer<Any>) {
        consumer.accept(GeoRenderProviderStorage.goldHatxolotlArmorRenderProvider.invoke())
    }

    override fun getRenderProvider(): Supplier<Any> {
        return renderProvider
    }
}
