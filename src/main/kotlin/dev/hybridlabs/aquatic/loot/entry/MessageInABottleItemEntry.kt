package dev.hybridlabs.aquatic.loot.entry

import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import dev.hybridlabs.aquatic.block.MessageInABottleBlock
import dev.hybridlabs.aquatic.component.HybridAquaticComponentTypes
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import dev.hybridlabs.aquatic.item.SeaMessageBookItem
import dev.hybridlabs.aquatic.registry.HybridAquaticRegistryKeys
import net.minecraft.item.ItemStack
import net.minecraft.loot.condition.LootCondition
import net.minecraft.loot.context.LootContext
import net.minecraft.loot.entry.LeafEntry
import net.minecraft.loot.entry.LootPoolEntryType
import net.minecraft.loot.function.LootFunction
import java.util.function.Consumer

class MessageInABottleItemEntry(
    weight: Int,
    quality: Int,
    conditions: List<LootCondition>,
    functions: List<LootFunction>
) : LeafEntry(weight, quality, conditions, functions) {
    override fun getType(): LootPoolEntryType {
        return HybridAquaticLootPoolEntryTypes.MESSAGE_IN_A_BOTTLE
    }

    public override fun generateLoot(consumer: Consumer<ItemStack>, context: LootContext) {
        val world = context.world
        val random = context.random
        val registryManager = world.registryManager
        val registry = registryManager.getWrapperOrThrow(HybridAquaticRegistryKeys.SEA_MESSAGE)
        registry.getRandom(random).ifPresent { messageEntry ->
            val message = messageEntry.value()

            val stack = ItemStack(HybridAquaticItems.MESSAGE_IN_A_BOTTLE)

            val variants = MessageInABottleBlock.Variant.entries
            stack.set(HybridAquaticComponentTypes.BOTTLE_VARIANT, variants[random.nextInt(variants.size)])
            stack.set(HybridAquaticComponentTypes.BOTTLE_MESSAGE, SeaMessageBookItem.createItemStack(message, registryManager))

            consumer.accept(stack)
        }
    }

    companion object {
        val CODEC: MapCodec<MessageInABottleItemEntry> = RecordCodecBuilder.mapCodec { instance ->
            addLeafFields(instance).apply(instance, ::MessageInABottleItemEntry)
        }

        fun builder(): Builder<*> {
            return builder(::MessageInABottleItemEntry)
        }
    }
}