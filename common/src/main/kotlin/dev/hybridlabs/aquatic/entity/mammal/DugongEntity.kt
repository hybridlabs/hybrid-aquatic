package dev.hybridlabs.aquatic.entity.mammal

import dev.hybridlabs.aquatic.entity.HAEntityTypes
import dev.hybridlabs.aquatic.entity.ai.goal.WaterAnimalBreedGoal
import dev.hybridlabs.aquatic.sound.HASoundEvents
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvent
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.AgeableMob
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.level.Level

@Suppress("DEPRECATION")
class DugongEntity(type: EntityType<out DugongEntity>, world: Level) : HASirenianEntity(type, world) {

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(2, WaterAnimalBreedGoal(this, 1.1))
    }

    override fun getBreedOffspring(p0: ServerLevel, p1: AgeableMob): DugongEntity? {
        return HAEntityTypes.DUGONG.get().create(p0)
    }

    //#region SFX
    override fun getAmbientSound(): SoundEvent {
        return HASoundEvents.DUGONG_AMBIENT.get()
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return HASoundEvents.DUGONG_HURT.get()
    }

    override fun getDeathSound(): SoundEvent {
        return HASoundEvents.DUGONG_DIE.get()
    }

    override fun getSwimSplashSound(): SoundEvent {
        return HASoundEvents.DUGONG_SPLASH.get()
    }

    override fun getSwimSound(): SoundEvent {
        return HASoundEvents.DUGONG_SWIM.get()
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
