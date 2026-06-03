package dev.hybridlabs.aquatic.entity.projectile

import dev.hybridlabs.aquatic.entity.HAEntityTypes
import dev.hybridlabs.aquatic.item.HAItems
import net.minecraft.sounds.SoundEvent
import net.minecraft.sounds.SoundEvents
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.entity.projectile.AbstractArrow
import net.minecraft.world.entity.projectile.ItemSupplier
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level
import net.minecraft.world.phys.BlockHitResult
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.core.animation.AnimatableManager
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
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache? {
        return animCache
    }

    override fun canBeCollidedWith(): Boolean {
        return true
    }

    override fun playerTouch(entity: Player) {
    }

    override fun getPickupItem(): ItemStack {
        return ItemStack(HAItems.STARFISH.get())
    }

    override fun getItem(): ItemStack {
        return ItemStack(HAItems.STARFISH.get())
    }

    override fun getDefaultHitGroundSoundEvent(): SoundEvent {
        return SoundEvents.TRIDENT_HIT_GROUND
    }

    override fun onHitBlock(result: BlockHitResult) {
        super.onHitBlock(result)
        this.setSoundEvent(SoundEvents.TRIDENT_HIT_GROUND)
    }

    override fun getWaterInertia(): Float {
        return 0.99f
    }

    override fun shouldRender(x: Double, y: Double, z: Double): Boolean {
        return true
    }
}