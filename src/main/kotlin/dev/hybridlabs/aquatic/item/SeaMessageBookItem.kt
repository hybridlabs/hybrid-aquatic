package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.block.SeaMessage
import dev.hybridlabs.aquatic.registry.HybridAquaticRegistryKeys
import net.minecraft.client.item.TooltipContext
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtElement
import net.minecraft.registry.DynamicRegistryManager
import net.minecraft.stat.Stats
import net.minecraft.text.Text
import net.minecraft.util.Formatting
import net.minecraft.util.Hand
import net.minecraft.util.Identifier
import net.minecraft.util.TypedActionResult
import net.minecraft.world.World

class SeaMessageBookItem(settings: Settings) : Item(settings) {
    override fun getName(stack: ItemStack): Text {
        // TODO book title - possible with item components? registry manager not present here (FUTURE)
        return super.getName(stack)
    }

    override fun appendTooltip(stack: ItemStack, world: World?, tooltip: MutableList<Text>, context: TooltipContext) {
        world?.registryManager?.let { registryManager ->
            val message = getSeaMessage(stack, registryManager) ?: return@let
            message.author.ifPresent { author ->
                tooltip.add(Text.translatable("book.byAuthor", author).formatted(Formatting.GRAY))
            }
        }
    }

    override fun use(world: World, player: PlayerEntity, hand: Hand): TypedActionResult<ItemStack> {
        val stack = player.getStackInHand(hand)
        player.useBook(stack, hand)
        player.incrementStat(Stats.USED.getOrCreateStat(this))
        return TypedActionResult.success(stack, world.isClient)
    }

    companion object {
        const val SEA_MESSAGE_KEY: String = "sea_message"

        private fun setSeaMessage(stack: ItemStack, message: SeaMessage, registryManager: DynamicRegistryManager): ItemStack {
            val id = message.getId(registryManager) ?: return stack
			val nbt = stack.orCreateNbt
            nbt.putString(SEA_MESSAGE_KEY, id.toString())
            return stack
        }

        fun getSeaMessage(stack: ItemStack, registryManager: DynamicRegistryManager): SeaMessage? {
            val nbt = stack.nbt ?: return null

            if (!nbt.contains(SEA_MESSAGE_KEY, NbtElement.STRING_TYPE.toInt())) {
                return null
            }

            val unparsedId = nbt.getString(SEA_MESSAGE_KEY)
            val id = Identifier.tryParse(unparsedId) ?: return null
            val registry = registryManager.get(HybridAquaticRegistryKeys.SEA_MESSAGE)
            return registry.get(id)
        }

        fun createItemStack(message: SeaMessage, registryManager: DynamicRegistryManager): ItemStack {
            val stack = ItemStack(HybridAquaticItems.SEA_MESSAGE_BOOK)
            setSeaMessage(stack, message, registryManager)
            return stack
        }
    }
}
