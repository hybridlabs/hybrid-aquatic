package dev.hybridlabs.aquatic.entity.ai.goal

import dev.hybridlabs.aquatic.entity.base.HAWaterAnimal
import net.minecraft.core.BlockPos
import net.minecraft.core.particles.BlockParticleOption
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.server.level.ServerLevel
import net.minecraft.tags.FluidTags
import net.minecraft.tags.TagKey
import net.minecraft.util.Mth
import net.minecraft.world.entity.ai.goal.Goal
import net.minecraft.world.level.block.Block
import java.util.*

class WaterAnimalGrazeGoal(
    private val waterAnimal: HAWaterAnimal,
    private val grazeTarget: TagKey<Block>
) : Goal() {

    private var grazeTime = 0
    private var grazeCooldown: Int
    private var targetPos: BlockPos? = null

    init {
        this.flags = EnumSet.of(Flag.MOVE, Flag.LOOK)

        grazeCooldown =
            waterAnimal.tickCount + (10 * 40 + waterAnimal.random.nextInt(10) * 80)
    }

    override fun canUse(): Boolean {
        if (grazeCooldown > waterAnimal.tickCount) {
            return false
        }

        val found = findNearbyGrazeTarget()

        if (found != null) {
            targetPos = found
            return true
        }

        return false
    }

    override fun start() {
        grazeTime = 60
        grazeCooldown = waterAnimal.tickCount + 400 + waterAnimal.random.nextInt(400)
    }

    override fun stop() {
        waterAnimal.stopGrazing()
        targetPos = null
    }

    override fun canContinueToUse(): Boolean {
        val pos = targetPos ?: return false

        return grazeTime > 0 &&
                waterAnimal.level().getBlockState(pos).`is`(grazeTarget)
    }

    override fun tick() {
        val pos = targetPos ?: return

        grazeTime--

        val distance = waterAnimal.distanceToSqr(
            pos.x + 0.5,
            pos.y + 0.5,
            pos.z + 0.5
        )

        waterAnimal.navigation.moveTo(
            pos.x + 0.5,
            pos.y + 0.5,
            pos.z + 0.5,
            1.0
        )

        waterAnimal.lookControl.setLookAt(
            pos.x + 0.5,
            pos.y + 0.5,
            pos.z + 0.5
        )

        if (distance > 2.0) {
            return
        }

        waterAnimal.navigation.stop()
        waterAnimal.startGrazing()

        if (grazeTime % 10 == 0) {
            spawnGrazingParticles(targetPos!!)
        }
    }

    private fun findNearbyGrazeTarget(): BlockPos? {
        val level = waterAnimal.level()
        val origin = waterAnimal.blockPosition()

        val radius = 4

        for (x in -radius..radius) {
            for (y in -2..2) {
                for (z in -radius..radius) {
                    val pos = origin.offset(x, y, z)

                    if (
                        level.getBlockState(pos).`is`(grazeTarget) &&
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
            val motionX = waterAnimal.getRandom().nextGaussian() * 0.07
            val motionY = waterAnimal.getRandom().nextGaussian() * 0.07
            val motionZ = waterAnimal.getRandom().nextGaussian() * 0.07
            val angle = ((0.0174532925 * waterAnimal.yBodyRot) + i1).toFloat()
            val extraX = (radius * Mth.sin(Mth.PI + angle)).toDouble()
            val extraY = 0.8
            val extraZ = (radius * Mth.cos(angle)).toDouble()
            val state = waterAnimal.level().getBlockState(target)
            (waterAnimal.level() as ServerLevel).sendParticles(
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