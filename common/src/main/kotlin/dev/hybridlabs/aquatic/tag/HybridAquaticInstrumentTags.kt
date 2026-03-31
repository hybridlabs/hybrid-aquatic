package dev.hybridlabs.aquatic.tag

import dev.hybridlabs.aquatic.CommonClass
import net.minecraft.core.registries.Registries
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Instrument

object HybridAquaticInstrumentTags {

    val OMINOUS_CONCH_INSTRUMENT = create("ominous_conch_instrument")

    private fun create(id: String): TagKey<Instrument> {
        return TagKey.create(Registries.INSTRUMENT, CommonClass.locate(id))
    }
}