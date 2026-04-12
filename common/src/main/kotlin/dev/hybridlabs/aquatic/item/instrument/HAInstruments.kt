package dev.hybridlabs.aquatic.item.instrument

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.platform.registration.RegistryObject
import dev.hybridlabs.aquatic.sound.HASoundEvents
import net.minecraft.core.Holder
import net.minecraft.sounds.SoundEvent
import net.minecraft.world.item.Instrument

object HAInstruments {
    var OMINOUS_CONCH_HORN = createInstrument("ominous_conch_horn", HASoundEvents.OMINOUS_CONCH_BLOWS.get())

    fun createInstrument(id: String, soundEvent: SoundEvent): RegistryObject<Instrument> {
        return createDurationRange(id, Holder.Direct(soundEvent))
    }

    fun createInstrument(id: String, soundEventHolder: Holder<SoundEvent>): RegistryObject<Instrument> {
        return createDurationRange(id, soundEventHolder)
    }

    fun createDurationRange(id: String, soundEventHolder: Holder<SoundEvent>): RegistryObject<Instrument> {
        return create(id, soundEventHolder, 140, 256.0f)
    }

    fun create(id: String, soundEventHolder: Holder<SoundEvent>, duration: Int, range: Float): RegistryObject<Instrument> {
        return CommonClass.INSTRUMENTS.register(id ) { Instrument(soundEventHolder, duration, range) }
    }
}