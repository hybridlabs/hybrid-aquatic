package dev.hybridlabs.aquatic.item

import net.minecraft.item.ArmorItem.Type
import net.minecraft.item.ArmorMaterial
import net.minecraft.item.Items
import net.minecraft.recipe.Ingredient
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.sound.SoundEvents
import net.minecraft.util.Identifier

object HybridAquaticArmorMaterials {
    val DIVING = register(
        "diving",
        ArmorMaterial(
            buildEquipmentDefenseMap(2, 5, 4, 2),
            9,
            SoundEvents.ITEM_ARMOR_EQUIP_CHAIN,
            { Ingredient.ofItems(Items.COPPER_INGOT) },
            listOf(ArmorMaterial.Layer(Identifier.of("hybrid_aquatic", "diving"))),
            1.0f,
            1.0f
        )
    )
    val SEASHELL = register(
        "seashell",
        ArmorMaterial(
            buildEquipmentDefenseMap(2, 4, 3, 2),
            22,
            SoundEvents.ITEM_ARMOR_EQUIP_TURTLE,
            { Ingredient.ofItems(Items.NAUTILUS_SHELL) },
            listOf(ArmorMaterial.Layer(Identifier.of("hybrid_aquatic", "turtle"))),
            1.0f,
            1.0f
        )
    )
    val TURTLE = register(
        "turtle",
        ArmorMaterial(
            buildEquipmentDefenseMap(2, 4, 3, 2),
            22,
            SoundEvents.ITEM_ARMOR_EQUIP_TURTLE,
            { Ingredient.ofItems(Items.TURTLE_SCUTE) },
            listOf(ArmorMaterial.Layer(Identifier.of("hybrid_aquatic", "turtle"))),
            1.0f,
            1.0f
        )
    )
    val MANGLERFISH = register(
        "manglerfish",
        ArmorMaterial(
            buildEquipmentDefenseMap(1, 1, 1, 1),
            15,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,
            { Ingredient.ofItems(Items.LEATHER) },
            listOf(ArmorMaterial.Layer(Identifier.of("hybrid_aquatic", "manglerfish"))),
            1.0f,
            1.0f
        )
    )
    val EEL = register(
        "eel",
        ArmorMaterial(
            buildEquipmentDefenseMap(1, 1, 1, 1),
            15,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,
            { Ingredient.ofItems(Items.LEATHER) },
            listOf(ArmorMaterial.Layer(Identifier.of("hybrid_aquatic", "eel"))),
            1.0f,
            1.0f
        )
    )
    val MOONJELLYFISH = register(
        "jellyfish",
        ArmorMaterial(
            buildEquipmentDefenseMap(1, 1, 1, 1),
            15,
            SoundEvents.ITEM_ARMOR_EQUIP_LEATHER,
            { Ingredient.ofItems(Items.LEATHER) },
            listOf(ArmorMaterial.Layer(Identifier.of("hybrid_aquatic", "jellyfish"))),
            1.0f,
            1.0f
        )
    )

    private fun register(name: String, material: ArmorMaterial): RegistryEntry<ArmorMaterial> {
        return Registry.registerReference(
            Registries.ARMOR_MATERIAL,
            Identifier.of("hybrid_aquatic", name),
            material
        )
    }

    private fun buildEquipmentDefenseMap(
        helmet: Int,
        chestplate: Int,
        leggings: Int,
        boots: Int,
        body: Int = chestplate
    ): Map<Type, Int> {
        return buildMap {
            this[Type.BOOTS] = boots
            this[Type.LEGGINGS] = leggings
            this[Type.CHESTPLATE] = chestplate
            this[Type.HELMET] = helmet
            this[Type.BODY] = body
        }
    }
}