package dev.hybridlabs.aquatic.entity.ai.goal

import dev.hybridlabs.hapi.entity.base.aquatic.BaseWaterAnimal
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

class UrchinEatKelpGoal(
    private val waterAnimal: BaseWaterAnimal,
    private val targetKelp: TagKey<Block>
) : Goal() {

    private var eatTime = 0
    private var eatCooldown: Int
    private var targetPos: BlockPos? = null
    private var spawnUrchinOnNextBreak = false

    init {
        this.flags = EnumSet.of(Flag.MOVE, Flag.LOOK)

        eatCooldown = waterAnimal.tickCount + (10 * 20 + waterAnimal.random.nextInt(60) * 20)
    }

    override fun canUse(): Boolean {
        if (eatCooldown > waterAnimal.tickCount) {
            return false
        }

        if (waterAnimal.fromCreatureNet) {
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
        eatTime = 60
        eatCooldown = waterAnimal.tickCount + (10 * 20 + waterAnimal.random.nextInt(60) * 20)
    }

    override fun stop() {
        waterAnimal.stopGrazing()
        targetPos = null
    }

    override fun canContinueToUse(): Boolean {
        val pos = targetPos ?: return false

        return eatTime > 0 &&
                waterAnimal.level().getBlockState(pos).`is`(targetKelp)
    }

    override fun tick() {
        val pos = targetPos ?: return

        eatTime--

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

        if (eatTime % 10 == 0) {
            spawnGrazingParticles(pos)
        }

        if (eatTime <= 0) {
            breakKelpAbove(pos)
        }
    }

    private fun breakKelpAbove(basePos: BlockPos) {
        val posAbove = basePos.above()
        val level = waterAnimal.level()

        if (level.getBlockState(posAbove).`is`(targetKelp)) {
            level.destroyBlock(posAbove, false)

            if (spawnUrchinOnNextBreak) {
                val newUrchin = waterAnimal.type.create(level)

                newUrchin?.moveTo(
                    posAbove.x + 0.5,
                    posAbove.y.toDouble(),
                    posAbove.z + 0.5,
                    waterAnimal.yRot,
                    0.0f
                )

                if (newUrchin != null) {
                    level.addFreshEntity(newUrchin)
                }

                spawnUrchinOnNextBreak = false
            } else {
                spawnUrchinOnNextBreak = true
            }
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
                        level.getBlockState(pos).`is`(targetKelp) &&
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
            val motionX = waterAnimal.random.nextGaussian() * 0.07
            val motionY = waterAnimal.random.nextGaussian() * 0.07
            val motionZ = waterAnimal.random.nextGaussian() * 0.07
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