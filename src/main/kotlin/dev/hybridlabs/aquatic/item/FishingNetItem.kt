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
import net.minecraft.nbt.NbtCompound
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand

class FishingNetItem(settings: Settings): Item(settings) {
    override fun useOnEntity(stack: ItemStack, user: PlayerEntity, entity: LivingEntity, hand: Hand): ActionResult {
        if (!alreadyHasFish(stack) && entity.type.isIn(HybridAquaticEntityTags.CAN_USE_FISHING_NET_ON)) {
            writeEntityToNet(entity, user, hand)
            entity.remove(Entity.RemovalReason.DISCARDED)
            return ActionResult.SUCCESS
        }

        return super.useOnEntity(stack, user, entity, hand)
    }

    override fun useOnBlock(context: ItemUsageContext): ActionResult {
        val world = context.world

        if (!world.isClient) {
            val stack = context.stack
            stack.get(DataComponentTypes.ENTITY_DATA)?.also { entityComponent ->
                val type = EntityType.fromNbt(entityComponent.copyNbt()).orElse(null)
                if (type == null) {
                    return@also
                }

                val entity = type.create(world) ?: return ActionResult.PASS
                entityComponent.applyToEntity(entity)
                stack.remove(DataComponentTypes.ENTITY_DATA)
                entity.setPosition(context.hitPos)
                world.spawnEntity(entity)
                return ActionResult.SUCCESS
            }
        }

        return super.useOnBlock(context)
    }

    companion object {
        fun writeEntityToNet(entity: Entity, user: PlayerEntity, hand: Hand) {
            val entityCompound = NbtCompound()
            entity.saveNbt(entityCompound)
            entityCompound.putBoolean("PersistenceRequired", true)
            entityCompound.putBoolean("FromFishingNet", true)
            val stack = user.getStackInHand(hand)
            stack.set(DataComponentTypes.ENTITY_DATA, NbtComponent.of(entityCompound))
        }

        fun alreadyHasFish(stack: ItemStack): Boolean {
            return stack.contains(DataComponentTypes.ENTITY_DATA)
        }
    }
}
