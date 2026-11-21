package dev.hybridlabs.aquatic.data.server.tag

import net.minecraft.core.Registry
import net.minecraft.core.registries.Registries
import net.minecraft.data.tags.TagAppender
import net.minecraft.resources.Identifier
import net.minecraft.resources.ResourceKey
import net.minecraft.tags.TagKey
import net.minecraft.world.level.material.Fluid

import dev.hybridlabs.aquatic.fluid.HAPlatformFluids
import dev.hybridlabs.aquatic.tag.HAFluidTags
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider
import net.minecraft.core.HolderLookup
import net.minecraft.tags.FluidTags
import java.util.concurrent.CompletableFuture

class FluidTagProvider(output: FabricPackOutput, registriesFuture: CompletableFuture<HolderLookup.Provider>):
    FabricTagsProvider.FluidTagsProvider(output, registriesFuture) {

    override fun addTags(p0: HolderLookup.Provider) {
        valueLookupBuilder(HAFluidTags.BRINE)
            .add(HAPlatformFluids.BRINE_STILL.get())
            .add(HAPlatformFluids.BRINE_FLOWING.get())


        valueLookupBuilder(FluidTags.WATER)
            .add(HAPlatformFluids.BRINE_STILL.get())
            .add(HAPlatformFluids.BRINE_FLOWING.get())
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

    private fun valueLookupBuilder(tag: TagKey<Fluid>) =
        ValueTagAppender(builder(tag), Registries.FLUID) { it.builtInRegistryHolder().key() }
}
