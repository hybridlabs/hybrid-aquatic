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
            .add(HAPaintings.TEST_PAINTING1)
            .add(HAPaintings.TEST_PAINTING2)
            .add(HAPaintings.MELON)
            .add(HAPaintings.PRAYA_DUBIA)
            .add(HAPaintings.KING_OF_HERRING)
            .add(HAPaintings.PRIDE_FLAG)
            .add(HAPaintings.GAY_PRIDE_FLAG)
            .add(HAPaintings.LESBIAN_PRIDE_FLAG)
            .add(HAPaintings.TRANS_PRIDE_FLAG)
            .add(HAPaintings.ASEXUAL_PRIDE_FLAG)
            .add(HAPaintings.BISEXUAL_PRIDE_FLAG)
            .add(HAPaintings.PALESTINE_FLAG)
            .add(HAPaintings.JOLLY_ROGER)
    }
}