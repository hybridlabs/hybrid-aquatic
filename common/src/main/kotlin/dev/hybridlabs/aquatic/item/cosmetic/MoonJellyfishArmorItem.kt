package dev.hybridlabs.aquatic.item.cosmetic

import dev.hybridlabs.aquatic.client.render.armor.MoonJellyfishArmorRenderer
import dev.hybridlabs.hapi.item.HAPIArmorMaterials
import net.minecraft.ChatFormatting
import net.minecraft.network.chat.Component
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.item.Item
import net.minecraft.world.item.equipment.ArmorType
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.component.TooltipDisplay
import net.minecraft.world.item.TooltipFlag
import software.bernie.geckolib.animatable.GeoItem
import software.bernie.geckolib.animatable.client.GeoRenderProvider
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.animatable.manager.AnimatableManager
import software.bernie.geckolib.renderer.GeoArmorRenderer
import software.bernie.geckolib.util.GeckoLibUtil
import java.util.function.Consumer

class MoonJellyfishArmorItem(type: ArmorType, settings: Properties) :
    Item(settings.humanoidArmor(HAPIArmorMaterials.MOONJELLYFISH, type)), GeoItem {
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
                    this.renderer = MoonJellyfishArmorRenderer()
                return this.renderer
            }
        })
    }

    override fun appendHoverText(
        stack: ItemStack,
        context: TooltipContext,
        tooltipDisplay: TooltipDisplay,
        tooltipComponents: Consumer<Component>,
        isAdvanced: TooltipFlag
    ) {
        val jellyfishHatText =
            Component.translatable("item.hybrid_aquatic.moon_jellyfish_hat.description").withStyle(ChatFormatting.GRAY)

        tooltipComponents.accept(jellyfishHatText)
        super.appendHoverText(stack, context, tooltipDisplay, tooltipComponents, isAdvanced)
    }
}