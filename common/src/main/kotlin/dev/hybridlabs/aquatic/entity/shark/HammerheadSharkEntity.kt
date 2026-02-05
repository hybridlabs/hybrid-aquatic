package dev.hybridlabs.aquatic.entity.shark

import dev.hybridlabs.aquatic.entity.ai.MobTargetConfiguration
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
import net.minecraft.world.level.Level

class HammerheadSharkEntity(type: EntityType<out HammerheadSharkEntity>, world: Level) : HybridAquaticSharkEntity(type, world) {
    override fun getTargetConfig() = TARGET_CONFIG

    override val isPassive: Boolean = false
    override val closePlayerAttack: Boolean = false

    override fun getMaxSpawnClusterSize(): Int {
        return 2
    }

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(1, HurtByTargetGoal(this))
    }

    companion object {
        private val TARGET_CONFIG = MobTargetConfiguration.create(
            listOf(
                HybridAquaticEntityTags.CRAB,
                HybridAquaticEntityTags.LOBSTER,
                HybridAquaticEntityTags.SMALL_CREATURES,
                HybridAquaticEntityTags.SMALL_SHARK,
                HybridAquaticEntityTags.RAY,
            ),
            listOf(
                HybridAquaticEntityTags.LARGE_SHARK
            ),
        )

        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 45.0)
                .add(Attributes.MOVEMENT_SPEED, 0.75)
                .add(Attributes.ATTACK_DAMAGE, 4.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 26.0)
        }
    }
}
