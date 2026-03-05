package dev.hybridlabs.aquatic.entity.misc

import net.minecraft.core.Direction
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.util.Mth
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.*
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.level.GameRules
import net.minecraft.world.level.Level
import net.minecraft.world.level.gameevent.GameEvent
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.util.GeckoLibUtil

open class ArgonautEntity(
    type: EntityType<out ArgonautEntity>,
    world: Level,
) :
    Entity(type, world), PlayerRideable,
    GeoEntity {
    private val animCache = GeckoLibUtil.createInstanceCache(this)
    private var outOfControlTicks = 0f

    override fun defineSynchedData() {
        this.entityData.define(DATA_ID_HURT, 0)
        this.entityData.define(DATA_ID_HURTDIR, 1)
        this.entityData.define(DATA_ID_DAMAGE, 0.0f)
        this.entityData.define(DATA_ID_RIGHT_PROPELLER, false)
        this.entityData.define(DATA_ID_LEFT_PROPELLER, false)
        this.entityData.define(DATA_ID_BACK_PROPELLER, false)
    }

    override fun tick() {
        super.tick()

        if (!this.isNoGravity) {
            this.deltaMovement = this.deltaMovement.add(0.0, -0.04, 0.0)
        }

        this.move(MoverType.SELF, this.deltaMovement)
    }

    override fun addAdditionalSaveData(tag: CompoundTag) {
        tag.putFloat("Damage", getDamage())
    }

    override fun readAdditionalSaveData(tag: CompoundTag) {
        setDamage(tag.getFloat("Damage"))
    }

    override fun hurt(source: DamageSource, amount: Float): Boolean {
        if (this.isInvulnerableTo(source)) {
            return false
        } else if (!this.level().isClientSide && !this.isRemoved) {
            this.setHurtTime(10)
            this.setDamage(this.getDamage() + amount * 10.0f)
            this.markHurt()
            this.gameEvent(GameEvent.ENTITY_DAMAGE, source.entity)
            val flag = source.entity is Player && (source.entity as Player).abilities.instabuild
            if (flag || this.getDamage() > 40.0f) {
                if (!flag && this.level().gameRules.getBoolean(GameRules.RULE_DOENTITYDROPS)) {
                    this.destroy(source)
                }

                this.discard()
            }

            return true
        } else {
            return true
        }
    }

    open fun getDropItem(): Item {
        return Items.BIRCH_BOAT
    }

    protected open fun destroy(damageSource: DamageSource?) {
        this.spawnAtLocation(this.getDropItem())
    }

    fun setDamage(damageTaken: Float) {
        this.entityData.set(DATA_ID_DAMAGE, damageTaken)
    }

    fun getDamage(): Float {
        return this.entityData.get(DATA_ID_DAMAGE)
    }

    fun setHurtTime(hurtTime: Int) {
        this.entityData.set(DATA_ID_HURT, hurtTime)
    }

    override fun getMotionDirection(): Direction {
        return this.direction.clockWise
    }

    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
        controllers.add(
            DefaultAnimations.genericSwimIdleController(this)
        )
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache? {
        return animCache
    }

    override fun getMovementEmission(): MovementEmission {
        return MovementEmission.EVENTS
    }

    override fun canCollideWith(entity: Entity): Boolean {
        return canVehicleCollide(this, entity)
    }

    fun canVehicleCollide(vehicle: Entity, entity: Entity): Boolean {
        return (entity.canBeCollidedWith() || entity.isPushable) && !vehicle.isPassengerOfSameVehicle(entity)
    }

    override fun canBeCollidedWith(): Boolean {
        return true
    }

    override fun isPushable(): Boolean {
        return true
    }

    override fun getPassengersRidingOffset(): Double {
        return 0.65
    }

    protected fun clampRotation(entityToUpdate: Entity) {
        entityToUpdate.setYBodyRot(this.yRot)
        val f = Mth.wrapDegrees(entityToUpdate.yRot - this.yRot)
        val f1 = Mth.clamp(f, -105.0f, 105.0f)
        entityToUpdate.yRotO += f1 - f
        entityToUpdate.yRot = entityToUpdate.yRot + f1 - f
        entityToUpdate.yHeadRot = entityToUpdate.yRot
    }

    override fun onPassengerTurned(entityToUpdate: Entity) {
        this.clampRotation(entityToUpdate)
    }

    override fun interact(player: Player, hand: InteractionHand): InteractionResult {
        return if (player.isSecondaryUseActive) {
            InteractionResult.PASS
        } else if (this.isVehicle) {
            InteractionResult.PASS
        } else if (!this.level().isClientSide) {
            if (player.startRiding(this)) InteractionResult.CONSUME else InteractionResult.PASS
        } else {
            InteractionResult.SUCCESS
        }
    }

    override fun isPickable(): Boolean{
        return true
    }

    override fun positionRider(passenger: Entity, callback: MoveFunction) {
        if (this.hasPassenger(passenger)) {
            callback.accept(
                passenger,
                this.x,
                this.y + this.passengersRidingOffset + passenger.myRidingOffset,
                this.z
            )
        }
    }

    override fun canAddPassenger(passenger: Entity): Boolean {
        return this.passengers.isEmpty()
    }

    override fun getControllingPassenger(): LivingEntity? {
        val entity = this.firstPassenger
        val livingentity1: LivingEntity? = entity as? LivingEntity

        return livingentity1
    }
    //#endregion

    companion object {
        private val DATA_ID_HURT: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(ArgonautEntity::class.java, EntityDataSerializers.INT)

        private val DATA_ID_HURTDIR: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(ArgonautEntity::class.java, EntityDataSerializers.INT)

        private val DATA_ID_DAMAGE: EntityDataAccessor<Float> =
            SynchedEntityData.defineId(ArgonautEntity::class.java, EntityDataSerializers.FLOAT)

        private val DATA_ID_RIGHT_PROPELLER: EntityDataAccessor<Boolean> =
            SynchedEntityData.defineId(ArgonautEntity::class.java, EntityDataSerializers.BOOLEAN)

        private val DATA_ID_LEFT_PROPELLER: EntityDataAccessor<Boolean> =
            SynchedEntityData.defineId(ArgonautEntity::class.java, EntityDataSerializers.BOOLEAN)

        private val DATA_ID_BACK_PROPELLER: EntityDataAccessor<Boolean> =
            SynchedEntityData.defineId(ArgonautEntity::class.java, EntityDataSerializers.BOOLEAN)
    }
}