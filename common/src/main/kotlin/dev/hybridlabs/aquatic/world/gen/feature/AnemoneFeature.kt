package dev.hybridlabs.aquatic.world.gen.feature

import com.mojang.serialization.Codec
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.tag.HybridAquaticBlockTags
import net.minecraft.core.Direction
import net.minecraft.core.Holder
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.tags.FluidTags
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.DirectionalBlock
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock
import net.minecraft.world.level.block.state.properties.AttachFace
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration
import java.util.*

class AnemoneFeature(configuration: Codec<NoneFeatureConfiguration>) :
    Feature<NoneFeatureConfiguration>(configuration) {

    override fun place(context: FeaturePlaceContext<NoneFeatureConfiguration>): Boolean {
        val level = context.level()
        val pos = context.origin()
        val random = context.random()

        val anemone: Optional<Block> = BuiltInRegistries.BLOCK
            .getTag(HybridAquaticBlockTags.ANEMONES)
            .flatMap { holders -> holders.getRandomElement(level.random) }
            .map(Holder<Block>::value)

        var placedCount = 0

        val baseBlock = anemone
            .map { it.defaultBlockState() }
            .orElseGet { HybridAquaticBlocks.ANEMONE.get().defaultBlockState() }
            .setValue(FaceAttachedHorizontalDirectionalBlock.FACE, AttachFace.FLOOR)
            .setValue(BlockStateProperties.WATERLOGGED, true)

        for (j in 0 until 5) {
            val blockPos = pos.offset(
                random.nextInt(8) - random.nextInt(8),
                random.nextInt(4) - random.nextInt(4),
                random.nextInt(8) - random.nextInt(8)
            )

            if (level.getFluidState(blockPos).`is`(FluidTags.WATER) && blockPos.y < 255 && baseBlock.canSurvive(
                    level,
                    blockPos
                )
            ) {
                var direction = Direction.getRandom(random)
                while (!baseBlock.setValue(FaceAttachedHorizontalDirectionalBlock.FACING, direction)
                        .canSurvive(level, blockPos)
                ) {
                    direction = Direction.getRandom(random)
                }

                level.setBlock(
                    blockPos,
                    baseBlock
                        .setValue(FaceAttachedHorizontalDirectionalBlock.FACING, direction)
                        .setValue(BlockStateProperties.WATERLOGGED, true),
                    2
                )

                placedCount++
            }
        }

        return placedCount > 0
    }
}