package dev.hybridlabs.aquatic.entity.ai.goal

import dev.hybridlabs.aquatic.entity.base.HAWaterAnimal
import net.minecraft.tags.TagKey
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.ai.goal.Goal
import net.minecraft.world.entity.item.ItemEntity
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import java.util.*
import java.util.function.Predicate

class WaterAnimalEatItemGoal(
    private val waterAnimal: HAWaterAnimal,
    private val targetItem: TagKey<Item>,
) : Goal() {

    private val ALLOWED_ITEMS = Predicate<ItemEntity> { item ->
        !item.hasPickUpDelay() &&
                item.isAlive &&
                item.item.`is`(targetItem)
    }

    init {
        this.flags = EnumSet.of<Flag>(Flag.LOOK, Flag.MOVE)
    }

    override fun canUse(): Boolean {
        if (!waterAnimal.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty) {
            return false
        } else if (waterAnimal.target == null && waterAnimal.lastHurtByMob == null) {
            if (!waterAnimal.isInWater) {
                return false
            } else if (waterAnimal.getRandom().nextInt(reducedTickDelay(10)) != 0) {
                return false
            } else {
                val list: MutableList<ItemEntity?> = waterAnimal.level().getEntitiesOfClass(
                    ItemEntity::class.java,
                    waterAnimal.boundingBox.inflate(8.0, 8.0, 8.0),
                    ALLOWED_ITEMS
                )
                return !list.isEmpty() && waterAnimal.getItemBySlot(EquipmentSlot.MAINHAND).isEmpty
            }
        } else {
            return false
        }
    }

    override fun tick() {
        val list: MutableList<ItemEntity?> = waterAnimal.level().getEntitiesOfClass(
            ItemEntity::class.java,
            waterAnimal.boundingBox.inflate(8.0, 8.0, 8.0),
            ALLOWED_ITEMS
        )
        val itemstack: ItemStack = waterAnimal.getItemBySlot(EquipmentSlot.MAINHAND)
        if (itemstack.isEmpty && !list.isEmpty()) {
            waterAnimal.getNavigation().moveTo(list[0] as Entity, 1.2)
        }
    }

    override fun start() {
        val list: MutableList<ItemEntity?> = waterAnimal.level().getEntitiesOfClass(
            ItemEntity::class.java,
            waterAnimal.boundingBox.inflate(8.0, 8.0, 8.0),
            ALLOWED_ITEMS
        )
        if (!list.isEmpty()) {
            waterAnimal.getNavigation().moveTo(list[0] as Entity, 1.2)
        }
    }
}