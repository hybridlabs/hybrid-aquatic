package dev.hybridlabs.aquatic.entity.base

import dev.hybridlabs.aquatic.entity.ai.MobTargetConfiguration
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundSource
import net.minecraft.tags.FluidTags
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.*
import net.minecraft.world.entity.ai.navigation.PathNavigation
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.GameRules
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.ServerLevelAccessor
import net.minecraft.world.level.pathfinder.BlockPathTypes
import software.bernie.geckolib.animatable.GeoEntity
import java.util.*

@Suppress("UNCHECKED_CAST")
abstract class HAWaterAnimal protected constructor(
    entityType: EntityType<out HAWaterAnimal>,
    level: Level,
) :
    AgeableMob(entityType, level), GeoEntity {
    private var inLove = 0
    private var loveCause: UUID? = null
    var fromFishingNet = false

    open fun getTargetConfig(): MobTargetConfiguration? = null

    override fun createNavigation(level: Level): PathNavigation {
        setPathfindingMalus(BlockPathTypes.WATER, 0.0f)
        setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 16.0f)
        setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, -1.0f)

        return WaterBoundPathNavigation(this, level)
    }

    override fun customServerAiStep() {
        if (this.getAge() != 0) {
            this.inLove = 0
        }

        super.customServerAiStep()
    }

    override fun removeWhenFarAway(distanceSquared: Double): Boolean {
        return !this.fromFishingNet && !this.hasCustomName()
    }

    override fun tick() {
        super.tick()

        if (hunger > 0) hunger -= 1
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

    //#region Drops
    override fun dropFromLootTable(source: DamageSource, causedByPlayer: Boolean) {
        val attacker = source.directEntity
        if (attacker !is HAWaterAnimal) {
            super.dropFromLootTable(source, causedByPlayer)
        }
    }

    override fun getExperienceReward(): Int {
        return 1 + this.level().random.nextInt(3)
    }
    //#endregion

    //#region Data
    override fun defineSynchedData() {
        super.defineSynchedData()
        entityData.define(SIZE, 0)
        entityData.define(HUNGER, MAX_HUNGER)
        entityData.define(MOISTNESS, getMaxMoistness())
    }

    override fun addAdditionalSaveData(compound: CompoundTag) {
        super.addAdditionalSaveData(compound)
        compound.putInt(SIZE_KEY, size)
        compound.putInt(HUNGER_KEY, hunger)
        compound.putInt(MOISTNESS_KEY, moistness)
        compound.putBoolean("FromFishingNet", fromFishingNet)
        compound.putInt("InLove", this.inLove)

        if (this.loveCause != null) {
            compound.putUUID("LoveCause", this.loveCause)
        }
    }

    override fun readAdditionalSaveData(compound: CompoundTag) {
        super.readAdditionalSaveData(compound)
        size = compound.getInt(SIZE_KEY)
        hunger = compound.getInt(HUNGER_KEY)
        moistness = compound.getInt(MOISTNESS_KEY)
        fromFishingNet = compound.getBoolean("FromFishingNet")
        this.inLove = compound.getInt("InLove")
        this.loveCause = if (compound.hasUUID("LoveCause")) compound.getUUID("LoveCause") else null
    }
    //#endregion

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

    fun canMate(otherWaterAnimal: HAWaterAnimal): Boolean {
        return if (otherWaterAnimal === this) {
            false
        } else if (otherWaterAnimal.javaClass != this.javaClass) {
            false
        } else {
            this.isInLove() && otherWaterAnimal.isInLove()
        }
    }

    open fun spawnChildFromBreeding(level: ServerLevel, mate: HAWaterAnimal) {
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

    override fun finalizeSpawn(
        world: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnReason: MobSpawnType,
        entityData: SpawnGroupData?,
        entityNbt: CompoundTag?,
    ): SpawnGroupData? {
        this.size = this.random.nextIntBetweenInclusive(getMinSize(), getMaxSize())
        return super.finalizeSpawn(world, difficulty, spawnReason, entityData, entityNbt)
    }

    fun finalizeSpawnChildFromBreeding(level: ServerLevel, waterAnimal: HAWaterAnimal) {
        this.size = this.random.nextIntBetweenInclusive(getMinSize(), getMaxSize())
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

    //#region Water Interaction
    override fun baseTick() {
        val i = this.airSupply
        super.baseTick()
        this.handleAirSupply(i)
    }

    protected open fun handleAirSupply(air: Int) {
        if (this.isAlive && !this.isInWaterOrBubble) {
            this.airSupply -= 1
            if (this.airSupply == -20) {
                this.airSupply = 0
                this.hurt(this.damageSources().drown(), 2.0f)
            }
        } else {
            this.airSupply = maxAirSupply
        }
    }

    fun isBelowWaterline(): Boolean {
        return this.isUnderWater || this.getFluidHeight(FluidTags.WATER) > this.getWaterline()
    }

    open fun getWaterline(): Float {
        return 0.5f
    }

    override fun isPushedByFluid(): Boolean {
        return false
    }

    override fun canBreatheUnderwater(): Boolean {
        return true
    }

    override fun getMobType(): MobType {
        return MobType.WATER
    }
    //#endregion

    override fun checkSpawnObstruction(level: LevelReader): Boolean {
        return level.isUnobstructed(this)
    }

    //#region SFX
    override fun getAmbientSoundInterval(): Int {
        return 120
    }

    override fun getSoundSource(): SoundSource {
        return SoundSource.AMBIENT
    }
    //#endregion

    override fun canBeLeashed(player: Player): Boolean {
        return false
    }

    //#region Properties
    protected open fun getMinSize(): Int {
        return -5
    }

    protected open fun getMaxSize(): Int {
        return 5
    }

    open fun getMaxMoistness(): Int {
        return 600
    }

    var size: Int
        get() = entityData.get(SIZE)
        set(size) {
            entityData.set(SIZE, size)
        }

    var hunger: Int
        get() = entityData.get(HUNGER)
        set(hunger) {
            entityData.set(HUNGER, hunger)
        }

    var moistness: Int
        get() = entityData.get(MOISTNESS)
        set(moistness) {
            entityData.set(MOISTNESS, moistness)
        }
    //#endregion

    companion object {
        val SIZE: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(HAWaterAnimal::class.java, EntityDataSerializers.INT)
        val HUNGER: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(HAWaterAnimal::class.java, EntityDataSerializers.INT)
        val MOISTNESS: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(HAWaterAnimal::class.java, EntityDataSerializers.INT)

        const val SIZE_KEY = "Size"
        const val MAX_HUNGER = 2400
        const val HUNGER_KEY = "Hunger"
        const val MOISTNESS_KEY = "Moistness"

        fun getScaleAdjustment(animal: HAWaterAnimal, adjustment: Float): Float {
            return 1.0f + (animal.size * adjustment)
        }
    }
}