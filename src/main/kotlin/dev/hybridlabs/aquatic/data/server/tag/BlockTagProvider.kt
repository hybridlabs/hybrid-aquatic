package dev.hybridlabs.aquatic.data.server.tag

import dev.hybridlabs.aquatic.block.PlushieBlock
import dev.hybridlabs.aquatic.data.HybridAquaticDataGenerator.filterHybridAquatic
import dev.hybridlabs.aquatic.tag.HybridAquaticBlockTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.block.Blocks
import net.minecraft.registry.Registries
import net.minecraft.registry.RegistryWrapper
import java.util.concurrent.CompletableFuture

class BlockTagProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>) : FabricTagProvider.BlockTagProvider(output, registriesFuture) {
    override fun configure(arg: RegistryWrapper.WrapperLookup) {
        // misc
        getOrCreateTagBuilder(HybridAquaticBlockTags.ANEMONES_GENERATE_IN)
            .add(Blocks.WATER)

        getOrCreateTagBuilder(HybridAquaticBlockTags.MESSAGE_IN_A_BOTTLE_SPAWNS_IN)
            .add(Blocks.WATER)

        getOrCreateTagBuilder(HybridAquaticBlockTags.CRAB_DIGGABLE_BLOCKS)
            .addTag(HybridAquaticBlockTags.CRAB_DIGGABLE_TREASURE_BLOCKS)
            .add(Blocks.DIRT)
            .add(Blocks.COARSE_DIRT)
            .add(Blocks.GRASS_BLOCK)

        getOrCreateTagBuilder(HybridAquaticBlockTags.CRAB_DIGGABLE_TREASURE_BLOCKS)
            .add(Blocks.SAND)
            .add(Blocks.SUSPICIOUS_SAND)
            .add(Blocks.GRAVEL)
            .add(Blocks.SUSPICIOUS_GRAVEL)

        // plushies
        Registries.BLOCK
            .filter(filterHybridAquatic(Registries.BLOCK))
            .forEach { block ->
                // plushies
                if (block is PlushieBlock) {
                    getOrCreateTagBuilder(HybridAquaticBlockTags.PLUSHIES).add(block)
                }
            }
    }
}
