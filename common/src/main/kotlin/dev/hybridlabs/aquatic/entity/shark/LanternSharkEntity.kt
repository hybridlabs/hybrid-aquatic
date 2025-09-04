package dev.hybridlabs.aquatic.entity.shark

import dev.hybridlabs.aquatic.entity.ai.goal.StayDeepGoal
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.AttributeSupplier
import net.minecraft.entity.attribute.Attributes
import net.minecraft.world.World

class LanternSharkEntity(entityType: EntityType<out LanternSharkEntity>, world: Level) :
    HybridAquaticSharkEntity(entityType, world, listOf(HybridAquaticEntityTags.SMALL_PREY), false, false) {

    override fun getSpawnClusterSize(): Int {
        return 2
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 8.0)
                .add(Attributes.MOVEMENT_SPEED, 0.4)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 8.0)
        }
    }

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(1, StayDeepGoal(this, 1.0, 1, 16))
    }

    override fun getMaxSize() : Int {
        return 3
    }

    override fun getMinSize(): Int {
        return -3
    }
}
