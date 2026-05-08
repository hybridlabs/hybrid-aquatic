package dev.hybridlabs.aquatic.entity.ai.goal

import dev.hybridlabs.aquatic.entity.fish.HAFishEntity
import dev.hybridlabs.aquatic.tag.HABlockTags
import net.minecraft.core.BlockPos
import net.minecraft.core.particles.BlockParticleOption
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.entity.ai.goal.Goal
import java.util.*

class CorallivoreGrazeGoal(
    private val fish: HAFishEntity
) : Goal() {

    private var grazeTime = 0
    private var grazeCooldown: Int
    private var targetPos: BlockPos? = null

    init {
        this.flags = EnumSet.of(Flag.MOVE, Flag.LOOK)

        grazeCooldown =
            fish.tickCount + (10 * 40 + fish.random.nextInt(10) * 80)
    }

    override fun canUse(): Boolean {
        if (grazeCooldown > fish.tickCount) {
            return false
        }

        val found = findNearbyCoralBlock()

        if (found != null) {
            targetPos = found
            return true
        }

        return false
    }

    override fun start() {
        grazeTime = 40
    }

    override fun stop() {
        fish.stopGrazing()
        targetPos = null
    }

    override fun canContinueToUse(): Boolean {
        val pos = targetPos ?: return false

        return grazeTime > 0 &&
                fish.level().getBlockState(pos).`is`(HABlockTags.ALGIVORE_EDIBLE)
    }

    override fun tick() {
        val pos = targetPos ?: return

        grazeTime--

        fish.lookControl.setLookAt(
            pos.x + 0.5,
            pos.y + 0.5,
            pos.z + 0.5
        )

        val distance = fish.distanceToSqr(
            pos.x + 0.5,
            pos.y + 0.5,
            pos.z + 0.5
        )

        if (distance > 1.0) {
            fish.navigation.moveTo(
                pos.x + 0.5,
                pos.y + 0.5,
                pos.z + 0.5,
                1.0
            )
            return
        }

        fish.navigation.stop()
        fish.startGrazing()

        if (grazeTime % 10 == 0) {
            val level = fish.level()

            if (level is ServerLevel) {
                val state = level.getBlockState(pos)

                level.sendParticles(
                    BlockParticleOption(ParticleTypes.BLOCK, state),
                    pos.x + 0.5,
                    pos.y + 0.5,
                    pos.z + 0.5,
                    6,
                    0.2, 0.2, 0.2,
                    0.02
                )
            }
        }
    }

    private fun findNearbyCoralBlock(): BlockPos? {
        val level = fish.level()
        val origin = fish.blockPosition()

        val radius = 4

        for (x in -radius..radius) {
            for (y in -2..2) {
                for (z in -radius..radius) {
                    val pos = origin.offset(x, y, z)

                    if (level.getBlockState(pos).`is`(HABlockTags.CORALLIVORE_EDIBLE)) {
                        return pos
                    }
                }
            }
        }

        return null
    }
}