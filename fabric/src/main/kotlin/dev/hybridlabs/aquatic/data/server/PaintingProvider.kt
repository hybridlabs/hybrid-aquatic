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
            register(context, HAPaintings.MELON, 1, 1)
            register(context, HAPaintings.CONCH_STREET, 2, 1)
            register(context, HAPaintings.BOLD_AND_BRASH, 1, 2)
            register(context, HAPaintings.BIG_LURE, 3, 2)
            register(context, HAPaintings.PRESERVER, 1, 1)
            register(context, HAPaintings.CREATURES_OF_THE_DEEP, 1, 2)
            register(context, HAPaintings.BIGEYE, 3, 2)
            register(context, HAPaintings.JAWS, 1, 2)
            register(context, HAPaintings.FAYETTE, 2, 2)
            register(context, HAPaintings.PRAYA_DUBIA, 2, 3)
            register(context, HAPaintings.KING_OF_HERRING, 2, 2)
            register(context, HAPaintings.PALESTINE_FLAG, 2, 1)
            register(context, HAPaintings.JOLLY_ROGER, 2, 1)
            register(context, HAPaintings.PRIDE_FLAG, 2, 1)
            register(context, HAPaintings.GAY_PRIDE_FLAG, 2, 1)
            register(context, HAPaintings.LESBIAN_PRIDE_FLAG, 2, 1)
            register(context, HAPaintings.ASEXUAL_PRIDE_FLAG, 2, 1)
            register(context, HAPaintings.BISEXUAL_PRIDE_FLAG, 2, 1)
            register(context, HAPaintings.TRANS_PRIDE_FLAG, 2, 1)

        }
    }
}