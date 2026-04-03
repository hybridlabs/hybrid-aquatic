package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.block.HABlocks
import net.minecraft.world.item.Item
import java.util.function.Supplier

object HABlockEntityItems {
    val blockEntityItems: HashMap<String, Supplier<Item>> = hashMapOf(
        "anemone" to Supplier {
            AnemoneBlockItem(
                HABlocks.ANEMONE.get(),
                Item.Properties()
            )
        },
        "strawberry_anemone" to Supplier {
            StrawberryAnemoneBlockItem(
                HABlocks.STRAWBERRY_ANEMONE.get(),
                Item.Properties()
            )
        },
        "giant_green_anemone" to Supplier {
            GiantGreenAnemoneBlockItem(
                HABlocks.GIANT_GREEN_ANEMONE.get(),
                Item.Properties()
            )
        }
    )
}