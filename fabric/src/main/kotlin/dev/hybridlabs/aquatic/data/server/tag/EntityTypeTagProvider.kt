package dev.hybridlabs.aquatic.data.server.tag

import dev.hybridlabs.aquatic.entity.HAEntityTypes
import dev.hybridlabs.hapi.tag.HAPIEntityTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.core.HolderLookup
import java.util.concurrent.CompletableFuture

class EntityTypeTagProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<HolderLookup.Provider>) :
    FabricTagProvider.EntityTypeTagProvider(output, registriesFuture) {

    override fun addTags(arg: HolderLookup.Provider) {
        // The argonaut does not extend vanilla's Boat, so HAPI needs to be told about it for
        // things like lily pad breaking to work.
        getOrCreateTagBuilder(HAPIEntityTags.BOATS)
            .add(HAEntityTypes.ARGONAUT.get())
    }
}
