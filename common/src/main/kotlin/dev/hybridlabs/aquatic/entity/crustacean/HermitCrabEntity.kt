package dev.hybridlabs.aquatic.entity.crustacean

import dev.hybridlabs.aquatic.tag.HybridAquaticItemTags
import net.minecraft.nbt.CompoundTag
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

@Suppress("DEPRECATION")
class HermitCrabEntity(entityType: EntityType<out HybridAquaticCrustaceanEntity>, world: Level) :
    HybridAquaticCrustaceanEntity(entityType, world, false, true) {

    //#region Shells & Items
    override fun finalizeSpawn(
        world: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnReason: MobSpawnType,
        entityData: SpawnGroupData?,
        entityNbt: CompoundTag?,
    ): SpawnGroupData? {
        val roll = random.nextFloat()

        val generatedRoll = when {
            roll < 0.60f -> Items.NAUTILUS_SHELL.defaultInstance
            roll < 0.85f -> Items.SKELETON_SKULL.defaultInstance
            else -> Items.AIR.defaultInstance
        }

        shellItem = generatedRoll

        return super.finalizeSpawn(world, difficulty, spawnReason, entityData, entityNbt)
    }

    override fun canTakeItem(stack: ItemStack): Boolean {
        return stack.`is`(HybridAquaticItemTags.PICKABLE_BY_CRABS)
    }

    override fun mobInteract(player: Player, hand: InteractionHand): InteractionResult {
        val playerStack = player.getItemInHand(hand)
        if (canTakeItem(playerStack) || playerStack.isEmpty) {
            val oldStack = shellItem.copy()
            shellItem = playerStack.copyWithCount(1)

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
        val held = shellItem
        if (!held.isEmpty) {
            spawnAtLocation(held)
            shellItem = ItemStack.EMPTY
        }
    }
    //#endregion

    //#region Properties
    override fun getMaxSize() : Int {
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

    override fun tick() {
        super.tick()

        if (isHiding && shellItem.isEmpty) {
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
        if (!isHiding && !shellItem.isEmpty) {
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
    }
}