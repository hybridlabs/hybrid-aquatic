package dev.hybridlabs.aquatic.item.instrument

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.platform.registration.RegistryObject
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.item.Instrument

object HAInstruments {
    var OMINOUS_CONCH_HORN = create("ominous_conch_horn")

    const val RANGE_BLOCKS: Float = 256.0F
    const val DURATION: Int = 140

    fun create(id: String): RegistryObject<Instrument> {
        return CommonClass.INSTRUMENTS.register(id ) { Instrument(SoundEvents.NOTE_BLOCK_GUITAR, DURATION, RANGE_BLOCKS) }
    }
}