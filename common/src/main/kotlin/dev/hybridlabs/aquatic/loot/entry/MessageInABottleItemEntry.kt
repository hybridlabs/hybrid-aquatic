package dev.hybridlabs.aquatic.loot.entry

import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import dev.hybridlabs.aquatic.block.MessageInABottleBlock
import dev.hybridlabs.aquatic.block.entity.MessageInABottleBlockEntity
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import dev.hybridlabs.aquatic.item.SeaMessageBookItem
import dev.hybridlabs.aquatic.registry.HybridAquaticRegistryKeys
import net.minecraft.core.component.DataComponents
import net.minecraft.nbt.CompoundTag
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.component.CustomData
import net.minecraft.world.level.storage.loot.LootContext
import net.minecraft.world.level.storage.loot.entries.LootPoolEntryType
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer
import net.minecraft.world.level.storage.loot.functions.LootItemFunction
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition
import java.util.function.Consumer


class MessageInABottleItemEntry(
    weight: Int,
    quality: Int,
    conditions: List<LootItemCondition>,
    functions: List<LootItemFunction>
) : LootPoolSingletonContainer(weight, quality, conditions, functions) {
    override fun getType(): LootPoolEntryType {
        return HybridAquaticLootPoolEntryTypes.MESSAGE_IN_A_BOTTLE.get()
    }

    public override fun createItemStack(consumer: Consumer<ItemStack?>, context: LootContext) {
        val world = context.level
        val random = context.random
        val registryManager = world.registryAccess()
        val registry = registryManager.registryOrThrow(HybridAquaticRegistryKeys.SEA_MESSAGE)
        registry.getRandom(random).ifPresent { messageEntry ->
            val message = messageEntry.value()

            val stack = ItemStack(HybridAquaticItems.MESSAGE_IN_A_BOTTLE.get())

            val stuff = CompoundTag().apply {
                val variants = MessageInABottleBlock.Variant.entries
                putString(MessageInABottleBlockEntity.VARIANT_KEY, variants[random.nextInt(variants.size)].id)

                val bookStack = SeaMessageBookItem.createItemStack(message, registryManager)
                put(MessageInABottleBlockEntity.MESSAGE_KEY, bookStack.save(registryManager, CompoundTag()))
            }
            stack.set(DataComponents.CUSTOM_DATA, CustomData.of(stuff))

            consumer.accept(stack)
        }
    }


    companion object {
        val CODEC: MapCodec<MessageInABottleItemEntry> = RecordCodecBuilder.mapCodec {
            inst -> singletonFields(inst).apply(inst, ::MessageInABottleItemEntry)
        }

        fun builder(): Builder<*> {
            return simpleBuilder(::MessageInABottleItemEntry)
        }
    }
}
