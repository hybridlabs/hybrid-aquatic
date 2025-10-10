package dev.hybridlabs.aquatic.item

import net.minecraft.tags.TagKey
import net.minecraft.world.item.Items
import net.minecraft.world.item.Tier
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.block.Block
import java.util.function.Supplier


enum class HybridAquaticToolMaterials(
    private val miningLevel: Int,
    private val itemDurability: Int,
    private val miningSpeed: Float,
    private val attackDamage: Float,
    private val enchantability: Int,
    private val repairIngredient: Supplier<Ingredient>
) :
    Tier {
    SEASHELL(
        1, 131, 4.0f, 1.0f, 22,
        Supplier<Ingredient> { Ingredient.of(Items.NAUTILUS_SHELL) }) {
        override fun getIncorrectBlocksForDrops(): TagKey<Block?> {
            TODO("Not yet implemented")
        }
    },
    CORAL(
        2, 250, 6.0f, 2.0f, 14,
        Supplier<Ingredient> { Ingredient.of(HybridAquaticItems.CORAL_CHUNK.get()) }) {
        override fun getIncorrectBlocksForDrops(): TagKey<Block?> {
            TODO("Not yet implemented")
        }
    };

    override fun getUses(): Int {
        return this.itemDurability
    }

    override fun getSpeed(): Float {
        return this.miningSpeed
    }

    override fun getAttackDamageBonus(): Float {
        return this.attackDamage
    }

    override fun getEnchantmentValue(): Int {
        return this.enchantability
    }

    override fun getRepairIngredient(): Ingredient {
        return repairIngredient.get()
    }
}