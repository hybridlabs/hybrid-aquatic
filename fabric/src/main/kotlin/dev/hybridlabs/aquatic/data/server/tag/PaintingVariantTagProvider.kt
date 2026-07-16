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
    }

    override fun reverseLookup(element: PaintingVariant): ResourceKey<PaintingVariant> {
        return BuiltInRegistries.PAINTING_VARIANT.getResourceKey(element).orElseThrow {
            IllegalArgumentException(
                "Painting Variant $element is not registered"
            )
        } as ResourceKey<PaintingVariant>
    }
}