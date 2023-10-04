package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.block.HybridAquaticBlocks
import dev.hybridlabs.aquatic.block.entity.FishingPlaqueBlockEntity
import dev.hybridlabs.aquatic.tag.HybridAquaticEntityTags
import net.fabricmc.fabric.impl.event.interaction.InteractionEventsRouter
import net.minecraft.block.Block
import net.minecraft.client.item.TooltipContext
import net.minecraft.entity.Entity
import net.minecraft.entity.EntityType
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.entity.projectile.FishingBobberEntity
import net.minecraft.item.*
import net.minecraft.nbt.NbtCompound
import net.minecraft.text.Text
import net.minecraft.util.ActionResult
import net.minecraft.util.Hand
import net.minecraft.world.World
import kotlin.jvm.optionals.getOrDefault
import kotlin.jvm.optionals.getOrNull

class FishingNetItem(settings: Settings?) : Item(settings) {

    override fun useOnEntity(stack: ItemStack?, user: PlayerEntity?, entity: LivingEntity?, hand: Hand?): ActionResult {
        val world : World = user!!.world!!
        if((entity!!.type.isIn(HybridAquaticEntityTags.SMALL_PREY) || entity.type.isIn(HybridAquaticEntityTags.MEDIUM_PREY)) && !world.isClient) {
            writeEntityToNet(stack, entity)
            entity.remove(Entity.RemovalReason.DISCARDED)
            return ActionResult.SUCCESS
        }
        return super.useOnEntity(stack, user, entity, hand)
    }

    override fun useOnBlock(context: ItemUsageContext?): ActionResult {
        val world: World = context!!.world
        if(!world.isClient) {
            println(context.stack.getSubNbt(ENTITY_KEY))
            val entity = getEntityFromNet(context.stack, context.world)
            val blockEntity = context.world.getBlockEntity(context.blockPos)

            if (entity != null) {
                if (blockEntity != null && blockEntity is FishingPlaqueBlockEntity) {
                    blockEntity.setStoredEntity(entity)
                } else {
                    entity.setPosition(context.hitPos)
                    world.spawnEntity(entity)
                }
                return ActionResult.SUCCESS
            }
        }
        return super.useOnBlock(context)
    }

    private fun writeEntityToNet(stack: ItemStack?, entity: Entity) {
        stack?.setSubNbt(ENTITY_KEY, entity.writeNbt(NbtCompound()))
        println(stack?.getSubNbt(ENTITY_KEY))
    }

    private fun getEntityFromNet(stack: ItemStack?, world: World?) : Entity? {
        val storedNBT = stack?.getSubNbt(ENTITY_KEY)
        if(storedNBT != null)
            return EntityType.getEntityFromNbt(storedNBT, world).getOrNull();
        return null
    }

    override fun appendTooltip(
        stack: ItemStack?,
        world: World?,
        tooltip: MutableList<Text>?,
        context: TooltipContext?
    ) {
        if(getEntityFromNet(stack, world) != null) {
            tooltip?.add(Text.literal("Stored Entity: ${getEntityFromNet(stack, world)?.displayName}"))
        }
        super.appendTooltip(stack, world, tooltip, context)
    }

    companion object {
        const val ENTITY_KEY : String = "storedEntity"
    }
}
