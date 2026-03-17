package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import dev.hybridlabs.aquatic.tag.HybridAquaticBiomeTags
import net.minecraft.server.level.ServerLevel
import net.minecraft.tags.TagKey
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Instrument
import net.minecraft.world.item.InstrumentItem
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Level

class OminousConchItem(
    properties: Properties,
    instruments: TagKey<Instrument>
) : InstrumentItem(properties, instruments) {

    companion object {
        private const val TAG_HAS_SUMMONED = "hasSummoned"
        private const val SUMMON_DELAY_TICKS = 140
    }

    override fun use(level: Level, player: Player, hand: InteractionHand): InteractionResultHolder<ItemStack> {
        val result = super.use(level, player, hand)
        val stack = player.getItemInHand(hand)

        if (level.isClientSide) return result

        val tag = stack.orCreateTag
        val hasSummoned = tag.getBoolean(TAG_HAS_SUMMONED)

        if (hasSummoned) return result

        val biome = level.getBiome(player.blockPosition())
        if (!biome.`is`(HybridAquaticBiomeTags.ALL_TRENCHES)) return result

        val serverLevel = level as ServerLevel
        val pos = player.blockPosition()

        serverLevel.server.execute {
            serverLevel.server.tickCount.let { startTick ->
                serverLevel.server.execute {
                    waitAndSpawn(serverLevel, pos, stack, startTick)
                }
            }
        }

        return result
    }

    private fun waitAndSpawn(
        level: ServerLevel,
        pos: net.minecraft.core.BlockPos,
        stack: ItemStack,
        startTick: Int
    ) {
        val server = level.server

        if (server.tickCount >= startTick + SUMMON_DELAY_TICKS) {

            val entity = HybridAquaticEntityTypes.SHELL_BEAST.get().create(level)
            entity?.moveTo(pos.x + 0.5, pos.y.toDouble(), pos.z + 0.5, 0f, 0f)
            if (entity != null) {
                level.addFreshEntity(entity)
            }

            val tag = stack.orCreateTag
            tag.putBoolean(TAG_HAS_SUMMONED, true)

        } else {
            server.execute { waitAndSpawn(level, pos, stack, startTick) }
        }
    }
}