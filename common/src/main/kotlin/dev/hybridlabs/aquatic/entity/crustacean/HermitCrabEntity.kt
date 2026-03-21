package dev.hybridlabs.aquatic.entity.crustacean

import dev.hybridlabs.aquatic.entity.ai.goal.FleeFromEntityGoal
import dev.hybridlabs.aquatic.entity.misc.SmallTNTEntity
import dev.hybridlabs.aquatic.item.HybridAquaticItems
import dev.hybridlabs.aquatic.tag.HybridAquaticItemTags
import net.minecraft.core.Vec3i
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.nbt.CompoundTag
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobSpawnType
import net.minecraft.world.entity.SpawnGroupData
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.item.ItemEntity
import net.minecraft.world.entity.item.PrimedTnt
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.NoteBlock
import net.minecraft.world.level.block.TntBlock
import net.minecraft.world.level.gameevent.GameEvent
import net.minecraft.world.phys.Vec3
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.core.`object`.PlayState

@Suppress("DEPRECATION")
class HermitCrabEntity(entityType: EntityType<out HybridAquaticCrustaceanEntity>, world: Level) :
    HybridAquaticCrustaceanEntity(entityType, world, false) {
    val hasShell: Boolean = true

    override fun registerGoals() {
        super.registerGoals()

        goalSelector.addGoal(1, FleeFromEntityGoal(this, PrimedTnt::class.java, 15.0, 0.3, 0.75))
        goalSelector.addGoal(1, FleeFromEntityGoal(this, SmallTNTEntity::class.java, 15.0, 0.3, 0.75))
    }

    override fun addAdditionalSaveData(nbt: CompoundTag) {
        super.addAdditionalSaveData(nbt)
        if (hasShell) nbt.put("ShellItem", shellItem.save(CompoundTag()))
    }

    override fun readAdditionalSaveData(nbt: CompoundTag) {
        super.readAdditionalSaveData(nbt)
        if (hasShell) {
            val shellItemNBT = nbt.getCompound("ShellItem")
            shellItem = if (shellItemNBT.isEmpty) Items.NAUTILUS_SHELL.defaultInstance else ItemStack.of(shellItemNBT)
        }
    }

    //#region Shells & Items
    override fun finalizeSpawn(
        world: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnReason: MobSpawnType,
        entityData: SpawnGroupData?,
        entityNbt: CompoundTag?,
    ): SpawnGroupData? {
        this.setCanPickUpLoot(true)

        val roll = random.nextFloat()
        val generatedRoll = when {
            roll < 0.10f -> HybridAquaticItems.OMINOUS_CONCH.get().defaultInstance
            roll < 0.30f -> Items.SKELETON_SKULL.defaultInstance
            roll < 0.80f -> Items.NAUTILUS_SHELL.defaultInstance
            else -> Items.AIR.defaultInstance
        }
        shellItem = generatedRoll

        return super.finalizeSpawn(world, difficulty, spawnReason, entityData, entityNbt)
    }

    override fun canTakeItem(stack: ItemStack): Boolean {
        return stack.`is`(HybridAquaticItemTags.CRAB_WEARABLES)
    }

    override fun getPickupReach(): Vec3i {
        return ITEM_PICKUP_REACH
    }

    override fun wantsToPickUp(stack: ItemStack): Boolean {
        return canTakeItem(stack)
    }

    override fun pickUpItem(itemEntity: ItemEntity) {
        if (!shellItem.isEmpty) return

        val itemStack = itemEntity.item
        if (!canTakeItem(itemStack)) return

        shellItem = itemStack.copyWithCount(1)
        itemStack.shrink(1)
        if (itemStack.isEmpty) itemEntity.discard()
    }

    override fun mobInteract(player: Player, hand: InteractionHand): InteractionResult {
        val playerStack = player.getItemInHand(hand)

        if (shellItem.`is`(HybridAquaticItems.OMINOUS_CONCH.get()) &&
            !playerStack.`is`(Items.NAUTILUS_SHELL)
        ) {
            return InteractionResult.PASS
        }

        if (canTakeItem(playerStack) || playerStack.isEmpty) {
            val oldStack = shellItem.copy()
            shellItem = playerStack.copyWithCount(1)

            if (!shellItem.`is` { item ->
                    item.equals(Items.NAUTILUS_SHELL) ||
                            item.equals(Items.SKELETON_SKULL) ||
                            item.equals(HybridAquaticItems.OMINOUS_CONCH)
                }) setPersistenceRequired()

            if (!player.abilities.instabuild) {
                playerStack.shrink(1)
            }

            if (!oldStack.isEmpty) {
                if (!player.addItem(oldStack)) {
                    player.drop(oldStack, false)
                }
            }

            return InteractionResult.sidedSuccess(level().isClientSide)
        }

        return super.mobInteract(player, hand)
    }

    override fun dropCustomDeathLoot(source: DamageSource, looting: Int, causedByPlayer: Boolean) {
        if (!shellItem.isEmpty) {

            if (shellItem.`is`(HybridAquaticItems.OMINOUS_CONCH.get())) {
                shellItem = ItemStack.EMPTY
                return
            }

            spawnAtLocation(shellItem)
            shellItem = ItemStack.EMPTY
        }
    }

    fun activateRedstoneComponents() {
        if (tickCount % 20 != 0) return // Only run every 20 ticks(1 second)
        if (!shellItem.`is`(HybridAquaticItemTags.REDSTONE_COMPONENTS)) return

        val blockPosBelow = blockPosition().below()
        val blockStateBelow = level().getBlockState(blockPosBelow)
        if (!blockStateBelow.`is`(Blocks.REDSTONE_BLOCK)) return

        val blockItem = shellItem.item as BlockItem
        val originalBlock = blockItem.block

        when (originalBlock) {
            is NoteBlock -> {
                level().addParticle(
                    ParticleTypes.NOTE,
                    position().x, position().y + 0.8, position().z,
                    24.0, 0.0, 0.0
                )

                level().playSeededSound(
                    null, position().x, position().y, position().z,
                    SoundEvents.NOTE_BLOCK_HARP, SoundSource.NEUTRAL,
                    3.0f, 1.0f, level().random.nextLong()
                )
            }

            is TntBlock -> {
                shellItem = ItemStack.EMPTY
                addDeltaMovement(Vec3(0.0, if (isInWater) 0.2 else 0.4, 0.0))
                goalSelector.tick()

                val primedTNT = SmallTNTEntity(level(), position().x, position().y, position().z, this)
                level().addFreshEntity(primedTNT)
                level().playSound(
                    null, position().x, position().y, position().z,
                    SoundEvents.TNT_PRIMED, SoundSource.BLOCKS,
                    1.0f, 1.0f
                )
                level().gameEvent(this, GameEvent.PRIME_FUSE, position())
            }
        }
    }
    //#endregion

    //#region Properties
    override fun getMaxSize(): Int {
        return 5
    }

    override fun getMinSize(): Int {
        return -5
    }
    //#endregion

    //#region Hiding
    private var isHiding: Boolean = false
    private var hidingTimer: Int = 0
    private var lastDamageTime: Long = 0

    private fun startHiding() {
        if (shellItem.isEmpty) return

        isHiding = true
        hidingTimer = 200

        attributes.getInstance(Attributes.MOVEMENT_SPEED)?.baseValue = 0.0
        attributes.getInstance(Attributes.ARMOR)?.baseValue = 50.0
    }

    fun hidingLogic() {
        if (!isHiding) return

        hidingTimer--
        if (!shellItem.isEmpty && (level().gameTime - lastDamageTime) <= 200) return

        isHiding = false
        attributes.getInstance(Attributes.MOVEMENT_SPEED)?.baseValue = 0.3
        attributes.getInstance(Attributes.ARMOR)?.baseValue = 5.0
    }

    override fun hurt(source: DamageSource, amount: Float): Boolean {
        if (!isHiding && !shellItem.isEmpty) startHiding()

        lastDamageTime = level().gameTime

        return super.hurt(source, amount)
    }
    //#endregion

    override fun tick() {
        super.tick()

        this.hidingLogic()
        this.activateRedstoneComponents()
    }

    //#region Animations
    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(
            DefaultAnimations.genericWalkIdleController(this)
        )
        controllerRegistrar.add(
            AnimationController(
                this, "Hide", 4,
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

        val ITEM_PICKUP_REACH = Vec3i(1, 0, 1)
    }
}