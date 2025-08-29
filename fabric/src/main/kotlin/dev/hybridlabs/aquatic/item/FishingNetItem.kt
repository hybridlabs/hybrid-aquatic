package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.nbt.CompoundTag
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.context.UseOnContext
import net.minecraft.world.level.Level
import java.util.*

class FishingNetItem(settings: Properties?) : Item(settings) {

    override fun interactLivingEntity(
        stack: ItemStack,
        user: Player,
        entity: LivingEntity,
        hand: InteractionHand
    ): InteractionResult {
        val validFishForNet = entity.type.`is`(HybridAquaticEntityTags.CAN_USE_FISHING_NET_ON)

        if (!alreadyHasFish(stack) && validFishForNet) {
            writeEntityToNet(entity, user, hand)
            entity.remove(Entity.RemovalReason.DISCARDED)
            return InteractionResult.SUCCESS
        }
        return super.interactLivingEntity(stack, user, entity, hand)
    }

    override fun useOn(context: UseOnContext): InteractionResult? {
        val world: Level = context.level

        if (!world.isClientSide) {
            val nbtCopy = context.itemInHand.tag?.copy() ?: return super.useOn(context)

            val optionalEntity = getEntityFromNBT(nbtCopy)

            if (optionalEntity.isPresent) {
                val entity = optionalEntity.get().create(context.level) ?: return InteractionResult.FAIL
                entity.load(context.itemInHand.tag?.getCompound(ENTITY_KEY))
                context.itemInHand.tag?.remove(ENTITY_KEY)

                entity.setPos(context.clickedPos.center)
                (world as ServerLevel).addFreshEntity(entity)
                return InteractionResult.SUCCESS
            }
        }
        return super.useOn(context)
    }


    companion object {
        private const val ENTITY_KEY: String = "storedEntity"

        fun writeEntityToNet(entity: Entity, user: Player, hand: InteractionHand) {
            val entityCompound = CompoundTag()
            entity.save(entityCompound)
            entityCompound.putBoolean("PersistenceRequired", true)
            entityCompound.putBoolean("FromFishingNet", true)
            val itemStack = user.getItemInHand(hand)
            itemStack.orCreateTag.put(ENTITY_KEY, entityCompound)
        }

        fun getEntityFromNBT(nbt: CompoundTag): Optional<EntityType<*>> {
            val storedNBT = nbt.getCompound(ENTITY_KEY)
            if (storedNBT != null) {
                return EntityType.by(storedNBT)
            }
            return Optional.empty()
        }

        fun alreadyHasFish(stack: ItemStack): Boolean {
            val nbtCopy = stack.tag?.copy() ?: return false
            val entityNBT = nbtCopy.getCompound(ENTITY_KEY) ?: return false

            return !entityNBT.isEmpty
        }
    }
}
