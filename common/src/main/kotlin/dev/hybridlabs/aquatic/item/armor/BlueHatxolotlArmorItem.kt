package dev.hybridlabs.aquatic.item.armor

import dev.hybridlabs.aquatic.item.HybridAquaticArmorMaterials
import net.minecraft.world.item.ArmorItem
import software.bernie.geckolib.animatable.GeoItem
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.util.GeckoLibUtil

abstract class BlueHatxolotlArmorItem(type: Type, settings: Properties) :
    ArmorItem(HybridAquaticArmorMaterials.HATXOLOTL, type, settings),
    GeoItem {
    protected val cache: AnimatableInstanceCache = GeckoLibUtil.createInstanceCache(this)


    override fun registerControllers(registrar: AnimatableManager.ControllerRegistrar) {
    }


    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return cache
    }
}
