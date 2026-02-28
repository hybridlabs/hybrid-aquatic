package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.entity.ai.MobTargetConfiguration
import dev.hybridlabs.aquatic.entity.ai.goal.HybridAquaticJumpGoal
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.nbt.CompoundTag
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
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.animation.AnimationState

class AfricanButterflyfishEntity(type: EntityType<out AfricanButterflyfishEntity>, world: Level) :
    HybridAquaticFishEntity(type, world) {

    override fun getTargetConfig() = MobTargetConfiguration.ofPrey(
        HybridAquaticEntityTags.MEDIUM_CREATURES,
        HybridAquaticEntityTags.LARGE_CREATURES,
        HybridAquaticEntityTags.ALL_SHARKS
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
        goalSelector.addGoal(4, HybridAquaticJumpGoal(this, 10, 1.5))
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

    override fun handleAirSupply(airSupply: Int) {
        if (isInWater && !isNoAi) {
            this.airSupply = airSupply - 1
        } else {
            this.airSupply = this.maxAirSupply
        }
    }

    override fun getMaxAirSupply(): Int {
        return 900
    }

    override fun increaseAirSupply(currentAir: Int): Int {
        return this.maxAirSupply
    }

    override fun finalizeSpawn(
        world: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnReason: MobSpawnType,
        entityData: SpawnGroupData?,
        entityNbt: CompoundTag?
    ): SpawnGroupData? {
        this.airSupply = this.maxAirSupply
        return super.finalizeSpawn(world, difficulty, spawnReason, entityData, entityNbt)
    }

    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
        controllers.add(
            AnimationController(this, "Fly/Swim/Idle", 5
            ) { state: AnimationState<HybridAquaticFishEntity> ->
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
