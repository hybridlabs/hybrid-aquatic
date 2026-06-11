package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.block.SeaMessage
import dev.hybridlabs.aquatic.registry.HARegistryKeys
import net.minecraft.ChatFormatting
import net.minecraft.core.RegistryAccess
import net.minecraft.nbt.Tag
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.stats.Stats
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.level.Level

class
SeaMessageBookItem(settings: Properties) : Item(settings) {

    override fun appendHoverText(
        stack: ItemStack,
        world: Level?,
        tooltip: MutableList<Component>,
        context: TooltipFlag
    ) {
        world?.registryAccess()?.let { registryManager ->
            val message = getSeaMessage(stack, registryManager) ?: return@let
            message.author.ifPresent { author ->
                tooltip.add(Component.translatable("book.byAuthor", author).withStyle(ChatFormatting.GRAY))
            }
        }
    }

    override fun use(world: Level, player: Player, hand: InteractionHand): InteractionResultHolder<ItemStack> {
        val stack = player.getItemInHand(hand)
        player.openItemGui(stack, hand)
        player.awardStat(Stats.ITEM_USED.get(this))
        return InteractionResultHolder.sidedSuccess(stack, world.isClientSide)
    }

    companion object {
        const val SEA_MESSAGE_KEY: String = "sea_message"

        private fun setSeaMessage(
            stack: ItemStack,
            message: SeaMessage,
            registryManager: RegistryAccess
        ): ItemStack {
            val id = message.getId(registryManager) ?: return stack
            val nbt = stack.orCreateTag
            nbt.putString(SEA_MESSAGE_KEY, id.toString())
            return stack
        }

        fun getSeaMessage(stack: ItemStack, registryManager: RegistryAccess): SeaMessage? {
            val nbt = stack.tag ?: return null

            if (!nbt.contains(SEA_MESSAGE_KEY, Tag.TAG_STRING.toInt())) {
                return null
            }

            val unparsedId = nbt.getString(SEA_MESSAGE_KEY)
            val id = ResourceLocation.tryParse(unparsedId) ?: return null
            val registry = registryManager.registryOrThrow(HARegistryKeys.SEA_MESSAGE)
            return registry.get(id)
        }

        fun createItemStack(message: SeaMessage, registryManager: RegistryAccess): ItemStack {
            val stack = ItemStack(HAItems.SEA_MESSAGE_BOOK.get())
            setSeaMessage(stack, message, registryManager)
            return stack
        }
    }
}