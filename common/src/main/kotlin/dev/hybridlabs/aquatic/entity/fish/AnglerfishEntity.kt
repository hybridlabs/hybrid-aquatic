package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.effect.HybridAquaticMobEffects
import dev.hybridlabs.aquatic.entity.ai.goal.StayDeepGoal
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.ai.goal.NearestAttackableTargetGoal
import net.minecraft.entity.ai.goal.MeleeAttackGoal
import net.minecraft.entity.ai.goal.RevengeGoal
import net.minecraft.entity.ai.goal.UniversalAngerGoal
import net.minecraft.entity.attribute.AttributeSupplier
import net.minecraft.entity.attribute.Attributes
import net.minecraft.entity.mob.Angerable
import net.minecraft.entity.player.Player
import net.minecraft.world.World
import java.util.*

class AnglerfishEntity(entityType: EntityType<out AnglerfishEntity>, world: Level) :
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

    override fun getSpawnClusterSize(): Int {
        return 2
    }

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(1, StayDeepGoal(this, 1.0, 1, 8))
        goalSelector.addGoal(1, MeleeAttackGoal(this, 1.5, false))
        targetSelector.addGoal(3, RevengeGoal(this))
        targetSelector.addGoal(3, UniversalAngerGoal(this, true))
        targetSelector.addGoal(1, NearestAttackableTargetGoal(this,Player::class.java, 10, true, true) { this.shouldAngerAt(it) })
        targetSelector.addGoal(2, NearestAttackableTargetGoal(this, LivingEntity::class.java, 10, true, true) { it.hasMobEffect(HybridAquaticMobEffects.BLEEDING) && it !is AnglerfishEntity
        })
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 2.0)
                .add(Attributes.MOVEMENT_SPEED, 0.25)
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 4.0)
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