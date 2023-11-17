package dev.hybridlabs.aquatic.entity.critter

import dev.hybridlabs.aquatic.entity.ai.goal.CrabDigGoal
import net.minecraft.entity.EntityGroup
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.world.World
import software.bernie.geckolib.core.animatable.GeoAnimatable
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.core.`object`.PlayState

class HermitCrabEntity(entityType: EntityType<out HybridAquaticCritterEntity>, world: World) :
    HybridAquaticCrabEntity(entityType, world) {

    private var isHiding: Boolean = false
    private var hidingTimer: Int = 0
    private var lastDamageTime: Long = 0

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 6.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0)
                .add(EntityAttributes.GENERIC_ARMOR, 5.0)
                .add(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, 5.0)
        }
    }

    override fun initGoals() {
        super.initGoals()
        goalSelector.add(3, CrabDigGoal(this, 0.05))
    }

    override fun getGroup(): EntityGroup? {
        return EntityGroup.ARTHROPOD
    }

    override fun getMaxSize(): Int {
        return 5
    }

    override fun getMinSize(): Int {
        return -5
    }

    private fun startHiding() {
        isHiding = true
        hidingTimer = 200
    }

    override fun tick() {
        super.tick()

        if (isHiding) {
            hidingTimer--

            if (hidingTimer <= 0 && (world.time - lastDamageTime) >= 200) {
                isHiding = false
                attributes.getCustomInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED)?.baseValue = 0.3
                attributes.getCustomInstance(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE)?.baseValue = 0.0
                attributes.getCustomInstance(EntityAttributes.GENERIC_ARMOR_TOUGHNESS)?.baseValue = 5.0
                attributes.getCustomInstance(EntityAttributes.GENERIC_ARMOR)?.baseValue = 5.0
            } else {
                attributes.getCustomInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED)?.baseValue = 0.0
                attributes.getCustomInstance(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE)?.baseValue = 100.0
                attributes.getCustomInstance(EntityAttributes.GENERIC_ARMOR_TOUGHNESS)?.baseValue = 50.0
                attributes.getCustomInstance(EntityAttributes.GENERIC_ARMOR)?.baseValue = 50.0
            }
        }
    }

    override fun <E : GeoAnimatable> predicate(event: AnimationState<E>): PlayState {
        if (isHiding) {
            event.controller.setAnimation(HIDING_ANIMATION)
            return PlayState.CONTINUE
        }

        return super.predicate(event)
    }

    override fun damage(source: net.minecraft.entity.damage.DamageSource?, amount: Float): Boolean {
        if (!isHiding) {
            startHiding()
        }

        lastDamageTime = world.time

        return super.damage(source, amount)
    }
}