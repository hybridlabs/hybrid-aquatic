package dev.hybridlabs.aquatic.entity.misc

import com.mojang.serialization.Codec
import dev.hybridlabs.aquatic.item.HAItems
import dev.hybridlabs.aquatic.world.inventory.ArgonautMenu
import net.minecraft.core.Direction
import net.minecraft.core.NonNullList
import net.minecraft.core.component.DataComponents
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.protocol.game.ServerboundPaddleBoatPacket
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.resources.ResourceKey
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
import net.minecraft.world.inventory.ContainerData
import net.minecraft.world.item.DyeColor
import net.minecraft.world.item.DyeItem
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.component.CustomData
import net.minecraft.world.level.GameRules
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity
import net.minecraft.world.level.gameevent.GameEvent
import net.minecraft.world.level.storage.loot.BuiltInLootTables
import net.minecraft.world.level.storage.loot.LootTable
import net.minecraft.world.phys.Vec2
import net.minecraft.world.phys.Vec3
import org.joml.Vector3f
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.animation.AnimatableManager
import software.bernie.geckolib.animation.AnimationController
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.util.GeckoLibUtil
import java.util.function.IntFunction

open class ArgonautEntity(
    type: EntityType<out ArgonautEntity>,
    world: Level,
) :
    Entity(type, world), HasCustomInventoryScreen, ContainerEntity,
    GeoEntity {
    private val animCache = GeckoLibUtil.createInstanceCache(this)
    private var itemStacks: NonNullList<ItemStack> = NonNullList.withSize(28, ItemStack.EMPTY)
    private var argonautLootTable: ResourceKey<LootTable> = BuiltInLootTables.EMPTY
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

    private var litTime = 0
    private var litDuration = 0
    private val dataAccess = object : ContainerData {
        override fun get(index: Int): Int {
            return when (index) {
                0 -> litTime
                1 -> litDuration
                else -> 0
            }
        }

        override fun set(index: Int, value: Int) {
            when (index) {
                0 -> litTime = value
                1 -> litDuration = value
            }
        }

        override fun getCount(): Int {
            return 2
        }
    }

    //#region Data
    override fun defineSynchedData(builder: SynchedEntityData.Builder) {
        builder.define(DATA_ID_HURT, 0)
        builder.define(DATA_ID_HURTDIR, 1)
        builder.define(DATA_ID_DAMAGE, 0.0f)
        builder.define(DATA_ID_RIGHT_PROPELLER, false)
        builder.define(DATA_ID_LEFT_PROPELLER, false)
        builder.define(DATA_ID_GLOWING, false)
        builder.define(SHELL_COLOR, ShellColor.NONE.id)
        builder.define(SAIL_COLOR, SailColor.NONE.id)
        builder.define(IS_BURNING, false)
    }

    override fun addAdditionalSaveData(tag: CompoundTag) {
        tag.putFloat("Damage", getDamage())
        tag.putBoolean("IsGlowing", isGlowing())
        tag.putString("ShellColor", this.getShellColor().serializedName)
        tag.putString("SailColor", this.getSailColor().serializedName)
        tag.putInt("BurnTime", this.litTime)
        this.addChestVehicleSaveData(tag, this.registryAccess())
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

        this.litTime = tag.getInt("BurnTime")
        setLit(litTime > 0)
        this.readChestVehicleSaveData(tag, this.registryAccess())
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

    override fun lerpTo(x: Double, y: Double, z: Double, pitch: Float, xRot: Float, posRotationIncrements: Int) {
        this.lerpX = x
        this.lerpY = y
        this.lerpZ = z
        this.lerpYRot = pitch.toDouble()
        this.lerpXRot = xRot.toDouble()
        this.lerpSteps = 10
    }

    open fun getBurnDuration(fuel: ItemStack): Int {
        return if (fuel.isEmpty) 0
        else AbstractFurnaceBlockEntity.getFuel().getOrDefault(fuel.item, 0) * 2
    }

    fun isLit(): Boolean {
        return entityData.get(IS_BURNING)
    }

    fun setLit(value: Boolean) {
        this.entityData.set(IS_BURNING, value)
    }

    fun burnTick() {
        val fuelItemStack = itemStacks[0]

        if (litTime > 0) {
            litTime--
            if (litTime == 0) setLit(false)
            return
        }
        if (fuelItemStack.isEmpty) return

        litTime = getBurnDuration(fuelItemStack)
        if (litTime <= 0) return

        litDuration = litTime

        val itemRemainder = fuelItemStack.item.craftingRemainingItem
        fuelItemStack.shrink(1)
        if (fuelItemStack.isEmpty && itemRemainder != null) itemStacks[0] = itemRemainder.defaultInstance

        setLit(true)
    }

    override fun tick() {
        super.tick()

        burnTick()

        val passenger = this.firstPassenger
        if (passenger is LivingEntity) {
            passenger.airSupply = passenger.maxAirSupply
        }

        if (passenger is Player) {
            tickRidden(passenger, passenger.deltaMovement)
        }

        tickLerp()
        if (this.isControlledByLocalInstance) {
            if (this.firstPassenger !is Player) {
                setPropellerState(left = false, right = false)
            }

            floatArgonaut()

            if (this.level().isClientSide) {
                if (isLit()) controlArgonaut()

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

    private fun tickLerp() {
        if (this.isControlledByLocalInstance) {
            this.lerpSteps = 0
            this.syncPacketPositionCodec(this.x, this.y, this.z)
        }

        if (this.lerpSteps > 0) {
            val d = this.x + (this.lerpX - this.x) / this.lerpSteps
            val e = this.y + (this.lerpY - this.y) / this.lerpSteps
            val f = this.z + (this.lerpZ - this.z) / this.lerpSteps
            val g = Mth.wrapDegrees(this.lerpYRot - this.yRot)
            this.yRot += g.toFloat() / this.lerpSteps
            this.xRot += (this.lerpXRot - this.xRot).toFloat() / this.lerpSteps
            this.lerpSteps--
            this.setPos(d, e, f)
            this.setRot(this.yRot, this.xRot)
        }
    }

    fun tickRidden(player: Player, travelVector: Vec3) {
        val vec2 = getRiddenRotation(player)

        val pitch = if (this.onGround() && !this.isInWater) 0f else vec2.x

        setRot(vec2.y, pitch)

        yRotO = yRot
    }

    fun getRiddenRotation(entity: LivingEntity): Vec2 {
        return Vec2(entity.xRot, entity.yRot)
    }

    private fun floatArgonaut() {
        if (this.isInWater) {
            val waterFriction = 0.96f

            this.deltaMovement =
                deltaMovement.multiply(waterFriction.toDouble(), waterFriction.toDouble(), waterFriction.toDouble())
            this.deltaRotation *= waterFriction
        } else {
            val groundFriction = 0.9

            if (!this.isNoGravity) {
                this.deltaMovement = deltaMovement.multiply(groundFriction, 0.0, groundFriction) //ground friction
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
        if (this.hasPassenger(source.entity)) {
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

    fun getArgonautItem(argonaut: ArgonautEntity): ItemStack {
        val stack = ItemStack(HAItems.ARGONAUT.get())
        val tag = CompoundTag().apply {
            putInt("ShellColor", argonaut.getShellColor().id)
            putInt("SailColor", argonaut.getSailColor().id)
            putBoolean("Glowing", argonaut.isGlowing())
        }
        stack.set(DataComponents.CUSTOM_DATA, CustomData.of(tag))
        return stack
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


    override fun getPassengerAttachmentPoint(entity: Entity, dimensions: EntityDimensions, partialTick: Float): Vec3 {
        return super.getPassengerAttachmentPoint(entity, dimensions, partialTick).add(0.0,0.65,0.0);
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

        //#region Add Glow
        if (stack.`is`(HAItems.GLOWSLIME.get()) && !this.isGlowing()) {
            if (!player.abilities.instabuild) stack.shrink(1)
            this.setGlowing(true)
            return InteractionResult.sidedSuccess(this.level().isClientSide)
        }

        //#region Remove Glow
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
                this.y + this.getPassengerRidingPosition(passenger).y,
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
        val stack = getArgonautItem(this)
        this.spawnAtLocation(stack)
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

    override fun getLootTable(): ResourceKey<LootTable> {
        return argonautLootTable
    }

    override fun setLootTable(id: ResourceKey<LootTable>?) {
        if (id != null) argonautLootTable = id
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
        return 28
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
        if (this.lootTable == BuiltInLootTables.EMPTY && player.isSpectator) {
            return null
        } else {
            this.unpackLootTable(playerInventory.player)
            return ArgonautMenu.threeRows(containerId, playerInventory, this, this.dataAccess)
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

        private val SHELL_COLOR: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(ArgonautEntity::class.java, EntityDataSerializers.INT)

        private val SAIL_COLOR: EntityDataAccessor<Int> =
            SynchedEntityData.defineId(ArgonautEntity::class.java, EntityDataSerializers.INT)

        private val IS_BURNING: EntityDataAccessor<Boolean> =
            SynchedEntityData.defineId(ArgonautEntity::class.java, EntityDataSerializers.BOOLEAN)
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