package dev.hybridlabs.aquatic.loot.entry

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonObject
import dev.hybridlabs.aquatic.block.MessageInABottleBlock
import dev.hybridlabs.aquatic.block.entity.MessageInABottleBlockEntity
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import dev.hybridlabs.aquatic.registry.HybridAquaticRegistryKeys
import net.minecraft.item.BlockItem
import net.minecraft.item.ItemStack
import net.minecraft.loot.condition.LootCondition
import net.minecraft.loot.context.LootContext
import net.minecraft.loot.entry.LeafEntry
import net.minecraft.loot.entry.LootPoolEntryType
import net.minecraft.loot.function.LootFunction
import net.minecraft.nbt.NbtCompound
import java.util.function.Consumer

class MessageInABottleItemEntry(
    weight: Int,
    quality: Int,
    conditions: Array<LootCondition>,
    functions: Array<LootFunction>
) : LeafEntry(weight, quality, conditions, functions) {
    override fun getType(): LootPoolEntryType {
        return HybridAquaticLootPoolEntryTypes.MESSAGE_IN_A_BOTTLE
    }

    public override fun generateLoot(consumer: Consumer<ItemStack>, context: LootContext) {
        val world = context.world
        val random = context.random
        val registry = world.registryManager.get(HybridAquaticRegistryKeys.SEA_MESSAGE)
        registry.getRandom(random).ifPresent { messageEntry ->
            val message = messageEntry.value()

            val stack = ItemStack(HybridAquaticItems.MESSAGE_IN_A_BOTTLE)
            stack.getOrCreateSubNbt(BlockItem.BLOCK_ENTITY_TAG_KEY).apply {
                val variants = MessageInABottleBlock.Variant.entries
                putString(MessageInABottleBlockEntity.VARIANT_KEY, variants[random.nextInt(variants.size)].id)

                val bookStack = message.createBookItemStack()
                put(MessageInABottleBlockEntity.MESSAGE_KEY, bookStack.writeNbt(NbtCompound()))
            }

            consumer.accept(stack)
        }
    }

    class Serializer : LeafEntry.Serializer<MessageInABottleItemEntry>() {
        override fun fromJson(
            json: JsonObject,
            context: JsonDeserializationContext,
            weight: Int,
            quality: Int,
            conditions: Array<LootCondition>,
            functions: Array<LootFunction>
        ): MessageInABottleItemEntry {
            return MessageInABottleItemEntry(weight, quality, conditions, functions)
        }
    }

    companion object {
        fun builder(): Builder<*> {
            return builder(::MessageInABottleItemEntry)
        }
    }
}
