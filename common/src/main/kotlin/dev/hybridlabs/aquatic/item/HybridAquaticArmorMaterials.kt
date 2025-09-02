package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.Constants
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.item.ArmorItem
import net.minecraft.world.item.ArmorMaterial
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Ingredient
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
    DIVING(
        "diving", 15, intArrayOf(2, 5, 4, 2), 9,
        SoundEvents.ARMOR_EQUIP_CHAIN, 0.0f, 0.0f, Supplier<Ingredient> {
            Ingredient.of(
                Items.COPPER_INGOT
            )
        }),
    SEASHELL(
        "seashell", 15, intArrayOf(2, 4, 3, 2), 22,
        SoundEvents.ARMOR_EQUIP_TURTLE, 0.0f, 0.0f, Supplier<Ingredient> {
            Ingredient.of(
                Items.NAUTILUS_SHELL
            )
        }),
    MANGLERFISH(
        "manglerfish", 15, intArrayOf(1, 1, 1, 1), 15,
        SoundEvents.ARMOR_EQUIP_LEATHER, 0.0f, 0.0f, Supplier<Ingredient> {
            Ingredient.of(
                HybridAquaticItems.GLOW_SLIME.get()
            )
        }),
    EEL(
        "eel", 15, intArrayOf(1, 1, 1, 1), 15,
        SoundEvents.ARMOR_EQUIP_LEATHER, 0.0f, 0.0f, Supplier<Ingredient> {
            Ingredient.of(
                HybridAquaticItems.MORAY_EEL.get()
            )
        }),
    MOONJELLYFISH(
        "moon_jelly", 15, intArrayOf(1, 1, 1, 1), 15,
        SoundEvents.SLIME_BLOCK_PLACE, 0.0f, 0.0f, Supplier<Ingredient> {
            Ingredient.of(
                Items.SLIME_BALL
            )
        }),
    TURTLE(
        "turtle", 25, intArrayOf(2, 6, 5, 2), 9,
        SoundEvents.ARMOR_EQUIP_TURTLE, 1.0f, 0.3f, Supplier<Ingredient> {
            Ingredient.of(
                Items.SCUTE
            )
        });

    override fun getDurabilityForType(type: ArmorItem.Type): Int {
        return BASE_DURABILITY[type.ordinal] * this.durabilityMultiplier
    }

    override fun getDefenseForType(type: ArmorItem.Type): Int {
        return protectionAmounts[type.ordinal]
    }

    override fun getEnchantmentValue(): Int {
        return this.enchantability
    }

    override fun getEquipSound(): SoundEvent {
        return this.equipSound
    }

    override fun getRepairIngredient(): Ingredient {
        return repairIngredient.get()
    }

    override fun getName(): String {
        return Constants.MOD_ID + ":" + this.id
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