package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.damage.DamageTypes
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.registry.tag.DamageTypeTags
import net.minecraft.server.world.ServerWorld
import net.minecraft.world.World

class StonefishEntity(entityType: EntityType<out StonefishEntity>, world: World) :
    HybridAquaticFishEntity(entityType, world, emptyMap(), HybridAquaticEntityTags.NONE, HybridAquaticEntityTags.NONE) {

    override fun getLimitPerChunk(): Int {
        return 2
    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 4.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.6)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 12.0)
        }
    }

    override fun damage(world: ServerWorld, source: DamageSource, amount: Float): Boolean {
        if (!source.isIn(DamageTypeTags.AVOIDS_GUARDIAN_THORNS) && !source.isOf(DamageTypes.THORNS)) {
            val attacker = source.source
            if (attacker is LivingEntity) {
                attacker.addStatusEffect(StatusEffectInstance(StatusEffects.POISON, 400, 2), this)
            }
        }

        return super.damage(source, amount)
    }
}
