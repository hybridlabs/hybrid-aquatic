package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.client.GeoRenderProviderStorage
import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterial
import software.bernie.geckolib3.GeoItem
import software.bernie.geckolib3.util.GeckoLibUtil
import java.util.function.Consumer
import java.util.function.Supplier

class SeashellArmorItem(material: ArmorMaterial, type: Type, settings: Settings) : ArmorItem(material, type, settings), GeoItem {
    private val cache = GeckoLibUtil.createFactory(this)
    private val renderProvider: Supplier<Any> = GeoItem.makeRenderer(this)

    override fun createRenderer(consumer: Consumer<Any>) {
        consumer.accept(GeoRenderProviderStorage.seashellArmorRenderProvider.invoke())
    }

    override fun registerControllers(registrar: AnimationData) {
    }

    override fun getRenderProvider(): Supplier<Any> {
        return renderProvider
    }

    override fun getFactory(): AnimationFactory {
        return cache
    }
}
