package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.CommonClass
import net.minecraft.core.Holder
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.tags.ItemTags
import net.minecraft.world.item.ArmorItem
import net.minecraft.world.item.ArmorMaterial
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Ingredient
import java.util.*
import java.util.function.Supplier

object HAArmorMaterials {
    val DIVING = register(
        "diving",
        mapOf(
            ArmorItem.Type.HELMET to 2,
            ArmorItem.Type.CHESTPLATE to 5,
            ArmorItem.Type.LEGGINGS to 4,
            ArmorItem.Type.BOOTS to 2,
            ArmorItem.Type.BODY to 5
        ),
        9,
        SoundEvents.ARMOR_EQUIP_CHAIN,
        {
            Ingredient.of(
                Items.COPPER_INGOT
            )
        },
        listOf(),
        0.0f,
        0.0f,
    )

    val GLOWING_DIVING = register(
        "glowing_diving",
        mapOf(
            ArmorItem.Type.HELMET to 2,
            ArmorItem.Type.CHESTPLATE to 5,
            ArmorItem.Type.LEGGINGS to 4,
            ArmorItem.Type.BOOTS to 2,
            ArmorItem.Type.BODY to 5
        ),
        9,
        SoundEvents.ARMOR_EQUIP_GENERIC,
        {
            Ingredient.of(
                Items.COPPER_INGOT
            )
        },
        listOf(),
        0.0f,
        0.0f,
    )

    val REINFORCED_DIVING = register(
        "reinforced_diving",
        mapOf(
            ArmorItem.Type.HELMET to 3,
            ArmorItem.Type.CHESTPLATE to 7,
            ArmorItem.Type.LEGGINGS to 5,
            ArmorItem.Type.BOOTS to 3,
            ArmorItem.Type.BODY to 5
        ),
        9,
        SoundEvents.ARMOR_EQUIP_NETHERITE,
        {
            Ingredient.of(
                Items.COPPER_INGOT
            )
        },
        listOf(),
        0.0f,
        1.0f,
    )

    val SEASHELL = register(
        "seashell",
        mapOf(
            ArmorItem.Type.HELMET to 2,
            ArmorItem.Type.CHESTPLATE to 4,
            ArmorItem.Type.LEGGINGS to 3,
            ArmorItem.Type.BOOTS to 2,
            ArmorItem.Type.BODY to 4
        ),
        22,
        SoundEvents.ARMOR_EQUIP_TURTLE,
        {
            Ingredient.of(
                Items.NAUTILUS_SHELL
            )
        },
        listOf(),
        0.0f,
        0.0f,
    )

    val MANGLERFISH = register(
        "manglerfish",
        mapOf(
            ArmorItem.Type.HELMET to 1,
            ArmorItem.Type.CHESTPLATE to 1,
            ArmorItem.Type.LEGGINGS to 1,
            ArmorItem.Type.BOOTS to 1,
            ArmorItem.Type.BODY to 1
        ),
        15,
        SoundEvents.ARMOR_EQUIP_GENERIC,
        {
            Ingredient.of(
                HAItems.GLOWSLIME.get()
            )
        },
        listOf(),
        0.0f,
        0.0f
    )

    val EEL = register(
        "eel",
        mapOf(
            ArmorItem.Type.HELMET to 1,
            ArmorItem.Type.CHESTPLATE to 1,
            ArmorItem.Type.LEGGINGS to 1,
            ArmorItem.Type.BOOTS to 1,
            ArmorItem.Type.BODY to 1
        ),
        15,
        SoundEvents.ARMOR_EQUIP_GENERIC,
        {
            Ingredient.of(
                ItemTags.WOOL
            )
        },
        listOf(),
        0.0f,
        0.0f
    )

    val HATXOLOTL = register(
        "hatxolotl",
        mapOf(
            ArmorItem.Type.HELMET to 1,
            ArmorItem.Type.CHESTPLATE to 1,
            ArmorItem.Type.LEGGINGS to 1,
            ArmorItem.Type.BOOTS to 1,
            ArmorItem.Type.BODY to 1
        ),
        15,
        SoundEvents.ARMOR_EQUIP_GENERIC,
        {
            Ingredient.of(
                Items.LEATHER
            )
        },
        listOf(),
        0.0f,
        0.0f
    )

    val MOONJELLYFISH = register(
        "moon_jelly",
        mapOf(
            ArmorItem.Type.HELMET to 1,
            ArmorItem.Type.CHESTPLATE to 1,
            ArmorItem.Type.LEGGINGS to 1,
            ArmorItem.Type.BOOTS to 1,
            ArmorItem.Type.BODY to 1
        ),
        15,
        SoundEvents.ARMOR_EQUIP_GENERIC,
        {
            Ingredient.of(
                Items.SLIME_BALL
            )
        },
        listOf(),
        0.0f,
        0.0f
    )

    val TURTLE = register(
        "turtle",
        mapOf(
            ArmorItem.Type.HELMET to 1,
            ArmorItem.Type.CHESTPLATE to 1,
            ArmorItem.Type.LEGGINGS to 1,
            ArmorItem.Type.BOOTS to 1,
            ArmorItem.Type.BODY to 1
        ),
        9,
        SoundEvents.ARMOR_EQUIP_TURTLE,
        {
            Ingredient.of(
                Items.TURTLE_SCUTE
            )
        },
        listOf(),
        1.0f,
        0.3f,
    )

    fun register(
        name: String,
        defense: Map<ArmorItem.Type, Int>,
        enchantmentValue: Int,
        equipSound: Holder<SoundEvent>,
        repairIngridient: Supplier<Ingredient>,
        layers: List<ArmorMaterial.Layer>,
        toughness: Float,
        knockbackResistance: Float
    ): Holder<ArmorMaterial?> {
        val enummap: EnumMap<ArmorItem.Type?, Int?> = EnumMap(ArmorItem.Type::class.java)
        for (type in ArmorItem.Type.entries) enummap[type] = defense[type]
        return Registry.registerForHolder(
            BuiltInRegistries.ARMOR_MATERIAL,
            CommonClass.locate(name),
            ArmorMaterial(
                enummap,
                enchantmentValue,
                equipSound,
                repairIngridient,
                layers,
                toughness,
                knockbackResistance
            )
        )
    }
}