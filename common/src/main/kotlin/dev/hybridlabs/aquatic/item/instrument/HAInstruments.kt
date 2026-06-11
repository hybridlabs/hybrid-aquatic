package dev.hybridlabs.aquatic.item.instrument

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.platform.registration.RegistryObject
import dev.hybridlabs.aquatic.sound.HASoundEvents
import net.minecraft.sounds.SoundEvent
import net.minecraft.world.item.Instrument

object HAInstruments {
    var OMINOUS_CONCH_HORN = createInstrument("ominous_conch_horn", HASoundEvents.OMINOUS_CONCH_BLOWS)

    fun createInstrument(id: String, soundEventRegistry: RegistryObject<SoundEvent>): RegistryObject<Instrument> {
        return create(id, soundEventRegistry, 140, 256.0f)
    }

    fun create(id: String, soundEventRegistry: RegistryObject<SoundEvent>, duration: Int, range: Float): RegistryObject<Instrument> {
        return CommonClass.INSTRUMENTS.register(id ) { Instrument(soundEventRegistry.asHolder(), duration, range) }
    }
}