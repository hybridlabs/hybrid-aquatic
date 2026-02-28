package dev.hybridlabs.aquatic.entity.base

import net.minecraft.core.particles.ParticleTypes
import net.minecraft.nbt.CompoundTag
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.AgeableMob
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ExperienceOrb
import net.minecraft.world.entity.MobType
import net.minecraft.world.entity.VariantHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.GameRules
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.pathfinder.BlockPathTypes
import java.util.*

abstract class HybridAquaticWaterAnimal protected constructor(
    entityType: EntityType<out HybridAquaticWaterAnimal>,
    level: Level,
) :
    AgeableMob(entityType, level) {
    private var inLove = 0
    private var loveCause: UUID? = null

    init {
        this.setPathfindingMalus(BlockPathTypes.WATER, 0.0f)
    }

    override fun customServerAiStep() {
        if (this.getAge() != 0) {
            this.inLove = 0
        }

        super.customServerAiStep()
    }

    override fun aiStep() {
        super.aiStep()
        if (this.getAge() != 0) {
            this.inLove = 0
        }

        if (this.inLove > 0) {
            --this.inLove
            if (this.inLove % 10 == 0) {
                val d0 = this.random.nextGaussian() * 0.02
                val d1 = this.random.nextGaussian() * 0.02
                val d2 = this.random.nextGaussian() * 0.02
                this.level().addParticle(
                    ParticleTypes.HEART,
                    this.getRandomX(1.0),
                    this.randomY + 0.5,
                    this.getRandomZ(1.0),
                    d0,
                    d1,
                    d2
                )
            }
        }
    }

    override fun hurt(source: DamageSource, amount: Float): Boolean {
        if (this.isInvulnerableTo(source)) {
            return false
        } else {
            this.inLove = 0
            return super.hurt(source, amount)
        }
    }

    override fun addAdditionalSaveData(compound: CompoundTag) {
        super.addAdditionalSaveData(compound)
        compound.putInt("InLove", this.inLove)
        if (this.loveCause != null) {
            compound.putUUID("LoveCause", this.loveCause)
        }
    }

    override fun readAdditionalSaveData(compound: CompoundTag) {
        super.readAdditionalSaveData(compound)
        this.inLove = compound.getInt("InLove")
        this.loveCause = if (compound.hasUUID("LoveCause")) compound.getUUID("LoveCause") else null
    }

    open fun isFood(stack: ItemStack): Boolean {
        return stack.`is`(Items.WHEAT)
    }

    public override fun mobInteract(player: Player, hand: InteractionHand): InteractionResult {
        val itemstack = player.getItemInHand(hand)
        if (this.isFood(itemstack)) {
            val i = this.getAge()
            if (!this.level().isClientSide && i == 0 && this.canFallInLove()) {
                this.usePlayerItem(player, hand, itemstack)
                this.setInLove(player)
                return InteractionResult.SUCCESS
            }

            if (this.isBaby) {
                this.usePlayerItem(player, hand, itemstack)
                this.ageUp(getSpeedUpSecondsWhenFeeding(-i), true)
                return InteractionResult.sidedSuccess(this.level().isClientSide)
            }

            if (this.level().isClientSide) {
                return InteractionResult.CONSUME
            }
        }

        return super.mobInteract(player, hand)
    }

    protected open fun usePlayerItem(player: Player, hand: InteractionHand?, stack: ItemStack) {
        if (!player.abilities.instabuild) {
            stack.shrink(1)
        }
    }

    open fun canFallInLove(): Boolean {
        return this.inLove <= 0
    }

    fun setInLove(player: Player?) {
        this.inLove = 600
        if (player != null) {
            this.loveCause = player.getUUID()
        }

        this.level().broadcastEntityEvent(this, 18.toByte())
    }

    fun isInLove(): Boolean {
        return this.inLove > 0
    }

    fun resetLove() {
        this.inLove = 0
    }

    fun canMate(otherWaterAnimal: HybridAquaticWaterAnimal): Boolean {
        return if (otherWaterAnimal === this) {
            false
        } else if (otherWaterAnimal.javaClass != this.javaClass) {
            false
        } else {
            this.isInLove() && otherWaterAnimal.isInLove()
        }
    }

    fun spawnChildFromBreeding(level: ServerLevel, mate: HybridAquaticWaterAnimal) {
        val baby = this.getBreedOffspring(level, mate) ?: return

        baby.isBaby = true
        baby.moveTo(this.x, this.y, this.z, 0.0f, 0.0f)

        if (this is VariantHolder<*> &&
            mate is VariantHolder<*> &&
            baby is VariantHolder<*>
        ) {
            (baby as VariantHolder<Any>).variant =
                (this as VariantHolder<Any>).variant
        }

        this.finalizeSpawnChildFromBreeding(level, mate)
        level.addFreshEntityWithPassengers(baby)
    }


    fun finalizeSpawnChildFromBreeding(level: ServerLevel, waterAnimal: HybridAquaticWaterAnimal) {
        this.setAge(6000)
        waterAnimal.setAge(6000)
        this.resetLove()
        waterAnimal.resetLove()
        level.broadcastEntityEvent(this, 18.toByte())
        if (level.gameRules.getBoolean(GameRules.RULE_DOMOBLOOT)) {
            level.addFreshEntity(
                ExperienceOrb(
                    level,
                    this.x,
                    this.y,
                    this.z,
                    this.getRandom().nextInt(7) + 1
                )
            )
        }
    }

    override fun handleEntityEvent(id: Byte) {
        if (id.toInt() == 18) {
            for (i in 0..6) {
                val d0 = this.random.nextGaussian() * 0.02
                val d1 = this.random.nextGaussian() * 0.02
                val d2 = this.random.nextGaussian() * 0.02
                this.level().addParticle(
                    ParticleTypes.HEART,
                    this.getRandomX(1.0),
                    this.randomY + 0.5,
                    this.getRandomZ(1.0),
                    d0,
                    d1,
                    d2
                )
            }
        } else {
            super.handleEntityEvent(id)
        }
    }

    override fun canBreatheUnderwater(): Boolean {
        return true
    }

    override fun getMobType(): MobType {
        return MobType.WATER
    }

    override fun checkSpawnObstruction(level: LevelReader): Boolean {
        return level.isUnobstructed(this)
    }

    override fun getAmbientSoundInterval(): Int {
        return 120
    }

    override fun getExperienceReward(): Int {
        return 1 + this.level().random.nextInt(3)
    }

    protected open fun handleAirSupply(airSupply: Int) {
        if (this.isAlive && !this.isInWaterOrBubble) {
            this.airSupply = airSupply - 1
            if (this.airSupply == -20) {
                this.airSupply = 0
                this.hurt(this.damageSources().drown(), 2.0f)
            }
        } else {
            this.airSupply = 300
        }
    }

    override fun baseTick() {
        val i = this.airSupply
        super.baseTick()
        this.handleAirSupply(i)
    }

    override fun isPushedByFluid(): Boolean {
        return false
    }

    override fun canBeLeashed(player: Player): Boolean {
        return false
    }
}