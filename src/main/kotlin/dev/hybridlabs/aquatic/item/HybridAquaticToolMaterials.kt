package dev.hybridlabs.aquatic.item

import net.minecraft.item.ToolMaterial
import net.minecraft.recipe.Ingredient
import java.util.function.Supplier


enum class HybridAquaticToolMaterials(
    private val miningLevel: Int,
    private val itemDurability: Int,
    private val miningSpeed: Float,
    private val attackDamage: Float,
    private val enchantability: Int,
    private val repairIngredient: Supplier<Ingredient>
) :
    ToolMaterial {
    SEASHELL(1, 131, 4.0f, 1.0f, 22,
        Supplier<Ingredient> { Ingredient.ofItems(net.minecraft.item.Items.NAUTILUS_SHELL) }),
    CORAL(2, 250, 6.0f, 2.0f, 14,
        Supplier<Ingredient> { Ingredient.ofItems(HybridAquaticItems.CORAL_CHUNK) });

    override fun getDurability(): Int {
        return this.itemDurability
    }

    override fun getMiningSpeedMultiplier(): Float {
        return this.miningSpeed
    }

    override fun getAttackDamage(): Float {
        return this.attackDamage
    }

    override fun getMiningLevel(): Int {
        return this.miningLevel
    }

    override fun getEnchantability(): Int {
        return this.enchantability
    }

    override fun getRepairIngredient(): Ingredient {
        return repairIngredient.get()
    }
}