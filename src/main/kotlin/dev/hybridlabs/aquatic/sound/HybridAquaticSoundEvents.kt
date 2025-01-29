package dev.hybridlabs.aquatic.sound

import dev.hybridlabs.aquatic.HybridAquatic
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.sound.SoundEvent
import net.minecraft.util.Identifier

object HybridAquaticSoundEvents {
    val ITEM_ARMOR_MOON_JELLYFISH_EQUIP = registerReference("item.armor.equip_moon_jellyfish")

    fun registerReference(id: String): RegistryEntry.Reference<SoundEvent> {
        val identifier = Identifier.of(HybridAquatic.MOD_ID, id)
        return Registry.registerReference(Registries.SOUND_EVENT, identifier, SoundEvent.of(identifier))
    }
}
