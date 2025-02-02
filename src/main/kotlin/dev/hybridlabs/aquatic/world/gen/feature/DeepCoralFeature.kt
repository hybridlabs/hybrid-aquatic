package dev.hybridlabs.aquatic.world.gen.feature

import com.mojang.serialization.Codec
import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.TubeWormBlock
import dev.hybridlabs.aquatic.tag.HybridAquaticBlockTags
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.DeadCoralWallFanBlock
import net.minecraft.registry.Registries
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.registry.entry.RegistryEntryList
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.random.Random
import net.minecraft.world.WorldAccess
import net.minecraft.world.gen.feature.DefaultFeatureConfig
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.util.FeatureContext

abstract class DeepCoralFeature(codec: Codec<DefaultFeatureConfig>) : Feature<DefaultFeatureConfig>(codec) {
    override fun generate(context: FeatureContext<DefaultFeatureConfig?>): Boolean {
        val random = context.random
        val structureWorldAccess = context.world
        val blockPos = context.origin
        val optional =
            Registries.BLOCK.getEntryList(HybridAquaticBlockTags.DEEP_CORAL_BLOCKS).flatMap { blocks: RegistryEntryList.Named<Block?> ->
                blocks.getRandom(
                    random
                )
            }.map { obj: RegistryEntry<Block?> -> obj.value() }
        return if (optional.isEmpty) false else this.generateDeepCoral(
            structureWorldAccess,
            random,
            blockPos,
            optional.get().defaultState
        )
    }

    protected abstract fun generateDeepCoral(
        world: WorldAccess,
        random: Random,
        pos: BlockPos,
        state: BlockState
    ): Boolean

    protected fun generateDeepCoralPiece(world: WorldAccess, random: Random, pos: BlockPos, state: BlockState?): Boolean {
        val blockPos = pos.up()
        val blockState = world.getBlockState(pos)
        if ((blockState.isOf(Blocks.WATER) || blockState.isIn(HybridAquaticBlockTags.DEEP_CORALS)) && world.getBlockState(blockPos)
                .isOf(Blocks.WATER)
        ) {
            world.setBlockState(pos, state, 3)
            if (random.nextFloat() < 0.25f) {
                Registries.BLOCK.getEntryList(HybridAquaticBlockTags.DEEP_CORALS).flatMap { blocks: RegistryEntryList.Named<Block> ->
                    blocks.getRandom(
                        random
                    )
                }.map { obj: RegistryEntry<Block> -> obj.value() }.ifPresent { block: Block ->
                    world.setBlockState(blockPos, block.defaultState, 2)
                }
            } else if (random.nextFloat() < 0.05f) {
                world.setBlockState(
                    blockPos,
                    HybridAquaticBlocks.TUBE_WORM.defaultState.with(TubeWormBlock.WORMS, random.nextInt(4) + 1), 2
                )
            }

            val var7: Iterator<*> = Direction.Type.HORIZONTAL.iterator()

            while (var7.hasNext()) {
                val direction = var7.next() as Direction
                if (random.nextFloat() < 0.2f) {
                    val blockPos2 = pos.offset(direction)
                    if (world.getBlockState(blockPos2).isOf(Blocks.WATER)) {
                        Registries.BLOCK.getEntryList(HybridAquaticBlockTags.DEEP_WALL_CORALS)
                            .flatMap { blocks: RegistryEntryList.Named<Block> ->
                                blocks.getRandom(
                                    random
                                )
                            }.map { obj: RegistryEntry<Block> -> obj.value() }.ifPresent { block: Block ->
                                var blockState = block.defaultState
                                if (blockState.contains(DeadCoralWallFanBlock.FACING)) {
                                    blockState =
                                        blockState.with(
                                            DeadCoralWallFanBlock.FACING,
                                            direction
                                        ) as BlockState
                                }
                                world.setBlockState(blockPos2, blockState, 2)
                            }
                    }
                }
            }

            return true
        } else {
            return false
        }
    }
}
