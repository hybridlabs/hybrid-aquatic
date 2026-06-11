package dev.hybridlabs.aquatic.item.cosmetic

import net.minecraft.world.item.Item
import software.bernie.geckolib.animatable.GeoItem
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.animation.AnimatableManager
import software.bernie.geckolib.util.GeckoLibUtil

abstract class EelScarfItem(settings: Properties) :
    Item(settings),
    GeoItem {
    protected val cache: AnimatableInstanceCache = GeckoLibUtil.createInstanceCache(this)


    override fun registerControllers(registrar: AnimatableManager.ControllerRegistrar) {
    }


    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return cache
    }

}