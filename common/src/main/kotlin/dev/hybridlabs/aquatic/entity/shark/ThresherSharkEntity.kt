package dev.hybridlabs.aquatic.entity.shark

import dev.hybridlabs.aquatic.entity.ai.goal.HybridAquaticJumpGoal
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
import net.minecraft.world.level.Level

class ThresherSharkEntity(entityType: EntityType<out ThresherSharkEntity>, world: Level) :
    HybridAquaticSharkEntity(
        entityType,
        world,
        listOf(HybridAquaticEntityTags.SMALL_PREY, HybridAquaticEntityTags.MEDIUM_PREY),
        false,
        false
    ) {

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(1, HurtByTargetGoal(this))
        goalSelector.addGoal(5, HybridAquaticJumpGoal(this, 10))
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 36.0)
                .add(Attributes.MOVEMENT_SPEED, 0.75)
                .add(Attributes.ATTACK_DAMAGE, 4.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 16.0)
        }
    }
}
