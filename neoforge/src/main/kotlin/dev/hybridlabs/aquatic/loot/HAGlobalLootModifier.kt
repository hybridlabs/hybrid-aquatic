package dev.hybridlabs.aquatic.loot

import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import dev.hybridlabs.aquatic.Constants
import it.unimi.dsi.fastutil.objects.ObjectArrayList
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagKey
import net.minecraft.util.random.WeightedEntry
import net.minecraft.util.random.WeightedRandomList
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.storage.loot.LootContext
import net.minecraft.world.level.storage.loot.LootParams
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition
import net.neoforged.neoforge.common.loot.IGlobalLootModifier
import net.neoforged.neoforge.common.loot.LootModifier
import net.neoforged.neoforge.registries.DeferredRegister
import net.neoforged.neoforge.registries.NeoForgeRegistries
import thedarkcolour.kotlinforforge.neoforge.forge.MOD_BUS

class HAGlobalLootModifier(
    conditionsIn: Array<LootItemCondition>,
    val target: ResourceLocation,
    val itemTag: TagKey<Item>,
    val chance: Float,
    val tables: WeightedRandomList<WeightedEntry.Wrapper<ResourceLocation>>
) : LootModifier(conditionsIn) {

    override fun doApply(
        generatedLoot: ObjectArrayList<ItemStack>, context: LootContext
    ): ObjectArrayList<ItemStack> {

        if (context.queriedLootTableId == target) {
            for ((idx, stack) in generatedLoot.withIndex()) {
                if (stack.itemHolder.`is`(itemTag)) {
                    if (context.random.nextFloat() >= chance) {
                        generatedLoot.removeAt(idx)
                        val table = tables.getRandom(context.random)
                        if (table.isPresent) {
                            val bob = ResourceKey.create(Registries.LOOT_TABLE, table.get().data)
                            val lootTable = context.level.server.reloadableRegistries().getLootTable(
                                bob
                            )
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

    override fun codec(): MapCodec<out IGlobalLootModifier> {
        return CODEC
    }

    companion object {
        val CODEC: MapCodec<HAGlobalLootModifier> = RecordCodecBuilder.mapCodec<HAGlobalLootModifier> { inst ->
            @Suppress("DEPRECATION") codecStart<HAGlobalLootModifier>(inst).and(
                inst.group(
                    ResourceLocation.CODEC.fieldOf("target").forGetter(HAGlobalLootModifier::target),
                    TagKey<Item>.codec(Registries.ITEM).fieldOf("item_tag").forGetter(
                        HAGlobalLootModifier::itemTag
                    ),
                    Codec.FLOAT.fieldOf("chance").forGetter(HAGlobalLootModifier::chance),
                    WeightedRandomList.codec(WeightedEntry.Wrapper<ResourceLocation>.codec(ResourceLocation.CODEC))
                        .fieldOf("tables").forGetter(HAGlobalLootModifier::tables)
                )
            ).apply(inst, ::HAGlobalLootModifier)
        }
        fun registerGlobalLootModifiers() {
            val lootModifiers = DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Constants.MOD_ID)
            lootModifiers.register("ha_loot_modifier", HAGlobalLootModifier::CODEC)
        }
    }
}