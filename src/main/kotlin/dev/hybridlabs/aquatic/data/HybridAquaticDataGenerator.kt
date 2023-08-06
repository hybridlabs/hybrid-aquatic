package dev.hybridlabs.aquatic.data

import dev.hybridlabs.aquatic.data.client.LanguageProvider
import dev.hybridlabs.aquatic.data.client.ModelProvider
import dev.hybridlabs.aquatic.data.server.BlockLootTableProvider
import dev.hybridlabs.aquatic.data.server.BlockTagProvider
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator

object HybridAquaticDataGenerator : DataGeneratorEntrypoint {
    override fun onInitializeDataGenerator(generator: FabricDataGenerator) {
        val pack = generator.createPack()
        pack.addProvider(::LanguageProvider)
        pack.addProvider(::ModelProvider)
        pack.addProvider(::BlockLootTableProvider)
        pack.addProvider(::BlockTagProvider)
    }
}
