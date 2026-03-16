package dev.hybridlabs.aquatic.entity.misc

import com.mojang.serialization.Codec
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import net.minecraft.core.Direction
import net.minecraft.core.NonNullList
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.protocol.game.ServerboundPaddleBoatPacket
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.resources.ResourceLocation
import net.minecraft.util.ByIdMap
import net.minecraft.util.Mth
import net.minecraft.util.StringRepresentable
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
import net.minecraft.world.item.*
import net.minecraft.world.level.GameRules
import net.minecraft.world.level.Level
import net.minecraft.world.level.gameevent.GameEvent
import net.minecraft.world.phys.Vec2
import net.minecraft.world.phys.Vec3
import org.joml.Vector3f
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.util.GeckoLibUtil
import java.util.function.IntFunction

open class ArgonautEntity(
    type: EntityType<out ArgonautEntity>,
    world: Level,
) :
    Entity(type, world), PlayerRideable, HasCustomInventoryScreen, ContainerEntity,
    GeoEntity {
    private val animCache = GeckoLibUtil.createInstanceCache(this)
    private var itemStacks: NonNullList<ItemStack> = NonNullList.withSize(27, ItemStack.EMPTY)
    private var argonautLootTable: ResourceLocation? = null
    private var argonautLootTableSeed: Long = 0
    private var inputLeft = false
    private var inputRight = false
    private var inputUp = false
    private var inputDown = false
    private var inputJumping = false
    private var inputSprint = false
    private var deltaRotation = 0f
    private var lerpSteps = 0
    private var lerpX = 0.0
    private var lerpY = 0.0
    private var lerpZ = 0.0
    private var lerpYRot = 0.0
    private var lerpXRot = 0.0

    //#region Data
    override fun defineSynchedData() {
        this.entityData.define(DATA_ID_HURT, 0)
        this.entityData.define(DATA_ID_HURTDIR, 1)
        this.entityData.define(DATA_ID_DAMAGE, 0.0f)
        this.entityData.define(DATA_ID_RIGHT_PROPELLER, false)
        this.entityData.define(DATA_ID_LEFT_PROPELLER, false)
        this.entityData.define(DATA_ID_GLOWING, false)
        this.entityData.define(SHELL_COLOR, ShellColor.NONE.id)
        this.entityData.define(SAIL_COLOR, SailColor.NONE.id)
    }

    override fun addAdditionalSaveData(tag: CompoundTag) {
        tag.putFloat("Damage", getDamage())
        tag.putBoolean("IsGlowing", isGlowing())
        tag.putString("ShellColor", this.getShellColor().serializedName)
        tag.putString("SailColor", this.getSailColor().serializedName)
        this.addChestVehicleSaveData(tag)
    }

    override fun readAdditionalSaveData(tag: CompoundTag) {
        setDamage(tag.getFloat("Damage"))
        setGlowing(tag.getBoolean("IsGlowing"))

        if (tag.contains("ShellColor", 8)) {
            val colorName = tag.getString("ShellColor")
            val color = ShellColor.entries.firstOrNull { it.serializedName == colorName } ?: ShellColor.NONE
            setShellColor(color)
        }

        if (tag.contains("SailColor", 8)) {
            val colorName = tag.getString("SailColor")
            val color = SailColor.entries.firstOrNull { it.serializedName == colorName } ?: SailColor.NONE
            setSailColor(color)
        }

        this.readChestVehicleSaveData(tag)
    }
    //#endregion

    open fun getShellColor(): ShellColor {
        return ShellColor.byId(entityData.get(SHELL_COLOR))
    }

    open fun setShellColor(shellColor: ShellColor) {
        entityData.set(SHELL_COLOR, shellColor.id)
    }

    open fun getSailColor(): SailColor {
        return SailColor.byId((entityData.get(SAIL_COLOR) as Int))
    }

    open fun setSailColor(sailColor: SailColor) {
        entityData.set(SAIL_COLOR, sailColor.id)
    }

    override fun getEyeHeight(pose: Pose, size: EntityDimensions): Float {
        return size.height * 0.5f
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

        val passenger = this.firstPassenger
        if (passenger is LivingEntity) {
            passenger.airSupply = passenger.maxAirSupply
        }

        if (passenger is Player) {
            tickRidden(passenger, passenger.deltaMovement)
        }

        if (this.isControlledByLocalInstance) {
            if (this.firstPassenger !is Player) {
                setPropellerState(left = false, right = false)
            }

            floatArgonaut()

            if (this.level().isClientSide) {
                controlArgonaut()

                this.level().sendPacketToServer(
                    ServerboundPaddleBoatPacket(
                        getPropellerState(0),
                        getPropellerState(1)
                    )
                )
            }

            this.move(MoverType.SELF, this.deltaMovement)
        } else {
            this.deltaMovement = Vec3.ZERO
        }
    }

    open fun tickRidden(player: Player, travelVector: Vec3) {
        val vec2 = getRiddenRotation(player)

        val pitch = if (this.onGround() && !this.isInWater) 0f else vec2.x

        setRot(vec2.y, pitch)

        yRotO = yRot
    }

    protected open fun getRiddenRotation(entity: LivingEntity): Vec2 {
        return Vec2(entity.xRot, entity.yRot)
    }

    private fun floatArgonaut() {
        if (this.isInWater) {
            val motion = this.deltaMovement
            val waterFriction = 0.96f
            this.deltaMovement = Vec3(
                motion.x * waterFriction,
                motion.y * waterFriction,
                motion.z * waterFriction
            )
            this.deltaRotation *= waterFriction
        } else {
            if (!this.isNoGravity) {
                this.deltaMovement = this.deltaMovement.add(0.0, -0.04, 0.0)
            }
            setPropellerState(left = false, right = false)
        }
    }

    private fun getRightDirection(): Vector3f {
        val rad = Math.PI.toFloat() / 180f
        return Vector3f(
            Mth.cos(-yRot * rad),
            0f,
            Mth.sin(yRot * rad)
        ).normalize()
    }

    private fun controlArgonaut() {

        if (!this.isInWater || !this.isVehicle || this.onGround()) return
        var forwardMovement = 0.0f
        var horizontalMovement = 0.0f
        var verticalMovement = 0.0f

        if (this.inputRight != this.inputLeft && !this.inputUp && !this.inputDown) {
            forwardMovement += 0.005f
        }

        if (this.inputUp) {
            forwardMovement += 0.02f
        }

        if (this.inputDown) {
            forwardMovement -= 0.015f
        }

        if (this.inputRight) {
            horizontalMovement -= 0.02f
        }

        if (this.inputLeft) {
            horizontalMovement += 0.02f
        }

        if (this.inputJumping) {
            verticalMovement += 0.015f
        }

        if (this.inputSprint) {
            verticalMovement -= 0.015f
        }

        val lookDirection = this.lookAngle
        val rightDirection = getRightDirection()

        this.deltaMovement = this.deltaMovement.add(
            lookDirection.x * forwardMovement + rightDirection.x * horizontalMovement,
            lookDirection.y * forwardMovement + verticalMovement,
            lookDirection.z * forwardMovement + rightDirection.z * horizontalMovement
        )

        this.setPropellerState(
            this.inputRight && !this.inputLeft || this.inputUp,
            this.inputLeft && !this.inputRight || this.inputUp
        )
    }

    fun setInput(
        inputLeft: Boolean,
        inputRight: Boolean,
        inputUp: Boolean,
        inputDown: Boolean,
        inputJumping: Boolean,
        inputSprint: Boolean,
    ) {
        this.inputLeft = inputLeft
        this.inputRight = inputRight
        this.inputUp = inputUp
        this.inputDown = inputDown
        this.inputJumping = inputJumping
        this.inputSprint = inputSprint
    }

    fun setPropellerState(left: Boolean, right: Boolean) {
        this.entityData.set(DATA_ID_LEFT_PROPELLER, left)
        this.entityData.set(DATA_ID_RIGHT_PROPELLER, right)
    }

    fun getPropellerState(side: Int): Boolean {
        return this.entityData.get(if (side == 0) DATA_ID_LEFT_PROPELLER else DATA_ID_RIGHT_PROPELLER) && this.getControllingPassenger() != null
    }

    fun setGlowing(glowing: Boolean) {
        this.entityData.set(DATA_ID_GLOWING, glowing)
    }

    fun isGlowing(): Boolean {
        return this.entityData.get(DATA_ID_GLOWING)
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
            if (flag || this.getDamage() > 90.0f) {
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
        return HybridAquaticItems.ARGONAUT.get()
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
            AnimationController(this, "Argonaut Controller", 4) { state ->
                val moving = this.deltaMovement.horizontalDistanceSqr() > 0.01

                when {
                    isInWater && moving -> {
                        state.setAndContinue(DefaultAnimations.SWIM)
                    }

                    else -> {
                        state.setAndContinue(DefaultAnimations.IDLE)
                    }
                }
            }
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
        val stack = player.getItemInHand(hand)

        //#region Glow
        if (stack.`is`(HybridAquaticItems.GLOWSLIME.get()) && !this.isGlowing()) {
            if (!player.abilities.instabuild) stack.shrink(1)
            this.setGlowing(true)
            return InteractionResult.sidedSuccess(this.level().isClientSide)
        }

        if (stack.`is`(Items.SLIME_BALL) && this.isGlowing()) {
            if (!player.abilities.instabuild) stack.shrink(1)
            this.setGlowing(false)
            return InteractionResult.sidedSuccess(this.level().isClientSide)
        }

        //#region Sail Color
        if (stack.item is DyeItem && player.isCrouching) {
            val dye = (stack.item as DyeItem).dyeColor
            val sailColor = SailColor.fromDye(dye)
            if (sailColor != getSailColor()) {
                setSailColor(sailColor)
                if (!player.abilities.instabuild) stack.shrink(1)
                return InteractionResult.sidedSuccess(level().isClientSide)
            }
        }

        //#region Shell Color
        if (stack.item is DyeItem) {
            val dye = (stack.item as DyeItem).dyeColor
            val shellColor = ShellColor.fromDye(dye)
            if (shellColor != getShellColor()) {
                setShellColor(shellColor)
                if (!player.abilities.instabuild) stack.shrink(1)
                return InteractionResult.sidedSuccess(level().isClientSide)
            }
        }

        //#region Riding
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
        return 27
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
            return ChestMenu.threeRows(containerId, playerInventory, this)
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

        private val DATA_ID_GLOWING: EntityDataAccessor<Boolean> =
            SynchedEntityData.defineId(ArgonautEntity::class.java, EntityDataSerializers.BOOLEAN)

        val SHELL_COLOR: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(ArgonautEntity::class.java, EntityDataSerializers.INT)

        val SAIL_COLOR: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(ArgonautEntity::class.java, EntityDataSerializers.INT)

    }

    enum class ShellColor(val id: Int, val key: String) : StringRepresentable {
        NONE(0, ""),
        WHITE(1, "white"),
        ORANGE(2, "orange"),
        MAGENTA(3, "magenta"),
        LIGHT_BLUE(4, "light_blue"),
        YELLOW(5, "yellow"),
        LIME(6, "lime"),
        PINK(7, "pink"),
        GRAY(8, "gray"),
        LIGHT_GRAY(9, "light_gray"),
        CYAN(10, "cyan"),
        PURPLE(11, "purple"),
        BLUE(12, "blue"),
        BROWN(13, "brown"),
        GREEN(14, "green"),
        RED(15, "red"),
        BLACK(16, "black");

        override fun getSerializedName(): String {
            return this.key
        }

        companion object {
            val CODEC: Codec<ShellColor> =
                StringRepresentable.fromEnum { ShellColor.entries.toTypedArray() }

            val BY_ID: IntFunction<ShellColor> = ByIdMap.continuous(
                { color -> color.id },
                ShellColor.entries.toTypedArray(),
                ByIdMap.OutOfBoundsStrategy.WRAP
            )

            fun byId(id: Int): ShellColor {
                return BY_ID.apply(id)
            }

            fun fromDye(dye: DyeColor): ShellColor {
                return when (dye) {
                    DyeColor.WHITE -> WHITE
                    DyeColor.ORANGE -> ORANGE
                    DyeColor.MAGENTA -> MAGENTA
                    DyeColor.LIGHT_BLUE -> LIGHT_BLUE
                    DyeColor.YELLOW -> YELLOW
                    DyeColor.LIME -> LIME
                    DyeColor.PINK -> PINK
                    DyeColor.GRAY -> GRAY
                    DyeColor.LIGHT_GRAY -> LIGHT_GRAY
                    DyeColor.CYAN -> CYAN
                    DyeColor.PURPLE -> PURPLE
                    DyeColor.BLUE -> BLUE
                    DyeColor.BROWN -> BROWN
                    DyeColor.GREEN -> GREEN
                    DyeColor.RED -> RED
                    DyeColor.BLACK -> BLACK
                }
            }
        }
    }

    enum class SailColor(val id: Int, val key: String) : StringRepresentable {
        NONE(0, ""),
        WHITE(1, "white"),
        ORANGE(2, "orange"),
        MAGENTA(3, "magenta"),
        LIGHT_BLUE(4, "light_blue"),
        YELLOW(5, "yellow"),
        LIME(6, "lime"),
        PINK(7, "pink"),
        GRAY(8, "gray"),
        LIGHT_GRAY(9, "light_gray"),
        CYAN(10, "cyan"),
        PURPLE(11, "purple"),
        BLUE(12, "blue"),
        BROWN(13, "brown"),
        GREEN(14, "green"),
        RED(15, "red"),
        BLACK(16, "black");

        override fun getSerializedName(): String {
            return this.key
        }

        companion object {
            val CODEC: Codec<SailColor> =
                StringRepresentable.fromEnum { SailColor.entries.toTypedArray() }

            val BY_ID: IntFunction<SailColor> = ByIdMap.continuous(
                { color -> color.id },
                SailColor.entries.toTypedArray(),
                ByIdMap.OutOfBoundsStrategy.WRAP
            )

            fun byId(id: Int): SailColor {
                return BY_ID.apply(id)
            }

            fun fromDye(dye: DyeColor): SailColor {
                return when (dye) {
                    DyeColor.WHITE -> WHITE
                    DyeColor.ORANGE -> ORANGE
                    DyeColor.MAGENTA -> MAGENTA
                    DyeColor.LIGHT_BLUE -> LIGHT_BLUE
                    DyeColor.YELLOW -> YELLOW
                    DyeColor.LIME -> LIME
                    DyeColor.PINK -> PINK
                    DyeColor.GRAY -> GRAY
                    DyeColor.LIGHT_GRAY -> LIGHT_GRAY
                    DyeColor.CYAN -> CYAN
                    DyeColor.PURPLE -> PURPLE
                    DyeColor.BLUE -> BLUE
                    DyeColor.BROWN -> BROWN
                    DyeColor.GREEN -> GREEN
                    DyeColor.RED -> RED
                    DyeColor.BLACK -> BLACK
                }
            }
        }
    }
}