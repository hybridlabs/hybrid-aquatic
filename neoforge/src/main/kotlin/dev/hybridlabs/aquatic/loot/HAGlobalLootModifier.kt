package dev.hybridlabs.aquatic.loot

import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import it.unimi.dsi.fastutil.objects.ObjectArrayList
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.Identifier
import net.minecraft.tags.TagKey
import net.minecraft.util.random.WeightedList
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.storage.loot.LootContext
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition
import net.neoforged.neoforge.common.loot.IGlobalLootModifier
import net.neoforged.neoforge.common.loot.LootModifier

class HAGlobalLootModifier(
    condition: java.util.Optional<net.minecraft.core.Holder<LootItemCondition>>,
    priority: Int,
    val target: Identifier,
    val itemTag: TagKey<Item>,
    val chance: Float,
    val tables: WeightedList<Identifier>
) : LootModifier(condition, priority) {

    override fun doApply(
        generatedLoot: ObjectArrayList<ItemStack>, context: LootContext
    ): ObjectArrayList<ItemStack> {

        if (context.queriedLootTableId == target) {
            for ((idx, stack) in generatedLoot.withIndex()) {
                if (stack.typeHolder().`is`(itemTag)) {
                    if (context.random.nextFloat() >= chance) {
                        generatedLoot.removeAt(idx)
                        val table = tables.getRandom(context.random)
                        if (table.isPresent) {
                            val bob = ResourceKey.create(Registries.LOOT_TABLE, table.get())
                            val lootTable = context.level.server.reloadableRegistries().getLootTable(
                                bob
                            )
                            lootTable.getRandomItems(context) { loot -> generatedLoot.add(loot) }
                        }
                    }
                }
            }
        }
        return generatedLoot
    }

    override fun codec(): MapCodec<out IGlobalLootModifier> {
        return CODEC
    }

    companion object {
        val CODEC: MapCodec<HAGlobalLootModifier> = RecordCodecBuilder.mapCodec<HAGlobalLootModifier> { inst ->
            @Suppress("DEPRECATION") codecStart<HAGlobalLootModifier>(inst).and(
                inst.group(
                    Identifier.CODEC.fieldOf("target").forGetter(HAGlobalLootModifier::target),
                    TagKey<Item>.codec(Registries.ITEM).fieldOf("item_tag").forGetter(
                        HAGlobalLootModifier::itemTag
                    ),
                    Codec.FLOAT.fieldOf("chance").forGetter(HAGlobalLootModifier::chance),
                    WeightedList.codec(Identifier.CODEC)
                        .fieldOf("tables").forGetter(HAGlobalLootModifier::tables)
                )
            ).apply(inst, ::HAGlobalLootModifier)
        }
    }
}