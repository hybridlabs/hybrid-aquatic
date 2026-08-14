package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.hapi.entity.ai.MobTargetConfiguration
import dev.hybridlabs.hapi.entity.ai.goal.WaterAnimalEatItemGoal
import dev.hybridlabs.hapi.entity.ai.goal.boids.BoidGoal
import dev.hybridlabs.hapi.entity.ai.goal.boids.StayInWaterGoal
import dev.hybridlabs.hapi.entity.base.aquatic.BaseSchoolingFishEntity
import dev.hybridlabs.aquatic.item.HAItems
import dev.hybridlabs.hapi.tag.HAPIEntityTags
import dev.hybridlabs.aquatic.tag.HAItemTags
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level

class NeedlefishEntity(type: EntityType<out NeedlefishEntity>, world: Level) :
    BaseSchoolingFishEntity(type, world) {

    override fun getTargetConfig() = TARGET_CONFIG

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(2, WaterAnimalEatItemGoal(this))
        goalSelector.addGoal(5, BoidGoal(this, 0.25f, 0.5f, 8 / 20f, 1 / 20f))
        goalSelector.addGoal(3, StayInWaterGoal(this))
    }

    override fun isFood(stack: ItemStack): Boolean {
        return stack.`is`(HAItems.RAW_TENTACLE.get()) ||
                stack.`is`(HAItemTags.SMALL_FISH)
    }

    override fun getMaxSpawnClusterSize(): Int {
        return 4
    }

    companion object {
        private val TARGET_CONFIG = MobTargetConfiguration.create(
            listOf(
                HAPIEntityTags.SMALL_CREATURES,
                HAPIEntityTags.ALL_CEPHALOPODS
            ),
            listOf(
                HAPIEntityTags.LARGE_CREATURES,
                HAPIEntityTags.ALL_SHARKS
            ),
        )

        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 6.0)
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.ATTACK_DAMAGE, 3.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 8.0)
        }
    }
}
