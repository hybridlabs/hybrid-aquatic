package dev.hybridlabs.aquatic.entity.miniboss

import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.control.LookControl
import net.minecraft.world.entity.ai.control.MoveControl
import net.minecraft.world.entity.ai.navigation.WallClimberNavigation
import net.minecraft.world.level.Level
import net.minecraft.world.level.pathfinder.BlockPathTypes

class KarcinogenEntity(entityType: EntityType<out HybridAquaticMinionEntity>, world: Level) :
    HybridAquaticMinionEntity(entityType, world) {
    init {
        setPathfindingMalus(BlockPathTypes.WATER, 0.0f)
        moveControl = MoveControl(this)
        navigation = WallClimberNavigation(this, world)
        lookControl = LookControl(this)
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return SoundEvents.TURTLE_EGG_CRACK
    }

    override fun getDeathSound(): SoundEvent {
        return SoundEvents.TURTLE_EGG_BREAK
    }

    override fun maxUpStep(): Float {
        return 1.0F
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