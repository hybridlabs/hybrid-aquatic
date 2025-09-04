package dev.hybridlabs.aquatic.entity.fish

import dev.hybridlabs.aquatic.entity.goal.FishJumpGoal
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.ai.attributes.AttributeSupplier
import net.minecraft.world.entity.ai.attributes.Attributes
import net.minecraft.world.level.Level
import net.minecraft.world.phys.Vec3

<<<<<<<< HEAD:common/src/main/kotlin/dev/hybridlabs/aquatic/entity/fish/AfricanButterflyEntity.kt
class AfricanButterflyEntity(entityType: EntityType<out AfricanButterflyEntity>, world: Level) :
========
class AfricanButterflyfishEntity(entityType: EntityType<out AfricanButterflyfishEntity>, world: World) :
>>>>>>>> latest:common/src/main/kotlin/dev/hybridlabs/aquatic/entity/fish/AfricanButterflyfishEntity.kt
    HybridAquaticFishEntity(
        entityType, world,
        listOf(HybridAquaticEntityTags.NONE),
        listOf(
            HybridAquaticEntityTags.MEDIUM_PREY,
            HybridAquaticEntityTags.LARGE_PREY,
            HybridAquaticEntityTags.SHARK
        )
    ) {

    private var isGliding = false

    override fun getMaxSpawnClusterSize(): Int {
        return 1
    }

    override fun registerGoals() {
        super.registerGoals()
        targetSelector.addGoal(5, FishJumpGoal(this, 10))
    }

    override fun tick() {
        super.tick()

        if (!this.isInWater && !onGround()) {
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

    companion object {
        fun createMobAttributes(): AttributeSupplier.Builder {
            return createLivingAttributes()
<<<<<<<< HEAD:common/src/main/kotlin/dev/hybridlabs/aquatic/entity/fish/AfricanButterflyEntity.kt
                .add(Attributes.MAX_HEALTH, 3.0)
                .add(Attributes.MOVEMENT_SPEED, 0.6)
                .add(Attributes.ATTACK_DAMAGE, 1.0)
                .add(Attributes.ATTACK_KNOCKBACK, 0.0)
                .add(Attributes.FOLLOW_RANGE, 4.0)
========
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 2.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.5)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 1.0)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 4.0)
>>>>>>>> latest:common/src/main/kotlin/dev/hybridlabs/aquatic/entity/fish/AfricanButterflyfishEntity.kt
        }
    }
}
