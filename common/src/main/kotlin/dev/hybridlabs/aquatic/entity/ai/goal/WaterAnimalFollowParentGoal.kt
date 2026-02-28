package dev.hybridlabs.aquatic.entity.ai.goal

import dev.hybridlabs.aquatic.entity.base.HybridAquaticWaterAnimal
import net.minecraft.world.entity.ai.goal.Goal

open class WaterAnimalFollowParentGoal(private val waterAnimal: HybridAquaticWaterAnimal, private val speedModifier: Double) : Goal() {
    private var parent: HybridAquaticWaterAnimal? = null
    private var timeToRecalcPath = 0

    override fun canUse(): Boolean {
        if (this.waterAnimal.getAge() >= 0) {
            return false
        } else {
            val list = this.waterAnimal.level()
                .getEntitiesOfClass(this.waterAnimal.javaClass, this.waterAnimal.boundingBox.inflate(8.0, 4.0, 8.0))
            var waterAnimal: HybridAquaticWaterAnimal? = null
            var d0 = Double.MAX_VALUE

            for (waterAnimal1 in list) {
                if (waterAnimal1.getAge() >= 0) {
                    val d1 = this.waterAnimal.distanceToSqr(waterAnimal1)
                    if (!(d1 > d0)) {
                        d0 = d1
                        waterAnimal = waterAnimal1
                    }
                }
            }

            if (waterAnimal == null) {
                return false
            } else if (d0 < 9.0) {
                return false
            } else {
                this.parent = waterAnimal
                return true
            }
        }
    }

    override fun canContinueToUse(): Boolean {
        if (this.waterAnimal.getAge() >= 0) {
            return false
        } else if (!this.parent!!.isAlive) {
            return false
        } else {
            val d0 = this.waterAnimal.distanceToSqr(this.parent)
            return !(d0 < 9.0) && !(d0 > 256.0)
        }
    }

    override fun start() {
        this.timeToRecalcPath = 0
    }

    override fun stop() {
        this.parent = null
    }

    override fun tick() {
        if (--this.timeToRecalcPath <= 0) {
            this.timeToRecalcPath = this.adjustedTickDelay(10)
            this.waterAnimal.getNavigation().moveTo(this.parent, this.speedModifier)
        }
    }
}