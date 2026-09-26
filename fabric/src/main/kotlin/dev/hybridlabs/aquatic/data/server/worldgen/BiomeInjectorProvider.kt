package dev.hybridlabs.aquatic.data.server.worldgen

import dev.hybridlabs.aquatic.world.gen.biome.HABiomeInjectors
import dev.worldgen.lithostitched.api.registry.LithostitchedRegistries
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import java.util.concurrent.CompletableFuture

class BiomeInjectorProvider(
    output: FabricDataOutput,
    registriesFuture: CompletableFuture<HolderLookup.Provider>,
) : FabricDynamicRegistryProvider(output, registriesFuture) {
    override fun configure(
        registries: HolderLookup.Provider,
        entries: Entries
    ) {
        entries.add(registries.lookupOrThrow(Registries.NOISE).getOrThrow(HABiomeInjectors.REEF_SELECTOR_NOISE))
        entries.add(registries.lookupOrThrow(Registries.DENSITY_FUNCTION).getOrThrow(HABiomeInjectors.REEF_SELECTOR))
        entries.addAll(registries.lookupOrThrow(LithostitchedRegistries.BIOME_INJECTOR))
    }

    override fun getName(): String {
        return "Biome Injectors"
    }
}
