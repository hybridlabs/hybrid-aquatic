package dev.hybridlabs.aquatic.world.gen.feature

import com.mojang.serialization.Codec
import dev.hybridlabs.aquatic.tag.HybridAquaticBlockTags
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.Holder
import net.minecraft.core.HolderSet
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.util.RandomSource
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.block.BaseCoralWallFanBlock
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration
import java.util.function.Consumer
import java.util.function.Function

abstract class BleachedCoralFeature(codec: Codec<NoneFeatureConfiguration?>) : Feature<NoneFeatureConfiguration?>(codec) {
    override fun place(context: FeaturePlaceContext<NoneFeatureConfiguration?>): Boolean {
        val randomSource = context.random()
        val worldGenLevel = context.level()
        val blockPos = context.origin()
        val optional = BuiltInRegistries.BLOCK.getTag(HybridAquaticBlockTags.BLEACHED_CORAL_BLOCKS)
            .flatMap(Function { named: HolderSet.Named<Block?>? ->
                named!!.getRandomElement(randomSource)
            }).map<Block>(
                Function { obj: Holder<Block?>? -> obj!!.value() })
        return if (optional.isEmpty) false else this.placeFeature(
            worldGenLevel, randomSource, blockPos, optional.get()
                .defaultBlockState()
        )
    }

    protected abstract fun placeFeature(
        level: LevelAccessor,
        random: RandomSource,
        pos: BlockPos,
        state: BlockState,
    ): Boolean

    protected fun placeBleachedCoralBlock(
        level: LevelAccessor,
        random: RandomSource,
        pos: BlockPos,
        state: BlockState,
    ): Boolean {
        val blockPos = pos.above()
        val blockState = level.getBlockState(pos)
        if ((blockState.`is`(Blocks.WATER) || blockState.`is`(HybridAquaticBlockTags.BLEACHED_CORALS)) && level.getBlockState(blockPos).`is`(
                Blocks.WATER
            )
        ) {
            level.setBlock(pos, state, 3)
            if (random.nextFloat() < 0.25f) {
                BuiltInRegistries.BLOCK.getTag(HybridAquaticBlockTags.BLEACHED_CORALS)
                    .flatMap(Function { named: HolderSet.Named<Block?>? ->
                        named!!.getRandomElement(random)
                    }).map<Block?>(
                        Function { obj: Holder<Block?>? -> obj!!.value() })
                    .ifPresent(Consumer { block: Block? -> level.setBlock(blockPos, block!!.defaultBlockState(), 2) })
            }

            for (direction in Direction.Plane.HORIZONTAL) {
                if (random.nextFloat() < 0.2f) {
                    val blockPos2 = pos.relative(direction)
                    if (level.getBlockState(blockPos2).`is`(Blocks.WATER)) {
                        BuiltInRegistries.BLOCK.getTag(HybridAquaticBlockTags.BLEACHED_WALL_CORALS)
                            .flatMap(Function { named: HolderSet.Named<Block?>? ->
                                named!!.getRandomElement(random)
                            }).map<Block?>(
                                Function { obj: Holder<Block?>? -> obj!!.value() })
                            .ifPresent(Consumer { block: Block? ->
                                var blockState = block!!.defaultBlockState()
                                if (blockState.hasProperty(BaseCoralWallFanBlock.FACING)) {
                                    blockState = blockState.setValue(
                                        BaseCoralWallFanBlock.FACING,
                                        direction
                                    ) as BlockState
                                }
                                level.setBlock(blockPos2, blockState, 2)
                            })
                    }
                }
            }

            return true
        } else {
            return false
        }
    }
}