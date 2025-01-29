package dev.hybridlabs.aquatic.item.equipment

import dev.hybridlabs.aquatic.HybridAquatic
import net.minecraft.util.Identifier

object HybridAquaticEquipmentModels {
    val DIVING = create("diving")
    val NAUTILUS = create("nautilus")
    val MANGLERFISH = create("manglerfish")
    val EEL = create("eel")
    val MOON_JELLYFISH = create("moon_jellyfish")

    fun create(id: String): Identifier {
        return Identifier.of(HybridAquatic.MOD_ID, id)
    }
}
