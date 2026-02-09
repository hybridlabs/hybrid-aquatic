package dev.hybridlabs.aquatic.entity.mammal

import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.AgeableMob
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.BreedGoal
import net.minecraft.world.level.Level

@Suppress("DEPRECATION")
class DugongEntity(type: EntityType<out DugongEntity>, world: Level) : HybridAquaticSirenianEntity(type, world) {

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(2, BreedGoal(this, 1.1))
    }

    override fun getBreedOffspring(p0: ServerLevel, p1: AgeableMob): DugongEntity? {
        return HybridAquaticEntityTypes.DUGONG.get().create(p0)
    }

    //#region SFX
    override fun getAmbientSound(): SoundEvent {
        return SoundEvents.COW_AMBIENT
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return SoundEvents.COW_HURT
    }

    override fun getDeathSound(): SoundEvent {
        return SoundEvents.COW_DEATH
    }

    override fun getSwimSplashSound(): SoundEvent {
        return SoundEvents.DOLPHIN_SPLASH
    }

    override fun getSwimSound(): SoundEvent {
        return SoundEvents.DOLPHIN_SWIM
    }
    //#endregion

    companion object {

        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 32.0)
                .add(Attributes.MOVEMENT_SPEED, 0.6)
                .add(Attributes.ATTACK_DAMAGE, 6.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 12.0)
        }
    }
}
