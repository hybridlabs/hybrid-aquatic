package dev.hybridlabs.aquatic.client.model

import dev.hybridlabs.aquatic.Constants
import net.minecraft.data.models.model.ModelTemplate
import net.minecraft.data.models.model.TextureSlot
import net.minecraft.resources.ResourceLocation
import java.util.Optional

object HAModelTemplates {
    val INCREMENTAL_BLOCK_1: ModelTemplate = create("block/incremental_block_1", TextureSlot.TEXTURE, TextureSlot.PARTICLE)
    val INCREMENTAL_BLOCK_2: ModelTemplate = create("block/incremental_block_2", TextureSlot.TEXTURE, TextureSlot.PARTICLE)
    val INCREMENTAL_BLOCK_3: ModelTemplate = create("block/incremental_block_3", TextureSlot.TEXTURE, TextureSlot.PARTICLE)
    val INCREMENTAL_BLOCK_4: ModelTemplate = create("block/incremental_block_4", TextureSlot.TEXTURE, TextureSlot.PARTICLE)

    private fun create(id: String, vararg slots: TextureSlot): ModelTemplate {
        return ModelTemplate(
            Optional.of(
                ResourceLocation.fromNamespaceAndPath(Constants.MOD_ID, id)
            ),
            Optional.empty(), *slots
        )
    }
}
