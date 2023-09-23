package dev.hybridlabs.aquatic.data.server

import dev.hybridlabs.aquatic.item.HybridAquaticItems
import dev.hybridlabs.aquatic.tag.HybridAquaticItemTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider
import net.minecraft.registry.RegistryWrapper
import java.util.concurrent.CompletableFuture

class ItemTagProvider(output: FabricDataOutput, registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>) : FabricTagProvider.ItemTagProvider(output, registriesFuture) {
    override fun configure(arg: RegistryWrapper.WrapperLookup) {
        // Items that will be consumed by a fishing rod
        listOf(
            HybridAquaticItems.BARBED_HOOK,
            HybridAquaticItems.GLOWING_HOOK,
            HybridAquaticItems.MAGNETIC_HOOK
        ).forEach { item ->
            getOrCreateTagBuilder(HybridAquaticItemTags.LURE_ITEMS).add(item)
        }
        listOf(
            HybridAquaticItems.LIONFISH,
            HybridAquaticItems.ROCKFISH,
            HybridAquaticItems.BLUE_SPOTTED_STINGRAY,
            HybridAquaticItems.NEEDLEFISH,
            HybridAquaticItems.PIRANHA,
            HybridAquaticItems.ANGLERFISH,
            HybridAquaticItems.BARRELEYE,
            HybridAquaticItems.BLUE_TANG,
            HybridAquaticItems.CLOWNFISH,
            HybridAquaticItems.UNICORN_FISH,
            HybridAquaticItems.COWFISH,
            HybridAquaticItems.TRIGGERFISH,
            HybridAquaticItems.TIGER_BARB,
            HybridAquaticItems.OSCAR,
        ).forEach { item ->
            getOrCreateTagBuilder(HybridAquaticItemTags.SMALL_FISH).add(item)
        }
        listOf(
            HybridAquaticItems.YELLOWFIN_TUNA,
            HybridAquaticItems.MAHI_MAHI
        ).forEach { item ->
            getOrCreateTagBuilder(HybridAquaticItemTags.MEDIUM_FISH).add(item)
        }
    }
}