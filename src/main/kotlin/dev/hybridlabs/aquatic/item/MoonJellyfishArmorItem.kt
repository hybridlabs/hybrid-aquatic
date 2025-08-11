package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.client.GeoRenderProviderStorage
import net.minecraft.item.ArmorItem
import net.minecraft.item.ItemStack
import net.minecraft.item.tooltip.TooltipType
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import software.bernie.geckolib.animatable.GeoItem
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.animation.AnimatableManager
import software.bernie.geckolib.util.GeckoLibUtil
import java.util.function.Consumer
import java.util.function.Supplier

class MoonJellyfishArmorItem(material: HybridAquaticArmorMaterials, type: Type, settings: Settings) : ArmorItem(material, type, settings), GeoItem {
    private val cache: AnimatableInstanceCache = GeckoLibUtil.createInstanceCache(this)
    private val renderProvider: Supplier<Any> = GeoItem.makeRenderer(this)

    override fun createRenderer(consumer: Consumer<Any>) {
        consumer.accept(GeoRenderProviderStorage.moonjellyfishArmorRenderProvider.invoke())
    }

    override fun registerControllers(registrar: AnimatableManager.ControllerRegistrar) {
    }

    override fun getRenderProvider(): Supplier<Any> {
        return renderProvider
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return cache
    }

    override fun appendTooltip(
        stack: ItemStack,
        context: TooltipContext,
        tooltip: MutableList<Text>,
        type: TooltipType?
    ) {
        val jellyfishHatText = Text.translatable("item.hybrid-aquatic.moon_jellyfish_hat.description").formatted(Formatting.GRAY)

        tooltip.add(jellyfishHatText)
        super.appendTooltip(stack, context, tooltip, type)
    }
}
