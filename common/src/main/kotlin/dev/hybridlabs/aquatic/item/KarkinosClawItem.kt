package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.CommonClass
import net.minecraft.world.entity.EquipmentSlotGroup
import net.minecraft.world.entity.ai.attributes.AttributeModifier
import net.minecraft.world.entity.ai.attributes.Attributes.BLOCK_INTERACTION_RANGE
import net.minecraft.world.item.Item
import net.minecraft.world.item.component.ItemAttributeModifiers

class KarkinosClawItem(settings: Properties) : Item(settings) {

    private var attributes: ItemAttributeModifiers

    init {
        val builder = ItemAttributeModifiers.builder()
        builder.add(
            BLOCK_INTERACTION_RANGE,
            AttributeModifier(
                CommonClass.locate("reach_modifier"),
                3.0,
                AttributeModifier.Operation.ADD_VALUE
            ),
            EquipmentSlotGroup.OFFHAND
        )
        attributes = builder.build()
    }

    override fun getDefaultAttributeModifiers(): ItemAttributeModifiers {
        return attributes
    }
}