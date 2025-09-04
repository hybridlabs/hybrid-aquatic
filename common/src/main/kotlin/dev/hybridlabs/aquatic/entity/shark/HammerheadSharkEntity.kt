package dev.hybridlabs.aquatic.entity.shark

import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.ai.goal.RevengeGoal
import net.minecraft.entity.attribute.AttributeSupplier
import net.minecraft.entity.attribute.Attributes
import net.minecraft.world.World

class HammerheadSharkEntity(entityType: EntityType<out HammerheadSharkEntity>, world: Level) :
    HybridAquaticSharkEntity(
        entityType,
        world,
        listOf(HybridAquaticEntityTags.CRUSTACEAN, HybridAquaticEntityTags.SMALL_PREY),
        false,
        false
    ) {

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(1, RevengeGoal(this))
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 45.0)
                .add(Attributes.MOVEMENT_SPEED, 0.6)
                .add(Attributes.ATTACK_DAMAGE, 4.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 26.0)
        }
    }
}