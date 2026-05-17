package dev.hybridlabs.aquatic.entity.miniboss

import dev.hybridlabs.aquatic.entity.ai.goal.MinionAttackGoal
import dev.hybridlabs.aquatic.entity.base.HAMinionEntity
import dev.hybridlabs.aquatic.sound.HASoundEvents
import net.minecraft.sounds.SoundEvent
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.control.LookControl
import net.minecraft.world.entity.ai.control.MoveControl
import net.minecraft.world.entity.ai.goal.LeapAtTargetGoal
import net.minecraft.world.entity.ai.goal.MoveTowardsTargetGoal
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation
import net.minecraft.world.level.Level
import net.minecraft.world.level.pathfinder.BlockPathTypes

class KarcinogenEntity(type: EntityType<out HAMinionEntity>, world: Level) :
    HAMinionEntity(type, world) {
    init {
        setPathfindingMalus(BlockPathTypes.WATER, 0.0f)
        moveControl = MoveControl(this)
        lookControl = LookControl(this)
        navigation = GroundPathNavigation(this, world)
    }

    override fun registerGoals() {
        goalSelector.addGoal(0, LeapAtTargetGoal(this, 0.4f))
        goalSelector.addGoal(0, MinionAttackGoal(this, 0.6, true))
        goalSelector.addGoal(1, MoveTowardsTargetGoal(this, 1.0, 16.0F))
        super.registerGoals()
    }

    //#region SFX
    override fun getAmbientSound(): SoundEvent {
        return HASoundEvents.KARCINOGEN_AMBIENT.get()
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return HASoundEvents.KARCINOGEN_HURT.get()
    }

    override fun getDeathSound(): SoundEvent {
        return HASoundEvents.KARCINOGEN_DIE.get()
    }
    //#endregion

    override fun maxUpStep(): Float {
        return 1.0F
    }

    override fun isAffectedByFluids(): Boolean {
        return false
    }

    override fun tick() {
        super.tick()

        if (isInWater && !onGround()) {
            isSwimming = false
            this.deltaMovement = deltaMovement.subtract(0.0, 0.1, 0.0)
            navigation.stop()
        }
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 6.0)
                .add(Attributes.MOVEMENT_SPEED, 0.6)
                .add(Attributes.ATTACK_DAMAGE, 4.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 16.0)
        }
    }
}