package dev.hybridlabs.aquatic.entity.shark

import dev.hybridlabs.aquatic.entity.ai.MobTargetConfiguration
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.FollowBoatGoal
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
import net.minecraft.world.level.Level

class SixgillSharkEntity(type: EntityType<out SixgillSharkEntity>, world: Level) :
    HybridAquaticSharkEntity(type, world) {

    override fun getTargetConfig() = MobTargetConfiguration.ofPredator(
        HybridAquaticEntityTags.MEDIUM_CREATURES,
        HybridAquaticEntityTags.SMALL_SHARK,
        HybridAquaticEntityTags.SQUID,
    )

    override val isPassive: Boolean = false
    override val closePlayerAttack: Boolean = false

    override fun getMaxSpawnClusterSize(): Int {
        return 1
    }

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(1, HurtByTargetGoal(this))
        goalSelector.addGoal(8, FollowBoatGoal(this))
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 54.0)
                .add(Attributes.MOVEMENT_SPEED, 0.7)
                .add(Attributes.ATTACK_DAMAGE, 8.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 32.0)
        }
    }
}
