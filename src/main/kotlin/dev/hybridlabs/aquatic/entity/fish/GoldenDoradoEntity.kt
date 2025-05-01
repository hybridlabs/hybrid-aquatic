package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
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

class GoldenDoradoEntity(entityType: EntityType<out GoldenDoradoEntity>, world: World) :
    HybridAquaticFishEntity(
        entityType, world, emptyMap(),
        listOf(
            HybridAquaticEntityTags.SMALL_PREY,
            HybridAquaticEntityTags.MEDIUM_PREY,
            HybridAquaticEntityTags.CRUSTACEAN
        ),
        listOf(
            HybridAquaticEntityTags.SHARK
        )
    ), Angerable {

    private var angerTime = 0
    private var angryAt: UUID? = null

    override fun getLimitPerChunk(): Int {
        return 1
    }

    override fun initGoals() {
        super.initGoals()
        goalSelector.add(1, MeleeAttackGoal(this, 1.5, false))
        targetSelector.add(3, RevengeGoal(this))
        targetSelector.add(3, UniversalAngerGoal(this, true))
        targetSelector.add(1, ActiveTargetGoal(this, PlayerEntity::class.java, 10, true, true) { this.shouldAngerAt(it) })
    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.6)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 5.0)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 16.0)
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