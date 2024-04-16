package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.world.World

class MahiMahiEntity(entityType: EntityType<out MahiMahiEntity>, world: World) :
    HybridAquaticSchoolingFishEntity(entityType, world, HybridAquaticEntityTags.MAHI_PREY, HybridAquaticEntityTags.MAHI_PREDATOR) {
    override fun initGoals() {
        super.initGoals()
        goalSelector.add(5, FishJumpGoal(this, 10))
    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 8.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.8)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 12.0)
        }
    }
    override fun speedModifier(): Double {
        return 0.005
    }
}