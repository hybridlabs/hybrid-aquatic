package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.sound.HybridAquaticSoundEvents
import dev.hybridlabs.aquatic.tag.HybridAquaticItemTags
import net.minecraft.item.ArmorItem
import net.minecraft.item.ArmorMaterial
import net.minecraft.recipe.Ingredient
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.Identifier
import java.util.function.Supplier

object HybridAquaticArmorMaterials {
    val DIVING = register("diving", buildEquipmentDefenseMap(2, 5, 4, 2), 9, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, { Ingredient.fromTag(HybridAquaticItemTags.REPAIRS_DIVING_HELMET) })
    val SEASHELL = register("seashell", buildEquipmentDefenseMap(2, 4, 3, 2), 22, SoundEvents.ITEM_ARMOR_EQUIP_TURTLE, { Ingredient.fromTag(HybridAquaticItemTags.REPAIRS_NAUTILUS_ARMOR) })
    val MANGLERFISH = register("manglerfish", buildEquipmentDefenseMap(1, 1, 1, 1), 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, { Ingredient.fromTag(HybridAquaticItemTags.REPAIRS_MANGLERFISH_ARMOR) })
    val EEL = register("eel", buildEquipmentDefenseMap(1, 1, 1, 1), 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, { Ingredient.fromTag(HybridAquaticItemTags.REPAIRS_EEL_SCARF) })
    val MOONJELLYFISH = register("moon_jellyfish", buildEquipmentDefenseMap(1, 1, 1, 1), 15, HybridAquaticSoundEvents.ITEM_ARMOR_MOON_JELLYFISH_EQUIP, { Ingredient.fromTag(HybridAquaticItemTags.REPAIRS_MOON_JELLYFISH_HAT) })

    fun buildEquipmentDefenseMap(helmet: Int, chestplate: Int, leggings: Int, boots: Int, body: Int = chestplate): Map<ArmorItem.Type, Int> {
        return buildMap {
            this[ArmorItem.Type.BOOTS] = boots
            this[ArmorItem.Type.LEGGINGS] = leggings
            this[ArmorItem.Type.CHESTPLATE] = chestplate
            this[ArmorItem.Type.HELMET] = helmet
            this[ArmorItem.Type.BODY] = body
        }
    }

    private fun register(id: String, defense: Map<ArmorItem.Type, Int>, enchantability: Int, equipSound: RegistryEntry<SoundEvent>, repairIngredient: Supplier<Ingredient>, toughness: Float = 0.0f, knockbackResistance: Float = 0.0f): RegistryEntry<ArmorMaterial> {
        val identifier = Identifier(HybridAquatic.MOD_ID, id)
        val list = listOf(ArmorMaterial.Layer(identifier))
        val material = ArmorMaterial(defense, enchantability, equipSound, repairIngredient, list, toughness, knockbackResistance)
        return Registry.registerReference(Registries.ARMOR_MATERIAL, identifier, material)
    }
}
