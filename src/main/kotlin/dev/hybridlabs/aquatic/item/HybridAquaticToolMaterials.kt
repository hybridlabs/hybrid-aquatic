package dev.hybridlabs.aquatic.item

import net.minecraft.block.Block
import net.minecraft.item.Items
import net.minecraft.item.ToolMaterial
import net.minecraft.recipe.Ingredient
import net.minecraft.registry.tag.BlockTags
import net.minecraft.registry.tag.TagKey
import java.util.function.Supplier

enum class HybridAquaticToolMaterials(
    private val itemDurability: Int,
    private val miningSpeed: Float,
    private val attackDamage: Float,
    private val enchantability: Int,
    private val repairIngredient: Supplier<Ingredient>,
    private val inverseTag: TagKey<Block>
) :
    ToolMaterial {
    SEASHELL(131, 4.0f, 1.0f, 22, { Ingredient.ofItems(Items.NAUTILUS_SHELL) }, BlockTags.INCORRECT_FOR_STONE_TOOL),
    CORAL(250, 6.0f, 2.0f, 14, { Ingredient.ofItems(HybridAquaticItems.CORAL_CHUNK) }, BlockTags.INCORRECT_FOR_STONE_TOOL);

    override fun getDurability(): Int {
        return this.itemDurability
    }

    override fun getMiningSpeedMultiplier(): Float {
        return this.miningSpeed
    }

    override fun getAttackDamage(): Float {
        return this.attackDamage
    }

    override fun getInverseTag(): TagKey<Block> {
        return this.inverseTag
    }

    override fun getEnchantability(): Int {
        return this.enchantability
    }

    override fun getRepairIngredient(): Ingredient {
        return repairIngredient.get()
    }
}
