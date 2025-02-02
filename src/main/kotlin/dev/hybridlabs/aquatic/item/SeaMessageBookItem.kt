package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.component.HybridAquaticComponentTypes
import net.minecraft.client.item.TooltipType
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.stat.Stats
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import net.minecraft.util.Hand
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World

class SeaMessageBookItem(settings: Settings) : Item(settings) {
    override fun getName(stack: ItemStack): Text {
        stack.get(HybridAquaticComponentTypes.SEA_MESSAGE)?.also { message ->
            val text = message.key.map { key ->
                val id = key.value
                val namespace = id.namespace
                val path = id.path
                val modId = HybridAquatic.MOD_ID
                Text.translatableWithFallback("$modId.sea_message.$namespace.$path.title", "Sea Message")
            }.orElse(null)

            text?.also { return it }
        }

        return super.getName(stack)
    }

    override fun appendTooltip(stack: ItemStack, context: TooltipContext, tooltip: MutableList<Text>, type: TooltipType) {
        stack.get(HybridAquaticComponentTypes.SEA_MESSAGE)?.also { entry ->
            val message = entry.value()
            message.author.ifPresent { author ->
                tooltip.add(Text.translatable("book.byAuthor", author).formatted(Formatting.GRAY))
            }
        }

        super.appendTooltip(stack, context, tooltip, type)
    }

    override fun use(world: World, player: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        val stack = player.getStackInHand(hand)
        player.useBook(stack, hand)
        player.incrementStat(Stats.USED.getOrCreateStat(this))
        return TypedActionResult.success(stack, world.isClient)
    }
}
