package dev.hybridlabs.aquatic.client.render.item

import dev.hybridlabs.aquatic.client.render.armor.*
import net.minecraft.client.model.HumanoidModel
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.item.ItemStack
import software.bernie.geckolib.animatable.client.RenderProvider
import software.bernie.geckolib.renderer.GeoArmorRenderer

object HAGeoRendererRegistry {
    init {
        GeoRenderProviderStorage.divingArmorRenderProvider =
            createBasicRenderProvider(::DivingArmorRenderer)
        GeoRenderProviderStorage.reinforcedDivingArmorRenderProvider =
            createBasicRenderProvider(::ReinforcedDivingArmorRenderer)
        GeoRenderProviderStorage.glowingDivingArmorRenderProvider =
            createBasicRenderProvider(::GlowingDivingArmorRenderer)
        GeoRenderProviderStorage.seashellArmorRenderProvider =
            createBasicRenderProvider(::SeashellArmorRenderer)
        GeoRenderProviderStorage.turtleArmorRenderProvider =
            createBasicRenderProvider(::TurtleArmorRenderer)

        //Cosmetics
        GeoRenderProviderStorage.manglerfishArmorRenderProvider =
            createBasicRenderProvider(::ManglerfishArmorRenderer)
        GeoRenderProviderStorage.eelArmorRenderProvider =
            createBasicRenderProvider(::EelArmorRenderer)
        GeoRenderProviderStorage.pinkHatxolotlArmorRenderProvider =
            createBasicRenderProvider(::PinkHatxolotlArmorRenderer)
        GeoRenderProviderStorage.goldHatxolotlArmorRenderProvider =
            createBasicRenderProvider(::GoldHatxolotlArmorRenderer)
        GeoRenderProviderStorage.brownHatxolotlArmorRenderProvider =
            createBasicRenderProvider(::BrownHatxolotlArmorRenderer)
        GeoRenderProviderStorage.cyanHatxolotlArmorRenderProvider =
            createBasicRenderProvider(::CyanHatxolotlArmorRenderer)
        GeoRenderProviderStorage.blueHatxolotlArmorRenderProvider =
            createBasicRenderProvider(::BlueHatxolotlArmorRenderer)
        GeoRenderProviderStorage.moonjellyfishArmorRenderProvider =
            createBasicRenderProvider(::MoonJellyfishArmorRenderer)
    }

    private fun createBasicRenderProvider(rendererProvider: () -> GeoArmorRenderer<*>): () -> RenderProvider {
        return {
            object : RenderProvider {
                private val renderer: GeoArmorRenderer<*> by lazy(rendererProvider)

                override fun getHumanoidArmorModel(
                    livingEntity: LivingEntity,
                    itemStack: ItemStack,
                    equipmentSlot: EquipmentSlot,
                    original: HumanoidModel<LivingEntity>
                ): HumanoidModel<LivingEntity> {
                    renderer.prepForRender(livingEntity, itemStack, equipmentSlot, original)
                    return renderer as HumanoidModel<LivingEntity>
                }
            }
        }
    }
}