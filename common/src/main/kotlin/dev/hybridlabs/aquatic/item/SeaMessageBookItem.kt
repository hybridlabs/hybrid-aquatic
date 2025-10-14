package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.block.SeaMessage
import dev.hybridlabs.aquatic.registry.HybridAquaticRegistryKeys
import net.minecraft.ChatFormatting
import net.minecraft.core.HolderLookup
import net.minecraft.core.component.DataComponents
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceKey
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
    override fun getName(stack: ItemStack): Component {
        stack.get(DataComponents.WRITTEN_BOOK_CONTENT)?.let{ content->
            if (content.title.get(false) != "") return Component.translatable(content.title.get(false))
        }
        return super.getName(stack)
    }


    override fun appendHoverText(
        stack: ItemStack,
        context: TooltipContext,
        tooltip: MutableList<Component>,
        tooltipFlag: TooltipFlag
    ) {
        context.registries()?.let { registryManager ->
            val message = getSeaMessage(stack, registryManager) ?: return@let
            message.author.ifPresent { author ->
                tooltip.add(Component.translatable("book.byAuthor", author).withStyle(ChatFormatting.GRAY))
            }
        }
    }

    override fun use(world: Level, player: Player, hand: InteractionHand): InteractionResultHolder<ItemStack> {
        val stack = player.getItemInHand(hand)

        if (!stack.has(DataComponents.WRITTEN_BOOK_CONTENT))
            setWrittenBookConent(stack, world.registryAccess())

        player.openItemGui(stack, hand)
        player.awardStat(Stats.ITEM_USED.get(this))
        return InteractionResultHolder.sidedSuccess(stack, world.isClientSide)
    }

    companion object {
        const val SEA_MESSAGE_KEY: String = "sea_message"

        private fun setWrittenBookConent(
            stack: ItemStack,
            registryAccess: HolderLookup.Provider
        ) {
            getSeaMessage(stack, registryAccess)?.let { message ->
                stack.set(DataComponents.WRITTEN_BOOK_CONTENT, message.getWrittenBookContent())
            }
        }

        fun getSeaMessage(stack: ItemStack, registryManager: HolderLookup.Provider): SeaMessage? {
            val customData = stack.get(DataComponents.CUSTOM_DATA)?.copyTag() ?: return null
            val id = ResourceLocation.tryParse(customData.getString(SEA_MESSAGE_KEY)) ?: return null
            val registry = registryManager.lookup(HybridAquaticRegistryKeys.SEA_MESSAGE).get()
            return registry.getOrThrow(ResourceKey.create(HybridAquaticRegistryKeys.SEA_MESSAGE, id)).value()
        }
    }
}
