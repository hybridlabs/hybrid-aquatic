package dev.hybridlabs.aquatic.entity.cephalopod

import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.entity.EntityType
import net.minecraft.entity.attribute.DefaultAttributeContainer
import net.minecraft.entity.attribute.EntityAttributes
import net.minecraft.world.World
import kotlin.random.Random

class CuttlefishEntity(entityType: EntityType<out CuttlefishEntity>, world: World) :
    HybridAquaticCephalopodEntity(
        entityType,
        world,
        emptyMap(),
        HybridAquaticEntityTags.CRUSTACEAN,
        HybridAquaticEntityTags.SHARK,
        true,
        false
    ) {

    private var tickCounter = 0
    private var sprintCounter = 0
    var randomValue = Random.nextInt(0, 9)

    override fun tick() {
        super.tick()

        tickCounter++

        if (this.isSprinting) {
            sprintCounter++
            if (sprintCounter >= 10) {
                randomValue = if (randomValue == 3) 4 else 3
                sprintCounter = 0
            }
        } else {
            if (tickCounter >= 3600) {
                randomValue = Random.nextInt(0, 12)
                tickCounter = 0
            }
        }
    }

    companion object {
        fun createMobAttributes(): DefaultAttributeContainer.Builder {
            return createLivingAttributes()
                .add(EntityAttributes.GENERIC_MAX_HEALTH, 6.0)
                .add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.6)
                .add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 2.0)
                .add(EntityAttributes.GENERIC_ATTACK_KNOCKBACK, 0.0)
                .add(EntityAttributes.GENERIC_FOLLOW_RANGE, 8.0)
        }
    }

    override fun getMaxSize(): Int {
        return 5
    }

    override fun getMinSize(): Int {
        return -5
    }
}