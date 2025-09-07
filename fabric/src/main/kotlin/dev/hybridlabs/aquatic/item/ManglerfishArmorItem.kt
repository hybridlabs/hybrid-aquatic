package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.client.render.GeoRenderProviderStorage
import net.minecraft.world.item.ArmorItem
import net.minecraft.world.item.ArmorMaterial
import software.bernie.geckolib.animatable.GeoItem
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.util.GeckoLibUtil
import java.util.function.Consumer
import java.util.function.Supplier

class ManglerfishArmorItem(material: ArmorMaterial, type: Type, settings: Properties) :
    ArmorItem(material, type, settings), GeoItem {
    private val cache: AnimatableInstanceCache = GeckoLibUtil.createInstanceCache(this)
    private val renderProvider: Supplier<Any> = GeoItem.makeRenderer(this)

    override fun createRenderer(consumer: Consumer<Any>) {
        consumer.accept(GeoRenderProviderStorage.manglerfishArmorRenderProvider.invoke())
    }

    override fun registerControllers(registrar: AnimatableManager.ControllerRegistrar) {
    }

    override fun getRenderProvider(): Supplier<Any> {
        return renderProvider
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return cache
    }
}
