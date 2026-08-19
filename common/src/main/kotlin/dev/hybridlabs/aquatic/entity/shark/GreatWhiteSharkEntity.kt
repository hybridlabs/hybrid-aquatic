package dev.hybridlabs.aquatic.entity.shark

import dev.hybridlabs.hapi.entity.ai.MobTargetConfiguration
import dev.hybridlabs.hapi.entity.ai.goal.aquatic.WaterAnimalJumpGoal
import dev.hybridlabs.hapi.entity.base.aquatic.BaseSharkEntity
import dev.hybridlabs.hapi.tag.HAPIEntityTags
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.FollowBoatGoal
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
import net.minecraft.world.level.Level

class GreatWhiteSharkEntity(type: EntityType<out GreatWhiteSharkEntity>, world: Level) :
    BaseSharkEntity(type, world) {

    override fun getTargetConfig() = MobTargetConfiguration.ofPredator(
        HAPIEntityTags.MEDIUM_CREATURES,
        HAPIEntityTags.LARGE_CREATURES,
        HAPIEntityTags.PLAYERS,
        HAPIEntityTags.SMALL_SHARK,
        HAPIEntityTags.MEDIUM_SHARK,
        HAPIEntityTags.SEAL,
        HAPIEntityTags.TURTLE,
        HAPIEntityTags.SIRENIAN,
    )

    override val isPassive: Boolean = false
    override val closePlayerAttack: Boolean = true

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(1, HurtByTargetGoal(this))
        goalSelector.addGoal(8, FollowBoatGoal(this))
        goalSelector.addGoal(5, WaterAnimalJumpGoal(this, 10, 5.0))
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 30.0)
                .add(Attributes.MOVEMENT_SPEED, 0.75)
                .add(Attributes.ATTACK_DAMAGE, 8.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 32.0)
        }
    }
}
