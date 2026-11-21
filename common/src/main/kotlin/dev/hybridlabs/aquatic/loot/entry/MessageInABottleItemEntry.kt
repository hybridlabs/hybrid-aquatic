package dev.hybridlabs.aquatic.loot.entry

import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import dev.hybridlabs.aquatic.block.MessageInABottleBlock
import dev.hybridlabs.aquatic.block.entity.MessageInABottleBlockEntity
import dev.hybridlabs.aquatic.item.HAItems
import net.minecraft.core.Holder
import net.minecraft.core.component.DataComponents
import net.minecraft.nbt.CompoundTag
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.component.CustomData
import net.minecraft.world.level.storage.loot.LootContext
import net.minecraft.world.level.storage.loot.entries.SingleEntryContainerBase
import net.minecraft.world.level.storage.loot.entries.UniformContainerBase
import net.minecraft.world.level.storage.loot.functions.LootItemFunction
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition
import java.util.Optional
import java.util.function.Consumer

class MessageInABottleItemEntry(
    weight: Int,
    quality: Int,
    condition: Optional<Holder<LootItemCondition>>,
    modifier: Optional<Holder<LootItemFunction>>,
) : SingleEntryContainerBase(weight, quality, condition, modifier) {
    override fun codec(): MapCodec<out SingleEntryContainerBase> = CODEC

    public override fun createItemStack(consumer: Consumer<ItemStack>, context: LootContext) {
        val random = context.random
        val stack = ItemStack(HAItems.MESSAGE_IN_A_BOTTLE.get())
        val stuff = CompoundTag().apply {
            val variants = MessageInABottleBlock.Variant.entries
            putString(MessageInABottleBlockEntity.VARIANT_KEY, variants[random.nextInt(variants.size)].id)
        }
        stack.set(DataComponents.CUSTOM_DATA, CustomData.of(stuff))
    }

    companion object {
        val CODEC: MapCodec<MessageInABottleItemEntry> = RecordCodecBuilder.mapCodec { inst ->
            uniformFields(inst).apply(inst, ::MessageInABottleItemEntry)
        }

        fun builder(): UniformContainerBase.Builder<*> {
            return simpleBuilder { weight, quality, condition, modifier ->
                MessageInABottleItemEntry(weight, quality, condition, modifier)
            }
        }
    }
}
