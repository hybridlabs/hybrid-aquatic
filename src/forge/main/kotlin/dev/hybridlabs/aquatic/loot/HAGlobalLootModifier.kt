package dev.hybridlabs.aquatic.loot

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import it.unimi.dsi.fastutil.objects.ObjectArrayList
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagKey
import net.minecraft.util.random.WeightedEntry
import net.minecraft.util.random.WeightedRandomList
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.storage.loot.LootContext
import net.minecraft.world.level.storage.loot.LootParams
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition
import net.minecraftforge.common.loot.IGlobalLootModifier
import net.minecraftforge.common.loot.LootModifier

class HAGlobalLootModifier(
    conditionsIn: Array<LootItemCondition>,
    val target: ResourceLocation,
    val item_tag: TagKey<Item>,
    val chance: Float,
    val tables: WeightedRandomList<WeightedEntry.Wrapper<ResourceLocation>>
) : LootModifier(conditionsIn) {

    override fun doApply(
        generatedLoot: ObjectArrayList<ItemStack>, context: LootContext
    ): ObjectArrayList<ItemStack> {

        if (context.queriedLootTableId == target) {
            for ((idx, stack) in generatedLoot.withIndex()) {
                if (stack.itemHolder.`is`(item_tag)) {
                    if (context.random.nextFloat() >= chance) {
                        generatedLoot.removeAt(idx)
                        val table = tables.getRandom(context.random)
                        if (table.isPresent) {
                            val lootTable = context.level.server.lootData.getLootTable(table.get().data)
                            generatedLoot.addAll(
                                lootTable.getRandomItems(
                                    LootParams(
                                        context.level, emptyMap(), emptyMap(), context.luck
                                    )
                                )
                            )
                        }
                    }
                }
            }
        }
        return generatedLoot
    }

    override fun codec(): Codec<out IGlobalLootModifier> {
        return CODEC
    }

    companion object {
        val CODEC: Codec<HAGlobalLootModifier> = RecordCodecBuilder.create<HAGlobalLootModifier> { inst ->
            @Suppress("DEPRECATION") codecStart<HAGlobalLootModifier>(inst).and(
                inst.group(
                    ResourceLocation.CODEC.fieldOf("target").forGetter(HAGlobalLootModifier::target),
                    TagKey<Item>.codec(Registries.ITEM).fieldOf("item_tag").forGetter(
                            HAGlobalLootModifier::item_tag
                        ),
                    Codec.FLOAT.fieldOf("chance").forGetter(HAGlobalLootModifier::chance),
                    WeightedRandomList.codec(WeightedEntry.Wrapper<ResourceLocation>.codec(ResourceLocation.CODEC))
                        .fieldOf("tables").forGetter(HAGlobalLootModifier::tables)
                )
            ).apply(inst, ::HAGlobalLootModifier)
        }
    }
}