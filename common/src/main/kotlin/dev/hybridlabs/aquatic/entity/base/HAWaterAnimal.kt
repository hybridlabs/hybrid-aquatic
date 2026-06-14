package dev.hybridlabs.aquatic.entity.base

import dev.hybridlabs.aquatic.entity.ai.MobTargetConfiguration
import dev.hybridlabs.aquatic.item.HAItems
import net.minecraft.core.particles.ItemParticleOption
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.server.level.ServerLevel
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.tags.FluidTags
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.*
import net.minecraft.world.entity.ai.navigation.PathNavigation
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation
import net.minecraft.world.entity.item.ItemEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.GameRules
import net.minecraft.world.level.Level
import net.minecraft.world.level.LevelReader
import net.minecraft.world.level.ServerLevelAccessor
import net.minecraft.world.level.pathfinder.PathType
import net.minecraft.world.phys.Vec3
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.animation.RawAnimation
import java.util.*

@Suppress("UNCHECKED_CAST")
abstract class HAWaterAnimal protected constructor(
    entityType: EntityType<out HAWaterAnimal>,
    level: Level,
) :
    AgeableMob(entityType, level), GeoEntity {
    private var inLove = 0
    private var loveCause: UUID? = null
    private var attackTick = 0
    private var ticksSinceEaten = 0
    var prevRoll: Float = 0f
    var currentRoll: Float = 0.0f
    var fromFishingNet = false

    open fun getTargetConfig(): MobTargetConfiguration? = null

    init {
        this.setCanPickUpLoot(true)
    }

    override fun createNavigation(level: Level): PathNavigation {
        setPathfindingMalus(PathType.WATER, 0.0f)
        setPathfindingMalus(PathType.DANGER_FIRE, 16.0f)
        setPathfindingMalus(PathType.DAMAGE_FIRE, -1.0f)

        return WaterBoundPathNavigation(this, level)
    }

    override fun customServerAiStep() {
        if (this.getAge() != 0) {
            this.inLove = 0
        }

        super.customServerAiStep()
    }

    override fun removeWhenFarAway(distanceSquared: Double): Boolean {
        return !isPersistenceRequired &&
                !this.fromFishingNet &&
                !this.hasCustomName()
    }

    override fun tick() {
        super.tick()

        prevRoll = currentRoll

        if (hunger > 0) hunger -= 1
    }

    override fun aiStep() {
        super.aiStep()


        prevRoll = currentRoll
        var targetRoll = ((this.yRot - this.yRotO) * 0.1f).coerceIn(-0.45f, 0.45f)
        targetRoll = -targetRoll
        currentRoll += (targetRoll - currentRoll) * 0.05f

        this.updateSwingTime()

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

        if (!this.level().isClientSide && this.isAlive && this.isEffectiveAi) {
            ++this.ticksSinceEaten

            val itemstack = this.getItemBySlot(EquipmentSlot.MAINHAND)

            if (this.canEat(itemstack)) {

                if (this.ticksSinceEaten == 60) {
                    triggerAnim("eat_controller", "eat")
                }

                if (this.ticksSinceEaten > 60) {

                    if (this.random.nextFloat() < 0.2f) {
                        this.playSound(this.getEatingSound(itemstack), 1.0f, 1.0f)
                        this.level().broadcastEntityEvent(this, 45.toByte())
                    }
                }

                if (this.ticksSinceEaten > 100) {
                    val result = itemstack.finishUsingItem(this.level(), this)

                    if (!result.isEmpty) {
                        this.setItemSlot(EquipmentSlot.MAINHAND, result)
                    } else {
                        this.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY)
                    }

                    this.ticksSinceEaten = 0
                }
            } else {
                this.ticksSinceEaten = 0
            }
        }
    }

    private fun canEat(stack: ItemStack): Boolean {
        return this.isFood(stack) && this.target == null
    }

    override fun canHoldItem(stack: ItemStack): Boolean {
        val heldStack = this.getItemBySlot(EquipmentSlot.MAINHAND)

        return if (heldStack.isEmpty) {
            this.isFood(stack)
        } else {
            this.ticksSinceEaten > 0 &&
                    this.isFood(stack) &&
                    !this.isFood(heldStack)
        }
    }

    override fun canTakeItem(itemstack: ItemStack): Boolean {
        val equipmentslot = getEquipmentSlotForItem(itemstack)
        return if (!this.getItemBySlot(equipmentslot).isEmpty) {
            false
        } else {
            equipmentslot == EquipmentSlot.MAINHAND && super.canTakeItem(itemstack)
        }
    }

    override fun pickUpItem(itemEntity: ItemEntity) {
        if (this.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty) {
            val itemstack = itemEntity.item
            if (this.canHoldItem(itemstack)) {
                this.onItemPickup(itemEntity)
                this.setItemSlot(EquipmentSlot.MAINHAND, itemstack)
                this.setGuaranteedDrop(EquipmentSlot.MAINHAND)
                this.take(itemEntity, itemstack.count)
                itemEntity.discard()
            }
        }
    }

    override fun handleEntityEvent(id: Byte) {
        if (id.toInt() == 45) {
            val itemstack = this.getItemBySlot(EquipmentSlot.MAINHAND)
            if (!itemstack.isEmpty) {
                for (i in 0..7) {
                    val vec3 = (Vec3(
                        (this.random.nextFloat().toDouble() - 0.5) * 0.1,
                        Math.random() * 0.1 + 0.1,
                        0.0
                    )).xRot(-this.xRot * (Math.PI.toFloat() / 180f))
                        .yRot(-this.yRot * (Math.PI.toFloat() / 180f))
                    this.level().addParticle(
                        ItemParticleOption(ParticleTypes.ITEM, itemstack),
                        this.x + this.lookAngle.x / 2.0,
                        this.y,
                        this.z + this.lookAngle.z / 2.0,
                        vec3.x,
                        vec3.y + 0.05,
                        vec3.z
                    )
                }
            }
        }

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

    override fun hurt(source: DamageSource, amount: Float): Boolean {
        if (this.isInvulnerableTo(source)) {
            return false
        }

        if (this.isSitting()) {
            this.stopSitting()
        }

        this.inLove = 0
        return super.hurt(source, amount)
    }

    //#region Drops
    override fun dropFromLootTable(source: DamageSource, causedByPlayer: Boolean) {
        val attacker = source.directEntity
        if (attacker !is HAWaterAnimal) {
            super.dropFromLootTable(source, causedByPlayer)
        }
    }

    override fun getBaseExperienceReward(): Int {
        return 1 + this.level().random.nextInt(3)
    }
    //#endregion

    //#region Data
    fun isSitting(): Boolean {
        return entityData.get(SITTING)
    }

    private fun setSitting(sitting: Boolean) {
        entityData.set(SITTING, sitting)
    }

    fun startSitting() {
        setSitting(true)
        navigation.stop()
    }

    fun stopSitting() {
        setSitting(false)
    }

    fun isGrazing(): Boolean {
        return entityData.get(GRAZING)
    }

    private fun setGrazing(grazing: Boolean) {
        entityData.set(GRAZING, grazing)
    }

    fun startGrazing() {
        setGrazing(true)
        navigation.stop()
    }

    fun stopGrazing() {
        setGrazing(false)
    }

    fun isFeeding(): Boolean {
        return entityData.get(FEEDING)
    }

    private fun setFeeding(feeding: Boolean) {
        entityData.set(FEEDING, feeding)
    }

    fun startFeeding() {
        setFeeding(true)
        navigation.stop()
    }

    fun stopFeeding() {
        setFeeding(false)
    }

    fun isDigging(): Boolean {
        return entityData.get(DIGGING)
    }

    private fun setDigging(digging: Boolean) {
        entityData.set(DIGGING, digging)
    }

    fun startDigging() {
        setDigging(true)
        playSound(SoundEvents.SNIFFER_DIGGING, 0.1f, 2.0f)
        navigation.stop()
    }

    fun stopDigging() {
        setDigging(false)
    }

    private fun setPerformingTrick(performing: Boolean) {
        entityData.set(PERFORMING, performing)
    }

    fun startPerfomingTrick() {
        setPerformingTrick(true)
    }

    fun stopPerformingTrick() {
        setPerformingTrick(false)
    }

    override fun defineSynchedData(builder: SynchedEntityData.Builder) {
        super.defineSynchedData(builder)
        builder.define(SIZE, 0)
        builder.define(HUNGER, MAX_HUNGER)
        builder.define(MOISTNESS, getMaxMoistness())
        builder.define(SITTING, false)
        builder.define(FEEDING, false)
        builder.define(GRAZING, false)
        builder.define(DIGGING, false)
        builder.define(PERFORMING, false)
        builder.define(ATTEMPT_ATTACK, false)
    }

    override fun addAdditionalSaveData(compound: CompoundTag) {
        super.addAdditionalSaveData(compound)
        compound.putInt(SIZE_KEY, size)
        compound.putInt(HUNGER_KEY, hunger)
        compound.putInt(MOISTNESS_KEY, moistness)
        compound.putBoolean("FromFishingNet", fromFishingNet)
        compound.putInt("InLove", this.inLove)
        compound.putInt("AttackTick", this.attackTick)
        compound.putBoolean("Sitting", isSitting())
        compound.putBoolean("Feeding", isFeeding())
        this.setGrazing(compound.getBoolean("Grazing"))
        this.setDigging(compound.getBoolean("Digging"))
        this.setDigging(compound.getBoolean("Performing"))

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
        this.attackTick = compound.getInt("AttackTick")
        this.setSitting(compound.getBoolean("Sitting"))
        this.setFeeding(compound.getBoolean("Feeding"))
        this.setGrazing(compound.getBoolean("Grazing"))
        this.setDigging(compound.getBoolean("Digging"))
        this.setDigging(compound.getBoolean("Performing"))
    }
    //#endregion

    open fun isFood(stack: ItemStack): Boolean {
        return stack.`is`(HAItems.FISH_FOOD.get())
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

        this.setPersistenceRequired()
        baby.setPersistenceRequired()
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
        entityData: SpawnGroupData?
    ): SpawnGroupData? {
        this.size = this.random.nextIntBetweenInclusive(getMinSize(), getMaxSize())
        return super.finalizeSpawn(world, difficulty, spawnReason, entityData)
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

    override fun canBeLeashed(): Boolean {
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

    private fun getHandSwingDuration(): Int {
        return 20
    }

    override fun updateSwingTime() {
        val i = this.getHandSwingDuration()
        if (this.swinging) {
            ++this.swingTime
            if (this.swingTime >= i) {
                this.swingTime = 0
                this.swinging = false
            }
        } else {
            this.swingTime = 0
        }

        this.attackAnim = swingTime.toFloat() / i.toFloat()
    }

    override fun getAttackAnim(tickDelta: Float): Float {
        var f = this.attackAnim - this.oAttackAnim
        if (f < 0.0f) {
            ++f
        }

        return this.oAttackAnim + f * tickDelta
    }

    companion object {
        val SIZE: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(HAWaterAnimal::class.java, EntityDataSerializers.INT)
        val HUNGER: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(HAWaterAnimal::class.java, EntityDataSerializers.INT)
        val MOISTNESS: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(HAWaterAnimal::class.java, EntityDataSerializers.INT)
        val ATTEMPT_ATTACK: EntityDataAccessor<Boolean> =
            SynchedEntityData.defineId(HAWaterAnimal::class.java, EntityDataSerializers.BOOLEAN)
        val SITTING: EntityDataAccessor<Boolean> =
            SynchedEntityData.defineId(HAWaterAnimal::class.java, EntityDataSerializers.BOOLEAN)
        val FEEDING: EntityDataAccessor<Boolean> =
            SynchedEntityData.defineId(HAWaterAnimal::class.java, EntityDataSerializers.BOOLEAN)
        val GRAZING: EntityDataAccessor<Boolean> =
            SynchedEntityData.defineId(HAWaterAnimal::class.java, EntityDataSerializers.BOOLEAN)
        val DIGGING: EntityDataAccessor<Boolean> =
            SynchedEntityData.defineId(HAWaterAnimal::class.java, EntityDataSerializers.BOOLEAN)
        val PERFORMING: EntityDataAccessor<Boolean> =
            SynchedEntityData.defineId(HAWaterAnimal::class.java, EntityDataSerializers.BOOLEAN)

        val EAT_ANIMATION: RawAnimation = RawAnimation.begin().thenPlay("misc.eat")
        val TRICK_ANIMATION: RawAnimation = RawAnimation.begin().thenPlay("misc.trick")
        val FEED_ANIMATION: RawAnimation = RawAnimation.begin().thenPlay("misc.feed")
        val GRAZE_ANIMATION: RawAnimation = RawAnimation.begin().thenPlay("misc.graze")
        val DIG_ANIMATION: RawAnimation = RawAnimation.begin().thenPlay("misc.dig")

        const val SIZE_KEY = "Size"
        const val MAX_HUNGER = 1200
        const val HUNGER_KEY = "Hunger"
        const val MOISTNESS_KEY = "Moistness"

        fun getScaleAdjustment(animal: HAWaterAnimal, adjustment: Float): Float {
            return 1.0f + (animal.size * adjustment)
        }
    }
}