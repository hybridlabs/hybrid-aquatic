package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import dev.hybridlabs.aquatic.entity.misc.ArgonautEntity
import net.minecraft.core.Direction
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerLevel
import net.minecraft.stats.Stats
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.MobSpawnType
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
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
        level: Level?,
        lines: MutableList<Component>,
        context: TooltipFlag
    ) {
        val tag = stack.tag ?: return

        if (tag.contains("ShellColor")) {
            val color = ArgonautEntity.ShellColor.byId(tag.getInt("ShellColor"))
            lines.add(Component.translatable("tooltip.hybrid-aquatic.argonaut.shell", color.name.uppercase()))
        }

        if (tag.contains("SailColor")) {
            val color = ArgonautEntity.SailColor.byId(tag.getInt("SailColor"))
            lines.add(Component.translatable("tooltip.hybrid-aquatic.argonaut.sail", color.name.uppercase()))
        }

        if (tag.contains("Glowing") && tag.getBoolean("Glowing")) {
            lines.add(Component.translatable("tooltip.hybrid-aquatic.argonaut.glowing"))
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

        val entity: ArgonautEntity? = HybridAquaticEntityTypes.ARGONAUT.get().spawn(
            level,
            stack,
            context.player,
            spawnPos,
            MobSpawnType.SPAWN_EGG,
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
        val tag = stack.tag ?: return

        if (tag.contains("ShellColor")) {
            val shellColorId = tag.getInt("ShellColor")
            entity.setShellColor(ArgonautEntity.ShellColor.byId(shellColorId))
        }

        if (tag.contains("SailColor")) {
            val sailColorId = tag.getInt("SailColor")
            entity.setSailColor(ArgonautEntity.SailColor.byId(sailColorId))
        }

        if (tag.contains("Glowing")) {
            entity.setGlowing(tag.getBoolean("Glowing"))
        }
    }

    override fun use(level: Level, player: Player, hand: InteractionHand): InteractionResultHolder<ItemStack> {
        val stack = player.getItemInHand(hand)
        val hit = getPlayerPOVHitResult(level, player, ClipContext.Fluid.SOURCE_ONLY)

        if (hit.type != HitResult.Type.BLOCK) {
            return InteractionResultHolder.pass(stack)
        }

        if (level !is ServerLevel) {
            return InteractionResultHolder.success(stack)
        }

        val pos = hit.blockPos

        if (level.getBlockState(pos).block !is LiquidBlock) {
            return InteractionResultHolder.pass(stack)
        }

        val entity: ArgonautEntity? = HybridAquaticEntityTypes.ARGONAUT.get().spawn(
            level,
            stack,
            player,
            pos,
            MobSpawnType.SPAWN_EGG,
            false,
            false
        )

        if (entity == null) {
            return InteractionResultHolder.pass(stack)
        }

        stack.tag?.let { tag ->
            if (tag.contains("ShellColor")) {
                entity.setShellColor(ArgonautEntity.ShellColor.byId(tag.getInt("ShellColor")))
            }
            if (tag.contains("SailColor")) {
                entity.setSailColor(ArgonautEntity.SailColor.byId(tag.getInt("SailColor")))
            }
            if (tag.contains("Glowing")) {
                entity.setGlowing(tag.getBoolean("Glowing"))
            }
        }

        if (!player.abilities.instabuild) {
            stack.shrink(1)
        }

        player.awardStat(Stats.ITEM_USED.get(this))
        level.gameEvent(player, GameEvent.ENTITY_PLACE, entity.position())

        return InteractionResultHolder.consume(stack)
    }
}