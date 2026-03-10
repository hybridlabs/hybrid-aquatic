package dev.hybridlabs.aquatic.entity.misc

import net.minecraft.core.Direction
import net.minecraft.core.NonNullList
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.protocol.game.ServerboundPaddleBoatPacket
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.Mth
import net.minecraft.world.Containers
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.*
import net.minecraft.world.entity.player.Inventory
import net.minecraft.world.entity.player.Player
import net.minecraft.world.entity.vehicle.ContainerEntity
import net.minecraft.world.inventory.AbstractContainerMenu
import net.minecraft.world.inventory.ChestMenu
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.GameRules
import net.minecraft.world.level.Level
import net.minecraft.world.level.gameevent.GameEvent
import net.minecraft.world.phys.Vec3
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.util.GeckoLibUtil

open class ArgonautEntity(
    type: EntityType<out ArgonautEntity>,
    world: Level,
) :
    Entity(type, world), PlayerRideable, HasCustomInventoryScreen, ContainerEntity,
    GeoEntity {
    private val animCache = GeckoLibUtil.createInstanceCache(this)
    private var itemStacks: NonNullList<ItemStack> = NonNullList.withSize(54, ItemStack.EMPTY)
    private var argonautLootTable: ResourceLocation? = null
    private var argonautLootTableSeed: Long = 0
    private var inputLeft = false
    private var inputRight = false
    private var inputUp = false
    private var inputDown = false
    private var deltaRotation = 0f
    private var lerpSteps = 0
    private var lerpX = 0.0
    private var lerpY = 0.0
    private var lerpZ = 0.0
    private var lerpYRot = 0.0
    private var lerpXRot = 0.0

    override fun defineSynchedData() {
        this.entityData.define(DATA_ID_HURT, 0)
        this.entityData.define(DATA_ID_HURTDIR, 1)
        this.entityData.define(DATA_ID_DAMAGE, 0.0f)
        this.entityData.define(DATA_ID_RIGHT_PROPELLER, false)
        this.entityData.define(DATA_ID_LEFT_PROPELLER, false)
        this.entityData.define(DATA_ID_BACK_PROPELLER, false)
    }

    override fun lerpTo(
        x: Double,
        y: Double,
        z: Double,
        yaw: Float,
        pitch: Float,
        posRotationIncrements: Int,
        teleport: Boolean,
    ) {
        this.lerpX = x
        this.lerpY = y
        this.lerpZ = z
        this.lerpYRot = yaw.toDouble()
        this.lerpXRot = pitch.toDouble()
        this.lerpSteps = 10
    }

    override fun tick() {
        super.tick()

        this.move(MoverType.SELF, this.deltaMovement)

        val passenger = this.firstPassenger
        if (passenger is LivingEntity) {
            passenger.airSupply = passenger.maxAirSupply
        }

        if (this.isControlledByLocalInstance) {
            if (this.firstPassenger !is Player) {
                this.setPropellerState(left = false, right = false)
            }

            this.floatArgonaut()
            if (this.level().isClientSide) {
                this.controlArgonaut()
                this.level()
                    .sendPacketToServer(
                        ServerboundPaddleBoatPacket(
                            this.getPropellerState(0),
                            this.getPropellerState(1)
                        )
                    )
            }

            this.move(MoverType.SELF, this.deltaMovement)
        } else {
            this.deltaMovement = Vec3.ZERO
        }
    }

    private fun floatArgonaut() {
        if (this.isInWater) {
            val motion = this.deltaMovement

            val waterFriction = 0.9f

            this.deltaMovement = Vec3(
                motion.x * waterFriction,
                0.0,
                motion.z * waterFriction
            )

            this.deltaRotation *= waterFriction
        } else {
            if (!this.isNoGravity) {
                this.deltaMovement = this.deltaMovement.add(0.0, -0.04, 0.0)
            }
        }
    }

    private fun controlArgonaut() {
        if (this.isVehicle) {
            var f = 0.0f
            if (this.inputLeft) {
                --this.deltaRotation
            }

            if (this.inputRight) {
                ++this.deltaRotation
            }

            if (this.inputRight != this.inputLeft && !this.inputUp && !this.inputDown) {
                f += 0.005f
            }

            this.yRot += this.deltaRotation
            if (this.inputUp) {
                f += 0.04f
            }

            if (this.inputDown) {
                f -= 0.005f
            }

            this.deltaMovement = this.deltaMovement.add(
                (Mth.sin(-this.yRot * (Math.PI.toFloat() / 180f)) * f).toDouble(),
                0.0,
                (Mth.cos(this.yRot * (Math.PI.toFloat() / 180f)) * f).toDouble()
            )
            this.setPropellerState(
                this.inputRight && !this.inputLeft || this.inputUp,
                this.inputLeft && !this.inputRight || this.inputUp
            )
        }
    }

    fun setInput(inputLeft: Boolean, inputRight: Boolean, inputUp: Boolean, inputDown: Boolean) {
        this.inputLeft = inputLeft
        this.inputRight = inputRight
        this.inputUp = inputUp
        this.inputDown = inputDown
    }

    fun setPropellerState(left: Boolean, right: Boolean) {
        this.entityData.set(DATA_ID_LEFT_PROPELLER, left)
        this.entityData.set(DATA_ID_RIGHT_PROPELLER, right)
    }

    fun getPropellerState(side: Int): Boolean {
        return this.entityData.get(if (side == 0) DATA_ID_LEFT_PROPELLER else DATA_ID_RIGHT_PROPELLER) && this.getControllingPassenger() != null
    }

    override fun addAdditionalSaveData(tag: CompoundTag) {
        tag.putFloat("Damage", getDamage())
        this.addChestVehicleSaveData(tag)
    }

    override fun readAdditionalSaveData(tag: CompoundTag) {
        setDamage(tag.getFloat("Damage"))
        this.readChestVehicleSaveData(tag)
    }

    override fun hurt(source: DamageSource, amount: Float): Boolean {
        if (source.entity != null && this.hasPassenger(source.entity)) {
            return false
        }

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

    override fun isPickable(): Boolean {
        return !this.isRemoved
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

    //#region Container
    protected open fun destroy(damageSource: DamageSource) {
        this.spawnAtLocation(this.getDropItem())
        this.chestVehicleDestroyed(damageSource, this.level(), this)
    }

    override fun remove(reason: RemovalReason) {
        if (!this.level().isClientSide && reason.shouldDestroy()) {
            Containers.dropContents(this.level(), this, this)
        }
        super.remove(reason)
    }

    override fun openCustomInventoryScreen(player: Player) {
        player.openMenu(this)
        if (!player.level().isClientSide) {
            this.gameEvent(GameEvent.CONTAINER_OPEN, player)
        }
    }

    override fun getLootTable(): ResourceLocation? {
        return argonautLootTable
    }

    override fun setLootTable(id: ResourceLocation?) {
        argonautLootTable = id
    }

    override fun getLootTableSeed(): Long {
        return argonautLootTableSeed
    }

    override fun setLootTableSeed(seed: Long) {
        argonautLootTableSeed = seed
    }

    override fun getItemStacks(): NonNullList<ItemStack> {
        return this.itemStacks
    }

    override fun clearItemStacks() {
        this.itemStacks = NonNullList.withSize(this.containerSize, ItemStack.EMPTY)
    }

    override fun getContainerSize(): Int {
        return 54
    }

    override fun getItem(slot: Int): ItemStack {
        return this.getChestVehicleItem(slot)
    }

    override fun removeItem(slot: Int, amount: Int): ItemStack {
        return this.removeChestVehicleItem(slot, amount)
    }

    override fun removeItemNoUpdate(slot: Int): ItemStack {
        return this.removeChestVehicleItemNoUpdate(slot)
    }

    override fun setItem(slot: Int, stack: ItemStack) {
        this.setChestVehicleItem(slot, stack)
    }

    override fun setChanged() {}

    override fun stillValid(player: Player): Boolean {
        return this.isChestVehicleStillValid(player)
    }

    override fun clearContent() {
        this.clearChestVehicleContent()
    }

    override fun createMenu(
        containerId: Int,
        playerInventory: Inventory,
        player: Player,
    ): AbstractContainerMenu? {
        if (this.lootTable != null && player.isSpectator) {
            return null
        } else {
            this.unpackLootTable(playerInventory.player)
            return ChestMenu.sixRows(containerId, playerInventory, this)
        }
    }

    fun unpackLootTable(player: Player?) {
        this.unpackChestVehicleLootTable(player)
    }

    override fun stopOpen(player: Player) {
        this.level().gameEvent(GameEvent.CONTAINER_CLOSE, this.position(), GameEvent.Context.of(player))
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