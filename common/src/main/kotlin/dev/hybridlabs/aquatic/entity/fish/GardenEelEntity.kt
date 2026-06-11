package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.entity.ai.MobTargetConfiguration
import dev.hybridlabs.aquatic.entity.base.HAFishEntity
import dev.hybridlabs.aquatic.tag.HAEntityTags
import dev.hybridlabs.aquatic.world.WorldHelper
import net.minecraft.core.BlockPos
import net.minecraft.util.RandomSource
import net.minecraft.world.damagesource.DamageSource
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobSpawnType
import net.minecraft.world.entity.MoverType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.entity.ai.control.LookControl
import net.minecraft.world.entity.ai.control.MoveControl
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal
import net.minecraft.world.entity.ai.goal.RandomLookAroundGoal
import net.minecraft.world.entity.ai.navigation.GroundPathNavigation
import net.minecraft.world.entity.player.Player
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import net.minecraft.world.level.pathfinder.BlockPathTypes
import net.minecraft.world.phys.Vec3
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.animation.RawAnimation

@Suppress("unused", "DEPRECATION")
class GardenEelEntity(type: EntityType<out GardenEelEntity>, world: Level) :
    HAFishEntity(type, world) {

    override fun getTargetConfig() = MobTargetConfiguration.ofPrey(
        HAEntityTags.MEDIUM_CREATURES,
        HAEntityTags.LARGE_CREATURES,
        HAEntityTags.ALL_SHARKS
    )

    init {
        setPathfindingMalus(BlockPathTypes.WATER, 0.0f)
        setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 16.0f)
        setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, -1.0f)
        moveControl = MoveControl(this)
        lookControl = LookControl(this)
        navigation = GroundPathNavigation(this, world)
    }

    override fun registerGoals() {
        goalSelector.addGoal(2, RandomLookAroundGoal(this))
        goalSelector.addGoal(1, LookAtPlayerGoal(this, Player::class.java, 6.0f))
    }

    override fun travel(travelVector: Vec3) {
        if (this.isEffectiveAi && this.isInWater) {
            this.moveRelative(0.01f, travelVector)
            this.move(MoverType.SELF, this.deltaMovement)
            this.deltaMovement = this.deltaMovement.scale(0.9)
            this.deltaMovement = this.deltaMovement.add(0.0, -0.05, 0.0)
        } else {
            super.travel(travelVector)
        }
    }

    override fun getMaxSpawnClusterSize(): Int {
        return 5
    }

    override fun isPushable(): Boolean {
        return false
    }

    override fun isPushedByFluid(): Boolean {
        return false
    }

    //#region Animations
    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
        controllers.add(
            AnimationController(this, "Garden Eel Controller", 4) { state ->
                when {
                    isInWater && onGround() && !isHiding -> {
                        state.setAndContinue(DefaultAnimations.IDLE)
                    }

                    isInWater && onGround() && isHiding -> {
                        state.setAndContinue(HIDE_ANIMATION)
                    }

                    this.moistness < 590 -> {
                        state.setAndContinue(FLOP_ANIMATION)
                    }

                    else -> {
                        state.setAndContinue(DefaultAnimations.IDLE)
                    }
                }
            }
        )
    }

    override fun shouldFlopOnLand(): Boolean {
        return false
    }

    //#region Hiding
    private var isHiding: Boolean = false
    private var hidingTimer: Int = 0
    private var lastDamageTime: Long = 0

    private fun startHiding() {
        isHiding = true
        hidingTimer = 200

        attributes.getInstance(Attributes.MOVEMENT_SPEED)?.baseValue = 0.0
        attributes.getInstance(Attributes.ARMOR)?.baseValue = 50.0
    }

    fun hidingLogic() {
        if (!isHiding) return

        hidingTimer--
        if ((level().gameTime - lastDamageTime) <= 200) return

        isHiding = false
        attributes.getInstance(Attributes.MOVEMENT_SPEED)?.baseValue = 0.3
        attributes.getInstance(Attributes.ARMOR)?.baseValue = 5.0
    }

    override fun hurt(source: DamageSource, amount: Float): Boolean {
        if (!isHiding) startHiding()

        lastDamageTime = level().gameTime

        return super.hurt(source, amount)
    }

    override fun tick() {
        super.tick()
        this.hidingLogic()
    }
    //#endregion

    companion object {
        val HIDE_ANIMATION: RawAnimation = RawAnimation.begin().thenPlay("misc.hide")

        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 3.0)
                .add(Attributes.MOVEMENT_SPEED, 0.1)
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .add(Attributes.KNOCKBACK_RESISTANCE, 1.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 4.0)
        }

        fun canSpawn(
            type: EntityType<out GardenEelEntity>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource,
        ): Boolean {
            val seaLevel = world.level.chunkSource.generator.seaLevel
            val bottomY = seaLevel - 32

            return pos.y >= bottomY &&
                    world.isWaterAt(pos) &&
                    WorldHelper.canSeeSkyFromBelowWater(world, pos)
        }
    }
}
