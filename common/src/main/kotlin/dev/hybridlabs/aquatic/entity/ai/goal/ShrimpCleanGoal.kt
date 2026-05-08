package dev.hybridlabs.aquatic.entity.ai.goal

import dev.hybridlabs.aquatic.entity.crustacean.ShrimpEntity
import net.minecraft.core.BlockPos
import net.minecraft.core.particles.BlockParticleOption
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.server.level.ServerLevel
import net.minecraft.tags.FluidTags
import net.minecraft.util.Mth
import net.minecraft.world.entity.ai.goal.Goal
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import java.util.*

class ShrimpCleanGoal(
    private val shrimp: ShrimpEntity
) : Goal() {

    companion object {
        val CLEANABLE_BLOCKS: Map<Block, Block> = mapOf(
            Blocks.MOSSY_COBBLESTONE to Blocks.COBBLESTONE,
            Blocks.MOSSY_COBBLESTONE_SLAB to Blocks.COBBLESTONE_SLAB,
            Blocks.MOSSY_COBBLESTONE_STAIRS to Blocks.COBBLESTONE_STAIRS,
            Blocks.MOSSY_COBBLESTONE_WALL to Blocks.COBBLESTONE_WALL,
            Blocks.MOSSY_STONE_BRICKS to Blocks.STONE_BRICKS,
            Blocks.MOSSY_STONE_BRICK_SLAB to Blocks.STONE_BRICK_SLAB,
            Blocks.MOSSY_STONE_BRICK_STAIRS to Blocks.STONE_BRICK_STAIRS,
            Blocks.MOSSY_STONE_BRICK_WALL to Blocks.STONE_BRICK_WALL,
            Blocks.INFESTED_MOSSY_STONE_BRICKS to Blocks.INFESTED_STONE_BRICKS,
            Blocks.OXIDIZED_COPPER to Blocks.WEATHERED_COPPER,
            Blocks.WEATHERED_COPPER to Blocks.EXPOSED_COPPER,
            Blocks.EXPOSED_COPPER to Blocks.COPPER_BLOCK,
            Blocks.OXIDIZED_CUT_COPPER to Blocks.WEATHERED_CUT_COPPER,
            Blocks.WEATHERED_CUT_COPPER to Blocks.EXPOSED_CUT_COPPER,
            Blocks.EXPOSED_CUT_COPPER to Blocks.CUT_COPPER,
            Blocks.OXIDIZED_CUT_COPPER_SLAB to Blocks.WEATHERED_CUT_COPPER_SLAB,
            Blocks.WEATHERED_CUT_COPPER_SLAB to Blocks.EXPOSED_CUT_COPPER_SLAB,
            Blocks.EXPOSED_CUT_COPPER_SLAB to Blocks.CUT_COPPER_SLAB,
            Blocks.OXIDIZED_CUT_COPPER_STAIRS to Blocks.WEATHERED_CUT_COPPER_STAIRS,
            Blocks.WEATHERED_CUT_COPPER_STAIRS to Blocks.EXPOSED_CUT_COPPER_STAIRS,
            Blocks.EXPOSED_CUT_COPPER_STAIRS to Blocks.CUT_COPPER_STAIRS,
        )
    }

    private var cleanTime = 0
    private var cleanCooldown: Int
    private var targetPos: BlockPos? = null

    init {
        this.flags = EnumSet.of(Flag.MOVE, Flag.LOOK)

        cleanCooldown =
            shrimp.tickCount + (10 * 40 + shrimp.random.nextInt(10) * 40)
    }

    override fun canUse(): Boolean {
        if (cleanCooldown > shrimp.tickCount) {
            return false
        }

        val found = findNearbyMossyBlock()

        if (found != null) {
            targetPos = found
            return true
        }

        return false
    }

    override fun start() {
        cleanTime = 60
    }

    override fun stop() {
        shrimp.stopCleaning()
        targetPos = null
    }

    override fun canContinueToUse(): Boolean {
        val pos = targetPos ?: return false

        return cleanTime > 0 &&
                shrimp.level().getBlockState(pos).block in CLEANABLE_BLOCKS
    }

    override fun tick() {
        val pos = targetPos ?: return

        cleanTime--

        shrimp.lookControl.setLookAt(
            pos.x + 0.5,
            pos.y + 0.5,
            pos.z + 0.5
        )

        val distance = shrimp.distanceToSqr(
            pos.x + 0.5,
            pos.y + 0.5,
            pos.z + 0.5
        )

        if (distance > 1.0) {
            shrimp.navigation.moveTo(
                pos.x + 0.5,
                pos.y + 0.5,
                pos.z + 0.5,
                1.0
            )
            return
        }

        shrimp.navigation.stop()
        shrimp.startCleaning()

        if (cleanTime % 10 == 0) {
            spawnCleaningParticles(targetPos!!)
        }

        if (cleanTime <= 0) {
            cleanBlock(pos)

            stop()
        }
    }

    private fun cleanBlock(pos: BlockPos) {
        val level = shrimp.level()
        val state = level.getBlockState(pos)

        val cleaned = CLEANABLE_BLOCKS[state.block] ?: return

        cleanCooldown =
            shrimp.tickCount + (10 * 20 + shrimp.random.nextInt(10) * 20)

        level.setBlockAndUpdate(pos, cleaned.defaultBlockState())
    }

    private fun findNearbyMossyBlock(): BlockPos? {
        val level = shrimp.level()
        val origin = shrimp.blockPosition()

        val radius = 4

        for (x in -radius..radius) {
            for (y in -2..2) {
                for (z in -radius..radius) {
                    val pos = origin.offset(x, y, z)
                    val block = level.getBlockState(pos).block

                    if (
                        (block in CLEANABLE_BLOCKS) &&
                        level.getFluidState(pos.above()).`is`(FluidTags.WATER)
                    ) {
                        return pos
                    }
                }
            }
        }

        return null
    }

    fun spawnCleaningParticles(target: BlockPos) {
        val radius = 0.3f
        for (i1 in 0..2) {
            val motionX = shrimp.getRandom().nextGaussian() * 0.07
            val motionY = shrimp.getRandom().nextGaussian() * 0.07
            val motionZ = shrimp.getRandom().nextGaussian() * 0.07
            val angle = ((0.0174532925 * shrimp.yBodyRot) + i1).toFloat()
            val extraX = (radius * Mth.sin(Mth.PI + angle)).toDouble()
            val extraY = 0.8
            val extraZ = (radius * Mth.cos(angle)).toDouble()
            val state = shrimp.level().getBlockState(target)
            (shrimp.level() as ServerLevel).sendParticles(
                BlockParticleOption(
                    ParticleTypes.BLOCK,
                    state
                ),
                target.x + 0.5 + extraX,
                target.y + 0.5 + extraY,
                target.z + 0.5 + extraZ,
                1,
                motionX,
                motionY,
                motionZ,
                1.0
            )
        }
    }
}