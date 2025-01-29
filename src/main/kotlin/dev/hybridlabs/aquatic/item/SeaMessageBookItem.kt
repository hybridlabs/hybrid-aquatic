package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.block.SeaMessage
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.tooltip.TooltipType
import net.minecraft.registry.DynamicRegistryManager
import net.minecraft.stat.Stats
import net.minecraft.text.Text
import net.minecraft.util.ActionResult
import net.minecraft.util.Formatting
import net.minecraft.util.Hand
import net.minecraft.world.World

class SeaMessageBookItem(settings: Settings) : Item(settings) {
    override fun getName(stack: ItemStack): Text {
        // TODO book title - possible with item components? registry manager not present here (FUTURE)
        return super.getName(stack)
    }

    override fun appendTooltip(stack: ItemStack?, context: TooltipContext?, tooltip: List<Text?>?, type: TooltipType?) {
        super.appendTooltip(stack, context, tooltip, type)
    }

    override fun appendTooltip(stack: ItemStack, world: World?, tooltip: MutableList<Text>, context: TooltipContext) {
        world?.registryManager?.let { registryManager ->
            val message = getSeaMessage(stack, registryManager) ?: return@let
            message.author.ifPresent { author ->
                tooltip.add(Text.translatable("book.byAuthor", author).formatted(Formatting.GRAY))
            }
        }
    }

    override fun use(world: World, user: PlayerEntity, hand: Hand): ActionResult {
        val itemStack = user.getStackInHand(hand)
        user.useBook(itemStack, hand)
        user.incrementStat(Stats.USED.getOrCreateStat(this))
        return ActionResult.SUCCESS
    }

    companion object {
        const val SEA_MESSAGE_KEY: String = "sea_message"

        fun createItemStack(message: SeaMessage, registryManager: DynamicRegistryManager): ItemStack {
            val stack = ItemStack(HybridAquaticItems.SEA_MESSAGE_BOOK)
            setSeaMessage(stack, message, registryManager)
            return stack
        }
    }
}
