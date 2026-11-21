package dev.hybridlabs.aquatic.client.render.item

import dev.hybridlabs.aquatic.block.HABlocks
import dev.hybridlabs.aquatic.block.entity.AnemoneBlockEntity
import dev.hybridlabs.aquatic.client.model.block.entity.AnemoneBlockEntityModel
import dev.hybridlabs.aquatic.item.AnemoneBlockItem
import net.minecraft.core.BlockPos

class AnemoneBlockItemRenderer : HABlockItemRenderer<AnemoneBlockItem, AnemoneBlockEntity>(
    AnemoneBlockEntityModel(),
    AnemoneBlockEntity(BlockPos.ZERO, HABlocks.ANEMONE.get().defaultBlockState())
)
