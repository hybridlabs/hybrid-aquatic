package dev.hybridlabs.aquatic.entity.ai.goal

import dev.hybridlabs.aquatic.entity.crustacean.HACrustaceanEntity
import net.minecraft.core.particles.BlockParticleOption
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.entity.ai.goal.Goal
import java.util.*

class CrustaceanDigGoal(
    private val crustacean: HACrustaceanEntity
) : Goal() {
    private var digTime = 0
    private var digCooldown: Int

    init {
        this.flags = EnumSet.of(Flag.MOVE, Flag.LOOK)
        digCooldown = crustacean.tickCount + (10 * 40 + crustacean.getRandom().nextInt(10) * 80)
    }

    override fun canUse(): Boolean {
        if (digCooldown > this.crustacean.tickCount ||
            crustacean.level().getNearestPlayer(crustacean, 32.0) == null) {
            return false
        }
        return this.crustacean.getRandom().nextInt(40) == 0
    }

    override fun start() {
        digTime = 40
        crustacean.startDigging()
        digCooldown = this.crustacean.tickCount + (10 * 20 + this.crustacean.getRandom().nextInt(10) * 20)

        val level = this.crustacean.level()
        val blockpos = this.crustacean.blockPosition()
        val blockstate = level.getBlockState(blockpos.below())

        if (level is ServerLevel) {
            level.sendParticles(
                BlockParticleOption(ParticleTypes.BLOCK, blockstate),
                blockpos.x + 0.5,
                blockpos.y + 0.8,
                blockpos.z + 0.5,
                6,
                0.3, 0.5, 0.3,
                0.02
            )
        }
    }

    override fun stop() {
        crustacean.stopDigging()
    }

    override fun canContinueToUse(): Boolean {
        return digTime >= 0
    }

    override fun tick() {
        digTime--
    }
}