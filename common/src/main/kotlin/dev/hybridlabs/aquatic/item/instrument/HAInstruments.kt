package dev.hybridlabs.aquatic.item.instrument

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.platform.registration.RegistryObject
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.item.Instrument

object HAInstruments {
    var OMINOUS_CONCH_HORN = create("ominous_conch_horn")

    fun create(id: String): RegistryObject<Instrument> {
        return CommonClass.INSTRUMENTS.register(id ) { Instrument(SoundEvents.NOTE_BLOCK_GUITAR, 140, 256.0F) }
    }
}