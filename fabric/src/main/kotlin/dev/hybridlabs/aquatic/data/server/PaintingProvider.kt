package dev.hybridlabs.aquatic.data.server

import dev.hybridlabs.aquatic.painting.HAPaintings
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import net.minecraft.data.worldgen.BootstrapContext
import net.minecraft.resources.ResourceKey
import net.minecraft.world.entity.decoration.PaintingVariant
import java.util.concurrent.CompletableFuture

class PaintingProvider(output: FabricDataOutput,
                       registriesFuture: CompletableFuture<HolderLookup.Provider>
) : FabricDynamicRegistryProvider(output, registriesFuture) {

    override fun configure(
        registries: HolderLookup.Provider,
        entries: Entries
    ) {
        entries.addAll(registries.lookupOrThrow(Registries.PAINTING_VARIANT))
    }

    override fun getName(): String {
        return "Paintings"
    }

    companion object {
        fun register(context: BootstrapContext<PaintingVariant>, key: ResourceKey<PaintingVariant>, width: Int, height: Int) {
            context.register(key, PaintingVariant(width, height, key.location()))
        }

        fun bootstrapVariants(context: BootstrapContext<PaintingVariant>) {
            register(context, HAPaintings.TEST_PAINTING1, 2, 2)
            register(context, HAPaintings.TEST_PAINTING2, 8, 8)
        }
    }
}