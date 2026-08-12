package dev.hybridlabs.aquatic.entity.shark

import dev.hybridlabs.aquatic.item.HAItems
import dev.hybridlabs.hapi.tag.HAPIEntityTags
import dev.hybridlabs.hapi.entity.ai.MobTargetConfiguration
import dev.hybridlabs.hapi.entity.water.base.BaseSharkEntity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level

class SandTigerSharkEntity(type: EntityType<out SandTigerSharkEntity>, world: Level) :
    BaseSharkEntity(type, world) {

    override fun getTargetConfig() = MobTargetConfiguration.ofPredator(
        HAPIEntityTags.CRAB,
        HAPIEntityTags.LOBSTER,
        HAPIEntityTags.RAY,
        HAPIEntityTags.SMALL_CREATURES,
        HAPIEntityTags.OCTOPUS,
    )

    override val isPassive: Boolean = false
    override val closePlayerAttack: Boolean = false

    override fun isFood(stack: ItemStack): Boolean {
        return stack.`is`(HAItems.SURGEONFISH.get())
    }

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(1, HurtByTargetGoal(this))
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 30.0)
                .add(Attributes.MOVEMENT_SPEED, 0.75)
                .add(Attributes.ATTACK_DAMAGE, 8.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 16.0)
        }
    }
}
