package dev.hybridlabs.aquatic.block

import dev.hybridlabs.aquatic.entity.PrimedDepthChargeEntity
import net.minecraft.core.BlockPos
import net.minecraft.sounds.SoundEvents
import net.minecraft.sounds.SoundSource
import net.minecraft.stats.Stats
import net.minecraft.world.InteractionHand
import net.minecraft.world.InteractionResult
import net.minecraft.world.entity.LivingEntity
import net.minecraft.world.entity.player.Player
import net.minecraft.world.entity.projectile.Projectile
import net.minecraft.world.item.Items
import net.minecraft.world.level.Explosion
import net.minecraft.world.level.Level
import net.minecraft.world.level.block.Block
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.state.BlockState
import net.minecraft.world.level.block.state.StateDefinition
import net.minecraft.world.level.block.state.properties.BlockStateProperties
import net.minecraft.world.level.block.state.properties.BooleanProperty
import net.minecraft.world.level.block.state.properties.Property
import net.minecraft.world.level.gameevent.GameEvent
import net.minecraft.world.phys.BlockHitResult

@Suppress("OVERRIDE_DEPRECATION", "DEPRECATION")
class DepthChargeBlock(properties: Properties) : Block(properties) {
    override fun onPlace(state: BlockState, level: Level, pos: BlockPos, oldState: BlockState, isMoving: Boolean) {
        if (!oldState.`is`(state.block) && level.hasNeighborSignal(pos)) {
            explode(level, pos)
            level.removeBlock(pos, false)
        }
    }

    override fun neighborChanged(
        state: BlockState,
        level: Level,
        pos: BlockPos,
        block: Block,
        fromPos: BlockPos,
        isMoving: Boolean,
    ) {
        if (level.hasNeighborSignal(pos)) {
            explode(level, pos)
            level.removeBlock(pos, false)
        }
    }

    override fun playerWillDestroy(level: Level, pos: BlockPos, state: BlockState, player: Player) {
        if (!level.isClientSide() && !player.isCreative && state.getValue(UNSTABLE) as Boolean) {
            explode(level, pos)
        }

        super.playerWillDestroy(level, pos, state, player)
    }

    override fun wasExploded(level: Level, pos: BlockPos, explosion: Explosion) {
        if (!level.isClientSide) {
            val primedDepthCharge = PrimedDepthChargeEntity(
                level,
                pos.x.toDouble() + 0.5,
                pos.y.toDouble(),
                pos.z.toDouble() + 0.5,
                explosion.indirectSourceEntity
            )
            val i = primedDepthCharge.fuse
            primedDepthCharge.fuse = (level.random.nextInt(i / 4) + i / 8).toShort().toInt()
            level.addFreshEntity(primedDepthCharge)
        }
    }

    override fun use(
        state: BlockState,
        level: Level,
        pos: BlockPos,
        player: Player,
        hand: InteractionHand,
        hit: BlockHitResult,
    ): InteractionResult {
        val itemstack = player.getItemInHand(hand)
        if (!itemstack.`is`(Items.FLINT_AND_STEEL) && !itemstack.`is`(Items.FIRE_CHARGE)) {
            return super.use(state, level, pos, player, hand, hit)
        } else {
            explode(level, pos, player)
            level.setBlock(pos, Blocks.AIR.defaultBlockState(), 11)
            val item = itemstack.item
            if (!player.isCreative) {
                if (itemstack.`is`(Items.FLINT_AND_STEEL)) {
                    itemstack.hurtAndBreak(
                        1,
                        player
                    ) { player: Player -> player.broadcastBreakEvent(hand) }
                } else {
                    itemstack.shrink(1)
                }
            }

            player.awardStat(Stats.ITEM_USED.get(item))
            return InteractionResult.sidedSuccess(level.isClientSide)
        }
    }

    override fun onProjectileHit(level: Level, state: BlockState, hit: BlockHitResult, projectile: Projectile) {
        if (!level.isClientSide) {
            val blockpos = hit.blockPos
            val entity = projectile.owner
            if (projectile.isOnFire && projectile.mayInteract(level, blockpos)) {
                explode(level, blockpos, entity as? LivingEntity)
                level.removeBlock(blockpos, false)
            }
        }
    }

    override fun dropFromExplosion(explosion: Explosion): Boolean {
        return false
    }

    override fun createBlockStateDefinition(builder: StateDefinition.Builder<Block?, BlockState?>) {
        builder.add(*arrayOf<Property<*>?>(UNSTABLE))
    }

    init {
        this.registerDefaultState(this.defaultBlockState().setValue<Boolean, Boolean>(UNSTABLE, false) as BlockState)
    }

    companion object {
        val UNSTABLE: BooleanProperty = BlockStateProperties.UNSTABLE

        fun explode(level: Level, pos: BlockPos) {
            explode(level, pos, null as LivingEntity?)
        }

        private fun explode(level: Level, pos: BlockPos, entity: LivingEntity?) {
            if (!level.isClientSide) {
                val primedDepthCharge = PrimedDepthChargeEntity(
                    level,
                    pos.x.toDouble() + 0.5,
                    pos.y.toDouble(),
                    pos.z.toDouble() + 0.5,
                    entity
                )
                level.addFreshEntity(primedDepthCharge)
                level.playSound(
                    null as Player?,
                    primedDepthCharge.x,
                    primedDepthCharge.y,
                    primedDepthCharge.z,
                    SoundEvents.TNT_PRIMED,
                    SoundSource.BLOCKS,
                    1.0f,
                    1.0f
                )
                level.gameEvent(entity, GameEvent.PRIME_FUSE, pos)
            }
        }
    }
}