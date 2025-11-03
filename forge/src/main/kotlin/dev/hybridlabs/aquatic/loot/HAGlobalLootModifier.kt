package dev.hybridlabs.aquatic.loot

import com.mojang.serialization.Codec
import com.mojang.serialization.codecs.RecordCodecBuilder
import it.unimi.dsi.fastutil.objects.ObjectArrayList
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.random.WeightedEntry
import net.minecraft.util.random.WeightedRandomList
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.storage.loot.LootContext
import net.minecraft.world.level.storage.loot.LootParams
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition
import net.minecraftforge.common.loot.IGlobalLootModifier
import net.minecraftforge.common.loot.LootModifier

class HAGlobalLootModifier(
    conditionsIn: Array<LootItemCondition>,
    val target: ResourceLocation,
    val chance: Float,
    val tables: WeightedRandomList<WeightedEntry.Wrapper<ResourceLocation>>
) : LootModifier(conditionsIn) {

    override fun doApply(
        generatedLoot: ObjectArrayList<ItemStack>, context: LootContext
    ): ObjectArrayList<ItemStack> {

        if (context.queriedLootTableId == target) {
            if (context.random.nextFloat() >= chance) {
                val table = tables.getRandom(context.random)
                if (table.isPresent) {
                    val lootTable = context.level.server.lootData.getLootTable(table.get().data)
                    return lootTable.getRandomItems(LootParams(context.level, emptyMap(), emptyMap(), context.luck))
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
            codecStart<HAGlobalLootModifier>(inst).and(
                inst.group(
                    ResourceLocation.CODEC.fieldOf("target").forGetter(HAGlobalLootModifier::target),
                    Codec.FLOAT.fieldOf("chance").forGetter(HAGlobalLootModifier::chance),
                    WeightedRandomList.codec(WeightedEntry.Wrapper<ResourceLocation>.codec(ResourceLocation.CODEC))
                        .fieldOf("tables").forGetter(HAGlobalLootModifier::tables)
                )
            ).apply(inst, ::HAGlobalLootModifier)
        }
    }
}