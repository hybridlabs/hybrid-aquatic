package dev.hybridlabs.aquatic.entity.mammal

import dev.hybridlabs.aquatic.block.HABlocks
import dev.hybridlabs.aquatic.entity.ai.goal.HerbivoreGrazeGoal
import dev.hybridlabs.aquatic.entity.ai.goal.WaterAnimalBreedGoal
import dev.hybridlabs.aquatic.entity.ai.goal.WaterAnimalFollowParentGoal
import dev.hybridlabs.aquatic.entity.ai.goal.boids.StayInWaterGoal
import dev.hybridlabs.aquatic.entity.base.HAWaterAnimal
import dev.hybridlabs.aquatic.item.HAItems
import dev.hybridlabs.aquatic.sound.HASoundEvents
import net.minecraft.commands.arguments.EntityAnchorArgument
import net.minecraft.core.BlockPos
import net.minecraft.core.particles.BlockParticleOption
import net.minecraft.core.particles.ParticleTypes
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.syncher.EntityDataAccessor
import net.minecraft.network.syncher.EntityDataSerializers
import net.minecraft.network.syncher.SynchedEntityData
import net.minecraft.server.level.ServerLevel
import net.minecraft.util.RandomSource
import net.minecraft.world.DifficultyInstance
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.*
import net.minecraft.world.entity.ai.control.SmoothSwimmingLookControl
import net.minecraft.world.entity.ai.control.SmoothSwimmingMoveControl
import net.minecraft.world.entity.ai.goal.Goal
import net.minecraft.world.entity.ai.goal.RandomSwimmingGoal
import net.minecraft.world.entity.ai.goal.TemptGoal
import net.minecraft.world.entity.ai.navigation.PathNavigation
import net.minecraft.world.entity.ai.navigation.WaterBoundPathNavigation
import net.minecraft.world.entity.item.ItemEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.pathfinder.BlockPathTypes
import net.minecraft.world.phys.Vec3
import software.bernie.geckolib.animatable.GeoEntity
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.core.animatable.instance.AnimatableInstanceCache
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.animation.AnimationState
import software.bernie.geckolib.util.GeckoLibUtil
import java.util.*

