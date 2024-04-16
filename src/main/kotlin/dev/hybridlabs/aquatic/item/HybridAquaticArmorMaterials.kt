package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.HybridAquatic
import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterial
import net.minecraft.item.Items
import net.minecraft.recipe.Ingredient
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import java.util.function.Supplier

enum class HybridAquaticArmorMaterials(
    private val id: String,
    private val durabilityMultiplier: Int,
    private val protectionAmounts: IntArray,
    private val enchantability: Int,
    private val equipSound: SoundEvent,
    private val toughness: Float,
    private val knockbackResistance: Float,
    private val repairIngredient: Supplier<Ingredient>
) : ArmorMaterial {
    DIVING("diving", 15, intArrayOf(4, 4, 3, 2), 9,
        SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.0f, 0.0f, Supplier<Ingredient> {
            Ingredient.ofItems(
                Items.COPPER_INGOT
            )
        }),
    MANGLERFISH("manglerfish", 15, intArrayOf(1, 1, 1, 1), 15,
        SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f, 0.0f, Supplier<Ingredient> {
            Ingredient.ofItems(
                HybridAquaticItems.GLOW_SLIME
            )
        }),
    EEL("eel", 15, intArrayOf(1, 1, 1, 1), 15,
        SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f, 0.0f, Supplier<Ingredient> {
            Ingredient.ofItems(
                HybridAquaticItems.MORAY_EEL
            )
        }),
    MOONJELLYFISH("moon_jelly", 15, intArrayOf(1, 1, 1, 1), 15,
        SoundEvents.BLOCK_SLIME_BLOCK_PLACE, 0.0f, 0.0f, Supplier<Ingredient> {
            Ingredient.ofItems(
                Items.SLIME_BALL
            )
        }),
    TURTLE("turtle", 25, intArrayOf(2, 6, 5, 2), 9,
        SoundEvents.ITEM_ARMOR_EQUIP_TURTLE, 1.0f, 0.3f, Supplier<Ingredient> {
            Ingredient.ofItems(
                Items.SCUTE
            )
        });

    override fun getDurability(type: ArmorItem.Type): Int {
        return BASE_DURABILITY[type.ordinal] * this.durabilityMultiplier
    }

    override fun getProtection(type: ArmorItem.Type): Int {
        return protectionAmounts[type.ordinal]
    }

    override fun getEnchantability(): Int {
        return this.enchantability
    }

    override fun getEquipSound(): SoundEvent {
        return this.equipSound
    }

    override fun getRepairIngredient(): Ingredient {
        return repairIngredient.get()
    }

    override fun getName(): String {
        return HybridAquatic.MOD_ID + ":" + this.id
    }

    override fun getToughness(): Float {
        return this.toughness
    }

    override fun getKnockbackResistance(): Float {
        return this.knockbackResistance
    }

    companion object {
        private val BASE_DURABILITY = intArrayOf(11, 16, 15, 13)
    }
}
