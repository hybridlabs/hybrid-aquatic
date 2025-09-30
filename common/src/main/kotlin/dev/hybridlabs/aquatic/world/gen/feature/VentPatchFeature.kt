package dev.hybridlabs.aquatic.world.gen.feature

import com.mojang.serialization.Codec
import dev.hybridlabs.aquatic.block.ThermalVentBlock
import dev.hybridlabs.aquatic.block.TubeWormBlock
import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import net.minecraft.core.BlockPos
import net.minecraft.core.Direction
import net.minecraft.core.Vec3i
import net.minecraft.util.RandomSource
import net.minecraft.util.valueproviders.IntProvider
import net.minecraft.world.entity.MobSpawnType
import net.minecraft.world.level.LevelAccessor
import net.minecraft.world.level.ServerLevelAccessor
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.properties.BlockStateProperties.WATERLOGGED
import net.minecraft.world.level.block.state.properties.DripstoneThickness
import net.minecraft.world.level.levelgen.feature.Feature
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider
import kotlin.math.max
import kotlin.math.sqrt

@Suppress("NAME_SHADOWING", "SameParameterValue")
class VentPatchFeature(codec: Codec<VentPatchFeatureConfig>) : Feature<VentPatchFeatureConfig>(codec) {
    companion object {
        val MAX_VENT_HEIGHT = 5
        val MIN_VENT_HEIGHT = 2
        val MIN_VENT_CLEARANCE = 2

    }

    override fun place(context: FeaturePlaceContext<VentPatchFeatureConfig>): Boolean {
        var generated = false
        val world = context.level()
        val origin = context.origin()
        val random = context.random()

        val (baseProvider, ventProvider, wormProvider, countProvider, radiusProvider, wormCountProvider, wormRadiusProvider, wormCountPerBlockProvider) = context.config()

        val count = countProvider.sample(random)
        repeat(count) {
            val radius = radiusProvider.sample(random)
            val offsetX = random.nextInt(radius * 2 + 1) - radius
            val offsetZ = random.nextInt(radius * 2 + 1) - radius
            val candidatePos = origin.offset(offsetX, 0, offsetZ)

            val distanceFromCenter = sqrt((offsetX * offsetX + offsetZ * offsetZ).toDouble())
            val heightMultiplier = 1.0 - (distanceFromCenter / radius).coerceIn(0.0, 1.0)

            if (generateSingleVent(world, candidatePos, random, heightMultiplier, baseProvider, ventProvider)) {
                val wormCount = wormCountProvider.sample(random)
                val wormRadius = wormRadiusProvider.sample(random)
                generateTubeWormPatch(
                    world,
                    candidatePos,
                    random,
                    wormCount,
                    wormCountPerBlockProvider,
                    wormRadius,
                    wormProvider
                )

                val biome = world.getBiome(candidatePos)
                if (biome.`is`(HybridAquaticBiomeTags.ARCTIC_OCEANS)) {
                    spawnYetiCrabsAroundVent(world, candidatePos, random, 1, 3)
                }

                generated = true
            }
        }

        return generated
    }

    private fun generateSingleVent(
        world: LevelAccessor,
        rootPos: BlockPos,
        random: RandomSource,
        heightMultiplier: Double,
        baseProvider: BlockStateProvider,
        ventProvider: BlockStateProvider,
    ): Boolean {
        if (!world.isWaterAt(rootPos)) {
            return false
        }

        val mutablePos = rootPos.mutable()

        val state = ventProvider.getState(random, mutablePos)
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
            val state = baseProvider.getState(random, mutablePos)
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

    private fun spawnYetiCrabsAroundVent(
        world: ServerLevelAccessor,
        rootPos: BlockPos,
        random: RandomSource,
        count: Int,
        radius: Int
    ) {
        repeat(count) {
            val offsetX = random.nextInt(radius * 2 + 1) - radius
            val offsetZ = random.nextInt(radius * 2 + 1) - radius
            val spawnPos = rootPos.offset(offsetX, 0, offsetZ)


            if (world.isWaterAt(spawnPos)) {
                val yetiCrabEntity = HybridAquaticEntityTypes.YETI_CRAB.get().create(world.level) ?: return@repeat
                yetiCrabEntity.moveTo(spawnPos, random.nextFloat() * 360F, 0F)
                yetiCrabEntity.setPersistenceRequired()
                yetiCrabEntity.finalizeSpawn(
                    world,
                    world.getCurrentDifficultyAt(spawnPos),
                    MobSpawnType.STRUCTURE,
                    null,
                    null
                )
                world.addFreshEntity(yetiCrabEntity)
            }
        }
    }

    private fun generateHydrothermalVent(
        world: LevelAccessor,
        pos: BlockPos,
        cycle: Int,
        height: Int,
        state: BlockState
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

    private fun getHydrothermalVentThickness(cycle: Int, height: Int): DripstoneThickness {
        if (cycle == 0) {
            return DripstoneThickness.BASE
        }

        if (cycle == height - 1) {
            return DripstoneThickness.TIP
        }

        return DripstoneThickness.MIDDLE
    }

    private fun calculateVentHeight(heightMultiplier: Double): Int {
        return max(MIN_VENT_HEIGHT, (MIN_VENT_HEIGHT + (MAX_VENT_HEIGHT - MIN_VENT_HEIGHT) * heightMultiplier).toInt())
    }

    private fun generateTubeWormPatch(
        world: LevelAccessor,
        pos: BlockPos,
        random: RandomSource,
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

            val tubeWormPos = pos.offset(offset).mutable()


            repeat(3){
                val testState = world.getBlockState(tubeWormPos)
                if (testState.block != Blocks.WATER) {
                    tubeWormPos.move(Direction.UP)
                } else if (!testState.isFaceSturdy(world, tubeWormPos, Direction.UP)) {
                    tubeWormPos.move(Direction.DOWN)
                }
            }

            val state = stateProvider.getState(random, tubeWormPos)
            if (isValidPosition(world, tubeWormPos, state)) {
                val wormCount = wormCountProvider.sample(random)
                world.setBlock(tubeWormPos, state.setValue(TubeWormBlock.WORMS, wormCount), Block.UPDATE_CLIENTS)
            }
        }
    }

    private fun isValidPosition(world: LevelAccessor, pos: BlockPos, state: BlockState): Boolean {
        val existingState = world.getBlockState(pos)

        if (!existingState.canBeReplaced()) {
            return false
        }

        return state.canSurvive(world, pos)
    }
}
