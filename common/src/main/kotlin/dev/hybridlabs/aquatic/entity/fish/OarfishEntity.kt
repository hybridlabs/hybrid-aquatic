package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.entity.ai.MobTargetConfiguration
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.core.BlockPos
import net.minecraft.util.RandomSource
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.MobSpawnType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.level.Level
import net.minecraft.world.level.ServerLevelAccessor
import software.bernie.geckolib.constant.DefaultAnimations
import software.bernie.geckolib.core.animation.AnimatableManager
import software.bernie.geckolib.core.animation.AnimationController
import software.bernie.geckolib.core.animation.AnimationState

@Suppress("DEPRECATION", "UNUSED_PARAMETER")
class OarfishEntity(type: EntityType<out OarfishEntity>, world: Level) :
    HybridAquaticFishEntity(type, world) {

    override fun getTargetConfig() = MobTargetConfiguration.ofPrey(
        HybridAquaticEntityTags.ALL_SHARKS
    )

    override fun getMaxSpawnClusterSize(): Int {
        return 1
    }

    override fun shouldFlopOnLand(): Boolean {
        return false
    }

    override fun registerControllers(controllers: AnimatableManager.ControllerRegistrar) {
        controllers.add(
            AnimationController(this, "Flop/Idle/Swim/Feed", 5
            ) { state: AnimationState<OarfishEntity> ->
                when {
                    state.isMoving && isUnderWater -> state.setAndContinue(DefaultAnimations.SWIM)
                    !this.isUnderWater && !this.isSwimming && this.moistness < 595 -> state.setAndContinue(FLOP_ANIMATION)
                    else -> state.setAndContinue(DefaultAnimations.IDLE)
                }
            }
        )
    }

    companion object {

        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 12.0)
                .add(Attributes.MOVEMENT_SPEED, 0.5)
                .add(Attributes.ATTACK_DAMAGE, 2.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 16.0)
        }

        fun canSpawn(
            type: EntityType<out OarfishEntity>,
            world: ServerLevelAccessor,
            reason: MobSpawnType,
            pos: BlockPos,
            random: RandomSource,
        ): Boolean {
            val shallowSpawn = (world.level.chunkSource.generator.seaLevel - 12)..(world.level.chunkSource.generator.seaLevel - 2)
            val deepSpawn = (world.level.chunkSource.generator.seaLevel - 256)..(world.level.chunkSource.generator.seaLevel - 48)

            val spawnY = if (world.level.isThundering) shallowSpawn else deepSpawn

            return pos.y in spawnY && world.isWaterAt(pos)
        }
    }
}
