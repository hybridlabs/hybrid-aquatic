package dev.hybridlabs.aquatic.block.egg

import dev.hybridlabs.aquatic.entity.HybridAquaticEntityTypes
import net.minecraft.block.BlockState
import net.minecraft.block.TurtleEggBlock
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.server.world.ServerWorld
import net.minecraft.sound.SoundCategory
import net.minecraft.sound.SoundEvents
import net.minecraft.util.math.BlockPos
import net.minecraft.util.math.random.Random

class LoggerheadTurtleEggBlock(settings: Settings) : TurtleEggBlock(settings) {
    @Deprecated("Deprecated in Java")
    override fun randomTick(state: BlockState, world: ServerWorld, pos: BlockPos, random: Random) {
        if (this.shouldHatchProgress(world) && isSandBelow(world, pos)) {
            val i = state.get(HATCH)
            if (i < 2) {
                world.playSound(
                    null as PlayerEntity?,
                    pos,
                    SoundEvents.ENTITY_TURTLE_EGG_CRACK,
                    SoundCategory.BLOCKS,
                    0.7f,
                    0.9f + random.nextFloat() * 0.2f
                )
                world.setBlockState(pos, state.with(HATCH, i + 1) as BlockState, 2)
            } else {
                world.playSound(
                    null as PlayerEntity?,
                    pos,
                    SoundEvents.ENTITY_TURTLE_EGG_HATCH,
                    SoundCategory.BLOCKS,
                    0.7f,
                    0.9f + random.nextFloat() * 0.2f
                )
                world.removeBlock(pos, false)

                for (j in 0 until state.get(EGGS) as Int) {
                    world.syncWorldEvent(2001, pos, getRawIdFromState(state))
                    val turtleEntity = HybridAquaticEntityTypes.LOGGERHEAD_TURTLE.create(world)
                    if (turtleEntity != null) {
                        turtleEntity.breedingAge = -24000
                        turtleEntity.homePos = pos
                        turtleEntity.refreshPositionAndAngles(
                            pos.x.toDouble() + 0.3 + j.toDouble() * 0.2,
                            pos.y.toDouble(),
                            pos.z.toDouble() + 0.3,
                            0.0f,
                            0.0f
                        )
                        world.spawnEntity(turtleEntity)
                    }
                }
            }
        }
    }
}