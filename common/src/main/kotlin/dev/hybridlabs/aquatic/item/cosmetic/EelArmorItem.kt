package dev.hybridlabs.aquatic.item.cosmetic

import dev.hybridlabs.aquatic.item.HAArmorMaterials
import net.minecraft.world.item.ArmorItem
import software.bernie.geckolib.animatable.GeoItem
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.animation.AnimatableManager
import software.bernie.geckolib.util.GeckoLibUtil

class EelArmorItem(type: Type, settings: Properties) :
    ArmorItem(HAArmorMaterials.EEL, type, settings), GeoItem {
    private val cache: AnimatableInstanceCache = GeckoLibUtil.createInstanceCache(this)

    override fun registerControllers(registrar: AnimatableManager.ControllerRegistrar) {
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return cache
    }
}