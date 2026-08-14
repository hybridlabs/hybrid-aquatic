package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.hapi.tag.HAPIEntityTags
import dev.hybridlabs.hapi.entity.ai.MobTargetConfiguration
import dev.hybridlabs.hapi.entity.ai.goal.WaterAnimalJumpGoal
import dev.hybridlabs.hapi.entity.base.aquatic.BaseFishEntity
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobSpawnType
import net.minecraft.world.entity.SpawnGroupData
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.goal.BreathAirGoal
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import net.minecraft.world.phys.Vec3
import software.bernie.geckolib.animation.AnimatableManager
import software.bernie.geckolib.animation.AnimationController
import software.bernie.geckolib.animation.AnimationState
import software.bernie.geckolib.constant.DefaultAnimations

class AfricanButterflyfishEntity(type: EntityType<out AfricanButterflyfishEntity>, world: Level) :
    BaseFishEntity(type, world) {

    override fun getTargetConfig() = MobTargetConfiguration.ofPrey(
        HAPIEntityTags.MEDIUM_CREATURES,
        HAPIEntityTags.LARGE_CREATURES,
        HAPIEntityTags.ALL_SHARKS
    )

    private var isGliding = false

    override fun getMaxSpawnClusterSize(): Int {
        return 1
    }

    override fun isFood(stack: ItemStack): Boolean {
        return stack.`is`(Items.SPIDER_EYE)
    }

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(0, BreathAirGoal(this))
        goalSelector.addGoal(4, WaterAnimalJumpGoal(this, 10, 1.5))
    }

    override fun tick() {
        super.tick()

        if (this.isNoAi) {
            this.airSupply = this.maxAirSupply
        }

        if (!this.wasTouchingWater && !onGround()) {
            if (!isGliding) {
                startGliding()
            }
            applyGlidingPhysics()
        } else if (isGliding) {
            stopGliding()
        }
    }

    private fun startGliding() {
        isGliding = true
    }

    private fun stopGliding() {
        isGliding = false
    }

    private fun applyGlidingPhysics() {
        if (!isGliding) return

        val motion = this.deltaMovement
        val newMotion = Vec3(
            motion.x * 1.1,
            (motion.y * 0.95).coerceAtLeast(-0.1),
            motion.z * 1.1
        )
        this.deltaMovement = newMotion
    }

    override fun increaseAirSupply(currentAir: Int): Int {
        return this.maxAirSupply
    }

    override fun finalizeSpawn(
        world: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnReason: MobSpawnType,
        entityData: SpawnGroupData?
    ): SpawnGroupData? {
        this.airSupply = this.maxAirSupply
        return super.finalizeSpawn(world, difficulty, spawnReason, entityData)
    }

    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
        controllers.add(
            AnimationController(
                this, "Fly/Swim/Idle", 5
            ) { state: AnimationState<BaseFishEntity> ->
                when {
                    this.isGliding -> state.setAndContinue(DefaultAnimations.FLY)
                    state.isMoving -> state.setAndContinue(DefaultAnimations.SWIM)
                    else -> state.setAndContinue(DefaultAnimations.IDLE)
                }
            }
        )
    }

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 2.0)
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 4.0)
        }
    }
}
