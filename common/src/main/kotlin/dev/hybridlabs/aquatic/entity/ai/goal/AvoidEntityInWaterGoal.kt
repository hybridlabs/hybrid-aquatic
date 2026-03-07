package dev.hybridlabs.aquatic.entity.ai.goal

import net.minecraft.world.entity.EntitySelector
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.PathfinderMob
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal
import net.minecraft.world.entity.ai.targeting.TargetingConditions
import net.minecraft.world.entity.ai.util.DefaultRandomPos
import java.util.function.Predicate

class AvoidEntityInWaterGoal<T: LivingEntity> (
    mob: PathfinderMob,
    entityClassToAvoid: Class<T>,
    avoidPredicate: Predicate<LivingEntity>,
    var maxDistance: Float,
    walkSpeedModifier: Double,
    sprintSpeedModifier: Double,
    predicateOnAvoidEntity: Predicate<LivingEntity>) :
    AvoidEntityGoal<T>(mob, entityClassToAvoid, avoidPredicate, maxDistance, walkSpeedModifier, sprintSpeedModifier, predicateOnAvoidEntity) {

    constructor(
        mob: PathfinderMob,
        entityClassToAvoid: Class<T>,
        maxDistance: Float,
        walkSpeedModifier: Double,
        sprintSpeedModifier: Double) :
            this(mob, entityClassToAvoid, { true }, maxDistance, walkSpeedModifier, sprintSpeedModifier, Predicate { t: LivingEntity? -> EntitySelector.NO_CREATIVE_OR_SPECTATOR.test(t) })

    constructor(
        mob: PathfinderMob,
        entityClassToAvoid: Class<T>,
        maxDistance: Float,
        walkSpeedModifier: Double,
        sprintSpeedModifier: Double,
        predicateOnAvoidEntity: Predicate<LivingEntity>
    ) :
        this(mob, entityClassToAvoid, { true }, maxDistance, walkSpeedModifier, sprintSpeedModifier, predicateOnAvoidEntity)

    override fun canUse(): Boolean {
        toAvoid = mob.level().getNearestEntity(
            mob.level().getEntitiesOfClass(avoidClass, mob.boundingBox.inflate(this.maxDist.toDouble(), 7.0, this.maxDist.toDouble()), { true }),
            TargetingConditions.forCombat().range(maxDistance.toDouble()).selector(predicateOnAvoidEntity.and(avoidPredicate)),
            mob, mob.x, mob.y, mob.z
        )

        if (this.toAvoid == null) return false

        val blockPosAway = DefaultRandomPos.getPosAway(this.mob, 16, 7, this.toAvoid!!.position()) ?: return false
        if (this.toAvoid!!.distanceToSqr(blockPosAway.x, blockPosAway.y, blockPosAway.z) < this.toAvoid!!.distanceToSqr(this.mob)) return false

        this.path = this.pathNav.createPath(blockPosAway.x, blockPosAway.y, blockPosAway.z, 0)
        return this.path != null
    }
}