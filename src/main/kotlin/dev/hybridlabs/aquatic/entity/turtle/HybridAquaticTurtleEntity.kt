package dev.hybridlabs.aquatic.entity.turtle

import net.minecraft.block.BlockState
import net.minecraft.block.Blocks
import net.minecraft.entity.EntityGroup
import net.minecraft.entity.EntityType
import net.minecraft.entity.MovementType
import net.minecraft.entity.SpawnReason
import net.minecraft.entity.ai.goal.*
import net.minecraft.entity.ai.pathing.AmphibiousSwimNavigation
import net.minecraft.entity.ai.pathing.EntityNavigation
import net.minecraft.entity.damage.DamageSource
import net.minecraft.entity.passive.TurtleEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.ItemStack
import net.minecraft.registry.tag.TagKey
import net.minecraft.sound.SoundEvent
import net.minecraft.sound.SoundEvents
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.Vec3d
import net.minecraft.util.math.random.Random
import net.minecraft.world.ServerWorldAccess
import net.minecraft.world.World
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.util.GeckoLibUtil

@Suppress("LeakingThis")
open class HybridAquaticTurtleEntity(
    type: EntityType<out HybridAquaticTurtleEntity>,
    world: World,
    open val prey: List<TagKey<EntityType<*>>>,
    open val predator: List<TagKey<EntityType<*>>>,
) : TurtleEntity(type, world), GeoEntity {

    private val factory = GeckoLibUtil.createInstanceCache(this)
    override fun createNavigation(world: World): EntityNavigation {
        return AmphibiousSwimNavigation(this, world)
    }

    override fun isPushedByFluids(): Boolean {
        return false
    }

    override fun canBreatheInWater(): Boolean {
        return true
    }

    override fun getGroup(): EntityGroup {
        return EntityGroup.AQUATIC
    }

    override fun playSwimSound(volume: Float) {
        super.playSwimSound(volume * 1.5f)
    }

    override fun getSwimSound(): SoundEvent {
        return SoundEvents.ENTITY_TURTLE_SWIM
    }

    override fun getHurtSound(source: DamageSource?): SoundEvent? {
        return if (this.isBaby) SoundEvents.ENTITY_TURTLE_HURT_BABY else SoundEvents.ENTITY_TURTLE_HURT
    }

    override fun getDeathSound(): SoundEvent? {
        return if (this.isBaby) SoundEvents.ENTITY_TURTLE_DEATH_BABY else SoundEvents.ENTITY_TURTLE_DEATH
    }

    override fun playStepSound(pos: BlockPos?, state: BlockState?) {
        val soundEvent = if (this.isBaby) SoundEvents.ENTITY_TURTLE_SHAMBLE_BABY else SoundEvents.ENTITY_TURTLE_SHAMBLE
        this.playSound(soundEvent, 0.15f, 1.0f)
    }

    override fun calculateNextStepSoundDistance(): Float {
        return this.distanceTraveled + 0.15f
    }

    override fun getScaleFactor(): Float {
        return if (this.isBaby) 0.3f else 1.0f
    }

    override fun isBreedingItem(stack: ItemStack): Boolean {
        return stack.isOf(Blocks.SEAGRASS.asItem())
    }

    override fun travel(movementInput: Vec3d?) {
        if (this.isLogicalSideForUpdatingMovement && this.isTouchingWater) {
            this.updateVelocity(0.1f, movementInput)
            this.move(MovementType.SELF, this.velocity)
            this.velocity = velocity.multiply(0.9)
        } else {
            super.travel(movementInput)
        }
    }

    override fun initGoals() {
        goalSelector.add(0, MoveIntoWaterGoal(this))
        goalSelector.add(1, SwimAroundGoal(this, 1.0, 10))
        goalSelector.add(1, LookAroundGoal(this))
        goalSelector.add(2, TemptGoal(this, 1.1, BREEDING_ITEM, false))
        goalSelector.add(8, LookAtEntityGoal(this, PlayerEntity::class.java, 8.0f))
        goalSelector.add(3, WanderAroundGoal(this, 0.4))
    }

    override fun registerControllers(controllerRegistrar: AnimatableManager.ControllerRegistrar) {
        controllerRegistrar.add(
            AnimationController(this, "Swim/Idle", 5,
                AnimationController.AnimationStateHandler { state: AnimationState<HybridAquaticTurtleEntity> ->
                    if (state.isMoving) {
                        return@AnimationStateHandler state.setAndContinue(DefaultAnimations.SWIM)
                    } else {
                        return@AnimationStateHandler state.setAndContinue(DefaultAnimations.IDLE)
                    }
                })
        )
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return factory
    }

    companion object {
        @Suppress("UNUSED_PARAMETER", "DEPRECATION")
        fun canSpawn(
            type: EntityType<out TurtleEntity>,
            world: ServerWorldAccess,
            reason: SpawnReason,
            pos: BlockPos,
            random: Random
        ): Boolean {
            return pos.y < world.seaLevel + 4 && isLightLevelValidForNaturalSpawn(world, pos)
        }
    }
}