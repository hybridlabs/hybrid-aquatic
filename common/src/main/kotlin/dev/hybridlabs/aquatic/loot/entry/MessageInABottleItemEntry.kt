package dev.hybridlabs.aquatic.loot.entry

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonObject
import dev.hybridlabs.aquatic.block.MessageInABottleBlock
import dev.hybridlabs.aquatic.block.entity.MessageInABottleBlockEntity
import dev.hybridlabs.aquatic.item.HAItems
import dev.hybridlabs.aquatic.item.SeaMessageBookItem
import dev.hybridlabs.aquatic.registry.HARegistryKeys
import net.minecraft.nbt.CompoundTag
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.storage.loot.LootContext
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryType
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer
import net.minecraft.world.level.storage.loot.functions.LootItemFunction
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition
import java.util.function.Consumer

class MessageInABottleItemEntry(
    weight: Int,
    quality: Int,
    conditions: Array<LootItemCondition>,
    functions: Array<LootItemFunction>
) : LootPoolSingletonContainer(weight, quality, conditions, functions) {
    override fun getType(): LootPoolEntryType {
        return HybridAquaticLootPoolEntryTypes.MESSAGE_IN_A_BOTTLE.get()
    }

    public override fun createItemStack(consumer: Consumer<ItemStack?>, context: LootContext) {
        val world = context.level
        val random = context.random
        val registryManager = world.registryAccess()
        val registry = registryManager.registryOrThrow(HARegistryKeys.SEA_MESSAGE)
        registry.getRandom(random).ifPresent { messageEntry ->
            val message = messageEntry.value()

            val stack = ItemStack(HAItems.MESSAGE_IN_A_BOTTLE.get())
            stack.getOrCreateTagElement(BlockItem.BLOCK_ENTITY_TAG).apply {
                val variants = MessageInABottleBlock.Variant.entries
                putString(MessageInABottleBlockEntity.VARIANT_KEY, variants[random.nextInt(variants.size)].id)

                val bookStack = SeaMessageBookItem.createItemStack(message, registryManager)
                put(MessageInABottleBlockEntity.MESSAGE_KEY, bookStack.save(CompoundTag()))
            }

            consumer.accept(stack)
        }
    }

    class Serializer : LootPoolSingletonContainer.Serializer<MessageInABottleItemEntry>() {
        override fun deserialize(
            json: JsonObject,
            context: JsonDeserializationContext,
            weight: Int,
            quality: Int,
            conditions: Array<LootItemCondition>,
            functions: Array<LootItemFunction>
        ): MessageInABottleItemEntry {
            return MessageInABottleItemEntry(weight, quality, conditions, functions)
        }
    }

    companion object {
        fun builder(): Builder<*> {
            return simpleBuilder(::MessageInABottleItemEntry)
        }
    }
}
