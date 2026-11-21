package dev.hybridlabs.aquatic.item.cosmetic

import dev.hybridlabs.aquatic.client.render.armor.BlueHatxolotlArmorRenderer
import dev.hybridlabs.hapi.item.HAPIArmorMaterials
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.Item
import net.minecraft.world.item.equipment.ArmorType
import net.minecraft.world.item.ItemStack
import software.bernie.geckolib.animatable.GeoItem
import software.bernie.geckolib.animatable.client.GeoRenderProvider
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.animatable.manager.AnimatableManager
import software.bernie.geckolib.renderer.GeoArmorRenderer
import software.bernie.geckolib.util.GeckoLibUtil
import java.util.function.Consumer

class BlueHatxolotlArmorItem(type: ArmorType, settings: Properties) :
    Item(settings.humanoidArmor(HAPIArmorMaterials.HATXOLOTL, type)), GeoItem {
    private val cache: AnimatableInstanceCache = GeckoLibUtil.createInstanceCache(this)
    
    override fun registerControllers(registrar: AnimatableManager.ControllerRegistrar) {
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return cache
    }

    override fun createGeoRenderer(consumer: Consumer<GeoRenderProvider>) {
        consumer.accept(object : GeoRenderProvider {
            private var renderer: GeoArmorRenderer<*, *>? = null

            override fun getGeoArmorRenderer(itemStack: ItemStack, equipmentSlot: EquipmentSlot): GeoArmorRenderer<*, *>? {
                if (this.renderer == null)
                    this.renderer = BlueHatxolotlArmorRenderer()
                return this.renderer
            }
        })
    }
}
