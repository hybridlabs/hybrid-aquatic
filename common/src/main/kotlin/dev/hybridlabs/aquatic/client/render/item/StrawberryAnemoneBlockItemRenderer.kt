package dev.hybridlabs.aquatic.client.render.item

import dev.hybridlabs.aquatic.block.HABlocks
import dev.hybridlabs.aquatic.block.entity.StrawberryAnemoneBlockEntity
import dev.hybridlabs.aquatic.client.model.block.entity.StrawberryAnemoneBlockEntityModel
import dev.hybridlabs.aquatic.item.StrawberryAnemoneBlockItem
import net.minecraft.core.BlockPos

class StrawberryAnemoneBlockItemRenderer : HABlockItemRenderer<StrawberryAnemoneBlockItem, StrawberryAnemoneBlockEntity>(
    StrawberryAnemoneBlockEntityModel(),
    StrawberryAnemoneBlockEntity(BlockPos.ZERO, HABlocks.STRAWBERRY_ANEMONE.get().defaultBlockState())
)
