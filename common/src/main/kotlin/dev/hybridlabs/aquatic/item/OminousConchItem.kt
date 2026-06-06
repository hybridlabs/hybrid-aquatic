package dev.hybridlabs.aquatic.item

import dev.hybridlabs.aquatic.entity.HAEntityTypes
import dev.hybridlabs.aquatic.tag.HABiomeTags
import net.minecraft.ChatFormatting
import net.minecraft.network.chat.Component
import net.minecraft.server.level.ServerLevel
import net.minecraft.tags.BiomeTags
import net.minecraft.tags.TagKey
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResultHolder
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.Instrument
import net.minecraft.world.item.InstrumentItem
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.level.Level

class OminousConchItem(
    properties: Properties,
    instruments: TagKey<Instrument>
) : InstrumentItem(properties, instruments) {

    companion object {
        private const val TAG_HAS_SUMMONED = "hasSummoned"
        private const val SUMMON_DELAY_TICKS = 140
    }

    override fun appendHoverText(
        stack: ItemStack,
        level: Level?,
        lines: MutableList<Component>,
        context: TooltipFlag
    ) {
        val tag = stack.tag

        lines.add(
            Component.translatable("item.hybrid-aquatic.ominous_conch.function")
                .withStyle(ChatFormatting.GRAY)
        )

        val hasSummoned = tag?.getBoolean("hasSummoned") == true

        if (!hasSummoned) {
            lines.add(
                Component.translatable("tooltip.hybrid-aquatic.ominous_conch.unused")
                    .withStyle(ChatFormatting.DARK_PURPLE)
            )
        } else {
            lines.add(
                Component.translatable("tooltip.hybrid-aquatic.ominous_conch.used")
                    .withStyle(ChatFormatting.GRAY)
            )
        }
    }

    override fun use(level: Level, player: Player, hand: InteractionHand): InteractionResultHolder<ItemStack> {
        val result = super.use(level, player, hand)
        val stack = player.getItemInHand(hand)

        if (level.isClientSide) return result

        val tag = stack.orCreateTag

        if (tag.getBoolean(TAG_HAS_SUMMONED)) return result

        val biome = level.getBiome(player.blockPosition())
        if (!biome.`is`(BiomeTags.IS_DEEP_OCEAN) || !biome.`is`(HABiomeTags.ALL_TRENCHES)) return result
        if (!player.isUnderWater) return result

        tag.putBoolean(TAG_HAS_SUMMONED, true)

        val serverLevel = level as ServerLevel
        val pos = player.blockPosition()

        val startTick = serverLevel.server.tickCount

        serverLevel.server.execute {
            spawnAfterCooldown(serverLevel, pos, startTick)
        }

        return result
    }

    private fun spawnAfterCooldown(
        level: ServerLevel,
        pos: net.minecraft.core.BlockPos,
        startTick: Int
    ) {
        val server = level.server

        if (server.tickCount >= startTick + SUMMON_DELAY_TICKS) {

            val shellBeast = HAEntityTypes.SHELL_BEAST.get().create(level)

            if (shellBeast != null) {

                shellBeast.moveTo(
                    pos.x + 0.5,
                    pos.y.toDouble(),
                    pos.z + 0.5,
                    0f,
                    0f
                )

                level.addFreshEntity(shellBeast)

                val random = level.random

                for (i in 0 until 6) {

                    val offsetX = (random.nextDouble() - 0.5) * 8.0
                    val offsetZ = (random.nextDouble() - 0.5) * 8.0

                    val beakling = HAEntityTypes.BEAKLING.get().create(level)

                    if (beakling != null) {

                        beakling.moveTo(
                            shellBeast.x + offsetX,
                            shellBeast.y,
                            shellBeast.z + offsetZ,
                            random.nextFloat() * 360f,
                            0f
                        )

                        beakling.setOwner(shellBeast)

                        level.addFreshEntity(beakling)
                    }
                }
            }

        } else {
            server.execute { spawnAfterCooldown(level, pos, startTick) }
        }
    }
}