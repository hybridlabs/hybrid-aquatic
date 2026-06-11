package dev.hybridlabs.aquatic.world.gen.feature

import com.mojang.serialization.Codec
import dev.hybridlabs.aquatic.loot.HALootTables
import net.minecraft.core.BlockPos
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.entity.BlockEntityType
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext
import net.minecraft.world.level.levelgen.feature.configurations.DiskConfiguration

class SuspiciousSandDiskFeature(
    codec: Codec<DiskConfiguration>
) : Feature<DiskConfiguration>(codec) {

    override fun place(context: FeaturePlaceContext<DiskConfiguration>): Boolean {
        val placed = DISK.place(context)
        if (!placed) return false

        val level = context.level()
        val origin = context.origin()

        val radius = context.config().radius().sample(context.random())
        val halfHeight = context.config().halfHeight()

        BlockPos.betweenClosed(
            origin.offset(-radius, -halfHeight, -radius),
            origin.offset(radius, halfHeight, radius)
        ).forEach { pos ->
            if (level.getBlockState(pos).`is`(Blocks.SUSPICIOUS_SAND)) {
                level.getBlockEntity(pos, BlockEntityType.BRUSHABLE_BLOCK)
                    .ifPresent { brushable ->
                        brushable.setLootTable(
                            HALootTables.BEACH_ARCHAEOLOGY_ID,
                            pos.asLong()
                        )
                    }
            }
        }

        return true
    }
}