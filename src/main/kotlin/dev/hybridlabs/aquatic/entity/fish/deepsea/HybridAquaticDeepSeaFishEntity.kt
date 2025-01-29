package dev.hybridlabs.aquatic.entity.fish.deepsea

import dev.hybridlabs.aquatic.entity.ai.goal.StayDeepGoal
import dev.hybridlabs.aquatic.entity.fish.HybridAquaticFishEntity
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.block.Blocks
import net.minecraft.entity.EntityType
import net.minecraft.entity.ai.control.MoveControl
import net.minecraft.entity.ai.goal.SwimAroundGoal
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.registry.tag.FluidTags
import net.minecraft.registry.tag.TagKey
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.MathHelper
import net.minecraft.world.World
import kotlin.math.sqrt

@Suppress("DEPRECATION", "LeakingThis")
open class HybridAquaticDeepSeaFishEntity(
    type: EntityType<out HybridAquaticFishEntity>,
    world: World,
    private val variants: Map<String, FishVariant> = mutableMapOf(),
    override val prey: TagKey<EntityType<*>>,
    override val predator: TagKey<EntityType<*>>,
    override val assumeDefault: Boolean = false,
    override val collisionRules: List<VariantCollisionRules> = listOf()
) : HybridAquaticFishEntity(type, world, variants, HybridAquaticEntityTags.NONE, HybridAquaticEntityTags.NONE) {

    init {
        moveControl = DeepSeaFishMoveControl(this)
    }

    override fun initGoals() {
        super.initGoals()
        goalSelector.add(0, StayDeepGoal(this))
        goalSelector.add(0, SwimToRandomPlaceGoal(this))
    }


    internal class DeepSeaFishMoveControl(private val deepSeaFish: HybridAquaticFishEntity) : MoveControl(deepSeaFish) {
        override fun tick() {
            if (deepSeaFish.isSubmergedIn(FluidTags.WATER)) {
                deepSeaFish.velocity = deepSeaFish.velocity.add(0.0, 0.005, 0.0)
            }

            if (this.state == State.MOVE_TO && !deepSeaFish.navigation.isIdle) {
                val f = (this.speed * deepSeaFish.getAttributeValue(EntityAttributes.MOVEMENT_SPEED)).toFloat()
                deepSeaFish.movementSpeed = MathHelper.lerp(0.125f, deepSeaFish.movementSpeed, f)
                val d = this.targetX - deepSeaFish.x
                val e = this.targetY - deepSeaFish.y
                val g = this.targetZ - deepSeaFish.z

                val blockPos = deepSeaFish.blockPos
                val blockAbove = deepSeaFish.entityWorld.getBlockState(blockPos.up(17))
                val ceilingAbove = isSolidBlockAbove(blockPos)
                val waterBelow = isWaterBelow(blockPos)

                if (e > 0.0 && !blockAbove.isOf(Blocks.WATER) && !ceilingAbove && waterBelow) {
                    // Prevent upward movement
                    return
                }

                if (e != 0.0) {
                    val h = sqrt(d * d + e * e + g * g)
                    deepSeaFish.velocity = deepSeaFish.velocity.add(
                        0.0,
                        deepSeaFish.movementSpeed.toDouble() * (e / h) * 0.1, 0.0
                    )
                }

                if (d != 0.0 || g != 0.0) {
                    val i = (MathHelper.atan2(g, d) * 57.2957763671875).toFloat() - 90.0f
                    deepSeaFish.yaw = this.wrapDegrees(deepSeaFish.yaw, i, 90.0f)
                    deepSeaFish.bodyYaw = deepSeaFish.yaw
                }
            } else {
                deepSeaFish.movementSpeed = 0.0f
            }
        }

        private fun isSolidBlockAbove(pos: BlockPos): Boolean {
            for (i in 1..16) {
                val state = deepSeaFish.entityWorld.getBlockState(pos.up(i))
                if (state.isSolid) {
                    val airGapBelow = deepSeaFish.entityWorld.getBlockState(pos.up(i - 1)).isOf(Blocks.AIR)
                    if (!airGapBelow) {
                        return true
                    }
                }
            }
            return false
        }

        private fun isWaterBelow(pos: BlockPos): Boolean {
            for (i in 1..8) {
                val state = deepSeaFish.entityWorld.getBlockState(pos.down(i))
                if (state.isOf(Blocks.WATER)) {
                    return true
                }
            }
            return false
        }
    }

    internal class SwimToRandomPlaceGoal(private val deepSeaFish: HybridAquaticDeepSeaFishEntity) : SwimAroundGoal(deepSeaFish, 1.0, 40) {
        override fun canStart(): Boolean {
            return deepSeaFish.hasSelfControl() && super.canStart()
        }

        override fun shouldContinue(): Boolean {
            val blockPos = deepSeaFish.blockPos
            val blockAbove = deepSeaFish.entityWorld.getBlockState(blockPos.up(17))
            val ceilingAbove = isSolidBlockAbove(blockPos)
            val waterBelow = isWaterBelow(blockPos)

            if (!blockAbove.isOf(Blocks.WATER) && !ceilingAbove && waterBelow) {
                return false
            }

            return super.shouldContinue()
        }

        private fun isSolidBlockAbove(pos: BlockPos): Boolean {
            for (i in 1..16) {
                val state = deepSeaFish.entityWorld.getBlockState(pos.up(i))
                if (state.isSolid) {
                    val airGapBelow = deepSeaFish.entityWorld.getBlockState(pos.up(i - 1)).isOf(Blocks.AIR)
                    if (!airGapBelow) {
                        return true
                    }
                }
            }
            return false
        }

        private fun isWaterBelow(pos: BlockPos): Boolean {
            for (i in 1..8) {
                val state = deepSeaFish.entityWorld.getBlockState(pos.down(i))
                if (state.isOf(Blocks.WATER)) {
                    return true
                }
            }
            return false
        }
    }


}
