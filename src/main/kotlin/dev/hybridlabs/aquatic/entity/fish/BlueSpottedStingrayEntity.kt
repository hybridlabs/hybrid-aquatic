package dev.hybridlabs.aquatic.entity.fish

import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.mob.MobEntity
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.sound.SoundEvents
import net.minecraft.world.World

class BlueSpottedStingrayEntity(entityType: EntityType<out BlueSpottedStingrayEntity>, world: World) : HybridAquaticFishEntity(entityType, world) {
    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 12.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1.25)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 20.0)

        }
    }
    private fun sting(mob: MobEntity) {
        val i = 0
        if (mob.damage(this.damageSources.mobAttack(this), (1 + i).toFloat())) {
            mob.addStatusEffect(StatusEffectInstance(StatusEffects.POISON, 60 * i, 0), this)
            playSound(SoundEvents.ENTITY_PUFFER_FISH_STING, 1.0f, 1.0f)
        }
    }
}