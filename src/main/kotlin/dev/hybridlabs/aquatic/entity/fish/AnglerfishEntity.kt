package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.effect.HybridAquaticStatusEffects
import dev.hybridlabs.aquatic.entity.ai.goal.StayDeepGoal
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.ai.goal.ActiveTargetGoal
import net.minecraft.entity.ai.goal.MeleeAttackGoal
import net.minecraft.entity.ai.goal.RevengeGoal
import net.minecraft.entity.ai.goal.UniversalAngerGoal
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.Angerable
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.world.World
import java.util.*

class AnglerfishEntity(entityType: EntityType<out AnglerfishEntity>, world: World) :
    HybridAquaticFishEntity(
        entityType, world,
        listOf(
            HybridAquaticEntityTags.SMALL_PREY
        ),
        listOf(
            HybridAquaticEntityTags.MEDIUM_PREY,
            HybridAquaticEntityTags.LARGE_PREY,
            HybridAquaticEntityTags.SHARK
        )
    ), Angerable {

    private var angerTime = 0
    private var angryAt: UUID? = null

    override fun getLimitPerChunk(): Int {
        return 2
    }

    override fun initGoals() {
        super.initGoals()
        goalSelector.add(1, StayDeepGoal(this, 1.0, 1, 8))
        goalSelector.add(1, MeleeAttackGoal(this, 1.5, false))
        targetSelector.add(3, RevengeGoal(this))
        targetSelector.add(3, UniversalAngerGoal(this, true))
        targetSelector.add(1, ActiveTargetGoal(this, PlayerEntity::class.java, 10, true, true) { this.shouldAngerAt(it) })
        targetSelector.add(2, ActiveTargetGoal(this, LivingEntity::class.java, 10, true, true) { it.hasStatusEffect(HybridAquaticStatusEffects.BLEEDING) && it !is AnglerfishEntity
        })
    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 2.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.0)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 4.0)
        }
    }

    //#region Angerable Implementation Details
    override fun getAngerTime(): Int {
        return angerTime
    }

    override fun setAngerTime(angerTime: Int) {
        this.angerTime = angerTime
    }

    override fun getAngryAt(): UUID? {
        return angryAt
    }

    override fun setAngryAt(angryAt: UUID?) {
        this.angryAt = angryAt
    }

    override fun chooseRandomAngerTime() {
        setAngerTime(PiranhaEntity.ANGER_TIME_RANGE.get(random))
    }
    //#endregion
}