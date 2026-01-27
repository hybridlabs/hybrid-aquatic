package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.effect.HybridAquaticMobEffects
import dev.hybridlabs.aquatic.entity.ai.MobTargetConfiguration
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.NeutralMob
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
import net.minecraft.world.entity.ai.goal.target.ResetUniversalAngerTargetGoal
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import java.util.UUID

class FangtoothEntity(entityType: EntityType<out FangtoothEntity>, world: Level) :
    HybridAquaticFishEntity(entityType, world), NeutralMob {

    override val targetConfig = TARGET_CONFIG

    private var angerTime = 0
    private var angryAt: UUID? = null

    override fun getMaxSpawnClusterSize(): Int {
        return 1
    }

    override fun registerGoals() {
        super.registerGoals()
        targetSelector.addGoal(1, HurtByTargetGoal(this))
        targetSelector.addGoal(3, ResetUniversalAngerTargetGoal(this, false))
        targetSelector.addGoal(1, NearestAttackableTargetGoal(this, Player::class.java, 10, true, true) { this.isAngryAt(it) })
        targetSelector.addGoal(1, NearestAttackableTargetGoal(this, LivingEntity::class.java, 10, true, true) { it.hasEffect(HybridAquaticMobEffects.BLEEDING.get()) && it !is FangtoothEntity })
    }

    companion object {
        private val TARGET_CONFIG = MobTargetConfiguration.create(
            listOf(
                HybridAquaticEntityTags.SMALL_PREY
            ),
            listOf(
                HybridAquaticEntityTags.MEDIUM_PREY,
                HybridAquaticEntityTags.LARGE_PREY,
                HybridAquaticEntityTags.SHARK
            ),
        )

        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 2.0)
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 4.0)
        }
    }

    //#region Angerable Implementation Details
    override fun getRemainingPersistentAngerTime(): Int {
        return angerTime
    }

    override fun setRemainingPersistentAngerTime(angerTime: Int) {
        this.angerTime = angerTime
    }

    override fun getPersistentAngerTarget(): UUID? {
        return angryAt
    }

    override fun setPersistentAngerTarget(angryAt: UUID?) {
        this.angryAt = angryAt
    }

    override fun startPersistentAngerTimer() {
        this.remainingPersistentAngerTime = PiranhaEntity.ANGER_TIME_RANGE.sample(this.random)
    }
    //#endregion
}
