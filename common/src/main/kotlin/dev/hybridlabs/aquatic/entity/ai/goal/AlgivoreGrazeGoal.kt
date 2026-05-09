package dev.hybridlabs.aquatic.entity.ai.goal

import dev.hybridlabs.aquatic.entity.fish.HAFishEntity
import dev.hybridlabs.aquatic.tag.HABlockTags
import net.minecraft.core.BlockPos
import net.minecraft.core.particles.BlockParticleOption
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.server.level.ServerLevel
import net.minecraft.tags.FluidTags
import net.minecraft.util.Mth
import net.minecraft.world.entity.ai.goal.Goal
import java.util.*

class AlgivoreGrazeGoal(
    private val fish: HAFishEntity,
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

        val found = findNearbyAlgaeBlock()

        if (found != null) {
            targetPos = found
            return true
        }

        return false
    }

    override fun start() {
        grazeTime = 60
        grazeCooldown = fish.tickCount + 400 + fish.random.nextInt(400)
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

        val distance = fish.distanceToSqr(
            pos.x + 0.5,
            pos.y + 0.5,
            pos.z + 0.5
        )

        fish.navigation.moveTo(
            pos.x + 0.5,
            pos.y + 0.5,
            pos.z + 0.5,
            1.0
        )

        fish.lookControl.setLookAt(
            pos.x + 0.5,
            pos.y + 0.5,
            pos.z + 0.5
        )

        if (distance > 2.0) {
            return
        }

        fish.navigation.stop()
        fish.startGrazing()

        if (grazeTime % 10 == 0) {
            spawnGrazingParticles(targetPos!!)
        }
    }

    private fun findNearbyAlgaeBlock(): BlockPos? {
        val level = fish.level()
        val origin = fish.blockPosition()

        val radius = 4

        for (x in -radius..radius) {
            for (y in -2..2) {
                for (z in -radius..radius) {
                    val pos = origin.offset(x, y, z)

                    if (
                        level.getBlockState(pos).`is`(HABlockTags.ALGIVORE_EDIBLE) &&
                        level.getFluidState(pos.above()).`is`(FluidTags.WATER)
                    ) {
                        return pos
                    }
                }
            }
        }

        return null
    }

    fun spawnGrazingParticles(target: BlockPos) {
        val radius = 0.3f
        for (i1 in 0..2) {
            val motionX = fish.getRandom().nextGaussian() * 0.07
            val motionY = fish.getRandom().nextGaussian() * 0.07
            val motionZ = fish.getRandom().nextGaussian() * 0.07
            val angle = ((0.0174532925 * fish.yBodyRot) + i1).toFloat()
            val extraX = (radius * Mth.sin(Mth.PI + angle)).toDouble()
            val extraY = 0.8
            val extraZ = (radius * Mth.cos(angle)).toDouble()
            val state = fish.level().getBlockState(target)
            (fish.level() as ServerLevel).sendParticles(
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