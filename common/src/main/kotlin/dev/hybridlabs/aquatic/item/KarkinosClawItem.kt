package dev.hybridlabs.aquatic.item

import com.google.common.collect.ImmutableMultimap
import com.google.common.collect.Multimap
import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.ai.attributes.Attribute
import net.minecraft.world.entity.ai.attributes.AttributeModifier
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import java.util.*

class KarkinosClawItem(settings: Properties) : Item(settings) {

    private var attributes: Multimap<Attribute, AttributeModifier>

    init {
        val builder = ImmutableMultimap.builder<Attribute, AttributeModifier>()
        builder.put(
            ReachEntityAttributes.REACH,
            AttributeModifier(
                UUID.fromString("592e9225-a554-42c9-9366-0fe5c53d9305"),
                "Reach modifier",
                3.0,
                AttributeModifier.Operation.ADDITION
            )
        )
        attributes = builder.build()
    }

    override fun getAttributeModifiers(
        stack: ItemStack,
        slot: EquipmentSlot
    ): Multimap<Attribute, AttributeModifier> {
        return if (slot == EquipmentSlot.OFFHAND) attributes
        else super.getAttributeModifiers(stack, slot)
    }
}