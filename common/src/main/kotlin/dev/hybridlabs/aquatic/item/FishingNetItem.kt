package dev.hybridlabs.aquatic.item

import dev.hybridlabs.hapi.tag.HAPIEntityTags
import net.minecraft.ChatFormatting
import net.minecraft.core.component.DataComponents
import net.minecraft.nbt.CompoundTag
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerLevel
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.EntityType
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.item.component.CustomData
import net.minecraft.world.level.Level
import net.minecraft.world.level.material.Fluids
import java.util.*

class FishingNetItem(settings: Properties) : Item(settings) {

    override fun appendHoverText(
        stack: ItemStack,
        context: TooltipContext,
        lines: MutableList<Component>,
        options: TooltipFlag
    ) {
        lines.add(
            Component.translatable("item.hybrid_aquatic.fishing_net.function")
                .withStyle(ChatFormatting.GRAY)
        )
        lines.add(
            Component.translatable("item.hybrid_aquatic.fishing_net.properties")
                .withStyle(ChatFormatting.GRAY)
        )

        val customData = stack.get(DataComponents.CUSTOM_DATA) ?: return

        val optionalEntity = getEntityFromNBT(customData)
        if (optionalEntity.isPresent) {
            val entityName = optionalEntity.get().description ?: return
            lines.add(
                Component.translatable(
                    "item.hybrid_aquatic.fishing_net.description",
                    entityName
                )
            )
        }
    }

    override fun interactLivingEntity(
        stack: ItemStack,
        user: Player,
        entity: LivingEntity,
        hand: InteractionHand
    ): InteractionResult {
        val validFishForNet = entity.type.`is`(HAPIEntityTags.CAN_USE_FISHING_NET_ON)

        if (!alreadyHasFish(stack) && validFishForNet) {
            writeEntityToNet(entity, user, hand)
            entity.remove(Entity.RemovalReason.DISCARDED)
            return InteractionResult.SUCCESS
        }
        return super.interactLivingEntity(stack, user, entity, hand)
    }

    override fun use(level: Level, player: Player, hand: InteractionHand): InteractionResultHolder<ItemStack> {
        val stack = player.getItemInHand(hand)

        if (!level.isClientSide) {
            val customData = stack.components.get(DataComponents.CUSTOM_DATA)
            if (customData != null) {
                val optionalEntity = getEntityFromNBT(customData)
                if (optionalEntity.isPresent) {
                    val hitResult =
                        getPlayerPOVHitResult(level, player, net.minecraft.world.level.ClipContext.Fluid.SOURCE_ONLY)
                    if (hitResult.type != net.minecraft.world.phys.HitResult.Type.BLOCK) {
                        return InteractionResultHolder.pass(stack)
                    }

                    val pos = hitResult.blockPos
                    val clickedFace = hitResult.direction
                    var spawnPos = pos.relative(clickedFace)

                    val fluidState = level.getFluidState(pos)
                    if (fluidState.`is`(Fluids.WATER)) {
                        spawnPos = pos
                    }

                    val entityType = optionalEntity.get()
                    val entity = entityType.create(level) ?: return InteractionResultHolder.fail(stack)
                    val customData =
                        stack.components.get(DataComponents.CUSTOM_DATA) ?: return InteractionResultHolder.fail(stack)
                    val tag = customData.copyTag()
                    val entityData = tag.getCompound(ENTITY_KEY)
                    entity.load(entityData)

                    entity.moveTo(
                        spawnPos.x + 0.5,
                        spawnPos.y + 0.1,
                        spawnPos.z + 0.5,
                        level.random.nextFloat() * 360f,
                        0f
                    )

                    (level as ServerLevel).addFreshEntity(entity)
                    stack.update(DataComponents.CUSTOM_DATA, CustomData.EMPTY) { data ->
                        data.update { compoundTag -> compoundTag.remove(ENTITY_KEY) }
                    }

                    return InteractionResultHolder.success(stack)
                }
            }
        }

        return InteractionResultHolder.pass(stack)
    }

    companion object {
        private const val ENTITY_KEY: String = "storedEntity"

        fun writeEntityToNet(entity: Entity, user: Player, hand: InteractionHand) {
            val entityCompound = CompoundTag()
            entity.save(entityCompound)
            entityCompound.putBoolean("PersistenceRequired", true)
            entityCompound.putBoolean("FromFishingNet", true)
            val itemStack = user.getItemInHand(hand)
            itemStack.update(DataComponents.CUSTOM_DATA, CustomData.EMPTY) { data ->
                data.update { compoundTag -> compoundTag.put(ENTITY_KEY, entityCompound) }
            }
        }

        fun getEntityFromNBT(customData: CustomData): Optional<EntityType<*>?> {
            val nbt = customData.copyTag()
            val storedNBT = nbt.getCompound(ENTITY_KEY)
            return EntityType.by(storedNBT)
        }

        fun alreadyHasFish(stack: ItemStack): Boolean {
            val customData = stack.components.get(DataComponents.CUSTOM_DATA)
            return (customData?.contains(ENTITY_KEY) ?: false)
        }
    }
}
