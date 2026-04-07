package dev.hybridlabs.aquatic.entity.miniboss

import dev.hybridlabs.aquatic.entity.HAEntityTypes
import dev.hybridlabs.aquatic.entity.ai.goal.KarkinosMeleeAttackGoal
import dev.hybridlabs.aquatic.entity.ai.goal.KarkinosSummonGoal
import dev.hybridlabs.aquatic.sound.HASoundEvents
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.chat.Component
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.server.level.ServerBossEvent
import net.minecraft.server.level.ServerPlayer
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.BossEvent
import net.minecraft.world.Difficulty
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.control.LookControl
import net.minecraft.world.entity.ai.control.MoveControl
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal
import net.minecraft.world.entity.ai.goal.RandomStrollGoal
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation
import net.minecraft.world.entity.animal.IronGolem
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.enchantment.EnchantmentHelper
import net.minecraft.world.item.enchantment.Enchantments
import net.minecraft.world.level.Level
import net.minecraft.world.level.pathfinder.BlockPathTypes
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.animation.RawAnimation
import software.bernie.geckolib.core.`object`.PlayState


class KarkinosEntity(type: EntityType<out HAMinibossEntity>, world: Level) :
    HAMinibossEntity(type, world) {
    private var flippedTimer: Int = 0
    private var flippedCooldown: Int = 0
    private var summonTimer: Int = 0
    var summonCooldown: Int = 0

    init {
        setPathfindingMalus(BlockPathTypes.WATER, 0.0f)
        moveControl = KarkinosMoveControl(this)
        navigation = GroundPathNavigation(this, world)
        lookControl = LookControl(this)
    }

    override fun maxUpStep(): Float {
        return 1.5F
    }

    override fun isAffectedByFluids(): Boolean {
        return false
    }

    private var bossBar: ServerBossEvent =
        ServerBossEvent(displayName, BossEvent.BossBarColor.RED, BossEvent.BossBarOverlay.NOTCHED_20)

    override fun registerGoals() {
        goalSelector.addGoal(1, KarkinosSummonGoal(this))
        goalSelector.addGoal(2, KarkinosMeleeAttackGoal(this, 0.5, true))
        goalSelector.addGoal(3, RandomStrollGoal(this, 0.5))
        goalSelector.addGoal(3, LookAtPlayerGoal(this, Player::class.java, 16.0f))
        goalSelector.addGoal(4, RandomLookAroundGoal(this))
        targetSelector.addGoal(1, HurtByTargetGoal(this))
        targetSelector.addGoal(2, NearestAttackableTargetGoal(this, Player::class.java, 10, true, true, null))
        targetSelector.addGoal(2, NearestAttackableTargetGoal(this, IronGolem::class.java, 10, true, true, null))
    }

    private fun getHandSwingDuration(): Int {
        return 40
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

    override fun startSeenByPlayer(player: ServerPlayer) {
        super.startSeenByPlayer(player)
        bossBar.addPlayer(player)
    }

    override fun stopSeenByPlayer(player: ServerPlayer) {
        super.stopSeenByPlayer(player)
        bossBar.removePlayer(player)
    }

    override fun checkDespawn() {
        if (level().difficulty == Difficulty.PEACEFUL && this.shouldDespawnInPeaceful()) {
            discard()
        } else {
            noActionTime = 0
        }
    }

    override fun getMobType(): MobType {
        return MobType.WATER
    }

    fun isFlipped(): Boolean {
        return entityData.get(FLIPPED)
    }

    private fun setFlipped(flipped: Boolean) {
        if (flipped && health <= maxHealth / 2f) return
        entityData.set(FLIPPED, flipped)
    }

    fun isSummoning(): Boolean {
        return entityData.get(SUMMONING)
    }

    private fun setSummoning(summon: Boolean) {
        entityData.set(SUMMONING, summon)
    }

    fun startSummoning() {
        setSummoning(true)
        playSound(SoundEvents.EVOKER_PREPARE_ATTACK, 1.0f, 1.0f)
        summonTimer = 30
        summonCooldown = 240
        navigation.stop()
    }

    fun stopSummoning() {
        setSummoning(false)
        summonTimer = 0
        summonCooldown = 300
    }

    private fun summonKarcinogens() {
        val random = this.random
        val count = 3

        for (i in 0 until count) {
            val offsetX = (random.nextDouble() - 0.5) * 6.0
            val offsetZ = (random.nextDouble() - 0.5) * 6.0
            val spawnPos = blockPosition().offset(offsetX.toInt(), 0, offsetZ.toInt())

            val karcinogen = HAEntityTypes.KARCINOGEN.get().create(level())
            if (karcinogen != null) {
                karcinogen.moveTo(
                    spawnPos.x.toDouble() + 0.5,
                    spawnPos.y.toDouble(),
                    spawnPos.z.toDouble() + 0.5,
                    random.nextFloat() * 360f,
                    0f
                )
                karcinogen.setOwner(this)
                karcinogen.setLimitedLife(200)
                level().addFreshEntity(karcinogen)
            }
        }
    }

    private fun summonKarcinomas() {
        val random = this.random
        val count = 3

        for (i in 0 until count) {
            val offsetX = (random.nextDouble() - 0.5) * 6.0
            val offsetZ = (random.nextDouble() - 0.5) * 6.0
            val spawnPos = blockPosition().offset(offsetX.toInt(), 0, offsetZ.toInt())

            val karcinoma = HAEntityTypes.KARCINOMA.get().create(level())
            if (karcinoma != null) {
                karcinoma.moveTo(
                    spawnPos.x.toDouble() + 0.5,
                    spawnPos.y.toDouble(),
                    spawnPos.z.toDouble() + 0.5,
                    random.nextFloat() * 360f,
                    0f
                )
                karcinoma.setOwner(this)
                karcinoma.setLimitedLife(400)
                level().addFreshEntity(karcinoma)
            }
        }
    }

    override fun defineSynchedData() {
        super.defineSynchedData()
        entityData.define(FLIPPED, false)
        entityData.define(SUMMONING, false)
    }

    override fun addAdditionalSaveData(compound: CompoundTag) {
        compound.putBoolean("Flipped", isFlipped())
        compound.putBoolean("Summoning", isSummoning())
        compound.putInt("SummonTimer", summonTimer)
        compound.putInt("SummonCooldown", summonCooldown)
        super.addAdditionalSaveData(compound)
    }

    override fun readAdditionalSaveData(compound: CompoundTag) {
        if (hasCustomName()) {
            bossBar.name = this.displayName
        }
        this.setFlipped(compound.getBoolean("Flipped"))
        this.setSummoning(compound.getBoolean("Summoning"))
        this.summonTimer = compound.getInt("SummonTimer")
        this.summonCooldown = compound.getInt("SummonCooldown")
        super.readAdditionalSaveData(compound)
    }

    override fun tick() {
        super.tick()

        if (isInWater && !onGround()) {
            isSwimming = false
            this.deltaMovement = deltaMovement.subtract(0.0, 0.1, 0.0)
            navigation.stop()
        }
    }

    override fun aiStep() {
        if (!level().isClientSide && this.isEffectiveAi) {
            if (this.isFlipped()) {
                if (--this.flippedTimer <= 0) {
                    this.setFlipped(false)
                } else {
                    this.deltaMovement = deltaMovement.multiply(0.0, 0.0, 0.0)
                    this.yHeadRot = 0f
                }
            }

            if (flippedCooldown > 0) {
                flippedCooldown--
            }
        }

        if (summonCooldown > 0) summonCooldown--

        if (isSummoning()) {
            summonTimer--

            if (summonTimer == 0) {
                if (this.isUnderWater) {
                    summonKarcinomas()
                    summonKarcinogens()
                } else {
                    summonKarcinogens()
                }
                stopSummoning()
            }
        }

        bossBar.progress = health / maxHealth
        super.aiStep()
    }

    override fun hurt(source: DamageSource, amount: Float): Boolean {
        val result = super.hurt(source, amount)

        if (result && !level().isClientSide &&
            source.directEntity is Player &&
            !isFlipped() &&
            flippedCooldown <= 0 &&
            health > maxHealth / 2f
        ) {

            val player = source.directEntity as Player
            val weapon = player.mainHandItem
            val hasFlipEnchant =
                EnchantmentHelper.getItemEnchantmentLevel(Enchantments.BANE_OF_ARTHROPODS, weapon) > 1 ||
                        EnchantmentHelper.getItemEnchantmentLevel(Enchantments.RIPTIDE, weapon) > 1

            if (hasFlipEnchant) {
                this.flippedTimer = random.nextInt(60, 100)
                this.flippedCooldown = 200
                this.setFlipped(true)
            }
        }
        return result
    }

    override fun setCustomName(name: Component?) {
        super.setCustomName(name)
        bossBar.name = this.displayName
    }

    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
        controllers.add(AnimationController(this, "flip_controller", 8) { state ->
            if (isFlipped()) {
                state.setAndContinue(FLIP_ANIMATION)
                PlayState.CONTINUE
            } else {
                PlayState.STOP
            }
        })
        controllers.add(AnimationController(this, "summon_controller", 4) { state ->
            if (isSummoning()) {
                state.setAndContinue(SUMMON_ANIMATION)
                PlayState.CONTINUE
            } else {
                PlayState.STOP
            }
        })
        controllers.add(DefaultAnimations.genericWalkRunIdleController(this))
        controllers.add(DefaultAnimations.genericAttackAnimation(this, DefaultAnimations.ATTACK_SWING))
    }

    override fun getAmbientSound(): SoundEvent {
        return HASoundEvents.KARKINOS_AMBIENT.get()
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return HASoundEvents.KARKINOS_HURT.get()
    }

    override fun getDeathSound(): SoundEvent {
        return HASoundEvents.KARKINOS_DIE.get()
    }

    companion object {
        val FLIP_ANIMATION: RawAnimation = RawAnimation.begin().thenPlay("misc.flip")
        val SUMMON_ANIMATION: RawAnimation = RawAnimation.begin().thenPlay("misc.summon")

        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 400.0)
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.ATTACK_DAMAGE, 10.0)
                .add(Attributes.ATTACK_KNOCKBACK, 1.0)
                .add(Attributes.FOLLOW_RANGE, 32.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0)
        }

        val FLIPPED: EntityDataAccessor<Boolean> =
            SynchedEntityData.defineId(KarkinosEntity::class.java, EntityDataSerializers.BOOLEAN)
        val SUMMONING: EntityDataAccessor<Boolean> =
            SynchedEntityData.defineId(KarkinosEntity::class.java, EntityDataSerializers.BOOLEAN)
    }

    internal class KarkinosMoveControl(
        private val karkinos: KarkinosEntity,
    ) : MoveControl(
        karkinos
    ) {
        override fun tick() {
            if (!karkinos.isFlipped() && !karkinos.isSummoning()) {
                super.tick()
            }
        }
    }
}
