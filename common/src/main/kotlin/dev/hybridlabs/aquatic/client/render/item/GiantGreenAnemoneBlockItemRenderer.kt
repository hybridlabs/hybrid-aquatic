package dev.hybridlabs.aquatic.client.render.item

import dev.hybridlabs.aquatic.block.HABlocks
import dev.hybridlabs.aquatic.block.entity.GiantGreenAnemoneBlockEntity
import dev.hybridlabs.aquatic.client.model.block.entity.GiantGreenAnemoneBlockEntityModel
import dev.hybridlabs.aquatic.item.GiantGreenAnemoneBlockItem
import net.minecraft.core.BlockPos

class GiantGreenAnemoneBlockItemRenderer : HABlockItemRenderer<GiantGreenAnemoneBlockItem, GiantGreenAnemoneBlockEntity>(
    GiantGreenAnemoneBlockEntityModel(),
    GiantGreenAnemoneBlockEntity(BlockPos.ZERO, HABlocks.GIANT_GREEN_ANEMONE.get().defaultBlockState())
)
