package dev.hybridlabs.aquatic.block

import dev.hybridlabs.aquatic.effect.HAMobEffects
import net.minecraft.core.BlockPos
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.entity.AreaEffectCloud
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.Explosion
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.entity.BlockEntity
import net.minecraft.world.level.block.state.BlockState

class CrystallineSulfurBlock(settings: Properties): Block(settings) {

    override fun playerDestroy(
        level: Level,
        player: Player,
        pos: BlockPos,
        state: BlockState,
        blockEntity: BlockEntity?,
        tool: ItemStack
    ) {
        super.playerDestroy(level, player, pos, state, blockEntity, tool)

        if (level.isClientSide) return

        val cloud = AreaEffectCloud(level, pos.x + 0.5, pos.y + 0.5, pos.z + 0.5)

        cloud.radius = 2.5f
        cloud.radiusOnUse = -0.5f
        cloud.radiusPerTick = -0.01f
        cloud.duration = 100

        cloud.addEffect(MobEffectInstance(MobEffects.POISON, 100, 1))
        cloud.addEffect(MobEffectInstance(HAMobEffects.CORROSION.get(), 100, 0))

        level.addFreshEntity(cloud)
    }

    override fun wasExploded(level: Level, pos: BlockPos, explosion: Explosion) {
        super.wasExploded(level, pos, explosion)

        if (level.isClientSide) return

        level.explode(
            null,
            pos.x + 0.5,
            pos.y + 0.5,
            pos.z + 0.5,
            2.5f,
            Level.ExplosionInteraction.NONE
        )

        val cloud = AreaEffectCloud(level, pos.x + 0.5, pos.y + 0.5, pos.z + 0.5)

        cloud.radius = 5.0f
        cloud.radiusOnUse = -0.5f
        cloud.radiusPerTick = -0.01f
        cloud.duration = 100

        cloud.addEffect(MobEffectInstance(MobEffects.POISON, 100, 1))
        cloud.addEffect(MobEffectInstance(HAMobEffects.CORROSION.get(), 100, 1))

        level.addFreshEntity(cloud)
    }
}