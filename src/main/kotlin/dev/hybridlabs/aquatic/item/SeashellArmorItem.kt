package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.client.GeoRenderProviderStorage
import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterial
import software.bernie.geckolib.animatable.GeoItem
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.animation.AnimatableManager
import software.bernie.geckolib.util.GeckoLibUtil
import java.util.function.Supplier

class SeashellArmorItem(material: ArmorMaterial, type: Type, settings: Settings) : ArmorItem(material, type, settings), GeoItem {
    private val cache: AnimatableInstanceCache = GeckoLibUtil.createInstanceCache(this)
    private val renderProvider: Supplier<Any> = Supplier { GeoRenderProviderStorage.seashellArmorRenderProvider.invoke() }


    override fun registerControllers(registrar: AnimatableManager.ControllerRegistrar) {
    }

    override fun getRenderProvider(): Supplier<Any> {
        return renderProvider
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return cache
    }
}
