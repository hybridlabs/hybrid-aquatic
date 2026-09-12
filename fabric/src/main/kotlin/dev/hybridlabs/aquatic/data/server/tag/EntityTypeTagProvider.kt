package dev.hybridlabs.aquatic.data.server.tag

import dev.hybridlabs.aquatic.entity.HAEntityTypes
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagKey
import net.minecraft.world.entity.EntityType
import java.util.concurrent.CompletableFuture

class EntityTypeTagProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<HolderLookup.Provider>) :
    FabricTagProvider.EntityTypeTagProvider(output, registriesFuture) {

    override fun addTags(arg: HolderLookup.Provider) {
        // Hybrid API's boat tag. The argonaut does not extend vanilla's Boat, so HAPI needs to be
        // told about it for things like lily pad breaking to work.
        // TODO: swap for HAPIEntityTags.BOATS once the HAPI dependency is bumped to 1.1.0.
        getOrCreateTagBuilder(HAPI_BOATS)
            .add(HAEntityTypes.ARGONAUT.get())
    }

    companion object {
        private val HAPI_BOATS: TagKey<EntityType<*>> =
            TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath("hapi", "boats"))
    }
}
