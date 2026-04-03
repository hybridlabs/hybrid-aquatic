package dev.hybridlabs.aquatic.item

import net.minecraft.world.item.Items
import net.minecraft.world.item.Tier
import net.minecraft.world.item.crafting.Ingredient
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
        1,
        131,
        4.0f,
        1.0f,
        22,
        Supplier<Ingredient> { Ingredient.of(Items.NAUTILUS_SHELL) }
    ),

    CORAL(
        2,
        250,
        6.0f,
        2.0f,
        14,
        Supplier<Ingredient> { Ingredient.of(HAAquaticItems.CORAL_CHUNK.get()) }
    );

    override fun getUses(): Int {
        return this.itemDurability
    }

    override fun getSpeed(): Float {
        return this.miningSpeed
    }

    override fun getAttackDamageBonus(): Float {
        return this.attackDamage
    }

    override fun getLevel(): Int {
        return this.miningLevel
    }

    override fun getEnchantmentValue(): Int {
        return this.enchantability
    }

    override fun getRepairIngredient(): Ingredient {
        return repairIngredient.get()
    }
}