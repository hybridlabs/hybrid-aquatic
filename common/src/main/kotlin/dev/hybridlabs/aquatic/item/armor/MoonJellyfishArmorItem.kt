package dev.hybridlabs.aquatic.item.armor

import dev.hybridlabs.aquatic.item.HybridAquaticArmorMaterials
import net.minecraft.ChatFormatting
import net.minecraft.network.chat.Component
import net.minecraft.world.item.ArmorItem
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.level.Level
import software.bernie.geckolib.animatable.GeoItem
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.util.GeckoLibUtil

abstract class MoonJellyfishArmorItem(type: Type, settings: Properties) :
    ArmorItem(HybridAquaticArmorMaterials.MOONJELLYFISH, type, settings),
    GeoItem {
    protected val cache: AnimatableInstanceCache = GeckoLibUtil.createInstanceCache(this)

    override fun registerControllers(registrar: AnimatableManager.ControllerRegistrar) {
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return cache
    }

    override fun appendHoverText(
        stack: ItemStack,
        level: Level?,
        tooltipComponents: MutableList<Component?>,
        isAdvanced: TooltipFlag
    ) {
        val jellyfishHatText =
            Component.translatable("item.hybrid-aquatic.moon_jellyfish_hat.description").withStyle(ChatFormatting.GRAY)

        tooltipComponents.add(jellyfishHatText)
        super.appendHoverText(stack, level, tooltipComponents, isAdvanced)
    }
}
