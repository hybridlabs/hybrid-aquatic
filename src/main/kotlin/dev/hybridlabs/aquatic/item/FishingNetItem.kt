package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.block.entity.FishingPlaqueBlockEntity
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.minecraft.client.item.TooltipContext
import net.minecraft.client.item.TooltipData
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.*
import net.minecraft.nbt.NbtCompound
import net.minecraft.text.Text
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.world.World
import java.util.*

class FishingNetItem(settings: Settings?): Item(settings) {

    override fun useOnEntity(stack: ItemStack, user: PlayerEntity, entity: LivingEntity, hand: Hand): ActionResult {
        val world: World = user.world

        if ((entity.type.isIn(HybridAquaticEntityTags.SMALL_PREY) || entity.type.isIn(HybridAquaticEntityTags.MEDIUM_PREY))) {
            writeEntityToNet(entity, user, hand)
            entity.remove(Entity.RemovalReason.DISCARDED)
            return ActionResult.SUCCESS
        }
        return super.useOnEntity(stack, user, entity, hand)
    }

    override fun useOnBlock(context: ItemUsageContext): ActionResult {
        val world: World = context.world

        if (!world.isClient) {
            val optionalEntity = getEntityFromNet(context.stack)
            val blockEntity = context.world.getBlockEntity(context.blockPos)

            if (optionalEntity.isPresent) {
                val entity = optionalEntity.get().create(context.world)
                context.stack.nbt?.remove(ENTITY_KEY)

                if (blockEntity != null && blockEntity is FishingPlaqueBlockEntity) {
                    blockEntity.setStoredEntity(entity)
                } else {
                    entity?.setPosition(context.hitPos)
                    world.spawnEntity(entity)
                }
                return ActionResult.SUCCESS
            }
        }
        return super.useOnBlock(context)
    }

    override fun appendTooltip(
        stack: ItemStack,
        world: World?,
        tooltip: MutableList<Text>,
        context: TooltipContext
    ) {
        super.appendTooltip(stack, world, tooltip, context)
    }

    override fun getTooltipData(stack: ItemStack): Optional<TooltipData> {
        return super.getTooltipData(stack)
    }

    companion object {
        const val ENTITY_KEY: String = "storedEntity"

        fun writeEntityToNet(entity: Entity, user: PlayerEntity, hand: Hand) {
            val entityCompound = NbtCompound()
            entity.saveNbt(entityCompound)
            val itemStack = user.getStackInHand(hand)
            itemStack.orCreateNbt.put(ENTITY_KEY, entityCompound)
        }

        fun getEntityFromNet(stack: ItemStack) : Optional<EntityType<*>> {
            val storedNBT = stack.nbt?.getCompound(ENTITY_KEY)
            if (storedNBT != null) {
                return EntityType.fromNbt(storedNBT)
            }
            return Optional.empty()
        }
    }
}
