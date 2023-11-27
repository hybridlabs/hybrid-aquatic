package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.client.item.TooltipData
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NbtCompound
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import java.util.*

class FishingNetItem(settings: Settings?): Item(settings) {

    override fun useOnEntity(stack: ItemStack, user: PlayerEntity, entity: LivingEntity, hand: Hand): ActionResult {
        val validFishForNet = entity.type.isIn(HybridAquaticEntityTags.JELLYFISH) || entity.type.isIn(HybridAquaticEntityTags.CRITTER) || entity.type.isIn(HybridAquaticEntityTags.SMALL_PREY) || entity.type.isIn(HybridAquaticEntityTags.MEDIUM_PREY)

        if (!alreadyHasFish(stack) && validFishForNet) {
            writeEntityToNet(entity, user, hand)
            entity.remove(Entity.RemovalReason.DISCARDED)
            return ActionResult.SUCCESS
        }
        return super.useOnEntity(stack, user, entity, hand)
    }

    override fun getTooltipData(stack: ItemStack): Optional<TooltipData> {
        return super.getTooltipData(stack)
    }

    companion object {
        private const val ENTITY_KEY: String = "storedEntity"

        fun writeEntityToNet(entity: Entity, user: PlayerEntity, hand: Hand) {
            val entityCompound = NbtCompound()
            entity.saveNbt(entityCompound)
            entityCompound.putBoolean("PersistenceRequired", true)

            val itemStack = user.getStackInHand(hand)
            itemStack.orCreateNbt.put(ENTITY_KEY, entityCompound)
        }

        fun getEntityFromNBT(nbt: NbtCompound): Optional<EntityType<*>> {
            val storedNBT = nbt.getCompound(ENTITY_KEY)
            if (storedNBT != null) {
                return EntityType.fromNbt(storedNBT)
            }
            return Optional.empty()
        }

        fun alreadyHasFish(stack: ItemStack): Boolean {
            val nbtCopy = stack.nbt?.copy() ?: return false
            val entityNBT = nbtCopy.getCompound(ENTITY_KEY) ?: return false

            return !entityNBT.isEmpty
        }
    }
}
