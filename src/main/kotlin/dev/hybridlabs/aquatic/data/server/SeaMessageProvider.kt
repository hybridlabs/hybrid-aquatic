package dev.hybridlabs.aquatic.data.server

import dev.hybridlabs.aquatic.data.SeaMessage
import dev.hybridlabs.aquatic.data.provider.HybridAquaticSeaMessageProvider
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.minecraft.text.Text

class SeaMessageProvider(output: FabricDataOutput) : HybridAquaticSeaMessageProvider(output) {
    override fun generateMessages(generator: (Text) -> Unit) {
        SeaMessage.entries.map(SeaMessage::text).forEach(generator)
    }
}
