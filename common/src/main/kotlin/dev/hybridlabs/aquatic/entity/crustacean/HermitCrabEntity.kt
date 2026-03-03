package dev.hybridlabs.aquatic.entity.crustacean

import net.minecraft.nbt.CompoundTag
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.util.ByIdMap
import net.minecraft.util.StringRepresentable
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.*
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.core.`object`.PlayState
import java.util.function.IntFunction

@Suppress("DEPRECATION")
class HermitCrabEntity(entityType: EntityType<out HybridAquaticCrustaceanEntity>, world: Level) :
    HybridAquaticCrustaceanEntity(entityType, world, false),
    VariantHolder<HermitCrabEntity.Companion.Type> {
    private var isHiding: Boolean = false
    private var hidingTimer: Int = 0
    private var lastDamageTime: Long = 0

    //#region Shells & Items
    override fun finalizeSpawn(
        world: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnReason: MobSpawnType,
        entityData: SpawnGroupData?,
        entityNbt: CompoundTag?,
    ): SpawnGroupData? {

        val roll = random.nextFloat()

        val heldItem = when {
            roll < 0.60f -> Type.SHELL
            roll < 0.85f -> Type.SKULL
            else -> Type.NONE
        }

        variant = heldItem

        val stack = when (heldItem) {
            Type.SHELL -> ItemStack(Items.NAUTILUS_SHELL)
            Type.SKULL -> ItemStack(Items.SKELETON_SKULL)
            Type.NONE -> ItemStack.EMPTY
            else -> ItemStack.EMPTY
        }

        setItemSlot(EquipmentSlot.MAINHAND, stack)

        return super.finalizeSpawn(world, difficulty, spawnReason, entityData, entityNbt)
    }

    private fun variantFromItem(stack: ItemStack): Type? {
        return when {
            stack.isEmpty -> Type.NONE
            stack.item == Items.NAUTILUS_SHELL -> Type.SHELL
            stack.item == Items.SKELETON_SKULL -> Type.SKULL
            stack.item == Items.WITHER_SKELETON_SKULL -> Type.WITHER_SKULL
            else -> null
        }
    }

    override fun canTakeItem(stack: ItemStack): Boolean {
        return variantFromItem(stack) != null
    }

    override fun mobInteract(player: Player, hand: InteractionHand): InteractionResult {
        val playerStack = player.getItemInHand(hand)
        val newType = variantFromItem(playerStack)

        if (newType != null) {
            if (!level().isClientSide) {

                val oldStack = getItemBySlot(EquipmentSlot.MAINHAND)

                setItemSlot(EquipmentSlot.MAINHAND, ItemStack(playerStack.item))
                variant = newType

                if (!player.abilities.instabuild) {
                    playerStack.shrink(1)
                }

                if (!oldStack.isEmpty) {
                    if (!player.addItem(oldStack)) {
                        player.drop(oldStack, false)
                    }
                }
            }

            return InteractionResult.sidedSuccess(level().isClientSide)
        }
        return super.mobInteract(player, hand)
    }

    override fun dropCustomDeathLoot(source: DamageSource, looting: Int, causedByPlayer: Boolean) {
        val held = getItemBySlot(EquipmentSlot.MAINHAND)
        if (!held.isEmpty) {
            spawnAtLocation(held)
            setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY)
            variant = Type.NONE
        }
    }
    //#endregion

    //#region Data
    override fun defineSynchedData() {
        entityData.define(TYPE, 0)
        super.defineSynchedData()
    }

    override fun addAdditionalSaveData(nbt: CompoundTag) {
        nbt.putString("Type", this.variant.serializedName)
        super.addAdditionalSaveData(nbt)
    }

    override fun readAdditionalSaveData(nbt: CompoundTag) {
        this.variant = Type.byName(nbt.getString("Type"))
        super.readAdditionalSaveData(nbt)
    }
    //#endregion

    //#region Properties
    override fun getMaxSize() : Int {
        return 5
    }

    override fun getMinSize(): Int {
        return -5
    }

    override fun getVariant(): Type {
        return Type.fromId((entityData.get(TYPE) as Int))
    }

    override fun setVariant(type: Type) {
        entityData.set(TYPE, type.id)
    }
    //#endregion

    //#region Hiding
    private fun startHiding() {
        if (variant == Type.NONE) return

        isHiding = true
        hidingTimer = 200

        attributes.getInstance(Attributes.MOVEMENT_SPEED)?.baseValue = 0.0
        attributes.getInstance(Attributes.ARMOR)?.baseValue = 50.0
    }

    override fun tick() {
        super.tick()

        if (isHiding && variant == Type.NONE) {
            isHiding = false
            attributes.getInstance(Attributes.MOVEMENT_SPEED)?.baseValue = 0.3
            attributes.getInstance(Attributes.ARMOR)?.baseValue = 5.0
            return
        }

        if (isHiding) {
            hidingTimer--

            if (hidingTimer <= 0 && (level().gameTime - lastDamageTime) >= 200) {
                isHiding = false
                attributes.getInstance(Attributes.MOVEMENT_SPEED)?.baseValue = 0.3
                attributes.getInstance(Attributes.ARMOR)?.baseValue = 5.0
            }
        }
    }

    override fun hurt(source: DamageSource, amount: Float): Boolean {
        if (!isHiding && variant != Type.NONE) {
            startHiding()
        }

        lastDamageTime = level().gameTime

        return super.hurt(source, amount)
    }
    //#endregion

    //#region Animations
    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(
            DefaultAnimations.genericWalkIdleController(this)
        )
        controllerRegistrar.add(
            AnimationController(this, "Hide", 4,
                AnimationController.AnimationStateHandler { state: AnimationState<HybridAquaticCrustaceanEntity> ->
                    if (this.isHiding) {
                        return@AnimationStateHandler state.setAndContinue(HIDE_ANIMATION)
                    } else {
                        PlayState.STOP
                    }
                }
            )
        )
    }
    //#endregion

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 3.0)
                .add(Attributes.MOVEMENT_SPEED, 0.3)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 4.0)
                .add(Attributes.ARMOR, 5.0)
                .add(Attributes.ARMOR_TOUGHNESS, 5.0)
        }
        val TYPE: EntityDataAccessor<Int> = SynchedEntityData.defineId(HermitCrabEntity::class.java, EntityDataSerializers.INT)

        enum class Type(val id: Int, private val key: String) : StringRepresentable {
            NONE(0, "none"),
            SHELL(1, "shell"),
            SKULL(2, "skull"),
            WITHER_SKULL(3, "skull");

            override fun getSerializedName(): String {
                return this.key
            }

            companion object {
                val CODEC: StringRepresentable.EnumCodec<Type> = StringRepresentable.fromEnum { entries.toTypedArray() }
                private val BY_ID: IntFunction<Type> = ByIdMap.continuous(
                    { obj: Type -> obj.id },
                    entries.toTypedArray(),
                    ByIdMap.OutOfBoundsStrategy.ZERO
                )

                fun byName(name: String?): Type {
                    return CODEC.byName(name, SHELL) as Type
                }

                fun fromId(id: Int): Type {
                    return BY_ID.apply(id) as Type
                }
            }
        }
    }
}