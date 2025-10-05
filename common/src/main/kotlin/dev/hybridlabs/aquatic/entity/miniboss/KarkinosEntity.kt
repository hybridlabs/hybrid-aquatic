package dev.hybridlabs.aquatic.entity.miniboss

import dev.hybridlabs.aquatic.entity.ai.goal.KarkinosMeleeAttackGoal
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
import net.minecraft.world.entity.ai.control.MoveControl
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal
import net.minecraft.world.entity.ai.goal.RandomStrollGoal
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation
import net.minecraft.world.entity.ai.navigation.PathNavigation
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


class KarkinosEntity(entityType: EntityType<out HybridAquaticMinibossEntity>, world: Level) :
    HybridAquaticMinibossEntity(entityType, world) {
    private var flippedTimer: Int = 0
    private var timeSinceLastFlip: Int = 0

    override fun createNavigation(world: Level): PathNavigation {
        return GroundPathNavigation(this, world)
    }

    init {
        setPathfindingMalus(BlockPathTypes.WATER, 0.0f)
        moveControl = KarkinosMoveControl(this)
        navigation = GroundPathNavigation(this, world)
    }

    override fun maxUpStep(): Float {
        return 1.5F
    }

    override fun isAffectedByFluids(): Boolean {
        return !onGround()
    }

    override fun isPushedByFluid(): Boolean {
        return false
    }

    private var bossBar: ServerBossEvent =
        ServerBossEvent(displayName, BossEvent.BossBarColor.RED, BossEvent.BossBarOverlay.NOTCHED_20)

    override fun registerGoals() {
        goalSelector.addGoal(1, KarkinosMeleeAttackGoal(this, 0.5, true))
        goalSelector.addGoal(4, RandomStrollGoal(this, 0.5))
        goalSelector.addGoal(5, RandomLookAroundGoal(this))
        goalSelector.addGoal(8, LookAtPlayerGoal(this, Player::class.java, 16.0f))
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
        return MobType.ARTHROPOD
    }

    fun isFlipped(): Boolean {
        return entityData.get(FLIPPED)
    }

    private fun setFlipped(flipped: Boolean) {
        if (flipped && health <= maxHealth / 2f) return
        entityData.set(FLIPPED, flipped)
    }

    override fun defineSynchedData() {
        super.defineSynchedData()
        entityData.define(FLIPPED, false)
    }

    override fun addAdditionalSaveData(nbt: CompoundTag) {
        nbt.putBoolean("Flipped", isFlipped())

        super.addAdditionalSaveData(nbt)
    }

    override fun readAdditionalSaveData(nbt: CompoundTag) {
        if (hasCustomName()) {
            bossBar.name = this.displayName
        }
        this.setFlipped(nbt.getBoolean("Flipped"))

        super.readAdditionalSaveData(nbt)
    }

    override fun aiStep() {
        if (!level().isClientSide && this.isEffectiveAi) {
            if (this.isFlipped()) {
                if (--this.flippedTimer <= 0) {
                    this.setFlipped(false)
                } else {
                    this.deltaMovement = deltaMovement.subtract(0.0, 0.01, 0.0)
                    this.yHeadRot = 0f
                }
            }

            if (timeSinceLastFlip > 0) {
                timeSinceLastFlip--
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
            timeSinceLastFlip <= 0 &&
            health > maxHealth / 2f
        ) {

            val player = source.directEntity as Player
            val weapon = player.mainHandItem
            val hasFlipEnchant =
                EnchantmentHelper.getItemEnchantmentLevel(Enchantments.BANE_OF_ARTHROPODS, weapon) > 1 ||
                        EnchantmentHelper.getItemEnchantmentLevel(Enchantments.KNOCKBACK, weapon) > 2 ||
                        EnchantmentHelper.getItemEnchantmentLevel(Enchantments.RIPTIDE, weapon) > 1

            if (hasFlipEnchant) {
                this.flippedTimer = random.nextInt(60, 100)
                this.timeSinceLastFlip = 400
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
        controllers.add(DefaultAnimations.genericWalkRunIdleController(this))
        controllers.add(DefaultAnimations.genericAttackAnimation(this, DefaultAnimations.ATTACK_SWING))
        controllers.add(AnimationController(this, 10) { state ->
            if (isFlipped()) {
                state.setAndContinue(FLIP)
                PlayState.CONTINUE
            } else {
                PlayState.STOP
            }
        }
        )
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return SoundEvents.TURTLE_EGG_CRACK
    }

    override fun getDeathSound(): SoundEvent {
        return SoundEvents.TURTLE_EGG_BREAK
    }

    companion object {

        val FLIP: RawAnimation = RawAnimation.begin().thenPlay("misc.flip")

        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 300.0)
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.ATTACK_DAMAGE, 10.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.5)
                .add(Attributes.FOLLOW_RANGE, 32.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0)
        }

        val FLIPPED: EntityDataAccessor<Boolean> =
            SynchedEntityData.defineId(KarkinosEntity::class.java, EntityDataSerializers.BOOLEAN)
    }

    internal class KarkinosMoveControl(
        private val karkinos: KarkinosEntity,
    ) : MoveControl(
        karkinos
    ) {
        override fun tick() {
            if (!karkinos.isFlipped()) {
                super.tick()
            }
        }
    }
}