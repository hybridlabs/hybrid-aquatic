package dev.hybridlabs.aquatic.entity.critter

import net.minecraft.enchantment.EnchantmentHelper
import net.minecraft.enchantment.Enchantments
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.world.World
import software.bernie.geckolib.core.animatable.GeoAnimatable
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.core.`object`.PlayState

class KarkinosEntity(entityType: EntityType<out HybridAquaticCritterEntity>, world: World) :
    HybridAquaticCrabEntity(entityType, world) {

    private var isFlipped: Boolean = false
    private var flipCooldown: Int = 0
    private var flipResetCooldown: Int = 0

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 100.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.6)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 32.0)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 100.0)
        }
    }

    override fun damage(source: net.minecraft.entity.damage.DamageSource?, amount: Float): Boolean {
        val damaged = super.damage(source, amount)

        if (damaged && source?.source is PlayerEntity) {
            val player = source.source as PlayerEntity
            val heldItem = player.mainHandStack

            if (EnchantmentHelper.getLevel(Enchantments.BANE_OF_ARTHROPODS, heldItem) > 0 ||
                (heldItem.item is net.minecraft.item.TridentItem && EnchantmentHelper.getLevel(Enchantments.RIPTIDE, heldItem) > 0)
            ) {
                isFlipped = true
                flipCooldown = 100
                flipResetCooldown = 300
            }
        }

        return damaged
    }

    override fun tick() {
        super.tick()

        if (isFlipped) {
            flipCooldown--

            if (flipCooldown <= 0) {
                isFlipped = false
            }
        }

        if (flipResetCooldown > 0) {
            flipResetCooldown--
        }

        if (flipResetCooldown == 0) {
            flipCooldown = 0
        }
    }


    override fun <E : GeoAnimatable> predicate(event: AnimationState<E>): PlayState {
        if (isFlipped) {
            event.controller.setAnimation(FLIPPED_ANIMATION)
            return PlayState.CONTINUE
        }

        return super.predicate(event)
    }


    override fun getMovementSpeed(): Float {
        return if (isFlipped) 0.0f else super.getMovementSpeed()
    }
}
