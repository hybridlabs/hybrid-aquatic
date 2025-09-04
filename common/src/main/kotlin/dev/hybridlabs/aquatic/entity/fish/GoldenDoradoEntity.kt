package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
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

class GoldenDoradoEntity(entityType: EntityType<out GoldenDoradoEntity>, world: Level) :
    HybridAquaticFishEntity(
        entityType, world,
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

    override fun getSpawnClusterSize(): Int {
        return 1
    }

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(1, MeleeAttackGoal(this, 1.5, false))
        targetSelector.addGoal(3, RevengeGoal(this))
        targetSelector.addGoal(3, UniversalAngerGoal(this, true))
        targetSelector.addGoal(1, NearestAttackableTargetGoal(this,Player::class.java, 10, true, true) { this.shouldAngerAt(it) })
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 10.0)
                .add(Attributes.MOVEMENT_SPEED, 0.6)
                .add(Attributes.ATTACK_DAMAGE, 5.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 16.0)
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