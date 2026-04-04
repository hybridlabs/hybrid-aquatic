package dev.hybridlabs.aquatic.entity.ai.goal

import dev.hybridlabs.aquatic.entity.base.HATameableWaterAnimal
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.ai.goal.target.TargetGoal
import net.minecraft.world.entity.ai.targeting.TargetingConditions
import java.util.*

class HAOwnerHurtTargetGoal(private val tameAnimal: HATameableWaterAnimal) : TargetGoal(
    tameAnimal, false
) {
    private var ownerLastHurt: LivingEntity? = null
    private var timestamp = 0

    init {
        this.flags = EnumSet.of<Flag>(Flag.TARGET)
    }

    override fun canUse(): Boolean {
        if (this.tameAnimal.isTame() && !this.tameAnimal.isOrderedToSit()) {
            val livingentity = this.tameAnimal.owner
            if (livingentity == null) {
                return false
            } else {
                this.ownerLastHurt = livingentity.lastHurtMob
                val i = livingentity.lastHurtMobTimestamp
                return i != this.timestamp && this.canAttack(
                    this.ownerLastHurt,
                    TargetingConditions.DEFAULT
                ) && this.tameAnimal.wantsToAttack(this.ownerLastHurt, livingentity)
            }
        } else {
            return false
        }
    }

    override fun start() {
        this.mob.target = this.ownerLastHurt
        val livingentity = this.tameAnimal.owner
        if (livingentity != null) {
            this.timestamp = livingentity.lastHurtMobTimestamp
        }

        super.start()
    }
}