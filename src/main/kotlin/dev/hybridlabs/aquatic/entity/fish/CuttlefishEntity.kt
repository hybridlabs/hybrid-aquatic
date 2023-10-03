package dev.hybridlabs.aquatic.entity.fish

import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.particle.ParticleTypes
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.math.Vec3d
import net.minecraft.world.World

class CuttlefishEntity(entityType: EntityType<out CuttlefishEntity>, world: World) :
    HybridAquaticFishEntity(entityType, world) {

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 12.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1.0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 20.0)
        }
    }

    override fun shouldFlopOnLand(): Boolean {
        return false
    }

    private var attackCooldown: Int = 0
    private var escapeDirection: Vec3d = Vec3d.ZERO

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return SoundEvents.ENTITY_SQUID_HURT
    }

    override fun getDeathSound(): SoundEvent {
        return SoundEvents.ENTITY_SQUID_DEATH
    }

    override fun getAmbientSound(): SoundEvent {
        return SoundEvents.ENTITY_SQUID_AMBIENT
    }

    override fun getSplashSound(): SoundEvent {
        return SoundEvents.ENTITY_DOLPHIN_SPLASH
    }

    override fun getSwimSound(): SoundEvent {
        return SoundEvents.ENTITY_SQUID_AMBIENT
    }

    override fun damage(source: DamageSource, amount: Float): Boolean {
        if (world is ServerWorld) {
            val particleCount = 8
            val particleOffset = 0.5
            val particleVelocityX = -escapeDirection.x * 0.05
            val particleVelocityY = -escapeDirection.y * 0.05
            val particleVelocityZ = -escapeDirection.z * 0.05
            val particleData = ParticleTypes.SQUID_INK
            (world as ServerWorld).spawnParticles(
                particleData,
                x + particleOffset,
                eyeY,
                z + particleOffset,
                particleCount,
                particleVelocityX,
                particleVelocityY,
                particleVelocityZ,
                0.0
            )
            attackCooldown = 40
        }

        return super.damage(source, amount)
    }

    override fun getAttacker(): LivingEntity? {
        val target = attackingPlayer
        if (target != null) {
            return target
        }
        return null
    }

    override fun getMaxSize(): Int {
        return 5
    }

    override fun getMinSize(): Int {
        return -5
    }
}