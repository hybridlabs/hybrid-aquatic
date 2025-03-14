package dev.hybridlabs.aquatic.world.gen.feature

import com.mojang.serialization.Codec
import dev.hybridlabs.aquatic.block.ThermalVentBlock
import dev.hybridlabs.aquatic.block.TubeWormBlock
import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import net.minecraft.block.Block
import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.block.enums.Thickness
import net.minecraft.entity.SpawnReason
import net.minecraft.state.property.Properties
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Direction
import net.minecraft.util.math.intprovider.IntProvider
import net.minecraft.util.math.random.Random
import net.minecraft.world.Heightmap
import net.minecraft.world.ServerWorldAccess
import net.minecraft.world.WorldAccess
import net.minecraft.world.gen.feature.Feature
import net.minecraft.world.gen.feature.util.FeatureContext
import net.minecraft.world.gen.stateprovider.BlockStateProvider
import kotlin.math.max
import kotlin.math.sqrt

@Suppress("NAME_SHADOWING", "SameParameterValue")
class VentPatchFeature(codec: Codec<VentPatchFeatureConfig>) : Feature<VentPatchFeatureConfig>(codec) {
    override fun generate(context: FeatureContext<VentPatchFeatureConfig>): Boolean {
        var generated = false

        val world = context.world
        val origin = context.origin
        val random = context.random

        val (baseProvider, ventProvider, wormProvider, countProvider, radiusProvider, wormCountProvider, wormRadiusProvider, wormCountPerBlockProvider) = context.config

        val count = countProvider.get(random)
        repeat(count) {
            val radius = radiusProvider.get(random)
            val offsetX = random.nextInt(radius * 2 + 1) - radius
            val offsetZ = random.nextInt(radius * 2 + 1) - radius
            val candidatePos = origin.add(offsetX, 0, offsetZ)

            val topY = world.getTopY(Heightmap.Type.OCEAN_FLOOR_WG, candidatePos.x, candidatePos.z)
            val candidateTopPos = BlockPos(candidatePos.x, topY, candidatePos.z)

            val distanceFromCenter = sqrt((offsetX * offsetX + offsetZ * offsetZ).toDouble())
            val heightMultiplier = 1.0 - (distanceFromCenter / radius).coerceIn(0.0, 1.0)

            if (generateSingleVent(world, candidateTopPos, random, heightMultiplier, baseProvider, ventProvider)) {
                val wormCount = wormCountProvider.get(random)
                val wormRadius = wormRadiusProvider.get(random)
                generateTubeWormPatch(world, candidateTopPos, random, wormCount, wormCountPerBlockProvider, wormRadius, wormProvider)

                val biome = world.getBiome(candidateTopPos)
                if (biome.isIn(HybridAquaticBiomeTags.ARCTIC_OCEANS)) {
                    spawnYetiCrabsAroundVent(world, candidateTopPos, random, 1, 3)
                }

                generated = true
            }
        }

        return generated
    }

    private fun generateSingleVent(
        world: WorldAccess,
        rootPos: BlockPos,
        random: Random,
        heightMultiplier: Double,
        baseProvider: BlockStateProvider,
        ventProvider: BlockStateProvider,
    ): Boolean {
        if (!world.isWater(rootPos)) {
            return false
        }

        val mutablePos = rootPos.mutableCopy()

        val state = ventProvider.get(random, mutablePos)
        if (!isValidPosition(world, mutablePos, state)) {
            return false
        }

        val baseThickness = 1 + random.nextInt(3)
        repeat(baseThickness) {
            val state = baseProvider.get(random, mutablePos)
            world.setBlockState(mutablePos, state, Block.NOTIFY_LISTENERS)
            mutablePos.move(Direction.UP)
        }

        val ventHeight = calculateVentHeight(heightMultiplier)
        repeat(ventHeight) { cycle ->
            generateHydrothermalVent(world, mutablePos, cycle, ventHeight, state)
            mutablePos.move(Direction.UP)
        }

        return true
    }

    private fun spawnYetiCrabsAroundVent(world: ServerWorldAccess, rootPos: BlockPos, random: Random, count: Int, radius: Int) {
        repeat(count) {
            val offsetX = random.nextInt(radius * 2 + 1) - radius
            val offsetZ = random.nextInt(radius * 2 + 1) - radius
            val spawnPos = rootPos.add(offsetX, 0, offsetZ)

            val spawnY = world.getTopY(Heightmap.Type.OCEAN_FLOOR_WG, spawnPos.x, spawnPos.z)
            val candidatePos = BlockPos(spawnPos.x, spawnY, spawnPos.z)

            if (world.isWater(candidatePos)) {
                val yetiCrabEntity = HybridAquaticEntityTypes.YETI_CRAB.create(world.toServerWorld()) ?: return@repeat
                yetiCrabEntity.refreshPositionAndAngles(candidatePos, random.nextFloat() * 360F, 0F)
                yetiCrabEntity.setPersistent()
                yetiCrabEntity.initialize(
                    world,
                    world.getLocalDifficulty(candidatePos),
                    SpawnReason.STRUCTURE,
                    null,
                    null
                )
                world.spawnEntity(yetiCrabEntity)
            }
        }
    }

    private fun generateHydrothermalVent(
        world: WorldAccess,
        pos: BlockPos,
        cycle: Int,
        height: Int,
        state: BlockState
    ) {
        val thickness = getHydrothermalVentThickness(cycle, height)
        world.setBlockState(
            pos,
            state
                .with(Properties.WATERLOGGED, world.isWater(pos))
                .with(ThermalVentBlock.THICKNESS, thickness),
            Block.NOTIFY_LISTENERS
        )
    }

    private fun getHydrothermalVentThickness(cycle: Int, height: Int): Thickness {
        if (cycle == 0) {
            return Thickness.BASE
        }

        if (cycle == height - 1) {
            return Thickness.TIP
        }

        return Thickness.MIDDLE
    }

    private fun calculateVentHeight(heightMultiplier: Double): Int {
        val maxVentHeight = 5
        val minVentHeight = 2
        return max(minVentHeight, (minVentHeight + (maxVentHeight - minVentHeight) * heightMultiplier).toInt())
    }

    private fun generateTubeWormPatch(
        world: WorldAccess,
        pos: BlockPos,
        random: Random,
        count: Int,
        wormCountProvider: IntProvider,
        radius: Int,
        stateProvider: BlockStateProvider
    ) {
        repeat(count) {
            val offset = BlockPos(
                random.nextInt(radius * 2) - radius,
                random.nextInt(radius * 2) - radius,
                random.nextInt(radius * 2) - radius,
            )

            val targetPos = pos.add(offset)
            val surfaceY = world.getTopY(Heightmap.Type.OCEAN_FLOOR, targetPos.x, targetPos.z)
            val tubeWormPos = BlockPos(targetPos.x, surfaceY, targetPos.z)

            if (world.getBlockState(tubeWormPos).block != Blocks.WATER) {
                return@repeat
            }

            val state = stateProvider.get(random, tubeWormPos)
            if (isValidPosition(world, tubeWormPos, state)) {
                val wormCount = wormCountProvider.get(random)
                world.setBlockState(tubeWormPos, state.with(TubeWormBlock.WORMS, wormCount), Block.NOTIFY_LISTENERS)
            }
        }
    }

    private fun isValidPosition(world: WorldAccess, pos: BlockPos, state: BlockState): Boolean {
        val existingState = world.getBlockState(pos)

        if (!existingState.isReplaceable) {
            return false
        }

        return state.canPlaceAt(world, pos)
    }
}
