package dev.hybridlabs.aquatic.item.armor

import dev.hybridlabs.aquatic.client.render.armor.EelArmorRenderer
import dev.hybridlabs.aquatic.item.HybridAquaticArmorMaterials
import net.minecraft.client.model.HumanoidModel
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.ArmorItem
import net.minecraft.world.item.ItemStack
import software.bernie.geckolib.animatable.GeoItem
import software.bernie.geckolib.animatable.client.GeoRenderProvider
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.animation.AnimatableManager
import software.bernie.geckolib.renderer.GeoArmorRenderer
import software.bernie.geckolib.util.GeckoLibUtil
import java.util.function.Consumer

class EelArmorItem(type: Type, settings: Properties) :
    ArmorItem(HybridAquaticArmorMaterials.EEL, type, settings),
    GeoItem {
    private val cache: AnimatableInstanceCache = GeckoLibUtil.createInstanceCache(this)


    override fun registerControllers(registrar: AnimatableManager.ControllerRegistrar) {
    }


    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return cache
    }

    override fun createGeoRenderer(consumer: Consumer<GeoRenderProvider?>) {
        consumer.accept(object : GeoRenderProvider {
            private var renderer: GeoArmorRenderer<*>? = null

            override fun <T : LivingEntity?> getGeoArmorRenderer(
                livingEntity: T?,
                itemStack: ItemStack?,
                equipmentSlot: EquipmentSlot?,
                original: HumanoidModel<T?>?
            ): HumanoidModel<*>? {
                if (this.renderer == null)
                    this.renderer = EelArmorRenderer()
                return this.renderer
            }
        })
    }

}
