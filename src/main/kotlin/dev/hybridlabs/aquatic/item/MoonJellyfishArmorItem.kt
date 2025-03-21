package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.client.GeoRenderProviderStorage
import net.minecraft.client.item.TooltipContext
import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterial
import net.minecraft.item.ItemStack
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import net.minecraft.world.World
import software.bernie.geckolib3.GeoItem
import software.bernie.geckolib3.util.GeckoLibUtil
import java.util.function.Consumer
import java.util.function.Supplier

class MoonJellyfishArmorItem(material: ArmorMaterial, type: Type, settings: Settings) : ArmorItem(material, type, settings), GeoItem {
    private val cache = GeckoLibUtil.createFactory(this)
    private val renderProvider: Supplier<Any> = GeoItem.makeRenderer(this)

    override fun createRenderer(consumer: Consumer<Any>) {
        consumer.accept(GeoRenderProviderStorage.moonjellyfishArmorRenderProvider.invoke())
    }

    override fun registerControllers(registrar: AnimationData) {
    }

    override fun getRenderProvider(): Supplier<Any> {
        return renderProvider
    }

    override fun getFactory(): AnimationFactory {
        return cache
    }

    override fun appendTooltip(stack: ItemStack, world: World?, tooltip: MutableList<Text>, context: TooltipContext) {
        val jellyfishHatText = Text.translatable("item.hybrid-aquatic.moon_jellyfish_hat.description").formatted(Formatting.GRAY)

        tooltip.add(jellyfishHatText)
        super.appendTooltip(stack, world, tooltip, context)
    }
}
