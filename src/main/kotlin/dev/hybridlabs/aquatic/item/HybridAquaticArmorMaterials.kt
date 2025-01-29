package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.item.equipment.HybridAquaticEquipmentModels
import dev.hybridlabs.aquatic.sound.HybridAquaticSoundEvents
import dev.hybridlabs.aquatic.tag.HybridAquaticItemTags
import net.minecraft.item.equipment.ArmorMaterial
import net.minecraft.item.equipment.EquipmentType
import net.minecraft.sound.SoundEvents

object HybridAquaticArmorMaterials {
    val DIVING = ArmorMaterial(15, buildEquipmentDefenseMap(2, 5, 4, 2), 9, SoundEvents.ITEM_ARMOR_EQUIP_CHAIN, 0.0f, 0.0f, HybridAquaticItemTags.REPAIRS_DIVING_HELMET, HybridAquaticEquipmentModels.DIVING)
    val SEASHELL = ArmorMaterial(15, buildEquipmentDefenseMap(2, 4, 3, 2), 22, SoundEvents.ITEM_ARMOR_EQUIP_TURTLE, 0.0f, 0.0f, HybridAquaticItemTags.REPAIRS_NAUTILUS_ARMOR, HybridAquaticEquipmentModels.NAUTILUS)
    val MANGLERFISH = ArmorMaterial(15, buildEquipmentDefenseMap(1, 1, 1, 1), 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f, 0.0f, HybridAquaticItemTags.REPAIRS_MANGLERFISH_ARMOR, HybridAquaticEquipmentModels.MANGLERFISH)
    val EEL = ArmorMaterial(15, buildEquipmentDefenseMap(1, 1, 1, 1), 15, SoundEvents.ITEM_ARMOR_EQUIP_LEATHER, 0.0f, 0.0f, HybridAquaticItemTags.REPAIRS_EEL_SCARF, HybridAquaticEquipmentModels.EEL)
    val MOONJELLYFISH = ArmorMaterial(15, buildEquipmentDefenseMap(1, 1, 1, 1), 15, HybridAquaticSoundEvents.ITEM_ARMOR_MOON_JELLYFISH_EQUIP, 0.0f, 0.0f, HybridAquaticItemTags.REPAIRS_MOON_JELLYFISH_HAT, HybridAquaticEquipmentModels.MOON_JELLYFISH)

    fun buildEquipmentDefenseMap(helmet: Int, chestplate: Int, leggings: Int, boots: Int, body: Int = chestplate): Map<EquipmentType, Int> {
        return buildMap {
            this[EquipmentType.BOOTS] = boots
            this[EquipmentType.LEGGINGS] = leggings
            this[EquipmentType.CHESTPLATE] = chestplate
            this[EquipmentType.HELMET] = helmet
            this[EquipmentType.BODY] = body
        }
    }
}
