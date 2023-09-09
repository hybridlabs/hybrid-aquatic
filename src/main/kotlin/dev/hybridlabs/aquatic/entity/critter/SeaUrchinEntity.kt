package dev.hybridlabs.aquatic.entity.critter

import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.damage.DamageTypes
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.registry.tag.DamageTypeTags
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.world.World

class SeaUrchinEntity(entityType: EntityType<out SeaUrchinEntity>, world: World) : HybridAquaticCritterEntity(entityType, world, 4) {
    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 3.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.2)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 20.0)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 100.0)

        }
    }
    override fun getHurtSound(source: DamageSource): SoundEvent {
        return SoundEvents.ENTITY_SLIME_HURT_SMALL
    }

    override fun getDeathSound(): SoundEvent {
        return SoundEvents.ENTITY_SLIME_DEATH_SMALL
    }

    override fun getAmbientSound(): SoundEvent {
        return SoundEvents.ENTITY_COD_AMBIENT
    }

    override fun getSplashSound(): SoundEvent {
        return SoundEvents.ENTITY_DOLPHIN_SPLASH
    }

    override fun getSwimSound(): SoundEvent {
        return SoundEvents.ENTITY_SLIME_SQUISH_SMALL
    }
    override fun damage(source: DamageSource, amount: Float): Boolean {
        return if (world.isClient) {
            false
        } else {
            if (!source.isIn(DamageTypeTags.AVOIDS_GUARDIAN_THORNS) && !source.isOf(
                    DamageTypes.THORNS
                )
            ) {
                val var4 = source.source
                if (var4 is LivingEntity) {
                    var4.damage(this.damageSources.thorns(this), 2.0f)
                }
            }
            super.damage(source, amount)
        }
    }
    override fun tick() {
        super.tick()
        if (!isWet) {
            this.speed = 0.01F
        }
    }
    override fun getMaxSize() : Int {
        return 5
    }

    override fun getMinSize(): Int {
        return -5
    }
}