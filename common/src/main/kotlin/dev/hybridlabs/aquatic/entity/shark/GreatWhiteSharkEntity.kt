package dev.hybridlabs.aquatic.entity.shark

import dev.hybridlabs.aquatic.entity.goal.SharkJumpGoal
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
import net.minecraft.world.level.Level

class GreatWhiteSharkEntity(entityType: EntityType<out GreatWhiteSharkEntity>, world: Level) :
    HybridAquaticSharkEntity(entityType, world, listOf(HybridAquaticEntityTags.LARGE_PREY), false, true) {

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(1, HurtByTargetGoal(this))
        goalSelector.addGoal(5, SharkJumpGoal(this, 10))
    }

    override fun getMaxSpawnClusterSize(): Int {
        return 1
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 54.0)
                .add(Attributes.MOVEMENT_SPEED, 1.0)
                .add(Attributes.ATTACK_DAMAGE, 8.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 32.0)
        }
    }

    override fun getMaxSize(): Int {
        return 5
    }

    override fun getMinSize(): Int {
        return 0
    }
}
