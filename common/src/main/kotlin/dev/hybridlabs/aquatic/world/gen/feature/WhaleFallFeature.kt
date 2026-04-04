package dev.hybridlabs.aquatic.world.gen.feature

import com.mojang.serialization.Codec
import net.minecraft.world.level.ChunkPos
import net.minecraft.world.level.WorldGenLevel
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.Mirror
import net.minecraft.world.level.block.Rotation
import net.minecraft.world.level.levelgen.Heightmap
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext
import net.minecraft.world.level.levelgen.structure.BoundingBox
import net.minecraft.world.level.levelgen.structure.templatesystem.StructurePlaceSettings
import org.apache.commons.lang3.mutable.MutableInt
import kotlin.math.max
import kotlin.math.min

class WhaleFallFeature(codec: Codec<WhaleFallFeatureConfig>) : Feature<WhaleFallFeatureConfig>(codec) {

    override fun place(context: FeaturePlaceContext<WhaleFallFeatureConfig>): Boolean {
        val randomsource = context.random()
        val worldgenlevel = context.level()
        val blockpos = context.origin()
        val rotation = Rotation.getRandom(randomsource)
        val config = context.config()

        val i = randomsource.nextInt(config.whaleFallStructures.size)
        val structuretemplatemanager = worldgenlevel.level.server.structureManager
        val structuretemplate = structuretemplatemanager.getOrCreate(config.whaleFallStructures[i])

        val chunkpos = ChunkPos(blockpos)
        val boundingbox = BoundingBox(
            chunkpos.minBlockX - 16,
            worldgenlevel.minBuildHeight,
            chunkpos.minBlockZ - 16,
            chunkpos.maxBlockX + 16,
            worldgenlevel.maxBuildHeight,
            chunkpos.maxBlockZ + 16
        )

        val settings = StructurePlaceSettings()
            .setRotation(rotation)
            .setBoundingBox(boundingbox)
            .setRandom(randomsource)

        val size = structuretemplate.getSize(rotation)
        val startPos = blockpos.offset(-size.x / 2, 0, -size.z / 2)

        var minY = blockpos.y
        for (x in 0 until size.x) {
            for (z in 0 until size.z) {
                minY = min(
                    minY,
                    worldgenlevel.getHeight(
                        Heightmap.Types.OCEAN_FLOOR_WG,
                        startPos.x + x,
                        startPos.z + z
                    )
                )
            }
        }

        val finalY = max(minY - 15 - randomsource.nextInt(10), worldgenlevel.minBuildHeight + 10)
        val placementPos = structuretemplate.getZeroPositionWithTransform(
            startPos.atY(finalY),
            Mirror.NONE,
            rotation
        )

        if (countEmptyCorners(worldgenlevel, structuretemplate.getBoundingBox(settings, placementPos))
            > config.maxEmptyCornersAllowed
        ) {
            return false
        }

        structuretemplate.placeInWorld(
            worldgenlevel,
            placementPos,
            placementPos,
            settings,
            randomsource,
            4
        )

        return true
    }

    companion object {
        private fun countEmptyCorners(level: WorldGenLevel, boundingBox: BoundingBox): Int {
            val count = MutableInt(0)
            boundingBox.forAllCorners { pos ->
                val state = level.getBlockState(pos)
                if (state.isAir || state.`is`(Blocks.LAVA) || state.`is`(Blocks.WATER)) {
                    count.increment()
                }
            }
            return count.value
        }
    }
}