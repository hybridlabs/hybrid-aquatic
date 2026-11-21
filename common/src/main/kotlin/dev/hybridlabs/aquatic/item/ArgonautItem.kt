package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.entity.HAEntityTypes
import dev.hybridlabs.aquatic.entity.misc.ArgonautEntity
import net.minecraft.core.Direction
import net.minecraft.core.component.DataComponents
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerLevel
import net.minecraft.stats.Stats
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.EntitySpawnReason
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.component.TooltipDisplay
import java.util.function.Consumer
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.item.context.UseOnContext
import net.minecraft.world.level.ClipContext
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.LiquidBlock
import net.minecraft.world.level.gameevent.GameEvent
import net.minecraft.world.phys.HitResult

class ArgonautItem(properties: Properties) : Item(properties) {

    override fun appendHoverText(
        stack: ItemStack,
        context: TooltipContext,
        tooltipDisplay: TooltipDisplay,
        lines: Consumer<Component>,
        tooltipFlag: TooltipFlag
    ) {
        val tag = stack.get(DataComponents.CUSTOM_DATA)?.copyTag() ?: return

        if (tag.contains("ShellColor")) {
            val color = ArgonautEntity.ShellColor.byId(tag.getIntOr("ShellColor", 0))
            lines.accept(Component.translatable("tooltip.hybrid_aquatic.argonaut.shell", color.name.uppercase()))
        }

        if (tag.contains("SailColor")) {
            val color = ArgonautEntity.SailColor.byId(tag.getIntOr("SailColor", 0))
            lines.accept(Component.translatable("tooltip.hybrid_aquatic.argonaut.sail", color.name.uppercase()))
        }

        if (tag.contains("Glowing") && tag.getBooleanOr("Glowing", false)) {
            lines.accept(Component.translatable("tooltip.hybrid_aquatic.argonaut.glowing"))
        }
    }

    override fun useOn(context: UseOnContext): InteractionResult {
        val level = context.level

        if (level !is ServerLevel) {
            return InteractionResult.SUCCESS
        }

        val stack = context.itemInHand
        val pos = context.clickedPos
        val face: Direction = context.clickedFace
        val state = level.getBlockState(pos)

        val spawnPos = if (state.getCollisionShape(level, pos).isEmpty) {
            pos
        } else {
            pos.relative(face)
        }

        val entity: ArgonautEntity? = HAEntityTypes.ARGONAUT.get().spawn(
            level,
            stack,
            context.player,
            spawnPos,
            EntitySpawnReason.SPAWN_ITEM_USE,
            true,
            face == Direction.UP
        )

        if (entity != null) {
            applyArgonautData(entity, stack)
            stack.shrink(1)
            level.gameEvent(context.player, GameEvent.ENTITY_PLACE, spawnPos)
        }

        return InteractionResult.CONSUME
    }

    private fun applyArgonautData(entity: ArgonautEntity, stack: ItemStack) {
        val tag = stack.get(DataComponents.CUSTOM_DATA)?.copyTag() ?: return

        if (tag.contains("ShellColor")) {
            val shellColorId = tag.getIntOr("ShellColor", 0)
            entity.setShellColor(ArgonautEntity.ShellColor.byId(shellColorId))
        }

        if (tag.contains("SailColor")) {
            val sailColorId = tag.getIntOr("SailColor", 0)
            entity.setSailColor(ArgonautEntity.SailColor.byId(sailColorId))
        }

        if (tag.contains("Glowing")) {
            entity.setGlowing(tag.getBooleanOr("Glowing", false))
        }
    }

    override fun use(level: Level, player: Player, hand: InteractionHand): InteractionResult {
        val stack = player.getItemInHand(hand)
        val customData = stack.get(DataComponents.CUSTOM_DATA)
        val hit = getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY)

        if (hit.type != HitResult.Type.BLOCK) {
            return InteractionResult.PASS
        }

        if (level !is ServerLevel) {
            return InteractionResult.SUCCESS
        }

        val pos = hit.blockPos

        if (level.getBlockState(pos).block !is LiquidBlock) {
            return InteractionResult.PASS
        }

        val entity: ArgonautEntity? = HAEntityTypes.ARGONAUT.get().spawn(
            level,
            stack,
            player,
            pos,
            EntitySpawnReason.SPAWN_ITEM_USE,
            false,
            false
        )

        if (entity == null) {
            return InteractionResult.PASS
        }

        customData?.copyTag()?.let { tag ->
            if (tag.contains("ShellColor")) {
                entity.setShellColor(ArgonautEntity.ShellColor.byId(tag.getIntOr("ShellColor", 0)))
            }
            if (tag.contains("SailColor")) {
                entity.setSailColor(ArgonautEntity.SailColor.byId(tag.getIntOr("SailColor", 0)))
            }
            if (tag.contains("Glowing")) {
                entity.setGlowing(tag.getBooleanOr("Glowing", false))
            }
        }

        if (!player.abilities.instabuild) {
            stack.shrink(1)
        }

        player.awardStat(Stats.ITEM_USED.get(this))
        level.gameEvent(player, GameEvent.ENTITY_PLACE, entity.position())

        return InteractionResult.CONSUME
    }
}
