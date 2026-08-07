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
        fun register(context: BootstrapContext<PaintingVariant>, key: ResourceKey<PaintingVariant>, id: String, width: Int, height: Int) {
            context.register(key, PaintingVariant(width, height, key.location()))
        }

        fun bootstrapVariants(context: BootstrapContext<PaintingVariant>) {
            register(context, HAPaintings.MELON, "melon", 1, 1)
            register(context, HAPaintings.JONA_SAXISA, "jona_saxisa", 1, 1)
            register(context, HAPaintings.CALM_WATERS, "calm_waters", 1, 1)

            register(context, HAPaintings.CONCH_STREET, "conch_street", 2, 1)
            register(context, HAPaintings.SHALLOWS, "shallows", 2, 1)
            register(context, HAPaintings.SEASHELL_SEASHELF, "seashell_seashelf", 2, 1)
            register(context, HAPaintings.BOLD_AND_BRASH, "bold_and_brash", 1, 2)
            register(context, HAPaintings.BIG_LURE, "big_lure", 3, 2)
            register(context, HAPaintings.PRESERVER, "preserver", 1, 1)

            register(context, HAPaintings.CREATURES_OF_THE_DEEP, "creatures_of_the_deep", 1, 2)
            register(context, HAPaintings.GONE_FISHING, "gone_fishing", 1, 2)
            register(context, HAPaintings.PRESSED_KELP, "pressed_kelp", 2, 1)
            register(context, HAPaintings.PRESSED_BULL_KELP, "pressed_bull_kelp", 1, 3)
            register(context, HAPaintings.BIGEYE, "bigeye", 3, 2)
            register(context, HAPaintings.THE_WATCHFUL, "the_watchful", 3, 3)
            register(context, HAPaintings.LIGHTHOUSE, "lighthouse", 1, 2)

            register(context, HAPaintings.JAWS, "jaws", 1, 2)
            register(context, HAPaintings.GREAT_LAKE, "great_lake", 2, 2)
            register(context, HAPaintings.SELGIO, "selgio", 2, 2)
            register(context, HAPaintings.SUNSET, "sunset", 2, 1)
            register(context, HAPaintings.DIORAMA, "diorama", 2, 2)
            register(context, HAPaintings.RED_DEVIL, "red_devil", 2, 2)
            register(context, HAPaintings.ABYSSAL, "abyssal", 2, 2)
            register(context, HAPaintings.THE_FAST_REGIMENT, "the_fast_regiment", 2, 2)
            register(context, HAPaintings.LIGHTHOUSE_AFTER_DUSK, "lighthouse_after_dark", 2, 2)
            register(context, HAPaintings.RIVERFISH, "riverfish", 2, 2)
            register(context, HAPaintings.JAM, "jam", 2, 3)
            register(context, HAPaintings.ADMIRAL_STEVE, "admiral_steve", 2, 3)
            register(context, HAPaintings.ADMIRAL_SKELLINGTON, "admiral_skellington", 2, 3)
            register(context, HAPaintings.SWIRLING, "swirling", 3, 3)
            register(context, HAPaintings.POULPE_COLOSSAL, "poulpe_colossal", 2, 3)
            register(context, HAPaintings.BELOW, "below", 2, 3)
            register(context, HAPaintings.CHASM, "chasm", 2, 3)
            register(context, HAPaintings.SUNTIDE, "suntide", 4, 2)
            register(context, HAPaintings.STEVE_AND_THE_SEA, "steve_and_the_sea", 4, 2)
            register(context, HAPaintings.MOONCATCHER, "mooncatcher", 4, 4)
            register(context, HAPaintings.NEVEN_NAIVEN, "neven_naiven", 3, 3)
            register(context, HAPaintings.MAJESTIC_BEHEMOTH, "majestic_behemoth", 3, 3)
            register(context, HAPaintings.ORGANON, "organon", 3, 3)

            register(context, HAPaintings.PRAYA_DUBIA, "praya_dubia", 2, 3)
            register(context, HAPaintings.KING_OF_HERRING, "king_of_herring", 2, 2)

            register(context, HAPaintings.PALESTINE_FLAG, "palestine_flag", 2, 1)
            register(context, HAPaintings.JOLLY_ROGER, "jolly_roger", 2, 1)
            register(context, HAPaintings.PRIDE_FLAG, "pride_flag", 2, 1)
            register(context, HAPaintings.GAY_PRIDE_FLAG, "gay_pride_flag", 2, 1)
            register(context, HAPaintings.LESBIAN_PRIDE_FLAG, "lesbian_pride_flag", 2, 1)
            register(context, HAPaintings.ASEXUAL_PRIDE_FLAG, "asexual_pride_flag", 2, 1)
            register(context, HAPaintings.BISEXUAL_PRIDE_FLAG, "bisexual_pride_flag", 2, 1)
            register(context, HAPaintings.TRANS_PRIDE_FLAG, "trans_pride_flag", 2, 1)
            register(context, HAPaintings.NONBINARY_PRIDE_FLAG, "nonbinary_pride_flag", 2, 1)
            register(context, HAPaintings.PANSEXUAL_PRIDE_FLAG, "pansexual_pride_flag", 2, 1)

        }
    }
}