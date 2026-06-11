package dev.hybridlabs.aquatic.item

import com.google.common.collect.ImmutableMultimap
import com.google.common.collect.Multimap
import dev.hybridlabs.aquatic.platform.Services
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.ai.attributes.Attribute
import net.minecraft.world.entity.ai.attributes.AttributeModifier
import net.minecraft.world.item.Item
import java.util.UUID

@Suppress("OVERRIDE_DEPRECATION")
class KarkinosClawItem(settings: Properties) : Item(settings) {

    private var attributes: Multimap<Attribute, AttributeModifier>

    init {
        val builder = ImmutableMultimap.builder<Attribute, AttributeModifier>()
        builder.put(
            Services.PLATFORM.reachAttribute,
            AttributeModifier(
                "Reach modifier",
                3.0,
                AttributeModifier.Operation.ADD_VALUE
            )
        )
        attributes = builder.build()
    }

    override fun getDefaultAttributeModifiers(
        slot: EquipmentSlot
    ): Multimap<Attribute, AttributeModifier> {
        return if (slot == EquipmentSlot.OFFHAND) attributes
        else super.getDefaultAttributeModifiers(slot)
    }
}
