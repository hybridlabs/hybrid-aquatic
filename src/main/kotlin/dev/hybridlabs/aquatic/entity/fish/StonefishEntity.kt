package dev.hybridlabs.aquatic.entity.fish

import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.server.network.ServerPlayerEntity
import net.minecraft.world.World

class StonefishEntity(entityType: EntityType<out StonefishEntity>, world: World) : HybridAquaticFishEntity(entityType, world) {
    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1.1)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 20.0)

        }
    }
    override fun onPlayerCollision(player: PlayerEntity) {
        val i = 1
        if (player is ServerPlayerEntity && player.damage(
                this.damageSources.mobAttack(this),
                (1 + i).toFloat()
            )
        )
            player.addStatusEffect(StatusEffectInstance(StatusEffects.POISON, 20, 1), this)
    }
}