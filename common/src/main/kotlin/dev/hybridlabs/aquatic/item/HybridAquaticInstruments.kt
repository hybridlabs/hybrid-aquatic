package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.CommonClass
import dev.hybridlabs.aquatic.sound.HybridAquaticSoundEvents
import net.minecraft.core.Holder
import net.minecraft.sounds.SoundEvent
import net.minecraft.world.item.Instrument
import java.util.function.Supplier

object HybridAquaticInstruments {

    val OMINOUS_CONCH_SOUND = register("ominous_conch_sound") { Instrument(HybridAquaticSoundEvents.OMINOUS_CONCH_SOUND as Holder<SoundEvent?>, 140, 256.0F) }

    fun register(id: String, instrument: Supplier<Instrument>): Supplier<Instrument> {
        return CommonClass.INSTRUMENTS.register(id, instrument)
    }
}