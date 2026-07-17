package dev.hybridlabs.aquatic.data.server.tag

import dev.hybridlabs.aquatic.painting.HAPaintings
import dev.hybridlabs.aquatic.tag.HAPaintingTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.world.entity.decoration.PaintingVariant
import java.util.concurrent.CompletableFuture

class PaintingVariantTagProvider (output: FabricDataOutput, registriesFuture: CompletableFuture<HolderLookup.Provider>) :
    FabricTagProvider<PaintingVariant>(output, Registries.PAINTING_VARIANT, registriesFuture) {

    override fun addTags(arg: HolderLookup.Provider) {
        getOrCreateTagBuilder(HAPaintingTags.KEEPS_PAINTING_VARIANT)
            .add(HAPaintings.MELON)
            .add(HAPaintings.CONCH_STREET)
            .add(HAPaintings.BOLD_AND_BRASH)
            .add(HAPaintings.BIG_LURE)
            .add(HAPaintings.PRESERVER)
            .add(HAPaintings.SUNSET)
            .add(HAPaintings.RIVERFISH)
            .add(HAPaintings.JAM)
            .add(HAPaintings.SWIRLING)
            .add(HAPaintings.POULPE_COLOSSAL)
            .add(HAPaintings.BELOW)
            .add(HAPaintings.SUNTIDE)
            .add(HAPaintings.MOONCATCHER)
            .add(HAPaintings.NEVEN_NAIVEN)
            .add(HAPaintings.ORGANON)
            .add(HAPaintings.CREATURES_OF_THE_DEEP)
            .add(HAPaintings.PRESSED_KELP)
            .add(HAPaintings.PRESSED_BULL_KELP)
            .add(HAPaintings.BIGEYE)
            .add(HAPaintings.JAWS)
            .add(HAPaintings.GREAT_LAKE)
            .add(HAPaintings.PRAYA_DUBIA)
            .add(HAPaintings.KING_OF_HERRING)
            .add(HAPaintings.PRIDE_FLAG)
            .add(HAPaintings.GAY_PRIDE_FLAG)
            .add(HAPaintings.LESBIAN_PRIDE_FLAG)
            .add(HAPaintings.TRANS_PRIDE_FLAG)
            .add(HAPaintings.NONBINARY_PRIDE_FLAG)
            .add(HAPaintings.ASEXUAL_PRIDE_FLAG)
            .add(HAPaintings.BISEXUAL_PRIDE_FLAG)
            .add(HAPaintings.PALESTINE_FLAG)
            .add(HAPaintings.JOLLY_ROGER)

        getOrCreateTagBuilder(HAPaintingTags.TRANSPARENT_PAINTING)
            .add(HAPaintings.ASEXUAL_PRIDE_FLAG)
            .add(HAPaintings.BISEXUAL_PRIDE_FLAG)
            .add(HAPaintings.GAY_PRIDE_FLAG)
            .add(HAPaintings.LESBIAN_PRIDE_FLAG)
            .add(HAPaintings.PALESTINE_FLAG)
            .add(HAPaintings.PRIDE_FLAG)
            .add(HAPaintings.TRANS_PRIDE_FLAG)
            .add(HAPaintings.NONBINARY_PRIDE_FLAG)
            .add(HAPaintings.BIG_LURE)
            .add(HAPaintings.JOLLY_ROGER)
            .add(HAPaintings.PRESERVER)
    }

    override fun reverseLookup(element: PaintingVariant): ResourceKey<PaintingVariant> {
        return BuiltInRegistries.PAINTING_VARIANT.getResourceKey(element).orElseThrow {
            IllegalArgumentException(
                "Painting Variant $element is not registered"
            )
        } as ResourceKey<PaintingVariant>
    }
}