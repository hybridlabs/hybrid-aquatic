package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import net.minecraft.world.item.Item
import java.util.function.Supplier

object HybridAquaticBlockEntityItems {
    val blockEntityItems: HashMap<String, Supplier<Item>> = hashMapOf(
        "anemone" to Supplier {
            AnemoneBlockItem(
                HybridAquaticBlocks.ANEMONE.get(),
                Item.Properties()
            )
        },
        "strawberry_anemone" to Supplier {
            StrawberryAnemoneBlockItem(
                HybridAquaticBlocks.STRAWBERRY_ANEMONE.get(),
                Item.Properties()
            )
        },
        "giant_green_anemone" to Supplier {
            GiantGreenAnemoneBlockItem(
                HybridAquaticBlocks.GIANT_GREEN_ANEMONE.get(),
                Item.Properties()
            )
        }
    )
}