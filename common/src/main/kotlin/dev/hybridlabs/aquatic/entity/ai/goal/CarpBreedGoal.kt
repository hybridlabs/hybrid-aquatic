package dev.hybridlabs.aquatic.entity.ai.goal

import dev.hybridlabs.aquatic.entity.fish.CarpEntity
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.entity.ai.goal.Goal
import net.minecraft.world.entity.ai.targeting.TargetingConditions
import net.minecraft.world.level.Level
import java.util.*

open class CarpBreedGoal @JvmOverloads constructor(
    protected val carp: CarpEntity,
    private val speedModifier: Double,
    private val partnerClass: Class<out CarpEntity> = carp.javaClass,
) : Goal() {
    protected val level: Level = carp.level()
    protected var partner: CarpEntity? = null
    private var loveTime = 0

    init {
        this.flags = EnumSet.of<Flag?>(Flag.MOVE, Flag.LOOK)
    }

    override fun canUse(): Boolean {
        if (!this.carp.isInLove()) {
            return false
        } else {
            this.partner = this.freePartner
            return true
        }
    }

    override fun canContinueToUse(): Boolean {
        val mate = this.partner ?: return false
        return mate.isAlive && mate.isInLove() && this.loveTime < 60
    }

    override fun stop() {
        this.partner = null
        this.loveTime = 0
    }

    override fun tick() {
        val mate = this.partner ?: return

        this.carp.getLookControl().setLookAt(mate, 10.0f, this.carp.maxHeadXRot.toFloat())
        this.carp.getNavigation().moveTo(mate, this.speedModifier)
        ++this.loveTime
        if (this.loveTime >= this.adjustedTickDelay(60) && this.carp.distanceToSqr(mate) < 9.0) {
            this.breed()
        }
    }

    private val freePartner: CarpEntity?
        get() {
            val list: MutableList<out CarpEntity> = this.level.getNearbyEntities(
                this.partnerClass,
                PARTNER_TARGETING,
                this.carp,
                this.carp.boundingBox.inflate(8.0)
            )
            var d0 = Double.MAX_VALUE
            var waterAnimal: CarpEntity? = null

            for (waterAnimal1 in list) {
                if (this.carp.canMate(waterAnimal1) &&
                    this.carp.distanceToSqr(waterAnimal1) < d0
                ) {
                    waterAnimal = waterAnimal1
                    d0 = this.carp.distanceToSqr(waterAnimal1)
                }
            }

            return waterAnimal
        }

    protected open fun breed() {
        val mate = this.partner ?: return
        this.carp.spawnChildFromBreeding(this.level as ServerLevel, mate)
    }


    companion object {
        private val PARTNER_TARGETING: TargetingConditions =
            TargetingConditions.forNonCombat().range(8.0).ignoreLineOfSight()
    }
}