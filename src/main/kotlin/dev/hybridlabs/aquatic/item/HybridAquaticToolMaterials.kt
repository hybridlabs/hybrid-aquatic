package dev.hybridlabs.aquatic.item

import net.minecraft.block.Block
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
    private val repairIngredient: Supplier<Ingredient>
) :
    ToolMaterial {
    SEASHELL(131, 4.0f, 1.0f, 22, Supplier<Ingredient> { Ingredient.ofItems(net.minecraft.item.Items.NAUTILUS_SHELL) }) {
        override fun getInverseTag(): TagKey<Block> {
            return BlockTags.INCORRECT_FOR_STONE_TOOL
        }
    },
    CORAL(250, 6.0f, 2.0f, 14, Supplier<Ingredient> { Ingredient.ofItems(HybridAquaticItems.CORAL_CHUNK) }) {
        override fun getInverseTag(): TagKey<Block> {
            return BlockTags.INCORRECT_FOR_IRON_TOOL
        }
    };

    override fun getDurability(): Int {
        return this.itemDurability
    }

    override fun getMiningSpeedMultiplier(): Float {
        return this.miningSpeed
    }

    override fun getAttackDamage(): Float {
        return this.attackDamage
    }

    override fun getEnchantability(): Int {
        return this.enchantability
    }

    override fun getRepairIngredient(): Ingredient {
        return repairIngredient.get()
    }
}