package dev.hybridlabs.aquatic.entity.projectile

import dev.hybridlabs.aquatic.entity.HAEntityTypes
import dev.hybridlabs.aquatic.item.HAItems
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.entity.projectile.AbstractArrow
import net.minecraft.world.entity.projectile.ItemSupplier
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.phys.BlockHitResult
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.animation.AnimatableManager
import software.bernie.geckolib.animation.AnimationController
import software.bernie.geckolib.animation.AnimationController.AnimationStateHandler
import software.bernie.geckolib.animation.AnimationState
import software.bernie.geckolib.animation.RawAnimation
import software.bernie.geckolib.animation.PlayState
import software.bernie.geckolib.util.GeckoLibUtil

class StarfishProjectileEntity : AbstractArrow, ItemSupplier, GeoEntity {
    private val animCache = GeckoLibUtil.createInstanceCache(this)

    constructor(type: EntityType<out StarfishProjectileEntity>, level: Level)
            : super(type, level) {
        this.pickup = Pickup.DISALLOWED
    }

    constructor(
        level: Level,
        owner: LivingEntity,
    ) : super(HAEntityTypes.STARFISH_PROJECTILE.get(), owner, level)

    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
        controllers.add(
            AnimationController(
                this, "thrown_controller",
                AnimationStateHandler { state: AnimationState<StarfishProjectileEntity> ->
                    if (!inGround) return@AnimationStateHandler state.setAndContinue(THROWN_ANIMATION)
                    else {
                        state.setControllerSpeed(0.0f)
                        PlayState.CONTINUE
                    }
                }
            )
        )
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache? {
        return animCache
    }

    override fun canBeCollidedWith(): Boolean {
        return inGround
    }

    override fun canCollideWith(entity: Entity): Boolean {
        if (entity is StarfishProjectileEntity) {
            return false
        }
        return super.canCollideWith(entity)
    }

    override fun playerTouch(entity: Player) {
    }

    override fun getPickupItem(): ItemStack {
        return ItemStack(HAItems.STARFISH.get())
    }

    override fun getItem(): ItemStack {
        return ItemStack(HAItems.STARFISH.get())
    }

    override fun interact(player: Player, hand: InteractionHand): InteractionResult {
        if (!this.level().isClientSide && this.inGround) {
            val item = ItemStack(HAItems.STARFISH.get())

            if (!player.addItem(item)) {
                player.drop(item, false)
            }

            this.discard()
            return InteractionResult.SUCCESS
        }

        return InteractionResult.PASS
    }

    override fun getDefaultHitGroundSoundEvent(): SoundEvent {
        return SoundEvents.TRIDENT_HIT_GROUND
    }

    override fun onHitBlock(result: BlockHitResult) {
        super.onHitBlock(result)
        this.syncPacketPositionCodec(x, y, z)
        this.setSoundEvent(SoundEvents.TRIDENT_HIT_GROUND)
    }

    override fun getWaterInertia(): Float {
        return 1.0f
    }

    override fun shouldRender(x: Double, y: Double, z: Double): Boolean {
        return true
    }

    override fun tick() {
        super.tick()

        if (isInWater && !inGround) {
            deltaMovement = deltaMovement.add(0.0, 0.05, 0.0)
        }
    }

    companion object {
        val THROWN_ANIMATION: RawAnimation = RawAnimation.begin().thenPlay("misc.thrown")
    }
}