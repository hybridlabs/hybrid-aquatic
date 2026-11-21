package dev.hybridlabs.aquatic.world.gen.feature

import com.mojang.serialization.Codec
import com.mojang.serialization.MapCodec
import com.mojang.serialization.codecs.RecordCodecBuilder
import dev.hybridlabs.aquatic.block.GiantThermalVentBlock
import dev.hybridlabs.aquatic.block.HABlocks
import dev.hybridlabs.aquatic.block.ThermalVentBlock
import dev.hybridlabs.aquatic.block.TubeWormBlock
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.Vec3i
import net.minecraft.util.RandomSource
import net.minecraft.util.valueproviders.IntProvider
import net.minecraft.world.level.WorldGenLevel
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.BlockStateProperties.WATERLOGGED
import net.minecraft.world.level.levelgen.Column
import net.minecraft.world.level.chunk.ChunkGenerator
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider
import java.util.*
import java.util.function.Predicate
import kotlin.math.max
import kotlin.math.sqrt

@Suppress("NAME_SHADOWING", "SameParameterValue")
data class VentPatchFeature(
    val floorSearchRange: Int = 0,
    val baseProvider: BlockStateProvider,
    val ventProvider: BlockStateProvider,
    val giantVentProvider: BlockStateProvider,
    val wormProvider: BlockStateProvider,
    val count: IntProvider,
    val spreadRadius: IntProvider,
    val wormCount: IntProvider,
    val wormSpreadRadius: IntProvider,
    val wormCountPerBlock: IntProvider,
) : Feature {
    override fun codec(): MapCodec<VentPatchFeature> = CODEC

    companion object {
        const val MAX_VENT_HEIGHT = 5
        const val MIN_VENT_HEIGHT = 2
        const val MIN_VENT_CLEARANCE = 2

        val CODEC: MapCodec<VentPatchFeature> = RecordCodecBuilder.mapCodec { instance ->
            instance.group(
                Codec.intRange(0, 512).fieldOf("floor_search_range")
                    .forGetter(VentPatchFeature::floorSearchRange),
                BlockStateProvider.DIRECT_CODEC.fieldOf("base_block").forGetter(VentPatchFeature::baseProvider),
                BlockStateProvider.DIRECT_CODEC.fieldOf("vent_block").forGetter(VentPatchFeature::ventProvider),
                BlockStateProvider.DIRECT_CODEC.fieldOf("giant_vent_block")
                    .forGetter(VentPatchFeature::giantVentProvider),
                BlockStateProvider.DIRECT_CODEC.fieldOf("worm_block").forGetter(VentPatchFeature::wormProvider),
                net.minecraft.util.valueproviders.IntProviders.POSITIVE_CODEC.fieldOf("count")
                    .forGetter(VentPatchFeature::count),
                net.minecraft.util.valueproviders.IntProviders.POSITIVE_CODEC.fieldOf("spread_radius")
                    .forGetter(VentPatchFeature::spreadRadius),
                net.minecraft.util.valueproviders.IntProviders.POSITIVE_CODEC.fieldOf("worm_count")
                    .forGetter(VentPatchFeature::wormCount),
                net.minecraft.util.valueproviders.IntProviders.POSITIVE_CODEC.fieldOf("worm_spread_radius")
                    .forGetter(VentPatchFeature::wormSpreadRadius),
                TubeWormBlock.WORM_COUNT_CODEC.fieldOf("worm_count_per_block")
                    .forGetter(VentPatchFeature::wormCountPerBlock),
            ).apply(instance, ::VentPatchFeature)
        }
    }

    private fun getFloorY(
        level: WorldGenLevel,
        pos: BlockPos,
    ): OptionalInt {
        val water = Predicate<BlockState> { it.`is`(Blocks.WATER) }
        val nonWater = Predicate<BlockState> { !it.`is`(Blocks.WATER) }

        return Column.scan(level, pos, this.floorSearchRange, water, nonWater)
            .map(Column::getFloor)
            .orElseGet { OptionalInt.empty() }
    }

    override fun place(
        world: WorldGenLevel,
        chunkGenerator: ChunkGenerator,
        random: RandomSource,
        origin: BlockPos,
    ): Boolean {
        var generated = false

        val ventCount = count.sample(random)

        repeat(ventCount) {
            val radius = spreadRadius.sample(random)

            val offsetX = random.nextInt(radius * 2 + 1) - radius
            val offsetZ = random.nextInt(radius * 2 + 1) - radius

            val candidatePos = origin.offset(offsetX, 0, offsetZ)

            val floorY = getFloorY(world, candidatePos)
            if (floorY.isEmpty) return@repeat

            val floorPos = candidatePos.atY(floorY.asInt)

            val distanceFromCenter = sqrt((offsetX * offsetX + offsetZ * offsetZ).toDouble())
            val heightMultiplier = 1.0 - (distanceFromCenter / radius).coerceIn(0.0, 1.0)

            if (
                generateSingleVent(
                    world,
                    floorPos.above(),
                    random,
                    heightMultiplier,
                    baseProvider,
                    ventProvider
                )
            ) {
                val wormCount = wormCount.sample(random)
                val wormRadius = wormSpreadRadius.sample(random)

                generateTubeWormPatch(
                    world,
                    floorPos,
                    random,
                    wormCount,
                    wormCountPerBlock,
                    wormRadius,
                    wormProvider
                )

                generated = true
            }
        }

        val giantVentCount = random.nextInt(2)

        repeat(giantVentCount) {
            val radius = spreadRadius.sample(random)

            val offsetX = random.nextInt(radius * 2 + 1) - radius
            val offsetZ = random.nextInt(radius * 2 + 1) - radius

            val candidatePos = origin.offset(offsetX, 0, offsetZ)

            val floorY = getFloorY(world, candidatePos)
            if (floorY.isEmpty) return@repeat

            val floorPos = candidatePos.atY(floorY.asInt)

            val distanceFromCenter = sqrt((offsetX * offsetX + offsetZ * offsetZ).toDouble())
            val heightMultiplier = 1.0 - (distanceFromCenter / radius).coerceIn(0.0, 1.0)

            if (
                generateGiantVent(
                    world,
                    floorPos.above(),
                    random,
                    heightMultiplier,
                    giantVentProvider
                )
            ) {
                generated = true
            }
        }

        return generated
    }

    private fun generateSingleVent(
        world: WorldGenLevel,
        rootPos: BlockPos,
        random: RandomSource,
        heightMultiplier: Double,
        baseProvider: BlockStateProvider,
        ventProvider: BlockStateProvider,
    ): Boolean {
        val belowPos = rootPos.below()
        if (!world.getBlockState(belowPos).isFaceSturdy(world, belowPos, Direction.UP) ||
            world.getBlockState(belowPos).`is`(HABlocks.THERMAL_VENT.get()) ||
            world.getBlockState(belowPos).`is`(HABlocks.GIANT_THERMAL_VENT.get())) {
            return false
        }

        val mutablePos = rootPos.mutable()

        val state = ventProvider.getState(world, random, mutablePos)
        if (!isValidPosition(world, mutablePos, state)) {
            return false
        }

        val baseThickness = 1 + random.nextInt(3)

        val minHeight = baseThickness + MAX_VENT_HEIGHT + MIN_VENT_CLEARANCE

        mutablePos.move(Vec3i(0, minHeight, 0))
        while (mutablePos.y > rootPos.y) {
            if (!world.isWaterAt(mutablePos)) return false
            mutablePos.move(Direction.DOWN)
        }

        repeat(baseThickness) {
            val state = baseProvider.getState(world, random, mutablePos)
            world.setBlock(mutablePos, state, Block.UPDATE_CLIENTS)
            mutablePos.move(Direction.UP)
        }

        val ventHeight = calculateVentHeight(heightMultiplier)
        repeat(ventHeight) { cycle ->
            generateHydrothermalVent(world, mutablePos, cycle, ventHeight, state)
            mutablePos.move(Direction.UP)
        }

        return true
    }

    private fun generateGiantVent(
        world: WorldGenLevel,
        rootPos: BlockPos,
        random: RandomSource,
        heightMultiplier: Double,
        giantVentProvider: BlockStateProvider,
    ): Boolean {
        val belowPos = rootPos.below()
        if (!world.getBlockState(belowPos).isFaceSturdy(world, belowPos, Direction.UP) ||
            world.getBlockState(belowPos).`is`(HABlocks.THERMAL_VENT.get()) ||
            world.getBlockState(belowPos).`is`(HABlocks.GIANT_THERMAL_VENT.get())) {
            return false
        }

        val mutablePos = rootPos.mutable()

        val state = giantVentProvider.getState(world, random, mutablePos)
        if (!isValidPosition(world, mutablePos, state)) {
            return false
        }

        val minHeight = MAX_VENT_HEIGHT + MIN_VENT_CLEARANCE

        mutablePos.move(0, minHeight, 0)
        while (mutablePos.y > rootPos.y) {
            if (!world.isWaterAt(mutablePos)) return false
            mutablePos.move(Direction.DOWN)
        }

        val ventHeight = calculateVentHeight(heightMultiplier) + (1 + random.nextInt(3))

        repeat(ventHeight) { cycle ->
            generateGiantVent(world, mutablePos, cycle, ventHeight, state)
            mutablePos.move(Direction.UP)
        }

        return true
    }

    private fun generateHydrothermalVent(
        world: WorldGenLevel,
        pos: BlockPos,
        cycle: Int,
        height: Int,
        state: BlockState,
    ) {
        val thickness = getHydrothermalVentThickness(cycle, height)
        world.setBlock(
            pos,
            state
                .setValue(WATERLOGGED, world.isWaterAt(pos))
                .setValue(ThermalVentBlock.THICKNESS, thickness),
            Block.UPDATE_CLIENTS
        )
    }

    private fun generateGiantVent(
        world: WorldGenLevel,
        pos: BlockPos,
        cycle: Int,
        height: Int,
        state: BlockState,
    ) {
        val thickness = getGiantVentThickness(cycle, height)
        world.setBlock(
            pos,
            state
                .setValue(GiantThermalVentBlock.THICKNESS, thickness),
            Block.UPDATE_CLIENTS
        )
    }

    private fun getHydrothermalVentThickness(cycle: Int, height: Int): ThermalVentBlock.ThermalVentPosition {
        if (cycle == 0) {
            return ThermalVentBlock.ThermalVentPosition.BASE
        }

        if (cycle == height - 1) {
            return ThermalVentBlock.ThermalVentPosition.TIP
        }

        return ThermalVentBlock.ThermalVentPosition.MIDDLE
    }

    private fun getGiantVentThickness(
        cycle: Int,
        height: Int,
    ): GiantThermalVentBlock.GiantThermalVentPosition {

        if (cycle == 0) {
            return GiantThermalVentBlock.GiantThermalVentPosition.BASE
        }

        if (cycle == height - 1) {
            return GiantThermalVentBlock.GiantThermalVentPosition.TIP
        }

        return GiantThermalVentBlock.GiantThermalVentPosition.MIDDLE
    }

    private fun calculateVentHeight(heightMultiplier: Double): Int {
        return max(MIN_VENT_HEIGHT, (MIN_VENT_HEIGHT + (MAX_VENT_HEIGHT - MIN_VENT_HEIGHT) * heightMultiplier).toInt())
    }

    private fun generateTubeWormPatch(
        world: WorldGenLevel,
        pos: BlockPos,
        random: RandomSource,
        count: Int,
        wormCountProvider: IntProvider,
        radius: Int,
        stateProvider: BlockStateProvider,
    ) {
        repeat(count) {
            val offset = BlockPos(
                random.nextInt(radius * 2) - radius,
                random.nextInt(radius * 2) - radius,
                random.nextInt(radius * 2) - radius,
            )

            val tubeWormPos = pos.offset(offset).mutable()

            repeat(3) {
                val testState = world.getBlockState(tubeWormPos)
                if (testState.block != Blocks.WATER) {
                    tubeWormPos.move(Direction.UP)
                } else if (!testState.isFaceSturdy(world, tubeWormPos, Direction.UP)) {
                    tubeWormPos.move(Direction.DOWN)
                }
            }

            val state = stateProvider.getState(world, random, tubeWormPos)
            if (isValidPosition(world, tubeWormPos, state)) {
                val wormCount = wormCountProvider.sample(random)
                world.setBlock(tubeWormPos, state.setValue(TubeWormBlock.WORMS, wormCount), Block.UPDATE_CLIENTS)
            }
        }
    }

    private fun isValidPosition(world: WorldGenLevel, pos: BlockPos, state: BlockState): Boolean {
        val existingState = world.getBlockState(pos)

        if (!existingState.canBeReplaced()) {
            return false
        }

        return state.canSurvive(world, pos)
    }
}
