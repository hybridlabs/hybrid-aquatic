package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.level.Level

class StonefishEntity(entityType: EntityType<out StonefishEntity>, world: Level) :
    HybridAquaticFishEntity(
        entityType, world,
        listOf(
            HybridAquaticEntityTags.SMALL_PREY
        ),
        listOf(
            HybridAquaticEntityTags.MEDIUM_PREY,
            HybridAquaticEntityTags.LARGE_PREY,
            HybridAquaticEntityTags.SHARK
        )
    ) {

    override fun getMaxSpawnClusterSize(): Int {
        return 2
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 3.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 8.0)
        }
    }

    override fun damage(source: DamageSource, amount: Float): Boolean {
        if (super.damage(source, amount)) {

            val attacker = source.attacker
            if (attacker is LivingEntity && attacker.mainHandStack.isEmpty) {
                attacker.addMobEffect(MobEffectInstance(MobEffects.POISON, 200, 2))
            }

            return true
        }

        return false
    }
}