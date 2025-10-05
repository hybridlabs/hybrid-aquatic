package dev.hybridlabs.aquatic.entity.shark

import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.FollowBoatGoal
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
import net.minecraft.world.level.Level

class BullSharkEntity(entityType: EntityType<out BullSharkEntity>, world: Level) :
    HybridAquaticSharkEntity(
        entityType,
        world,
        listOf(HybridAquaticEntityTags.LARGE_PREY, HybridAquaticEntityTags.MEDIUM_PREY),
        false,
        true
    ) {

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(1, HurtByTargetGoal(this))
        goalSelector.addGoal(8, FollowBoatGoal(this))
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 54.0)
                .add(Attributes.MOVEMENT_SPEED, 0.75)
                .add(Attributes.ATTACK_DAMAGE, 8.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 32.0)
        }
    }
}
