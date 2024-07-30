package dev.hybridlabs.aquatic.entity.cephalopod

import dev.hybridlabs.aquatic.entity.ai.goal.StayDeepGoal
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.world.World

class NautilusEntity(entityType: EntityType<out NautilusEntity>, world: World) :
    HybridAquaticCephalopodEntity(entityType, world, emptyMap(), HybridAquaticEntityTags.NONE, HybridAquaticEntityTags.NAUTILUS_PREDATOR, false) {

    override fun initGoals() {
        super.initGoals()
        goalSelector.add(0, StayDeepGoal(this))
    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 12.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.6)
        }
    }

    override fun getMaxSize(): Int {
        return 5
    }

    override fun getMinSize(): Int {
        return -5
    }
}