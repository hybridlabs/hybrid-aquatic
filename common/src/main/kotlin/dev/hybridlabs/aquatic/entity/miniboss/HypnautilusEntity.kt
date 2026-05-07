package dev.hybridlabs.aquatic.entity.miniboss

import dev.hybridlabs.aquatic.entity.ai.goal.MinionLookAtOwnerTargetGoal
import dev.hybridlabs.aquatic.sound.HASoundEvents
import net.minecraft.core.BlockPos
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.util.Mth
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl
import net.minecraft.world.entity.ai.goal.Goal
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.pathfinder.BlockPathTypes
import net.minecraft.world.phys.Vec3
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import java.util.*

class HypnautilusEntity(type: EntityType<out HAMinionEntity>, world: Level) : HAMinionEntity(type, world) {
    var beastPosition = 0
    val beastDistance = 5.0
    var prevOwner: UUID? = null
    private var hypnosisTimer: Int = 0
    var hypnosisCooldown: Int = 0

    init {
        setPathfindingMalus(BlockPathTypes.WATER, 0.0f)
        moveControl = SmoothSwimmingMoveControl(this, 85, 5, 0.02F, 0.1f, true)
        lookControl = SmoothSwimmingLookControl(this, 10)
        navigation = WaterBoundPathNavigation(this, world)
    }

    override fun registerGoals() {
        //super.registerGoals()
        goalSelector.addGoal(1, HypnautilusFindOwnerGoal(this))
        goalSelector.addGoal(1, MinionLookAtOwnerTargetGoal(this))
        goalSelector.addGoal(4, RandomSwimmingGoal(this, 1.0, 2))
        goalSelector.addGoal(1, HypnautilusSyncedMovementGoal(this))
        goalSelector.addGoal(2, LookAtPlayerGoal(this, Player::class.java, 64.0f, 1f))
        targetSelector.addGoal(1, HurtByTargetGoal(this))
        targetSelector.addGoal(2, NearestAttackableTargetGoal(this, Player::class.java, 10, true, true, null))
    }

    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
        controllers.add(DefaultAnimations.genericAttackAnimation(this, DefaultAnimations.ATTACK_SWING))
        controllers.add(
            AnimationController(this, "Swim/Run/Idle", 4) { state ->
                when {
                    isInWater -> {
                        state.setAndContinue(DefaultAnimations.IDLE)
                    }

                    else -> {
                        state.setAndContinue(DefaultAnimations.IDLE)
                    }
                }
            })
    }

    fun isHypnotizing(): Boolean {
        return entityData.get(HYPNOTIZING)
    }

    private fun setHypnotizing(hypnotizing: Boolean) {
        entityData.set(HYPNOTIZING, hypnotizing)
    }

    fun startHypnotizing() {
        setHypnotizing(true)
        playSound(SoundEvents.EVOKER_PREPARE_ATTACK, 1.0f, 1.0f)
        hypnosisTimer = 30
        hypnosisCooldown = 240
        navigation.stop()
    }

    fun stopHypnotizing() {
        setHypnotizing(false)
        hypnosisTimer = 0
        hypnosisCooldown = 300
    }

    override fun defineSynchedData() {
        super.defineSynchedData()
        entityData.define(HYPNOTIZING, false)
    }

    override fun addAdditionalSaveData(nbt: CompoundTag) {
        if (this.getOwner() != null) {
            nbt.putUUID("Owner", this.getOwner()!!.uuid)
        }
        nbt.putBoolean("Hypnotizing", isHypnotizing())
        nbt.putInt("HypnosisTimer", hypnosisTimer)
        nbt.putInt("HypnosisCooldown", hypnosisCooldown)
        super.addAdditionalSaveData(nbt)
    }

    override fun readAdditionalSaveData(nbt: CompoundTag) {
        super.readAdditionalSaveData(nbt)
        if (nbt.contains("Owner")) {
            prevOwner = nbt.getUUID("Owner")
        }
        this.setHypnotizing(nbt.getBoolean("Hypnotizing"))
        this.hypnosisTimer = nbt.getInt("HypnosisTimer")
        this.hypnosisCooldown = nbt.getInt("HypnosisCooldown")
    }

    override fun tick() {
        super.tick()

        if (!this.isInWater) {
            this.xRot = 0.0f
            this.yRot = 0.0f
        }
    }

    fun getWobble(amount: Float): Float {
        return (this.random.nextFloat() * amount) - (amount / 2f)

    }

    fun getAngleWobble(): Float {
        return getWobble(0.0625f)
    }

    fun getYawWobble(): Float {
        return getWobble(0.125f)
    }

    fun calcBeastRelativePos(): Vec3 {
        val owner = this.getOwner() ?: return Vec3.ZERO
        val upVector = Vec3(0.0, 1.0, 0.0)

        val angle = Mth.PI / 3.0F * beastPosition + Mth.PI / 6F +
                getAngleWobble()


        return owner.position().add(
            upVector
                .zRot(angle)
                .yRot(
                    (180 - owner.yRot) * Mth.DEG_TO_RAD
                            + getYawWobble()
                )
                .scale(beastDistance)
        )
    }

    //#region SFX
    override fun getAmbientSound(): SoundEvent {
        return HASoundEvents.HYPNAUTILUS_AMBIENT.get()
    }

    override fun getHurtSound(source: DamageSource): SoundEvent {
        return HASoundEvents.HYPNAUTILUS_HURT.get()
    }

    override fun getDeathSound(): SoundEvent {
        return HASoundEvents.HYPNAUTILUS_DIE.get()
    }
    //#endregion

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes().add(Attributes.MAX_HEALTH, 6.0).add(Attributes.MOVEMENT_SPEED, 1.0)
                .add(Attributes.ATTACK_DAMAGE, 4.0).add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 24.0)
        }

        val HYPNOTIZING: EntityDataAccessor<Boolean> =
            SynchedEntityData.defineId(HypnautilusEntity::class.java, EntityDataSerializers.BOOLEAN)

        class HypnautilusFindOwnerGoal(private val hypnautilus: HypnautilusEntity) : Goal() {
            override fun canUse(): Boolean {
                return (hypnautilus.prevOwner != null && hypnautilus.getOwner() == null)
            }

            override fun tick() {
                val candidates = hypnautilus.level().getEntitiesOfClass(ShellBeastEntity::class.java,hypnautilus.boundingBox.inflate(64.0))
                val owner = candidates.getOrNull(0)
                owner?.addMinion(hypnautilus)
            }
        }

        class HypnautilusSyncedMovementGoal(private val hypnautilus: HypnautilusEntity) : Goal() {
            override fun canUse(): Boolean {
                return (hypnautilus.getOwner() != null)
            }

            override fun requiresUpdateEveryTick(): Boolean {
                return true
            }

            override fun tick() {
                val target = hypnautilus.calcBeastRelativePos()
                if (hypnautilus.level().getBlockState(BlockPos.containing(target)).`is`(Blocks.WATER))
                    hypnautilus.moveTo(hypnautilus.calcBeastRelativePos())
            }
        }
    }
}