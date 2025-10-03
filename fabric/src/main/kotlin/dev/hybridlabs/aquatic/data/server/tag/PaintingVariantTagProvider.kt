package dev.hybridlabs.aquatic.data.server.tag

import dev.hybridlabs.aquatic.painting.HybridAquaticPaintings
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.resources.ResourceKey
import net.minecraft.tags.PaintingVariantTags
import net.minecraft.world.entity.decoration.PaintingVariant
import java.util.concurrent.CompletableFuture

class PaintingVariantTagProvider (output: FabricDataOutput, registriesFuture: CompletableFuture<HolderLookup.Provider>) :
    FabricTagProvider<PaintingVariant>(output, Registries.PAINTING_VARIANT, registriesFuture) {
    override fun addTags(arg: HolderLookup.Provider) {
        setOf(
            HybridAquaticPaintings.TEST_PAINTING1,
            HybridAquaticPaintings.TEST_PAINTING2
        ).forEach { painting ->
            getOrCreateTagBuilder(PaintingVariantTags.PLACEABLE).add(painting)
        }
    }

    override fun reverseLookup(element: PaintingVariant): ResourceKey<PaintingVariant> {
        return BuiltInRegistries.PAINTING_VARIANT.getResourceKey(element).orElseThrow {
            IllegalArgumentException(
                "Painting Variant $element is not registered"
            )
        } as ResourceKey<PaintingVariant>
    }
}