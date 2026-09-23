package dev.hybridlabs.aquatic.entity.ai.goal

import dev.hybridlabs.aquatic.entity.mammal.OtterEntity
import dev.hybridlabs.aquatic.entity.mammal.OtterEntity.Companion.OtterAction
import net.minecraft.world.entity.EquipmentSlot
import net.minecraft.world.entity.ai.goal.Goal
import net.minecraft.world.entity.item.ItemEntity
import net.minecraft.world.item.ItemStack
import java.util.*

class OtterFetchItemGoal(
    private val otter: OtterEntity,
    private val speedModifier: Double,
) : Goal() {

    private var itemTarget: ItemEntity? = null

    init {
        this.flags = EnumSet.of(Flag.MOVE)
    }

    private fun heldItem(): ItemStack = otter.getItemBySlot(EquipmentSlot.MAINHAND)

    private fun isCarrying(): Boolean {
        val held = heldItem()
        return !held.isEmpty && !otter.isFood(held)
    }

    override fun canUse(): Boolean {
        if (!otter.isTame() || otter.isSitting()) return false
        if (otter.owner == null) return false
        if (isCarrying()) return true
        if (!heldItem().isEmpty) return false

        itemTarget = otter.level()
            .getEntitiesOfClass(ItemEntity::class.java, otter.boundingBox.inflate(SEARCH_RANGE)) { item ->
                item.isAlive && !item.hasPickUpDelay() && otter.wantsToFetch(item)
            }
            .minByOrNull { it.distanceToSqr(otter) }

        return itemTarget != null
    }

    override fun canContinueToUse(): Boolean {
        if (!otter.isTame() || otter.isSitting() || otter.owner == null) return false
        if (isCarrying()) return true

        val item = itemTarget ?: return false
        return item.isAlive && heldItem().isEmpty && otter.wantsToFetch(item)
    }

    override fun stop() {
        itemTarget = null
        otter.navigation.stop()
    }

    override fun tick() {
        otter.setAction(if (otter.isInWater) OtterAction.SWIMMING else OtterAction.WALKING)

        if (isCarrying()) {
            deliverToOwner()
            return
        }

        val item = itemTarget ?: return
        otter.lookControl.setLookAt(item, 10.0f, otter.maxHeadXRot.toFloat())
        otter.navigation.moveTo(item, speedModifier)
    }

    private fun deliverToOwner() {
        val owner = otter.owner ?: return

        if (otter.distanceToSqr(owner) > DELIVERY_DISTANCE_SQR) {
            otter.lookControl.setLookAt(owner, 10.0f, otter.maxHeadXRot.toFloat())
            otter.navigation.moveTo(owner, speedModifier)
            return
        }

        otter.navigation.stop()

        val held = heldItem()
        otter.setItemSlot(EquipmentSlot.MAINHAND, ItemStack.EMPTY)
        otter.spawnAtLocation(held)
        otter.startFetchCooldown()
    }

    companion object {
        private const val SEARCH_RANGE = 8.0
        private const val DELIVERY_DISTANCE_SQR = OtterEntity.FETCH_DELIVERY_DISTANCE_SQR
    }
}
