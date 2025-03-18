package dev.hybridlabs.aquatic.item

import com.google.common.collect.ImmutableMultimap
import com.google.common.collect.Multimap
import com.jamieswhiteshirt.reachentityattributes.ReachEntityAttributes
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.attribute.EntityAttribute
import net.minecraft.entity.attribute.EntityAttributeModifier
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import java.util.*

class KarkinosClawItem(settings: Settings): Item(settings) {

    private var attributes: Multimap<EntityAttribute, EntityAttributeModifier>

    init {
        val builder = ImmutableMultimap.builder<EntityAttribute, EntityAttributeModifier>()
        builder.put(ReachEntityAttributes.REACH, EntityAttributeModifier(UUID.fromString("592e9225-a554-42c9-9366-0fe5c53d9305"), "Reach modifier", 3.0, EntityAttributeModifier.Operation.ADDITION))
        attributes = builder.build()
    }

    override fun getAttributeModifiers(
        stack: ItemStack,
        slot: EquipmentSlot
    ): Multimap<EntityAttribute, EntityAttributeModifier> {
        return if(slot == EquipmentSlot.OFFHAND) attributes
        else super.getAttributeModifiers(stack, slot)
    }
}