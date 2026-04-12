package dev.hybridlabs.aquatic.tag

import dev.hybridlabs.aquatic.CommonClass
import net.minecraft.core.registries.Registries
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Instrument

object HAInstrumentTags {
    var OMINOUS_CONCH = create("ominous_conch")

    private fun create(id: String): TagKey<Instrument> {
        return TagKey.create(Registries.INSTRUMENT, CommonClass.locate(id))
    }
}