package dev.hybridlabs.aquatic.entity.critter

import dev.hybridlabs.aquatic.item.HybridAquaticItems
import net.minecraft.entity.EntityType
import net.minecraft.entity.ai.goal.LookAroundGoal
import net.minecraft.entity.ai.goal.LookAtEntityGoal
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.mob.WaterCreatureEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemUsage
import net.minecraft.particle.ParticleTypes
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.world.World
import software.bernie.geckolib.core.animatable.GeoAnimatable
import software.bernie.geckolib.core.animation.Animation
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.core.animation.RawAnimation
import software.bernie.geckolib.core.`object`.PlayState
import kotlin.math.max

class GiantClamEntity(entityType: EntityType<out GiantClamEntity>, world: World) :
    HybridAquaticCritterEntity(entityType, world) {

    private var lastInteractionTime: Long = 0

    override fun initGoals() {
        super.initGoals()
        goalSelector.add(0, LookAtEntityGoal(this, PlayerEntity::class.java, 0.1f))
        goalSelector.add(0, LookAroundGoal(this))
    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder =
            WaterCreatureEntity.createMobAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 18.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.0)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 4.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 16.0)
                .add(EntityAttributes.GENERIC_KNOCKBACK_RESISTANCE, 100.0)
    }

    override fun getLimitPerChunk(): Int = 8

    override fun getHurtSound(source: DamageSource): SoundEvent =
        SoundEvents.ENTITY_SHULKER_HURT_CLOSED

    override fun getDeathSound(): SoundEvent =
        SoundEvents.ENTITY_TURTLE_EGG_BREAK

    override fun getAmbientSound(): SoundEvent =
        SoundEvents.ENTITY_COD_AMBIENT

    private var particleSpawnCooldown: Int = 0

    private fun spawnBubbleParticles() {
        if (particleSpawnCooldown > 0) {
            particleSpawnCooldown--
            return
        }

        val xOffset = 0.0
        val yOffset = max(0.0, random.nextGaussian() * 0.5)
        val zOffset = 0.0
        world.addParticle(
            ParticleTypes.BUBBLE_COLUMN_UP,
            x + 0.0,
            y + 0.75,
            z + 0.0,
            xOffset,
            yOffset + 0.05,
            zOffset
        )
        particleSpawnCooldown = 3
    }

    override fun <E : GeoAnimatable> predicate(event: AnimationState<E>): PlayState {
        val currentTime = world.timeOfDay
        return when {
            currentTime > lastInteractionTime + 6000 -> {
            event.controller.setAnimation(RawAnimation.begin().then("open", Animation.LoopType.LOOP))
            spawnBubbleParticles()
            PlayState.CONTINUE
        }

            currentTime < lastInteractionTime + 6000 -> {
                event.controller.setAnimation(RawAnimation.begin().then("closed", Animation.LoopType.LOOP))
                PlayState.CONTINUE
            }

            !isWet -> {
                event.controller.setAnimation(RawAnimation.begin().then("closed", Animation.LoopType.LOOP))
                PlayState.CONTINUE
            }

            else -> PlayState.STOP
        }
    }

    override fun interactMob(player: PlayerEntity, hand: Hand?): ActionResult? {
        val currentTime = world.timeOfDay
        val itemStack = player.getStackInHand(hand)
        return when {
            hand == Hand.MAIN_HAND && itemStack.isEmpty && isSubmergedInWater && currentTime > lastInteractionTime + 6000 -> {
                player.playSound(SoundEvents.ENTITY_SHULKER_CLOSE, 1.0f, 1.0f)
                val itemStack2 = ItemUsage.exchangeStack(itemStack, player, ItemStack(HybridAquaticItems.PEARL))
                player.setStackInHand(hand, itemStack2)
                lastInteractionTime = currentTime
                ActionResult.SUCCESS
            }

            hand == Hand.MAIN_HAND && itemStack.isOf(HybridAquaticItems.PEARL) && isSubmergedInWater && currentTime > lastInteractionTime + 6000 -> {
                player.playSound(SoundEvents.ENTITY_SHULKER_CLOSE, 1.0f, 1.0f)
                val itemStack2 = ItemUsage.exchangeStack(itemStack, player, ItemStack(HybridAquaticItems.BLACK_PEARL))
                player.setStackInHand(hand, itemStack2)
                lastInteractionTime = currentTime
                ActionResult.SUCCESS
            }

            else -> super.interactMob(player, hand)
        }
    }

    override fun isPushable(): Boolean =
        !this.isAlive && !this.isSpectator && !this.isClimbing

    override fun getMaxSize(): Int = 5

    override fun getMinSize(): Int = -5
}
