package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.entity.goal.FishJumpGoal
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
import net.minecraft.world.level.Level

class MantaRayEntity(entityType: EntityType<out MantaRayEntity>, world: Level) :
    HybridAquaticFishEntity(
        entityType, world, emptyMap(),
        listOf(HybridAquaticEntityTags.NONE), listOf(HybridAquaticEntityTags.NONE)
    ) {

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(5, FishJumpGoal(this, 10))
        goalSelector.addGoal(1, HurtByTargetGoal(this))
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.ATTACK_DAMAGE, 5.0)
                .add(Attributes.FOLLOW_RANGE, 16.0)
        }
    }
}