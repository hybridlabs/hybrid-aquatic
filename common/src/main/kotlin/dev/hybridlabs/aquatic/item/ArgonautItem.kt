package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import net.minecraft.core.Direction
import net.minecraft.server.level.ServerLevel
import net.minecraft.stats.Stats
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.Entity
import net.minecraft.world.entity.MobSpawnType
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.context.UseOnContext
import net.minecraft.world.level.ClipContext
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.LiquidBlock
import net.minecraft.world.level.gameevent.GameEvent
import net.minecraft.world.phys.HitResult

class ArgonautItem(properties: Properties) : Item(properties) {

    private val type = HybridAquaticEntityTypes.ARGONAUT.get()

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

        val entity: Entity? = type.spawn(
            level,
            stack,
            context.player,
            spawnPos,
            MobSpawnType.SPAWN_EGG,
            true,
            face == Direction.UP
        )

        if (entity != null) {
            stack.shrink(1)
            level.gameEvent(context.player, GameEvent.ENTITY_PLACE, spawnPos)
        }

        return InteractionResult.CONSUME
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

        val entity: Entity? = type.spawn(
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

        if (!player.abilities.instabuild) {
            stack.shrink(1)
        }

        player.awardStat(Stats.ITEM_USED.get(this))
        level.gameEvent(player, GameEvent.ENTITY_PLACE, entity.position())

        return InteractionResultHolder.consume(stack)
    }
}