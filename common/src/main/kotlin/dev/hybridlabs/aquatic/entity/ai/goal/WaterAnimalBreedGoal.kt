package dev.hybridlabs.aquatic.entity.ai.goal

import dev.hybridlabs.aquatic.entity.base.HAWaterAnimal
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.entity.VariantHolder
import net.minecraft.world.entity.ai.goal.Goal
import net.minecraft.world.entity.ai.targeting.TargetingConditions
import net.minecraft.world.level.Level
import java.util.*

open class WaterAnimalBreedGoal @JvmOverloads constructor(
    protected val waterAnimal: HAWaterAnimal,
    private val speedModifier: Double,
    private val partnerClass: Class<out HAWaterAnimal> = waterAnimal.javaClass,
) : Goal() {
    protected val level: Level = waterAnimal.level()
    protected var partner: HAWaterAnimal? = null
    private var loveTime = 0

    init {
        this.flags = EnumSet.of<Flag?>(Flag.MOVE, Flag.LOOK)
    }

    override fun canUse(): Boolean {
        if (!this.waterAnimal.isInLove()) {
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

        this.waterAnimal.getLookControl().setLookAt(mate, 10.0f, this.waterAnimal.maxHeadXRot.toFloat())
        this.waterAnimal.getNavigation().moveTo(mate, this.speedModifier)
        ++this.loveTime
        if (this.loveTime >= this.adjustedTickDelay(60) && this.waterAnimal.distanceToSqr(mate) < 9.0) {
            this.breed()
        }
    }

    private fun variantsMatch(a: HAWaterAnimal, b: HAWaterAnimal): Boolean {
        return if (a is VariantHolder<*> && b is VariantHolder<*>) {
            a.variant == b.variant
        } else {
            true
        }
    }

    private val freePartner: HAWaterAnimal?
        get() {
            val list: MutableList<out HAWaterAnimal> = this.level.getNearbyEntities(
                this.partnerClass,
                PARTNER_TARGETING,
                this.waterAnimal,
                this.waterAnimal.boundingBox.inflate(8.0)
            )
            var d0 = Double.MAX_VALUE
            var waterAnimal: HAWaterAnimal? = null

            for (waterAnimal1 in list) {
                if (this.waterAnimal.canMate(waterAnimal1) &&
                    variantsMatch(this.waterAnimal, waterAnimal1) &&
                    this.waterAnimal.distanceToSqr(waterAnimal1) < d0
                ) {
                    waterAnimal = waterAnimal1
                    d0 = this.waterAnimal.distanceToSqr(waterAnimal1)
                }
            }

            return waterAnimal
        }

    protected open fun breed() {
        val mate = this.partner ?: return
        this.waterAnimal.spawnChildFromBreeding(this.level as ServerLevel, mate)
    }


    companion object {
        private val PARTNER_TARGETING: TargetingConditions =
            TargetingConditions.forNonCombat().range(8.0).ignoreLineOfSight()
    }
}