@Suppress("LeakingThis", "UNUSED_PARAMETER", "unused", "DEPRECATION")
open class HASirenianEntity(type: EntityType<out HASirenianEntity>, world: Level) :
    HAWaterAnimal(type, world), GeoEntity {
    private val factory = GeckoLibUtil.createInstanceCache(this)
    var prevRoll: Float = 0f
    var currentRoll: Float = 0.0f

    override fun createNavigation(level: Level): PathNavigation {
        setPathfindingMalus(BlockPathTypes.WATER, 0.0f)
        setPathfindingMalus(BlockPathTypes.DANGER_FIRE, 16.0f)
        setPathfindingMalus(BlockPathTypes.DAMAGE_FIRE, -1.0f)

        moveControl = SmoothSwimmingMoveControl(this, 60, 6, 0.02F, 0.1F, false)
        lookControl = SmoothSwimmingLookControl(this, 15)

        return WaterBoundPathNavigation(this, level)
    }

    override fun registerGoals() {
        super.registerGoals()
        goalSelector.addGoal(0, StayInWaterGoal(this))
        goalSelector.addGoal(1, SirenianDigClamGoal(this))
        goalSelector.addGoal(2, HerbivoreGrazeGoal(this))
        goalSelector.addGoal(1, WaterAnimalBreedGoal(this, 1.1))
        goalSelector.addGoal(2, TemptGoal(this, 1.1, BREEDING_INGREDIENT, false))
        goalSelector.addGoal(3, RandomSwimmingGoal(this, 1.0, 2))
        goalSelector.addGoal(5, WaterAnimalFollowParentGoal(this, 1.1))
    }

    //#region Data
    fun setClamPos(pos: BlockPos) {
        this.entityData.set(CLAM_POS, pos)
    }

    fun getClamPos(): BlockPos {
        return this.entityData.get(CLAM_POS) as BlockPos
    }

    fun gotSeaLettuce(): Boolean {
        return this.entityData.get(HAS_SEA_LETTUCE) as Boolean
    }

    fun setGotSeaLettuce(gotFish: Boolean) {
        this.entityData.set(HAS_SEA_LETTUCE, gotFish)
    }

    override fun defineSynchedData() {
        super.defineSynchedData()
        entityData.define(CLAM_POS, BlockPos.ZERO)
        entityData.define(HAS_SEA_LETTUCE, false)
    }

    override fun addAdditionalSaveData(compound: CompoundTag) {
        super.addAdditionalSaveData(compound)
        compound.putInt("TreasurePosX", this.getClamPos().x)
        compound.putInt("TreasurePosY", this.getClamPos().y)
        compound.putInt("TreasurePosZ", this.getClamPos().z)
        compound.putBoolean("GotFish", this.gotSeaLettuce())
    }

    override fun readAdditionalSaveData(compound: CompoundTag) {
        super.readAdditionalSaveData(compound)
        val i = compound.getInt("TreasurePosX")
        val j = compound.getInt("TreasurePosY")
        val k = compound.getInt("TreasurePosZ")
        this.setClamPos(BlockPos(i, j, k))
        this.setGotSeaLettuce(compound.getBoolean("GotFish"))
    }

    override fun getMaxSpawnClusterSize(): Int {
        return 2
    }

    override fun getMaxHeadXRot(): Int {
        return 1
    }

    override fun getMaxHeadYRot(): Int {
        return 1
    }

    override fun tick() {
        super.tick()
        prevRoll = currentRoll
    }

    override fun mobInteract(player: Player, hand: InteractionHand): InteractionResult {
        val itemstack = player.getItemInHand(hand)
        if (!itemstack.isEmpty && itemstack.`is`(HAItems.SEA_LETTUCE.get())) {
            if (!this.level().isClientSide) {
                this.playSound(HASoundEvents.SIRENIAN_EAT.get(), 1.0f, 1.0f)
            }

            this.setGotSeaLettuce(true)
            if (!player.abilities.instabuild) {
                itemstack.shrink(1)
            }

            return InteractionResult.sidedSuccess(this.level().isClientSide)
        } else {
            return super.mobInteract(player, hand)
        }
    }

    override fun isFood(stack: ItemStack): Boolean {
        return BREEDING_INGREDIENT.test(stack)
    }

    override fun aiStep() {

        prevRoll = currentRoll
        var targetRoll = ((this.yRot - this.yRotO) * 0.1f).coerceIn(-0.45f, 0.45f)
        targetRoll = -targetRoll
        currentRoll += (targetRoll - currentRoll) * 0.05f

        val vec3d = this.deltaMovement
        if (!this.onGround() && this.isSwimming && vec3d.y < 0.0) {
            this.deltaMovement = vec3d.multiply(1.0, 0.6, 1.0)
        }

        super.aiStep()
    }

    override fun travel(travelVector: Vec3) {
        if (this.isEffectiveAi && this.isInWater) {
            this.moveRelative(this.speed, travelVector)
            this.move(MoverType.SELF, this.deltaMovement)
            this.deltaMovement = deltaMovement.scale(0.9)
        } else {
            super.travel(travelVector)
        }
    }

    override fun finalizeSpawn(
        world: ServerLevelAccessor,
        difficulty: DifficultyInstance,
        spawnReason: MobSpawnType,
        entityData: SpawnGroupData?,
        entityNbt: CompoundTag?,
    ): SpawnGroupData? {
        this.airSupply = this.maxAirSupply
        this.yRot = 0.0f

        if (this.random.nextFloat() < 0.1f) {
            this.setAge(-6000)
        }

        return super.finalizeSpawn(world, difficulty, spawnReason, entityData, entityNbt)
    }

    override fun getBreedOffspring(p0: ServerLevel, p1: AgeableMob): AgeableMob? {
        return null
    }

    //#region Animations
    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
        controllers.add(
            AnimationController(
                this, "Swim/Idle", 4
            ) { state: AnimationState<HASirenianEntity> ->
                when {
                    state.isMoving && isInWater -> state.setAndContinue(DefaultAnimations.SWIM)
                    !state.isMoving && isInWater -> state.setAndContinue(DefaultAnimations.IDLE)
                    else -> state.setAndContinue(DefaultAnimations.IDLE)
                }
            }
        )
    }

    override fun getAnimatableInstanceCache(): AnimatableInstanceCache {
        return factory
    }

    override fun getStandingEyeHeight(pose: Pose, dimensions: EntityDimensions): Float {
        return 0.3f
    }

    companion object {
        val CLAM_POS: EntityDataAccessor<BlockPos> =
            SynchedEntityData.defineId(HASirenianEntity::class.java, EntityDataSerializers.BLOCK_POS)

        val HAS_SEA_LETTUCE: EntityDataAccessor<Boolean> =
            SynchedEntityData.defineId(HASirenianEntity::class.java, EntityDataSerializers.BOOLEAN)

        val BREEDING_INGREDIENT: Ingredient = Ingredient.of(
            Items.SEAGRASS,
        )

        fun canSpawn(
            type: EntityType<out HASirenianEntity>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource,
        ): Boolean {
            val seaLevel = world.level.chunkSource.generator.seaLevel
            val topY = seaLevel - 4
            val bottomY = seaLevel - 32

            return pos.y in bottomY..topY &&
                    world.isWaterAt(pos)
        }
    }

    class SirenianDigClamGoal(
        private val sirenian: HASirenianEntity,
    ) : Goal() {

        private var targetPos: BlockPos? = null
        private var digTime = 0

        init {
            this.flags = EnumSet.of(Flag.MOVE, Flag.LOOK)
        }

        override fun canUse(): Boolean {
            if (!sirenian.gotSeaLettuce()) return false

            val pos = findGrassySand()
            if (pos != null) {
                targetPos = pos
                return true
            }

            return false
        }

        override fun canContinueToUse(): Boolean {
            return targetPos != null && digTime < 60
        }

        override fun start() {
            digTime = 0
            targetPos?.let {
                sirenian.navigation.moveTo(
                    it.x + 0.5,
                    it.y + 0.5,
                    it.z + 0.5,
                    1.2
                )
            }
        }

        override fun tick() {
            val pos = targetPos ?: return

            val distance = sirenian.distanceToSqr(
                pos.x + 0.5,
                pos.y + 0.5,
                pos.z + 0.5
            )

            if (distance < 4.0) {
                sirenian.navigation.stop()
                digTime++

                sirenian.lookAt(
                    EntityAnchorArgument.Anchor.EYES,
                    Vec3.atCenterOf(pos)
                )

                if (digTime % 5 == 0) {
                    val level = sirenian.level()
                    val state = level.getBlockState(pos)

                    if (level is ServerLevel) {
                        level.sendParticles(
                            BlockParticleOption(ParticleTypes.BLOCK, state),
                            pos.x + 0.5,
                            pos.y + 0.8,
                            pos.z + 0.5,
                            6,
                            0.3, 0.5, 0.3,
                            0.02
                        )
                    }
                }

                if (digTime == 60 && !sirenian.level().isClientSide) {
                    val level = sirenian.level()

                    val item = ItemStack(HAItems.CLAM.get())
                    val itemEntity = ItemEntity(
                        level,
                        pos.x + 0.5,
                        pos.y + 1.0,
                        pos.z + 0.5,
                        item
                    )
                    level.addFreshEntity(itemEntity)

                    val currentState = level.getBlockState(pos)
                    if (currentState.`is`(HABlocks.GRASSY_SAND.get())) {
                        level.setBlock(
                            pos,
                            Blocks.SAND.defaultBlockState(),
                            3
                        )
                    }

                    sirenian.setGotSeaLettuce(false)
                }
            } else {
                sirenian.navigation.moveTo(
                    pos.x + 0.5,
                    pos.y + 0.5,
                    pos.z + 0.5,
                    1.2
                )
            }
        }

        override fun stop() {
            targetPos = null
            digTime = 0
        }

        private fun findGrassySand(): BlockPos? {
            val origin = sirenian.blockPosition()

            for (i in 0 until 20) {
                val offset = origin.offset(
                    sirenian.random.nextInt(-8, 9),
                    sirenian.random.nextInt(-4, 5),
                    sirenian.random.nextInt(-8, 9)
                )

                val state = sirenian.level().getBlockState(offset)

                if (state.`is`(HABlocks.GRASSY_SAND.get())) {
                    return offset
                }
            }

            return null
        }
    }
}