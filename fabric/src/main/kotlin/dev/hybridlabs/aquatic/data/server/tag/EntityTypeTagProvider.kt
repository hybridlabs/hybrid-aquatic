package dev.hybridlabs.aquatic.data.server.tag

import net.minecraft.core.Registry
import net.minecraft.core.registries.Registries
import net.minecraft.data.tags.TagAppender
import net.minecraft.resources.Identifier
import net.minecraft.resources.ResourceKey
import net.minecraft.tags.TagKey
import net.minecraft.world.entity.EntityType

import dev.hybridlabs.aquatic.entity.HAEntityTypes
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider
import net.minecraft.core.HolderLookup
import java.util.concurrent.CompletableFuture

class EntityTypeTagProvider(output: FabricPackOutput, registriesFuture: CompletableFuture<HolderLookup.Provider>) :
    FabricTagsProvider.EntityTypeTagsProvider(output, registriesFuture) {

    override fun addTags(arg: HolderLookup.Provider) {
        // Hybrid API's boat tag. The argonaut does not extend vanilla's Boat, so HAPI needs to be
        // told about it for things like lily pad breaking to work.
        // TODO: swap for HAPIEntityTags.BOATS once the HAPI dependency is bumped to 1.1.0.
        valueLookupBuilder(HAPI_BOATS)
            .add(HAEntityTypes.ARGONAUT.get())
    }

    companion object {
        private val HAPI_BOATS: TagKey<EntityType<*>> =
            TagKey.create(Registries.ENTITY_TYPE, Identifier.fromNamespaceAndPath("hapi", "boats"))
    }

    private class ValueTagAppender<T : Any>(
        private val delegate: TagAppender<T>,
        private val registryKey: ResourceKey<out Registry<T>>,
        private val key: (T) -> ResourceKey<T>,
    ) {
        fun add(value: T) = apply { delegate.add(key(value)) }
        fun addOptional(value: T) = apply { delegate.addOptional(key(value)) }
        fun addTag(tag: TagKey<T>) = apply { delegate.addTag(tag) }
        fun addOptionalTag(tag: TagKey<T>) = apply { delegate.addOptionalTag(tag) }
        fun forceAddTag(tag: TagKey<T>) = addTag(tag)
        fun addItems(vararg values: T) = apply { values.forEach { delegate.add(key(it)) } }
        fun addOptionalItem(id: Identifier) = apply { delegate.addOptional(ResourceKey.create(registryKey, id)) }
        fun addOptionalItemTag(id: Identifier) = apply { delegate.addOptionalTag(TagKey.create(registryKey, id)) }
    }

    private fun valueLookupBuilder(tag: TagKey<EntityType<*>>) =
        ValueTagAppender(builder(tag), Registries.ENTITY_TYPE) { it.builtInRegistryHolder().key() }
}
