package dev.hybridlabs.aquatic.goal

import net.minecraft.entity.ai.goal.TrackTargetGoal
import net.minecraft.entity.mob.MobEntity
import net.minecraft.entity.vehicle.BoatEntity
import net.minecraft.util.math.Box

open class TargetBoatGoal(
    mob: MobEntity,
    checkVisibility: Boolean,
    private val reciprocalChance: Int
) :
    TrackTargetGoal(mob, checkVisibility) {

    private var targetBoat: BoatEntity? = null

    private fun getSearchBox(distance: Double): Box? {
        return mob.boundingBox.expand(distance, 4.0, distance)
    }

    private fun findClosestBoat() {
        val boats = mob.world.getEntitiesByClass(BoatEntity::class.java, this.getSearchBox(this.followRange)) { true }

        if (boats.isEmpty()) {
            targetBoat = null
            return
        }

        boats.sortBy { boat -> boat.squaredDistanceTo(mob) }

        targetBoat = boats[0]
    }

    override fun canStart(): Boolean {
        return if (this.reciprocalChance > 0 && mob.random.nextInt(this.reciprocalChance) != 0) {
            false
        } else {
            this.findClosestBoat()
            this.targetBoat != null
        }
    }

    override fun start() {
//        mob.target = targetBoat
        super.start()
    }

}