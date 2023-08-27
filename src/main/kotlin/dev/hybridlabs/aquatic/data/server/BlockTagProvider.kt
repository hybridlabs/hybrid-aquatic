package dev.hybridlabs.aquatic.data.server

import dev.hybridlabs.aquatic.HybridAquatic
import dev.hybridlabs.aquatic.block.BlahajPlushieBlock
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

        // blahaj plushies
        Registries.BLOCK
            .filter { block ->
                val id = Registries.BLOCK.getId(block)
                id.namespace == HybridAquatic.MOD_ID
            }
            .forEach { block ->
                // blahaj plushies
                if (block is BlahajPlushieBlock) {
                    getOrCreateTagBuilder(HybridAquaticBlockTags.BLAHAJ_PLUSHIES).add(block)
                }
            }
    }
}
