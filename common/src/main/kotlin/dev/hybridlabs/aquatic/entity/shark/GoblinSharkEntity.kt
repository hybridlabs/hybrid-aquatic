package dev.hybridlabs.aquatic.entity.shark

import dev.hybridlabs.aquatic.entity.ai.MobTargetConfiguration
import dev.hybridlabs.aquatic.item.HAItems
import dev.hybridlabs.aquatic.tag.HAEntityTags
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level

class GoblinSharkEntity(type: EntityType<out GoblinSharkEntity>, world: Level) :
    HASharkEntity(type, world) {

    override fun getTargetConfig() = MobTargetConfiguration.ofPredator(
        HAEntityTags.CRAB,
        HAEntityTags.LOBSTER,
        HAEntityTags.RAY,
        HAEntityTags.SMALL_CREATURES,
        HAEntityTags.OCTOPUS,
    )

    override val isPassive: Boolean = false
    override val closePlayerAttack: Boolean = false

    override fun isFood(stack: ItemStack): Boolean {
        return stack.`is`(HAItems.FLASHLIGHT_FISH.get())
    }

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(1, HurtByTargetGoal(this))
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 54.0)
                .add(Attributes.MOVEMENT_SPEED, 0.6)
                .add(Attributes.ATTACK_DAMAGE, 8.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 16.0)
        }
    }
}
