package dev.hybridlabs.aquatic.entity.shark

import dev.hybridlabs.aquatic.entity.ai.goal.SharkJumpGoal
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.ai.goal.ChaseBoatGoal
import net.minecraft.entity.ai.goal.RevengeGoal
import net.minecraft.entity.attribute.AttributeSupplier
import net.minecraft.entity.attribute.Attributes
import net.minecraft.world.World

class GreatWhiteSharkEntity(entityType: EntityType<out GreatWhiteSharkEntity>, world: Level) :
    HybridAquaticSharkEntity(entityType, world, listOf(HybridAquaticEntityTags.LARGE_PREY), false, true) {

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(1, RevengeGoal(this))
        goalSelector.addGoal(8, ChaseBoatGoal(this))
        goalSelector.addGoal(5, SharkJumpGoal(this, 10))
    }

    override fun getSpawnClusterSize(): Int {
        return 1
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 54.0)
                .add(Attributes.MOVEMENT_SPEED, 1.5)
                .add(Attributes.ATTACK_DAMAGE, 8.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 32.0)
        }
    }

    override fun getMaxSize(): Int {
        return 3
    }

    override fun getMinSize(): Int {
        return -3
    }
}