package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.component.DataComponentTypes
import net.minecraft.component.type.NbtComponent
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.ItemUsageContext
import net.minecraft.item.tooltip.TooltipData
import net.minecraft.nbt.NbtCompound
import net.minecraft.sound.SoundEvents
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import java.util.*

@Suppress("DEPRECATION")
class FishingNetItem(settings: Settings?): Item(settings) {
    override fun useOnEntity(stack: ItemStack, user: PlayerEntity, entity: LivingEntity, hand: Hand): ActionResult {
        val validFishForNet = entity.type.isIn(HybridAquaticEntityTags.CAN_USE_FISHING_NET_ON)

        if (!alreadyHasFish(stack) && validFishForNet) {
            writeEntityToNet(entity, user, hand)
            entity.remove(Entity.RemovalReason.DISCARDED)
            return ActionResult.SUCCESS
        }
        return super.useOnEntity(stack, user, entity, hand)
    }

    override fun useOnBlock(context: ItemUsageContext): ActionResult {
        val world = context.world

        if (!world.isClient) {
            val storedData = context.stack.get(DataComponentTypes.CUSTOM_DATA)?.nbt ?: return super.useOnBlock(context)

            val optionalEntity = getEntityFromNBT(storedData)
            if (optionalEntity.isPresent) {
                val entityType = optionalEntity.get()
                val entity = entityType.create(world) ?: return ActionResult.FAIL

                entity.readNbt(storedData)
                entity.setPosition(context.hitPos)
                world.spawnEntity(entity)

                // Clear stored fish
                context.stack.remove(DataComponentTypes.CUSTOM_DATA)

                context.player?.playSound(SoundEvents.ITEM_BUNDLE_REMOVE_ONE, 1.0f, 1.0f)
                return ActionResult.SUCCESS
            }
        }
        return super.useOnBlock(context)
    }

    override fun getTooltipData(stack: ItemStack): Optional<TooltipData> {
        return super.getTooltipData(stack)
    }

    companion object {
        fun writeEntityToNet(entity: Entity, user: PlayerEntity, hand: Hand) {
            val entityCompound = NbtCompound()
            entity.saveNbt(entityCompound)
            entityCompound.putBoolean("PersistenceRequired", true)
            entityCompound.putBoolean("FromFishingNet", true)

            val itemStack = user.getStackInHand(hand)

            val nbtComponent = NbtComponent.of(entityCompound)
            itemStack.set(DataComponentTypes.CUSTOM_DATA, nbtComponent)
        }

        fun getEntityFromNBT(nbt: NbtCompound): Optional<EntityType<*>> {
            return EntityType.fromNbt(nbt)
        }

        fun alreadyHasFish(stack: ItemStack): Boolean {
            val storedData = stack.get(DataComponentTypes.CUSTOM_DATA)?.nbt ?: return false
            return !storedData.isEmpty
        }
    }
}