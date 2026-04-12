package dev.hybridlabs.aquatic.item.instrument

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.platform.registration.RegistryObject
import net.minecraft.core.Holder
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.item.Instrument

object HAInstruments {
    var OMINOUS_CONCH_HORN = create("ominous_conch_horn", SoundEvents.GRASS_BREAK)

    const val RANGE_BLOCKS: Float = 256.0F
    const val DURATION: Int = 140

    // Instrument only takes Holder<SoundEvent>
    fun create(id: String, soundEvent: SoundEvent): RegistryObject<Instrument> {
        return create(id, Holder.Direct(soundEvent))
    }

    fun create(id: String, soundEventHolder: Holder<SoundEvent>): RegistryObject<Instrument> {
        return CommonClass.INSTRUMENTS.register(id ) { Instrument(soundEventHolder, DURATION, RANGE_BLOCKS) }
    }
}