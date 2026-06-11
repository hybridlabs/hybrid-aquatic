package dev.hybridlabs.aquatic.entity.ai.goal

import dev.hybridlabs.aquatic.entity.base.HAWaterAnimal
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.ai.goal.Goal
import net.minecraft.world.entity.item.ItemEntity
import java.util.*

class WaterAnimalEatItemGoal(
    private val waterAnimal: HAWaterAnimal,
) : Goal() {

    private val ALLOWED_ITEMS = { item: ItemEntity ->
        !item.hasPickUpDelay() &&
                item.isAlive &&
                waterAnimal.isFood(item.item)
    }

    init {
        this.flags = EnumSet.of(Flag.LOOK, Flag.MOVE)
    }

    override fun canUse(): Boolean {
        if (!waterAnimal.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty) {
            return false
        }

        if (waterAnimal.target != null || waterAnimal.lastHurtByMob != null) {
            return false
        }

        if (!waterAnimal.isInWater) {
            return false
        }

        if (waterAnimal.random.nextInt(reducedTickDelay(10)) != 0) {
            return false
        }

        val list = waterAnimal.level().getEntitiesOfClass(
            ItemEntity::class.java,
            waterAnimal.boundingBox.inflate(8.0, 8.0, 8.0),
            ALLOWED_ITEMS
        )

        return list.isNotEmpty()
    }

    override fun tick() {
        val list = waterAnimal.level().getEntitiesOfClass(
            ItemEntity::class.java,
            waterAnimal.boundingBox.inflate(8.0, 8.0, 8.0),
            ALLOWED_ITEMS
        )

        if (waterAnimal.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty && list.isNotEmpty()) {
            waterAnimal.navigation.moveTo(list[0] as Entity, 1.2)
        }
    }

    override fun start() {
        val list = waterAnimal.level().getEntitiesOfClass(
            ItemEntity::class.java,
            waterAnimal.boundingBox.inflate(8.0, 8.0, 8.0),
            ALLOWED_ITEMS
        )

        if (list.isNotEmpty()) {
            waterAnimal.navigation.moveTo(list[0] as Entity, 1.2)
        }
    }
}