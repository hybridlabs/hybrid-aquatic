package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.block.SeaMessage
import dev.hybridlabs.aquatic.registry.HybridAquaticRegistryKeys
import net.minecraft.ChatFormatting
import net.minecraft.core.HolderLookup
import net.minecraft.core.RegistryAccess
import net.minecraft.core.component.DataComponents
import net.minecraft.nbt.CompoundTag
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
import net.minecraft.world.item.component.CustomData
import net.minecraft.world.level.Level

class
SeaMessageBookItem(settings: Properties) : Item(settings) {
    override fun getName(stack: ItemStack): Component {
        // TODO book title - possible with item components? registry manager not present here (FUTURE)
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
            val nbt = CompoundTag()
            nbt.putString(SEA_MESSAGE_KEY, id.toString())
            stack.set(DataComponents.CUSTOM_DATA,CustomData.of(nbt))
            return stack
        }

        fun getSeaMessage(stack: ItemStack, registryManager: HolderLookup.Provider): SeaMessage? {
            val customData = stack.components.get(DataComponents.CUSTOM_DATA) ?: return null

            if (!customData.contains(SEA_MESSAGE_KEY)){
                return null
            }

            val unparsedId = customData.copyTag().getString(SEA_MESSAGE_KEY)
            val id = ResourceLocation.tryParse(unparsedId) ?: return null
            val registry = registryManager.lookup(HybridAquaticRegistryKeys.SEA_MESSAGE).get()
            return registry.getOrThrow(ResourceKey.create(HybridAquaticRegistryKeys.SEA_MESSAGE,id)).value()
        }

        fun createItemStack(message: SeaMessage, registryManager: RegistryAccess): ItemStack {
            val stack = ItemStack(HybridAquaticItems.SEA_MESSAGE_BOOK.get())
            setSeaMessage(stack, message, registryManager)
            return stack
        }
    }
}
